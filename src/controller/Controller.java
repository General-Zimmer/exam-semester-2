package controller;
import gui.Gui;
import javafx.scene.control.DatePicker;
import model.Lager;
import model.Destillat;
import storage.IStorage;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public abstract class Controller {
    private static IStorage storage;

    public static void setStorage(IStorage storage) {
        Controller.storage = storage;
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
        Destillat destillat = new Destillat(ID, maltBatch, kornsort, mængde, destillering, alkoholProcent, destillationsDato);
        destillat.setKommentar(kommentar);
        storage.addDestillat(destillat);
        Gui.getInstance().notifyObservers();
        return destillat;
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


}
