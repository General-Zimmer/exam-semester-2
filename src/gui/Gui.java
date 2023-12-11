package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import observers.IStorageObserver;
import thread.AutoSave;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

public class Gui extends Application {

    private static Gui instance;
    private final HashSet<IStorageObserver> observers = new HashSet<>();
    private Thread autoSave;


    public Gui() {
        if (instance != null) {
            throw new IllegalStateException("Cannot create more than one instance Gui");
        }
        instance = this;
    }

    private Stage stageHovedMenu;
    private Stage stageLager;
    private Stage stageDestillat;
    private Stage stageLagerTabs;
    private Stage stageVisDestillat;
    private Stage stageOpretFad;
    private Stage stageOpretFyld;
    private Stage stageVisFad;
    private Stage stageVisFadIndhold;
    private Stage stageOpretWhisky;
    private Stage stageVisWhisky;
    private LagerTabsController lagerTabsController;
    private VisDestillatController visDestillatController;
    private OpretFadController opretFadController;
    private OpretFyldController opretFyldController;
    private VisFadController visFadController;
    private VisFadIndholdController visFadIndholdController;
    private OpretWhiskyController opretWhiskyController;
    private VisWhiskyController visWhiskyController;


    /**
     * Starter GUI'en
     * @param stage Stage
     * @throws Exception Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        final int STAGE = 0;
        final int CONTROLLER = 1;


        // Lager stage
        Object[] opretLager = createStage("OpretLager.fxml", "Opret lager");
        stageLager = (Stage) opretLager[STAGE];

        // Opret destillat stage
        Object[] opretDestillat = createStage("OpretDestillat.fxml", "Opret destillat");
        stageDestillat = (Stage) opretDestillat[STAGE];

        // Lagertabs stage
        Object[] lagertabs = createStage("LagerTabs.fxml", "Lager");
        stageLagerTabs = (Stage) lagertabs[STAGE];
        lagerTabsController = (LagerTabsController) lagertabs[CONTROLLER];

        // Vis destillat stage
        Object[] visDestillat = createStage("VisDestillat.fxml", "Vis destillat");
        stageVisDestillat = (Stage) visDestillat[STAGE];
        visDestillatController = (VisDestillatController) visDestillat[CONTROLLER];

        // Opret fad stage
        Object[] opretFad = createStage("OpretFad.fxml", "Opret fad");
        stageOpretFad = (Stage) opretFad[STAGE];
        opretFadController = (OpretFadController) opretFad[CONTROLLER];

        // Hovedmenu stage
        Object[] hovedMenu = createStage("HovedMenu.fxml", "Hovedmenu", stage);
        stageHovedMenu = (Stage) hovedMenu[STAGE];
        stageHovedMenu.show();

        // Opret fyld stage
        Object[] opretFyld = createStage("OpretFyld.fxml", "Opret fyld");
        stageOpretFyld = (Stage) opretFyld[STAGE];
        opretFyldController = (OpretFyldController) opretFyld[CONTROLLER];

        // Vis fyld stage
        Object[] visFadIndhold = createStage("VisFadIndhold.fxml", "Vis fadindhold");
        stageVisFadIndhold = (Stage) visFadIndhold[STAGE];
        visFadIndholdController = (VisFadIndholdController) visFadIndhold[CONTROLLER];

        // Vis fad stage
        Object[] visFad = createStage("VisFad.fxml", "Vis fad");
        stageVisFad = (Stage) visFad[STAGE];
        visFadController = (VisFadController) visFad[CONTROLLER];

        // Opret whisky stage
        Object[] opretWhisky = createStage("OpretWhisky.fxml", "Opret whisky");
        stageOpretWhisky = (Stage) opretWhisky[STAGE];
        opretWhiskyController = (OpretWhiskyController) opretWhisky[CONTROLLER];

        // Vis whisky stage
        Object[] visWhisky = createStage("VisWhisky.fxml", "Vis whisky");
        stageVisWhisky = (Stage) visWhisky[STAGE];
        visWhiskyController = (VisWhiskyController) visWhisky[CONTROLLER];

        // Auto-gemmer zimmerstuff
        autoSave = new AutoSave(10);
        autoSave.start();

        Controller.loadStorageProd();
    }

    private Object[] createStage(String resource, String title) throws Exception {
        Stage stage = new Stage();
        return createStage(resource, title, stage);
    }

    private <T extends IStorageObserver> Object[] createStage(String resource, String title, Stage stage) throws Exception {
        Object[] returnArray = new Object[2];

        returnArray[0] = stage;
        URL fxmlFileNameVisFadIndhold = this.getClass().getResource(resource);
        if (fxmlFileNameVisFadIndhold == null) throw new NoSuchElementException("FXML file not found");
        FXMLLoader VisFadIndholdLoader = new FXMLLoader(fxmlFileNameVisFadIndhold);
        Parent parent = VisFadIndholdLoader.load();
        T controller = VisFadIndholdLoader.getController();
        returnArray[1] = controller;
        registerObserver(controller);
        stage.setTitle(title);
        stage.setMinWidth(parent.minWidth(-1));
        stage.setMinHeight(parent.minHeight(-1));
        Scene sceneFadIndhold = new Scene(parent);
        stage.setScene(sceneFadIndhold);
        return returnArray;
    }


    /**
     * Tilføjer en observer
     * @param observer observer
     */
    public void registerObserver(IStorageObserver observer) {
        observers.add(observer);
    }

    /**
     * Fjerner en observer fra listen med observers.
     * @param observer observer
     */
    public void unregisterObserver(IStorageObserver observer) {
        observers.remove(observer);
    }

    /**
     * Videregiver opdateringerne til observerne så de kan indlæse det.
     */
    public void notifyObservers() {
        for (IStorageObserver observer : observers) {
            observer.update();
        }
    }

    public static Gui getInstance() {
        return instance;
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

    public Stage getStageOpretFad(){
        return stageOpretFad;
    }

    public Stage getStageOpretFyld(){
        return stageOpretFyld;
    }
    public Stage getStageVisFad(){
        return stageVisFad;
    }
    public Stage getStageVisFyld(){
        return stageVisFadIndhold;
    }
    public Stage getStageOpretWhisky(){
        return stageOpretWhisky;
    }
    public Stage getStageVisWhisky(){
        return stageVisWhisky;
    }

    public LagerTabsController getLagerTabsController() {
        return lagerTabsController;
    }

    public VisDestillatController getVisDestillatController() {
        return visDestillatController;
    }

    public OpretFadController getOpretFadController() {
        return opretFadController;
    }
    public VisFadController getVisFadController(){
        return visFadController;
    }
    public OpretFyldController getOpretFyldController(){
        return opretFyldController;
    }
    public VisFadIndholdController getVisFadIndholdController(){
        return visFadIndholdController;
    }
    public OpretWhiskyController getOpretWhiskyController(){
        return opretWhiskyController;
    }
    public VisWhiskyController getVisWhiskyController(){
        return visWhiskyController;
    }

    /**
     * Sørger for at stoppe vores Auto Save funktion når gui'en bliver lukket.
     * @throws Exception Exception
     */
    @Override
    public void stop() throws Exception {
        super.stop();
        autoSave.interrupt();
        Controller.saveStorageProd();
    }
}
