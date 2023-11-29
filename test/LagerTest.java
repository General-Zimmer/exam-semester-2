import controller.Controller;
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
    public void getAntalTommePladser() {



        //arrange
        //act
        //assert
    }


    @Test
    public void redigerLager() {



        //arrange
        //act
        //assert
    }
}
