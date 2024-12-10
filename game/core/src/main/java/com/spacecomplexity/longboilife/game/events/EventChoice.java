package com.spacecomplexity.longboilife.game.events;
import java.util.function.Function;
public class EventChoice {
    public String choiceDescription; 
    public Function<Object[], Object> event; 
    public EventChoice(String choiceDescription, Function<Object[], Object> event) { 
        this.choiceDescription = choiceDescription; 
        this.event = event;
    }
}
