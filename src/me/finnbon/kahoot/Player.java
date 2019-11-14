package me.finnbon.kahoot;

import me.finnbon.kahoot.connection.SocketConnection;
import me.finnbon.kahoot.quiz.Answer;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Finn Bon
 */
public class Player extends SocketConnection {

	private String nickname;

	public Player(String nickname, String host, int port) {
		super(host, port);
		this.nickname = nickname;
	}

	public String getNickname() {
		return nickname;
	}

	public void updateAnswers(Answer[] answers) {
		super.send(Arrays.stream(answers).map(Answer::toString).collect(Collectors.joining("\n")).getBytes());
	}
}
