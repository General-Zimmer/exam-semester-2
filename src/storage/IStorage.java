package storage;

import model.Destillat;
import model.Fad;
import model.Lager;

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


    /**
     * Finder et destillat ud fra et UUID
     * @param id UUID'et der skal søges efter
     * @return Destillatet der blev fundet
     */
    Destillat getDestillat(UUID id);

    /**
     * Fjerner et lager fra storage
     * @param id UUID'et på det lager der skal fjernes
     */
    void removeLager(UUID id);

    /**
     * Fjerner et destillat fra storage
     * @param lager Lageret der skal fjernes
     */
    void removeLager(Lager lager);

    /**
     * Fjerner et destillat fra storage
     * @param destillat Destillatet der skal fjernes
     */
    void removeDestillat(Destillat destillat);

    /**
     * Fjerner et destillat fra storage
     * @param id UUID'et på det destillat der skal fjernes
     */
    void removeDestillat(UUID id);

    /**
     * Får fat i alle lager objekterne i storage
     * @return Set med alle lager objekterne
     */
    Set<Lager> getLagre();

    /**
     * Får fat i alle destillat objekterne i storage
     * @return Set med alle destillat objekterne
     */
    Set<Destillat> getDestillater();



}
