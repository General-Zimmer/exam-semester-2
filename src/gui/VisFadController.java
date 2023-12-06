package gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Fad;
import model.Fyld;
import observers.IStorageObserver;

public class VisFadController implements IStorageObserver {

    @FXML
    private Button btnLuk;

    @FXML
    private TextField txfAntalFills;

    @FXML
    private TextField txfFadHistorik;

    @FXML
    private TextField txfFadID;

    @FXML
    private TextField txfFadType;

    @FXML
    private TextField txfFyld;

    @FXML
    private TextField txfLeverandør;

    @FXML
    private TextField txfStørrelse;
    private Fyld fyld;


    @Override
    public void update() {
    }

    public void setFields(Fad fad) {
        if(!txfFyld.getText().isBlank()) {
            txfFyld.setText(fad.getFyld().toString());
        }
        txfFadHistorik.setText(fad.getFadHistorik());
        txfFadID.setText(fad.getID().toString());
        txfLeverandør.setText(fad.getLeverandør());
        txfFadType.setText(fad.getType().toString());
        txfAntalFills.setText(""+fad.getFillAntal());
        txfStørrelse.setText(""+fad.getStørrelse());
}
    public void setFyld(Fyld fyld){
        this.fyld = fyld;
    }

    @FXML
    public void visFadPaneLuk() {
        Gui gui = Gui.getInstance();
        gui.getStageVisFad().close();
    }

}
