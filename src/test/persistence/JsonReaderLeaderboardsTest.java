package persistence;

import model.Leaderboards;
import model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderLeaderboardsTest {
    Leaderboards l;
    Player p;
    Player p2;
    JsonReaderLeaderboards reader;

    @BeforeEach
    void before() {
        reader = new JsonReaderLeaderboards("./data/PlayersTesting.json");
        p = new Player("Leo");
        l = new Leaderboards();

        p2 = new Player("John");
        p2.setSumEliminationMediumHighScore(4);
        p.setWordRecollectionEasyHighScore(3);
        p.setWordRecollectionHardHighScore(6);
        l.addToAllLeaderboards(p);
        l.addToAllLeaderboards(p2);
    }

    @Test
    void readingLeaderboardsTest() throws InvocationTargetException, IllegalAccessException {
        try {
            Leaderboards readInLeaderboards = reader.readLeaderboards();
            assertEquals(l.getAllPlayers().size(), readInLeaderboards.getAllPlayers().size());
            assertEquals(4, readInLeaderboards.getSumEliminationMedium().getTopNPlayers(1)
                    .get(0).getSumEliminationMediumHighScore());
            assertEquals(6, readInLeaderboards.getWordRecollectionHard().getTopNPlayers(1)
                    .get(0).getWordRecollectionHardHighScore());
        } catch (IOException e) {
            fail("Should not throw");
        }

    }
}
