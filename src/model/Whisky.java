package model;

import java.io.Serializable;
import java.time.LocalDate;
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
        StringBuilder historie = new StringBuilder();
        Map<String, Object> historieMap = fadIndhold.getKompleteHistorie();
        historieMap.put("whiskyDato", whiskyDato);
        historieMap.put("kvalitet", kvalitet);
        historieMap.put("mængde", mængde);
        historieMap.forEach((key, value) -> historie.append(key).append(": ").append(value).append("\n"));

        LocalDate ældsteDestillat = LocalDate.MAX;
        Map<String, Object> fyldMap = (Map<String, Object>) historieMap.get("fyldMap");
        for (Map.Entry<String, Object> entry : fyldMap.entrySet()) {
            Map<String, Object> destillaterMap = (Map<String, Object>) entry.getValue();
            for (Map.Entry<String, Object> destillatEntry : destillaterMap.entrySet()) {
                Map<String, Object> destillatMap = (Map<String, Object>) destillatEntry.getValue();
                LocalDate destillationsDato = (LocalDate) destillatMap.get("destillationsDato");
                if (destillationsDato.isBefore(ældsteDestillat)) {
                    ældsteDestillat = destillationsDato;
                }
            }
        }


        return historie.toString();
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
    public int hashCode() {
        return Objects.hash(getWhiskyDato(), getKvalitet(), getBlanding(), mængde);
    }
}
