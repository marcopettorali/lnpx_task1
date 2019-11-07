
import javafx.scene.text.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

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
    private TextField passwordTextField;
    private Label repeatPasswordLabel;
    private PasswordField repeatPasswordTextField;
    private Button signupButton;
    private Button goBackToLoginButton;
    
    public void buildSignupButton() {
        signupButton.setId("signupButton");
        signupButton.setMaxWidth(Double.MAX_VALUE);
        signupButton.setMnemonicParsing(false);
        signupButton.setText("REGISTER");
        VBox.setMargin(signupButton, new Insets(50.0, 0.0, 0.0, 0.0));
        signupButton.setOnAction(e -> {
            
        });

    }
    
    public void buildGoBackToLoginButton() {
        goBackToLoginButton.setId("signupButton");
        goBackToLoginButton.setMaxWidth(Double.MAX_VALUE);
        goBackToLoginButton.setMnemonicParsing(false);
        goBackToLoginButton.setText("REGISTER");
        VBox.setMargin(goBackToLoginButton, new Insets(50.0, 0.0, 0.0, 0.0));
        goBackToLoginButton.setOnAction(e -> {
            
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
        passwordTextField = new TextField();
        repeatPasswordLabel = new Label();
        repeatPasswordTextField = new PasswordField();
        
        buildSignupButton();

        setId("loginPane");
        setPrefHeight(633.0);
        setPrefWidth(421.0);

        signupVBox.setId("signupVBox");
        signupVBox.setLayoutX(77.0);
        signupVBox.setLayoutY(48.0);
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
        usernameLabel.setText("Username:");

        usernameTextField.setEditable(false);
        usernameTextField.setId("usernameTextField");
        usernameTextField.setMaxWidth(Double.MAX_VALUE);
        usernameTextField.setPrefHeight(25.0);
        usernameTextField.setPrefWidth(197.0);
        usernameTextField.setPromptText("Your assigned username");
        VBox.setMargin(usernameHBox, new Insets(10.0, 0.0, 10.0, 0.0));

        passwordLabel.setId("passwordLabel");
        passwordLabel.setText("Password:");

        passwordTextField.setId("passwordTextField");

        repeatPasswordLabel.setId("repeatPasswordLabel");
        repeatPasswordLabel.setText("Repeat password:");

        repeatPasswordTextField.setId("repeatPasswordTextField");

        signupVBox.getChildren().add(signupLabel);
        signupVBox.getChildren().add(matriculationLabel);
        signupVBox.getChildren().add(matriculationTextField);
        signupVBox.getChildren().add(firstNameLabel);
        signupVBox.getChildren().add(firstNameTextField);
        signupVBox.getChildren().add(lastNameLabel);
        signupVBox.getChildren().add(lastNameTextField);
        usernameHBox.getChildren().add(usernameLabel);
        usernameHBox.getChildren().add(usernameTextField);
        signupVBox.getChildren().add(usernameHBox);
        signupVBox.getChildren().add(passwordLabel);
        signupVBox.getChildren().add(passwordTextField);
        signupVBox.getChildren().add(repeatPasswordLabel);
        signupVBox.getChildren().add(repeatPasswordTextField);
        signupVBox.getChildren().add(signupButton);
        getChildren().add(signupVBox);

    }
}
