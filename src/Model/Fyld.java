package Model;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class Fyld {
    private HashSet<Fad> fad;
    private HashMap<Destillat, Integer> destillater;
    private Date startDato;
    private Date slutDato;
    private float alkoholProcent;
    private String medarbejdere; // Dem som har fyldt fadet

    public Fyld(HashSet<Fad> fad, HashMap<Destillat, Integer> destillater,
                Date startDato, Date slutDato, float alkoholProcent, String medarbejdere) {
        this.fad = new HashSet<>();
        this.destillater = new HashMap<>();
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.alkoholProcent = alkoholProcent;
        this.medarbejdere = medarbejdere;
    }
}
