package com.spacecomplexity.longboilife.game.ui.game;



import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.spacecomplexity.longboilife.game.events.RandomEvents;
import com.spacecomplexity.longboilife.game.globals.Filepaths;
import com.spacecomplexity.longboilife.game.globals.GameState;
import com.spacecomplexity.longboilife.game.globals.MainTimer;
import com.spacecomplexity.longboilife.game.ui.UIElement;
import com.spacecomplexity.longboilife.game.utils.UIUtils; 
import com.spacecomplexity.longboilife.game.utils.SoundEffect;


//NEW CLASS
public class UIEventsMenu extends UIElement{

    public UIEventsMenu(Viewport uiViewport, Table parentTable, Skin skin) {
        super(uiViewport, parentTable, skin);

    
        var event = RandomEvents.getRandomEvent();

        Label label = new Label("Event!\n\n" + event.eventDescription, skin);
        label.setAlignment(Align.center);
        label.setFontScale(1.2f);
        label.setColor(Color.WHITE); 
        label.setWrap(false);
        MainTimer.getTimerManager().getTimer().pauseTimer();
        //GameState.getState().paused = true;
        UIUtils.disableAllActors(table.getStage());
        // Place leaderboard onto table
        table.add(label).align(Align.center);
        Table choiceButtonsTable = new Table(skin); 
        table.row(); 
        float maxButtonWidth = -1;
        float maxButtonHeight = -1;
        
        if (event.choices.length >= 1) {
            for (var choice : event.choices) {
                TextButton button = new TextButton(choice.choiceDescription, skin);
                
                
                button.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        choice.event.apply(new Object[0]); 
                        MainTimer.getTimerManager().getTimer().resumeTimer(); 
                        UIUtils.enableAllActors(table.getStage()); 
                        GameState.getState().paused = false;
                        table.remove(); 
                        new SoundEffect(Filepaths.CLICK_SOUND).play();
                    }
                }); 
                float buttonWidth = button.getWidth(); 
                if (buttonWidth > maxButtonWidth) maxButtonWidth = buttonWidth;


                choiceButtonsTable.add(button).expandX().padLeft(2);
            } 
        } else {  
            
            var button = new TextButton("close", skin); 
            var eventEffect = event.event;
            button.addListener(new ClickListener() { 
                @Override 
                public void clicked(InputEvent event, float x, float y) { 
                    eventEffect.apply(new Object[0]); 
                    MainTimer.getTimerManager().getTimer().resumeTimer(); 
                    UIUtils.enableAllActors(table.getStage()); 
                    GameState.getState().paused = false;
                    table.remove(); 
                    new SoundEffect(Filepaths.CLICK_SOUND).play();
                }
            });
            choiceButtonsTable.add(button).expandX().padLeft(2); 
            maxButtonHeight = button.getHeight(); 
            System.out.println(maxButtonHeight);
        } 
        
        // Add the buttons onto the main table
        table.add(choiceButtonsTable).expandX();
        // Style and place the table
        table.setBackground(skin.getDrawable("panel1"));
        table.setSize(label.getPrefWidth() + 20f, label.getPrefHeight() + maxButtonHeight + 20f);
        placeTable(); 
        UIUtils.enableActor(table);
    }

    @Override
    public void render() {
    }

    @Override
    protected void placeTable() {
        table.setPosition((uiViewport.getWorldWidth() - table.getWidth()) / 2, uiViewport.getWorldHeight() / 2 - table.getHeight() / 2);
    }
}
