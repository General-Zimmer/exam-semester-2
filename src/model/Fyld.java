package model;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class Fyld {
    private final HashSet<Fad> fad;
    private final HashMap<Destillat, Integer> destillater;
    private final Date startDato;
    private Date slutDato;
    private float alkoholProcent;
    private String medarbejdere; // Dem som har fyldt fadet

    public Fyld(Date startDato, float alkoholProcent, String medarbejdere) {
        this.fad = new HashSet<>();
        this.destillater = new HashMap<>();
        this.startDato = startDato;
        this.alkoholProcent = alkoholProcent;
        this.medarbejdere = medarbejdere;
    }

    public Fyld (float alkoholProcent, String medarbejdere){
        this(new Date(), alkoholProcent, medarbejdere);
    }

    public HashSet<Fad> getFad() {
        return fad;
    }

    public HashMap<Destillat, Integer> getDestillater() {
        return destillater;
    }

    public Date getStartDato() {
        return startDato;
    }

    public Date getSlutDato() {
        return slutDato;
    }

    public void setSlutDato(Date slutDato) {
        this.slutDato = slutDato;
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
        return Float.compare(getAlkoholProcent(), fyld.getAlkoholProcent()) == 0 && Objects.equals(getFad(), fyld.getFad()) && Objects.equals(getDestillater(), fyld.getDestillater()) && Objects.equals(getStartDato(), fyld.getStartDato()) && Objects.equals(getSlutDato(), fyld.getSlutDato()) && Objects.equals(getMedarbejdere(), fyld.getMedarbejdere());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartDato(), getSlutDato(), getAlkoholProcent(), getMedarbejdere());
    }
}
