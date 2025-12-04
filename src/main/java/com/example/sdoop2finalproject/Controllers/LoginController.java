package com.example.sdoop2finalproject.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Controller for the login/sign-up screen.
 * Shows login by default, lets users switch to sign-up, validates inputs with simple rules,
 * highlights invalid fields, and opens the next view on success.
 *
 * @author pascale
 */
public class LoginController {
    /** Path and title for the manager view. */
    private static final String FXML_MANAGER = "/com/example/sdoop2finalproject/ManagerMovie/manager-view.fxml";
    /** Path and title for the client view. */
    private static final String FXML_CLIENT  = "/com/example/sdoop2finalproject/ClientSide/movie-view.fxml";
    private static final String TITLE_MANAGER = "Manager";
    private static final String TITLE_CLIENT  = "Movies";

    /** Inline manager credentials (kept simple for presentation). */
    private static final String MANAGER_USER = "manager";
    private static final String MANAGER_PASS = "manager123";

    // --- Login fields ---
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Button logInButton;

    // --- Sign Up fields ---
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField signupEmailField;
    @FXML private PasswordField signupPasswordField;
    @FXML private Button signUpButton;

    // --- Containers (one visible at a time) ---
    @FXML private VBox loginForm;
    @FXML private VBox signupForm;

    // --- Live password criteria (Sign Up) ---
    @FXML private Label critLen;
    @FXML private Label critUpper;
    @FXML private Label critDigit;

    /**
     * Runs when the view loads.
     * Shows the login form, collapses the sign-up form, wires buttons, and enables live password checks.
     */
    @FXML
    public void initialize() {
        loginForm.managedProperty().bind(loginForm.visibleProperty());
        signupForm.managedProperty().bind(signupForm.visibleProperty());
        loginForm.setVisible(true);
        signupForm.setVisible(false);

        signupPasswordField.textProperty().addListener((obs, ov, nv) -> updatePasswordCriteria(nv == null ? "" : nv));
        updatePasswordCriteria("");

        logInButton.setOnAction(e -> handleLogIn());
        signUpButton.setOnAction(e -> handleSignUp());
    }

    /**
     * Shows the Sign Up section and hides the Login section.
     */
    @FXML
    private void showSignUp() {
        toggleForms(true);
    }

    /**
     * Shows the Login section and hides the Sign Up section.
     */
    @FXML
    private void showLogin() {
        toggleForms(false);
    }

    /**
     * Toggles which form is visible.
     * managed is already bound to visible, so the hidden form collapses.
     *
     * @param showSignup true to show Sign Up; false to show Login
     */
    private void toggleForms(boolean showSignup) {
        signupForm.setVisible(showSignup);
        loginForm.setVisible(!showSignup);
    }

    /**
     * Log in flow.
     * - Manager: logs in with username + password.
     * - Client: must enter a valid email and a password that meets the same rules as sign-up.
     * Shows one generic error if something is missing/invalid, otherwise opens the next screen.
     */
    @FXML
    private void handleLogIn() {
        String user = emailField.getText();
        String pw   = passwordField.getText();

        boolean ok = setOk(emailField, isNotEmpty(user)) & setOk(passwordField, isNotEmpty(pw));
        if (!ok) { inputError(); return; }

        String trimmed = user.trim();

        // Manager login path by username
        if (MANAGER_USER.equalsIgnoreCase(trimmed) && MANAGER_PASS.equals(pw)) {
            navigateTo(FXML_MANAGER, TITLE_MANAGER, logInButton);
            return;
        }

        // Client path: require valid email and password criteria
        if (!isEmailValid(trimmed)) {
            setOk(emailField, false);
            inputError();
            return;
        }
        setOk(emailField, true);

        if (!isPasswordValid(pw)) {
            setOk(passwordField, false);
            inputError();
            return;
        }
        setOk(passwordField, true);

        navigateTo(FXML_CLIENT, TITLE_CLIENT, logInButton);
    }

    /**
     * Sign-up flow.
     * Checks first/last name, email, and password against simple rules, marks bad fields in red,
     * shows one generic error if needed, and opens the client view on success.
     */
    @FXML
    private void handleSignUp() {
        String first = firstNameField.getText();
        String last  = lastNameField.getText();
        String mail  = signupEmailField.getText();
        String pw    = signupPasswordField.getText();

        boolean ok =
                setOk(firstNameField,  isNotEmpty(first)) &
                setOk(lastNameField,   isNotEmpty(last))  &
                setOk(signupEmailField, isEmailValid(mail)) &
                setOk(signupPasswordField, isPasswordValid(pw));

        if (!ok) { inputError(); return; }

        navigateTo(FXML_CLIENT, TITLE_CLIENT, signUpButton);
    }

    /** Default gray border; error red border for invalid fields. */
    private static final String DEFAULT_FIELD_STYLE = "-fx-font-size: 13px; -fx-padding: 10px; -fx-border-color: #cccccc; -fx-border-width: 1;";
    private static final String ERROR_FIELD_STYLE   = "-fx-font-size: 13px; -fx-padding: 10px; -fx-border-color: #e53935; -fx-border-width: 2;";

    // Base texts for live criteria (used to show ✓ when satisfied)
    private static final String CRIT_LEN_TEXT   = "10-20 characters";
    private static final String CRIT_UPPER_TEXT = "1 uppercase (A-Z)";
    private static final String CRIT_DIGIT_TEXT = "1 number";

    /**
     * Sets the field border based on validity and returns the validity for chaining.
     *
     * @param field the input field to style
     * @param valid true if the field is valid
     * @return the same boolean valid, to allow compact boolean expressions
     */
    private boolean setOk(TextField field, boolean valid) {
        field.setStyle(valid ? DEFAULT_FIELD_STYLE : ERROR_FIELD_STYLE);
        return valid;
    }

    /**
     * Updates the three live password criteria labels in Sign Up.
     * Criteria: length 10–20, at least one uppercase letter, and at least one digit.
     *
     * @param pw current password text
     */
    private void updatePasswordCriteria(String pw) {
        String v = pw == null ? "" : pw;
        boolean lenOk   = v.length() >= 10 && v.length() <= 20;
        boolean upperOk = v.matches(".*[A-Z].*");
        boolean digitOk = v.matches(".*\\d.*");

        setCriterion(critLen,   lenOk,   CRIT_LEN_TEXT);
        setCriterion(critUpper, upperOk, CRIT_UPPER_TEXT);
        setCriterion(critDigit, digitOk, CRIT_DIGIT_TEXT);
    }

    /**
     * Colors a criterion label and shows a check mark when met.
     *
     * @param label the label to update
     * @param ok true if the criterion is met
     * @param baseText the plain text of the criterion
     */
    private void setCriterion(Label label, boolean ok, String baseText) {
        if (label == null) return;
        label.setText(ok ? "✓ " + baseText : baseText);
        label.setStyle("-fx-font-size: 12px; -fx-text-fill: " + (ok ? "#2e7d32" : "#666666") + ";");
    }

    /**
     * Loads a new FXML and swaps it into the same window.
     *
     * @param fxmlPath FXML resource to load
     * @param title window title to set
     * @param fromNode any node on the current scene (to find the Stage)
     */
    private void navigateTo(String fxmlPath, String title, Node fromNode) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) fromNode.getScene().getWindow();
            if (stage.getScene() == null) {
                stage.setScene(new Scene(root, 929, 648));
            } else {
                stage.getScene().setRoot(root);
            }
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Navigation Error");
            a.setHeaderText(null);
            a.setContentText("Unable to open page.");
            a.showAndWait();
        }
    }

    /**
     * Shows one generic validation error dialog.
     */
    private void inputError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);
        alert.setContentText("Missing or invalid information. Please check your entries.");
        alert.showAndWait();
    }

    /**
     * @param text any string
     * @return true when text is non-null and not blank
     */
    private boolean isNotEmpty(String text) {
        return text != null && !text.trim().isEmpty();
    }

    /**
     * Validates client email format (selected providers + .com/.ca).
     *
     * @param email email string
     * @return true when the email matches the pattern
     */
    private boolean isEmailValid(String email) {
        if (email == null) return false;
        String trimmed = email.trim();
        return trimmed.matches("^[A-Za-z0-9._%+-]+@(gmail|yahoo|hotmail|outlook|live|icloud)\\.(com|ca)$");
    }

    /**
     * Password must be 10–20 chars and contain at least one uppercase and one digit.
     *
     * @param pw password text
     * @return true if the password meets the rules
     */
    private boolean isPasswordValid(String pw) {
        return pw != null
                && pw.length() >= 10 && pw.length() <= 20
                && pw.matches(".*[A-Z].*")
                && pw.matches(".*\\d.*");
    }
}
