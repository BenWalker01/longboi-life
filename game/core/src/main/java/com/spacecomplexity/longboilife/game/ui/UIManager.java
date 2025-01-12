package com.spacecomplexity.longboilife.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.spacecomplexity.longboilife.game.achievements.IAchievement;
import com.spacecomplexity.longboilife.game.events.RandomEvents;
import com.spacecomplexity.longboilife.game.globals.Constants;
import com.spacecomplexity.longboilife.game.globals.GameState;
import com.spacecomplexity.longboilife.game.globals.MainTimer;
import com.spacecomplexity.longboilife.game.globals.Soundtrack;
import com.spacecomplexity.longboilife.game.ui.game.*;
import com.spacecomplexity.longboilife.game.ui.gameover.*;
import com.spacecomplexity.longboilife.game.utils.EventHandler;
import com.spacecomplexity.longboilife.game.globals.Filepaths;
import java.util.ArrayList;
import com.spacecomplexity.longboilife.game.utils.SoundEffect;

import java.util.Queue;

/**
 * Class to manage the UI in the game.
 */
public class UIManager {
    private Viewport viewport;

    private Stage stage;
    private final Skin skin;

    //NEW CHANGED FROM STATIC ARRRAY TO DYNAMIC ARRAY
    private ArrayList<UIElement> uiElements = new ArrayList<>();

    private long lastAchievementTime = 5*60*1000;

    /**
     * Initialise UI elements needed for the game.
     *
     * @param inputMultiplexer to add the UI events to the input processing
     */
    public UIManager(InputMultiplexer inputMultiplexer) {
        // Initialise viewport for rescaling
        // ASSESSMENT 2 - Moved default window size to Constants.DEFAULT_WINDOW_WIDTH and Constants.DEFAULT_WINDOW_HEIGHT
        viewport = new ScalingViewport(Scaling.fit, Constants.DEFAULT_WINDOW_WIDTH, Constants.DEFAULT_WINDOW_HEIGHT);

        // Initialise stage
        stage = new Stage(viewport);
        inputMultiplexer.addProcessor(stage);

        // Initialise root table
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Load external UI skin
        skin = new Skin(Gdx.files.internal(Filepaths.SKIN_JSON_ASSET));

        // Load external font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Filepaths.MEDIUM_FONT_ASSET));
        // Generate a bitmap font for size 12
        BitmapFont ourFont12 = generator.generateFont(new FreeTypeFontGenerator.FreeTypeFontParameter() {
            {
                size = 12;
            }
        });
        // Generate a bitmap font for size 14
        BitmapFont ourFont16 = generator.generateFont(new FreeTypeFontGenerator.FreeTypeFontParameter() {
            {
                size = 14;
            }
        });
        generator.dispose();

        // Set skins font to our font
        skin.get("default", Label.LabelStyle.class).font = ourFont12;
        skin.get("default", TextButton.TextButtonStyle.class).font = ourFont16;

        // Create our UI elements
        // Note: The order of these is the order that they will be rendered
        //NEW: CHANGED FROM STATIC ARRAY TO DYNAMIC
        uiElements.add(new UIBuildingSelectedMenu(viewport, table, skin));
        uiElements.add(new UIBottomMenu(viewport, table, skin));
        uiElements.add(new UIClockMenu(viewport, table, skin));
        uiElements.add(new UISatisfactionMenu(viewport, table, skin));
        uiElements.add(new UIMoneyMenu(viewport, table, skin));
        uiElements.add(new UIBuildingCounter(viewport, table, skin));
        uiElements.add(new UIAchievementPopUp(viewport, table, skin));
        uiElements.add(new UIMoneyPopUp(viewport, table, skin));
        //uiElements.add(new UIEventsMenu(viewport, table, skin));

        // Hide game UI and show end UI
        EventHandler.getEventHandler().createEvent(EventHandler.Event.GAME_END, (params) -> {
            GameState.getState().gameOver = true;
            EventHandler.getEventHandler().callEvent(EventHandler.Event.CANCEL_OPERATIONS);

            // Run dispose functions on UI elements
            for (UIElement uiElement : uiElements) {
                uiElement.dispose();
            }
            table.clear();

            // Create the new end elements
            // NEW USE DYANMIC ARRAY INSTEAD;
            uiElements = new ArrayList<>();

            uiElements.add(new UIOverview(viewport, table, skin));
            uiElements.add(new UILeaderboard(viewport, table, skin));

            // Pause the soundtrack and play the game over sound
            Soundtrack.getSoundtrack().pause();
            new SoundEffect(Filepaths.GAME_OVER_SOUND).play();

            return null;
        });
        
        /**
         * Adds random event to the event handler
         * 
         *
         * ASSESSMENT 2 - NEW
         */
        EventHandler.getEventHandler().createEvent(EventHandler.Event.RANDOM_EVENT, (params) -> {
            var event = RandomEvents.getRandomEvent();
            GameState.getState().paused = true;
            uiElements.add(new UIEventsMenu(viewport, table, skin, event));
            return null;
        });

    }


    /**
     * Apply and draw UI onto the screen.
     */
    public void render() {
        // Render on each of the UI elements
        for (UIElement uiElement : uiElements) {
            uiElement.render();
        }

        // Apply and then draw
        viewport.apply();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    /**
     * Handles resizing events, to ensure the UI is scaled correctly.
     *
     * @param width  the new width in pixels.
     * @param height the new height in pixels.
     */
    public void resize(int width, int height) {
        // Update world size to match scaling of uiScaleFactor
        viewport.setWorldSize(
                (float) width / GameState.getState().uiScaleFactor,
                (float) height / GameState.getState().uiScaleFactor);

        // Updates viewport to match new window size
        viewport.update(width, height, true);

        // Run resize functions on UI elements
        for (UIElement uiElement : uiElements) {
            uiElement.resize();
        }
    }

    /**
     * Show the achievement pop up.
     * <p>
     *     If the last achievement was shown less than 5 seconds ago, don't show another.
     *     If the queue is empty, don't show anything.
     *     Otherwise, show the next achievement in the queue.
     *     The achievement will be shown for 5 seconds.
     * </p>
     *
     * ASSESSMENT 2 - New method
     *
     * @param achievementQueue the queue of achievements to show.
     */
    public void showAchievement(Queue<IAchievement> achievementQueue) {
        long currentTime = MainTimer.getTimerManager().getTimer().getTimeLeft();
        if (lastAchievementTime - currentTime < 5000) {
            return;
        }
        ((UIAchievementPopUp) uiElements.get(6)).hideAchievement();
        if (achievementQueue.isEmpty()) {
            return;
        }
        lastAchievementTime = currentTime;
        IAchievement achievement = achievementQueue.poll();
        ((UIAchievementPopUp) uiElements.get(6)).showAchievement(achievement.getName(), achievement.getDescription());
    }

    /**
     * Show the money pop up.
     *
     * @param moneyChange the number by which the balance is increased/decreased.
     */
    public void showMoneyPopUp(int moneyChange) {
        if (!GameState.getState().gameOver)
            ((UIMoneyPopUp) uiElements.get(7)).show(moneyChange);
    }

    /**
     * Dispose of all loaded assets.
     */
    public void dispose() {
        stage.dispose();
        skin.dispose();

        // Run dispose functions on UI elements
        for (UIElement uiElement : uiElements) {
            uiElement.dispose();
        }
    }
}
