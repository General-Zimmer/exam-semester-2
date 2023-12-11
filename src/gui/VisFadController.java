package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Fad;
import model.Lager;
import observers.IStorageObserver;

public class VisFadController implements IStorageObserver {
    @FXML
    private Button btnLuk;

    @FXML
    private Label lblAntalFills;

    @FXML
    private Label lblFadHistorik;

    @FXML
    private Label lblFadID;

    @FXML
    private Label lblFadType;

    @FXML
    private Label lblFadIndhold;

    @FXML
    private Label lblLeverandør;

    @FXML
    private Label lblPladsNummer;

    @FXML
    private Label lblReol;

    @FXML
    private Label lblStørrelse;

    @FXML
    private TextField txfAntalFills;

    @FXML
    private TextField txfFadHistorik;

    @FXML
    private TextField txfFadID;

    @FXML
    private TextField txfFadType;

    @FXML
    private TextField txfFadIndhold;

    @FXML
    private TextField txfLeverandør;

    @FXML
    private TextField txfPladsNummer;

    @FXML
    private TextField txfReol;

    @FXML
    private TextField txfStørrelse;
    private Fad fad = null;


    @Override
    public void update() {
        Gui gui = Gui.getInstance();
        Fad fad = this.fad;
        if(fad == null){
            return;
        }
        Lager lager = fad.getLager();
        for(int i = 0; i < lager.getReoler().length; i++){
            for(int j = 0; j < lager.getReoler()[0].length; j++){
                if(fad.equals(lager.getFad(i, j))){
                    txfReol.setText("" + i);
                    txfPladsNummer.setText("" + j);
                }
            }
        }
    }

    public void setFields(Fad fad, Lager lager) {
        this.fad = fad;
        if(fad.getFadindhold() != null) {
            txfFadIndhold.setText(fad.getFadindhold().toString());
        }
        txfFadHistorik.setText(fad.getFadHistorik());
        txfFadID.setText(fad.getID().toString());
        txfLeverandør.setText(fad.getLeverandør());
        txfFadType.setText(fad.getType().toString());
        txfAntalFills.setText(""+fad.getFillAntal());
        txfStørrelse.setText(""+fad.getStørrelse());
}

    public void setPlads(int reol, int plads){
        txfReol.setText("" + (reol + 1));
        txfPladsNummer.setText("" + (plads + 1));
    }


    /**
     * Sørger for at lukke vis fad vinduet, når det ønskes.
     */
    @FXML
    public void visFadPaneLuk() {
        Gui gui = Gui.getInstance();
        gui.getStageVisFad().close();
    }

}
