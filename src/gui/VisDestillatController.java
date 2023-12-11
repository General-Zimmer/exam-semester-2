package gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Destillat;
import model.Lager;
import observers.IStorageObserver;

import java.time.LocalDate;
import java.util.UUID;

public class VisDestillatController implements IStorageObserver {

    @FXML
    private Button btnLuk;
    @FXML
    private Label lblAlkoholProcent;

    @FXML
    private Label lblDestillationsdato;

    @FXML
    private Label lblDestillering;

    @FXML
    private Label lblKommentar;

    @FXML
    private Label lblKornSort;

    @FXML
    private Label lblMaltBatch;

    @FXML
    private Label lblMængde;

    @FXML
    private Label lblUUID;

    @FXML
    private TextArea txaKommentar;

    @FXML
    private TextField txfAlkoholProcent;

    @FXML
    private TextField txfDestillationsdato;

    @FXML
    private TextField txfDestillering;

    @FXML
    private TextField txfKornSort;

    @FXML
    private TextField txfMaltBatch;

    @FXML
    private TextField txfUUID;

    @FXML
    private TextField txfMængde;

    /**
     * Sørger for at informationerne bliver loaded ind, når man åbner destillat panelet
     * @param destillat
     */
    public void setFields(Destillat destillat){
        txaKommentar.setText(destillat.getKommentar());
        txfDestillationsdato.setText(destillat.getDestillationsDato().toString());
        txfDestillering.setText(destillat.getDestillering());
        txfAlkoholProcent.setText("" + destillat.getAlkoholProcent());
        txfMængde.setText("" + destillat.getMængde());
        txfKornSort.setText(destillat.getKornsort());
        txfUUID.setText(destillat.getID().toString());
        txfMaltBatch.setText(""+destillat.getMaltBatch());
    }


    /**
     * Lukker vinduet for vis destillat
     */
    @FXML
    public void visDestillatPaneLuk() {
        Gui gui = Gui.getInstance();
        gui.getStageVisDestillat().close();
    }
    @Override
    public void update() {
    }

}
