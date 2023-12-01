package gui;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Destillat;
import model.Fad;
import model.Lager;
import observers.IStorageObserver;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class LagerTabsController implements IStorageObserver {

    @FXML
    private Label lblAddresse;

    @FXML
    private Button btnTilføjFad;

    @FXML
    private Label lblAntalFills;

    @FXML
    private Label lblAntalHylder;

    @FXML
    private Label lblAntalReoler;

    @FXML
    private Label lblAntalTommePladser;

    @FXML
    private Label lblFad;

    @FXML
    private Label lblFadHistorik;

    @FXML
    private Label lblFadType;

    @FXML
    private Label lblFyld;

    @FXML
    private Label lblFadID;
    @FXML
    private Label lblLagerID;

    @FXML
    private Label lblLeverandør;

    @FXML
    private Label lblStørrelse;

    @FXML
    private ListView<Fad> lwFad;
    private Fad fad;

    @FXML
    private AnchorPane paneFadTab;

    @FXML
    private Tab tabFadOversigt;

    @FXML
    private Tab tabLagerOversigt;

    @FXML
    private TextField txfAddresse;

    @FXML
    private TextField txfAntalFills;

    @FXML
    private TextField txfAntalHylder;

    @FXML
    private TextField txfAntalReoler;

    @FXML
    private TextField txfAntalTommePladser;

    @FXML
    private TextField txfFadHistorik;

    @FXML
    private TextField txfFadType;

    @FXML
    private TextField txfFyld;

    @FXML
    private TextField txfFadID;
    @FXML
    private TextField txfLagerID;

    @FXML
    private TextField txfLeverandør;

    @FXML
    private TextField txfStørrelse;

    private Lager lager;

    @Override
    public void update() {
        Set<Fad> fade = new HashSet<>();

        lwFad.getItems().clear();

        if(lager != null){
            for(int i = 0; i < lager.getReoler().length; i++){
                for(int j = 0; j < lager.getReoler()[i].length; j++){
                    if(lager.getReoler()[i][j] != null){
                        lwFad.getItems().add(lager.getFad(i, j));
                    }
                }
            }
        }
    }
    @FXML
    public void opretFadPane() {
        Gui gui = Gui.getInstance();
        gui.getStageOpretFad().show();
    }
    public void setLager(Lager lager){
        this.lager = lager;
    }

    public void setAddress(String adresse){
        txfAddresse.setText(adresse);
    }


    public void setFadID(UUID ID){
        txfFadID.setText(ID.toString());
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

    public void clickOnFadAndShowSpecs(MouseEvent mouseEvent){
        if (mouseEvent.getClickCount() == 2 && !lwFad.getSelectionModel().getSelectedItem().equals(null)) {
            Gui gui = Gui.getInstance();
            fad = lwFad.getSelectionModel().getSelectedItem();
        }
    }

}
