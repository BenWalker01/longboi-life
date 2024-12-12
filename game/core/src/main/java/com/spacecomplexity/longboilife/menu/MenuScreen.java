package com.spacecomplexity.longboilife.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.spacecomplexity.longboilife.Main;
import com.spacecomplexity.longboilife.MainInputManager;
import com.spacecomplexity.longboilife.game.globals.Constants;
import com.spacecomplexity.longboilife.game.globals.Filepaths;
import com.spacecomplexity.longboilife.game.globals.LeaderboardPrefs;;

/**
 * Main class to control the menu screen.
 */
public class MenuScreen implements Screen {
    private final Main game;

    private Viewport viewport;

    private Texture backgroundTexture;
    private SpriteBatch batch;

    private Stage stage;
    private Skin skin;

    public MenuScreen(Main game) {
        this.game = game;

        // Initialise viewport and drawing elements
        // ASSESSMENT 2 - Moved default window size to Constants.DEFAULT_WINDOW_WIDTH
        // and Constants.DEFAULT_WINDOW_HEIGHT
        viewport = new FitViewport(Constants.DEFAULT_WINDOW_WIDTH, Constants.DEFAULT_WINDOW_HEIGHT);
        stage = new Stage(viewport);
        batch = new SpriteBatch();

        // Load background texture
        backgroundTexture = new Texture(Gdx.files.internal(Filepaths.MENU_BG_ASSET));

        // Load UI skin for buttons
        skin = new Skin(Gdx.files.internal(Filepaths.SKIN_JSON_ASSET));
    }

    @Override
    public void show() {
        // Table layout for menu alignment
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Initialise play button
        TextButton playButton = new TextButton("Play", skin, "round");
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Switch to game screen
                // game.switchScreen(Main.ScreenType.GAME);
                startGame();
                playButton.remove();
            }
        });

        // Initialise exit button
        TextButton exitButton = new TextButton("Exit", skin, "round");
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Exit the application
                Gdx.app.exit();
            }
        });

        // Add buttons to table
        table.pad(100).right().bottom();
        table.add(playButton);
        table.row();
        table.add(exitButton).padTop(10);

        // Allows UI to capture touch events
        InputMultiplexer inputMultiplexer = new InputMultiplexer(new MainInputManager(), stage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private void startGame() {
        Table table = new Table();
        table.setFillParent(false);
        stage.addActor(table);

        // Initialise name entry label
        Label givenNameLabel = new Label("Enter your name:", skin);
        givenNameLabel.setAlignment(Align.center);
        givenNameLabel.setFontScale(1.2f);
        givenNameLabel.setColor(Color.WHITE);

        // Initialise name entry
        TextField givenName = new TextField(LeaderboardPrefs.displayName, skin);
        givenName.setMaxLength(20);
        givenName.setAlignment(Align.center);
        givenName.setColor(Color.WHITE);

        TextButton playButton = new TextButton("Play", skin, "round");
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String enteredName = givenName.getText();
                LeaderboardPrefs.setName(enteredName);
                // Switch to game screen
                game.switchScreen(Main.ScreenType.GAME);
            }
        });

        table.add(givenNameLabel).align(Align.center).padTop(5);
        table.row();
        table.add(givenName).align(Align.center).pad(5);
        table.row();
        table.add(playButton);

        // Set style and place table
        table.setBackground(skin.getDrawable("panel1"));
        table.setSize(220, 115);
        table.setPosition((Gdx.graphics.getWidth() - table.getWidth()) / 2,
                (Gdx.graphics.getHeight() - table.getHeight()) / 2);
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        ScreenUtils.clear(0, 0, 0, 1f);

        // Draw background image
        batch.begin();
        batch.draw(backgroundTexture, (640 - 480) / 2f, 0, 480, 480);
        batch.end();

        // Draw and apply ui
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        backgroundTexture.dispose();
        batch.dispose();
    }
}
