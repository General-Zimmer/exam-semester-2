package Model;

import java.util.UUID;

public class Destillat {
    private UUID ID;
    private int maltBatch;
    private String kornsort;
    private float mængde;
    private String kommentar;
    private String destillering;

    public Destillat(UUID ID, int maltBatch, String kornsort, float mængde, String kommentar, String destillering) {
        this.ID = ID;
        this.maltBatch = maltBatch;
        this.kornsort = kornsort;
        this.mængde = mængde;
        this.kommentar = kommentar;
        this.destillering = destillering;
    }
}
