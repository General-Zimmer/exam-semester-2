package model;

import java.util.ArrayList;
import java.util.HashSet;

public class Blanding {

    ArrayList<Fyld> fyld = new ArrayList<>();

    HashSet<Whisky> whiskyPåFyld = new HashSet<>();




    public Blanding(ArrayList<Fyld> fyld, HashSet<Whisky> whiskyPåFyld) {
        this.fyld = fyld;
        this.whiskyPåFyld = whiskyPåFyld;
    }
}
