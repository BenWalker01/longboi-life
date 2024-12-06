package com.spacecomplexity.longboilife.game.globals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class Soundtrack {
    private static final Soundtrack soundtrack = new Soundtrack();

    public static Soundtrack getSoundtrack() {
        return soundtrack;
    }

    private Soundtrack() {}

    private final Music music = Gdx.audio.newMusic(Gdx.files.internal(Filepaths.SOUNDTRACK));

    public void play() {
        // to change when preferences are implemented
        music.setVolume(0.5f);
        music.setLooping(true);
        music.play();
    }

    public void pause() {
        music.pause();
    }
}
