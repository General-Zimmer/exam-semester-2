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
    private Lager lager;
    private Destillat destillat;


    /**
     * Åbner panelet til at oprette et lager
     */
    @FXML
    public void opretLagerPane() {
        Gui gui = Gui.getInstance();
        gui.getStageLager().show();
    }

    @FXML
    public void visLagerTabs() {
        Gui gui = Gui.getInstance();
        gui.getStageLagerTabs().show();
    }

    /**
     * Åbner panelet til at oprette et destillat
     */
    @FXML
    public void opretDestillatPane() {
        Gui gui = Gui.getInstance();
        gui.getStageDestillat().show();
    }

    @FXML
    public void visDestillatPane() {
        Gui gui = Gui.getInstance();
        gui.getStageVisDestillat().show();
    }

     // SKAL LAVES FÆRDIG TIL NÆSTE ITERATION. DRØFTES MED HANNE OM DESIGN AF BRUGERGRÆNSEFLADE YES
    public void clickOnLagerAndOpenNewWindow(MouseEvent mouseEvent){
        if (mouseEvent.getClickCount() == 2 && !lwLagre.getSelectionModel().getSelectedItem().equals(null)) {
            Gui gui = Gui.getInstance();
            lager = lwLagre.getSelectionModel().getSelectedItem();
            gui.getLagerTabsController().setLager(lager);
            gui.getLagerTabsController().setAddress(lager.getAddresse());
            gui.getLagerTabsController().setAntalHylder(lager.getReoler()[0].length);
            gui.getLagerTabsController().setAntalReoler(lager.getReoler().length);
            gui.getLagerTabsController().setLagerID(lager.getID());
            gui.getLagerTabsController().setAntalTommePladser(lager.getAntalTommePladser());
            visLagerTabs();
        }
    }


    public void clickOnDestillatAndOpenNewWindow(MouseEvent mouseEvent){
        if (mouseEvent.getClickCount() == 2 && !lwDestillater.getSelectionModel().getSelectedItem().equals(null)) {
            Gui gui = Gui.getInstance();
            destillat = lwDestillater.getSelectionModel().getSelectedItem();
            gui.getVisDestillatController().setID(destillat.getID());
            gui.getVisDestillatController().setAlkoholProcent(destillat.getAlkoholProcent());
            gui.getVisDestillatController().setDestillering(destillat.getDestillering());
            gui.getVisDestillatController().setMaltBatch(destillat.getMaltBatch());
//            gui.getVisDestillatController().setMængde(destillat.getMængde());
            gui.getVisDestillatController().setKornSort(destillat.getKornsort());
            gui.getVisDestillatController().setKommentar(destillat.getKommentar());
            //gui.getVisDestillatController().setDestillationsDato(destillat.getDestillationsDato());

            visDestillatPane();
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
        lwDestillater.getItems().clear();
        for (Destillat dest : destillater) {
            lwDestillater.getItems().add(dest);
        }
        for (Lager lag : lagre) {
            lwLagre.getItems().add(lag);
        }
    }
}