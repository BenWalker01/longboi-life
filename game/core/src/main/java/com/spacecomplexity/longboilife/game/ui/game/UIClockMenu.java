package com.spacecomplexity.longboilife.game.ui.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.spacecomplexity.longboilife.game.globals.MainTimer;
import com.spacecomplexity.longboilife.game.ui.UIElement;
import com.spacecomplexity.longboilife.game.utils.EventHandler;

/**
 * Class to represent the Clock UI.
 */
public class UIClockMenu extends UIElement {
    private TextButton timerLabel;

    private final MainTimer mainTimer = MainTimer.getTimerManager();

    /**
     * Initialise clock menu elements.
     *
     * @param uiViewport  the viewport used to render UI.
     * @param parentTable the table to render this element onto.
     * @param skin        the provided skin.
     */
    public UIClockMenu(Viewport uiViewport, Table parentTable, Skin skin) {
        super(uiViewport, parentTable, skin);

        // Initialise time label
        timerLabel = new TextButton("5:00", skin);

        // Initialise button
        timerLabel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Call the events to return to the menu
                EventHandler.getEventHandler().callEvent(EventHandler.Event.TIMER_CLICK);
            }
        });

        // Place label onto table
        table.add(timerLabel).align(Align.center);

        // Style and place the table
        table.setBackground(skin.getDrawable("panel1"));
        table.setSize(75, 50);
        placeTable();
    }

    public void render() {
        setTime(mainTimer.getTimer().getTimeLeft() / 1000);
    }

    /**
     * Set the labels on the clock menu to the time given.
     *
     * @param time the time in seconds
     */
    private void setTime(long time) {
        // Format this onto the time label
        timerLabel.setText(String.format("%d:%02d", time / 60, time % 60));
    }

    @Override
    protected void placeTable() {
        table.setPosition(0, uiViewport.getWorldHeight() - table.getHeight());
    }
}
