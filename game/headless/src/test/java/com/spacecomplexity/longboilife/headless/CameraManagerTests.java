// NEW FOR PART 2

package com.spacecomplexity.longboilife.headless;

import com.badlogic.gdx.*;
import com.badlogic.gdx.math.Vector3;
import com.spacecomplexity.longboilife.game.globals.Filepaths;
import com.spacecomplexity.longboilife.game.utils.CameraManager;
import com.spacecomplexity.longboilife.game.world.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CameraManagerTests extends AbstractHeadlessGdxTest {

    private World world;

    @BeforeEach
    public void setUp() {
        try {
            world = new World(Gdx.files.internal(Filepaths.MAP_ASSET));
        } catch (Exception e) {
            throw new IllegalArgumentException(e.toString());
        }
    }

    @Test
    public void testZoomAtIfChangingPosition() {
        CameraManager cameraManager = new CameraManager(world);
        cameraManager.zoom = 0.5f;
        Vector3 atPosition = new Vector3(10, 10, 10);
        cameraManager.zoomAt(-0.1f, atPosition);
        assertEquals(0.4f, cameraManager.zoom);
        assertEquals(2f, cameraManager.position.x, 0.01f);
        assertEquals(2f, cameraManager.position.y, 0.01f);
    }

    @Test
    public void testZoomAtIfNotChangingPosition() {
        CameraManager cameraManager = new CameraManager(world);
        cameraManager.zoom = 0.5f;
        Vector3 atPosition = new Vector3();
        cameraManager.zoomAt(-0.1f, atPosition);
        assertEquals(0.4f, cameraManager.zoom);
        assertEquals(0f, cameraManager.position.x, 0.01f);
        assertEquals(0f, cameraManager.position.y, 0.01f);
    }

    @Test
    public void testZoomAtIfNewZoomGreaterThanMaxZoomAndChangingPosition() {
        CameraManager cameraManager = new CameraManager(world);
        cameraManager.zoom = 0.2f;
        Vector3 atPosition = new Vector3(10, 10, 10);
        cameraManager.zoomAt(1f, atPosition);
        assertEquals(0.5f, cameraManager.zoom);
        assertEquals(-15f, cameraManager.position.x, 0.01f);
        assertEquals(-15f, cameraManager.position.y, 0.01f);
    }

    @Test
    public void testZoomAtIfNewZoomLessThanMinZoomAndChangingPosition() {
        CameraManager cameraManager = new CameraManager(world);
        cameraManager.zoom = 0.2f;
        Vector3 atPosition = new Vector3(10, 10, 10);
        cameraManager.zoomAt(-1f, atPosition);
        assertEquals(0.05f, cameraManager.zoom);
        assertEquals(7.5f, cameraManager.position.x, 0.01f);
        assertEquals(7.5f, cameraManager.position.y, 0.01f);
    }
}
