package tests;

import model.Destillat;
import model.Fad;
import model.FadType;
import model.Fyld;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

public class FadIndholdTest {

    @Test
    public void beregnOplaringstidTest() {

        // Fad
        Fad fad = new Fad(UUID.randomUUID(), FadType.OTHER, "100", 100, 100);

        // Testcase 1

        // arrange
        Fyld fyld = new Fyld(LocalDate.now().minusDays(547), "Søren", fad);

        // act
        float oplaringstid = fyld.beregnOplaringstid();

        // assert
        Assertions.assertEquals(547, oplaringstid);

        // Testcase 2

        // arrange
        Fyld fyld2 = new Fyld(LocalDate.now().minusDays(1), "Søren", fad);

        // act
        float oplaringstid2 = fyld2.beregnOplaringstid();

        // assert
        Assertions.assertEquals(1, oplaringstid2);

        // Testcase 3

        // arrange
        Fyld fyld3 = new Fyld(LocalDate.now().minusDays(0), "Søren",fad);

        // act
        float oplaringstid3 = fyld3.beregnOplaringstid();

        // assert
        Assertions.assertEquals(0, oplaringstid3);
    }

    @Test
    public void beregnMængdeTilgængelig() throws Exception {
        // Basis data
        Fad fad = new Fad(UUID.randomUUID(), FadType.OTHER, "100", 100, 50);
        Fyld fyld = new Fyld("Søren", fad);
        fyld.addDestillat(new Destillat(UUID.randomUUID(), 1, "Lars' korn", 100, "Destillering", 48, LocalDate.now(), "Test123"),
                25);

        // Testcase 1

        // arrange

        // act

        // assert
        Assertions.assertEquals(25, fad.getBlanding().beregnMængdeTilgængelig());

        // Testcase 2

        // arrange

        // act
        fad.getFyld(0).setStartDato(LocalDate.now().minusDays(365));

        // assert
        Assertions.assertEquals(26.25, fad.getBlanding().beregnMængdeTilgængelig());

        // Testcase 3

        // arrange

        // act
        fad.getFyld(0).setStartDato(LocalDate.now().minusDays(730));

        // assert
        Assertions.assertEquals(26.94, fad.getBlanding().beregnMængdeTilgængelig(), 0.025);

        // Testcase 4

        // arrange

        // act
        fad.getFyld(0).setStartDato(LocalDate.now().minusDays(1095));

        // assert
        Assertions.assertEquals(27.65, fad.getBlanding().beregnMængdeTilgængelig(), 0.025);
    }
}
