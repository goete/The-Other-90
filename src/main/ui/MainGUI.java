package ui;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

public class MainGUI {


    // creates and runs the game
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException, FileNotFoundException {
        new GameGUI();
    }
}