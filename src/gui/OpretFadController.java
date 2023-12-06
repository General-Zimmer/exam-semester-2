package gui;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.FadType;
import model.Lager;
import observers.IStorageObserver;


@SuppressWarnings("SpellCheckingInspection")
public class OpretFadController implements IStorageObserver, OpretInterface {

    @FXML
    private Label lblFadType;
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

    @FXML
    private Label lblPladsNr;

    @FXML
    private Label lblReolNr;

    @FXML
    private TextField txfPladsNr;

    @FXML
    private TextField txfReolNr;
    private FadType type;
    private Lager lager;
    private final int[] størrelser = {30, 90, 130};

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

    /**
     * Sætter teksten til menuen og typen til hvad end brugeren vælger i dropdownmenuen.
     */
    @FXML
    public void indhentMenuItem(ActionEvent event) {
        MenuItem selectedMenuItem = (MenuItem) event.getSource();
        String selectedValue = selectedMenuItem.getText();
        mbtnType.setText(selectedValue);
        type = FadType.valueOf(selectedValue);
    }

    /**
     * Laver en string med alle størrelserne på de mulige fad(30, 90, 130 liter) og sætter kommaer så det kan vises til brugeren i en exception
     * @return En String med alle fadstørrelser.
     */
    public String printStørrelser() {
        StringBuilder print = new StringBuilder();
        int length = størrelser.length;
        int i = 0;
        while (i < length - 1) {
            print.append(størrelser[i]).append(", ");
            i++;
        }
        print.append(størrelser[length - 1]);
        return print.toString();
    }

    /**
     * Checker om den indtastede størrelse er en del af de mulige størrelser(30, 90, 130 liter).
     * @return true, hvis den indtastede størrelse er en del af de mulige størrelser.
     */
    public boolean checkStørrelse(int størrelseDerSkalCheckes) {
        for (int j : størrelser) {
            if (størrelseDerSkalCheckes == j) {
                return true;
            }
        }
        return false;
    }


    public void opretException(){
        try {
            String leverandør = txfLeverandør.getText();
            String fadHistorik = txfFadHistorik.getText();
            int fillAntal = Integer.parseInt(txffillAntal.getText());
            int størrelse = Integer.parseInt(txfStørrelse.getText());
            int reolNr = Integer.parseInt(txfReolNr.getText());
            int pladsNr = Integer.parseInt(txfPladsNr.getText());

            if (fillAntal <= 0) {
                throw new IllegalArgumentException("Fill skal være større end nul.");
            }

            if (reolNr <= 0) {
                throw new IllegalArgumentException("Reolnummer skal være større end nul.");
            }
            if (pladsNr <= 0) {
                throw new IllegalArgumentException("Pladsnummer skal være større end nul.");
            }

            if (!checkStørrelse(størrelse)) {
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

            Controller.createFad(type, leverandør, fillAntal, størrelse, fadHistorik, lager, reolNr, pladsNr);
            clearAllTextFields();
            opretVindueClose();

        } catch (NumberFormatException e) {
            visAlert("Ugyldigt input", "Indtast et gyldigt tal for fill og størrelse");
        } catch (IllegalArgumentException e) {
            visAlert("Ugyldigt input", e.getMessage());
        }
    }

    @FXML
    public void opretOK() {
        Gui gui = Gui.getInstance();
        opretException();
}

    public void visAlert(String title, String besked) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(besked);
        alert.showAndWait();
    }


    @FXML
    public void opretVindueClose() {
        Gui gui = Gui.getInstance();
        gui.getStageOpretFad().close();
        clearAllTextFields();
    }


    @FXML
    public void clearAllTextFields() {
        txffillAntal.clear();
        txfFadHistorik.clear();
        txfLeverandør.clear();
        txfStørrelse.clear();
        txfPladsNr.clear();
        txfReolNr.clear();
        mbtnType.setText("Type");
    }
}