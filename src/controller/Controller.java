package controller;
import gui.Gui;
import javafx.scene.control.DatePicker;
import model.Lager;
import model.Destillat;
import storage.IStorage;

import java.util.Set;
import java.util.UUID;

public abstract class Controller {
    private static IStorage storage;

    public static void setStorage(IStorage storage) {
        Controller.storage = storage;
    }


    public static Lager createLager(String addresse, int antal, int kapacitet) {
        UUID ID = UUID.randomUUID();
        Lager l = new Lager(addresse, ID, antal, kapacitet);
        storage.addLager(l);
        return l;
    }

    public static Destillat createDestillat(int maltBatch, String kornsort, float mængde, float alkoholProcent,
                                            String destillering,DatePicker destillationsDato, String kommentar) {
        UUID ID = UUID.randomUUID();
        Destillat d = new Destillat(ID, maltBatch, kornsort, mængde, destillering, alkoholProcent, destillationsDato.getValue());
        d.setKommentar(kommentar);
        storage.addDestillat(d);
        Gui.getInstance().notifyObservers();
        return d;
    }

    public static Set<Lager> getLager() {
        return storage.getLagre();
    }

    public static Set<Destillat> getDestillater() {
        return storage.getDestillater();
    }

    public static void removeDestillat(Destillat d) {
        storage.removeDestillat(d);
    }

    public static void removeLager(Lager l) {
        storage.removeLager(l);
    }

    public static Lager getLager(UUID ID) {
        return storage.getLager(ID);
    }

    public static Destillat getDestillat(UUID ID) {
        return storage.getDestillat(ID);
    }


}
