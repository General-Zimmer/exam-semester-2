package gui;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Destillat;
import model.Fad;
import model.Fyld;
import model.Lager;
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

        Set<Fad> fade = new HashSet<>();

        lwFad.getItems().clear();

        if(lager != null){
            for(int i = 0; i < lager.getReoler().length; i++){
                for(int j = 0; j < lager.getReoler()[i].length; j++){
                    if(lager.getReoler()[i][j] != null){
                        lwFad.getItems().add(lager.getFad(i, j));
                        lwFyld.getItems().add(lager.getFad(i,j).getFyld());
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
    public void setLager(Lager lager){
        this.lager = lager;
    }

    public void setAddress(String adresse){
        txfAddresse.setText(adresse);
    }


    public void setLagerID(UUID ID){
        txfLagerID.setText(ID.toString());
    }

    public void setAntalTommePladser(int antalTommePladser){
        txfAntalTommePladser.setText("" + antalTommePladser);
    }

    public void setAntalHylder(int antalHylder){
        txfAntalHylder.setText("" + antalHylder);
    }

    public void setAntalReoler(int antalReoler){
        txfAntalReoler.setText("" + antalReoler);
    }

    public void clearText(){

    }

    public void clickOnFadAndOpenNewWindow(MouseEvent mouseEvent){
        if (mouseEvent.getClickCount() == 2 && lwFad.getSelectionModel().getSelectedItem() != null) {
            clearText();
            Gui gui = Gui.getInstance();
            fad = lwFad.getSelectionModel().getSelectedItem();
            visFadPane();
/*
                txfFadHistorik.setText(fad.getFadHistorik());
                txfFadID.setText(fad.getID().toString());
                txfFyld.setText(fad.getFyld().toString());
                txfLeverandør.setText(fad.getLeverandør());
                txfFadType.setText(fad.getType().toString());
                txfAntalFills.setText("" + fad.getFillAntal());
                txfStørrelse.setText("" + fad.getStørrelse());
                }
 */
        }
    }

    public void clickOnFyldAndShowSpecs(MouseEvent mouseEvent){
        if (mouseEvent.getClickCount() == 2 && lwFyld.getSelectionModel().getSelectedItem() != null) {
            clearText();
            Gui gui = Gui.getInstance();
            fyld = lwFyld.getSelectionModel().getSelectedItem();
            if(fyld != null) {
                visFyldPane();
            }
        }
    }

    public void clickOnFadAndDestillatToCreateFyld(MouseEvent mouseEvent){
        try {
                clearText();
                Gui gui = Gui.getInstance();
                fad = lwFad.getSelectionModel().getSelectedItem();
                destillat = lwDestillater.getSelectionModel().getSelectedItem();
                if (fad != null && destillat != null) {
                    gui.getOpretFyldController().setDestillat(destillat);
                    gui.getOpretFyldController().setFad(fad);
                    gui.getOpretFyldController().setFadSpecs(fad);
                    gui.getOpretFyldController().setDestillatSpecs(destillat);
                    visOpretFyldPane();
                }else {

                    showAlert("Error", "Vælg både et destillat OG et fad.");
                }
        } catch (NullPointerException e) {

            showAlert("Error", "Fejl.");
        } catch (RuntimeException e) {
            showAlert("Error", "Fejl - Runtime error.");
        }
    }

    // Utility method to show an alert box
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    public void visOpretFyldPane(){
        Gui gui = Gui.getInstance();
        gui.getStageOpretFyld().setTitle("Opret fyld");
        gui.getStageOpretFyld().show();
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
        if (mouseEvent.getClickCount() == 2 && !lwDestillater.getSelectionModel().getSelectedItem().equals(null)) {
            Gui gui = Gui.getInstance();
            destillat = lwDestillater.getSelectionModel().getSelectedItem();
            gui.getVisDestillatController().setID(destillat.getID());
            gui.getVisDestillatController().setAlkoholProcent(destillat.getAlkoholProcent());
            gui.getVisDestillatController().setDestillering(destillat.getDestillering());
            gui.getVisDestillatController().setMaltBatch(destillat.getMaltBatch());
            gui.getVisDestillatController().setMængde(destillat.getMængde());
            gui.getVisDestillatController().setKornSort(destillat.getKornsort());
            gui.getVisDestillatController().setKommentar(destillat.getKommentar());
            gui.getVisDestillatController().setDestillationsDato(destillat.getDestillationsDato());

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
