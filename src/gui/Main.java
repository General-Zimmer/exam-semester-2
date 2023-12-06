package gui;
import controller.Controller;

import javafx.application.Application;
import storage.Storage;

import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        Controller.setStorage(new Storage());
        Application.launch(Gui.class);
    }
}
