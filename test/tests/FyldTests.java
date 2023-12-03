package tests;

import controller.Controller;
import gui.Gui;
import model.Destillat;
import model.Fyld;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;

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
        float mængde = 200;
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
        // Basis data
        String kornsort = "Lars' korn";
        String destillering = "Destillering";
        String kommentar = "Test123";
        float mængde = 100;
        LocalDate baseDate = LocalDate.now().minusDays(1);
        Destillat destillat1 = new Destillat(UUID.randomUUID(), 1, kornsort, mængde, destillering, 48, baseDate, kommentar);
        Destillat destillat2 = new Destillat(UUID.randomUUID(), 1, kornsort, mængde, destillering, 40, baseDate, kommentar);
        Fyld fyld = new Fyld("Søren");

        fyld.addDestillat(destillat1, mængde);
        fyld.addDestillat(destillat2, mængde);

        Assertions.assertEquals(44, fyld.beregnAlkoholsProcent());

    }

    @Test
    public void beregnAlkoholdsProcentLangTidTest() {
        // todo test if estimation of long time storage drops the alcohol percentage accordingly.
        // Basis data
        String kornsort = "Lars' korn";
        String destillering = "Destillering";
        String kommentar = "Test123";
        float mængde = 100;
        LocalDate baseDate = LocalDate.now().minusDays(1);
        Destillat destillat1 = new Destillat(UUID.randomUUID(), 1, kornsort, mængde, destillering, 48, baseDate, kommentar);
        Destillat destillat2 = new Destillat(UUID.randomUUID(), 1, kornsort, mængde, destillering, 40, baseDate, kommentar);
        Fyld fyld = new Fyld("Søren");

        fyld.addDestillat(destillat1, mængde);
        fyld.addDestillat(destillat2, mængde);

        Assertions.assertEquals(44, fyld.beregnAlkoholsProcent());

    }
}
