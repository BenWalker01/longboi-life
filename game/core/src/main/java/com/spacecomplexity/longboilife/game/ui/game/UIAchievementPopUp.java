package com.spacecomplexity.longboilife.game.ui.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.spacecomplexity.longboilife.game.ui.UIElement;

public class UIAchievementPopUp extends UIElement {
    private Label achievementNameLabel;
    private Label achievementDescriptionLabel;

    public UIAchievementPopUp(Viewport uiViewport, Table parentTable, Skin skin) {
        super(uiViewport, parentTable, skin);
        Label achievementUnlockedLabel = new Label("Achievement Unlocked!", skin);
        achievementUnlockedLabel.setFontScale(1.2f);
        achievementUnlockedLabel.setColor(Color.WHITE);
        achievementUnlockedLabel.setAlignment(Align.center);

        achievementNameLabel = new Label("", skin);
        achievementNameLabel.setFontScale(1.2f);
        achievementNameLabel.setColor(Color.WHITE);
        achievementNameLabel.setAlignment(Align.center);

        achievementDescriptionLabel = new Label("", skin);
        achievementDescriptionLabel.setFontScale(1.2f);
        achievementDescriptionLabel.setColor(Color.WHITE);
        achievementDescriptionLabel.setAlignment(Align.center);
        achievementDescriptionLabel.setWrap(true);

        table.add(achievementUnlockedLabel).align(Align.center);
        table.row();
        table.add(achievementNameLabel).align(Align.center);
        table.row();
        table.add(achievementDescriptionLabel).align(Align.center).width(180);
        table.setVisible(false);
        table.setBackground(skin.getDrawable("panel1"));
        table.setSize(200, 100);
        placeTable();
    }

    @Override
    public void render() {

    }

    public void showAchievement(String achievementName, String achievementDescription) {
        achievementNameLabel.setText(achievementName);

        achievementDescriptionLabel.setText(achievementDescription);

        table.setVisible(true);
    }

    public void hideAchievement() {
        table.setVisible(false);
    }

    @Override
    protected void placeTable() {
        table.setPosition((uiViewport.getWorldWidth() - table.getWidth()), (uiViewport.getWorldHeight() - table.getHeight()) / 2);
    }
}
