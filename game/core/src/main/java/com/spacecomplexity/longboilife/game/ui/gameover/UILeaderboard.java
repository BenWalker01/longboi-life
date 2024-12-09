package com.spacecomplexity.longboilife.game.ui.gameover;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.spacecomplexity.longboilife.game.ui.UIElement;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import com.spacecomplexity.longboilife.game.globals.Filepaths;
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

        String username = System.getProperty("user.name");
        String allScores = "----------Leaderboard----------\n";
        TreeSet<String> sortedNames = new TreeSet<String>();
        TreeSet<String> sortedScores = new TreeSet<String>();

        // Read in names and scores from the leaderboard
        // try {
        //   BufferedReader leaderboardReader = new BufferedReader(new FileReader(Filepaths.LEADERBOARD_DATA));
        //   while ((line = leaderboardReader.readLine()) != null) {  
        //     String[] entry = line.split(",");
        //     // Save old score if user has already played the game
        //     if (entry[0] == username) {
        //       prevScore = entry[1];
        //     }
        //     sortedNames.add(entry[0]);
        //     sortedScores.add(entry[1]);
        //   }
        //   leaderboardReader.close();
        // } catch (IOException e) {
        //   // TODO: Check for file as part of testing
        //   allScores += "MISSING FILE - READ";
        // };


        // Write new score to leaderboard
        try {
          BufferedWriter leaderboardWriter = new BufferedWriter(new FileWriter(Filepaths.LEADERBOARD_DATA, true));
          String score = String.format("%.2f", GameState.getState().satisfactionScore * 100);
          if (username == currentUser && score.compareTo(prevScore) == 1) {
            leaderboardWriter.append("\n" + username + "," + score);
            leaderboardWriter.close();
            sortedNames.add(username);
            sortedScores.add(score);
          }
        } catch (IOException e) {
          // TODO: Check for file as part of testing
          allScores += "MISSING FILE - WRITE";
        };

        // Save sorted names and scores
        List<String> nameList = new ArrayList<String>(sortedNames);
        List<String> scoreList = new ArrayList<String>(sortedScores);
        // Reverse lists to display in descending order
        Collections.reverse(nameList);
        Collections.reverse(scoreList);
        for (int i = 0; i < nameList.size(); i ++) {
          allScores += (i + 1) + "." + nameList.get(i) + "    " + scoreList.get(i) + "\n";
        }

        // LeaderboardPrefs.setName(username);
        // LeaderboardPrefs.setScore(prevScore);
        System.out.println(LeaderboardPrefs.getNames());
        System.out.println(LeaderboardPrefs.getScores());
        
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
        if (nameList.size() > 3) {
            table.setSize(220, 100 + (nameList.size()-3)*15);
        }
        else {
            table.setSize(220, 100);
        }
        placeTable();
    }

    public void render() {
    }

    @Override
    protected void placeTable() {
        table.setPosition((uiViewport.getWorldWidth() - table.getWidth()) / 2, uiViewport.getWorldHeight() / 2 - table.getHeight() / 2);
    }
}
