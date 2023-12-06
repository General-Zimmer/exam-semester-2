package tests;

import controller.Controller;
import gui.Gui;
import model.Lager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.IStorage;
import storage.Storage;

public class LagerTest {


    private IStorage storage;


    @BeforeEach
    public void setUp() {
        storage = new Storage();
        Controller.setStorage(storage);
        if (Gui.getInstance() == null) {
            new Gui();
        }
    }


    @Test
    public void redigerLager() {

        // TestCase 1

        //arrange
        Lager testLager = Controller.createLager("Sønderhøj 30, 8240 Viby", 2, 4);

        //act
        testLager.redigerReoler(3, 5);


        //assert
        Assertions.assertEquals(3, testLager.getReoler().length);
        Assertions.assertEquals(5, testLager.getReoler()[0].length);



        // TestCase 2


        // Arange
        Lager testLager2 = Controller.createLager("Sønderhøj 30, 8240 Viby", 2, 4);

        // act
        testLager2.redigerReoler(1, 3);

        // assert

        Assertions.assertEquals(1, testLager2.getReoler().length);
        Assertions.assertEquals(3, testLager2.getReoler()[0].length);





        // TestCase 3

        // arrange
        Lager testLager3 = Controller.createLager("Sønderhøj 30, 8240 Viby", 2, 4);

        // act & assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            testLager3.redigerReoler(0, 5);
        });

    }
}
