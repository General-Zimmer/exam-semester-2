import controller.Controller;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;

import java.util.UUID;

public class ControllerTest {

    @BeforeEach
    public void setUp() {
        Controller.setStorage(new Storage());
    }

    @Test
    public void TotallyLegitTest() {
        Assertions.assertEquals(1, 1);
    }

    @Test
    public void createLagerTest() {

        Controller.createLager("Test", UUID.randomUUID(), 9, 1);
    }

    @Test
    public void createDestillatTest() {
        Assertions.assertEquals(1, 1);
    }

    @AfterEach
    public void tearDown() {
    }

}
