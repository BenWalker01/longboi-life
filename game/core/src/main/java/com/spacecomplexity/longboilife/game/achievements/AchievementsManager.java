package com.spacecomplexity.longboilife.game.achievements;

import com.spacecomplexity.longboilife.game.building.BuildingType;

import java.util.HashSet;

public class AchievementsManager {
    private final IAchievement[] achievements;
    private final HashSet<IAchievement> unlockedAchievements = new HashSet<>();

    public AchievementsManager() {
        achievements = new IAchievement[] {
            new MilestoneAchievement("First Steps", "Place your first building", state -> {
                int buildingCount = 0;
                for (BuildingType buildingType : BuildingType.values()) {
                    buildingCount += state.getBuildingCount(buildingType);
                }
                buildingCount -= state.getBuildingCount(BuildingType.ROAD);
                return buildingCount > 0;
            }),
            new MilestoneAchievement("Campus Planner", "Place 10 buildings", state -> {
                int buildingCount = 0;
                for (BuildingType buildingType : BuildingType.values()) {
                    buildingCount += state.getBuildingCount(buildingType);
                }
                buildingCount -= state.getBuildingCount(BuildingType.ROAD);
                return buildingCount >= 10;
            }),
            new SustainedAchievement("Strategic Pause", "Build no new buildings for 30 seconds", 30, (state,memory) -> {
                int buildingCount = 0;
                for (BuildingType buildingType : BuildingType.values()) {
                    buildingCount += state.getBuildingCount(buildingType);
                }
                if (!memory.containsKey("buildingCount")) {
                    memory.put("buildingCount", buildingCount);
                }
                return buildingCount == (int) memory.get("buildingCount");
            }),
            new MilestoneAchievement("Road to Nowhere", "Have 50 road segments and no other buildings", state -> {
                int roadCount = state.getBuildingCount(BuildingType.ROAD);
                int buildingCount = 0;
                for (BuildingType buildingType : BuildingType.values()) {
                    buildingCount += state.getBuildingCount(buildingType);
                }
                return roadCount >= 50 && buildingCount == roadCount;
            }),
            new MilestoneAchievement("Master Architect", "Place a building of each type", state -> {
                for (BuildingType buildingType : BuildingType.values()) {
                    if (state.getBuildingCount(buildingType) == 0) {
                        return false;
                    }
                }
                return true;
            }),
            new MilestoneAchievement("Financial Crisis", "Run out of money", state -> state.money <= 0)
        };
    }

    public IAchievement[] getAchievements() {
        return achievements;
    }

    public void resetAchievements() {
        for (IAchievement achievement : achievements) {
            achievement.reset();
        }
    }

    public void checkAchievements() {
        for (IAchievement achievement : achievements) {
            if (!unlockedAchievements.contains(achievement) && achievement.checkUnlocked()) {
                unlockedAchievements.add(achievement);
                System.out.println("UNLOCKED: " + achievement.getName() + " - " + achievement.getDescription());
            }
        }
    }
}
