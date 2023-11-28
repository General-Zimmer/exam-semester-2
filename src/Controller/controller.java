package Controller;
import Model.Fyld;
import Model.Lager;
import Storage.Storage;
import Model.Fad;
import Model.Destillat;

import java.util.ArrayList;
import java.util.UUID;

public class controller {
    private static Storage storage;
    public static Lager createLager(String addresse, UUID ID, int antal, int kapacitet) {
        Lager l = new Lager(addresse, ID, antal, kapacitet);
        storage.addLager(l);
        return l;
    }

    public static Destillat createDestillat(UUID ID, int maltBatch, String kornsort, float mængde, String kommentar, String destillering) {
        Destillat d = new Destillat(ID, maltBatch, kornsort, mængde, kommentar, destillering);
        storage.addDestillat(d);
        return d;
    }


}
