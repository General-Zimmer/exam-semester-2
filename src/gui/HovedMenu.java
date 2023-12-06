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

    /**
     * Åbner panelet til at vise lageroversigt over bestemt lager
     */
    @FXML
    public void visLagerTabs() {
        Gui gui = Gui.getInstance();
        gui.getStageLagerTabs().setTitle("Lager- og fadoversigt");
        //gui.getLagerTabsController().clearText();
        gui.getStageLagerTabs().show();
    }


    /**
     * Åbner lagertabs
     * @param mouseEvent registrerer når du klikker på knappen.
     */
    public void clickOnLagerAndOpenNewWindow(MouseEvent mouseEvent){
        if (mouseEvent.getClickCount() == 2 && lwLagre.getSelectionModel().getSelectedItem() != null) {
            Gui gui = Gui.getInstance();
            lager = lwLagre.getSelectionModel().getSelectedItem();
            gui.getOpretFadController().setLager(lager);
            gui.getLagerTabsController().setFields(lager);
            gui.notifyObservers();
            visLagerTabs();
        }
    }
    public Lager getLager(){
        return lager;
    }

    /**
     * Opdaterer informationerne
     */
    @Override
    public void update () {
        Set<Destillat> destillater = Controller.getDestillater();
        Set<Lager> lagre = Controller.getLager();
        lwLagre.getItems().clear();

        for (Lager lag : lagre) {
            lwLagre.getItems().add(lag);
        }
    }
}