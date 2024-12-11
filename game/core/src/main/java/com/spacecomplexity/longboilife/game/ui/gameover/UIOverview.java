package com.spacecomplexity.longboilife.game.ui.gameover;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.spacecomplexity.longboilife.game.achievements.IAchievement;
import com.spacecomplexity.longboilife.game.globals.GameState;
import com.spacecomplexity.longboilife.game.ui.UIElement;
import com.spacecomplexity.longboilife.game.utils.EventHandler;

/**
 * Class to represent the Overview UI after the game is completed.
 */
public class UIOverview extends UIElement {
    private Label achievementsLabel;

    /**
     * Initialise overview elements.
     *
     * @param uiViewport  the viewport used to render UI.
     * @param parentTable the table to render this element onto.
     * @param skin        the provided skin.
     */
    public UIOverview(Viewport uiViewport, Table parentTable, Skin skin) {
        super(uiViewport, parentTable, skin);

        String overview = String.format("Game Over\r\nSatisfaction Score: %.2f",
                GameState.getState().satisfactionScore * 100);

        // Initialise game over label
        Label label = new Label(overview, skin);
        label.setAlignment(Align.center);
        label.setFontScale(1.2f);
        label.setColor(Color.WHITE);

        // Initialise achievements unlocked label
        Label achievementsUnlockedLabel = new Label("Achievements Unlocked:", skin);
        achievementsUnlockedLabel.setAlignment(Align.center);
        achievementsUnlockedLabel.setFontScale(1.2f);
        achievementsUnlockedLabel.setColor(Color.WHITE);

        // Initialise list of unlocked achievements label
        String achievements = buildUnlockedAchievementsString();
        achievementsLabel = new Label(achievements, skin);
        achievementsLabel.setAlignment(Align.center);
        achievementsLabel.setFontScale(1.2f);
        achievementsLabel.setColor(Color.WHITE);
        achievementsLabel.setWrap(true);

        // Initialise name entry label
        Label givenNameLabel = new Label("Enter your name:", skin);
        givenNameLabel.setAlignment(Align.center);
        givenNameLabel.setFontScale(1.2f);
        givenNameLabel.setColor(Color.WHITE);

        // Initialise name entry
        TextField givenName = new TextField(System.getProperty("user.name"), skin);
        givenName.setMaxLength(20);
        givenName.setAlignment(Align.center);
        givenName.setColor(Color.WHITE);

        // Initialise update button
        TextButton updateName = new TextButton("Update Leaderboard", skin);
        updateName.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String enteredName = givenName.getText();
                System.out.println(enteredName);

            }
        });

        // Initialise button
        TextButton button = new TextButton("Menu", skin);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Call the events to return to the menu
                EventHandler.getEventHandler().callEvent(EventHandler.Event.RETURN_MENU);
            }
        });

        // Place elements onto table
        table.add(label).align(Align.center);
        table.row();
        table.add(givenNameLabel).align(Align.center).padTop(5);
        table.row();
        table.add(givenName).align(Align.center).padTop(5);
        table.row();
        table.add(updateName).align(Align.center).padTop(5);
        table.row();
        if (GameState.getState().unlockedAchievements.size() > 0) {
            table.add(achievementsUnlockedLabel).align(Align.center).padTop(5);
            table.row();
            table.add(achievementsLabel).align(Align.center).width(200);
            table.row();
        }
        table.add(button).padTop(5).align(Align.center);

        // Style and place the table
        table.setBackground(skin.getDrawable("panel1"));
        if (GameState.getState().unlockedAchievements.size() > 0) {
            table.setSize(220, 225 + GameState.getState().unlockedAchievements.size() * 35);
        } else {
            table.setSize(220, 175);
        }
        placeTable();
    }

    public void render() {
    }

    @Override
    protected void placeTable() {
        table.setPosition(0, (uiViewport.getWorldHeight() - table.getHeight()) / 2);
    }

    /**
     * Build a string of unlocked achievements using the achievement set from the
     * game state.
     *
     * @return the string of unlocked achievements.
     */
    protected String buildUnlockedAchievementsString() {
        StringBuilder achievements = new StringBuilder();
        for (IAchievement achievement : GameState.getState().unlockedAchievements) {
            achievements.append(achievement.getName()).append(" - ").append(achievement.getDescription())
                    .append("\r\n");
        }
        return achievements.toString();
    }
}
