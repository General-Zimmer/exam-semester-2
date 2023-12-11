package testmodels;

import model.Destillat;
import model.Lager;
import storage.IStorage;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@SuppressWarnings("SpellCheckingInspection")
public class TestStorage implements IStorage {
    private final LinkedHashSet<Destillat> destillater = new LinkedHashSet<>();
    private final LinkedHashSet<Lager> lagre = new LinkedHashSet<>();


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
        return new LinkedHashSet<>(lagre);
    }

    /**
     * Får fat i alle destillat objekterne i storage
     * @return Set med alle destillat objekterne
     */
    @Override
    public Set<Destillat> getDestillater() {
        return new LinkedHashSet<>(destillater);
    }

}
