package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import observers.IStorageObserver;

public class VisFyldController implements IStorageObserver {
    @FXML
    private Button btnLuk;

    @FXML
    public void visFyldPaneLuk() {
        Gui gui = Gui.getInstance();
        gui.getStageVisFyld().close();
    }

    @Override
    public void update() {

    }
}
