package Model;

import java.util.ArrayList;
import java.util.UUID;

public class Fad {
    private ArrayList<Fyld> fyld;
    private UUID ID; // Et unikt ID, vi kan generere for at adskille hvert objekt
    private String type;
    private String leverandør;
    private int fillAntal; // Hvor mange gange fadet er blevet fyldt med væske
    private float størrelse; // Størrelsen på fadet
    private String fadHistorik;

    public Fad(ArrayList<Fyld> fyld, UUID ID, String type, String leverandør, int fillAntal, float størrelse, String fadHistorik) {
        this.fyld = new ArrayList<>();
        this.ID = ID;
        this.type = type;
        this.leverandør = leverandør;
        this.fillAntal = fillAntal;
        this.størrelse = størrelse;
        this.fadHistorik = fadHistorik;
    }
}
