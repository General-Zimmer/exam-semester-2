package Model;

import java.util.Date;

public class Whisky {
    private Date whiskyDato;
    private Kvalitet kvalitet;
    private Fyld fyld;

    public Whisky(Date whiskyDato, Kvalitet kvalitet, Fyld fyld) {
        this.whiskyDato = whiskyDato;
        this.kvalitet = kvalitet;
        this.fyld = fyld;
    }
}
