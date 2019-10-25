
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class LoginPaneGUI extends AnchorPane {

    protected final VBox vBox;
    protected final Label label;
    protected final TextField textField;
    protected final Label label0;
    protected final PasswordField passwordField;
    protected final CheckBox checkBox;
    protected final Button button;

    public LoginPaneGUI() {

        vBox = new VBox();
        label = new Label();
        textField = new TextField();
        label0 = new Label();
        passwordField = new PasswordField();
        checkBox = new CheckBox();
        button = new Button();

        setId("AnchorPane");
        setPrefHeight(370.0);
        setPrefWidth(339.0);

        vBox.setLayoutX(60.0);
        vBox.setLayoutY(71.0);
        vBox.setPrefHeight(114.0);
        vBox.setPrefWidth(220.0);
        vBox.setSpacing(15.0);

        label.setText("Username:");

        label0.setText("Password:");

        checkBox.setMnemonicParsing(false);
        checkBox.setText("Show/hide password");

        button.setMaxWidth(Double.MAX_VALUE);
        button.setMnemonicParsing(false);
        button.setText("LOGIN");
        VBox.setMargin(button, new Insets(50.0, 0.0, 0.0, 0.0));

        vBox.getChildren().add(label);
        vBox.getChildren().add(textField);
        vBox.getChildren().add(label0);
        vBox.getChildren().add(passwordField);
        vBox.getChildren().add(checkBox);
        vBox.getChildren().add(button);
        getChildren().add(vBox);

    }
}
