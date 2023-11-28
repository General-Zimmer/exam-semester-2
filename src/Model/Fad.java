package Model;

import java.util.ArrayList;
import java.util.UUID;

public class Fad {
    private final ArrayList<Fyld> fyld;
    private final UUID ID; // Et unikt ID, vi kan generere for at adskille hvert objekt
    private final String type;
    private final String leverandør;
    private int fillAntal; // Hvor mange gange fadet er blevet fyldt med væske
    private final float størrelse; // Størrelsen på fadet
    private String fadHistorik;

    public Fad(UUID ID, String type, String leverandør, int fillAntal, float størrelse) {
        this.fyld = new ArrayList<>();
        this.ID = ID;
        this.type = type;
        this.leverandør = leverandør;
        this.fillAntal = fillAntal;
        this.størrelse = størrelse;
    }


    public ArrayList<Fyld> getFyld() {
        return fyld;
    }

    public UUID getID() {
        return ID;
    }

    public String getType() {
        return type;
    }

    public String getLeverandør() {
        return leverandør;
    }

    public int getFillAntal() {
        return fillAntal;
    }

    public void setFillAntal(int fillAntal) {
        this.fillAntal = fillAntal;
    }

    public float getStørrelse() {
        return størrelse;
    }

    public String getFadHistorik() {
        return fadHistorik;
    }

    public void setFadHistorik(String fadHistorik) {
        this.fadHistorik = fadHistorik;
    }
}
