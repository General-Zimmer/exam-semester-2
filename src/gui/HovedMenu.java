package gui;

import controller.Controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.Destillat;
import model.Fad;
import model.Lager;
import observers.IStorageObserver;

import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.Set;

public class HovedMenu implements IStorageObserver {

    @FXML
    private AnchorPane pane;

    @FXML
    private Button btnOpretLager;

    @FXML
    private Label lblLagre;

    @FXML
    private ListView<Lager> lwLagre;
    private Lager lager;


    /**
     * Åbner panelet til at oprette et lager
     */
    @FXML
    public void opretLagerPane() {
        Gui gui = Gui.getInstance();
        gui.getStageLager().setTitle("Opret lager");
        gui.getStageLager().show();
    }

    @FXML
    public void visLagerTabs() {
        Gui gui = Gui.getInstance();
        gui.getStageLagerTabs().setTitle("Lager- og fadoversigt");
        //gui.getLagerTabsController().clearText();
        gui.getStageLagerTabs().show();
    }

    /**
     * Åbner panelet til at oprette et destillat
     */


    public void clickOnLagerAndOpenNewWindow(MouseEvent mouseEvent){
        if (mouseEvent.getClickCount() == 2 && !lwLagre.getSelectionModel().getSelectedItem().equals(null)) {
            Gui gui = Gui.getInstance();
            lager = lwLagre.getSelectionModel().getSelectedItem();
            gui.getOpretFadController().setLager(lager);
            gui.getLagerTabsController().setLager(lager);
            gui.getLagerTabsController().setAddress(lager.getAddresse());
            gui.getLagerTabsController().setAntalHylder(lager.getReoler()[0].length);
            gui.getLagerTabsController().setAntalReoler(lager.getReoler().length);
            gui.getLagerTabsController().setLagerID(lager.getID());
            gui.getLagerTabsController().setAntalTommePladser(lager.getAntalTommePladser());
            gui.notifyObservers();
            visLagerTabs();
        }
    }
    public Lager getLager(){
        return lager;
    }


    @Override
    public void update () {
        Set<Destillat> destillater = Controller.getDestillater();
        Set<Lager> lagre = Controller.getLager();
        lwLagre.getItems().clear();
        /*
         lwDestillater.getItems().clear();
        for (Destillat dest : destillater) {
            lwDestillater.getItems().add(dest);
        }

         */
        for (Lager lag : lagre) {
            lwLagre.getItems().add(lag);
        }
    }
}