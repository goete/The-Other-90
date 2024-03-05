package persistence;

import model.*;

import java.io.IOException;


import org.json.*;

// Represents a reader that reads all the leaderboards from JSON data stored in file
// Citation for the majority of the class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReaderLeaderboards extends JsonReader {

    // EFFECTS: constructs reader to read from source file
    public JsonReaderLeaderboards(String source) {
        super(source);
    }

    // REQUIRES: source == "./data/Players.json"
    // EFFECTS: reads Leaderboard from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Leaderboards readLeaderboards() throws IOException {
        String jsonData = super.readFile();
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLeaderboards(jsonObject);
    }

    // EFFECTS: parses leaderboard from JSON object and returns it
    private Leaderboards parseLeaderboards(JSONObject jsonObject) {
        Leaderboards l = new Leaderboards();
        addPlayers(l, jsonObject);
        return l;
    }

    // MODIFIES: l
    // EFFECTS: parses player from JSON object and adds them to leaderboards
    private void addPlayers(Leaderboards l, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Players");
        for (Object json : jsonArray) {
            JSONObject playerJson = (JSONObject) json;
            addPlayerToAll(l, playerJson);
        }
    }

    // MODIFIES: l
    // EFFECTS: parses Player from JSON object, sets all the high scores and adds it to all leaderboards
    private void addPlayerToAll(Leaderboards l, JSONObject playerJson) {
        Player player = new Player(playerJson.getString("name"));

        player.setSumEliminationEasyHighScore(playerJson.getInt("Sum Elimination Easy"));
        player.setSumEliminationMediumHighScore(playerJson.getInt("Sum Elimination Medium"));
        player.setSumEliminationHardHighScore(playerJson.getInt("Sum Elimination Hard"));

        player.setWordRecollectionEasyHighScore(playerJson.getInt("Word Recollection Easy"));
        player.setWordRecollectionMediumHighScore(playerJson.getInt("Word Recollection Medium"));
        player.setWordRecollectionHardHighScore(playerJson.getInt("Word Recollection Hard"));

        l.addToAllLeaderboards(player);
    }
}
