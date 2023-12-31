package controller;
import gui.Gui;
import model.*;
import storage.IStorage;
import storage.Storage;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        Destillat destillat3 = createDestillat(3, "Byg", 1, 1, "Indisk Malt support", LocalDate.of(2023, 12, 1), "Kommentar");
        Lager lager1 = createLager("Sall Skur 5, st. th", 3, 6);
        Lager lager2 = createLager("Sall Dungeon 5", 20, 12);
        Fad fad1 = createFad(FadType.BOURBON, "Sall Whipsky", 200, 0, "Kommentar1", lager1, 0, 0);
        Fad fad2 = createFad(FadType.RØDVIN, "Sall Pisky", 200, 0, "Kommentar2", lager2, 0, 1);
        Fad fad3 = createFad(FadType.RØDVIN, "Sall Pisky", 200, 0, "Kommentar2", lager2, 0, 2);
        Map<Destillat, Float> map = new HashMap<>();
        map.put(destillat1, 100f);
        Map<Destillat, Float> map2 = new HashMap<>();
        map2.put(destillat2, 100f);
        Map<Destillat, Float> map3 = new HashMap<>();
        map3.put(destillat3, 100f);
        createFyld(fad1, LocalDate.of(2021, 1, 1), "Sall", map);
        createFyld(fad2, LocalDate.of(2021, 1, 1), "Sall", map2);
        createFyld(fad3, LocalDate.of(2021, 1, 1), "YEEEEEEET", map3);
        createWhisky(LocalDate.of(2021, 1, 1), Kvalitet.SINGLECASK, fad1.getFadindhold(), 100);
        createWhisky(LocalDate.of(2021, 1, 1), Kvalitet.SINGLEMALT, fad1.getFadindhold(), 750);
        createWhisky(LocalDate.of(2021, 1, 1), Kvalitet.BLENDED, fad2.getFadindhold(), 50);
        Gui.getInstance().notifyObservers();
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
            throw new IllegalArgumentException("Antal og kapacitet må ikke være mindre end 0");
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

        Fyld fyld = new Fyld(startDato, medarbejdere, fad);

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
     * @param fadIndhold fyldet, som whiskyen er lavet af
     * @param mændge mængden af whiskyen
     * @return det nye whisky objekt
     */
    public static Whisky createWhisky(LocalDate whiskyDato, Kvalitet kvalitet, FadIndhold fadIndhold, float mændge) {
        Whisky whisky = new Whisky(whiskyDato, kvalitet, fadIndhold, mændge);
        fadIndhold.addWhisky(whisky);
        Gui.getInstance().notifyObservers();
        return whisky;
    }

    /**
     * Laver et nyt fad objekt.
     * <p>
     *     Denne metode kaster en IllegalArgumentException, hvis nogen af parametrene er mindre end 0. <p>
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
        lager.addFad(fad, reol, plads);
        Gui.getInstance().notifyObservers();
        return fad;
    }

    /**
     * Laver et nyt fad objekt.
     * @param fad1 det første fad
     * @param fad2 det andet fad
     */
    public static void mergeFad(Fad fad1, Fad fad2) {
        fad1.mergeFad(fad2);
        Gui.getInstance().notifyObservers();
    }

    /**
     * Får fat i alle Destillat objekterne fra et bestemt lager
     * @return Set med alle destillat objekterne fra et bestemt lager
     */
    public static Set<Destillat> getDestillater(Lager lager) {
        Set<Destillat> destillater = new HashSet<>();
        for (Fad[] reol : lager.getReoler()) {
            for (Fad fad : reol) {
                if (fad != null) {
                    for (Fyld fyld : fad.getFyld()) {
                        destillater.addAll(fyld.getDestillater().keySet());
                    }
                }
            }
        }
        return destillater;
    }

    /**
     * Gemmer storage fra en fil.
     * @param fileName filnavnet, som storage skal gemmes i
     */
    public static void saveStorage(String fileName) {
        try {
            FileOutputStream fileOutDestillat = new FileOutputStream(fileName);
            ObjectOutputStream outDestillat = new ObjectOutputStream(fileOutDestillat);
            outDestillat.writeObject(storage);
            outDestillat.close();
            fileOutDestillat.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loader storage fra en fil.
     * @param fileName filnavnet, som storage skal loades fra
     */
    public static void loadStorage(String fileName) {
        try {
            FileInputStream fileInDestillat = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileInDestillat);
            storage = (Storage) in.readObject();
            in.close();
            fileInDestillat.close();
            if (storage == null) {
                storage = new Storage();
                initStorage();
                Logger.getGlobal().log(Level.SEVERE, "Storage kunne ikke indlæses, indlæser default storage");
            }

        } catch (IOException | ClassNotFoundException e) {
            if (e instanceof FileNotFoundException) {
                storage = new Storage();
                initStorage();
            }
            else
                throw new RuntimeException(e);
        } finally {
            Gui.getInstance().notifyObservers();
        }
    }

    /**
     * Laver et nyt fad objekt.
     * @param whiskyDato datoen for whiskyen
     * @param kvalitet kvaliteten af whiskyen
     * @param fadIndhold fyldet, som whiskyen er lavet af
     * @param mændge mængden af whiskyen
     * @param antal antallet af whiskyer, der skal laves
     * @return de nye whisky objekter
     */
    public static ArrayList<Whisky> createFlereWhisky(LocalDate whiskyDato, Kvalitet kvalitet, FadIndhold fadIndhold, float mændge, int antal) {

        ArrayList<Whisky> whiskys = new ArrayList<>();

        for (int i = 0; i < antal; i++) {
            Whisky whisky = new Whisky(whiskyDato, kvalitet, fadIndhold, mændge);
            fadIndhold.addWhisky(whisky);
        }

        Gui.getInstance().notifyObservers();
        return whiskys;
    }


    public static void loadStorageTest() {
        loadStorage("testStorage.ser");
    }

    public static void saveStorageTest() {
        saveStorage("testStorage.ser");
    }

    public static void loadStorageProd() {
        loadStorage("prodStorage.ser");
    }

    public static void saveStorageProd() {
        saveStorage("prodStorage.ser");
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

    public static IStorage getStorage() {
        return storage;
    }

}
