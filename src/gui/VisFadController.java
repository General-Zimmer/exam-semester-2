package gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Fad;
import model.Fyld;
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
    private Fad fad;


    @Override
    public void update() {
    }


    /**
     * Sørger for at indlæse vores data, når man åbner vis fad panelet.
     * @param fad
     */
    // ?????????????????? lol
    public void initFyld(){
        if(fad != null){
        txfFyld.setText(fad.getFyld().toString());}
    }
    public void clearAllFields(){
        txfFyld.clear();
        txfFadHistorik.clear();
        txfPladsNummer.clear();
        txfFadID.clear();
        txfLeverandør.clear();
        txfStørrelse.clear();
        txfFadType.clear();
    }

    public void setFields(Fad fad, Lager lager) {
        this.fad = fad;
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
