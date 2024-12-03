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
import java.util.Scanner;
import com.spacecomplexity.longboilife.game.globals.Filepaths;

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

        float index = 1.0f;
        String allScores = "----------Leaderboard----------\n";

        try {
          // Read in names and scores from the leaderboard
          Scanner LeaderboardReader = new Scanner(new File(Filepaths.LEADERBOARD_DATA));
          LeaderboardReader.useDelimiter(",");
          while (LeaderboardReader.hasNext()) {  
            System.out.print(LeaderboardReader.next());  //find and returns the next complete token from this scanner  
          }   
          LeaderboardReader.close();
        } catch (FileNotFoundException e) {
          // TODO: Check for file as part of testing
          allScores += "MISSING FILE";
        }

        // try {
        //     File LeaderboardObj = new File(Filepaths.LEADERBOARD_DATA);
        //     Scanner LeaderboardReader = new Scanner(LeaderboardObj);
        //     // Read each line of the text file, adding names and scores based on index
        //     while (LeaderboardReader.hasNextLine()) {
        //       String data = LeaderboardReader.nextLine();
        //       if (index % 1 == 0) {
        //         allScores += (int) index + ". " + data;
        //       }
        //       else {
        //         allScores += "   " + data + "\n";
        //       }
        //       index += 0.5f;
        //     }
        //     LeaderboardReader.close();
        //   } catch (FileNotFoundException e) {
        //     // TODO: Check for file as part of testing
        //     allScores += "MISSING FILE";
        //   }
        
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
