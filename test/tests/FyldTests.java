package tests;

import model.Destillat;
import model.Fad;
import model.FadType;
import model.Fyld;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

public class FyldTests {

    @BeforeEach
    public void setUp() {
    }


    @Test
    public void beregnOplaringsTest() {
        // Basis data
        String kornsort = "Lars' korn";
        String destillering = "Destillering";
        String kommentar = "Test123";
        float mængde = 100;
        LocalDate baseDate = LocalDate.now().minusDays(40);
        LocalDate baseDate2 = LocalDate.now().minusDays(20);
        Destillat destillat = new Destillat(UUID.randomUUID(), 1, kornsort, mængde, destillering, 48, baseDate, kommentar);
        Destillat destillat2 = new Destillat(UUID.randomUUID(), 1, kornsort, mængde, destillering, 48, baseDate2, kommentar);
        LocalDate fyldDato = LocalDate.now().minusDays(10);
        Fyld fyld = new Fyld(fyldDato, "Søren");

        fyld.addDestillat(destillat, mængde);
        fyld.addDestillat(destillat2, mængde);

        Assertions.assertEquals(10, fyld.beregnOplaringstid());

    }

    @Test
    public void beregnAlkoholdsProcentTest() {


    }

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

    }

    @Test
    public void beregnOplaringstidTest() {

        // Testcase 1

        // arrange
        Fyld fyld = new Fyld(LocalDate.now().minusDays(547), "Søren");

        // act
        float oplaringstid = fyld.beregnOplaringstid();

        // assert
        Assertions.assertEquals(547, oplaringstid);

        // Testcase 2

        // arrange
        Fyld fyld2 = new Fyld(LocalDate.now().minusDays(1), "Søren");

        // act
        float oplaringstid2 = fyld2.beregnOplaringstid();

        // assert
        Assertions.assertEquals(1, oplaringstid2);

        // Testcase 3

        // arrange
        Fyld fyld3 = new Fyld(LocalDate.now().minusDays(0), "Søren");

        // act
        float oplaringstid3 = fyld3.beregnOplaringstid();

        // assert
        Assertions.assertEquals(0, oplaringstid3);
    }

    @Test
    public void beregnAlkoholsProcent() {
        // Testcase 1

        // Basis data
        String kornsort = "Lars' korn";
        String destillering = "Destillering";
        String kommentar = "Test123";
        float mængde = 100;
        LocalDate baseDate = LocalDate.now().minusDays(1);
        Destillat destillat1 = new Destillat(UUID.randomUUID(), 1, kornsort, mængde, destillering, 48, baseDate, kommentar);
        Destillat destillat2 = new Destillat(UUID.randomUUID(), 1, kornsort, mængde, destillering, 40, baseDate, kommentar);
        Fyld fyld = new Fyld("Søren");

        // act
        fyld.addDestillat(destillat1, mængde);
        fyld.addDestillat(destillat2, mængde);

        // assert
        Assertions.assertEquals(44, fyld.beregnAlkoholsProcent());

        // Testcase 2

        // arrange
        Fyld fyld2 = new Fyld("Søren");

        // act
        Destillat destillat3 = new Destillat(UUID.randomUUID(), 1, kornsort, mængde, destillering, 47, baseDate, kommentar);
        fyld2.addDestillat(destillat3, mængde);

        // assert
        Assertions.assertEquals(47, fyld2.beregnAlkoholsProcent());

        // Testcase 3

        // arrange
        Destillat destillat5 = new Destillat(UUID.randomUUID(), 1, kornsort, mængde, destillering, -1, baseDate, kommentar);

        // act
        Fyld fyld3 = new Fyld("Søren");


        // assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {fyld3.addDestillat(destillat5, 2);});

    }

    @Test
    public void beregnMængdeTilgængelig() throws Exception {
        // Basis data
        Fad fad = new Fad(UUID.randomUUID(), FadType.OTHER, "100", 100, 50);
        Fyld fyld = new Fyld("Søren");
        fad.setFyld(fyld);
        fyld.addFad(fad);
        fyld.addDestillat(new Destillat(UUID.randomUUID(), 1, "Lars' korn", 100, "Destillering", 48, LocalDate.now(), "Test123"),
                25);

        // Testcase 1

        // arrange
        Fad fad1 = fad.clone();

        // act

        // assert
        Assertions.assertEquals(25, fad1.getFyld().beregnMængdeTilgængelig());

        // Testcase 2

        // arrange
        Fad fad2 = fad.clone();

        // act
        fad2.getFyld().setStartDato(LocalDate.now().minusDays(365));

        // assert
        Assertions.assertEquals(26.25, fad2.getFyld().beregnMængdeTilgængelig());

        // Testcase 3

        // arrange
        Fad fad3 = fad.clone();

        // act
        fad3.getFyld().setStartDato(LocalDate.now().minusDays(730));

        // assert
        Assertions.assertEquals(26.94, fad3.getFyld().beregnMængdeTilgængelig(), 0.025);

        // Testcase 4

        // arrange
        Fad fad4 = fad.clone();

        // act
        fad4.getFyld().setStartDato(LocalDate.now().minusDays(1095));

        // assert
        Assertions.assertEquals(27.65, fad4.getFyld().beregnMængdeTilgængelig(), 0.025);
    }

}
