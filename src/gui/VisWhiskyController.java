package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
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
                    FadIndhold currentIndhold = currentFad.getBlanding();
                    if(currentIndhold != null) {
                        Set<Whisky> whiskySets = currentIndhold.getWhiskyPåFyld();
                        for(Whisky currentWhisky : whiskySets) {
                            whiskys.add(currentWhisky);
                        }
                    }
                }
            }
        }
        lwWhiskys.getItems().addAll(whiskys);
    }
    public void setLager(Lager lager){
        this.lager = lager;
    }

}
