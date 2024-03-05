package persistence;

import model.Leaderboard;
import model.Leaderboards;
import model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    private Leaderboards l;
    private Player p;
    private Player p2;
    private JsonReaderLeaderboards reader;
    private JsonReaderLeaderboards readerTesting;

    @BeforeEach
    void before() {
        reader = new JsonReaderLeaderboards("./data/PlayersTesting.json");
        readerTesting = new JsonReaderLeaderboards("./data/PlayersTestingEmpty.json");
        p = new Player("Leo");
        l = new Leaderboards();

        p2 = new Player("John");
        p2.setSumEliminationMediumHighScore(4);
        p.setWordRecollectionEasyHighScore(3);
        p.setWordRecollectionHardHighScore(6);
        l.addToAllLeaderboards(p);
        l.addToAllLeaderboards(p2);
    }

    // Citation for the file name: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            writer.write(l);
            writer.close();
            fail("IOException was expected");
        } catch (IOException e) {
            assertTrue(true);
        }
    }

    @Test
    void testWriterFilledInLeaderboard() {
        try {
            JsonWriter writer = new JsonWriter("./data/PlayersTestingEmpty.json");
            writer.open();
            writer.write(l);
            writer.close();
            Leaderboards hold = reader.readLeaderboards();
            assertEquals(l.getAllPlayers().size(), hold.getAllPlayers().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
