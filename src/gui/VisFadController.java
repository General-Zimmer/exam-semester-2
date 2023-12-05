package gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import observers.IStorageObserver;

public class VisFadController implements IStorageObserver {

    @FXML
    private Button btnLuk;

    @Override
    public void update() {
    }


    @FXML
    public void visFadPaneLuk() {
        Gui gui = Gui.getInstance();
        gui.getStageVisFad().close();
    }

}
