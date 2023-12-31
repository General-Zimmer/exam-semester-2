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
    private FadIndhold fadIndhold;


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
        List<Fad> fad = fadIndhold.getFad();
        gui.getVisWhiskyController().setFad(fad.get(fad.size() - 1));
        gui.getStageVisWhisky().show();
        gui.notifyObservers();
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
