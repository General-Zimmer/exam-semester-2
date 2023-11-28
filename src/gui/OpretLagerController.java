package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.awt.event.ActionEvent;

public class OpretLagerController {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnOK;

    @FXML
    private Label lblAdresse;

    @FXML
    private Label lblAntalHylder;

    @FXML
    private Label lblAntalReoler;

    @FXML
    private Label lblLagerType;

    @FXML
    private Label lblOpretLager;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txfAdresse;

    @FXML
    private TextField txfAntalHylder;

    @FXML
    private TextField txfAntalReoler;
    @FXML
    private Button btnOpretLager;

    @FXML
    private Label lblLagre;

    @FXML
    private TextArea txaLagre;

    @FXML
    public void opretLagerPane() {
        Gui gui = Gui.getInstance();

        gui.getStageLager().show();
    }
}