package model;

import java.util.ArrayList;
import java.util.UUID;

public class Fad {
    private Fyld fyld;
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
    }

    /**
     * Getter for fyld
     * @return ArrayList<Fyld>
     */
    public Fyld getFyld() {
        return fyld;
    }

    /**
     * Setter for fyld
     * @param fyld Fyldet i fadet
     */
    public void setFyld(Fyld fyld) {
        this.fyld = fyld;
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
}
