package storage;

import model.Destillat;
import model.Lager;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@SuppressWarnings("SpellCheckingInspection")
public class Storage implements IStorage, Serializable {

    private final Set<Destillat> destillater = new HashSet<>();
    private final Set<Lager> lagre = new HashSet<>();

    /**
     * Gemmer et lager objekt i storage.
     * @param lager Lageret der skal tilføjes til storage
     * @return Lageret der er blevet tilføjet til storage
     */
    @Override
    public Lager addLager(Lager lager) {
        lagre.add(lager);
        return lager;
    }

    /**
     * Gemmer et destillat objekt i storage.
     * @param destillat Destillatet der skal tilføjes til storage
     * @return Destillatet der er blevet tilføjet til storage
     */
    @Override
    public Destillat addDestillat(Destillat destillat) {
        destillater.add(destillat);
        return destillat;
    }

    /**
     * Finder et lager objekt i storage ud fra et UUID
     * @param id UUID'et der skal søges efter i storage
     * @return Lager objektet der er blevet fundet
     */
    @Override
    public Lager getLager(UUID id) {

        for (Lager lager : lagre) {
            if (lager.getID().equals(id)) {
                return lager;
            }
        }
        return null;
    }

    /**
     * Finder et destillat objekt i storage ud fra et UUID
     * @param id UUID'et der skal søges efter i storage
     * @return Destillat objektet der er blevet fundet
     */
    @Override
    public Destillat getDestillat(UUID id) {
        for (Destillat destillat : destillater) {
            if (destillat.getID().equals(id)) {
                return destillat;
            }
        }
        return null;
    }


    /**
     * Fjerner et destillat objekt fra storage
     * @param lager Lageret der skal fjernes fra storage
     */
    @Override
    public void removeLager(Lager lager) {
        lagre.remove(lager);
    }



    /**
     * Får fat i alle lager objekterne i storage
     * @return Set med alle lager objekterne
     */
    @Override
    public Set<Lager> getLagre() {
        return new HashSet<>(lagre);
    }

    /**
     * Får fat i alle destillat objekterne i storage
     * @return Set med alle destillat objekterne
     */
    @Override
    public Set<Destillat> getDestillater() {
        return new HashSet<>(destillater);
    }

}
