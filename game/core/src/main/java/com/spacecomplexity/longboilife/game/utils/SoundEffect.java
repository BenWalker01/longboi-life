package com.spacecomplexity.longboilife.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.spacecomplexity.longboilife.game.globals.GameState;

/**
 * Class to represent a sound effect.
 *
 * ASSESSMENT 2 - New feature
 */
public class SoundEffect {
    private Sound sound;

    /**
     * Create a new sound effect.
     *
     * @param path the path to the sound effect file.
     */
    public SoundEffect(String path) {
        sound = Gdx.audio.newSound(Gdx.files.internal(path));
    }

    /**
     * Play the sound effect if game sound is on.
     */
    public void play() {
        if(GameState.getState().soundOn)
            sound.play(0.5f);
    }
}
