package tests;

import controller.Controller;
import gui.Gui;
import org.junit.jupiter.api.BeforeEach;
import storage.IStorage;
import storage.Storage;

public class DestillatTest {




    private IStorage storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage();
        Controller.setStorage(storage);
        new Gui();
    }
}
