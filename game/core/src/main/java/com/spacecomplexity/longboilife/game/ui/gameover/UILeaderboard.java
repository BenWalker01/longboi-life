package com.spacecomplexity.longboilife.game.ui.gameover;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.spacecomplexity.longboilife.game.ui.UIElement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import com.spacecomplexity.longboilife.game.globals.Filepaths;
import com.spacecomplexity.longboilife.game.globals.GameState;

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

        Integer index = 1;
        String line = "";
        String allScores = "----------Leaderboard----------\n";

        // Read in names and scores from the leaderboard
        try {
          BufferedReader leaderboardReader = new BufferedReader(new FileReader(Filepaths.LEADERBOARD_DATA));
          while ((line = leaderboardReader.readLine()) != null) {  
            String[] entry = line.split(",");
            allScores += index + "." + entry[0] + "    " + entry[1] + "\n";
            index += 1;
          }
          leaderboardReader.close();
        } catch (IOException e) {
          // TODO: Check for file as part of testing
          allScores += "MISSING FILE - READ";
        };

        // Write new score to leaderboard
        try {
          BufferedWriter leaderboardWriter = new BufferedWriter(new FileWriter(Filepaths.LEADERBOARD_DATA, true));
          String username = System.getProperty("user.name");
          String score = String.format("%.2f", GameState.getState().satisfactionScore * 100);
          leaderboardWriter.append("\n" + username + "," + score);
          leaderboardWriter.close();
          allScores += index + "." + username + "    " + score;
        } catch (IOException e) {
          // TODO: Check for file as part of testing
          allScores += "MISSING FILE - WRITE";
        };
        
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
        if (index > 3) {
            table.setSize(220, 100 + (index-3)*15);
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
