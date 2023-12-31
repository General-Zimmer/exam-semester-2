package gui;

public interface OpretInterface {

    /**
     * Sørger for at de indtastede værdier i GUI er gyldige, og ellers får brugeren en advarsel.
     */
    void opretException();

    /**
     * Rydder alle tekstfelter, når der trykkes "Cancel" eller "OK"
     */
    void clearAllTextFields();


    /**
     * Lukker vinduet, når der trykkes "Cancel" eller "OK"
     */
    void opretVindueClose();

    /**
     * Opretter et objekt fra gui med de indtastede værdier, når der trykkes "OK"
     */
    void opretOK();

    /**
     * Pop-up alarm, når der skal kastes en exception under oprettelsen af et objekt.
     * @param title titlen på pop-up vinduet
     * @param besked besked til brugeren
     */
    void visAlert(String title, String besked);
}
