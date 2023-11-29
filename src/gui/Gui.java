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

    private Stage stageLagerMenu;
    private Stage stageLager;
    private Stage stageDestillat;

    @Override
    public void start(Stage stage) throws Exception {
        stageLagerMenu = stage;
        URL fxmlFileName = this.getClass().getResource("OpretLagerMenu.fxml");
        if (fxmlFileName == null) throw new NoSuchElementException("FXML file not found");

        Parent OpretLagerMenu = FXMLLoader.load(fxmlFileName);
        stageLagerMenu.setMinWidth(OpretLagerMenu.minWidth(-1));
        stageLagerMenu.setMinHeight(OpretLagerMenu.minHeight(-1));
        Scene scene = new Scene(OpretLagerMenu);
        stageLagerMenu.setScene(scene);
        stageLagerMenu.show();


        // MORE WINDOWS
        stageLager = new Stage();
        URL fxmlFileName2 = this.getClass().getResource("OpretLager.fxml");
        if (fxmlFileName2 == null) throw new NoSuchElementException("FXML file not found");
        Parent OpretLager = FXMLLoader.load(fxmlFileName2);
        stageLager.setMinWidth(OpretLager.minWidth(-1));
        stageLager.setMinHeight(OpretLager.minHeight(-1));
        Scene scene2 = new Scene(OpretLager);
        stageLager.setScene(scene2);

        // Destillat stage
        stageDestillat = new Stage();
        URL fxmlFileNameDestillat = this.getClass().getResource("OpretDestillat.fxml");
        if (fxmlFileNameDestillat == null) throw new NoSuchElementException("FXML file not found");
        Parent OpretDestillat = FXMLLoader.load(fxmlFileNameDestillat);
        stageDestillat.setMinWidth(OpretDestillat.minWidth(-1));
        stageDestillat.setMinHeight(OpretDestillat.minHeight(-1));
        Scene sceneDestillat = new Scene(OpretDestillat);
        stageDestillat.setScene(sceneDestillat);
    }


    public static Gui getInstance() {
        return instance;
    }

    public Stage getStageLagerMenu() {
        return stageLagerMenu;
    }

    public Stage getStageLager() {
        return stageLager;
    }

    public Stage getStageDestillat() {
        return stageDestillat;
    }
}
