package com.spacecomplexity.longboilife.headless;

import com.badlogic.gdx.*;
import com.badlogic.gdx.math.Vector3;
import com.spacecomplexity.longboilife.game.building.Building;
import com.spacecomplexity.longboilife.game.building.BuildingType;
import com.spacecomplexity.longboilife.game.globals.Constants;
import com.spacecomplexity.longboilife.game.globals.Filepaths;
import com.spacecomplexity.longboilife.game.globals.GameState;
import com.spacecomplexity.longboilife.game.globals.MainCamera;
import com.spacecomplexity.longboilife.game.utils.CameraManager;
import com.spacecomplexity.longboilife.game.utils.GameUtils;
import com.spacecomplexity.longboilife.game.utils.Vector2Int;
import com.spacecomplexity.longboilife.game.world.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameUtilsTests extends AbstractHeadlessGdxTest {

    private GameState gameState;
    private World world;

    @BeforeEach
    public void setUp() {

        Gdx.input = mock(Input.class);
        Gdx.graphics = mock(Graphics.class);

        InputMultiplexer inputMultiplexer = spy(InputMultiplexer.class);
        Gdx.input.setInputProcessor(inputMultiplexer);

        gameState = GameState.getState();
        gameState.reset();

        try {
            world = new World(Gdx.files.internal(Filepaths.MAP_ASSET));
        } catch (Exception e) {
            throw new IllegalArgumentException(e.toString());
        }
        CameraManager camera = new CameraManager(world);
        camera.position.set(new Vector3(
                world.getWidth() * Constants.TILE_SIZE * gameState.scaleFactor / 2,
                world.getHeight() * Constants.TILE_SIZE * gameState.scaleFactor / 2,
                0));
        camera.zoom = 1.0f;
        MainCamera.setMainCamera(camera);

    }

    @Test
    public void testGetMouseOnGridMethod() {

        Vector2Int mousePositionMin = new Vector2Int(0, 0);
        when(Gdx.input.getX()).thenReturn(mousePositionMin.x);
        when(Gdx.input.getY()).thenReturn(mousePositionMin.y);

        assertEquals(mousePositionMin, GameUtils.getMouseOnGrid(world));
    }

    @Test
    public void testCalculateScalingMethod() {
        float oldScaleFactor = gameState.scaleFactor;
        when(Gdx.graphics.getHeight()).thenReturn(0);
        assertEquals(oldScaleFactor, gameState.scaleFactor, 0.001f);
        when(Gdx.graphics.getHeight()).thenReturn(1080);
        assertEquals(1.0f, gameState.scaleFactor, 0.001f);

    }

    @Test
    public void testUpdateSatisfactionScoreWMethod() {

        when(Gdx.graphics.getDeltaTime()).thenReturn(1 / 60f); // 60 fps

        // Game should start at 0 satisfaction
        assertEquals(0, gameState.satisfactionScore);
        GameUtils.updateSatisfactionScore(world);
        // Satisfaction should not change if there are no buildings
        assertEquals(0, gameState.satisfactionScore);

    }

    @Test
    public void testAccomLimitOnSatisfactionScoreMin() {
        when(Gdx.graphics.getDeltaTime()).thenReturn(1 / 60f); // 60 fps

        // Place on of each building type
        world.build(new Building(BuildingType.HALLS, new Vector2Int(0, 0)));
        world.build(new Building(BuildingType.GREGGS, new Vector2Int(0, 3)));
        world.build(new Building(BuildingType.GYM, new Vector2Int(0, 5)));
        world.build(new Building(BuildingType.LIBRARY, new Vector2Int(0, 8)));
        // satisfaction modifier should now be 4.0

        GameUtils.updateSatisfactionScore(world);
        assertEquals((2 / 3f) * Math.pow(10, -7), gameState.satisfactionScore, 1e-8);

        for (int i = 0; i < 5 * 60 * 60; i++) { // "Play" for 5 mins
            GameUtils.updateSatisfactionScore(world);
        }
        assertEquals(0.1f, gameState.satisfactionScore, 0.01f); // Max 10% due to 1 accommodation building
    }

    @Test
    public void testScripts() {
        assertTrue(false);
    }
}
