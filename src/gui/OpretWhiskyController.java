package gui;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import model.Destillat;
import model.FadType;
import model.Fyld;
import model.Kvalitet;
import observers.IStorageObserver;

import java.time.LocalDate;
import java.util.HashMap;

public class OpretWhiskyController implements IStorageObserver, OpretInterface {

    @FXML
    private Button btnGem;

    @FXML
    private Button btnLuk;

    @FXML
    private DatePicker dpWhiskyDato;

    @FXML
    private Label lblFyld;

    @FXML
    private Label lblMængde;

    @FXML
    private MenuItem miBLENDED;

    @FXML
    private MenuButton miButton;

    @FXML
    private MenuItem miSINGLECASK;

    @FXML
    private MenuItem miSINGLEMALT;

    @FXML
    private TextField txfFyld;

    @FXML
    private TextField txfMængde;
    private Kvalitet kvalitet;
    private Fyld fyld;

    public void setFyld(Fyld fyld){
        this.fyld = fyld;
    }
    @FXML
    public void indhentMenuItem(ActionEvent event) {
        MenuItem selectedMenuItem = (MenuItem) event.getSource();
        String selectedValue = selectedMenuItem.getText();
        miButton.setText(selectedValue);
        kvalitet = Kvalitet.valueOf(selectedValue);
    }
    @Override
    public void opretException() {
        try{
        LocalDate whiskyDato = dpWhiskyDato.getValue();
        float mængde = Float.parseFloat(txfMængde.getText());
        if (mængde <= 0) {
            throw new IllegalArgumentException("Vælg venligst en værdi over 0.");
        }

        Controller.createWhisky(whiskyDato, kvalitet, fyld, mængde);
        clearAllTextFields();
        opretVindueClose();

    } catch (NumberFormatException e) {
        visAlert("Ugyldigt input", "Indtast et gyldigt input");
    } catch (IllegalArgumentException e) {
        visAlert("Ugyldigt input", e.getMessage());
        }
    }



    @Override
    public void clearAllTextFields() {

    }

    @FXML
    public void opretVindueClose() {
        Gui gui = Gui.getInstance();
        gui.getStageOpretWhisky().close();
        clearAllTextFields();
    }


    /**
     * Sørger for at ovennævnte exceptions bliver kastet.
     */
    @FXML
    public void opretOK() {
        Gui gui = Gui.getInstance();
        opretException();
    }


    @Override
    public void visAlert(String title, String besked) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(besked);
        alert.showAndWait();
    }

    @Override
    public void update() {

    }
}
