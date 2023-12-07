package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Blanding implements Serializable {
    private final List<Fyld> fyld = new ArrayList<>(2);
    private final HashSet<Whisky> whiskyPåFyld = new HashSet<>();

    public Blanding() {

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


    /**
     * beregnMængdeBrugt
     * @return Mængden brugt som double
     */
    public double beregnMængdeBrugt() {
        double sum = 0;
        for (Fyld f : fyld) {
            sum += f.beregnMængdeTilgængelig();
        }

        // Dette tager sig af at der er blevet tappet fra fadet.
        for (Whisky whisky : whiskyPåFyld) {
            sum -= whisky.getMændge();
        }

        return sum;
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
