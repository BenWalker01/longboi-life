package com.spacecomplexity.longboilife.game.ui.gameover;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.spacecomplexity.longboilife.game.ui.UIElement;

import java.util.*;

import com.spacecomplexity.longboilife.game.globals.GameState;
import com.spacecomplexity.longboilife.game.globals.LeaderboardPrefs;

/**
 * Class to represent the Overview UI after the game is completed.
 */
public class UILeaderboard extends UIElement {
    /**
     * Initialise overview elements.
     *
     * @param uiViewport  the viewport used to render UI.
     * @param parentTable the table to render this element onto.
     * @param skin        the provided skin.
     */
    public UILeaderboard(Viewport uiViewport, Table parentTable, Skin skin) {
        super(uiViewport, parentTable, skin);

        String score = String.format(Locale.UK, "%.2f", GameState.getState().satisfactionScore * 100);

        String username = LeaderboardPrefs.displayName;
        String allScores = "----------Leaderboard----------\n";

        String names = LeaderboardPrefs.getNames();
        String scores = LeaderboardPrefs.getScores();

        HashMap<String, Float> leaderboardHash = updateScores(names, scores, username, score);

        List<Map.Entry<String, Float>> leaderboardList = sortLeaderboardEntries(leaderboardHash);

        Map<String, Float> sortedLeaderboard = generateLeaderboardText(allScores, leaderboardList);

        // Initialise leaderboard
        Label label = new Label(String.format(allScores), skin);
        label.setAlignment(Align.center);
        label.setFontScale(1.2f);
        label.setColor(Color.WHITE);

        // Place leaderboard onto table
        table.add(label).align(Align.center);

        // Style and place the table
        table.setBackground(skin.getDrawable("panel1"));
        // Update height based on number of entries to display - minimum 4
        if (sortedLeaderboard.size() > 3) {
            table.setSize(220, 100 + (sortedLeaderboard.size() - 3) * 15);
        } else {
            table.setSize(220, 100);
        }
        placeTable();
    }

    /**
     * Adds the users latest score to the leaderboard if its higher than the current
     * score
     * 
     * @param names    current names on the leaderboard
     * @param scores   current scores on the leaderboard
     * @param username username of current player
     * @param score    score of current player
     * @return Hashmap representation of the leaderboard
     */
    public static HashMap<String, Float> updateScores(String names, String scores, String username, String score) {
        String[] namesList = names.split("[,]");
        String[] scoresList = scores.split("[,]");
        Boolean userCheck = false;
        HashMap<String, Float> leaderboardHash = new HashMap<String, Float>();

        for (int i = 0; i < namesList.length; i++) {
            // Update score if user is already on leaderboard and has a higher score
            if (namesList[i].equals(username)) {
                if (Float.valueOf(score) >= Float.valueOf(scoresList[i])) {
                    leaderboardHash.put(username, Float.valueOf(score));
                    scoresList[i] = score;
                    scores = "";
                    for (int j = 0; j < scoresList.length; j++) {
                        scores += scoresList[j] + ",";
                    }
                    LeaderboardPrefs.writeScores(scores);
                } else {
                    leaderboardHash.put(username, Float.valueOf(scoresList[i]));
                }
                userCheck = true;
            } else {
                leaderboardHash.put(namesList[i], Float.valueOf(scoresList[i]));
            }
        }
        // Set new name and score if user not on leaderboard
        if (userCheck == false) {
            leaderboardHash.put(username, Float.valueOf(score));
            LeaderboardPrefs.writeNames(names + "," + username);
            LeaderboardPrefs.writeScores(scores + "," + score);
        }

        return leaderboardHash;
    }

    /**
     * Sorts the leaderboard in descending order (Highest score at the top)
     * 
     * @param leaderboard hashmap representation of the current state of the
     *                    leaderboard
     * @return sorted leaderboard
     */
    public static List<Map.Entry<String, Float>> sortLeaderboardEntries(HashMap<String, Float> leaderboard) {
        // Sort leaderboard by scores
        List<Map.Entry<String, Float>> leaderboardList = new LinkedList<Map.Entry<String, Float>>(
                leaderboard.entrySet());

        Collections.sort(leaderboardList, new Comparator<Map.Entry<String, Float>>() {
            public int compare(Map.Entry<String, Float> o1,
                    Map.Entry<String, Float> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // Sorted leaderboard list in descending order
        Collections.reverse(leaderboardList);

        return leaderboardList;

    }

    /**
     * Updates the text of the leaderboard, and returns the a Map of the leaderboard
     * 
     * @param allScores       current state of the leaderboard
     * @param leaderboardList list of leaderboard entries
     * @return
     */
    public static Map<String, Float> generateLeaderboardText(String allScores,
            List<Map.Entry<String, Float>> leaderboardList) {
        // Create sorted leaderboard hash map
        Map<String, Float> sortedLeaderboard = new LinkedHashMap<String, Float>();
        for (Map.Entry<String, Float> entry : leaderboardList) {
            sortedLeaderboard.put(entry.getKey(), entry.getValue());
        }

        // Add names and scores to the leaderboard display
        Integer index = 1;
        for (Map.Entry<String, Float> entry : sortedLeaderboard.entrySet()) {
            allScores += index + "." + entry.getKey() + "    " + entry.getValue() + "\n";
            index += 1;
        }

        return sortedLeaderboard;

    }

    public void render() {
    }

    @Override
    protected void placeTable() {
        table.setPosition((uiViewport.getWorldWidth() - table.getWidth()),
                uiViewport.getWorldHeight() / 2 - table.getHeight() / 2);
    }
}
