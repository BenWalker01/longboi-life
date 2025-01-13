// NEW FOR PART 2

package com.spacecomplexity.longboilife.headless;

import com.spacecomplexity.longboilife.game.achievements.SustainedAchievement;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SustainedAchievementTests extends AbstractHeadlessGdxTest {
    boolean criterionMet;

    @Test
    public void testCheckUnlockedIfCriterionMetAndThresholdMet() {
        criterionMet = true;
        SustainedAchievement achievement = new SustainedAchievement("name", "description", 1,
                (gs, memory) -> criterionMet);
        assertFalse(achievement.checkUnlocked());
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertTrue(achievement.checkUnlocked());
    }

    @Test
    public void testCheckUnlockedIfCriterionNotMetAndThresholdMet() {
        criterionMet = false;
        SustainedAchievement achievement = new SustainedAchievement("name", "description", 1,
                (gs, memory) -> criterionMet);
        assertFalse(achievement.checkUnlocked());
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertFalse(achievement.checkUnlocked());
    }

    @Test
    public void testCheckUnlockedIfCriterionMetAndThresholdNotMet() {
        criterionMet = true;
        SustainedAchievement achievement = new SustainedAchievement("name", "description", 1,
                (gs, memory) -> criterionMet);
        assertFalse(achievement.checkUnlocked());
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertFalse(achievement.checkUnlocked());
    }

    @Test
    public void testCheckUnlockedIfCriterionNotMetAndThresholdNotMet() {
        criterionMet = false;
        SustainedAchievement achievement = new SustainedAchievement("name", "description", 1,
                (gs, memory) -> criterionMet);
        assertFalse(achievement.checkUnlocked());
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertFalse(achievement.checkUnlocked());
    }

    @Test
    public void testCheckUnlockedIfCriterionChangesAndThresholdMet() {
        criterionMet = true;
        SustainedAchievement achievement = new SustainedAchievement("name", "description", 1,
                (gs, memory) -> criterionMet);
        assertFalse(achievement.checkUnlocked());
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        criterionMet = false;
        assertFalse(achievement.checkUnlocked());
    }

    @Test
    public void testResetIfCriterionMet() {
        criterionMet = true;
        SustainedAchievement achievement = new SustainedAchievement("name", "description", 1,
                (gs, memory) -> criterionMet);
        assertFalse(achievement.checkUnlocked());
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertTrue(achievement.checkUnlocked());
        achievement.reset();
        assertFalse(achievement.checkUnlocked());
    }

    @Test
    public void testResetIfCriterionNotMet() {
        criterionMet = false;
        SustainedAchievement achievement = new SustainedAchievement("name", "description", 1,
                (gs, memory) -> criterionMet);
        assertFalse(achievement.checkUnlocked());
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertFalse(achievement.checkUnlocked());
        achievement.reset();
        assertFalse(achievement.checkUnlocked());
    }
}
