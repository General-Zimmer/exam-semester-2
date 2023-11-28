package Model;

import java.util.UUID;

public class Lager {
    private final UUID ID; // Et unikt ID, vi kan generere for at adskille hvert objekt
    private final Fad[][] reoler; // 2-dimensionelt reol-system. Med flere 2D reoler, har vi et 3D lager

    public Lager(UUID ID, int reoler, int reolKapacitet) {
        this.ID = ID;
        this.reoler = new Fad[reoler][reolKapacitet];
    }

    public UUID getID() {
        return ID;
    }

    public Fad[][] getReoler() {
        return reoler.clone();
    }
}
