package com.spacecomplexity.longboilife.game.events;

import java.util.function.Function;
import com.badlogic.gdx.math.MathUtils;
import com.spacecomplexity.longboilife.game.building.BuildingType;
import com.spacecomplexity.longboilife.game.globals.GameState;


  /**
 * Defines various types of random events 
 * 
 *
 * ASSESSMENT 2 - NEW
 */
public enum RandomEvents {
    LESS_INTERNATIONAL_STUDENTS(BiasType.HIGH_MONEY,
            "Due to changes in immigration policy international students\n are dropping out of the university",
            (params) -> {
                var gameState = GameState.getState();
                gameState.money *= MathUtils.clamp((float) Math.random(), 0.7f, 0.9f);
                return null;
            }),
    CHARITY(BiasType.LOW_MONEY, "A billionaire wants to donate to your university", null, new EventChoice("Accept", (params) -> {
        var gameState = GameState.getState();
        gameState.money *= 1.f + MathUtils.clamp((float) Math.random(), 0.1f, 0.4f);
        return null;
    }), new EventChoice("Decline", (params) -> {return null;})),
    MAINTENANCE(BiasType.HIGH_BUILDING_COUNT, "The accomodation is falling apart! Repairs need to be done", (params) -> {
        var gameState = GameState.getState();
        gameState.money *= MathUtils.clamp((float) Math.random(), 0.5f, 0.8f);
        return null;
    }),
    DUCK_EVENT(BiasType.LOW_BUILDING_COUNT, "There has been a Longboi sighting! There is a sudden influx of new students", (params) -> {
        var gameState = GameState.getState();
        gameState.money *= 1.f + MathUtils.clamp((float) Math.random(), 0.2f, 0.5f);
        return null;
    }), 
    OPEN_DAY(BiasType.NO_BIAS, 25, "Prospective students have come to see your university", (params) -> {return null;})
    ;

    private enum BiasType {
        LOW_MONEY,
        HIGH_MONEY,
        NO_BIAS,
        HIGH_BUILDING_COUNT,
        LOW_BUILDING_COUNT
    }

    public BiasType biasType;
    public String eventDescription;
    public Function<Object[], Object> event;
    public int bias = 10;
    public EventChoice[] choices;

    RandomEvents() {
        this.bias = 20;
        this.biasType = BiasType.NO_BIAS;
    }
    RandomEvents(BiasType biasType, int initialBias, String eventDescription, Function<Object[], Object> event, EventChoice... choices) { 
        this(biasType, eventDescription, event, choices); 
        this.bias = initialBias;
    }
    RandomEvents(BiasType biasType, String eventDescription, Function<Object[], Object> event, EventChoice... choices) {
        this.biasType = biasType;
        this.eventDescription = eventDescription;
        this.event = event;
        this.choices = choices;

    }

    private static void adjustBias() {
        for (var event : RandomEvents.values()) {
            switch (event.biasType) {
                case LOW_MONEY: {
                    float moneyThreshold = 800000f * 0.5f;
                    if (Float.compare(moneyThreshold, GameState.getState().money) > 0) {
                        event.bias += 10;
                    } else {
                        event.bias -= 5;
                    }
                }
                    break;
                case HIGH_MONEY: {
                    float moneyThreshold = 800000f * 1.4f;
                    if (Float.compare(moneyThreshold, GameState.getState().money) > 0) {
                        event.bias += 10;
                    } else {
                        event.bias -= 5;
                    }
                }
                    break;
                case HIGH_BUILDING_COUNT: {
                    int buildingCount = GameState.getState().buildingsCount.entrySet()
                            .stream()
                            .filter(entry -> entry.getKey() != BuildingType.ROAD)
                            .mapToInt(entry -> entry.getValue())
                            .reduce(0, (accumulated, value) -> accumulated + value);
                    if (buildingCount > 10) {
                        event.bias += 10;
                    } else {
                        event.bias -= 5;
                    }

                }
                    break;
                case LOW_BUILDING_COUNT: {
                    int buildingCount = GameState.getState().buildingsCount.entrySet()
                            .stream()
                            .filter(entry -> entry.getKey() != BuildingType.ROAD)
                            .mapToInt(entry -> entry.getValue())
                            .reduce(0, (accumulated, value) -> accumulated + value);
                    if (buildingCount < 5) {
                        event.bias += 10;
                    } else {
                        event.bias -= 5;
                    }
                }
                    break;
                default:
                    break;
            }
        }
    }

    private static double[] cumulativeBiases() {
        var events = RandomEvents.values();
        adjustBias();
        double[] biases = new double[events.length];
        biases[0] = events[0].bias;
        for (int i = 1; i < events.length; ++i) {
            biases[i] = biases[i - 1] + events[i].bias;
        }
        return biases;
    }

    private static void resetBiases() {
        for (var event : RandomEvents.values()) {
                if (event != OPEN_DAY) event.bias = 10;
        }
    }

    public static RandomEvents getRandomEvent() {
        var cb = cumulativeBiases();
        double randomVal = Math.random() * cb[cb.length - 1];
        System.out.println(randomVal);
        for (int i = 0; i < cb.length; ++i) {
            if (randomVal < cb[i]) {
                resetBiases();

                return RandomEvents.values()[i];
            }
        }
        return null;
    }

}
