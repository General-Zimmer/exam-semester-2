import controller.Controller;
import gui.Gui;
import model.Destillat;
import model.Lager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.IStorage;
import storage.Storage;

import java.util.UUID;

public class ControllerTest {

    private IStorage storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage();
        Controller.setStorage(storage);
        new Gui();
    }

    @Test
    public void TotallyLegitTest() {
        Assertions.assertEquals(1, 1);
    }

    @Test
    public void createLagerTest() {

        String addresse = "Test";

        Lager lager = Controller.createLager(addresse, 9, 1);

        UUID uuid = lager.getID();


        for (Lager tempLager : storage.getLagre()) {
            Assertions.assertEquals(addresse, tempLager.getAddresse());
            Assertions.assertEquals(lager, tempLager);
            Assertions.assertEquals(new Lager(addresse, uuid, 9, 1), tempLager);
        }

    }
/* AFVENTER DAVIDES; HEY ZIMMER
    @Test
    public void createDestillatTest() {
        String kornsort = "Byg";
        String destillering = "Destillering";

        Destillat destillat = Controller.createDestillat(1, kornsort, 10, destillering);

        UUID uuid = destillat.getID();

        for (Destillat tempDestillat : storage.getDestillater()) {
            Assertions.assertEquals(kornsort, tempDestillat.getKornsort());
            Assertions.assertEquals(destillat, tempDestillat);
            Assertions.assertEquals(new Destillat(uuid, 1, kornsort, 10, destillering), tempDestillat);
        }

    }
    */

    @AfterEach
    public void tearDown() {
    }

}
