package gui;

import controller.Controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.Destillat;
import model.Lager;
import observers.IStorageObserver;

import java.util.ArrayList;
import java.util.Set;

public class HovedMenu implements IStorageObserver {

    @FXML
    private Button btnOpretDestillat;

    @FXML
    private Label lblDestillater;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button btnOpretLager;

    @FXML
    private Label lblLagre;

    @FXML
    private ListView<Destillat> lwDestillater;

    @FXML
    private ListView<Lager> lwLagre;

    /**
     * Åbner panelet til at oprette et lager
     */
    @FXML
    public void opretLagerPane() {
        Gui gui = Gui.getInstance();
        gui.getStageLager().show();

    }

    /**
     * Åbner panelet til at oprette et destillat
     */
    @FXML
    public void opretDestillatPane() {
        Gui gui = Gui.getInstance();
        gui.getStageDestillat().show();
    }

    /* SKAL LAVES FÆRDIG TIL NÆSTE ITERATION. DRØFTES MED HANNE OM DESIGN AF BRUGERGRÆNSEFLADE YES
    public void clickOnListViewAndOpenNewWindow(MouseEvent mouseEvent){
        if (mouseEvent.getClickCount() == 2) {
            Lager lager = lwLagre.getSelectionModel().getSelectedItem();
            Gui gui = Gui.getInstance();
            gui.get.show();

        }

    }

     */

    @Override
    public void update () {
        Set<Destillat> destillater = Controller.getDestillater();
        Set<Lager> lagre = Controller.getLager();
        lwLagre.getItems().clear();
        lwDestillater.getItems().clear();
        for (Destillat dest : destillater) {
            lwDestillater.getItems().add(dest);
        }
        for (Lager lag : lagre) {
            lwLagre.getItems().add(lag);
        }
    }
}