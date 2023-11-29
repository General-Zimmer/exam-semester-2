package gui;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.Date;

public class OpretDestillatController {

    @FXML
    private Button btnCancelDestillat;

    @FXML
    private Button btnOKDestillat;

    @FXML
    private DatePicker dpDestillationsDato;

    @FXML
    private Label lblAlkoholPrc;

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
    private Label lblOpretLager;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextArea txaKommentar;

    @FXML
    private TextField txfAlkoholProcent;

    @FXML
    private TextField txfDestillering;

    @FXML
    private TextField txfKornSort;

    @FXML
    private TextField txfMaltBatch;

    @FXML
    private TextField txfMængde;


    @FXML
    public void opretDestillatOK() {
        Gui gui = Gui.getInstance();
        int maltBatch = Integer.parseInt(txfMaltBatch.getText());
        String kornsort = txfKornSort.getText();
        float mængde = Float.parseFloat(txfMængde.getText());
        float alkoholProcent = Float.parseFloat(txfAlkoholProcent.getText());
        String destillering = txfDestillering.getText();
        DatePicker destillationsDato = dpDestillationsDato;
        String kommentar = txaKommentar.toString();

        Controller.createDestillat(maltBatch, kornsort, mængde, alkoholProcent, destillering,
                destillationsDato, kommentar);

        /*
        txfMaltBatch.clear();
        txfKornSort.clear();
        txfMængde.clear();
        txfAlkoholProcent.clear();
        txfDestillering.clear();
        txaKommentar.clear();

         */
        gui.getStageDestillat().close();
    }
}
