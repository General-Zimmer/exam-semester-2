package Storage;

import Model.Destillat;
import Model.Lager;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

public interface IStorage extends Serializable {


    /**
     * Tilføjer et lager til storage
     * @param lager Lageret der skal tilføjes
     */
    Lager addLager(Lager lager);

    /**
     * Tilføjer et destillat til storage
     * @param destillat Destillatet der skal tilføjes
     */
    Destillat addDestillat(Destillat destillat);

    /**
     * Finder et lager ud fra et UUID
     * @param id UUID'et der skal søges efter
     * @return Lageret der blev fundet
     */
    Lager getLager(UUID id);

    Destillat getDestillat(UUID id);

    void removeLager(UUID id);

    void removeLager(Lager lager);

    void removeDestillat(Destillat destillat);

    void removeDestillat(UUID id);

    Set<Lager> getLagre();
    Set<Destillat> getDestillater();
}
