package com.spacecomplexity.longboilife.game.globals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Class to manage the soundtrack in the game.
 *
 * ASSESSMENT 2 - New feature
 */
public class Soundtrack {
    private static final Soundtrack soundtrack = new Soundtrack();

    /**
     * Get the singleton instance of the {@link Soundtrack} class.
     *
     * @return The single {@link Soundtrack} class.
     */
    public static Soundtrack getSoundtrack() {
        return soundtrack;
    }

    private Soundtrack() {}

    private final Music music = Gdx.audio.newMusic(Gdx.files.internal(Filepaths.SOUNDTRACK));

    /**
     * Play the soundtrack.
     */
    public void play() {
        // TODO: change volume when preferences are implemented
        music.setVolume(0.5f);
        music.setLooping(true);
        music.play();
    }

    /**
     * Pause the soundtrack.
     */
    public void pause() {
        music.pause();
    }
}
