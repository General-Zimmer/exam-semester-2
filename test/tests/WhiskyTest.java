package tests;

import controller.Controller;
import gui.Gui;
import model.Fad;
import model.FadIndhold;
import model.Lager;
import model.Whisky;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.IStorage;
import storage.Storage;
import testmodels.TestStorage;

public class WhiskyTest {


    @BeforeEach
    public void setUp() {
        if (Gui.getInstance() == null) {
            new Gui();
        }
    }

    @Test
    public void KompleteHistorieTest() {

        // BaseData
        IStorage storage = new TestStorage();
        Controller.setStorage(storage);
        Controller.initStorage();

        // arrange
        Lager lager = storage.getLagre().iterator().next();
        Fad fad = lager.getReoler()[0][0];
        FadIndhold fadIndhold = fad.getFadindhold();
        Whisky whisky = fadIndhold.getWhiskyPåFyld().iterator().next();

        System.out.println(whisky.getKompleteHistorie());

        // act

        // assert
    }
}
