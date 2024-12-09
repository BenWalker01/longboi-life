package com.spacecomplexity.longboilife.game.achievements;

import com.spacecomplexity.longboilife.game.globals.GameState;

import java.util.function.Predicate;

/**
 * Class to represent a milestone achievement - unlocked when criterion is met once.
 *
 * ASSESSMENT 2 - New feature
 */
public class MilestoneAchievement implements IAchievement {
    private final String name;
    private final String description;
    private final Predicate<GameState> criterion;
    private boolean unlocked;

    /**
     * Create a new milestone achievement.
     *
     * @param name name of the achievement
     * @param description description of the achievement
     * @param criterion criterion to unlock the achievement
     */
    public MilestoneAchievement(String name, String description, Predicate<GameState> criterion) {
        this.name = name;
        this.description = description;
        this.unlocked = false;
        this.criterion = criterion;
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
            unlocked = criterion.test(GameState.getState());
        }
        return unlocked;
    }

    @Override
    public void reset() {
        unlocked = false;
    }
}
