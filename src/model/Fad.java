package model;

import java.io.Serializable;
import java.util.*;

public class Fad implements Serializable, Cloneable {
    private Blanding blanding;
    private final UUID ID; // Et unikt ID, vi kan generere for at adskille hvert objekt
    private final FadType type;
    private final String leverandør;
    private int fillAntal; // Hvor mange gange fadet er blevet fyldt med væske
    private final float størrelse; // Størrelsen på fadet
    private String fadHistorik;

    /**
     * Constructor for Fad
     * @param ID Et unikt ID, vi kan generere for at adskille hvert objekt
     * @param type Type af fad
     * @param leverandør Leverandør af fad
     * @param fillAntal Hvor mange gange fadet er blevet fyldt med væske
     * @param størrelse Størrelsen på fadet
     */
    public Fad(UUID ID, FadType type, String leverandør, int fillAntal, float størrelse) {
        this.ID = ID;
        this.type = type;
        this.leverandør = leverandør;
        this.fillAntal = fillAntal;
        this.størrelse = størrelse;
        this.fadHistorik = "";
        this.blanding = new Blanding();
    }

    /**
     * Getter for fyld
     * @return ArrayList<Fyld>
     */
    public Fyld getFyld(int index) {
        return blanding.getFyld(index);
    }

    /**
     * Get the list of fyld
     * @return ArrayList<Fyld>
     */
    public List<Fyld> getFyld() {
        return new ArrayList<>(blanding.getFyld());
    }

    /**
     * Add a Fyld to the list of fyld
     * @param fyld Fyld to add
     */
    public void addFyld(Fyld fyld) {
        if (blanding.beregnMængdeBrugt() > beregnMængdeTilgængelig())
            throw new IllegalArgumentException("Fyldet er for stort til fadet");

        this.blanding.addFyld(fyld);
    }

    /**
     * beregnMængdeBrugt
     * @return Mængden brugt som double
     */
    public double beregnMængdeBrugt() {
        return blanding.beregnMængdeBrugt();
    }

    /**
     * beregnMængdeTilgængelig
     * @return Mængden tilgængelig som double
     */
    public double beregnMængdeTilgængelig() {
        return størrelse - beregnMængdeBrugt();
    }

    /**
     * Getter for blanding
     * @return Blanding
     */
    public Blanding getBlanding() {
        return blanding;
    }

    /**
     * Setter for blanding
     * <p>
     *     Kaster en IllegalArgumentException hvis blanding er null
     * @param blanding Blanding
     */
    public void setBlanding(Blanding blanding) {
        if (blanding == null)
            throw new IllegalArgumentException("Blanding må ikke være null");
        this.blanding = blanding;
    }

    /**
     * Clear the blanding
     */
    public void clearBlanding() {
        this.blanding = new Blanding();
    }

    /**
     * Getter for ID
     * @return UUID
     */
    public UUID getID() {
        return ID;
    }

    /**
     * Getter for type
     * @return String
     */
    public FadType getType() {
        return type;
    }

    /**
     * Getter for leverandør
     * @return String
     */
    public String getLeverandør() {
        return leverandør;
    }

    /**
     * Getter for fillAntal
     * @return int
     */
    public int getFillAntal() {
        return fillAntal;
    }

    /**
     * Setter for fillAntal
     * @param fillAntal Hvor mange gange fadet er blevet fyldt med væske
     */
    public void setFillAntal(int fillAntal) {
        this.fillAntal = fillAntal;
    }

    /**
     * Getter for størrelse
     * @return float
     */
    public float getStørrelse() {
        return størrelse;
    }

    /**
     * Getter for fadHistorik
     * @return String
     */
    public String getFadHistorik() {
        return fadHistorik;
    }

    /**
     * Setter for fadHistorik
     * @param fadHistorik Historik for fadet
     */
    public void setFadHistorik(String fadHistorik) {
        this.fadHistorik = fadHistorik;
    }

    @Override
    public Fad clone() throws CloneNotSupportedException {
        return (Fad) super.clone();
    }

    @Override
    public String toString() {
        return "Fad{" +
                "type=" + type + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fad fad)) return false;
        return getFillAntal() == fad.getFillAntal() && Float.compare(getStørrelse(), fad.getStørrelse()) == 0 && Objects.equals(getID(), fad.getID()) && getType() == fad.getType() && Objects.equals(getLeverandør(), fad.getLeverandør()) && Objects.equals(getFadHistorik(), fad.getFadHistorik());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getType(), getLeverandør(), getFillAntal(), getStørrelse(), getFadHistorik());
    }
}
