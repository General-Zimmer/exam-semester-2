package model;

import java.util.Objects;
import java.util.UUID;

public class Lager {
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
        Fad[][] fadLagerTing = new Fad[reoler][reolKapacitet];
        int biggestSize = Math.max(this.reoler[0].length, fadLagerTing[0].length);
        for (int i = 0; i < this.reoler.length; i++) {
            System.arraycopy(this.reoler[i], 0, fadLagerTing[i], 0, biggestSize);
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
     * Getter for addresse
     * @return String
     */
    public String getAddresse() {
        return addresse;
    }

    @Override
    public String toString() {
        return "addresse: " + addresse;
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
