package gui;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.FadType;
import model.Lager;
import observers.IStorageObserver;
import storage.Storage;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static java.lang.String.valueOf;
import static java.util.regex.Pattern.matches;


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
    private int[] størrelser = {30, 90, 130, 1337};

    @Override
    public void update() {
    }

    public void setLager(Lager lager) {
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
        clearAllTexts();
        gui.getStageOpretFad().close();
    }

    @FXML
    public void clearAllTexts() {
        txffillAntal.clear();
        txfFadHistorik.clear();
        txfLeverandør.clear();
        txfStørrelse.clear();
        mbtnType.setText("Type");
    }

    public String printStørrelser() {
        String print = "";
        int length = størrelser.length;
        int i = 0;
        while (i < length - 1) {
            print += størrelser[i] + ", ";
            i++;
        }
        print += størrelser[length - 1];
        return print;
    }

    public boolean checkStørrelse(int størrelseDerSkalCheckes) {
        for (int i = 0; i < størrelser.length; i++) {
            if(størrelseDerSkalCheckes == størrelser[i]) {
                return false;
            }
        }
        return true;
    }
    public void opretException(){
        String leverandør = txfLeverandør.getText();
        String fadHistorik = txfFadHistorik.getText();

        try {

            int fillAntal = Integer.parseInt(txffillAntal.getText());
            int størrelse = Integer.parseInt(txfStørrelse.getText());

            if (fillAntal <= 0) {
                throw new IllegalArgumentException("Fill skal være større end nul.");
            }

            if (checkStørrelse(størrelse)) {
                throw new IllegalArgumentException("Brug en af størrelserne: " + printStørrelser() + " liter.");
            }

            if(leverandør.matches(".*\\d.*")){
                throw new IllegalArgumentException("Indtast et gyldigt navn.");
            }

            if(fadHistorik.matches(".*\\d.*")){
                throw new IllegalArgumentException("Indtast en gyldig historik.");
            }

            if(type == null){
                throw new IllegalArgumentException("Vælg en fadtype.");
            }

            Controller.createFad(type, leverandør, fillAntal, størrelse, fadHistorik, lager, 1, 1);
            opretFadClose();

        } catch (NumberFormatException e) {
            visAlert("Ugyldigt input", "Indtast et gyldigt tal for fill og størrelse");
        } catch (IllegalArgumentException e) {
            visAlert("Ugyldigt input", e.getMessage());
        }
    }
    @FXML
    public void opretFadOK() {
        Gui gui = Gui.getInstance();
        opretException();
        clearAllTexts();
}
    private void visAlert(String title, String besked) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(besked);
        alert.showAndWait();
    }
}