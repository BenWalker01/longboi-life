package com.spacecomplexity.longboilife.game.building;

import com.badlogic.gdx.graphics.Texture;
import com.spacecomplexity.longboilife.game.utils.Vector2Int;
import com.spacecomplexity.longboilife.game.globals.Filepaths;

import java.util.stream.Stream;

/**
 * Contains a list of all buildings, including their default data.
 */
public enum BuildingType {
    GREGGS("Greggs", new Texture(Filepaths.GREGGS_ASSET), new Vector2Int(2, 2), BuildingCategory.FOOD, 5000),
    LIBRARY("Library", new Texture(Filepaths.LIBRARY_ASSET), new Vector2Int(4, 4), BuildingCategory.EDUCATIONAL,
            200000),
    GYM("Gym", new Texture(Filepaths.GYM_ASSET), new Vector2Int(4, 3), BuildingCategory.RECREATIONAL, 80000),
    HALLS("Halls", new Texture(Filepaths.HALLS_ASSET), new Vector2Int(3, 3), BuildingCategory.ACCOMMODATION, 12000),
    ROAD("Road", new Texture(Filepaths.ROAD_STRAIGHT_ASSET), new Vector2Int(1, 1), BuildingCategory.PATHWAY, 100),
    BASKETBALL("Basketball", new Texture(Filepaths.BASKETBALL_ASSET), new Vector2Int(1, 2), BuildingCategory.RECREATIONAL, 2000),
    ;

    private final String displayName;
    private final Texture texture;
    private final Vector2Int size;
    private final BuildingCategory category;
    private final float cost;

    /**
     * Create a {@link BuildingType} with specified attributes.
     *
     * @param displayName the name to display when selecting this building.
     * @param texture     the texture representing the building.
     * @param size        the size of the building (in tiles).
     * @param category    the category of the building.
     * @param cost        the cost to place the building.
     */
    BuildingType(String displayName, Texture texture, Vector2Int size, BuildingCategory category, float cost) {
        this.displayName = displayName;
        this.texture = texture;
        this.size = size;
        this.category = category;
        this.cost = cost;
    }

    public Texture getTexture() {
        return texture;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Vector2Int getSize() {
        return size;
    }

    public BuildingCategory getCategory() {
        return category;
    }

    public float getCost() {
        return cost;
    }

    public static BuildingType[] getBuildingsOfType(BuildingCategory category) {
        return Stream.of(BuildingType.values())
                .filter(buildingType -> buildingType.getCategory().equals(category))
                .toArray(BuildingType[]::new);

    }

    /**
     * Will dispose of the all loaded assets (like textures).
     * <p>
     * <strong>Warning:</strong> Once disposed of no attributes will be able to be
     * reloaded, which could lead to undefined behaviour.
     */
    public void dispose() {
        texture.dispose();
    }
}
