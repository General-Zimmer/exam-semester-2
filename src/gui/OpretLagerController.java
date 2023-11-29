package gui;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import observers.IStorageObserver;

public class OpretLagerController implements IStorageObserver {

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
    private Label lblLagre;

    @FXML
    private TextArea txaLagre;



    @FXML
    public void opretLagerOK() {
        Gui gui = Gui.getInstance();
        String addresse = txfAdresse.getText();
        int antal = Integer.parseInt(txfAntalReoler.getText());
        int kapacitet = Integer.parseInt(txfAntalHylder.getText());

        Controller.createLager(addresse, antal, kapacitet);

        // clearAllTexts();
        opretLagerClose();
    }

    @FXML
    public void opretLagerClose() {
        Gui gui = Gui.getInstance();
        gui.getStageLager().close();
    }

    @FXML
    public void clearAllTexts() {
        txfAdresse.clear();
        txfAntalReoler.clear();
        txfAntalHylder.clear();
    }


    @Override
    public void update() {

    }

}