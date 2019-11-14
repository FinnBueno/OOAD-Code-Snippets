package me.finnbon.kahoot;

import me.finnbon.kahoot.connection.User;
import me.finnbon.kahoot.quiz.Answer;
import me.finnbon.kahoot.quiz.Question;
import me.finnbon.kahoot.quiz.Quiz;
import me.finnbon.kahoot.state.GameState;

import java.util.*;

@SuppressWarnings("unused")
public class Game {

	private User host;
	private String pin;
	private List<Player> players;
	private Quiz quiz;
	private Iterator<Question> questions;
	private GameState state;
	private Question currentQuestion;
	private Map<Player, Answer> givenAnswers;

	public Game(User host, String pin, Quiz quiz) {
		this.host = host;
		this.pin = pin;
		this.quiz = quiz;
		this.players = new LinkedList<>();
		this.state = GameState.LOBBY;
		// use TreeMap to maintain order of insertion and assign points by that
		//noinspection SortedCollectionWithNonComparableKeys
		this.givenAnswers = new TreeMap<>();
	}

	public int getAmountOfPlayers() {
		return players.size();
	}

	public void storeAnswer(Answer answer, Player player) {
		this.givenAnswers.put(player, answer);
		this.host.sendAnswerAmount(givenAnswers.size());
	}

	public void addPlayer(Player player) {
		if (state != GameState.LOBBY) {
			throw new IllegalStateException("Cannot add players to a game that has already started.");
		}
		this.players.add(player);
	}

	public void removePlayer(Player player) {
		this.players.remove(player);
	}

	public void progressGame() {
		GameState newState = state.advanceGame(this);
		if (newState != null) {
			state = newState;
		}
	}

	public Question nextQuestion() {
		if (questions.hasNext()) {
			this.currentQuestion = questions.next();
			this.host.updateQuestion(this.currentQuestion);
			this.players.forEach(player -> player.updateAnswers(this.currentQuestion.getAnswers()));
			this.host.sendScoreboard(givenAnswers);
			this.persistAndClearAnswers();
			return this.currentQuestion;
		} else {
			return this.currentQuestion = null;
		}
	}

	private void persistAndClearAnswers() {
		// TODO: Persist answers to database
		this.givenAnswers.clear();
	}

	public void restartQuestionIterator() {
		questions = quiz.iterator();
	}
}