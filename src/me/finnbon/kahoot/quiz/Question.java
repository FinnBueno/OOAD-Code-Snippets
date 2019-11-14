package me.finnbon.kahoot.quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Finn Bon
 */
public class Question {

	private String text;
	private List<Answer> answers;

	public Question(String text, Answer[] answers) {
		this.text = text;
		this.answers = new ArrayList<>(Arrays.asList(answers));
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return String.format(
			"%s\n%s",
			text,
			answers
				.stream()
				.map(Answer::toString)
				.collect(Collectors.joining("\n"))
		);
	}

	public Answer[] getAnswers() {
		return answers.toArray(new Answer[0]);
	}

	public void addAnswer(Answer newAnswer) {
		this.answers.add(newAnswer);
	}
}
