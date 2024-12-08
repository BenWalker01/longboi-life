package com.spacecomplexity.longboilife.game.ui.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.spacecomplexity.longboilife.game.ui.UIElement;

/**
 * Class to represent the Achievement Pop Up UI element.
 *
 * ASSESSMENT 2 - New feature
 */
public class UIAchievementPopUp extends UIElement {
    private Label achievementNameLabel;
    private Label achievementDescriptionLabel;

    /**
     * Initialise the Achievement Pop Up UI element.
     *
     * @param uiViewport  the viewport used to render UI.
     * @param parentTable the table to render this element onto.
     * @param skin        the provided skin.
     */
    public UIAchievementPopUp(Viewport uiViewport, Table parentTable, Skin skin) {
        super(uiViewport, parentTable, skin);

        // Initialise Achievement Unlocked label
        Label achievementUnlockedLabel = new Label("Achievement Unlocked!", skin);
        achievementUnlockedLabel.setFontScale(1.2f);
        achievementUnlockedLabel.setColor(Color.WHITE);
        achievementUnlockedLabel.setAlignment(Align.center);

        // Initialise Achievement Name label
        achievementNameLabel = new Label("", skin);
        achievementNameLabel.setFontScale(1.2f);
        achievementNameLabel.setColor(Color.WHITE);
        achievementNameLabel.setAlignment(Align.center);

        // Initialise Achievement Description label
        achievementDescriptionLabel = new Label("", skin);
        achievementDescriptionLabel.setFontScale(1.2f);
        achievementDescriptionLabel.setColor(Color.WHITE);
        achievementDescriptionLabel.setAlignment(Align.center);
        achievementDescriptionLabel.setWrap(true);

        // Place labels onto table
        table.add(achievementUnlockedLabel).align(Align.center);
        table.row();
        table.add(achievementNameLabel).align(Align.center);
        table.row();
        table.add(achievementDescriptionLabel).align(Align.center).width(180);

        // Hide the table initially
        table.setVisible(false);

        // Style and place the table
        table.setBackground(skin.getDrawable("panel1"));
        table.setSize(200, 100);
        placeTable();
    }

    @Override
    public void render() {

    }

    /**
     * Show the achievement pop up with the given achievement name and description.
     *
     * @param achievementName
     * @param achievementDescription
     */
    public void showAchievement(String achievementName, String achievementDescription) {
        achievementNameLabel.setText(achievementName);

        achievementDescriptionLabel.setText(achievementDescription);

        table.setVisible(true);
    }

    /**
     * Hide the achievement pop up.
     */
    public void hideAchievement() {
        table.setVisible(false);
    }

    @Override
    protected void placeTable() {
        table.setPosition((uiViewport.getWorldWidth() - table.getWidth()), (uiViewport.getWorldHeight() - table.getHeight()) / 2);
    }
}
