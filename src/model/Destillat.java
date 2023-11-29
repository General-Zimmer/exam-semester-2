package model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Destillat {
    private final UUID ID;
    private final int maltBatch;
    private final String kornsort;
    private float mængde;
    float alkoholProcent;
    private final String destillering;
    LocalDate destillationsDato;
    private String kommentar;

    public Destillat(UUID ID, int maltBatch, String kornsort, float mængde, String destillering, float alkoholProcent, LocalDate destillationsDato) {
        this.ID = ID;
        this.maltBatch = maltBatch;
        this.kornsort = kornsort;
        this.mængde = mængde;
        this.alkoholProcent = alkoholProcent;
        this.destillering = destillering;
        this.destillationsDato = destillationsDato;
    }

    public Destillat(UUID ID, int maltBatch, String kornsort, float mængde, String destillering, float alkoholProcent, LocalDate destillationsDato, String kommentar) {
        this(ID, maltBatch, kornsort, mængde, destillering, alkoholProcent, destillationsDato);
        this.kommentar = kommentar;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Destillat destillat)) return false;
        return getMaltBatch() == destillat.getMaltBatch() && Float.compare(getMængde(), destillat.getMængde()) == 0 && Objects.equals(getID(), destillat.getID()) && Objects.equals(getKornsort(), destillat.getKornsort()) && Objects.equals(getKommentar(), destillat.getKommentar()) && Objects.equals(getDestillering(), destillat.getDestillering());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getMaltBatch(), getKornsort(), getMængde(), getKommentar(), getDestillering());
    }
}
