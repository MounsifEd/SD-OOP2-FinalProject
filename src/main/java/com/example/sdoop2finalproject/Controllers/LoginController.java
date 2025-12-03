//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.sdoop2finalproject.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class LoginController {
    // FXML paths and titles
    private static final String FXML_MANAGER = "/com/example/sdoop2finalproject/ManagerMovie/manager-view.fxml";
    private static final String FXML_CLIENT  = "/com/example/sdoop2finalproject/ClientSide/movie-view.fxml";
    private static final String TITLE_MANAGER = "Manager";
    private static final String TITLE_CLIENT  = "Movies";

    // Inline auth for manager
    private static final String MANAGER_USER = "manager";
    private static final String MANAGER_PASS = "manager123";

    // Styles
    private static final String DEFAULT_PWD_STYLE   = "-fx-font-size: 13px; -fx-padding: 10px; -fx-border-color: #cccccc; -fx-border-width: 1;";
    private static final String ERROR_PWD_STYLE     = "-fx-font-size: 13px; -fx-padding: 10px; -fx-border-color: #e53935; -fx-border-width: 2;";
    private static final String DEFAULT_EMAIL_STYLE = "-fx-font-size: 13px; -fx-padding: 10px; -fx-border-color: #cccccc; -fx-border-width: 1;";
    private static final String ERROR_EMAIL_STYLE   = "-fx-font-size: 13px; -fx-padding: 10px; -fx-border-color: #e53935; -fx-border-width: 2;";

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private CheckBox keepLoggedInCheckBox;
    @FXML private Button logInButton;

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField signupEmailField;
    @FXML private PasswordField signupPasswordField;
    @FXML private Button signUpButton;

    @FXML private VBox loginForm;
    @FXML private VBox signupForm;
    @FXML private Label signUpLink;
    @FXML private Label loginLink;

    @FXML private Label critLen;
    @FXML private Label critUpper;
    @FXML private Label critLower;
    @FXML private Label critDigit;
    @FXML private Label critSpecial;

    @FXML
    public void initialize() {
        // show only login by default
        signupForm.setVisible(false);
        signupForm.setManaged(false);

        // reset styles when user types valid values
        attachDefaultStyleOnInput(emailField, DEFAULT_EMAIL_STYLE);
        attachDefaultStyleOnInput(passwordField, DEFAULT_PWD_STYLE);
        attachDefaultStyleOnInput(firstNameField, DEFAULT_EMAIL_STYLE);
        attachDefaultStyleOnInput(lastNameField, DEFAULT_EMAIL_STYLE);
        attachDefaultStyleOnInput(signupEmailField, DEFAULT_EMAIL_STYLE);
        attachDefaultStyleOnInput(signupPasswordField, DEFAULT_PWD_STYLE);

        // password criteria for sign-up
        signupPasswordField.textProperty().addListener((obs, ov, nv) -> {
            String value = nv == null ? "" : nv;
            updatePasswordCriteria(value);
            if (isPasswordValid(value)) {
                signupPasswordField.setStyle(DEFAULT_PWD_STYLE);
            }
        });
        updatePasswordCriteria("");

        // Adjust visible criteria: show uppercase, hide lowercase and special
        critUpper.setVisible(true);
        critUpper.setManaged(true);
        critLower.setVisible(false);
        critLower.setManaged(false);
        critSpecial.setVisible(false);
        critSpecial.setManaged(false);

        // actions
        logInButton.setOnAction(e -> handleLogIn());
        signUpButton.setOnAction(e -> handleSignUp());
    }

    @FXML
    private void showSignUp() {
        loginForm.setVisible(false);
        loginForm.setManaged(false);
        signupForm.setVisible(true);
        signupForm.setManaged(true);
    }

    @FXML
    private void showLogin() {
        signupForm.setVisible(false);
        signupForm.setManaged(false);
        loginForm.setVisible(true);
        loginForm.setManaged(true);
    }

    private void attachDefaultStyleOnInput(TextField field, String defaultStyle) {
        field.textProperty().addListener((obs, ov, nv) -> {
            if (isNotEmpty(nv)) field.setStyle(defaultStyle);
        });
    }

    private void updatePasswordCriteria(String pw) {
        boolean lenOk = pw.length() >= 10 && pw.length() <= 20;
        boolean upperOk = pw.matches(".*[A-Z].*");
        boolean digitOk = pw.matches(".*\\d.*");

        setCriterion(critLen,   "10-20 characters",  lenOk);
        setCriterion(critUpper, "1 uppercase (A-Z)", upperOk);
        setCriterion(critDigit, "1 number",          digitOk);
    }

    private boolean isPasswordValid(String pw) {
        return pw != null
                && pw.length() >= 10 && pw.length() <= 20
                && pw.matches(".*[A-Z].*")
                && pw.matches(".*\\d.*");
    }

    private boolean isEmailValid(String email) {
        if (email == null) return false;
        String trimmed = email.trim();
        // Accept only specified providers with strictly lowercase domain and .com or .ca TLDs
        return trimmed.matches("^[A-Za-z0-9._%+-]+@(gmail|yahoo|hotmail|outlook|live|icloud)\\.(com|ca)$");
    }

    private boolean isNotEmpty(String text) {
        return text != null && !text.trim().isEmpty();
    }

    private boolean validateNotEmpty(TextField field, String fieldName) {
        if (!isNotEmpty(field.getText())) {
            field.setStyle(ERROR_EMAIL_STYLE);
            field.requestFocus();
            showErrorAlert("Invalid credentials");
            System.out.println(fieldName + " field is empty.");
            return false;
        }
        return true;
    }

    private void setCriterion(Label label, String base, boolean ok) {
        label.setText(ok ? "✓ " + base : base);
        label.setStyle("-fx-font-size: 12px; -fx-text-fill: " + (ok ? "#2e7d32" : "#666666") + ";");
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Invalid Credentials");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleLogIn() {
        String user = emailField.getText();
        String pw   = passwordField.getText();

        if (!isNotEmpty(user)) {
            emailField.setStyle(ERROR_EMAIL_STYLE);
            emailField.requestFocus();
            showErrorAlert("Invalid credentials");
            System.out.println("Login email/username field is empty.");
            return;
        }
        if (!isNotEmpty(pw)) {
            passwordField.setStyle(ERROR_PWD_STYLE);
            passwordField.requestFocus();
            showErrorAlert("Invalid credentials");
            System.out.println("Login password field is empty.");
            return;
        }

        if (MANAGER_USER.equalsIgnoreCase(user.trim()) && MANAGER_PASS.equals(pw)) {
            navigateTo(FXML_MANAGER, TITLE_MANAGER, logInButton);
            return;
        }
        navigateTo(FXML_CLIENT, TITLE_CLIENT, logInButton);
    }

    @FXML
    private void handleSignUp() {
        String firstName = firstNameField.getText();
        String lastName  = lastNameField.getText();
        String email     = signupEmailField.getText();
        String pw        = signupPasswordField.getText();

        if (!validateNotEmpty(firstNameField, "First name")) return;
        if (!validateNotEmpty(lastNameField, "Last name")) return;

        if (!isEmailValid(email)) {
            signupEmailField.setStyle(ERROR_EMAIL_STYLE);
            signupEmailField.requestFocus();
            showErrorAlert("Invalid credentials");
            System.out.println("Sign-up email is blank or missing '@'.");
            return;
        }
        if (!isPasswordValid(pw)) {
            signupPasswordField.setStyle(ERROR_PWD_STYLE);
            signupPasswordField.requestFocus();
            showErrorAlert("Invalid credentials");
            System.out.println("Password does not meet requirements.");
            return;
        }

        System.out.println("Sign up attempt - Name: " + firstName + " " + lastName + ", Email: " + email);
        navigateTo(FXML_CLIENT, TITLE_CLIENT, signUpButton);
    }

    @FXML
    private void handleForgotPassword() {
        System.out.println("Forgot password clicked");
    }

    private void navigateTo(String fxmlPath, String title, Node fromNode) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) fromNode.getScene().getWindow();
            // Keep a consistent window size across navigations
            stage.setScene(new Scene(root, 929, 648));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showErrorAlert("Unable to open page.");
        }
    }

}
