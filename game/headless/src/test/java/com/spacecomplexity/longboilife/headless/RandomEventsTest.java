// NEW FOR PART 2

package com.spacecomplexity.longboilife.headless;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.spacecomplexity.longboilife.game.events.RandomEvents;
import com.spacecomplexity.longboilife.game.globals.GameState;
import com.spacecomplexity.longboilife.game.globals.Constants;

/**
 * Random Events tests
 * 
 *
 * ASSESSMENT 2 - NEW
 */
public class RandomEventsTest extends AbstractHeadlessGdxTest {
  @BeforeEach
  void setUp() {

    GameState.getState().reset();
    RandomEvents.getRandomEvent();
  }

  @Test
  void testBiasInitialization() {
    assertEquals(10, RandomEvents.LESS_INTERNATIONAL_STUDENTS.bias);
    assertEquals(10, RandomEvents.MAINTENANCE.bias);
    assertEquals(25, RandomEvents.OPEN_DAY.bias);
  }

  @Test
  void testResetBiases() {
    RandomEvents.CHARITY.bias = 50;

    RandomEvents.getRandomEvent();

    assertEquals(10, RandomEvents.CHARITY.bias);
  }

  @Test
  void testGetRandomEvent() {
    RandomEvents randomEvent = RandomEvents.getRandomEvent();

    assertNotNull(randomEvent);
    assertTrue(randomEvent.bias >= 10);
  }

  @Test
  void testEventExecution() {

    var gameState = GameState.getState();
    gameState.money = Constants.INITIAL_FUNDS;

    RandomEvents.LESS_INTERNATIONAL_STUDENTS.event.apply(new Object[0]);

    assertTrue(gameState.money < Constants.INITIAL_FUNDS);
  }

  @Test
  void testEventChoiceExecution() {
    var gameState = GameState.getState();
    gameState.money = Constants.INITIAL_FUNDS;

    RandomEvents.CHARITY.choices[0].event.apply(new Object[0]);

    assertTrue(gameState.money > Constants.INITIAL_FUNDS);
  }

  @Test
  void testEventChoiceExecution2() {
    var gameState = GameState.getState();
    gameState.money = Constants.INITIAL_FUNDS;

    RandomEvents.CHARITY.choices[1].event.apply(new Object[0]);

    assertTrue(gameState.money == Constants.INITIAL_FUNDS);
  }

  @Test
  void testOpenDayHasNoChoices() {
    assertEquals(0, RandomEvents.OPEN_DAY.choices.length);
  }
}