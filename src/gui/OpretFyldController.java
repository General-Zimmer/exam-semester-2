package gui;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Destillat;
import model.Fad;
import observers.IStorageObserver;

import java.util.HashSet;
import java.util.Set;

public class OpretFyldController implements IStorageObserver {

    @FXML
    private TextField txfDestillat;

    @FXML
    private TextField txfFad;
    private Destillat destillat;
    private Fad fad;

    @Override
    public void update() {
    }

    public void setDestillat(Destillat destillat){
        this.destillat = destillat;
    }

    public void setFad(Fad fad){
        this.fad = fad;
    }
}
