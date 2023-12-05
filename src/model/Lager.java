package model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Lager implements Serializable {
    private final String addresse;
    private final UUID ID; // Et unikt ID, vi kan generere for at adskille hvert objekt
    private Fad[][] reoler; // 2-dimensionelt reol-system. Med flere 2D reoler, har vi et 3D lager

    /**
     * Constructor for Lager
     * @param addresse Addressen på lageret
     * @param ID Et unikt ID, vi kan generere for at adskille hvert objekt
     * @param reoler Antallet af reoler
     * @param reolKapacitet Antallet af fad der kan være i en reol
     */
    public Lager(String addresse, UUID ID, int reoler, int reolKapacitet) {
        this.addresse = addresse;
        this.ID = ID;
        this.reoler = new Fad[reoler][reolKapacitet];
    }

    /**
     * Returnerer antallet af tomme pladser i lageret
     * @return
     */
    public int getAntalTommePladser() {
        int count = 0;

        for (int i = 0; i < reoler.length; i++) {
            for (int j = 0; j < reoler[i].length; j++) {

                if (reoler[i][j] == null) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Vil lave størrelsen om på lageret
     * @param reoler Antallet af reoler
     * @param reolKapacitet Antallet af fad der kan være i en reol
     */
    public void redigerReoler(int reoler, int reolKapacitet) {

        if (reoler <= 0 || reolKapacitet <= 0) {
            throw new IllegalArgumentException("Reoler og Kapacitet skal være over 0");
        }
        Fad[][] fadLagerTing = new Fad[reoler][reolKapacitet];
        int innerArraySize = Math.min(this.reoler[0].length, reolKapacitet)-1;
        int outerArraySize = Math.min(this.reoler.length, reoler);


        for (int i = 0; i < outerArraySize; i++) {
            System.arraycopy(this.reoler[i], 0, fadLagerTing[i], 0, innerArraySize);
        }

            this.reoler = fadLagerTing;
        }


    /**
     * Getter for ID
     * @return UUID
     */
    public UUID getID() {
        return ID;
    }

    /**
     * Getter for reoler
     * @return Fad[][]
     */
    public Fad[][] getReoler() {
        return reoler.clone();
    }

    /**
     * Tilføjer et fad til lageret
     * <p>
     *     Denne metode kaster en IllegalArgumentException, hvis pladsen ikke er tom <p>
     *     pre: fad er ikke null
     * @param fad Fadet der skal tilføjes
     * @param reol Reolen fadet skal tilføjes til
     * @param placering Placeringen fadet skal tilføjes til
     */
    public void addfad(Fad fad, int reol, int placering) {
        if (this.reoler[reol][placering] != null) throw new IllegalArgumentException("Pladsen er ikke tom");

        this.reoler[reol][placering] = fad;
    }

    /**
     * Fjerner et fad fra lageret
     * @param reol Reolen fadet skal fjernes fra
     * @param placering Placeringen fadet skal fjernes fra
     */
    public void removeFad(int reol, int placering) {
        this.reoler[reol][placering] = null;
    }

    /**
     * Finder et fad på lageret
     * @param reol Reolen fadet skal findes på
     * @param placering Placeringen fadet skal findes på
     * @return Fad
     */
    public Fad getFad(int reol, int placering) {
        return this.reoler[reol][placering];
    }

    /**
     * Flytter et fad fra en placering til en anden
     * @param lager Lageret fadet skal flyttes fra
     * @param nyReol Den nye reol fadet skal flyttes til
     * @param nyPlacering Den nye placering fadet skal flyttes til
     * @param gammelReol Den gamle reol fadet skal flyttes fra
     * @param gammelPlacering Den gamle placering fadet skal flyttes fra
     */
    public void moveFad(Lager lager, int nyReol, int nyPlacering, int gammelReol, int gammelPlacering) {
        if (lager.getFad(nyReol, nyPlacering) != null) throw new IllegalArgumentException("Pladsen er ikke tom");
        lager.addfad(this.getFad(gammelReol, gammelPlacering), nyReol, nyPlacering);
        this.removeFad(gammelReol, gammelPlacering);
    }


    /**
     * Getter for addresse
     * @return String
     */
    public String getAddresse() {
        return addresse;
    }

    @Override
    public String toString() {
        return "Lager{" +
                "addresse = '" + addresse + '\'' +
                ", antal tomme pladser: " + getAntalTommePladser() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lager lager)) return false;
        return Objects.equals(getAddresse(), lager.getAddresse()) && Objects.equals(getID(), lager.getID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddresse(), getID());
    }


}
