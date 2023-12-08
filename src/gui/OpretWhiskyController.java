package gui;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import model.*;
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
    private Label lblFadIndhold;

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
    private TextField txfFadIndhold;

    @FXML
    private TextField txfMængde;
    private Kvalitet kvalitet;
    private FadIndhold fadIndhold;

    public void setFadIndhold(FadIndhold indhold){
        txfFadIndhold.setText(indhold.toString());
        fadIndhold = indhold;
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
        double totalMængdeTilgængelig = fadIndhold.beregnMængdeTilgængelig();

        if (mængde <= 0) {
            throw new IllegalArgumentException("Vælg venligst en værdi over 0.");
        }
        if (mængde > totalMængdeTilgængelig) {
            throw new IllegalArgumentException("Vælg venligst en mængde under " + totalMængdeTilgængelig + ".");
        }
        if (whiskyDato == null) {
            throw new IllegalArgumentException("Vælg venligst en dato.");
        }
        if(kvalitet == null){
            throw new IllegalArgumentException("Vælg venligst en kvalitet.");
        }

        Whisky whisky = Controller.createWhisky(whiskyDato, kvalitet, fadIndhold, mængde);
        Gui gui = Gui.getInstance();
        gui.getVisFadIndholdController().setFyld(fadIndhold.getFyld().get(fadIndhold.getFyld().size()-1));
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
