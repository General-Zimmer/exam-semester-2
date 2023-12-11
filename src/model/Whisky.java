package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("SpellCheckingInspection")
public class Whisky implements Serializable {
    private final LocalDate whiskyDato;
    private final Kvalitet kvalitet; // SINGLECASK, SINGLEMALT, BLENDED
    private final FadIndhold fadIndhold;
    private final float mængde;

    /**
     *  Constructor for Whisky
     * @param whiskyDato Datoen for den dag destilleringen blev til whisky
     * @param kvalitet Kvaliteten af whiskyen(SINGLECASK, SINGLEMALT, BLENDED)
     * @param fadIndhold Er en opfyldning af et fad. Opfyldningen kan indeholde flere destillater og kan skifte fad over tid
     */
    public Whisky(LocalDate whiskyDato, Kvalitet kvalitet, FadIndhold fadIndhold, float mængde) {
        this.whiskyDato = whiskyDato;
        this.kvalitet = kvalitet;
        this.fadIndhold = fadIndhold;
        this.mængde = mængde;
    }

    public String getKompleteHistorie() {

        String medarbejder = null;
        int batch = -1;
        String fadHistorie = "N/A";
        String whiskyKorn = "N/A";
        float whiskyMængde = -1;
        float whiskyAlkoholProcent = -1;
        String kommentar = "N/A";
        Fad fad = fadIndhold.getFad().get(fadIndhold.getFad().size()-1);

        LocalDate ældsteDestillat = LocalDate.MAX;
        for (Fyld fyld : fadIndhold.getFyld()) {
            for (Destillat destillat : fyld.getDestillater().keySet()) {
                if (destillat.getDestillationsDato().isBefore(ældsteDestillat)) {
                    ældsteDestillat = destillat.getDestillationsDato();
                    medarbejder = fyld.getMedarbejdere();
                    batch = destillat.getMaltBatch();
                    fadHistorie = fadIndhold.toStringHistorie();
                    whiskyKorn = destillat.getKornsort();
                    whiskyMængde = destillat.getMængde();
                    whiskyAlkoholProcent = destillat.getAlkoholProcent();
                    kommentar = destillat.getKommentar();
                }
            }
        }

        String kompletHistorie = "Whiskyens historie: \n" +
                "Whiskyen er blevet produceret d. " + whiskyDato + ". Den er lavet som en: "+ kvalitet + ". og har en samlet mængde på "+ mængde + "\n" +
                "Det ældste destillatet for whiskyen er lavet d. " + ældsteDestillat + " og blev destilleret af " + medarbejder +
                "\n" + "Whiskyen har modnet i et " + fad.toString() + " i " + ChronoUnit.YEARS.between(fadIndhold.beregnOplaringstid(), LocalDate.now()) + " år, og er batch nr. " + batch + "\n"
        + "\n" + "Fadets historie: " + "\n" + fadHistorie + "\n \n" +
                "Destillatets historie: " + "\n" +
                "Whiskyen er lavet af et destillat, hvor der er blevet brugt korntypen " + whiskyKorn + " og har haft en samlet destillations mængde på " + whiskyMængde + "\n" +
                "Den samlede alkohols procent for destillatet er derfor endt på " + whiskyAlkoholProcent + "\n \n" +
                kommentar;

        return kompletHistorie;
    }

    /**
     * Getter for whiskyDato
     * @return Date
     */
    public LocalDate getWhiskyDato() {
        return whiskyDato;
    }

    /**
     * Getter for kvalitet
     * @return Kvalitet
     */
    public Kvalitet getKvalitet() {
        return kvalitet;
    }

    public FadIndhold getBlanding() {
        return fadIndhold;
    }

    public float getMængde() {
        return mængde;
    }

    /**
     * Equals metode til at sammenligne to Whisky objekter
     * @param o Objektet der skal sammenlignes med
     * @return Om de er ens eller ej
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Whisky whisky)) return false;
        return Float.compare(mængde, whisky.mængde) == 0 && Objects.equals(getWhiskyDato(), whisky.getWhiskyDato()) && getKvalitet() == whisky.getKvalitet() && Objects.equals(getBlanding(), whisky.getBlanding());
    }

    @Override
    public String toString() {
        return "Kvalitet: " + kvalitet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWhiskyDato(), getKvalitet(), getBlanding(), mængde);
    }
}
