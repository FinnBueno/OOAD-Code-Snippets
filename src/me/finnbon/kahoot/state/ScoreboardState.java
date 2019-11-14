package me.finnbon.kahoot.state;

import me.finnbon.kahoot.Game;

/**
 * @author Finn Bon
 */
public class ScoreboardState implements GameState {
	@Override
	public GameState advanceGame(Game game) {
		return game.nextQuestion() == null ? FINISHED : QUESTION;
	}
}
