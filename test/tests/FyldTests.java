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
        Fad fad = new Fad(UUID.randomUUID(), FadType.OTHER, "100", 100, 100);
        Fyld fyld = new Fyld("Søren", fad);
        float mængde = 50;
        fyld.addDestillat(new Destillat(UUID.randomUUID(), 1, "Lars' korn", 100, "Destillering", 48, LocalDate.now(), "Test123"),
                mængde);
        Destillat destillat1 = new Destillat(UUID.randomUUID(), 1, "Lars' korn", 100, "Destillering", 48, LocalDate.now(), "Test123");

        // act
        fyld.addDestillat(destillat1, 50);

        // assert
        Assertions.assertEquals(0, fad.beregnMængdeTilgængelig());

        // Testcase 2

        // arrange
        float mængde2 = 75;
        Fad fad2 = new Fad(UUID.randomUUID(), FadType.OTHER, "100", 100, 75);
        Fyld fyld2 = new Fyld("Søren", fad2);
        fyld2.addDestillat(new Destillat(UUID.randomUUID(), 1, "Lars' korn", 100, "Destillering", 48, LocalDate.now(), "Test123"),
                mængde2);
        Destillat destillat2 = new Destillat(UUID.randomUUID(), 1, "Lars' korn", 200, "Destillering", 48, LocalDate.now(), "Test123");

        // act

        // assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> fyld2.addDestillat(destillat2, 50));

        // Testcase 3

        // arrange
        float mængde3 = 75;
        Fad fad3 = new Fad(UUID.randomUUID(), FadType.OTHER, "100", 100, 75);
        Fyld fyld3 = new Fyld("Søren", fad3);
        fyld3.addDestillat(new Destillat(UUID.randomUUID(), 1, "Lars' korn", 100, "Destillering", 48, LocalDate.now(), "Test123"),
                mængde3);
        Destillat destillat3 = new Destillat(UUID.randomUUID(), 1, "Lars' korn", 100, "Destillering", 48, LocalDate.now(), "Test123");

        // act

        // assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> fyld3.addDestillat(destillat3, 0));

        // Testcase EXTRA

        // arrange
        float mængde4 = 75;
        Fad fad4 = new Fad(UUID.randomUUID(), FadType.OTHER, "100", 100, 75);
        Fyld fyld4 = new Fyld("Søren", fad4);
        fyld4.addDestillat(new Destillat(UUID.randomUUID(), 1, "Lars' korn", 100, "Destillering", 48, LocalDate.now(), "Test123"),
                mængde4);
        Destillat destillat4 = new Destillat(UUID.randomUUID(), 1, "Lars' korn", 100, "Destillering", 48, LocalDate.now(), "Test123");

        // act

        // assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> fyld4.addDestillat(destillat4, -1));
    }

    @Test
    public void beregnAlkoholsProcentTest() {
        // Testcase 1

        // Basis data
        String kornsort = "Lars' korn";
        String destillering = "Destillering";
        String kommentar = "Test123";
        float mængde = 100;
        LocalDate baseDate = LocalDate.now().minusDays(1);
        Destillat destillat1 = new Destillat(UUID.randomUUID(), 1, kornsort, mængde, destillering, 48, baseDate, kommentar);
        Destillat destillat2 = new Destillat(UUID.randomUUID(), 1, kornsort, mængde, destillering, 40, baseDate, kommentar);
        Fad fad = new Fad(UUID.randomUUID(), FadType.OTHER, "100", 100, 9001);
        Fyld fyld = new Fyld("Søren", fad);

        // act
        fyld.addDestillat(destillat1, mængde);
        fyld.addDestillat(destillat2, mængde);

        // assert
        Assertions.assertEquals(44, fyld.beregnAlkoholsProcent());

        // Testcase 2

        // arrange
        Fyld fyld2 = new Fyld("Søren", fad);

        // act
        Destillat destillat3 = new Destillat(UUID.randomUUID(), 1, kornsort, mængde, destillering, 47, baseDate, kommentar);
        fyld2.addDestillat(destillat3, mængde);

        // assert
        Assertions.assertEquals(47, fyld2.beregnAlkoholsProcent());

        // Testcase 3

        // arrange
        Destillat destillat5 = new Destillat(UUID.randomUUID(), 1, kornsort, mængde, destillering, -1, baseDate, kommentar);

        // act
        Fyld fyld3 = new Fyld("Søren", fad);


        // assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> fyld3.addDestillat(destillat5, 2));

    }


}
