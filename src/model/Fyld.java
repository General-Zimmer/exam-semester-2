package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Fyld klasse repræsentere en opfyldning af et fad. Denne opfyldning kan indeholde flere destillater og kan skifte fad
 * over tid.
 */
public class Fyld implements Serializable {
    private final ArrayList<Fad> fad;
    private final HashMap<Destillat, Float> destillater;
    private final HashSet<Whisky> whiskyPåFyld;
    private LocalDate startDato;
    private String medarbejdere; // Dem som har fyldt fadet

    /**
     * Constructor for Fyld
     * @param startDato startDato for opfyldning af fad
     * @param medarbejdere medarbejdere som har fyldt fadet
     */
    public Fyld(LocalDate startDato, String medarbejdere) {
        this.fad = new ArrayList<>();
        this.destillater = new HashMap<>();
        this.whiskyPåFyld = new HashSet<>();
        this.startDato = startDato;
        this.medarbejdere = medarbejdere;
    }

    /**
     * Constructor for Fyld, hvor startDato er LocalDate.now()
     *
     * @param medarbejdere medarbejdere som har fyldt fadet
     */
    public Fyld (String medarbejdere){
        this(LocalDate.now(), medarbejdere);
    }

    /**
     * Metode som udregner den samlede oplaringstid for fyld.
     * @return udregnet oplaringstid
     */
    public long beregnOplaringstid() {
        return ChronoUnit.DAYS.between(startDato, LocalDate.now());
    }


    /**
     * Udregner mængden der ligger i et fad og tager forbehold for at noget af indholdet fordamper.
     * @return den fulde mængde i fad.
     */
    public double beregnMængdeTilgængelig() {
        if (fad.isEmpty()) {
            return -1;
        }
        return fad.get(fad.size()-1).getStørrelse() - beregnMængdeBrugt();
    }

    /**
     * Udregner mængden der er blevet brugt fra fadet.
     * @return den mængde der er blevet brugt fra fadet.
     */
    public double beregnMængdeBrugt() {
        if (fad.isEmpty()) {
            return -1;
        }

        // Opsummere mængden af destillater i fadet.
        double sum = 0;
        for (Map.Entry<Destillat, Float> entry : destillater.entrySet()) {
            sum += entry.getValue();
        }

        // Dette tager sig af tab af mængde over tid.
        if (ChronoUnit.DAYS.between(this.startDato, LocalDate.now()) < 365) {

        } else if (ChronoUnit.DAYS.between(startDato, LocalDate.now()) < 730){
            sum = (sum * 0.95);
        } else {
            long years = ChronoUnit.YEARS.between(startDato, LocalDate.now());
            double afterFirstYear = Math.pow(0.97, (years > 1 ? years-1 : 1));
            sum = (sum * 0.95*afterFirstYear) ;
        }

        // Dette tager sig af at der er blevet tappet fra fadet.
        for (Whisky whisky : whiskyPåFyld) {
            sum -= whisky.getMængde();
        }

        return sum;
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

        double mængdeTilgængelig = beregnMængdeTilgængelig();

        if (mængde > mængdeTilgængelig && mængdeTilgængelig != -1) {
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
     * Getter for fad
     * @return copy of HashSet<Fad>
     */
    public HashSet<Fad> getFad() {
        return new HashSet<>(fad);
    }

    public void addFad(Fad fad) {
        this.fad.add(fad);
    }

    public void removeFad(Fad fad) {
        this.fad.remove(fad);
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

    public HashSet<Whisky> getWhiskyPåFyld() {
        return new HashSet<>(whiskyPåFyld);
    }

    public void addWhisky(Whisky whisky) {
        whiskyPåFyld.add(whisky);
    }

    public void removeWhisky(Whisky whisky) {
        whiskyPåFyld.remove(whisky);
    }

    public void setStartDato(LocalDate startDato) {
        this.startDato = startDato;
    }

    @Override
    public String toString() {
        return "Whisky: " + whiskyPåFyld +
                ", start dato: " + startDato +
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
        if (!(o instanceof Fyld)) return false;
        Fyld fyld = (Fyld) o;
        return Objects.equals(getStartDato(), fyld.getStartDato()) && Objects.equals(getMedarbejdere(), fyld.getMedarbejdere());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getStartDato(), getMedarbejdere());
    }
}
