package gui;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Fad;
import model.Fyld;
import model.Lager;
import observers.IStorageObserver;

import java.util.logging.Level;

public class VisFadController implements IStorageObserver {
    @FXML
    private Button btnLuk;

    @FXML
    private Button btnTilføjWhisky;

    @FXML
    private Label lblAntalFills;

    @FXML
    private Label lblFadHistorik;

    @FXML
    private Label lblFadID;

    @FXML
    private Label lblFadType;

    @FXML
    private Label lblFyld;

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
    private TextField txfFyld;

    @FXML
    private TextField txfLeverandør;

    @FXML
    private TextField txfPladsNummer;

    @FXML
    private TextField txfReol;

    @FXML
    private TextField txfStørrelse;
    private Fyld fyld;


    @Override
    public void update() {
        Gui gui = Gui.getInstance();
        Fad fad = gui.getLagerTabsController().getFad();
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


    /**
     * Sørger for at indlæse vores data, når man åbner vis fad panelet.
     * @param fad
     */
    // ?????????????????? lol

    public void clearAllFields(){
        txfFyld.clear();
        txfFadHistorik.clear();
        txfPladsNummer.clear();
        txfFadID.clear();
        txfLeverandør.clear();
        txfStørrelse.clear();
        txfFadType.clear();
    }

    public void setFields(Fad fad, Lager lager, Fyld fyld) {
        if(fyld != null) {
            txfFyld.setText(fyld.toString());
        }
        txfFadHistorik.setText(fad.getFadHistorik());
        txfFadID.setText(fad.getID().toString());
        txfLeverandør.setText(fad.getLeverandør());
        txfFadType.setText(fad.getType().toString());
        txfAntalFills.setText(""+fad.getFillAntal());
        txfStørrelse.setText(""+fad.getStørrelse());
}

    public void setPlads(int reol, int plads){
        txfReol.setText("" + reol);
        txfPladsNummer.setText("" + plads);
    }
    public void setFyld(Fyld fyld){
        this.fyld = fyld;
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
