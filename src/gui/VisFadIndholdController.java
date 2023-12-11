package gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import observers.IStorageObserver;

import java.time.LocalDate;
import java.util.*;

public class VisFadIndholdController implements IStorageObserver {
    @FXML
    private Button btnOpretWhisky;
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
    private ListView<Integer> lwWhisky;

    @FXML
    private TextField txfMedarbejder;

    @FXML
    private TextField txfStartDato;
    @FXML
    private DatePicker dpStartDato;
    private ArrayList<Fad> fade;
    private HashMap<Destillat, Float> destillater;
    private FadIndhold fadIndhold;
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

    @FXML
    public void visWhiskyPane() {
        Gui gui = Gui.getInstance();
        gui.getStageVisWhisky().show();
    }

    public void setIndhold(FadIndhold indhold){
        this.fadIndhold = indhold;
    }

    @FXML
    public void opretWhiskyKnap(){
        Gui gui = Gui.getInstance();
        gui.getStageOpretWhisky().setTitle("Opret whisky");
        gui.getOpretWhiskyController().setFadIndhold(fadIndhold);
        gui.getStageOpretWhisky().show();
        gui.notifyObservers();
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
        lwDestillater.refresh();
        lwWhisky.refresh();
        txfMedarbejder.clear();
        txfStartDato.clear();
    }
    public void setFad(List<Fad> fad){
            lwFade.getItems().setAll(fad);
    }

    /*
    public void setIndhold(FadIndhold indhold){
        Gui gui = Gui.getInstance();
        gui.getOpretWhiskyController().setFyld(indhold);
    }

     */
    public void setFyld(Fyld fyld){
        lwWhisky.getItems().clear();
        lwWhisky.getItems().add(fyld.getFadindhold().getWhiskyPåFyld().size());
    }
    public void setDestillat(Destillat dest){
        lwDestillater.getItems().clear();
        lwDestillater.getItems().add(dest);
    }

    @Override
    public void update() {
    }
}
