package Model;

import java.util.UUID;

public class Lager {
    private final UUID ID; // Et unikt ID, vi kan generere for at adskille hvert objekt
    private final Fad[][] reoler; // 2-dimensionelt reol-system. Med flere 2D reoler, har vi et 3D lager

    public Lager(UUID ID, Fad[][] reoler) {
        this.ID = ID;
        this.reoler = reoler;
    }

    public UUID getID() {
        return ID;
    }

    public Fad[][] getReoler() {
        return reoler.clone();
    }
}
