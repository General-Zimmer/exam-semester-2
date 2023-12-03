package gui;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.FadType;
import model.Lager;
import observers.IStorageObserver;

import static java.lang.String.valueOf;


public class OpretFadController implements IStorageObserver, OpretInterface {

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

    /**
     * Sætter teksten til menuen og typen til hvad end brugeren vælger i dropdownmenuen.
     * @param event
     */
    @FXML
    public void indhentMenuItem(ActionEvent event) {
        MenuItem selectedMenuItem = (MenuItem) event.getSource();
        String selectedValue = selectedMenuItem.getText();
        mbtnType.setText(selectedValue);
        type = FadType.valueOf(selectedValue);
    }

    /**
     * Laver en string med alle størrelserne på de mulige fad(30, 90, 130 liter) og sætter kommaer så det kan vises til brugeren.
     * i en exception
     * @return En String med alle fadstørrelser.
     */
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

    /**
     * Checker om den indtastede størrelse er en del af de mulige størrelser(30, 90, 130 liter).
     * @param størrelseDerSkalCheckes
     * @return true, hvis den indtastede størrelse er en del af de mulige størrelser.
     */
    public boolean checkStørrelse(int størrelseDerSkalCheckes) {
        for (int i = 0; i < størrelser.length; i++) {
            if(størrelseDerSkalCheckes == størrelser[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sørger for at de indtastede værdier i GUI er gyldige, og ellers får brugeren en advarsel.
     */
    public void opretException(){
        try {
            String leverandør = txfLeverandør.getText();
            String fadHistorik = txfFadHistorik.getText();
            int fillAntal = Integer.parseInt(txffillAntal.getText());
            int størrelse = Integer.parseInt(txfStørrelse.getText());

            if (fillAntal <= 0) {
                throw new IllegalArgumentException("Fill skal være større end nul.");
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

            Controller.createFad(type, leverandør, fillAntal, størrelse, fadHistorik, lager, 1, 1);
            clearAllTextFields();
            opretVindueClose();

        } catch (NumberFormatException e) {
            visAlert("Ugyldigt input", "Indtast et gyldigt tal for fill og størrelse");
        } catch (IllegalArgumentException e) {
            visAlert("Ugyldigt input", e.getMessage());
        }
    }

    /**
     * Funktion til at oprette et fad, når man trykker på "OK"-knappen.
     */
    @FXML
    public void opretOK() {
        Gui gui = Gui.getInstance();
        opretException();
}

    /**
     * Pop-up alarm, når der skal kastes en exception under oprettelsen af et fad.
     * @param title
     * @param besked
     */
    public void visAlert(String title, String besked) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(besked);
        alert.showAndWait();
    }

    /**
     * Til at lukke vinduet til fad-oprettelse, og når man trykker på "OK"-knappen.
     */
    @FXML
    public void opretVindueClose() {
        Gui gui = Gui.getInstance();
        gui.getStageOpretFad().close();
        clearAllTextFields();
    }

    /**
     * Rydder alle tekstfields.
     */
    @FXML
    public void clearAllTextFields() {
        txffillAntal.clear();
        txfFadHistorik.clear();
        txfLeverandør.clear();
        txfStørrelse.clear();
        mbtnType.setText("Type");
    }
}