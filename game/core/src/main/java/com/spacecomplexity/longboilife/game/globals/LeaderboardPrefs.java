// NEW FOR PART 2

package com.spacecomplexity.longboilife.game.globals;

import com.badlogic.gdx.Preferences;

import com.badlogic.gdx.Gdx;

public class LeaderboardPrefs {

  private LeaderboardPrefs() {
    // restricts instantiation of class
  }

  private static final Preferences prefs = Gdx.app.getPreferences(Constants.LEADERBOARD_PREFS);

  public static final String NAMES = "leaderboard.names";
  private static final String SCORES = "leaderboard.scores";

  public static final String initialNames = "Longboi,Alice,Bob,";
  public static final String initialScores = "75.00,50.00,10.00,";

  public static String displayName = System.getProperty("user.name");

  public static String getNames() {
    return prefs.getString(NAMES, initialNames);
  }

  public static void writeNames(String newNames) {
    prefs.putString(NAMES, newNames);
    prefs.flush();
  }

  public static String getScores() {
    return prefs.getString(SCORES, initialScores);
  }

  public static void writeScores(String score) {
    prefs.putString(SCORES, score);
    prefs.flush();
  }

  public static void clear() {
    prefs.clear();
    prefs.flush();
  }

  public static void setDisplayName(String name) {
    displayName = name;
  }
}
