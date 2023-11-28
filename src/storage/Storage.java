package storage;

import model.Destillat;
import model.Lager;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Storage implements IStorage, Serializable {

    private final Set<Destillat> destillater = new HashSet<>();
    private final Set<Lager> lagre = new HashSet<>();


    @Override
    public Lager addLager(Lager lager) {
        lagre.add(lager);
        return lager;
    }

    @Override
    public Destillat addDestillat(Destillat destillat) {
        destillater.add(destillat);
        return destillat;
    }

    @Override
    public Lager getLager(UUID id) {

        for (Lager lager : lagre) {
            if (lager.getID().equals(id)) {
                return lager;
            }
        }
        return null;
    }

    @Override
    public Destillat getDestillat(UUID id) {
        for (Destillat destillat : destillater) {
            if (destillat.getID().equals(id)) {
                return destillat;
            }
        }
        return null;
    }

    @Override
    public void removeLager(UUID id) {
        lagre.remove(getLager(id));
    }

    @Override
    public void removeLager(Lager lager) {
        lagre.remove(lager);
    }

    @Override
    public void removeDestillat(Destillat destillat) {
        destillater.remove(destillat);
    }


    @Override
    public void removeDestillat(UUID id) {
        destillater.remove(getDestillat(id));
    }

    @Override
    public Set<Lager> getLagre() {
        return new HashSet<>(lagre);
    }

    @Override
    public Set<Destillat> getDestillater() {
        return new HashSet<>(destillater);
    }

}