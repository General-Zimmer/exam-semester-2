package gui;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.*;
import observers.IStorageObserver;


import java.util.*;

public class LagerTabsController implements IStorageObserver {
    @FXML
    private ListView<FadIndhold> lwFadIndhold;

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
    private Button btnMergeFad;

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
        lwFad.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lwDestillater.getItems().clear();
        lwFad.getItems().clear();
        lwFadIndhold.getItems().clear();

        if(lager != null){
            lwDestillater.getItems().addAll(Controller.getDestillater());
            for(int i = 0; i < lager.getReoler().length; i++){
                for(int j = 0; j < lager.getReoler()[i].length; j++){
                    if(lager.getReoler()[i][j] != null){
                        lwFad.getItems().add(lager.getFad(i, j));
                        FadIndhold fadIndhold = lager.getFad(i,j).getFadindhold();
                        if(fadIndhold != null) {
                            lwFadIndhold.getItems().add(fadIndhold);
                        }
                    }
                }
            }
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

    @FXML
    public void mergeFad(){
        ObservableList<Fad> selectedItems = lwFad.getSelectionModel().getSelectedItems();
        if (selectedItems.size() == 2) {
            Fad item1 = selectedItems.get(0);
            Fad item2 = selectedItems.get(1);
            boolean godkend = visConfirmation("Godkend", "Merge " + item2.getType() + "-fadet ind i " + item1.getType() + "-fadet?");
            if (godkend) {
                Controller.mergeFad(item1, item2);
                Gui gui = Gui.getInstance();
                gui.notifyObservers();
            }
        } else {
            visAlert("Fejl.","Vælg venligst præcis 2 fad!");
        }
    }

    /**
     * Åbner fad oversigt panelet.
     */
    @FXML
    public void visFadPane() {
        Gui gui = Gui.getInstance();
        gui.getStageVisFad().setTitle("Vis fad");
        gui.getStageVisFad().show();
        gui.notifyObservers();
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
     * Åbner og viser fad panelet
     * @param mouseEvent registrerer når der klikkes på knappen.
     */
    public void clickOnFadAndOpenNewWindow(MouseEvent mouseEvent){
        if (mouseEvent.getClickCount() == 2 && lwFad.getSelectionModel().getSelectedItem() != null) {
            Gui gui = Gui.getInstance();
            Fad fad = getFad();
            gui.getVisFadController().setFields(fad, lager);
            visFadPane();
        }
    }

    /**
     * Opdaterer informationerne omkring fyld, under fad.
     * @param gui vores gui
     * @param fyld fyld som bliver opdateret.
     */
    public void updateFadIndholdPane(Gui gui, FadIndhold indhold){
        gui.getVisFadIndholdController().setFad(indhold.getFad());
        for(Fyld fyld : indhold.getFyld()){
            gui.getVisFadIndholdController().setFyld(fyld);
            for (Map.Entry<Destillat, Float> entry : fyld.getDestillater().entrySet()) {
                Destillat destillat = entry.getKey();
                Float mængde = entry.getValue();
                gui.getVisFadIndholdController().setDestillat(destillat);
            }
        }
    }

    public String printMedarbejdere(FadIndhold fadIndhold) {
        List<String> medarbejdere = new ArrayList<>();
        for(Fyld fyld : fadIndhold.getFyld()) {
            medarbejdere.add(fyld.getMedarbejdere());
        }
        StringBuilder print = new StringBuilder();
        int length = medarbejdere.size();
        int i = 0;
        while (i < length - 1) {
            print.append(medarbejdere.get(i)).append(", ");
            i++;
        }
        print.append(medarbejdere.get(length - 1));
        return print.toString();
    }


    /**
     * Åbner og viser panelet for fyld under fad.
     * @param mouseEvent registrerer når der bliver klikket på knappen.
     */
    public void clickOnFadIndholdAndShowSpecs(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2 && lwFadIndhold.getSelectionModel().getSelectedItem() != null) {
            Gui gui = Gui.getInstance();
            FadIndhold fadIndhold = lwFadIndhold.getSelectionModel().getSelectedItem();
            gui.getVisFadIndholdController().setDato(fadIndhold.beregnOplaringstid());
            if (fadIndhold != null && !fadIndhold.getFyld().isEmpty() && !fadIndhold.getFad().isEmpty()) {

                gui.getVisFadIndholdController().setIndhold(fadIndhold);
                gui.getVisFadIndholdController().setMedarbejder(printMedarbejdere(fadIndhold));
                updateFadIndholdPane(gui, fadIndhold);
                visFadIndholdPane();
            } else
                visAlert("Fejl","Der enten ikke et fyld eller et fad i dette fadindhold! Opret et fyld først.");
        }
    }


    /**
     * Åbner og viser panelet for at oprette fyld objekter.
     */
    @FXML
    public void visOpretFyldPane(){
        if (lwFad.getSelectionModel().getSelectedItem() == null && lwDestillater.getSelectionModel().getSelectedItem() == null) {
            visAlert("Vælg fad OG destillat", "Du skal både vælge et fad og et destillat!");
            return;
        }
        Gui gui = Gui.getInstance();
        gui.getOpretFyldController().setFad(lwFad.getSelectionModel().getSelectedItem());
        gui.getOpretFyldController().setDestillat(lwDestillater.getSelectionModel().getSelectedItem());
        gui.getStageOpretFyld().setTitle("Opret fyld");
        gui.getStageOpretFyld().show();
    }


    /**
     * Viser fadindhold-panelet.
     */
    @FXML
    public void visFadIndholdPane(){
        Gui gui = Gui.getInstance();
        gui.getStageVisFyld().setTitle("Fadindhold");
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

    public void visAlert(String title, String besked) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(besked);
        alert.showAndWait();
    }
    public boolean visConfirmation(String title, String besked) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(besked);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
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

    public Fad getFad() {
        return lwFad.getSelectionModel().getSelectedItem();
    }
}
