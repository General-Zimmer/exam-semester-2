package tests;

import model.Destillat;
import model.Fad;
import model.FadType;
import model.Fyld;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

@SuppressWarnings("SpellCheckingInspection")
public class FyldTests {

    @Test
    public void addDestillatTest() {

        // Testcase 1

        // arrange
        Fyld fyld = new Fyld("Søren");
        fyld.addFad(new Fad(UUID.randomUUID(), FadType.OTHER, "100", 100, 100));
        float mængde = 50;
        fyld.addDestillat(new Destillat(UUID.randomUUID(), 1, "Lars' korn", 100, "Destillering", 48, LocalDate.now(), "Test123"),
                mængde);
        Destillat destillat1 = new Destillat(UUID.randomUUID(), 1, "Lars' korn", 100, "Destillering", 48, LocalDate.now(), "Test123");

        // act
        fyld.addDestillat(destillat1, 50);

        // assert
        Assertions.assertEquals(0, fyld.beregnMængdeTilgængelig());

        // Testcase 2

        // arrange
        float mængde2 = 75;
        Fyld fyld2 = new Fyld("Søren");
        fyld2.addFad(new Fad(UUID.randomUUID(), FadType.OTHER, "100", 100, 75));
        fyld2.addDestillat(new Destillat(UUID.randomUUID(), 1, "Lars' korn", 100, "Destillering", 48, LocalDate.now(), "Test123"),
                mængde2);
        Destillat destillat2 = new Destillat(UUID.randomUUID(), 1, "Lars' korn", 200, "Destillering", 48, LocalDate.now(), "Test123");

        // act

        // assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> fyld2.addDestillat(destillat2, 50));

        // Testcase 3

        // arrange
        float mængde3 = 75;
        Fyld fyld3 = new Fyld("Søren");
        fyld3.addFad(new Fad(UUID.randomUUID(), FadType.OTHER, "100", 100, 75));
        fyld3.addDestillat(new Destillat(UUID.randomUUID(), 1, "Lars' korn", 100, "Destillering", 48, LocalDate.now(), "Test123"),
                mængde3);
        Destillat destillat3 = new Destillat(UUID.randomUUID(), 1, "Lars' korn", 100, "Destillering", 48, LocalDate.now(), "Test123");

        // act

        // assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> fyld3.addDestillat(destillat3, 0));

        // Testcase EXTRA

        // arrange
        float mængde4 = 75;
        Fyld fyld4 = new Fyld("Søren");
        fyld4.addFad(new Fad(UUID.randomUUID(), FadType.OTHER, "100", 100, 75));
        fyld4.addDestillat(new Destillat(UUID.randomUUID(), 1, "Lars' korn", 100, "Destillering", 48, LocalDate.now(), "Test123"),
                mængde4);
        Destillat destillat4 = new Destillat(UUID.randomUUID(), 1, "Lars' korn", 100, "Destillering", 48, LocalDate.now(), "Test123");

        // act

        // assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> fyld4.addDestillat(destillat4, -1));
    }

}
