package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

/**
 * Fyld klasse repræsentere en opfyldning af et fad. Denne opfyldning kan indeholde flere destillater og kan skifte fad
 * over tid.
 */
public class Fyld {
    private final HashSet<Fad> fad;
    private final HashMap<Destillat, Float> destillater;
    private final HashSet<Whisky> whiskyPåFyld;
    private final LocalDate startDato;
    private String medarbejdere; // Dem som har fyldt fadet

    /**
     * Constructor for Fyld
     * @param startDato startDato for opfyldning af fad
     * @param medarbejdere medarbejdere som har fyldt fadet
     */
    public Fyld(LocalDate startDato, String medarbejdere) {
        this.fad = new HashSet<>();
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


    public long beregnOplaringstid() {
        LocalDate tidligsteDato = LocalDate.MAX;
        LocalDate senesteDato = LocalDate.MIN;


        for (Destillat destillat : destillater.keySet()) {
            LocalDate destillationsDato = destillat.getDestillationsDato();


            if (destillationsDato.isBefore(tidligsteDato)) {
                tidligsteDato = destillationsDato;
            }
            if (destillationsDato.isAfter(senesteDato)) {
                senesteDato = destillationsDato;
            }
        }


        long oplaringstidIDage = ChronoUnit.DAYS.between(tidligsteDato, senesteDato);

        return oplaringstidIDage;

    }


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


        float alkoholProcent = (totalAlkoholMængde / totalMængde) * 100;



        return alkoholProcent;
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

    public void addDestillat(Destillat destillat, float mængde) {
        destillater.put(destillat, mængde);
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
