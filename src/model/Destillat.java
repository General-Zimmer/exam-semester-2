package model;

import java.util.UUID;

public class Destillat {
    private final UUID ID;
    private final int maltBatch;
    private final String kornsort;
    private float mængde;
    private String kommentar;
    private final String destillering;

    public Destillat(UUID ID, int maltBatch, String kornsort, float mængde, String kommentar, String destillering) {
        this.ID = ID;
        this.maltBatch = maltBatch;
        this.kornsort = kornsort;
        this.mængde = mængde;
        this.kommentar = kommentar;
        this.destillering = destillering;
    }

    public UUID getID() {
        return ID;
    }

    public int getMaltBatch() {
        return maltBatch;
    }

    public String getKornsort() {
        return kornsort;
    }

    public float getMængde() {
        return mængde;
    }

    public void setMængde(float mængde) {
        this.mængde = mængde;
    }

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }

    public String getDestillering() {
        return destillering;
    }
}
