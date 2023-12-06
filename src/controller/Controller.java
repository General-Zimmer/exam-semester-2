package controller;
import gui.Gui;
import model.*;
import storage.IStorage;
import storage.Storage;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

@SuppressWarnings("SpellCheckingInspection")
public abstract class Controller {
    private static IStorage storage;

    public static void setStorage(IStorage storage) {
        Controller.storage = storage;
    }

    /**
     * Initialiserer storage med nogle objekter
     */
    public static void initStorage() {
        Destillat destillat1 = createDestillat(1, "Byg", 1, 1, "Indisk Malt support", LocalDate.of(2023, 12, 1), "Kommentar");
        Destillat destillat2 = createDestillat(2, "LarsKorn", 1, 1, "Sall Whisky", LocalDate.of(2024, 3, 2), "Kommentar 2.0");
        createLager("Sall Skur", 3, 6);
        createLager("Sall Dungeon", 20, 12);
        Iterator<Lager> lagre = storage.getLagre().iterator();
        Fad fad1 = createFad(FadType.BOURBON, "Sall Whipsky", 200, 0, "Kommentar1", lagre.next(), 0, 0);
        Fad fad2 = createFad(FadType.BOURBON, "Sall Pisky", 200, 0, "Kommentar2", lagre.next(), 0, 1);
        Map<Destillat, Float> map = new HashMap<>();
        map.put(destillat1, 100f);
        Map<Destillat, Float> map2 = new HashMap<>();
        map2.put(destillat2, 100f);
        createFyld(fad1, LocalDate.of(2021, 1, 1), "Sall", map);
        createFyld(fad2, LocalDate.of(2021, 1, 1), "Sall", map2);
    }

    /**
     * Laver et nyt lager objekt og tilføjer det til storage.
     * <p>
     *     Denne metode kaster en IllegalArgumentException, hvis nogen af parametrene er mindre end 0.
     *     pre: parametrene er ikke null
     * @param addresse addresse på lageret
     * @param antal antal reoler
     * @param kapacitet kapaciteten af hver reol
     * @return det nye lager
     */
    public static Lager createLager(String addresse, int antal, int kapacitet) {
        if (antal < 0 || kapacitet < 0) {
            throw new IllegalArgumentException("antal og kapacitet må ikke være mindre end 0");
        }

        UUID ID = UUID.randomUUID();
        Lager lager = new Lager(addresse, ID, antal, kapacitet);
        storage.addLager(lager);
        Gui.getInstance().notifyObservers();
        return lager;
    }

    /**
     * Laver et nyt destillat objekt og tilføjer det til storage.
     * <p>
     *     Denne metode kaster en IllegalArgumentException, hvis nogen af parametrene er mindre end 0.
     *     pre: parametrene er ikke null
     * @param maltBatch ID på den maltbatch, som destillatet er lavet af
     * @param kornsort kornsorten, som destillatet er lavet af
     * @param mængde mængden af destillatet
     * @param alkoholProcent alkoholprocenten i destillatet
     * @param destillering destilleringsfaciliteten
     * @param destillationsDato datoen for destilleringen
     * @param kommentar en kommentar til destillatet
     * @return det nye destillat
     */
    public static Destillat createDestillat(int maltBatch, String kornsort, float mængde, float alkoholProcent,
                                            String destillering, LocalDate destillationsDato, String kommentar) {
        if (maltBatch < 0 || mængde < 0 || alkoholProcent < 0) {
            throw new IllegalArgumentException("Maltbatch, mængde, og alkoholprocent må ikke være mindre end 0");
        }

        UUID ID = UUID.randomUUID();
        Destillat destillat = new Destillat(ID, maltBatch, kornsort, destillering, mængde, alkoholProcent, destillationsDato);
        destillat.setKommentar(kommentar);
        storage.addDestillat(destillat);
        Gui.getInstance().notifyObservers();
        return destillat;
    }

    /**
     * Laver et nyt fyld objekt.
     * <p>
     *     Denne metode kaster en IllegalArgumentException, hvis nogen af parametrene er null.
     * @param startDato datoen for fyldet
     * @param medarbejdere medarbejderne, der har fyldt fadet
     * @return det nye fyld
     */
    public static Fyld createFyld(Fad fad, LocalDate startDato, String medarbejdere, Map<Destillat, Float> destillat) {
        if (fad == null || startDato == null || medarbejdere == null || destillat == null) {
            throw new IllegalArgumentException("startDato, medarbejdere og destillat må ikke være null");
        }
        Fyld fyld = new Fyld(startDato, medarbejdere);
        fad.addFyld(fyld); // Vigtig 1
        fyld.addFad(fad); // Vigtig 2
        for (Map.Entry<Destillat, Float> entry : destillat.entrySet()) {
            fyld.addDestillat(entry.getKey(), entry.getValue());
        } // Vigtig 3
        Gui.getInstance().notifyObservers();
        return fyld;
    }

    /**
     * Laver et nyt whisky objekt.
     * @param whiskyDato datoen for whiskyen
     * @param kvalitet kvaliteten af whiskyen
     * @param fyld fyldet, som whiskyen er lavet af
     * @param mændge mængden af whiskyen
     * @return det nye whisky objekt
     */
    public static Whisky createWhisky(LocalDate whiskyDato, Kvalitet kvalitet, Fyld fyld, float mændge) {
        Whisky whisky = new Whisky(whiskyDato, kvalitet, fyld, mændge);
        fyld.addWhisky(whisky);
        Gui.getInstance().notifyObservers();
        return whisky;
    }

    /**
     * Laver et nyt fad objekt.
     * <p>
     *     Denne metode kaster en IllegalArgumentException, hvis nogen af parametrene er mindre end 0.
     *     pre: parametrene er ikke null
     * @param fadType typen af fad
     * @param fadLevendøre leverandøren af fadet
     * @param fadStørrelse størrelsen af fadet
     * @param fillAntal antallet af fyldninger af fadet
     * @param kommentar en kommentar til fadet
     * @param lager lageret, som fadet skal tilføjes til
     * @param reol reolen, som fadet skal tilføjes til
     * @param plads pladsen, som fadet skal tilføjes til
     * @return det nye fad
     */
    public static Fad createFad(FadType fadType, String fadLevendøre, float fadStørrelse, int fillAntal,  String kommentar, Lager lager, int reol, int plads) {
        if (fadStørrelse < 0 || fillAntal < 0 || reol < 0 || plads < 0) {
            throw new IllegalArgumentException("Størrelse, fillAntal, reol og plads må ikke være mindre end 0");
        }

        UUID ID = UUID.randomUUID();
        Fad fad = new Fad(ID, fadType, fadLevendøre, fillAntal, fadStørrelse);
        fad.setFadHistorik(kommentar);
        lager.addfad(fad, reol, plads);
        Gui.getInstance().notifyObservers();
        return fad;
    }

    /**
     * Får fat i alle lager objekterne i storage
     * @return Set med alle lager objekterne
     */
    public static Set<Lager> getLager() {
        return storage.getLagre();
    }

    /**
     * Får fat i alle destillat objekterne i storage
     * @return Set med alle destillat objekterne
     */
    public static Set<Destillat> getDestillater() {
        return storage.getDestillater();
    }

    /**
     * Fjerner et lager objekt fra storage
     * @param d det lager objekt, der skal fjernes
     */
    public static void removeDestillat(Destillat d) {
        storage.removeDestillat(d);
    }

    /**
     * Fjerner et destillat objekt fra storage
     * @param lager det destillat objekt, der skal fjernes
     */
    public static void removeLager(Lager lager) {
        storage.removeLager(lager);
    }

    /**
     * Får fat i et lager objekt fra storage
     * @param ID ID'et på det lager objekt, der skal findes
     * @return lager objektet med det givne ID
     */
    public static Lager getLager(UUID ID) {
        return storage.getLager(ID);
    }

    /**
     * Får fat i et destillat objekt fra storage
     * @param ID ID'et på det destillat objekt, der skal findes
     * @return destillat objektet med det givne ID
     */
    public static Destillat getDestillat(UUID ID) {
        return storage.getDestillat(ID);
    }

    public static void saveStorage() {
        try {
            FileOutputStream fileOutDestillat = new FileOutputStream("Storage.ser");
            ObjectOutputStream outDestillat = new ObjectOutputStream(fileOutDestillat);
            outDestillat.writeObject(storage);
            outDestillat.close();
            fileOutDestillat.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadStorage() {
        try {
            FileInputStream fileInDestillat = new FileInputStream("Storage.ser");
            ObjectInputStream in = new ObjectInputStream(fileInDestillat);
            storage = (Storage) in.readObject();
            in.close();
            fileInDestillat.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static IStorage getStorage() {
        return storage;
    }

}
