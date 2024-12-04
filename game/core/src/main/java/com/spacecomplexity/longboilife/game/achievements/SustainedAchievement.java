package com.spacecomplexity.longboilife.game.achievements;

import com.spacecomplexity.longboilife.game.globals.GameState;

import java.util.HashMap;
import java.util.function.BiPredicate;

public class SustainedAchievement implements IAchievement {
    private final String name;
    private final String description;
    private final BiPredicate<GameState, HashMap<String, Object>> criterion;
    private HashMap<String, Object> memory = new HashMap<>();
    private boolean unlocked;
    private long startTime;
    private final long threshold;

    public SustainedAchievement(String name, String description, int threshold, BiPredicate<GameState, HashMap<String, Object>> criterion) {
        this.name = name;
        this.description = description;
        this.criterion = criterion;
        this.threshold = threshold * 1000;
        this.unlocked = false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean checkUnlocked() {
        if (!unlocked) {
            if (criterion.test(GameState.getState(), memory)) {
                if (startTime == 0) {
                    startTime = System.currentTimeMillis();
                } else if (System.currentTimeMillis() - startTime >= threshold) {
                    unlocked = true;
                }
            } else {
                reset();
            }
        }
        return unlocked;
    }

    @Override
    public void reset() {
        startTime = 0;
        unlocked = false;
        memory = new HashMap<>();
    }
}
