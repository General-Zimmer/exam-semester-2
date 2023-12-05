package gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private PasswordField txfAntal;

    @FXML
    private TextField txfUUID;

    @FXML
    private TextField txfMængde;

    @Override
    public void update() {
    }
    public void setMaltBatch(int maltbatch){
        txfMaltBatch.setText(""+maltbatch);
    }

    public void setID(UUID ID){
        txfUUID.setText(ID.toString());
    }

    public void setKornSort(String kornsort){
        txfKornSort.setText(kornsort);
    }

    public void setMængde(float mængde){
        txfMængde.setText("" + mængde);
    }

    public void setAlkoholProcent(float alkoholprocent){
        txfAlkoholProcent.setText("" + alkoholprocent);
    }

    public void setDestillering(String destillering){
        txfDestillering.setText(destillering);
    }

    public void setDestillationsDato(LocalDate dato){
        txfDestillationsdato.setText(dato.toString());
    }
    public void setKommentar(String kommentar){
        txaKommentar.setText(kommentar);
    }

    @FXML
    public void visDestillatPaneLuk() {
        Gui gui = Gui.getInstance();
        gui.getStageVisDestillat().close();
    }

}
