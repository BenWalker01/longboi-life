package com.spacecomplexity.longboilife.headless;
import com.spacecomplexity.longboilife.game.utils.UIUtils;

import org.junit.jupiter.api.Test;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*; 

public class UIUtilsTests extends AbstractHeadlessGdxTest {
    
    

    @Test 
    public void testEnableActorBaseCase() { 
        var table = new Table();
          
        try (var staticUIUtilsMock = mockStatic(UIUtils.class)) { 
            UIUtils.enableActor(table); 
            staticUIUtilsMock.verify( 
                () -> UIUtils.enableActor(table), 
                times(1)
            ); 
        }  
    }  
    @Test 
    public void testEnableActorRecursiveCase() { 
        var table = new Table();
        var table2 = new Table();
        table2.add(table);
        UIUtils.enableActor(table);  
        assertAll( 
            () -> assertTrue(table.isTouchable()), 
            () -> assertTrue(table2.isTouchable())
        );
    }  
       
    @Test 
    public void testDisableAllActorsBaseCase() { 
        var table = new Table();
       
        try (var staticUIUtilsMock = mockStatic(UIUtils.class)) { 
            try (var stageMock = mockConstruction(Stage.class)) {
            var stage = new Stage();
            stage.addActor(table); 
            UIUtils.enableAllActors(stage);
            UIUtils.disableAllActors(stage); 
            staticUIUtilsMock.verify( 
                () -> UIUtils.disableAllActors(stage), 
                times(1)
            ); 
            } 
        }  
    }  
    @Test 
    public void testDiasableActorRecursiveCase() { 
        var table = new Table();
        var table2 = new Table();
        table2.add(table); 
        Array<Actor> array = new Array<>(); 
        array.add(table2);
  
        try (var stageMock = mockConstruction(Stage.class)) { 
            
            var stage = new Stage();
            when(stage.getActors()).thenReturn(array);
            stage.addActor(table2); 
            UIUtils.enableAllActors(stage);
            UIUtils.disableAllActors(stage); 
            assertAll( 
                () -> assertFalse(table.isTouchable()), 
                () -> assertFalse(table2.isTouchable())
            ); 
        }
    }    

    @Test 
    public void testEnableAllActorsBaseCase() { 
        var table = new Table();
       
        try (var staticUIUtilsMock = mockStatic(UIUtils.class)) { 
            try (var stageMock = mockConstruction(Stage.class)) {
            var stage = new Stage();
            stage.addActor(table); 
            UIUtils.disableAllActors(stage);
            UIUtils.enableAllActors(stage);
             
            staticUIUtilsMock.verify( 
                () -> UIUtils.enableAllActors(stage), 
                times(1)
            ); 
            } 
        }  
    }  
    @Test 
    public void testEnableAllActorsRecursiveCase() { 
        var table = new Table();
        var table2 = new Table();
        table2.add(table); 
        Array<Actor> array = new Array<>(); 
        array.add(table2);
  
        try (var stageMock = mockConstruction(Stage.class)) { 
            
            var stage = new Stage();
            when(stage.getActors()).thenReturn(array);
            stage.addActor(table2);  
            UIUtils.disableAllActors(stage);
            UIUtils.enableAllActors(stage);
             
            assertAll( 
                () -> assertTrue(table.isTouchable()), 
                () -> assertTrue(table2.isTouchable())
            ); 
        }
    }  

}
