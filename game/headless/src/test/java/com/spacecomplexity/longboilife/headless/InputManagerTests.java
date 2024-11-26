package com.spacecomplexity.longboilife.headless;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Vector3;
import com.spacecomplexity.longboilife.game.globals.Constants;
import com.spacecomplexity.longboilife.game.globals.Filepaths;
import com.spacecomplexity.longboilife.game.globals.GameState;
import com.spacecomplexity.longboilife.game.globals.Keybindings;
import com.spacecomplexity.longboilife.game.globals.MainCamera;
import com.spacecomplexity.longboilife.game.utils.CameraManager;
import com.spacecomplexity.longboilife.game.utils.InputManager;
import com.spacecomplexity.longboilife.game.world.World;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




public class InputManagerTests extends AbstractHeadlessGdxTest {

    private GameState gameState;
    private InputManager inputManager;
    private InputMultiplexer inputMultiplexer;  
    private World world;

    @BeforeEach
    public void inputManagerTestSetup() {

        Gdx.input = mock(Input.class);
        Gdx.graphics = spy(Graphics.class);
        

        
        inputMultiplexer = spy(InputMultiplexer.class);
        inputManager = new InputManager(inputMultiplexer); 
        Gdx.input.setInputProcessor(inputMultiplexer);
        
        gameState = GameState.getState();
        gameState.reset();

         
        try {
            world = new World(Gdx.files.internal(Filepaths.MAP_ASSET));
        } catch(Exception e){throw new IllegalArgumentException(e.toString());} 
        CameraManager camera = new CameraManager(world);
        camera.position.set(new Vector3(
                world.getWidth() * Constants.TILE_SIZE * gameState.scaleFactor / 2,
                world.getHeight() * Constants.TILE_SIZE * gameState.scaleFactor / 2,
                0));
        camera.zoom = 1.0f;  
        MainCamera.setMainCamera(camera);
    }

    @Test
    public void testCameraMovesUp() {
        when(Gdx.input.isKeyPressed(Keybindings.CAMERA_UP.getKey())).thenReturn(true);  
        doReturn(0.016f).when(Gdx.graphics).getDeltaTime();
        float previousY = MainCamera.camera().position.y;
        inputManager.handleContinuousInput();

        float expectedY = previousY + (gameState.cameraSpeed * Gdx.graphics.getDeltaTime() * MainCamera.camera().zoom * gameState.scaleFactor);
        assertEquals(expectedY, MainCamera.camera().position.y, 0.01f, "Camera Y position is incorrect");
    } 

    @Test
    public void testCameraMovesDown() {
        when(Gdx.input.isKeyPressed(Keybindings.CAMERA_DOWN.getKey())).thenReturn(true);
        doReturn(0.016f).when(Gdx.graphics).getDeltaTime();
        float previousY = MainCamera.camera().position.y;
        inputManager.handleContinuousInput();

        float expectedY = previousY - (gameState.cameraSpeed * Gdx.graphics.getDeltaTime() * MainCamera.camera().zoom * gameState.scaleFactor);
        assertEquals(expectedY, MainCamera.camera().position.y, 0.01f, "Camera Y position is incorrect");
    } 
    @Test
    public void testCameraMovesLeft() {
        when(Gdx.input.isKeyPressed(Keybindings.CAMERA_LEFT.getKey())).thenReturn(true);
        doReturn(0.016f).when(Gdx.graphics).getDeltaTime();
        float previousX = MainCamera.camera().position.x;

        inputManager.handleContinuousInput();

        float expectedX = previousX - (gameState.cameraSpeed * Gdx.graphics.getDeltaTime() * MainCamera.camera().zoom * gameState.scaleFactor);
        assertEquals(expectedX, MainCamera.camera().position.x, 0.01f, "Camera X position is incorrect");
    } 
    @Test
    public void testCameraMovesRight() {
        when(Gdx.input.isKeyPressed(Keybindings.CAMERA_RIGHT.getKey())).thenReturn(true);
        doReturn(0.016f).when(Gdx.graphics).getDeltaTime();
        float previousX = MainCamera.camera().position.x;
        inputManager.handleContinuousInput();

        float expectedX = previousX + (gameState.cameraSpeed * Gdx.graphics.getDeltaTime() * MainCamera.camera().zoom * gameState.scaleFactor);
        assertEquals(expectedX, MainCamera.camera().position.x, 0.01f, "Camera X position is incorrect");
    }
    @Test 
    public void testNoCameraMovementWithOpposingInputsHorizontal() { 
        doReturn(0.016f).when(Gdx.graphics).getDeltaTime();
        when(Gdx.input.isKeyPressed(Keybindings.CAMERA_RIGHT.getKey())).thenReturn(true);
        when(Gdx.input.isKeyPressed(Keybindings.CAMERA_LEFT.getKey())).thenReturn(true); 

        inputManager.handleContinuousInput(); 
        assertAll(
            () -> assertEquals(world.getWidth() * Constants.TILE_SIZE * gameState.scaleFactor / 2, MainCamera.camera().position.x, 0.01f, "Camera X position should not change"),
            () -> assertEquals(world.getHeight() * Constants.TILE_SIZE * gameState.scaleFactor / 2, MainCamera.camera().position.y, 0.01f, "Camera Y position should not change"),
            () -> assertEquals(1.0f, MainCamera.camera().zoom, 0.01f, "Camera zoom should not change")
        );
    } 
    public void testNoCameraMovementWithOpposingInputsVertical() { 
        doReturn(0.016f).when(Gdx.graphics).getDeltaTime();
        when(Gdx.input.isKeyPressed(Keybindings.CAMERA_UP.getKey())).thenReturn(true);
        when(Gdx.input.isKeyPressed(Keybindings.CAMERA_DOWN.getKey())).thenReturn(true); 

        inputManager.handleContinuousInput(); 
        assertAll(
            () -> assertEquals(world.getWidth() * Constants.TILE_SIZE * gameState.scaleFactor / 2, MainCamera.camera().position.x, 0.01f, "Camera X position should not change"),
            () -> assertEquals(world.getHeight() * Constants.TILE_SIZE * gameState.scaleFactor / 2, MainCamera.camera().position.y, 0.01f, "Camera Y position should not change"),
            () -> assertEquals(1.0f, MainCamera.camera().zoom, 0.01f, "Camera zoom should not change")
        );
    }  
    @Test
    public void testNoCameraMovementWithOpposingInputsOmni() { 
        doReturn(0.016f).when(Gdx.graphics).getDeltaTime();
        when(Gdx.input.isKeyPressed(Keybindings.CAMERA_RIGHT.getKey())).thenReturn(true);
        when(Gdx.input.isKeyPressed(Keybindings.CAMERA_LEFT.getKey())).thenReturn(true); 
        when(Gdx.input.isKeyPressed(Keybindings.CAMERA_UP.getKey())).thenReturn(true);
        when(Gdx.input.isKeyPressed(Keybindings.CAMERA_DOWN.getKey())).thenReturn(true);  

        inputManager.handleContinuousInput(); 
        assertAll(
            () -> assertEquals(world.getWidth() * Constants.TILE_SIZE * gameState.scaleFactor / 2, MainCamera.camera().position.x, 0.01f, "Camera X position should not change"),
            () -> assertEquals(world.getHeight() * Constants.TILE_SIZE * gameState.scaleFactor / 2, MainCamera.camera().position.y, 0.01f, "Camera Y position should not change"),
            () -> assertEquals(1.0f, MainCamera.camera().zoom, 0.01f, "Camera zoom should not change")
        );
    }
    @Test
    public void testNoMovementOrZoomWithoutInput() {
        doReturn(0.016f).when(Gdx.graphics).getDeltaTime();
        when(Gdx.input.isKeyPressed(anyInt())).thenReturn(false);


        inputManager.handleContinuousInput();

        assertAll(
            () -> assertEquals(world.getWidth() * Constants.TILE_SIZE * gameState.scaleFactor / 2, MainCamera.camera().position.x, 0.01f, "Camera X position should not change"),
            () -> assertEquals(world.getHeight() * Constants.TILE_SIZE * gameState.scaleFactor / 2, MainCamera.camera().position.y, 0.01f, "Camera Y position should not change"),
            () -> assertEquals(1.0f, MainCamera.camera().zoom, 0.01f, "Camera zoom should not change")
        );
    } 

    @Test 
    public void testNoZoomChangeIfHorizontallyScrolled() { 
        doReturn(0.016f).when(Gdx.graphics).getDeltaTime();
        inputMultiplexer.scrolled(Float.NEGATIVE_INFINITY, 0); 
        MainCamera.camera().update();
        assertEquals(Constants.MAX_ZOOM, MainCamera.camera().zoom, 0.01f, "Camera zoom should not change");
    }  
    @Test
    public void testClampingForExtremeNegativeValueForScrolling() { 
        doReturn(0.016f).when(Gdx.graphics).getDeltaTime();
        inputMultiplexer.scrolled(0, Float.NEGATIVE_INFINITY);
        MainCamera.camera().update();
        assertEquals(Constants.MIN_ZOOM, MainCamera.camera().zoom, 0.01f, "Camera zoom should clamp to Constants.MIN_ZOOM");
    }  
    @Test
    public void testClampingForExtremePositiveValueForScrolling() { 
        doReturn(0.016f).when(Gdx.graphics).getDeltaTime();
        inputMultiplexer.scrolled(0, Float.POSITIVE_INFINITY);
        MainCamera.camera().update();
        assertEquals(Constants.MAX_ZOOM, MainCamera.camera().zoom, 0.01f, "Camera zoom should clamp to Constants.MAX_ZOOM");
    }  
    
    @Test 
    public void testCorrectionForNaNYValue() {  
        doReturn(0.016f).when(Gdx.graphics).getDeltaTime();
        float previousZoom = MainCamera.camera().zoom;
        inputMultiplexer.scrolled(0, Float.NaN);
        MainCamera.camera().update();
        assertEquals(previousZoom, MainCamera.camera().zoom, 0.01f, "Camera zoom should not change for invalid floating point values");
    } 

    @AfterEach 
    public void destroy() { 
        Gdx.graphics = null; 
        inputMultiplexer = null;
    }
}
