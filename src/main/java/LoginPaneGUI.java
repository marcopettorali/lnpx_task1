
import java.util.ArrayList;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class LoginPaneGUI extends AnchorPane {

    private VBox loginVBox;
    private Label usernameLabel;
    private TextField usernameTextField;
    private Label passwordLabel;
    private PasswordField passwordTextField;
    private TextField visiblePasswordTextField;
    private CheckBox showPasswordCheckBox;
    private Button loginButton;
    private Label errorLabel;

    public void buildLoginButton() {
        loginButton = new Button();
        loginButton.setId("loginButton");
        loginButton.setMaxWidth(Double.MAX_VALUE);
        loginButton.setText("LOGIN");
        VBox.setMargin(loginButton, new Insets(50.0, 0.0, 0.0, 0.0));
        loginButton.setOnAction(e -> {
            if (!PCBookingApplicationController.login(usernameTextField.getText(), passwordTextField.getText())) {
                errorLabel.setText("Username or password not valid");
            }
        });

    }

    public LoginPaneGUI() {

        loginVBox = new VBox();
        usernameLabel = new Label();
        usernameTextField = new TextField();
        passwordLabel = new Label();
        passwordTextField = new PasswordField();
        visiblePasswordTextField = new TextField();
        showPasswordCheckBox = new CheckBox();
        errorLabel = new Label("");

        buildLoginButton();

        setId("loginPane");
        setPrefHeight(370.0);
        setPrefWidth(339.0);

        loginVBox.setId("loginVBox");
        loginVBox.setLayoutX(60.0);
        loginVBox.setLayoutY(71.0);
        loginVBox.setPrefHeight(114.0);
        loginVBox.setPrefWidth(220.0);
        loginVBox.setSpacing(15.0);

        usernameLabel.setId("usernameLabel");
        usernameLabel.setText("Username:");

        usernameTextField.setId("usernameTextField");

        passwordLabel.setId("passwordLabel");
        passwordLabel.setText("Password:");

        passwordTextField.setId("passwordTextField");

        showPasswordCheckBox.setId("showPasswordCheckBox");
        showPasswordCheckBox.setMnemonicParsing(false);
        showPasswordCheckBox.setText("Show password");

        visiblePasswordTextField.setManaged(false);
        visiblePasswordTextField.setVisible(false);

        visiblePasswordTextField.managedProperty().bind(showPasswordCheckBox.selectedProperty());
        visiblePasswordTextField.visibleProperty().bind(showPasswordCheckBox.selectedProperty());

        passwordTextField.managedProperty().bind(showPasswordCheckBox.selectedProperty().not());
        passwordTextField.visibleProperty().bind(showPasswordCheckBox.selectedProperty().not());

        visiblePasswordTextField.textProperty().bindBidirectional(passwordTextField.textProperty());

        errorLabel.setTextFill(Color.RED);
        errorLabel.setAlignment(Pos.CENTER_LEFT);

        loginVBox.getChildren().add(usernameLabel);
        loginVBox.getChildren().add(usernameTextField);
        loginVBox.getChildren().add(passwordLabel);
        loginVBox.getChildren().add(passwordTextField);
        loginVBox.getChildren().add(visiblePasswordTextField);
        loginVBox.getChildren().add(showPasswordCheckBox);
        loginVBox.getChildren().add(errorLabel);
        loginVBox.getChildren().add(loginButton);

        getChildren().add(loginVBox);

    }
}
