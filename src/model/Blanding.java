package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Blanding implements Serializable {
    private final ArrayList<Fad> fad = new ArrayList<>();
    private final List<Fyld> fyld = new ArrayList<>(2);
    private final HashSet<Whisky> whiskyPåFyld = new HashSet<>();


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
     * @return mængden i liter. -1 hvis fadet er tomt.
     */
    public double beregnMængdeBrugt() {
        if (fad.isEmpty()) {
            return -1;
        }

        // Opsummere mængden af destillater i fadet.
        double sum = 0;
        for (Fyld fyld : this.fyld) {
            Map<Destillat, Float> destillater = fyld.getDestillater();
            for (Map.Entry<Destillat, Float> entry : destillater.entrySet()) {
                sum += entry.getValue();
            }
        }

        LocalDate tidligsteStartDato = beregnOplaringstid();

        // Dette tager sig af tab af mængde over tid.
        //noinspection StatementWithEmptyBody
        if (ChronoUnit.DAYS.between(tidligsteStartDato, LocalDate.now()) < 365) {

        } else if (ChronoUnit.DAYS.between(tidligsteStartDato, LocalDate.now()) < 730){
            sum = (sum * 0.95);
        } else {
            long years = ChronoUnit.YEARS.between(tidligsteStartDato, LocalDate.now());
            double afterFirstYear = Math.pow(0.97, (years > 1 ? years-1 : 1));
            sum = (sum * 0.95*afterFirstYear) ;
        }

        // Dette tager sig af at der er blevet tappet fra fadet.
        for (Whisky whisky : whiskyPåFyld) {
            sum -= whisky.getMændge();
        }

        return sum;
    }

    /**
     * Udregner den tidligste start dato for fyldet i fadet.
     * @return tidligste start dato
     */
    public LocalDate beregnOplaringstid() {
        LocalDate tidligsteStartDato = LocalDate.now();
        for (Fyld fyld : this.fyld) {
            if (fyld.getStartDato().isBefore(tidligsteStartDato)) {
                tidligsteStartDato = fyld.getStartDato();
            }
        }
        return tidligsteStartDato;
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

    public List<Fyld> getFyld() {
        return new ArrayList<>(fyld);
    }

    public Fyld getFyld(int index) {
        if (index < 0 || index >= fyld.size()) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
        }
        return fyld.get(index);
    }

    public void addFyld(Fyld fyld) {
        this.fyld.add(fyld);
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


}