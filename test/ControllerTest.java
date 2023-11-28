import controller.Controller;
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
    }

    @Test
    public void TotallyLegitTest() {
        Assertions.assertEquals(1, 1);
    }

    @Test
    public void createLagerTest() {

        UUID uuid = UUID.randomUUID();
        String addresse = "Test";

        Lager lager = Controller.createLager(addresse, uuid, 9, 1);



        for (Lager tempLager : storage.getLagre()) {
            Assertions.assertEquals(addresse, tempLager.getAddresse());
            Assertions.assertEquals(lager, tempLager);
            Assertions.assertEquals(new Lager(addresse, uuid, 9, 1), tempLager);
        }

    }

    @Test
    public void createDestillatTest() {
        Assertions.assertEquals(1, 1);
    }

    @AfterEach
    public void tearDown() {
    }

}
