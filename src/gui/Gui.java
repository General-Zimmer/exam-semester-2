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
        if (instance != null) {
            throw new IllegalStateException("Cannot create new instance of singleton");
        }
        instance = this;
    }

    private Stage stageHovedMenu;
    private Stage stageLager;
    private Stage stageDestillat;
    private Stage stageLagerTabs;
    private Stage stageVisDestillat;

    private LagerTabsController lagerTabsController;
    private VisDestillatController visDestillatController;


    /**
     * Starter GUI'en
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        // Hovedmenu stage
        stageHovedMenu = stage;
        URL fxmlFileName = this.getClass().getResource("HovedMenu.fxml");
        if (fxmlFileName == null) throw new NoSuchElementException("FXML file not found");

        FXMLLoader HovedMenuLoader = new FXMLLoader(fxmlFileName);
        Parent OpretLagerMenu = HovedMenuLoader.load();
        registerObserver(HovedMenuLoader.getController());

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

        // Opret destillat stage
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

        // Lagertabs stage
        stageLagerTabs = new Stage();
        URL fxmlFileNameLagerTabs = this.getClass().getResource("LagerTabs.fxml");
        if (fxmlFileNameLagerTabs == null) throw new NoSuchElementException("FXML file not found");
        FXMLLoader LagerTabsLoader = new FXMLLoader(fxmlFileNameLagerTabs);
        Parent LagerTabs = LagerTabsLoader.load();
        registerObserver(LagerTabsLoader.getController());
        lagerTabsController = LagerTabsLoader.getController();
        stageLagerTabs.setMinWidth(LagerTabs.minWidth(-1));
        stageLagerTabs.setMinHeight(LagerTabs.minHeight(-1));
        Scene sceneLagerTabs = new Scene(LagerTabs);
        stageLagerTabs.setScene(sceneLagerTabs);

        // Vis destillat stage
        stageVisDestillat = new Stage();
        URL fxmlFileNameVisDestillat = this.getClass().getResource("VisDestillat.fxml");
        if (fxmlFileNameVisDestillat == null) throw new NoSuchElementException("FXML file not found");
        FXMLLoader VisDestillatLoader = new FXMLLoader(fxmlFileNameVisDestillat);
        Parent VisDestillat = VisDestillatLoader.load();
        registerObserver(VisDestillatLoader.getController());
        visDestillatController = VisDestillatLoader.getController();
        stageVisDestillat.setMinWidth(VisDestillat.minWidth(-1));
        stageVisDestillat.setMinHeight(VisDestillat.minHeight(-1));
        Scene sceneVisDestillat = new Scene(VisDestillat);
        stageVisDestillat.setScene(sceneVisDestillat);
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

    public Stage getStageLagerTabs() {
        return stageLagerTabs;
    }

    public Stage getStageVisDestillat(){
        return stageVisDestillat;
    }

    public LagerTabsController getLagerTabsController() {
        return lagerTabsController;
    }

    public VisDestillatController getVisDestillatController() {
        return visDestillatController;
    }
}
