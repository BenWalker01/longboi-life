package com.spacecomplexity.longboilife.game.achievements;

/**
 * Interface for an achievement.
 *
 * ASSESSMENT 2 - New feature
 */
public interface IAchievement {
    /**
     * Get the name of the achievement.
     *
     * @return the name of the achievement
     */
    String getName();

    /**
     * Get the description of the achievement.
     *
     * @return the description of the achievement
     */
    String getDescription();

    /**
     * Check if the achievement is unlocked.
     *
     * @return true if the achievement is unlocked, false otherwise
     */
    boolean checkUnlocked();

    /**
     * Reset the achievement progress.
     */
    void reset();
}
