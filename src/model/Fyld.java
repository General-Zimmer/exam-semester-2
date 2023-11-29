package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class Fyld {
    private final HashSet<Fad> fad;
    private final HashMap<Destillat, Integer> destillater;
    private final LocalDate startDato;
    private float alkoholProcent;
    private String medarbejdere; // Dem som har fyldt fadet

    public Fyld(LocalDate startDato, float alkoholProcent, String medarbejdere) {
        this.fad = new HashSet<>();
        this.destillater = new HashMap<>();
        this.startDato = startDato;
        this.alkoholProcent = alkoholProcent;
        this.medarbejdere = medarbejdere;
    }

    public Fyld (float alkoholProcent, String medarbejdere){
        this(LocalDate.now(), alkoholProcent, medarbejdere);
    }

    public HashSet<Fad> getFad() {
        return fad;
    }

    public HashMap<Destillat, Integer> getDestillater() {
        return destillater;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public float getAlkoholProcent() {
        return alkoholProcent;
    }

    public void setAlkoholProcent(float alkoholProcent) {
        this.alkoholProcent = alkoholProcent;
    }

    public String getMedarbejdere() {
        return medarbejdere;
    }

    public void setMedarbejdere(String medarbejdere) {
        this.medarbejdere = medarbejdere;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fyld fyld)) return false;
        return Float.compare(getAlkoholProcent(), fyld.getAlkoholProcent()) == 0 && Objects.equals(getFad(), fyld.getFad()) && Objects.equals(getDestillater(), fyld.getDestillater()) && Objects.equals(getStartDato(), fyld.getStartDato()) && Objects.equals(getMedarbejdere(), fyld.getMedarbejdere());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartDato(), getAlkoholProcent(), getMedarbejdere());
    }
}
