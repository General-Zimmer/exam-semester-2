package tests;

import controller.Controller;
import gui.Gui;
import model.Destillat;
import model.Lager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledForJreRange;
import storage.IStorage;
import storage.Storage;
import testmodels.TestStorage;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.UUID;

public class ControllerTest {

    private TestStorage storage;

    @BeforeEach
    public void setUp() {
        storage = new TestStorage();
        Controller.setStorage(storage);
        new Gui();
    }

    @Test
    public void TotallyLegitTest() {
        Assertions.assertEquals(1, 1);
    }

    @Test
    public void createLagerTest() {

        // baseData
        String addresse = "Sønderhøj 30, 8260 Viby";


        // TestCase 1

        // Arrange
        int reoler = 9;
        int reolKapacitet = 1;

        // Act
        Lager lager = Controller.createLager(addresse, reoler, reolKapacitet);
        UUID uuid = lager.getID();

        // Assert
        Lager tempLager = getLager(1);
            Assertions.assertEquals(addresse, tempLager.getAddresse());
            Assertions.assertEquals(lager, tempLager);
            Assertions.assertEquals(new Lager(addresse, uuid, 9, 1), tempLager);

        // TestCase 2

        // Arrange
        reoler = 0;
        reolKapacitet = 20;

        // Act
        lager = Controller.createLager(addresse, reoler, reolKapacitet);
        uuid = lager.getID();

        // Assert
        tempLager = getLager(2);
        Assertions.assertEquals(addresse, tempLager.getAddresse());
        Assertions.assertEquals(lager, tempLager);
        Assertions.assertEquals(new Lager(addresse, uuid, 0, 20), tempLager);


        // TestCase 3

        // Arrange
        reoler = 5;
        reolKapacitet = 0;

        // Act
        lager = Controller.createLager(addresse, reoler, reolKapacitet);
        uuid = lager.getID();

        // Assert
        tempLager = getLager(3);
        Assertions.assertEquals(addresse, tempLager.getAddresse());
        Assertions.assertEquals(lager, tempLager);
        Assertions.assertEquals(new Lager(addresse, uuid, 5, 0), tempLager);


        // TestCase 4

        // Arrange
        reoler = 0;
        reolKapacitet = 0;

        // Act
        lager = Controller.createLager(addresse, reoler, reolKapacitet);
        uuid = lager.getID();

        // Assert
        tempLager = getLager(4);
        Assertions.assertEquals(addresse, tempLager.getAddresse());
        Assertions.assertEquals(lager, tempLager);
        Assertions.assertEquals(new Lager(addresse, uuid, 0, 0), tempLager);


    }
    @Test
    public void createDestillatTest() {
        // Basis data
        String kornsort = "Lars' korn";
        String destillering = "Destillering";
        String kommentar = "Test123";
        LocalDate baseDate = LocalDate.of(2023, 11, 29);

        // TestCase 1

        // Arrange
        int maltBatch = 1;
        float mængde = 200;
        float alkoholProcent = 48;

        // Act
        Destillat destillat = Controller.createDestillat(maltBatch, kornsort, mængde, alkoholProcent, destillering, baseDate, kommentar);
        UUID uuid = destillat.getID();

        // Assert
        Destillat tempDestillat = getDestillat(1);
        Assertions.assertEquals(kornsort, tempDestillat.getKornsort());
        Assertions.assertEquals(destillat, tempDestillat);
        Assertions.assertEquals(new Destillat(uuid, maltBatch, kornsort, mængde, destillering, alkoholProcent, baseDate, kommentar), tempDestillat);

        // TestCase 2

        // Arrange
        maltBatch = 0;
        mængde = 200;
        alkoholProcent = 48;

        // Act
        destillat = Controller.createDestillat(maltBatch, kornsort, mængde, alkoholProcent, destillering, baseDate, kommentar);
        uuid = destillat.getID();

        // Assert
        tempDestillat = getDestillat(2);
            Assertions.assertEquals(kornsort, tempDestillat.getKornsort());
            Assertions.assertEquals(destillat, tempDestillat);
            Assertions.assertEquals(new Destillat(uuid, maltBatch, kornsort, mængde, destillering, alkoholProcent, baseDate, kommentar), tempDestillat);

        // TestCase 3

        // Arrange
        maltBatch = 1;
        mængde = 0;
        alkoholProcent = 48;

        // Act
        destillat = Controller.createDestillat(maltBatch, kornsort, mængde, alkoholProcent, destillering, baseDate, kommentar);
        uuid = destillat.getID();

        // Assert
        tempDestillat = getDestillat(3);
            Assertions.assertEquals(kornsort, tempDestillat.getKornsort());
            Assertions.assertEquals(destillat, tempDestillat);
            Assertions.assertEquals(new Destillat(uuid, maltBatch, kornsort, mængde, destillering, alkoholProcent, baseDate, kommentar), tempDestillat);

        // TestCase 4

        // Arrange
        maltBatch = 1;
        mængde = 200;
        alkoholProcent = -1;

        final int maltBatch1 = maltBatch;
        final float mængde1 = mængde;
        final float alkoholProcent1 = alkoholProcent;


        // Act
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Controller.createDestillat(maltBatch1, kornsort, mængde1, alkoholProcent1, destillering, baseDate, kommentar);
        });
        
    }

    @AfterEach
    public void tearDown() {
    }

    public Lager getLager(int index) {
        Iterator<Lager> iterator = storage.getLagre().iterator();
        Lager lager = null;

        for (int i = 0; i < index; i++) {
            lager = iterator.next();
        }

        return lager;
    }


    public Destillat getDestillat(int index) {
        Iterator<Destillat> iterator = storage.getDestillater().iterator();
        Destillat destillat = null;

        for (int i = 0; i < index; i++) {
            destillat = iterator.next();
        }

        return destillat;
    }

}