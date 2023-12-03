package gui;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import observers.IStorageObserver;

public class OpretLagerController implements IStorageObserver, OpretInterface {

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

    /**
     * Checker om den indtastede adresse i GUI er gyldigt. Adressen skal være mindst 4 bogstaver og må IKKE være et tal.
     * @param addresse
     * @return
     */
    public boolean gyldigAddresse(String addresse) {
        String førsteOrd = addresse.split(" ")[0];
        if (førsteOrd.length() >= 4 && !førsteOrdErEtTal(førsteOrd)) {
            return true;
        }
        return false;
    }

    /**
     * Checker om String "ord" er et tal. Hvis det er, så returner true.
     * @param ord
     * @return
     */
    public boolean førsteOrdErEtTal(String ord) {
        try {
            Integer.parseInt(ord);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    /**
     * Sørger for at de indtastede værdier i GUI er gyldige, og ellers får brugeren en advarsel.
     */
        public void opretException(){
        try {
            String addresse = txfAdresse.getText();
            int antal = Integer.parseInt(txfAntalReoler.getText());
            int kapacitet = Integer.parseInt(txfAntalHylder.getText());

            if (antal <= 0) {
                throw new IllegalArgumentException("Antallet skal være større end nul.");
            }
            if (kapacitet <= 0) {
                throw new IllegalArgumentException("Kapaciteten skal være større end nul.");
            }
            if (!gyldigAddresse(addresse)) {
                throw new IllegalArgumentException("Addressen er ugyldig.");
            }

            Controller.createLager(addresse, antal, kapacitet);
            clearAllTextFields();
            opretVindueClose();

        } catch (NumberFormatException e) {
            visAlert("Ugyldigt input", "Indtast et gyldigt antal for reoler og pladser");
        } catch (IllegalArgumentException e) {
            visAlert("Ugyldigt input", e.getMessage());
        }
    }

    /**
     * Opretter et lager med de indtastede værdier, når der trykkes "OK"
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
     * Lukker vinduet, når der trykkes "Cancel" eller "OK"
     */
    @FXML
    public void opretVindueClose() {
        Gui gui = Gui.getInstance();
        gui.getStageLager().close();
        clearAllTextFields();
    }

    /**
     * Rydder alle tekstfelter, når der trykkes "Cancel" eller "OK"
     */
    @FXML
    public void clearAllTextFields() {
        txfAdresse.clear();
        txfAntalReoler.clear();
        txfAntalHylder.clear();
    }


    @Override
    public void update() {

    }

}