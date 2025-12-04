# LoginController – Simple Guide

Purpose:
- One controller handles both Login and Sign Up on a single screen.
- Keep the code easy to read and explain (intro-level friendly).

What it connects to (FXML):
- Login fields: emailField, passwordField, logInButton
- Sign Up fields: firstNameField, lastNameField, signupEmailField, signupPasswordField, signUpButton
- Containers to show/hide: loginForm, signupForm
- Live password criteria labels: critLen, critUpper, critDigit

Manager vs Client:
- Manager logs in with a username and password:
  - Username: "manager"
  - Password: "manager123"
- Clients log in with a valid email address and a password.

1) initialize()
- Binds each form’s "managed" to its "visible" so hidden forms don’t take layout space.
- Shows only the Login form by default.
- Adds a listener on signupPasswordField to update the three live criteria (length, uppercase, number).
- Wires the two buttons:
  - logInButton → handleLogIn()
  - signUpButton → handleSignUp()

2) Toggling forms
- showSignUp() and showLogin() call toggle(true/false).
- toggle(boolean) switches only the "visible" flags.
- Because "managed" is bound to "visible", the hidden form is fully collapsed.

3) handleLogIn()
- Reads user and password from the fields.
- setOk(field, condition) colors the border:
  - Gray border when valid
  - Red border when invalid
- If either field is empty, show a generic error alert and stop.
- If the user matches the manager username and password, navigate to the Manager screen.
- Otherwise, enforce client login:
  - Email must be valid (only selected providers and .com/.ca).
  - If valid, open the Client screen.

4) handleSignUp()
- Reads first name, last name, email, and password.
- Validates each field using setOk():
  - First and last name must NOT be empty.
  - Email must be valid.
  - Password must be 10–20 chars and include at least 1 uppercase and 1 digit.
- If any is invalid, shows a generic error alert and stops.
- If all are valid, navigates to the Client screen (placeholder action).

5) Live password criteria (Sign Up)
- updatePasswordCriteria(String):
  - critLen turns green when length is 10–20.
  - critUpper turns green when there is at least one uppercase letter.
  - critDigit turns green when there is at least one number.
- setCrit() sets label color to green when condition is true, gray otherwise.

6) Small helpers
- setOk(TextField, boolean): sets the border color for valid/invalid and returns the validity.
- isNotEmpty(String): true when the string has text.
- isEmailValid(String): matches a simple pattern (gmail/yahoo/hotmail/outlook/live/icloud with .com or .ca).
- isPasswordValid(String): length 10–20, has uppercase and digit.
- alert(String): shows a small error popup.

7) Navigation
- navigateTo(fxmlPath, title, fromNode):
  - Loads the next view.
  - Reuses the same window by replacing the Scene’s root.
  - Shows a small error popup if the page can’t load.

How to present it quickly:
- Start with “We keep one screen and toggle two forms.”
- Show initialize() for the default state and live criteria.
- Show toggle() for form switching.
- Show handleLogIn() flow: validate → manager or client path.
- Show handleSignUp() flow: field-by-field validation → navigate.
- Show the tiny helpers for borders, validation, and alerts.
