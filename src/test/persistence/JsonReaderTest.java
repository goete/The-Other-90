package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    String correct;

    @BeforeEach
    void beforeEach() {
        correct = "{" +
                "  \"Players\": [" +
                "    {" +
                "      \"Sum Elimination Easy\": 0," +
                "      \"Word Recollection Hard\": 6," +
                "      \"Sum Elimination Medium\": 0," +
                "      \"Word Recollection Easy\": 3," +
                "      \"name\": \"Leo\"," +
                "      \"Sum Elimination Hard\": 0," +
                "      \"Word Recollection Medium\": 0" +
                "    }," +
                "    {" +
                "      \"Sum Elimination Easy\": 0," +
                "      \"Word Recollection Hard\": 0," +
                "      \"Sum Elimination Medium\": 4," +
                "      \"Word Recollection Easy\": 0," +
                "      \"name\": \"John\"," +
                "      \"Sum Elimination Hard\": 0," +
                "      \"Word Recollection Medium\": 0" +
                "    }" +
                "  ]" +
                "}";
    }

    @Test
    void testReaderFileDoesNotExist() {
        JsonReader reader = new JsonReaderLeaderboards("./data/nothingToSeeHere");
        try {
            String s = reader.readFile();
            fail("IOException expected");
        } catch (IOException e) {
            assertTrue(true);
        }
    }

    @Test
    void testReaderRealFile() {
        JsonReader reader = new JsonReaderLeaderboards("./data/PlayersTesting.json");
        try {
            String s = reader.readFile();
            assertEquals(correct, s);
        } catch (IOException e) {
            fail("Should not throw");
        }
    }
}
