
import javafx.scene.text.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class SignupPaneGUI extends AnchorPane {

    private VBox signupVBox;
    private Label signupLabel;
    private Label matriculationLabel;
    private TextField matriculationTextField;
    private Label firstNameLabel;
    private TextField firstNameTextField;
    private Label lastNameLabel;
    private TextField lastNameTextField;
    private HBox usernameHBox;
    private Label usernameLabel;
    private TextField usernameTextField;
    private Label passwordLabel;
    private PasswordField passwordTextField;
    private Label repeatPasswordLabel;
    private PasswordField repeatPasswordTextField;
    private Button signupButton;
    private Button goBackToLoginButton;
    private Label errorLabel;

    public void buildSignupButton() {
        signupButton = new Button();
        signupButton.setId("signupButton");
        signupButton.setMaxWidth(Double.MAX_VALUE);
        signupButton.setMnemonicParsing(false);
        signupButton.setText("REGISTER");
        VBox.setMargin(signupButton, new Insets(30.0, 0.0, 0.0, 0.0));
        signupButton.setOnAction(e -> {
            
            String matriculationString = matriculationTextField.getText();
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();
            String password = passwordTextField.getText();

            if (matriculationString.equals("") || firstName.equals("") || lastName.equals("") || password.equals("")) {
                errorLabel.setText("Some of the fields are empty");
                return;
            }
            
            for (char c : matriculationString.toCharArray()) {
                if (!Character.isDigit(c)) {
                    errorLabel.setText("Matriculation must be a number");
                    return;
                }
            }
            
            int matriculation = Integer.parseInt(matriculationString);

            for (char c : firstName.toCharArray()) {
                if (Character.isDigit(c)) {
                    errorLabel.setText("First name must not contain a number");
                    return;
                }
            }
            
            for (char c : lastName.toCharArray()) {
                if (Character.isDigit(c)) {
                    errorLabel.setText("Last name must not contain a number");
                    return;
                }
            }
            
            if (!passwordTextField.getText().equals(repeatPasswordTextField.getText())) {
                errorLabel.setText("Repeated password does not match");
                return;
            }

            String username = PCBookingApplicationController.signup(matriculation, firstName, lastName, password);
            usernameTextField.setText(username);

        });

    }

    public void buildGoBackToLoginButton() {
        goBackToLoginButton = new Button();
        goBackToLoginButton.setId("signupButton");
        goBackToLoginButton.setMaxWidth(Double.MAX_VALUE);
        goBackToLoginButton.setMnemonicParsing(false);
        goBackToLoginButton.setText("Go back to login screen");
        VBox.setMargin(goBackToLoginButton, new Insets(10.0, 0.0, 0.0, 0.0));
        goBackToLoginButton.setOnAction(e -> {
            PCBookingApplicationController.backToLoginForm();
        });

    }

    public SignupPaneGUI() {

        signupVBox = new VBox();
        signupLabel = new Label();
        matriculationLabel = new Label();
        matriculationTextField = new TextField();
        firstNameLabel = new Label();
        firstNameTextField = new TextField();
        lastNameLabel = new Label();
        lastNameTextField = new TextField();
        usernameHBox = new HBox();
        usernameLabel = new Label();
        usernameTextField = new TextField();
        passwordLabel = new Label();
        passwordTextField = new PasswordField();
        repeatPasswordLabel = new Label();
        repeatPasswordTextField = new PasswordField();
        errorLabel = new Label();

        buildSignupButton();
        buildGoBackToLoginButton();

        setId("loginPane");
        setPrefHeight(633.0);
        setPrefWidth(421.0);

        signupVBox.setId("signupVBox");
        signupVBox.setLayoutX(77.0);
        signupVBox.setLayoutY(30.0);
        signupVBox.setPrefHeight(517.0);
        signupVBox.setPrefWidth(267.0);
        signupVBox.setSpacing(15.0);

        signupLabel.setId("signupLabel");
        signupLabel.setText("SIGN UP");
        signupLabel.setFont(new Font(18.0));

        matriculationLabel.setId("matriculationLabel");
        matriculationLabel.setText("Matriculation number:");

        matriculationTextField.setId("matriculationTextField");

        firstNameLabel.setId("firstNameLabel");
        firstNameLabel.setText("First Name:");

        firstNameTextField.setId("firstNameTextField");

        lastNameLabel.setId("lastNameLabel");
        lastNameLabel.setText("Last Name:");

        lastNameTextField.setId("lastNameTextField");

        usernameHBox.setAlignment(javafx.geometry.Pos.CENTER);
        usernameHBox.setId("usernameHBox");
        usernameHBox.setPrefHeight(100.0);
        usernameHBox.setPrefWidth(200.0);
        usernameHBox.setSpacing(15.0);

        usernameLabel.setId("usernameLabel");
        usernameLabel.setText("Assigned username:");

        usernameTextField.setEditable(false);
        usernameTextField.setId("usernameTextField");
        usernameTextField.setMaxWidth(Double.MAX_VALUE);
        usernameTextField.setPrefHeight(25.0);
        usernameTextField.setPrefWidth(147.0);
        usernameTextField.setPromptText("Your assigned username");
        VBox.setMargin(usernameHBox, new Insets(20.0, 0.0, 0.0, 0.0));

        passwordLabel.setId("passwordLabel");
        passwordLabel.setText("Password:");

        passwordTextField.setId("passwordTextField");

        repeatPasswordLabel.setId("repeatPasswordLabel");
        repeatPasswordLabel.setText("Repeat password:");

        repeatPasswordTextField.setId("repeatPasswordTextField");

        errorLabel.setTextFill(Color.RED);
        errorLabel.setAlignment(Pos.CENTER_LEFT);

        signupVBox.getChildren().add(signupLabel);
        signupVBox.getChildren().add(matriculationLabel);
        signupVBox.getChildren().add(matriculationTextField);
        signupVBox.getChildren().add(firstNameLabel);
        signupVBox.getChildren().add(firstNameTextField);
        signupVBox.getChildren().add(lastNameLabel);
        signupVBox.getChildren().add(lastNameTextField);
        usernameHBox.getChildren().add(usernameLabel);
        usernameHBox.getChildren().add(usernameTextField);

        signupVBox.getChildren().add(passwordLabel);
        signupVBox.getChildren().add(passwordTextField);
        signupVBox.getChildren().add(repeatPasswordLabel);
        signupVBox.getChildren().add(repeatPasswordTextField);

        signupVBox.getChildren().add(errorLabel);

        signupVBox.getChildren().add(signupButton);
        signupVBox.getChildren().add(usernameHBox);
        signupVBox.getChildren().add(goBackToLoginButton);

        getChildren().add(signupVBox);

    }
}
