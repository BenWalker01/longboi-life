package com.spacecomplexity.longboilife.game.events;
import java.util.function.Function; 
import com.badlogic.gdx.math.MathUtils;


import com.spacecomplexity.longboilife.game.globals.GameState; 
// NEW CLASS
public enum RandomEvents {
    LESS_INTERNATIONAL_STUDENTS(BiasType.HIGH_MONEY, "Due to changes in immigration policy international students\n are dropping out of the university", 
    (params) -> {  
        var gameState = GameState.getState(); 
        gameState.money *= MathUtils.clamp((float)Math.random(), 0.7f, 0.9f);
        return null;
    }), 
    CHARITY(BiasType.LOW_MONEY, "A billionaire donated to your university", (params) -> { 
        var gameState = GameState.getState(); 
        gameState.money *= 1.f + MathUtils.clamp((float)Math.random(), 0.1f, 0.4f); 
        return null;
    }), 
    ;  
    private enum BiasType { 
        LOW_MONEY, 
        HIGH_MONEY, 
        DONT_CARE, 
        HIGH_BUILDING_COUNT, 
        LOW_BUILDING_COUNT
    
    }
    public BiasType biasType;
    public String eventDescription;  
    public Function<Object[], Object> event;
    public int bias = 10; 
    public RandomEvents currentEvent;  
    public EventChoice[] choices;
    

    RandomEvents(BiasType biasType, String eventDescription, Function<Object[], Object> event, EventChoice...choices) { 
        this.biasType = biasType;
        this.eventDescription = eventDescription; 
        this.event = event; 
        this.choices = choices;  
        
    }   
    public static void adjustBias() { 
        for(var event : RandomEvents.values()) { 
            switch(event.biasType) { 
                case LOW_MONEY: {

                } break; 
                case HIGH_MONEY: { 

                } break; 
                default: break;
            }
        }
    } 
    public static double[] cumulativeBiases() {  
        var events = RandomEvents.values(); 
        adjustBias();
        double[] biases = new double[events.length];  
        biases[0] = events[0].bias; 
        for (int i = 1; i < events.length; ++i) { 
            biases[i] = biases[i - 1] + events[i].bias;
        } 
        return biases;
    }
    public static RandomEvents getRandomEvent() {  
        var cb = cumulativeBiases();
        double randomVal = Math.random() * cb[cb.length - 1];  
        System.out.println(randomVal);
        for (int i = 0; i < cb.length; ++i) {
            if (randomVal < cb[i]) {
                return RandomEvents.values()[i];
            }
        } 
        return null;
    }

}
