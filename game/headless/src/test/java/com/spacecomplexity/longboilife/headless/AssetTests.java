package com.spacecomplexity.longboilife.headless;

import com.badlogic.gdx.Gdx;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssetTests extends AbstractHeadlessGdxTest {

    @Test
    public void testFallbackrAssetExists() {
        assertTrue(Gdx.files.internal("error.png").exists(),
            "The Fallback asset should be available");
    }
}
