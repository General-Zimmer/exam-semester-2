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
    private Button btnLuk;

    @FXML
    private Label lblAntalFills;

    @FXML
    private Label lblFadHistorik;

    @FXML
    private Label lblFadID;

    @FXML
    private Label lblFadType;

    @FXML
    private Label lblFyld;

    @FXML
    private Label lblLeverandør;

    @FXML
    private Label lblPladsNummer;

    @FXML
    private Label lblReol;

    @FXML
    private Label lblStørrelse;

    @FXML
    private TextField txfAntalFills;

    @FXML
    private TextField txfFadHistorik;

    @FXML
    private TextField txfFadID;

    @FXML
    private TextField txfFadType;

    @FXML
    private TextField txfFyld;

    @FXML
    private TextField txfLeverandør;

    @FXML
    private TextField txfPladsNummer;

    @FXML
    private TextField txfReol;

    @FXML
    private TextField txfStørrelse;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnOK;

    @FXML
    private Label lblFillAntal;
    @FXML
    private Label lblOpretFad;

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
    private int[] størrelser = {30, 90, 130};

    @Override
    public void update() {
    }

    public void setLager(Lager lager) {
        this.lager = lager;
    }


    /**
     * Åbner og viser panelet for at oprette fad.
     */
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
     * Sørger for at kaste exceptions ved indtastelse af ugyldig data.
     */
    public void opretException(){
        try {
            String leverandør = txfLeverandør.getText();
            String fadHistorik = txfFadHistorik.getText();
            int fillAntal = Integer.parseInt(txffillAntal.getText());
            int størrelse = Integer.parseInt(txfStørrelse.getText());
            int reolNr = Integer.parseInt(txfReolNr.getText());
            int pladsNr = Integer.parseInt(txfPladsNr.getText());
            int reolMax = lager.getReoler().length;
            int pladsMax = lager.getReoler()[0].length;

            if (reolNr > reolMax) {
                throw new IllegalArgumentException("Vi har kun " + reolMax + " reoler.");
            }

            if (pladsNr > pladsMax) {
                throw new IllegalArgumentException("Vi har kun " + pladsMax + " pladser");
            }

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

            Controller.createFad(type, leverandør, størrelse, fillAntal, fadHistorik, lager, reolNr - 1, pladsNr - 1);
            Gui gui = Gui.getInstance();
            gui.getVisFadController().setPlads(reolNr, pladsNr);
            clearAllTextFields();
            opretVindueClose();

        } catch (NumberFormatException e) {
            visAlert("Ugyldigt input", "Indtast et gyldigt tal for fill og størrelse");
        } catch (IllegalArgumentException e) {
            visAlert("Ugyldigt input", e.getMessage());
        }
    }


    /**
     * Sørger for at exceptions bliver kastet
     */
    @FXML
    public void opretOK() {
        Gui gui = Gui.getInstance();
        opretException();
}


    /**
     * Åbner vindue med en fejlbeskrivelse, hvis bruger har indtastet ugyldige data.
     * @param title titlen på pop-up vinduet
     * @param besked besked til brugeren
     */
    public void visAlert(String title, String besked) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(besked);
        alert.showAndWait();
    }


    /**
     * Lukker for panelet opret fad.
     */
    @FXML
    public void opretVindueClose() {
        Gui gui = Gui.getInstance();
        gui.getStageOpretFad().close();
        clearAllTextFields();
    }


    /**
     * Rydder alle textfield indenfor fad.
     */
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