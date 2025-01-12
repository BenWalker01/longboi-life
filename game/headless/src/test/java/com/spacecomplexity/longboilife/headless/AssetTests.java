package com.spacecomplexity.longboilife.headless;

import com.spacecomplexity.longboilife.game.globals.Filepaths;

import com.badlogic.gdx.Gdx;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssetTests extends AbstractHeadlessGdxTest {

        @Test
        public void testFallbackAssetExists() {
                assertTrue(Gdx.files.internal(Filepaths.FALLBACK_ASSET).exists(),
                                "The Fallback asset should be available");
        }

        @Test
        public void testMapAssetExists() {
                assertTrue(Gdx.files.internal(Filepaths.MAP_ASSET).exists(),
                                "The map asset should be available");
        }

        @Test
        public void testBuildingAssetsExists() {
                assertTrue(Gdx.files.internal(Filepaths.GREGGS_ASSET).exists(),
                                "The Gregg's asset exists");
                assertTrue(Gdx.files.internal(Filepaths.GYM_ASSET).exists(),
                                "The gym asset exists");
                assertTrue(Gdx.files.internal(Filepaths.HALLS_ASSET).exists(),
                                "The halls asset exists");
                assertTrue(Gdx.files.internal(Filepaths.LIBRARY_ASSET).exists(),
                                "The library asset exists");
                assertTrue(Gdx.files.internal(Filepaths.BASKETBALL_ASSET).exists(),
                                "The basketball asset exists");
        }

        @Test
        public void testRoadAssetsExists() {
                assertTrue(Gdx.files.internal(Filepaths.ROAD_3WAY_ASSET).exists(),
                                "The 3-way road asset exists");
                assertTrue(Gdx.files.internal(Filepaths.ROAD_4WAY_ASSET).exists(),
                                "The 4-way road asset exists");
                assertTrue(Gdx.files.internal(Filepaths.ROAD_CORNER_ASSET).exists(),
                                "The corner road asset exists");
                assertTrue(Gdx.files.internal(Filepaths.ROAD_STRAIGHT_ASSET).exists(),
                                "The straight road asset exists");
        }

        @Test
        public void testMenuAssetsExists() {
                assertTrue(Gdx.files.internal(Filepaths.MENU_BG_ASSET).exists(),
                                "The menu background asset exists");
        }

        @Test
        public void testTileAssetsExists() {
                assertTrue(Gdx.files.internal(Filepaths.GRASS_TILE_ASSET).exists(),
                                "The grass tile asset exists");
                assertTrue(Gdx.files.internal(Filepaths.WATER_TILE_ASSET).exists(),
                                "The water tile asset exists");
        }

        @Test
        public void testUiButtonsAssetsExists() {
                assertTrue(Gdx.files.internal(Filepaths.PAUSE_BUTTON_ASSET).exists(),
                                "The pause button asset exists");
                assertTrue(Gdx.files.internal(Filepaths.PLAY_BUTTON_ASSET).exists(),
                                "The play button asset exists");
        }

        @Test
        public void testUiFontsAssetsExists() {
                assertTrue(Gdx.files.internal(Filepaths.MEDIUM_FONT_ASSET).exists(),
                                "The medium font asset exists");
                assertTrue(Gdx.files.internal(Filepaths.REGULAR_FONT_ASSET).exists(),
                                "The regular font asset exists");
        }

        @Test
        public void testUiSkinAssetsExists() {
                assertTrue(Gdx.files.internal(Filepaths.BUTTON_FONT_ASSET).exists(),
                                "The button font asset exists");
                assertTrue(Gdx.files.internal(Filepaths.LABEL_FONT_ASSET).exists(),
                                "The label font asset exists");
                assertTrue(Gdx.files.internal(Filepaths.TITLE_FONT_ASSET).exists(),
                                "The title font asset exists");
                assertTrue(Gdx.files.internal(Filepaths.SKIN_ATLAS_ASSET).exists(),
                                "The skin atlas asset exists");
                assertTrue(Gdx.files.internal(Filepaths.SKIN_JSON_ASSET).exists(),
                                "The skin json asset exists");
                assertTrue(Gdx.files.internal(Filepaths.SKIN_PNG_ASSET).exists(),
                                "The skin png asset exists");
        }

        @Test
        public void testAudioAssetsExist() {
                assertTrue(Gdx.files.internal(Filepaths.SOUNDTRACK).exists(),
                                "The main soundtrack exists");
                assertTrue(Gdx.files.internal(Filepaths.GAME_OVER_SOUND).exists(),
                                "The game over sound exists");
                assertTrue(Gdx.files.internal(Filepaths.CLICK_SOUND).exists(),
                                "The click sound exists");
                assertTrue(Gdx.files.internal(Filepaths.SELECT_BUILDING_SOUND).exists(),
                                "The select building sound exists");
                assertTrue(Gdx.files.internal(Filepaths.BUILD_SOUND).exists(),
                                "The build sound exists");
                assertTrue(Gdx.files.internal(Filepaths.SELL_SOUND).exists(),
                                "The sell sound exists");
                assertTrue(Gdx.files.internal(Filepaths.PAUSE_SOUND).exists(),
                                "The pause sound exists");
        }

}
