package ComplexCalculator;

import ComplexCalculatorException.InvalidInputException;
import ComplexCalculatorException.NotEnoughDataException;
import ComplexCalculatorException.NotEnoughStackElementsException;
import ComplexCalculatorException.VariableException;
import ComplexCalculatorException.ZeroDivisionException;
import ComplexCalculatorOperation.Calculator;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;

/**
 * The ComplexCalculatorController class controls the Calculator and the GUI.
 *
 * @author group11
 */
public class ComplexCalculatorController implements Initializable {

    private Calculator c = new Calculator();
    private HashMap<String, Complex> map = c.getMap();
    private ObservableList<Complex> oblist = FXCollections.observableArrayList();
    private ObservableList<String> combolist = FXCollections.observableArrayList();
    private Alert alert;

    @FXML
    private ComboBox<String> varList;
    @FXML
    private TextField textInput;
    @FXML
    private ListView<Complex> stackView;
    @FXML
    private Button pushVar;
    @FXML
    private Button popVar;
    @FXML
    private Button sumVar;
    @FXML
    private Button subVar;
    @FXML
    private Button deleteButton;
    @FXML
    private Button enterButton;
    @FXML
    private Button numButton;

    /**
     * Initializes the GUI elements.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        stackView.setItems(oblist);
        initBinding();
        initVarList();
        initStackList();
        alert = new Alert(AlertType.ERROR);
    }

    /**
     * Initializes the bindings between the variable buttons and the variable
     * name.
     */
    private void initBinding() {
        StringProperty btn0 = new SimpleStringProperty(">");
        pushVar.textProperty().bind(Bindings.concat(">", varList.valueProperty()));

        StringProperty btn1 = new SimpleStringProperty("<");
        popVar.textProperty().bind(Bindings.concat("<", varList.valueProperty()));

        StringProperty btn2 = new SimpleStringProperty("+");
        sumVar.textProperty().bind(Bindings.concat("+", varList.valueProperty()));

        StringProperty btn3 = new SimpleStringProperty("-");
        subVar.textProperty().bind(Bindings.concat("-", varList.valueProperty()));
    }

    /**
     * Initializes the variable list with 26 letters (from a to z).
     */
    private void initVarList() {
        // Adds 26 letters to the list.
        for (Character i = 'a'; i <= 'z'; i++) {
            combolist.add(i.toString());
        }
        // Inserts the combolist elements in the variable list
        varList.setItems(combolist);
        // The first variable shown is 'a'.
        varList.getSelectionModel().selectFirst();
        // Builds the var list aligning them on the left side.
        varList.setCellFactory(lv -> {
            ListCell<String> cell = new ListCell<String>() {
                @Override
                protected void updateItem(String c, boolean empty) {
                    super.updateItem(c, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(c);
                    }
                }
            };
            cell.setAlignment(Pos.CENTER_LEFT);
            return cell;
        });

    }

    /**
     * Initializes the Stack list.
     */
    private void initStackList() {
        // Builds the stack view centering items in it.
        stackView.setCellFactory(lv -> {
            ListCell<Complex> cell = new ListCell<Complex>() {
                @Override
                protected void updateItem(Complex c, boolean empty) {
                    super.updateItem(c, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(c.toString());
                        setFont(Font.font(15));
                    }
                }
            };
            cell.setAlignment(Pos.CENTER);

            return cell;
        });

    }

    /**
     * Sends the input to the Calculator interpreter and manages eventual
     * Exceptions thrown showing an exceptionDialog with its relative message.
     */
    private void inputSend() {
        try {
            c.interpreter(textInput.getText());
        } catch (InvalidInputException e) {
            exceptionDialog("Invalid Input Detected", "Please insert a number in the format a+bj or an operation!");
        } catch (NotEnoughDataException e) {
            exceptionDialog("Not Enough Operands", "There aren't enough operands in the stack for the operation!");
        } catch (NotEnoughStackElementsException e) {
            exceptionDialog("Not Enough Stack Elements", "There aren't enough elements in the stack!");
        } catch (VariableException e) {
            exceptionDialog("Empty Variable Exception", "The selected variable isn't initialized!");
        } catch (ZeroDivisionException e) {
            exceptionDialog("Zero Division Detected", "Dividing by 0 is impossible!");
        } finally {
            textInput.setText("");
            oblist.setAll(c.getStack());
            updateVariableValue();
        }

    }

    /**
     * Updates the variable value shown in the variable list. If a variable
     * value is null it isn't shown, otherwise its value is shown using the
     * getMap method.
     */
    private void updateVariableValue() {
        varList.setCellFactory(lv -> {
            ListCell<String> cell = new ListCell<String>() {
                @Override
                protected void updateItem(String c, boolean empty) {
                    super.updateItem(c, empty);
                    if (map.get(c) == null) {
                        setText(c);
                    } else {
                        setText(c + " = " + map.get(c));
                    }
                }
            };
            cell.setAlignment(Pos.CENTER_LEFT);
            return cell;
        });
    }

    /**
     * Determines the behaviour of the calculator when a button is pressed.
     *
     * @param event
     */
    @FXML
    private void handleEasyButton(ActionEvent event) {
        Button b = (Button) event.getSource();
        textInput.setText(textInput.getText() + b.getText());
    }

    /**
     * Determines the behaviour of the calculator when the delete all button
     * (AC) is pressed.
     *
     * @param event
     */
    @FXML
    private void handleDeleteAllAction(ActionEvent event) {
        textInput.setText("");
    }

    /**
     * Determines the behaviour of the calculator when the delete button (⌫) is
     * pressed.
     *
     * @param event
     */
    @FXML
    private void handleDeleteAction(ActionEvent event) {
        if (!textInput.getText().equals("")) {
            String s = textInput.getText().substring(0, textInput.getText().length() - 1);
            textInput.setText(s);
        }
    }

    /**
     * Determines the behaviour of the calculator when the Enter button (⏎) is
     * pressed.
     *
     * @param event
     */
    @FXML
    private void handleEnter(ActionEvent event) {
        if (!textInput.getText().equals("")) {
            inputSend();
        }
    }

    /**
     * Determines the behaviour of the calculator when the Enter button is
     * pressed from the keyboard.
     *
     * @param event
     */
    @FXML
    private void handleEnterGeneral(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER) && !textInput.getText().equals("")) {
            inputSend();
        }
    }

    /**
     * Determines the behaviour of the calculator when a variableOp button or a
     * stackOp button is pressed.
     *
     * @param event
     */
    @FXML
    private void handleFixedButton(ActionEvent event) {
        Button b = (Button) event.getSource();
        textInput.setText(b.getText());
    }

    /**
     * Function used to execute the action of a button without pressing the
     * "Enter" button.
     *
     * @param event
     */
    @FXML
    private void handleRapidButton(ActionEvent event) {
        Button b = (Button) event.getSource();
        String input = b.getText();
        switch (input) {
            case "×":
                input = "*";
                break;
            case "÷":
                input = "/";
                break;
            case "√":
                input = "sqrt";
                break;
        }
        textInput.setText(input);
        inputSend();
    }

    /**
     * Sets Title,HeaderText,ContentText for the alert window.
     *
     * @param header
     * @param text
     */
    private void exceptionDialog(String header, String text) {
        alert.setTitle("Exception thrown");
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
    }

}
