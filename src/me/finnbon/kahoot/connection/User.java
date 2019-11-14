package me.finnbon.kahoot.connection;

import me.finnbon.kahoot.Player;
import me.finnbon.kahoot.connection.SocketConnection;
import me.finnbon.kahoot.quiz.Answer;
import me.finnbon.kahoot.quiz.Question;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Finn Bon
 */
public class User extends SocketConnection {

	private String username;
	private String email;
	private byte[] password;

	public User(String host, int port) {
		super(host, port);
	}

	public boolean comparePassword(String password) {
		// TODO: Compare passwords
		return true;
	}

	public void sendAnswerAmount(int amount) {
		super.send(("ANSWER_AMOUNT|" + amount).getBytes());
	}

	public void updateQuestion(Question question) {
		super.send(("UPDATE_QUESTION|" + question.toString()).getBytes());
	}

	public void sendScoreboard(Map<Player, Answer> givenAnswers) {
		super.send(
			(
				"SCOREBOARD|" +
					givenAnswers
						.entrySet()
						.stream()
						.map(
							entry -> (
								entry.getValue().isCorrect() ? '+' : '-'
							) + entry.getKey().getNickname()
						)
						.collect(
							Collectors.joining("\n")
						)
			).getBytes()
		);
	}
}
