package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.NoSuchElementException;

public class Gui extends Application {

    private static Gui instance;

    public Gui() {
        instance = this;
    }

    private Parent OpretLagerMenu;

    @Override
    public void start(Stage stage) throws Exception {
        URL fxmlFileName = this.getClass().getResource("OpretLagerMenu.fxml");
        if (fxmlFileName == null) throw new NoSuchElementException("FXML file not found");

        this.OpretLagerMenu = FXMLLoader.load(fxmlFileName);
        stage.setMinWidth(OpretLagerMenu.minWidth(-1));
        stage.setMinHeight(OpretLagerMenu.minHeight(-1));
        Scene scene = new Scene(OpretLagerMenu);
        stage.setScene(scene);
        stage.show();


        // MORE WINDOWS
    }


    public static Gui getInstance() {
        return instance;
    }
}
