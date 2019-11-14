package me.finnbon.kahoot.state;

import me.finnbon.kahoot.Game;

/**
 * @author Finn Bon
 */
public interface GameState {
	// finished state will never redirect to another state
	GameState FINISHED = game -> null;
	GameState SCOREBOARD = new ScoreboardState();
	// this state's progress is only ever triggered by the host's timer or them clicking the next question button
	GameState QUESTION = game -> SCOREBOARD;
	// simply see if we're ready to start
	GameState LOBBY = game -> game.getAmountOfPlayers() > 3 ? QUESTION : null;

	GameState advanceGame(Game game);
}
