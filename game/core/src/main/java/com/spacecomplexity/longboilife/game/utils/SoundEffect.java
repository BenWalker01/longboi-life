package com.spacecomplexity.longboilife.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundEffect {
    private Sound sound;

    public SoundEffect(String path) {
        sound = Gdx.audio.newSound(Gdx.files.internal(path));
    }

    public void play() {
        // TODO: change volume when preferences are implemented
        sound.play(0.5f);
    }
}
