package com.spacecomplexity.longboilife.game.ui.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.spacecomplexity.longboilife.game.globals.GameState;
import com.spacecomplexity.longboilife.game.ui.UIElement;

/**
 * Class to represent the Satisfaction Score UI.
 * Updated for Assessment 2
 */
public class UISatisfactionMenu extends UIElement {
    private Label scoreLabel;

    /**
     * Initialise satisfaction menu elements.
     *
     * @param uiViewport  the viewport used to render UI.
     * @param parentTable the table to render this element onto.
     * @param skin        the provided skin.
     */
    public UISatisfactionMenu(Viewport uiViewport, Table parentTable, Skin skin) {
        super(uiViewport, parentTable, skin);

        // Initialise label
        Label label = new Label("Satisfaction Score:", skin);
        label.setFontScale(1f);
        label.setColor(Color.WHITE);

        // Initialise score label
        scoreLabel = new Label(null, skin);
        scoreLabel.setFontScale(1f);

        // Place elements onto table
        Table labelTable = new Table(skin);
        labelTable.add(label);
        labelTable.add(scoreLabel).padLeft(5);
        table.add(labelTable);
        table.row();

        // Style and place the table
        table.setBackground(skin.getDrawable("panel1"));
        table.setSize(185, 60);
        placeTable();
    }

    public void render() {
        scoreLabel.setText(String.format("%.2f", GameState.getState().satisfactionScore * 100));
        scoreLabel.setColor(GameState.getState().satisfactionModifierPositive ? Color.GREEN : Color.RED);
    }

    @Override
    protected void placeTable() {
        table.setPosition(uiViewport.getWorldWidth() - table.getWidth(),
                uiViewport.getWorldHeight() - table.getHeight());
    }
}
