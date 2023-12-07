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

    /**
     * Opdaterer lager tabs.
     */
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

    /**
     * Åbner panelet til at oprette fad.
     */
    @FXML
    public void opretFadPane() {
        Gui gui = Gui.getInstance();
        gui.getStageOpretFad().setTitle("Opret fad");
        gui.getStageOpretFad().show();
    }


    /**
     * Åbner fad oversigt panelet.
     */
    @FXML
    public void visFadPane() {
        Gui gui = Gui.getInstance();
        gui.getStageVisFad().setTitle("Vis fad");
        gui.getStageVisFad().show();
    }

    /**
     * Sørger for at vise de korrekte informationer i text fields under lager.
     * @param lager
     */
    public void setFields(Lager lager){
        this.lager = lager;
        txfAddresse.setText(lager.getAddresse());
        txfLagerID.setText(lager.getID().toString());
        txfAntalTommePladser.setText("" + lager.getAntalTommePladser());
        txfAntalHylder.setText("" + lager.getReoler()[0].length);
        txfAntalReoler.setText("" + lager.getReoler().length);
    }

    /**
     * Rydder teksten.
     */

    public void clearText(){
    }


    /**
     * Åbner og viser fad panelet
     * @param mouseEvent registrerer når der klikkes på knappen.
     */
    public void clickOnFadAndOpenNewWindow(MouseEvent mouseEvent){
        if (mouseEvent.getClickCount() == 2 && lwFad.getSelectionModel().getSelectedItem() != null) {
            Gui gui = Gui.getInstance();
            fad = lwFad.getSelectionModel().getSelectedItem();
            gui.getVisFadController().setFyld(fad.getFyld());
            gui.getVisFadController().setFields(fad, lager);
            visFadPane();
        }
    }

    /**
     * Opdaterer informationerne omkring fyld, under fad.
     * @param gui vores gui
     * @param fyld fyld som bliver opdateret.
     */
    public void updateFyldPane(Gui gui, Fyld fyld){
        for (Fad fad : fyld.getFad()) {

            gui.getVisFyldController().setFad(fad);
        }

        for (Map.Entry<Destillat, Float> entry : fyld.getDestillater().entrySet()) {
            Destillat destillat = entry.getKey();
            Float mængde = entry.getValue();
            gui.getVisFyldController().setDestillat(destillat);
        }
        HashSet<Whisky> whisky = fyld.getWhiskyPåFyld();
        for(Whisky whisk : whisky){
            gui.getVisFyldController().setWhisky(whisk);
        }


    }


    /**
     * Åbner og viser panelet for fyld under fad.
     * @param mouseEvent registrerer når der bliver klikket på knappen.
     */
    public void clickOnFyldAndShowSpecs(MouseEvent mouseEvent){
        if (mouseEvent.getClickCount() == 2 && lwFyld.getSelectionModel().getSelectedItem() != null) {
            Gui gui = Gui.getInstance();

            Fyld fyld = lwFyld.getSelectionModel().getSelectedItem();

            if(fyld != null) {
                updateFyldPane(gui, fyld);
                gui.getOpretWhiskyController().setFyld(fyld);
                gui.getVisFyldController().setDato(fyld.getStartDato());
                gui.getVisFyldController().setMedarbejder(fyld.getMedarbejdere());
                visFyldPane();
            } else System.out.println(fyld == null);
        }
    }


    /**
     * Åbner og viser panelet for at oprette fyld objekter.
     */
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


    /**
     * Viser fyld panelet.
     */
    @FXML
    public void visFyldPane(){
        Gui gui = Gui.getInstance();
        gui.getStageVisFyld().setTitle("Fyld");
        gui.getStageVisFyld().show();
    }


    /**
     * Åbner panelet for destillat.
     */
    @FXML
    public void visDestillatPane() {
        Gui gui = Gui.getInstance();
        gui.getStageVisDestillat().setTitle("Destillat batch " + destillat.getMaltBatch() + ", " + "kornsort '" + destillat.getKornsort() + "'");
        gui.getStageVisDestillat().show();
    }


    /**
     * Fuld metode til at åbne for destillat panelet
     * @param mouseEvent registrere klikket på knappen for at åbne vindue.
     */
    public void clickOnDestillatAndOpenNewWindow(MouseEvent mouseEvent){
        if (mouseEvent.getClickCount() == 2 && lwDestillater.getSelectionModel().getSelectedItem() != null) {
            Gui gui = Gui.getInstance();
            destillat = lwDestillater.getSelectionModel().getSelectedItem();
            gui.getVisDestillatController().setFields(destillat);
            visDestillatPane();
        }
    }


    /**
     * Åbner panelet for at oprette et destillat
     */
    @FXML
    public void opretDestillatPane() {
        Gui gui = Gui.getInstance();
        gui.getStageDestillat().setTitle("Opret destillat");
        gui.getStageDestillat().show();
    }


    /**
     * Lukker for opretFad panelet.
     */
    @FXML
    public void opretFadPaneLuk() {
        Gui gui = Gui.getInstance();
        gui.getStageLagerTabs().close();
    }
}
