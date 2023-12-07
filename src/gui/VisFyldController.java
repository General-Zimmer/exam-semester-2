package gui;

import controller.Controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import observers.IStorageObserver;

import java.time.LocalDate;
import java.util.*;

public class VisFyldController implements IStorageObserver {
    @FXML
    private Button btnLuk;

    @FXML
    private Label lblDestillater;

    @FXML
    private Label lblFade;

    @FXML
    private Label lblMedarbejdere;

    @FXML
    private Label lblStartDato;

    @FXML
    private Label lblWhisky;

    @FXML
    private TextArea txaDestillater;

    @FXML
    private TextArea txaFade;

    @FXML
    private TextArea txaWhisky;

    @FXML
    private TextField txfMedarbejder;

    @FXML
    private TextField txfStartDato;
    @FXML
    private DatePicker dpStartDato;
    private ArrayList<Fad> fade;
    private HashMap<Destillat, Float> destillater;
    private Fyld fyld;


    /**
     * Sørger for at lukke vinduet vis fyld, når det ønskes.
     */
    @FXML
    public void visFyldPaneLuk() {
        Gui gui = Gui.getInstance();
        clearTextFields();
        gui.getStageVisFyld().close();
    }

    public void setMedarbejder(String medarbejder) {
        txfMedarbejder.setText(medarbejder);
    }

    public void setDato(LocalDate dato) {
        txfStartDato.setText(dato.toString());
    }
/*
    // ?????????? lol
    public void addWhisky(HashSet<Whisky> whiskys) {
        txaWhisky.setText((ObservableList<Whisky>) whiskys);
    }

 */


    /**
     * Rydder textfields i vis fyld panelet.
     */
    private void clearTextFields() {
        txaDestillater.clear();
        txaFade.clear();
        txaWhisky.clear();
        txfMedarbejder.clear();
        txfStartDato.clear();
    }
    public void setFad(Fad fad){
        txaFade.setText(fad.getType().toString());
    }

    public void setDestillat(Destillat dest){
        txaDestillater.setText(dest.getKornsort().toString());
    }

    @Override
    public void update() {
    }
}
