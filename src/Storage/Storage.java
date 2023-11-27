package Storage;

import Model.Destillat;
import Model.Lager;

import java.io.Serializable;
import java.util.HashSet;

public class Storage implements IStorage, Serializable {



    private static final HashSet<Destillat> destillater = new HashSet<>();
    private static final HashSet<Lager> lagre = new HashSet<>();



    public static void gemLager(Lager lager) {
        lagre.add(lager);
    }

    public static void gemDestillat(Destillat destillat) {
        destillater.add(destillat);
    }






}
