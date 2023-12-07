package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import observers.IStorageObserver;

public class OpretWhiskyController implements IStorageObserver, OpretInterface {

    @FXML
    private Button btnGem;

    @FXML
    private Button btnLuk;

    @FXML
    private DatePicker dpWhiskyDato;

    @FXML
    private Label lblFyld;

    @FXML
    private Label lblMængde;

    @FXML
    private MenuItem miBLENDED;

    @FXML
    private MenuButton miButton;

    @FXML
    private MenuItem miSINGLECASK;

    @FXML
    private MenuItem miSINGLEMALT;

    @FXML
    private TextField txfFyld;

    @FXML
    private TextField txfMængde;

    @FXML
    void opretOK(MouseEvent event) {
    }

    @Override
    public void opretException() {

    }

    @Override
    public void clearAllTextFields() {

    }

    @Override
    public void opretVindueClose() {
        Gui gui = Gui.getInstance();
        gui.getStageOpretWhisky().close();
        clearAllTextFields();
    }


    /**
     * Sørger for at ovennævnte exceptions bliver kastet.
     */
    @Override
    public void opretOK() {
        Gui gui = Gui.getInstance();
        opretException();
    }


    @Override
    public void visAlert(String title, String besked) {

    }

    @Override
    public void update() {

    }
}
