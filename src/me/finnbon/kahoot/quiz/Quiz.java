package me.finnbon.kahoot.quiz;

import me.finnbon.kahoot.connection.User;
import me.finnbon.kahoot.quiz.Question;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Finn Bon
 */
public class Quiz implements Iterable<Question> {

	private User creator;
	private String title;
	private Question[] questions;

	public Quiz(String title, User creator, List<Question> questions) {
		this.title = title;
		this.creator = creator;
		this.questions = questions.toArray(new Question[0]);
	}

	public String getTitle() {
		return title;
	}

	@Override
	public Iterator<Question> iterator() {
		return Arrays.asList(questions).iterator();
	}
}
