package gui;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.FadType;
import model.Lager;
import observers.IStorageObserver;

import java.util.Iterator;

import static java.lang.String.valueOf;


public class OpretFadController implements IStorageObserver {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnOK;

    @FXML
    private Label lblFadHistorik;

    @FXML
    private Label lblFillAntal;

    @FXML
    private Label lblLeverandør;

    @FXML
    private Label lblOpretFad;

    @FXML
    private Label lblStørrelse;

    @FXML
    private MenuButton mbtnType;

    @FXML
    private MenuItem miAndet;

    @FXML
    private MenuItem miBourbon;

    @FXML
    private MenuItem miRødvin;

    @FXML
    private MenuItem miSherry;

    @FXML
    private TextField txfFadHistorik;

    @FXML
    private TextField txfLeverandør;

    @FXML
    private TextField txfStørrelse;

    @FXML
    private TextField txffillAntal;
    private FadType type;
    private Lager lager;
    @Override
    public void update() {
    }

    public void setLager(Lager lager){
        this.lager = lager;
    }

    @FXML
    public void opretFadPane() {
        Gui gui = Gui.getInstance();
        gui.getStageOpretFad().show();
    }
    @FXML
    public void indhentMenuItem(ActionEvent event) {
        MenuItem selectedMenuItem = (MenuItem) event.getSource();
        String selectedValue = selectedMenuItem.getText();
        mbtnType.setText(selectedValue);
        type = FadType.valueOf(selectedValue);
    }

    @FXML
    public void opretFadClose() {
        Gui gui = Gui.getInstance();
        gui.getStageOpretFad().close();
    }

    @FXML
    public void clearAllTexts() {
        //.clear();
        //.clear();
        //.clear();
    }


    @FXML
    public void opretFadOK() {
        Gui gui = Gui.getInstance();
        String leverandør = txfLeverandør.getText();
        int fillAntal = Integer.parseInt(txffillAntal.getText());
        int størrelse = Integer.parseInt(txfStørrelse.getText());
        String fadHistorik = txfFadHistorik.getText();
        Controller.createFad(type, leverandør, fillAntal, størrelse, fadHistorik, lager, 1, 1);
        clearAllTexts();
        opretFadClose();
    }
}
