package Model;

import java.util.ArrayList;
import java.util.UUID;

public class Fad {
    private ArrayList<Fyld> fyld;
    private UUID ID;
    private String type;
    private String leverandør;
    private int fillAntal;
    private float størrelse;
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
