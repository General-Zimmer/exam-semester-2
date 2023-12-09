package model;

import java.io.Serializable;
import java.util.*;

public class Fad implements Serializable {
    private FadIndhold fadIndhold;
    private final UUID ID; // Et unikt ID, vi kan generere for at adskille hvert objekt
    private final FadType type;
    private final String leverandør;
    private int fillAntal; // Hvor mange gange fadet er blevet fyldt med væske
    private final float størrelse; // Størrelsen på fadet
    private String fadHistorik;
    private Lager lager;

    /**
     * Constructor for Fad
     * @param ID Et unikt ID, vi kan generere for at adskille hvert objekt
     * @param type Type af fad
     * @param leverandør Leverandør af fad
     * @param fillAntal Hvor mange gange fadet er blevet fyldt med væske
     * @param størrelse Størrelsen på fadet
     */
    public Fad(UUID ID, FadType type, String leverandør, int fillAntal, float størrelse) {
        this.ID = ID;
        this.type = type;
        this.leverandør = leverandør;
        this.fillAntal = fillAntal;
        this.størrelse = størrelse;
        this.fadHistorik = "";
        this.fadIndhold = new FadIndhold();
        fadIndhold.addFad(this);
    }

    /**
     * Vil tilføj al fyld fra det andet Fad til dette Fad og clear blanding fra det andet fad.
     * <p>
     *     Kaster en IllegalArgumentException hvis fad er null
     * @param fad Fad som skal merges med dette fad
     */
    public void mergeFad(Fad fad) {
        if (fad == null)
            throw new IllegalArgumentException("Fad må ikke være null");

        for (Fyld fyld : fad.getFyld()) {
            this.fadIndhold.addFyld(fyld);
        }
        fad.clearBlanding();

    }

    public Lager getLager() {
        return lager;
    }

    public void setLager(Lager lager) {
        this.lager = lager;
    }

    /**
     * Getter for fyld
     * @return ArrayList<Fyld>
     */
    public Fyld getFyld(int index) {
        return fadIndhold.getFyld(index);
    }

    /**
     * Get the list of fyld
     * @return ArrayList<Fyld>
     */
    public List<Fyld> getFyld() {
        return new ArrayList<>(fadIndhold.getFyld());
    }

    /**
     * Add a Fyld to the list of fyld
     * @param fyld Fyld to add
     */
    public void addFyld(Fyld fyld) {
        if (fadIndhold.beregnMængdeBrugt() > beregnMængdeTilgængelig())
            throw new IllegalArgumentException("Fyldet er for stort til fadet");

        this.fadIndhold.addFyld(fyld);
    }

    /**
     * beregnMængdeBrugt
     * @return Mængden brugt som double
     */
    public float beregnMængdeBrugt() {
        return fadIndhold.beregnMængdeBrugt();
    }

    /**
     * beregnMængdeTilgængelig
     * @return Mængden tilgængelig som double
     */
    public float beregnMængdeTilgængelig() {
        return størrelse - beregnMængdeBrugt();
    }

    public Map<String, Object> getKompleteHistorie() {
        Map<String, Object> historie = new LinkedHashMap<>();
        historie.put("Fadtype", type);
        historie.put("Fadleverandør", leverandør);
        historie.put("fillAntal", fillAntal);
        historie.put("Fadstørrelse", størrelse);
        historie.put("fadHistorik", fadHistorik);
        return historie;
    }

    /**
     * Getter for blanding
     * @return Blanding
     */
    public FadIndhold getFadindhold() {
        return fadIndhold;
    }

    /**
     * Setter for blanding
     * <p>
     *     Kaster en IllegalArgumentException hvis blanding er null
     * @param fadIndhold Blanding
     */
    public void setFadindhold(FadIndhold fadIndhold) {
        if (fadIndhold == null)
            throw new IllegalArgumentException("Blanding må ikke være null");
        this.fadIndhold = fadIndhold;
    }

    /**
     * Clear the blanding
     */
    public void clearBlanding() {
        this.fadIndhold = new FadIndhold();
    }

    /**
     * Getter for ID
     * @return UUID
     */
    public UUID getID() {
        return ID;
    }

    /**
     * Getter for type
     * @return String
     */
    public FadType getType() {
        return type;
    }

    /**
     * Getter for leverandør
     * @return String
     */
    public String getLeverandør() {
        return leverandør;
    }

    /**
     * Getter for fillAntal
     * @return int
     */
    public int getFillAntal() {
        return fillAntal;
    }

    /**
     * Setter for fillAntal
     * @param fillAntal Hvor mange gange fadet er blevet fyldt med væske
     */
    public void setFillAntal(int fillAntal) {
        this.fillAntal = fillAntal;
    }

    /**
     * Getter for størrelse
     * @return float
     */
    public float getStørrelse() {
        return størrelse;
    }

    /**
     * Getter for fadHistorik
     * @return String
     */
    public String getFadHistorik() {
        return fadHistorik;
    }

    /**
     * Setter for fadHistorik
     * @param fadHistorik Historik for fadet
     */
    public void setFadHistorik(String fadHistorik) {
        this.fadHistorik = fadHistorik;
    }

    @Override
    public String toString() {
        return "Fad{" +
                "type=" + type + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fad fad)) return false;
        return getFillAntal() == fad.getFillAntal() && Float.compare(getStørrelse(), fad.getStørrelse()) == 0 && Objects.equals(getID(), fad.getID()) && getType() == fad.getType() && Objects.equals(getLeverandør(), fad.getLeverandør()) && Objects.equals(getFadHistorik(), fad.getFadHistorik());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getType(), getLeverandør(), getFillAntal(), getStørrelse(), getFadHistorik());
    }
}
