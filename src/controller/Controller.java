package controller;
import model.Lager;
import model.Destillat;
import storage.IStorage;

import java.util.UUID;

public abstract class Controller {
    private static IStorage storage;

    public static void setStorage(IStorage storage) {
        Controller.storage = storage;
    }


    public static Lager createLager(String addresse, UUID ID, int antal, int kapacitet) {
        Lager l = new Lager(addresse, ID, antal, kapacitet);
        storage.addLager(l);
        return l;
    }

    public static Destillat createDestillat(UUID ID, int maltBatch, String kornsort, float mængde, String destillering) {
        Destillat d = new Destillat(ID, maltBatch, kornsort, mængde, destillering);
        storage.addDestillat(d);
        return d;
    }



}
