package gui;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    }

    public void setFad(Fad fad){
        this.fad = fad;
    }

    public void setFadSpecs(Fad fad){
        txfFad.setText(fad.getType().toString());
    }

    public void setDestillatSpecs(Destillat destillat){
        txfDestillat.setText("" + destillat.getMaltBatch());
    }

    @Override
    public void opretException() {
        /*
            private final HashSet<Fad> fad;
    private final HashMap<Destillat, Float> destillater;
    private final HashSet<Whisky> whiskyPåFyld;
    private final LocalDate startDato;
    private String medarbejdere; // Dem som har fyldt fadet
         */
        try {
            LocalDate startDato = dpStartDato.getValue();
            String medarbejder = txfMedarbejder.getText();
            HashMap<Destillat,Float> destillater = new HashMap<>();
            destillater.put(destillat, destillat.getMængde());
            if (medarbejder.isBlank()) {
                throw new IllegalArgumentException("Vælg venligst en medarbejder.");
            }
            if (startDato == null) {
                throw new IllegalArgumentException("Datoen for fyld er ugyldigt eller ikke valgt.");
            }

            Controller.createFyld(fad,startDato,medarbejder,destillater);
            clearAllTextFields();
            opretVindueClose();

        } catch (NumberFormatException e) {
            visAlert("Ugyldigt input", "Indtast et gyldigt input");
        } catch (IllegalArgumentException e) {
            visAlert("Ugyldigt input", e.getMessage());
        }
    }

    @Override
    public void clearAllTextFields() {

    }

    @Override
    public void opretVindueClose() {
        Gui gui = Gui.getInstance();
        gui.getStageOpretFyld().close();
        clearAllTextFields();
    }

    @Override
    public void opretOK() {
        Gui gui = Gui.getInstance();
        opretException();
    }

    @Override
    public void visAlert(String title, String besked) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(besked);
        alert.showAndWait();
    }
}
