package gui;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import observers.IStorageObserver;

import java.time.LocalDate;

public class OpretDestillatController implements IStorageObserver, OpretInterface {

    @FXML
    private Button btnCancelDestillat;

    @FXML
    private Button btnOKDestillat;

    @FXML
    private DatePicker dpDestillationsDato;

    @FXML
    private Label lblAlkoholPrc;

    @FXML
    private Label lblDestillationsdato;

    @FXML
    private Label lblDestillering;

    @FXML
    private Label lblKommentar;

    @FXML
    private Label lblKornSort;

    @FXML
    private Label lblMaltBatch;

    @FXML
    private Label lblMængde;

    @FXML
    private Label lblOpretLager;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextArea txaKommentar;

    @FXML
    private TextField txfAlkoholProcent;

    @FXML
    private TextField txfDestillering;

    @FXML
    private TextField txfKornSort;

    @FXML
    private TextField txfMaltBatch;

    @FXML
    private TextField txfMængde;


    /**
     * Sørger for at kaste exceptions hvis der indtastes ugyldige data.
     */
    @FXML
    public void opretOK() {
        Gui gui = Gui.getInstance();
        opretException();
    }


    /**
     * Sørger for exceptions bliver kastet hvis indtastede data er forkerte
     */
    public void opretException(){
        try {
            int maltBatch = Integer.parseInt(txfMaltBatch.getText());
            String kornSort = txfKornSort.getText();
            float mængde = Float.parseFloat(txfMængde.getText());
            float alkoholProcent = Float.parseFloat(txfAlkoholProcent.getText());
            String destillering = txfDestillering.getText();
            LocalDate destillationsDato = dpDestillationsDato.getValue();
            String kommentar = txaKommentar.getText();

            if (maltBatch <= 0) {
                throw new IllegalArgumentException("Maltbatch skal være større end nul.");
            }
            if (kornSort.length() <= 1) {
                throw new IllegalArgumentException("Ugyldigt kornsort.");
            }
            if (mængde <= 0) {
                throw new IllegalArgumentException("Mængden er ugyldig.");
            }
            if (alkoholProcent <= 0 || alkoholProcent >= 100) {
                throw new IllegalArgumentException("Alkoholprocent er ugyldig.");
            }
            if (destillering.length() <= 1) {
                throw new IllegalArgumentException("Destillering er ugyldig.");
            }
            if (destillationsDato == null) {
                throw new IllegalArgumentException("Datoen for destillation kan ikke ligge i fremtiden.");
            }

            Controller.createDestillat(maltBatch, kornSort, mængde, alkoholProcent, destillering,
                    destillationsDato, kommentar);
            clearAllTextFields();
            opretVindueClose();

        } catch (NumberFormatException e) {
            visAlert("Ugyldigt input", "Indtast et gyldigt antal maltbatch, alkoholprocent og mængde");
        } catch (IllegalArgumentException e) {
            visAlert("Ugyldigt input", e.getMessage());
        }
    }


    /**
     * Viser en pop up med fejlbesked hvis bruger støder på en error.
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
     * Sørger for at lukke vinduet til oprettelse af destillat.
     */
    @FXML
    public void opretVindueClose() {
        Gui gui = Gui.getInstance();
        gui.getStageDestillat().close();
    }

    /**
     * Rydder alle tekstfelter, når der trykkes "Cancel" eller "OK"
     */
    @FXML
    public void clearAllTextFields() {
        txfMaltBatch.clear();
        txfKornSort.clear();
        txfMængde.clear();
        txfAlkoholProcent.clear();
        txfDestillering.clear();
        txaKommentar.clear();
    }

    @Override
    public void update() {

    }
}
