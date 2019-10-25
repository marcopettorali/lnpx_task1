
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MainPaneGUI extends HBox {

    protected final VBox vBox;
    protected final VBox vBox0;
    protected final DatePicker datePicker;
    protected final ComboBox comboBox;
    protected final Button button;
    protected final Separator separator;
    protected final Label label;
    protected final TableView tableView;
    protected final TableColumn tableColumn;
    protected final TableColumn tableColumn0;
    protected final Button button0;
    protected final Separator separator0;
    protected final VBox vBox1;
    protected final Label label0;
    protected final TableView tableView0;
    protected final TableColumn tableColumn1;
    protected final TableColumn tableColumn2;
    protected final Button button1;

    public MainPaneGUI() {

        vBox = new VBox();
        vBox0 = new VBox();
        datePicker = new DatePicker();
        comboBox = new ComboBox();
        button = new Button();
        separator = new Separator();
        label = new Label();
        tableView = new TableView();
        tableColumn = new TableColumn();
        tableColumn0 = new TableColumn();
        button0 = new Button();
        separator0 = new Separator();
        vBox1 = new VBox();
        label0 = new Label();
        tableView0 = new TableView();
        tableColumn1 = new TableColumn();
        tableColumn2 = new TableColumn();
        button1 = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(600.0);
        setPrefWidth(800.0);

        vBox.setPrefHeight(560.0);
        vBox.setPrefWidth(363.0);

        vBox0.setMaxHeight(USE_PREF_SIZE);
        vBox0.setMaxWidth(USE_PREF_SIZE);
        vBox0.setMinHeight(USE_PREF_SIZE);
        vBox0.setMinWidth(USE_PREF_SIZE);
        vBox0.setPrefHeight(113.0);
        vBox0.setPrefWidth(146.0);
        vBox0.setSpacing(15.0);

        comboBox.setPrefWidth(150.0);

        button.setMaxWidth(Double.MAX_VALUE);
        button.setMnemonicParsing(false);
        button.setText("SEARCH");

        separator.setPrefWidth(200.0);
        VBox.setMargin(separator, new Insets(50.0, 0.0, 20.0, 0.0));

        label.setText("My reservations:");

        tableView.setPrefHeight(378.0);
        tableView.setPrefWidth(352.0);

        tableColumn.setPrefWidth(75.0);
        tableColumn.setText("C1");

        tableColumn0.setPrefWidth(75.0);
        tableColumn0.setText("C2");
        VBox.setMargin(tableView, new Insets(20.0, 0.0, 0.0, 0.0));

        button0.setMnemonicParsing(false);
        button0.setText("Delete reservation");
        VBox.setMargin(button0, new Insets(20.0, 0.0, 0.0, 0.0));
        HBox.setMargin(vBox, new Insets(20.0));

        separator0.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator0.setPrefHeight(200.0);
        separator0.setStyle("-fx-background-color: light-grey;");

        vBox1.setMaxWidth(Double.MAX_VALUE);
        vBox1.setPrefHeight(560.0);
        vBox1.setPrefWidth(373.0);

        label0.setText("Available rooms:");

        tableView0.setPrefHeight(502.0);
        tableView0.setPrefWidth(362.0);

        tableColumn1.setPrefWidth(75.0);
        tableColumn1.setText("C1");

        tableColumn2.setPrefWidth(75.0);
        tableColumn2.setText("C2");
        VBox.setMargin(tableView0, new Insets(20.0, 0.0, 0.0, 0.0));

        button1.setMnemonicParsing(false);
        button1.setText("Reserve");
        VBox.setMargin(button1, new Insets(20.0, 0.0, 0.0, 0.0));
        HBox.setMargin(vBox1, new Insets(20.0));

        vBox0.getChildren().add(datePicker);
        vBox0.getChildren().add(comboBox);
        vBox0.getChildren().add(button);
        vBox.getChildren().add(vBox0);
        vBox.getChildren().add(separator);
        vBox.getChildren().add(label);
        tableView.getColumns().add(tableColumn);
        tableView.getColumns().add(tableColumn0);
        vBox.getChildren().add(tableView);
        vBox.getChildren().add(button0);
        getChildren().add(vBox);
        getChildren().add(separator0);
        vBox1.getChildren().add(label0);
        tableView0.getColumns().add(tableColumn1);
        tableView0.getColumns().add(tableColumn2);
        vBox1.getChildren().add(tableView0);
        vBox1.getChildren().add(button1);
        getChildren().add(vBox1);

    }
}
