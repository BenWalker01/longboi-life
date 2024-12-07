package com.spacecomplexity.longboilife.game.globals;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Gdx;

public class LeaderboardSave {
    
    private LeaderboardSave() {
    // restricts instantiation of class
  }

  private static final Preferences prefs = Gdx.app.getPreferences(Constants.LEADERBOARD_PREFS);

  public static final String NAMES = "leaderboard.names";
  private static final String SCORES = "leaderboard.scores";

  public static String getNames() {
    return prefs.getString(NAMES, "");
  }

  public static void setName(String name) {
    prefs.putString(NAMES, name);
    prefs.flush();
  }

  public static String getScores() {
    return prefs.getString(SCORES, "");
  }

  public static void setScore(String score) {
    prefs.putString(SCORES, score);
    prefs.flush();
  }

  public static void clear() {
    prefs.clear();
    prefs.flush();
  }
}
