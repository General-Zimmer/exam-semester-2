package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Fyld klasse repræsentere en opfyldning af et fad. Denne opfyldning kan indeholde flere destillater og kan skifte fad
 * over tid.
 */
@SuppressWarnings("SpellCheckingInspection")
public class Fyld implements Serializable {
    private Blanding blanding;
    private final HashMap<Destillat, Float> destillater;
    private LocalDate startDato;
    private String medarbejdere; // Dem som har fyldt fadet


    /**
     * Constructor for Fyld
     * @param startDato startDato for opfyldning af fad
     * @param medarbejdere medarbejdere som har fyldt fadet
     */
    public Fyld(LocalDate startDato, String medarbejdere, Fad fad) {
        this.destillater = new HashMap<>();
        this.startDato = startDato;
        this.medarbejdere = medarbejdere;
        this.blanding = fad.getBlanding();
        fad.addFyld(this);
    }

    /**
     * Constructor for Fyld, hvor startDato er LocalDate.now()
     *
     * @param medarbejdere medarbejdere som har fyldt fadet
     */
    public Fyld (String medarbejdere, Fad fad){
        this(LocalDate.now(), medarbejdere, fad);
    }

    /**
     * Metode som udrenger den samlede alkoholsprocent for fyld.
     * @return udregnet alkohols procent
     */
    public float beregnAlkoholsProcent() {
        float totalMængde = 0;
        float totalAlkoholMængde = 0;

        for (Map.Entry<Destillat, Float> entry : destillater.entrySet()) {
            Destillat destillat = entry.getKey();
            float destillatMængde = entry.getValue();

            float alkoholMængde = (destillat.getAlkoholProcent() / 100) * destillatMængde;

            totalMængde += destillatMængde;
            totalAlkoholMængde += alkoholMængde;
        }

        return (totalAlkoholMængde / totalMængde) * 100;
    }

    /**
     * Tilføjer et destillat som fyld
     * <p>
     *     Kaster en IlegalArgumentException hvis der er ikke plads i fadet.
     * @param destillat er selve destillatet der bliver fyldt på
     * @param mængde er den mængde som der fyldes på af destillatet
     */
    public void addDestillat(Destillat destillat, float mængde) {

        double yeet = blanding.beregnMængdeTilgængelig();

        if (mængde > yeet && yeet != -1) {
            throw new IllegalArgumentException("Mængden af destillat er større end destillatets mængde");
        }

        if (destillat.getAlkoholProcent() < 0) {
            throw new IllegalArgumentException("Alkoholprocenten er mindre end 0");
        }

        if (mængde <= 0) {
            throw new IllegalArgumentException("Mængden af destillat er mindre end 0");
        }

        destillater.put(destillat, mængde);
    }

    /**
     * Metode som udregner den samlede oplaringstid for fyld.
     * @return udregnet oplaringstid
     */
    public long beregnOplaringstid() {
        return ChronoUnit.DAYS.between(startDato, LocalDate.now());
    }

    /**
     * Getter for destillater
     * @return copy of HashMap<Destillat, Integer>
     */
    public HashMap<Destillat, Float> getDestillater() {
        return new HashMap<>(destillater);
    }

    public void removeDestillat(Destillat destillat) {
        destillater.remove(destillat);
    }

    /**
     * Getter for startDato
     * @return LocalDate
     */
    public LocalDate getStartDato() {
        return startDato;
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

    public void setStartDato(LocalDate startDato) {
        this.startDato = startDato;
    }

    /**
     * Getter for blanding
     * <p>
     *     Kaster en IlegalArgumentException hvis blanding er null.
     * @param blanding blanding som skal sættes
     */
    public void setBlanding(Blanding blanding) {
        if (blanding == null)
            throw new IllegalArgumentException("Blanding må ikke være null");
        this.blanding = blanding;
    }

    @Override
    public String toString() {
        return "start dato: " + startDato +
                ", medarbejdere: " + medarbejdere + '\'';
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
        return Objects.equals(getStartDato(), fyld.getStartDato()) && Objects.equals(getMedarbejdere(), fyld.getMedarbejdere());
    }



    @Override
    public int hashCode() {
        return Objects.hash(getStartDato(), getMedarbejdere());
    }
}
