package gui;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import observers.IStorageObserver;

@SuppressWarnings("SpellCheckingInspection")
public class OpretLagerController implements IStorageObserver, OpretInterface {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnOK;

    @FXML
    private Label lblAdresse;

    @FXML
    private Label lblAntalPladser;

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
    private TextField txfAntalPladser;

    @FXML
    private TextField txfAntalReoler;

    @FXML
    private Label lblLagre;
    @FXML
    private TextArea txaLagre;

    /**
     * Checker om den indtastede adresse i GUI er gyldigt. Adressen skal være mindst 4 bogstaver og må IKKE være et tal.
     */
    public boolean gyldigAddresse(String addresse) {
        String førsteOrd = addresse.split(" ")[0];
        return førsteOrd.length() >= 4 && !førsteOrdErEtTal(førsteOrd);
    }

    /**
     * Checker om String "ord" er et tal. Hvis det er, så returner true.
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
     * Sørger for at oprette exceptions på lager, hvis der bliver indtastet ugyldige data.
     */
    public void opretException(){
        try {
            String addresse = txfAdresse.getText();
            int antal = Integer.parseInt(txfAntalReoler.getText());
            int kapacitet = Integer.parseInt(txfAntalPladser.getText());

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
     * Kaster exception
     */
    @FXML
    public void opretOK() {
        Gui gui = Gui.getInstance();
        opretException();
    }


    /**
     * Åbner vindue med fejlbesked info
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
     * Lukker for vinduet opret lager
     */
    @FXML
    public void opretVindueClose() {
        Gui gui = Gui.getInstance();
        gui.getStageLager().close();
        clearAllTextFields();
    }


    /**
     * Rydder texfFields i opret lager
     */
    @FXML
    public void clearAllTextFields() {
        txfAdresse.clear();
        txfAntalReoler.clear();
        txfAntalPladser.clear();
    }


    @Override
    public void update() {

    }

}