package com.spacecomplexity.longboilife.game.ui.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.spacecomplexity.longboilife.game.globals.GameState;
import com.spacecomplexity.longboilife.game.globals.MainTimer;
import com.spacecomplexity.longboilife.game.ui.UIElement;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Class to represent the Money Change Pop Up UI element.
 *
 * ASSESSMENT 2 - New feature
 */
public class UIMoneyPopUp extends UIElement {
    private Label label;

    private long showTime = 5*60*1000;

    /**
     * Initialise money menu elements.
     *
     * @param uiViewport  the viewport used to render UI.
     * @param parentTable the table to render this element onto.
     * @param skin        the provided skin.
     */
    public UIMoneyPopUp(Viewport uiViewport, Table parentTable, Skin skin) {
        super(uiViewport, parentTable, skin);

        // Initialise money label
        label = new Label(null, skin);
        label.setFontScale(1.2f);
        label.setColor(Color.WHITE);

        // Place label onto table
        table.add(label).align(Align.center);

        // Hide the table initially
        table.setVisible(false);

        // Style and place the table
        table.setBackground(skin.getDrawable("panel1"));
        table.setSize(120, 40);
        placeTable();
    }

    public void render() {
        long currentTime = MainTimer.getTimerManager().getTimer().getTimeLeft();
        if (showTime - currentTime > 5000) {
            hide();
        }
    }

    @Override
    protected void placeTable() {
        table.setPosition(uiViewport.getWorldWidth() - table.getWidth(), uiViewport.getWorldHeight() - table.getHeight() - 100);
    }

    /**
     * Show the money change pop up.
     *
     * @param moneyChange the number by which the balance is increased/decreased.
     */
    public void show(int moneyChange) {
        if (moneyChange == 0) {
            return;
        }
        table.setVisible(true);
        showTime = MainTimer.getTimerManager().getTimer().getTimeLeft();
        if (moneyChange > 0) {
            label.setColor(Color.GREEN);
            label.setText("+" + NumberFormat.getCurrencyInstance(Locale.UK).format(moneyChange));
        } else {
            label.setColor(Color.RED);
            label.setText(NumberFormat.getCurrencyInstance(Locale.UK).format(moneyChange));
        }
    }

    /**
     * Hide the money change pop up.
     */
    public void hide() {
        table.setVisible(false);
    }
}
