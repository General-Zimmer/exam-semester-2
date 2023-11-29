package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

/**
 * Fyld klasse repræsentere en opfyldning af et fad. Denne opfyldning kan indeholde flere destillater og kan skifte fad
 * over tid.
 */
public class Fyld {
    private final HashSet<Fad> fad;
    private final HashMap<Destillat, Integer> destillater;
    private final LocalDate startDato;
    private float alkoholProcent;
    private String medarbejdere; // Dem som har fyldt fadet

    /**
     * Constructor for Fyld
     * @param startDato startDato for opfyldning af fad
     * @param alkoholProcent alkoholprocent for fadet
     * @param medarbejdere medarbejdere som har fyldt fadet
     */
    public Fyld(LocalDate startDato, float alkoholProcent, String medarbejdere) {
        this.fad = new HashSet<>();
        this.destillater = new HashMap<>();
        this.startDato = startDato;
        this.alkoholProcent = alkoholProcent;
        this.medarbejdere = medarbejdere;
    }

    /**
     * Constructor for Fyld, hvor startDato er LocalDate.now()
     * @param alkoholProcent alkoholprocent for fadet
     * @param medarbejdere medarbejdere som har fyldt fadet
     */
    public Fyld (float alkoholProcent, String medarbejdere){
        this(LocalDate.now(), alkoholProcent, medarbejdere);
    }

    /**
     * Getter for fad
     * @return HashSet<Fad>
     */
    public HashSet<Fad> getFad() {
        return fad;
    }

    /**
     * Getter for destillater
     * @return HashMap<Destillat, Integer>
     */
    public HashMap<Destillat, Integer> getDestillater() {
        return destillater;
    }

    /**
     * Getter for startDato
     * @return LocalDate
     */
    public LocalDate getStartDato() {
        return startDato;
    }

    /**
     * Getter for alkoholProcent
     * @return float
     */
    public float getAlkoholProcent() {
        return alkoholProcent;
    }

    /**
     * Setter for alkoholProcent
     * @param alkoholProcent alkoholprocent for fadet
     */
    public void setAlkoholProcent(float alkoholProcent) {
        this.alkoholProcent = alkoholProcent;
    }

    /**
     * Getter for medarbejdere
     * @return String
     */
    public String getMedarbejdere() {
        return medarbejdere;
    }

    /**
     * Setter for medarbejdere
     * @param medarbejdere medarbejdere som har fyldt fadet
     */
    public void setMedarbejdere(String medarbejdere) {
        this.medarbejdere = medarbejdere;
    }

    /**
     * Equals metode for Fyld
     * @param o Object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fyld fyld)) return false;
        return Float.compare(getAlkoholProcent(), fyld.getAlkoholProcent()) == 0 && Objects.equals(getFad(), fyld.getFad()) && Objects.equals(getDestillater(), fyld.getDestillater()) && Objects.equals(getStartDato(), fyld.getStartDato()) && Objects.equals(getMedarbejdere(), fyld.getMedarbejdere());
    }

    /**
     * HashCode metode for Fyld
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(getStartDato(), getAlkoholProcent(), getMedarbejdere());
    }
}
