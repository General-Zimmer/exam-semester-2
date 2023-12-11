package gui;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import model.Destillat;
import model.Fad;
import model.Fyld;
import observers.IStorageObserver;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OpretFyldController implements IStorageObserver, OpretInterface {

    @FXML
    private ListView<Fyld> lwFyld;
    @FXML
    private Label lblFyld;
    @FXML
    private Button btnGem;

    @FXML
    private Button btnLuk;

    @FXML
    private Label lblDestillat;

    @FXML
    private Label lblFad;
    @FXML
    private DatePicker dpStartDato;

    @FXML
    private TextField txfDestillat;
    @FXML
    private TextField txfMedarbejder;

    @FXML
    private Label lblMedarbejder;

    @FXML
    private TextField txfFad;
    private Destillat destillat;
    private Fad fad;

    @Override
    public void update() {
    }

    public void setDestillat(Destillat destillat){
        this.destillat = destillat;
        txfDestillat.setText(destillat.getKornsort().toString());
    }

    public void setFad(Fad fad){
        this.fad = fad;
        txfFad.setText(fad.getLeverandør());
    }



    /**
     * Sørger for at oprette exceptions hvis bruger indtaster ugyldige data.
     */
    @Override
    public void opretException() {
        try {
            LocalDate startDato = dpStartDato.getValue();
            String medarbejder = txfMedarbejder.getText();
            txfFad.setText(fad.getType().toString());
            txfDestillat.setText("" + destillat.getMaltBatch());
            HashMap<Destillat, Float> destil = new HashMap<>();
            destil.put(destillat,destillat.getMængde());
            if (medarbejder.isBlank()) {
                throw new IllegalArgumentException("Vælg venligst en medarbejder.");
            }
            if (startDato == null) {
                throw new IllegalArgumentException("Datoen for fyld er ugyldig eller ikke valgt.");
            }

            Controller.createFyld(fad, startDato, medarbejder, destil);
            clearAllTextFields();
            opretVindueClose();

        } catch (NumberFormatException e) {
            visAlert("Ugyldigt input", "Indtast et gyldigt input");
        } catch (IllegalArgumentException e) {
            visAlert("Ugyldigt input", e.getMessage());
        }
    }


    /**
     * Rydder textfields
     */
    @Override
    public void clearAllTextFields() {
        txfFad.clear();
        txfDestillat.clear();
        txfMedarbejder.clear();
    }


    /**
     * Lukker for panelet opret fyld
     */
    @Override
    public void opretVindueClose() {
        Gui gui = Gui.getInstance();
        gui.getStageOpretFyld().close();
        clearAllTextFields();
    }


    /**
     * Sørger for at ovennævnte exceptions bliver kastet.
     */
    @Override
    public void opretOK() {
        Gui gui = Gui.getInstance();
        opretException();
    }


    /**
     * Åbner vindue med fejlbesked, hvis bruger har indtastet ugyldige data.
     * @param title titlen på pop-up vinduet
     * @param besked besked til brugeren
     */
    @Override
    public void visAlert(String title, String besked) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(besked);
        alert.showAndWait();
    }
}
