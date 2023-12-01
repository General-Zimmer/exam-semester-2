package gui;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import observers.IStorageObserver;

import java.util.UUID;

public class LagerTabsController implements IStorageObserver {


    @FXML
    private Label lblAddresse;

    @FXML
    private Label lblAntalHylder;

    @FXML
    private Label lblAntalReoler;

    @FXML
    private Label lblAntalTommePladser;

    @FXML
    private Label lblFad;

    @FXML
    private Label lblID;

    @FXML
    private ListView<?> lwFad;

    @FXML
    private AnchorPane paneFadTab;

    @FXML
    private Tab tabFadOversigt;

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
    private TextField txfID;

    @Override
    public void update() {
    }

    public void setAddress(String thing){
        txfAddresse.setText(thing);
    }


    public void setID(UUID ID){
        txfID.setText(ID.toString());
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

}
