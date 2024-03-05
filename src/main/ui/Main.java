package ui;

import model.Leaderboards;
import model.Player;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

public class Main {


    // creates and runs the game
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException, FileNotFoundException {
        //new TerminalGame();
        JsonWriter j = new JsonWriter("./data/Players.json");
        Player p = new Player("Leo");
        Leaderboards l = new Leaderboards();

        Player p2 = new Player("John");
        p2.setSumEliminationMediumHighScore(4);
        p.setWordRecollectionEasyHighScore(3);
        p.setWordRecollectionHardHighScore(6);
        l.addToAllLeaderboards(p);
        l.addToAllLeaderboards(p2);
        j.open();
        j.write(l);
        j.close();
    }
}
