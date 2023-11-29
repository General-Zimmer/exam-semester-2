package model;

import java.util.Objects;
import java.util.UUID;

public class Lager {
    private final String addresse;
    private final UUID ID; // Et unikt ID, vi kan generere for at adskille hvert objekt
    private final Fad[][] reoler; // 2-dimensionelt reol-system. Med flere 2D reoler, har vi et 3D lager

    public Lager(String addresse, UUID ID, int reoler, int reolKapacitet) {
        this.addresse = addresse;
        this.ID = ID;
        this.reoler = new Fad[reoler][reolKapacitet];
    }

    public UUID getID() {
        return ID;
    }

    public Fad[][] getReoler() {
        return reoler.clone();
    }

    public String getAddresse() {
        return addresse;
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
}
