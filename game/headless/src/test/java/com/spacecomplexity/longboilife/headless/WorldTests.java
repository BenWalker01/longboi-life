// NEW FOR PART 2

package com.spacecomplexity.longboilife.headless;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.Gdx;
import com.spacecomplexity.longboilife.game.building.Building;
import com.spacecomplexity.longboilife.game.building.BuildingType;
import com.spacecomplexity.longboilife.game.globals.Filepaths;
import com.spacecomplexity.longboilife.game.globals.GameState;
import com.spacecomplexity.longboilife.game.pathways.PathwayPositions;
import com.spacecomplexity.longboilife.game.tile.Tile;
import com.spacecomplexity.longboilife.game.tile.TileType;
import com.spacecomplexity.longboilife.game.utils.Vector2Int;
import com.spacecomplexity.longboilife.game.world.World;

public class WorldTests extends AbstractHeadlessGdxTest {

    private World world;
    private GameState gameState;
    private Vector2Int origin = new Vector2Int(0, 0);

    @BeforeEach
    public void setUp() {
        System.out.println(world);
        try {
            world = new World(Gdx.files.internal(Filepaths.MAP_ASSET));
            gameState = GameState.getState();
            gameState.reset();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.toString());
        }
    }

    @Test
    public void testGetTile() {
        Tile tile = world.getTile(origin);
        assertEquals(TileType.GRASS, tile.getType());

    }

    @Test
    public void testGetPathwayPosition() {
        PathwayPositions pos = world.getPathwayPosition(origin);
        assertNull(pos);

        world.build(new Building(BuildingType.ROAD, origin));
        pos = world.getPathwayPosition(origin);
        assertEquals(PathwayPositions.TOP_BOTTOM, pos);

    }

    @Test
    public void testCanBuildMethod() {
        assertTrue(world.canBuild(BuildingType.GREGGS, origin));
        world.build(BuildingType.GREGGS, origin);
        assertFalse(world.canBuild(BuildingType.GREGGS, origin));
    }

    @Test
    public void testBuildingMethods() {
        assertEquals(0, GameState.getState().getBuildingCount(BuildingType.GREGGS));

        world.build(BuildingType.GREGGS, origin);
        assertEquals(1, GameState.getState().getBuildingCount(BuildingType.GREGGS));

        Building greggs = new Building(BuildingType.GREGGS, new Vector2Int(2, 2));
        world.build(greggs);
        assertEquals(2, GameState.getState().getBuildingCount(BuildingType.GREGGS));

        world.build(greggs, new Vector2Int(2, 0));
        assertEquals(3, GameState.getState().getBuildingCount(BuildingType.GREGGS));

        try {
            world.build(greggs, origin);
            fail("IllegalStateException not thrown");
        } catch (Exception e) {
        }

    }

    @Test
    public void testDemolishMethod() {
        Tile oldTile = world.getTile(origin);
        Building greggs = new Building(BuildingType.GREGGS, origin);
        world.build(greggs);
        world.demolish(greggs);
        assertEquals(0, GameState.getState().getBuildingCount(BuildingType.GREGGS));
        assertEquals(oldTile, world.getTile(origin));

    }

    @Test
    public void testInWorldMethod() {
        assertFalse(world.isInWorld(new Vector2Int(-1, -1)));
        assertTrue(world.isInWorld(origin));
    }

    @Test
    public void testGetPathDistanceMethod() {
        Building[] roads = new Building[10];
        Building path = null;
        for (int i = 0; i < 10; i++) {
            path = new Building(BuildingType.ROAD, new Vector2Int(i, 0));
            world.build(path);
            roads[i] = path;
        }
        assertEquals(9, world.getPathDistance(origin, new Vector2Int(9, 0)));
        world.demolish(roads[5]);
        assertEquals(-1, world.getPathDistance(origin, new Vector2Int(9, 0)));
    }

}
