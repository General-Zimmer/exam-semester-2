import controller.Controller;
import model.Lager;
import org.junit.Assert;
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
    }


    @Test
    public void redigerLager() {

        // TestCase 1

        //arrange
        Lager testLager = Controller.createLager("Sønderhøj 30, 8240 Viby", 2, 4);

        //act
        testLager.redigerReoler(3, 5);


        //assert
        for (int i = 0; i < testLager.getReoler().length; i++) {
            Assertions.assertEquals(3, testLager.getReoler().length);

            for (int j = 0; j < testLager.getReoler()[i].length; j++) {
                Assertions.assertEquals(5, testLager.getReoler()[i].length);
            }

        }


        // TestCase 2


        // Arange
        Lager testLager2 = Controller.createLager("Sønderhøj 30, 8240 Viby", 2, 4);

        //act
        testLager2.redigerReoler(1, 3);

        // assert
        for (int i = 0; i < testLager2.getReoler().length; i++) {
            Assertions.assertEquals(1, testLager2.getReoler().length);

            for (int j = 0; j < testLager2.getReoler()[i].length; j++) {
                Assertions.assertEquals(3, testLager2.getReoler()[i].length);
            }

        }

    }
}
