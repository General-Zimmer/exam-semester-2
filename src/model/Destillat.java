package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Destillat implements Serializable {
    private final UUID ID;
    private final int maltBatch;
    private final String kornsort;
    private float mængde;
    final float alkoholProcent;
    private final String destillering;
    final LocalDate destillationsDato;
    private String kommentar;

    /**
     * Constructor for Destillat uden kommentar
     * @param ID ID for destillatet
     * @param maltBatch maltBatch for destillatet
     * @param kornsort kornsort for destillatet
     * @param mængde mængde for destillatet
     * @param destillering destillering for destillatet
     * @param alkoholProcent alkoholprocent for destillatet
     * @param destillationsDato destillationsdato for destillatet
     */
    public Destillat(UUID ID, int maltBatch, String kornsort, String destillering, float mængde, float alkoholProcent, LocalDate destillationsDato) {
        this.ID = ID;
        this.maltBatch = maltBatch;
        this.kornsort = kornsort;
        this.mængde = mængde;
        this.alkoholProcent = alkoholProcent;
        this.destillering = destillering;
        this.destillationsDato = destillationsDato;
    }

    /**
     * Constructor for Destillat med kommentar
     * @param ID ID for destillatet
     * @param maltBatch maltBatch for destillatet
     * @param kornsort kornsort for destillatet
     * @param mængde mængde for destillatet
     * @param destillering destillering for destillatet
     * @param alkoholProcent alkoholprocent for destillatet
     * @param destillationsDato destillationsdato for destillatet
     * @param kommentar kommentar for destillatet
     */
    public Destillat(UUID ID, int maltBatch, String kornsort, float mængde, String destillering, float alkoholProcent, LocalDate destillationsDato, String kommentar) {
        this(ID, maltBatch, kornsort, destillering, mængde, alkoholProcent, destillationsDato);
        this.kommentar = kommentar;
    }

    /**
     * Getter for ID
     * @return ID som UUID
     */
    public UUID getID() {
        return ID;
    }

    /**
     * Getter for maltBatch
     * @return maltBatch som int
     */
    public int getMaltBatch() {
        return maltBatch;
    }

    /**
     * Getter for kornsort
     * @return kornsort som String
     */
    public String getKornsort() {
        return kornsort;
    }

    /**
     * Getter for mængde
     * @return mængde som float
     */
    public float getMængde() {
        return mængde;
    }

    /**
     * Setter for mængde
     * @param mængde mængde for destillatet
     */
    public void setMængde(float mængde) {
        this.mængde = mængde;
    }

    /**
     * Getter for alkoholProcent
     * @return alkoholProcent som float
     */
    public String getKommentar() {
        return kommentar;
    }

    /**
     * Setter for kommentar
     * @param kommentar kommentar for destillatet
     */
    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }

    /**
     * Getter for alkoholProcent
     * @return alkoholProcent som float
     */
    public String getDestillering() {
        return destillering;
    }

    public float getAlkoholProcent() {
        return alkoholProcent;
    }

    public LocalDate getDestillationsDato() {
        return destillationsDato;
    }


    /**
     * Bliver brugt i whisky-klassen til at oprette den samlede historie af det færdige produkt.
     * @return
     */
    public Map<String, Object> getKompleteHistorie() {
        return Map.of(
                "ID", ID,
                "maltBatch", maltBatch,
                "kornsort", kornsort,
                "mængde", mængde,
                "alkoholProcent", alkoholProcent,
                "destillering", destillering,
                "destillationsDato", destillationsDato,
                "kommentar", kommentar
        );
    }

    @Override
    public String toString() {
        return "Maltbatch: " + maltBatch + ", kornsort: " + kornsort;
    }

    /**
     * Getter for alkoholProcent
     * @param o Object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Destillat destillat)) return false;
        return getMaltBatch() == destillat.getMaltBatch() && Float.compare(getMængde(), destillat.getMængde()) == 0 && Float.compare(alkoholProcent, destillat.alkoholProcent) == 0 && Objects.equals(getID(), destillat.getID()) && Objects.equals(getKornsort(), destillat.getKornsort()) && Objects.equals(getDestillering(), destillat.getDestillering()) && Objects.equals(destillationsDato, destillat.destillationsDato) && Objects.equals(getKommentar(), destillat.getKommentar());
    }

    /**
     * Hashcode metode for Destillat
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(getID(), getMaltBatch(), getKornsort(), getMængde(), getKommentar(), getDestillering());
    }
}
