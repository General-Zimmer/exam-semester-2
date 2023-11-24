package Model;

import java.util.UUID;

public class Lager {
    private UUID ID;
    private Fad[][] reoler;

    public Lager(UUID ID, Fad[][] reoler) {
        this.ID = ID;
        this.reoler = reoler;
    }
}
