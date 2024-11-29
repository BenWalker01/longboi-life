package com.spacecomplexity.longboilife.headless;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.spacecomplexity.longboilife.game.GameScreen;
import com.spacecomplexity.longboilife.menu.MenuScreen; 
import com.spacecomplexity.longboilife.Main;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import static org.mockito.Mockito.*;

public class MainTests extends AbstractHeadlessGdxTest { 
    
    private Main main; 
    
    @BeforeEach 
    public void setUp() { 
        Game game = (Game) Gdx.app.getApplicationListener(); 
        main = (Main) game; 
        
        
    }

    @Test 
    public void testThrowsOnInvalidScreenType() {  
        
        assertThrows(RuntimeException.class, () -> main.switchScreen(null));
    }  
    @Test 
    public void testCorrectScreenMappedToScreenType() {  
        
        assertAll(
            () -> assertEquals(GameScreen.class, Main.ScreenType.GAME.getScreenClass()),
            () -> assertEquals(MenuScreen.class, Main.ScreenType.MENU.getScreenClass()) 
        );
    } 
    @Test 
    public void testDisposeIsOnlyCalledForOwnedResources() {
        try (MockedConstruction<GameScreen> mocked = mockConstruction(GameScreen.class)) {  
            try (var mockMenu = mockConstruction(MenuScreen.class)) {
                main.switchScreen(Main.ScreenType.GAME);  
                var gs = main.getScreen();
                main.switchScreen(Main.ScreenType.MENU); 
                var ms = main.getScreen();
                main.dispose();
                verify(gs).dispose(); 
                verify(ms).dispose();
            }
        }
    }
    
}
