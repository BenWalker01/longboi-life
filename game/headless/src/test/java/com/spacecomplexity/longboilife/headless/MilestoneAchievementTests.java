package com.spacecomplexity.longboilife.headless;
import com.spacecomplexity.longboilife.game.achievements.MilestoneAchievement;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class MilestoneAchievementTests extends AbstractHeadlessGdxTest {
    boolean criterionMet;
    @Test
    public void testCheckUnlockedIfCriterionMet() {
        MilestoneAchievement achievement = new MilestoneAchievement("name", "description", gs -> true);
        assertTrue(achievement.checkUnlocked());
    }
    @Test
    public void testCheckUnlockedIfCriterionNotMet() {
        MilestoneAchievement achievement = new MilestoneAchievement("name", "description", gs -> false);
        assertFalse(achievement.checkUnlocked());
    }
    @Test
    public void testCheckUnlockedIfCriterionChanges() {
        criterionMet = true;
        MilestoneAchievement achievement = new MilestoneAchievement("name", "description", gs -> criterionMet);
        assertTrue(achievement.checkUnlocked());
        criterionMet = false;
        assertTrue(achievement.checkUnlocked());
    }
    @Test
    public void testResetIfCriterionMet() {
        MilestoneAchievement achievement = new MilestoneAchievement("name", "description", gs -> true);
        assertTrue(achievement.checkUnlocked());
        achievement.reset();
        assertTrue(achievement.checkUnlocked());
    }
    @Test
    public void testResetIfCriterionNotMet() {
        MilestoneAchievement achievement = new MilestoneAchievement("name", "description", gs -> false);
        assertFalse(achievement.checkUnlocked());
        achievement.reset();
        assertFalse(achievement.checkUnlocked());
    }
    @Test
    public void testResetIfCriterionChanges() {
        criterionMet = true;
        MilestoneAchievement achievement = new MilestoneAchievement("name", "description", gs -> criterionMet);
        assertTrue(achievement.checkUnlocked());
        achievement.reset();
        criterionMet = false;
        assertFalse(achievement.checkUnlocked());
    }
}
