package gui;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.*;
import observers.IStorageObserver;

import java.util.*;

public class LagerTabsController implements IStorageObserver {
    @FXML
    private ListView<Fyld> lwFyld;

    @FXML
    private ListView<Destillat> lwDestillater;

    @FXML
    private Label lblDestillater;
    @FXML
    private Button btnOpretFyld;

    @FXML
    private Label lblAddresse;

    @FXML
    private Button btnTilføjFad;

    @FXML
    private Button btnLuk;

    @FXML
    private Label lblAntalHylder;

    @FXML
    private Label lblAntalReoler;

    @FXML
    private Label lblAntalTommePladser;

    @FXML
    private Label lblFad;

    @FXML
    private Label lblLagerID;

    @FXML
    private ListView<Fad> lwFad;
    private Fad fad;

    @FXML
    private AnchorPane paneFadTab;

    @FXML
    private Tab tabOpretFyld;

    @FXML
    private Tab tabLagerOversigt;

    @FXML
    private TextField txfAddresse;

    @FXML
    private TextField txfAntalHylder;

    @FXML
    private TextField txfAntalReoler;

    @FXML
    private TextField txfAntalTommePladser;


    @FXML
    private TextField txfLagerID;
    @FXML
    private Button btnLukFadOversigt;

    private Destillat destillat;

    private Lager lager;
    private Fyld fyld;

    @Override
    public void update() {

        lwFad.getItems().clear();
        lwFyld.getItems().clear();

        if(lager != null){
            for(int i = 0; i < lager.getReoler().length; i++){
                for(int j = 0; j < lager.getReoler()[i].length; j++){
                    if(lager.getReoler()[i][j] != null){
                        lwFad.getItems().add(lager.getFad(i, j));
                        Fyld fyld = lager.getFad(i,j).getFyld();
                        if(fyld != null) {
                            lwFyld.getItems().add(fyld);
                        }
                    }
                }
            }
        }

        Set<Destillat> destillater = Controller.getDestillater();
        lwDestillater.getItems().clear();
        for (Destillat dest : destillater) {
            lwDestillater.getItems().add(dest);
        }


    }
    @FXML
    public void opretFadPane() {
        Gui gui = Gui.getInstance();
        gui.getStageOpretFad().setTitle("Opret fad");
        gui.getStageOpretFad().show();
    }

    @FXML
    public void visFadPane() {
        Gui gui = Gui.getInstance();
        gui.getStageVisFad().setTitle("Vis fad");
        gui.getStageVisFad().show();
    }
    public void setFields(Lager lager){
        this.lager = lager;
        txfAddresse.setText(lager.getAddresse());
        txfLagerID.setText(lager.getID().toString());
        txfAntalTommePladser.setText("" + lager.getAntalTommePladser());
        txfAntalHylder.setText("" + lager.getReoler()[0].length);
        txfAntalReoler.setText("" + lager.getReoler().length);
    }

    public void clearText(){
    }

    public void clickOnFadAndOpenNewWindow(MouseEvent mouseEvent){
        if (mouseEvent.getClickCount() == 2 && lwFad.getSelectionModel().getSelectedItem() != null) {
            Gui gui = Gui.getInstance();
            fad = lwFad.getSelectionModel().getSelectedItem();
            gui.getVisFadController().setFyld(fad.getFyld());
            gui.getVisFadController().setFields(fad);
            visFadPane();
        }
    }

    public void updateFyldPane(Gui gui, Fyld fyld){
        for (Fad fad : fyld.getFad()) {
            gui.getVisFyldController().setFad(fad);
        }

        for (Map.Entry<Destillat, Float> entry : fyld.getDestillater().entrySet()) {
            Destillat destillat = entry.getKey();
            Float mængde = entry.getValue();
            gui.getVisFyldController().setDestillat(destillat);
        }
    }

    public void clickOnFyldAndShowSpecs(MouseEvent mouseEvent){
        if (mouseEvent.getClickCount() == 2 && lwFyld.getSelectionModel().getSelectedItem() != null) {
            Gui gui = Gui.getInstance();

            fyld = lwFyld.getSelectionModel().getSelectedItem();

            if(fyld != null) {
                updateFyldPane(gui, fyld);

                gui.getVisFyldController().setDato(fyld.getStartDato());
                gui.getVisFyldController().setMedarbejder(fyld.getMedarbejdere());
                visFyldPane();
            }
        }
    }

    @FXML
    public void visOpretFyldPane(){
        if (lwFad.getSelectionModel().getSelectedItem() != null && lwDestillater.getSelectionModel().getSelectedItem() != null) {
            Gui gui = Gui.getInstance();
            gui.getOpretFyldController().setFad(lwFad.getSelectionModel().getSelectedItem());
            gui.getOpretFyldController().setDestillat(lwDestillater.getSelectionModel().getSelectedItem());
            gui.getStageOpretFyld().setTitle("Opret fyld");
            gui.getStageOpretFyld().show();
        }
    }

    @FXML
    public void visFyldPane(){
        Gui gui = Gui.getInstance();
        gui.getStageVisFyld().setTitle("Fyld");
        gui.getStageVisFyld().show();
    }
    @FXML
    public void visDestillatPane() {
        Gui gui = Gui.getInstance();
        gui.getStageVisDestillat().setTitle("Destillat batch " + destillat.getMaltBatch() + ", " + "kornsort '" + destillat.getKornsort() + "'");
        gui.getStageVisDestillat().show();
    }
    public void clickOnDestillatAndOpenNewWindow(MouseEvent mouseEvent){
        if (mouseEvent.getClickCount() == 2 && lwDestillater.getSelectionModel().getSelectedItem() != null) {
            Gui gui = Gui.getInstance();
            destillat = lwDestillater.getSelectionModel().getSelectedItem();
            gui.getVisDestillatController().setFields(destillat);
            visDestillatPane();
        }
    }
    @FXML
    public void opretDestillatPane() {
        Gui gui = Gui.getInstance();
        gui.getStageDestillat().setTitle("Opret destillat");
        gui.getStageDestillat().show();
    }
    @FXML
    public void opretFadPaneLuk() {
        Gui gui = Gui.getInstance();
        gui.getStageLagerTabs().close();
    }
}
