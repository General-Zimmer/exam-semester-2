package model;

import java.util.ArrayList;
import java.util.List;

public class Blanding {
    private final List<Fyld> fyld = new ArrayList<>(2);

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
        double mængde = 0;
        for (Fyld f : fyld) {
            mængde += f.beregnMængdeTilgængelig();
        }
        return mængde;
    }


}
