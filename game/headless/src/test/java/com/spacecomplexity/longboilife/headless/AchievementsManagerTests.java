package com.spacecomplexity.longboilife.headless;

import com.spacecomplexity.longboilife.game.achievements.AchievementsManager;
import com.spacecomplexity.longboilife.game.achievements.IAchievement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AchievementsManagerTests extends AbstractHeadlessGdxTest {
    @Test
    public void testCheckAchievements() {
        IAchievement mockAchievement1 = mock(IAchievement.class);
        when(mockAchievement1.checkUnlocked()).thenReturn(true);
        IAchievement mockAchievement2 = mock(IAchievement.class);
        when(mockAchievement2.checkUnlocked()).thenReturn(false);
        AchievementsManager achievementsManager = new AchievementsManager(
            new IAchievement[] {mockAchievement1, mockAchievement2});
        achievementsManager.checkAchievements();
        assertTrue(achievementsManager.getUnlockedAchievements().size() == 1);
        assertTrue(achievementsManager.getUnlockedAchievements().contains(mockAchievement1));
        assertTrue(achievementsManager.getAchievementQueue().size() == 1);
        assertTrue(achievementsManager.getAchievementQueue().contains(mockAchievement1));
    }

    @Test
    public void testCheckAchievementsEmpty() {
        AchievementsManager achievementsManager = new AchievementsManager(new IAchievement[0]);
        achievementsManager.checkAchievements();
        assertTrue(achievementsManager.getUnlockedAchievements().isEmpty());
        assertTrue(achievementsManager.getAchievementQueue().isEmpty());
    }

    @Test
    public void testResetAchievements() {
        IAchievement mockAchievement1 = mock(IAchievement.class);
        IAchievement mockAchievement2 = mock(IAchievement.class);
        when(mockAchievement1.checkUnlocked()).thenReturn(true);
        when(mockAchievement2.checkUnlocked()).thenReturn(false);
        AchievementsManager achievementsManager = new AchievementsManager(
            new IAchievement[] {mockAchievement1, mockAchievement2});
        achievementsManager.checkAchievements();
        assertTrue(achievementsManager.getUnlockedAchievements().size() == 1);
        assertTrue(achievementsManager.getUnlockedAchievements().contains(mockAchievement1));
        achievementsManager.resetAchievements();
        assertTrue(achievementsManager.getUnlockedAchievements().isEmpty());
        assertTrue(achievementsManager.getAchievementQueue().isEmpty());
    }

    @Test
    public void testResetAchievementsEmpty() {
        AchievementsManager achievementsManager = new AchievementsManager(new IAchievement[0]);
        achievementsManager.resetAchievements();
        assertTrue(achievementsManager.getUnlockedAchievements().isEmpty());
        assertTrue(achievementsManager.getAchievementQueue().isEmpty());
    }
}
