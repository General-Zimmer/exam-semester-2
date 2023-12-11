package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import model.Fad;
import model.FadIndhold;
import model.Lager;
import model.Whisky;
import observers.IStorageObserver;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class VisWhiskyController  implements IStorageObserver {

    @FXML
    private Button btnLuk;

    @FXML
    private Label lblWhiskyHistorie;

    @FXML
    private Label lblWhiskys;

    @FXML
    private ListView<Whisky> lwWhiskys;

    @FXML
    private TextArea txaWhiskyHistorie;
    private Lager lager;

    @Override
    public void update() {
        Gui gui = Gui.getInstance();
        lwWhiskys.getItems().clear();
        if(lager == null) {
            return;
        }

        Set<Whisky> whiskys = new HashSet<>();

        for(int i = 0; i < lager.getReoler().length; i++) {
            for(int j = 0; j < lager.getReoler()[0].length; j++) {
                Fad currentFad = lager.getFad(i, j);
                if(currentFad != null) {
                    FadIndhold currentIndhold = currentFad.getFadindhold();
                    Set<Whisky> whiskySets = currentIndhold.getWhiskyPåFyld();
                    whiskys.addAll(whiskySets);
                }
            }
        }
        lwWhiskys.getItems().addAll(whiskys);
    }
    private void clearAllTextFields(){
        txaWhiskyHistorie.clear();
    }

    public void clickOnWhiskyAndShowSpecs(MouseEvent mouseEvent){
        if (mouseEvent.getClickCount() == 2 && lwWhiskys.getSelectionModel().getSelectedItem() != null) {
            txaWhiskyHistorie.clear();
            Whisky whisky = lwWhiskys.getSelectionModel().getSelectedItem();
            txaWhiskyHistorie.setText(whisky.getKompleteHistorie());
        }
    }
    public void setLager(Lager lager){
        this.lager = lager;
    }

    @FXML
    public void visWhiskyPaneLuk() {
        Gui gui = Gui.getInstance();
        clearAllTextFields();
        gui.getStageVisWhisky().close();
    }

}
