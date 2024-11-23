package uk.ac.york.cs.eng1.gemo.headless;

import com.badlogic.gdx.Gdx;
import uk.ac.york.cs.eng1.gemo.Ship;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssetTests extends AbstractHeadlessGdxTest {

    @Test
    public void testFallbackrAssetExists() {
        assertTrue(Gdx.files.internal("error.png").exists(),
            "The Fallback asset should be available");
    }
}
