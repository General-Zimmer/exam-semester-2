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
    private ListView<Destillat> lwDestillater;

    @FXML
    private ListView<Fad> lwFade;

    @FXML
    private ListView<Whisky> lwWhisky;

    @FXML
    private TextField txfMedarbejder;

    @FXML
    private TextField txfStartDato;
    @FXML
    private DatePicker dpStartDato;
    private ArrayList<Fad> fade;
    private HashMap<Destillat, Float> destillater;
    private Fyld fyld;

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

    // ?????????? lol
    public void addWhisky(HashSet<Whisky> whiskys) {
        lwWhisky.setItems((ObservableList<Whisky>) whiskys);
    }

    public void setFyld(Fyld fyld) {
        this.fyld = fyld;
    }

    private void clearTextFields() {
        lwFade.getItems().clear();
        lwDestillater.getItems().clear();
        lwWhisky.getItems().clear();
    }
    public void setFad(Fad fad){
        lwFade.getItems().add(fad);
    }

    public void setDestillat(Destillat dest){
        lwDestillater.getItems().add(dest);
    }

    @Override
    public void update() {
    }
}
