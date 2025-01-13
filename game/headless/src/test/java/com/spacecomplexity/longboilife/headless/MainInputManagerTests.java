// NEW FOR PART 2

package com.spacecomplexity.longboilife.headless;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.spacecomplexity.longboilife.game.globals.Keybindings;
import com.spacecomplexity.longboilife.Main;
import com.spacecomplexity.longboilife.MainInputManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MainInputManagerTests extends AbstractHeadlessGdxTest {

    private MainInputManager MainInputManager;

    @BeforeEach
    public void setup() {
        Gdx.graphics = mock(Graphics.class);
        MainInputManager = new MainInputManager();
        Main.fullscreen = false;
    }

    @Test
    public void testKeyDownToggleFullscreenTrue() {
        when(Gdx.graphics.getDisplayMode()).thenReturn(mock(Graphics.DisplayMode.class));
        when(Gdx.graphics.getWidth()).thenReturn(800);
        when(Gdx.graphics.getHeight()).thenReturn(600);
        boolean result = MainInputManager.keyDown(Keybindings.FULLSCREEN.getKey());
        verify(Gdx.graphics).setFullscreenMode(Gdx.graphics.getDisplayMode());

        assertAll(
                () -> assertTrue(Main.fullscreen, "Fullscreen should be enabled"),
                () -> assertEquals(800, Main.prevAppWidth, "Previous width should be recorded"),
                () -> assertEquals(600, Main.prevAppHeight, "Previous height should be recorded"),
                () -> assertTrue(result, "The event should always be handled"));
    }

    @Test
    public void testKeyDownToggleFullscreenFalse() {
        Main.fullscreen = true;
        Main.prevAppWidth = 800;
        Main.prevAppHeight = 600;
        boolean result = MainInputManager.keyDown(Keybindings.FULLSCREEN.getKey());
        verify(Gdx.graphics).setWindowedMode(800, 600);
        assertAll(
                () -> assertFalse(Main.fullscreen, "Fullscreen should be disabled"),
                () -> assertTrue(result, "The event should be handled"));
    }

    @Test
    public void testKeyDownUnboundKey() {
        boolean result = MainInputManager.keyDown(Integer.MIN_VALUE);
        verifyNoInteractions(Gdx.graphics);
        assertAll(
                () -> assertFalse(Main.fullscreen, "Fullscreen state should not change"),
                () -> assertFalse(result, "The event should never be handled"));
    }

    @AfterEach
    public void destroy() {
        Gdx.graphics = null;
    }
}
