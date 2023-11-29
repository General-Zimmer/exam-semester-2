package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import observers.IStorageObserver;

import java.net.URL;
import java.util.HashSet;
import java.util.NoSuchElementException;

public class Gui extends Application {

    private static Gui instance;
    private HashSet<IStorageObserver> observers = new HashSet<>();

    public Gui() {
        instance = this;
    }

    private Stage stageHovedMenu;
    private Stage stageLager;
    private Stage stageDestillat;

    /**
     * Starter GUI'en
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        stageHovedMenu = stage;
        URL fxmlFileName = this.getClass().getResource("HovedMenu.fxml");
        if (fxmlFileName == null) throw new NoSuchElementException("FXML file not found");

        FXMLLoader HovedMenuLoader = new FXMLLoader(fxmlFileName);
        Parent OpretLagerMenu = HovedMenuLoader.load();
        //registerObserver(HovedMenuLoader.getController());

        stageHovedMenu.setMinWidth(OpretLagerMenu.minWidth(-1));
        stageHovedMenu.setMinHeight(OpretLagerMenu.minHeight(-1));
        Scene scene = new Scene(OpretLagerMenu);
        stageHovedMenu.setScene(scene);
        stageHovedMenu.show();


        // Lager stage
        stageLager = new Stage();
        URL fxmlFileName2 = this.getClass().getResource("OpretLager.fxml");
        if (fxmlFileName2 == null) throw new NoSuchElementException("FXML file not found");
        FXMLLoader lagerLoader = new FXMLLoader(fxmlFileName2);
        Parent OpretLager = lagerLoader.load();
        registerObserver(lagerLoader.getController());
        stageLager.setMinWidth(OpretLager.minWidth(-1));
        stageLager.setMinHeight(OpretLager.minHeight(-1));
        Scene scene2 = new Scene(OpretLager);
        stageLager.setScene(scene2);

        // Destillat stage
        stageDestillat = new Stage();
        URL fxmlFileNameDestillat = this.getClass().getResource("OpretDestillat.fxml");
        if (fxmlFileNameDestillat == null) throw new NoSuchElementException("FXML file not found");
        FXMLLoader destillatLoader = new FXMLLoader(fxmlFileNameDestillat);
        Parent OpretDestillat = destillatLoader.load();
        registerObserver(destillatLoader.getController());
        stageDestillat.setMinWidth(OpretDestillat.minWidth(-1));
        stageDestillat.setMinHeight(OpretDestillat.minHeight(-1));
        Scene sceneDestillat = new Scene(OpretDestillat);
        stageDestillat.setScene(sceneDestillat);
    }

    public void registerObserver(IStorageObserver observer) {
        observers.add(observer);
    }

    public void unregisterObserver(IStorageObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (IStorageObserver observer : observers) {
            observer.update();
        }
    }


    public static Gui getInstance() {
        return instance;
    }

    public Stage getStageHovedMenu() {
        return stageHovedMenu;
    }

    public Stage getStageLager() {
        return stageLager;
    }

    public Stage getStageDestillat() {
        return stageDestillat;
    }
}
