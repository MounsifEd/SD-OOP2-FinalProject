//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.sdoop2finalproject.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;

public class LoginController {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private CheckBox keepLoggedInCheckBox;
    @FXML
    private Button logInButton;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField signupEmailField;
    @FXML
    private PasswordField signupPasswordField;
    @FXML
    private Button signUpButton;
    @FXML
    private VBox loginForm;
    @FXML
    private VBox signupForm;
    @FXML
    private Label signUpLink;
    @FXML
    private Label loginLink;
    @FXML
    private Label critLen;
    @FXML
    private Label critUpper;
    @FXML
    private Label critLower;
    @FXML
    private Label critDigit;
    @FXML
    private Label critSpecial;
    private static final String DEFAULT_PWD_STYLE = "-fx-font-size: 13px; -fx-padding: 10px; -fx-border-color: #cccccc; -fx-border-width: 1;";
    private static final String ERROR_PWD_STYLE = "-fx-font-size: 13px; -fx-padding: 10px; -fx-border-color: #e53935; -fx-border-width: 2;";
    private static final String DEFAULT_EMAIL_STYLE = "-fx-font-size: 13px; -fx-padding: 10px; -fx-border-color: #cccccc; -fx-border-width: 1;";
    private static final String ERROR_EMAIL_STYLE = "-fx-font-size: 13px; -fx-padding: 10px; -fx-border-color: #e53935; -fx-border-width: 2;";

    @FXML
    public void initialize() {
        this.signupForm.setVisible(false);
        this.signupForm.setManaged(false);
        this.signupPasswordField.textProperty().addListener((obs, oldV, newV) -> {
            this.updatePasswordCriteria(newV == null ? "" : newV);
            if (this.isPasswordValid(newV)) {
                this.signupPasswordField.setStyle("-fx-font-size: 13px; -fx-padding: 10px; -fx-border-color: #cccccc; -fx-border-width: 1;");
            }

        });
        this.updatePasswordCriteria("");
        this.signupEmailField.textProperty().addListener((obs, oldV, newV) -> {
            if (this.isEmailValid(newV)) {
                this.signupEmailField.setStyle("-fx-font-size: 13px; -fx-padding: 10px; -fx-border-color: #cccccc; -fx-border-width: 1;");
            }

        });
        this.firstNameField.textProperty().addListener((obs, ov, nv) -> {
            if (this.isNotEmpty(nv)) {
                this.firstNameField.setStyle("-fx-font-size: 13px; -fx-padding: 10px; -fx-border-color: #cccccc; -fx-border-width: 1;");
            }

        });
        this.lastNameField.textProperty().addListener((obs, ov, nv) -> {
            if (this.isNotEmpty(nv)) {
                this.lastNameField.setStyle("-fx-font-size: 13px; -fx-padding: 10px; -fx-border-color: #cccccc; -fx-border-width: 1;");
            }

        });
        this.emailField.textProperty().addListener((obs, ov, nv) -> {
            if (this.isNotEmpty(nv)) {
                this.emailField.setStyle("-fx-font-size: 13px; -fx-padding: 10px; -fx-border-color: #cccccc; -fx-border-width: 1;");
            }

        });
        this.passwordField.textProperty().addListener((obs, ov, nv) -> {
            if (this.isNotEmpty(nv)) {
                this.passwordField.setStyle("-fx-font-size: 13px; -fx-padding: 10px; -fx-border-color: #cccccc; -fx-border-width: 1;");
            }

        });
        this.logInButton.setOnAction((event) -> this.handleLogIn());
        this.signUpButton.setOnAction((event) -> this.handleSignUp());
    }

    @FXML
    private void showSignUp() {
        this.loginForm.setVisible(false);
        this.loginForm.setManaged(false);
        this.signupForm.setVisible(true);
        this.signupForm.setManaged(true);
    }

    @FXML
    private void showLogin() {
        this.signupForm.setVisible(false);
        this.signupForm.setManaged(false);
        this.loginForm.setVisible(true);
        this.loginForm.setManaged(true);
    }

    private void updatePasswordCriteria(String pw) {
        boolean lenOk = pw.length() >= 10 && pw.length() <= 20;
        boolean upperOk = pw.matches(".*[A-Z].*");
        boolean lowerOk = pw.matches(".*[a-z].*");
        boolean digitOk = pw.matches(".*\\d.*");
        boolean specialOk = pw.matches(".*[^A-Za-z0-9].*");
        String baseLen = "10-20 characters";
        String baseUpper = "1 uppercase (A-Z)";
        String baseLower = "1 lowercase (a-z)";
        String baseDigit = "1 number";
        String baseSpecial = "1 non-alphanumeric";
        this.setCriterion(this.critLen, baseLen, lenOk);
        this.setCriterion(this.critUpper, baseUpper, upperOk);
        this.setCriterion(this.critLower, baseLower, lowerOk);
        this.setCriterion(this.critDigit, baseDigit, digitOk);
        this.setCriterion(this.critSpecial, baseSpecial, specialOk);
    }

    private boolean isPasswordValid(String pw) {
        if (pw == null) {
            return false;
        } else {
            return pw.length() >= 10 && pw.length() <= 20 && pw.matches(".*[A-Z].*") && pw.matches(".*[a-z].*") && pw.matches(".*\\d.*") && pw.matches(".*[^A-Za-z0-9].*");
        }
    }

    private boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        } else {
            String trimmed = email.trim();
            return !trimmed.isEmpty() && trimmed.contains("@");
        }
    }

    private boolean isNotEmpty(String text) {
        return text != null && !text.trim().isEmpty();
    }

    private boolean validateNotEmpty(TextField field, String fieldName) {
        if (!this.isNotEmpty(field.getText())) {
            field.setStyle("-fx-font-size: 13px; -fx-padding: 10px; -fx-border-color: #e53935; -fx-border-width: 2;");
            field.requestFocus();
            this.showErrorAlert("Invalid credentials");
            System.out.println(fieldName + " field is empty.");
            return false;
        } else {
            return true;
        }
    }

    private void setCriterion(Label label, String base, boolean ok) {
        label.setText(ok ? "✓ " + base : base);
        label.setStyle("-fx-font-size: 12px; -fx-text-fill: " + (ok ? "#2e7d32" : "#666666") + ";");
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Invalid Credentials");
        alert.setHeaderText((String)null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleLogIn() {
        String email = this.emailField.getText();
        String password = this.passwordField.getText();
        boolean keepLoggedIn = this.keepLoggedInCheckBox.isSelected();
        if (!this.isNotEmpty(email)) {
            this.emailField.setStyle("-fx-font-size: 13px; -fx-padding: 10px; -fx-border-color: #e53935; -fx-border-width: 2;");
            this.emailField.requestFocus();
            this.showErrorAlert("Invalid credentials");
            System.out.println("Login email/username field is empty.");
        } else if (!this.isNotEmpty(password)) {
            this.passwordField.setStyle("-fx-font-size: 13px; -fx-padding: 10px; -fx-border-color: #e53935; -fx-border-width: 2;");
            this.passwordField.requestFocus();
            this.showErrorAlert("Invalid credentials");
            System.out.println("Login password field is empty.");
        } else {
            System.out.println("Login attempt - Email/Username: " + email + ", Keep logged in: " + keepLoggedIn);
        }
    }

    @FXML
    private void handleSignUp() {
        String firstName = this.firstNameField.getText();
        String lastName = this.lastNameField.getText();
        String email = this.signupEmailField.getText();
        String password = this.signupPasswordField.getText();
        if (this.validateNotEmpty(this.firstNameField, "First name")) {
            if (this.validateNotEmpty(this.lastNameField, "Last name")) {
                if (!this.isEmailValid(email)) {
                    this.signupEmailField.setStyle("-fx-font-size: 13px; -fx-padding: 10px; -fx-border-color: #e53935; -fx-border-width: 2;");
                    this.signupEmailField.requestFocus();
                    this.showErrorAlert("Invalid credentials");
                    System.out.println("Sign-up email is blank or missing '@'.");
                } else if (!this.isPasswordValid(password)) {
                    this.signupPasswordField.setStyle("-fx-font-size: 13px; -fx-padding: 10px; -fx-border-color: #e53935; -fx-border-width: 2;");
                    this.signupPasswordField.requestFocus();
                    this.showErrorAlert("Invalid credentials");
                    System.out.println("Password does not meet requirements.");
                } else {
                    System.out.println("Sign up attempt - Name: " + firstName + " " + lastName + ", Email: " + email);
                }
            }
        }
    }

    @FXML
    private void handleForgotPassword() {
        System.out.println("Forgot password clicked");
    }
}
