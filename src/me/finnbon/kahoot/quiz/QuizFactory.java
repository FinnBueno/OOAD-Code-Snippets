package me.finnbon.kahoot.quiz;

import me.finnbon.kahoot.connection.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * @author Finn Bon
 */
public class QuizFactory {
	private List<Question> questions;
	private String title;

	public Quiz create(User creator) {
		Quiz quiz = new Quiz(title, creator, questions);
		// TODO: Save quiz in database
		return quiz;
	}

	public QuizFactory addQuestion(String text, String correctAnswer, String... otherAnswers) {
		if (otherAnswers.length == 0) {
			throw new IllegalArgumentException("Question must have at least 2 answers!");
		}
		List<Answer> answers = Arrays.stream(otherAnswers).map(answer -> new Answer(answer, false)).collect(Collectors.toList());
		answers.add(new Answer(correctAnswer, true));
		this.questions.add(new Question(text, answers.toArray(new Answer[0])));
		return this;
	}

	public QuizFactory removeQuestion(int index) {
		questions.remove(index);
		return this;
	}

	public QuizFactory addAnswerToQuestion(int questionIndex, String answerText, boolean isCorrect) {
		Question question = questions.get(questionIndex);
		if (question.getAnswers().length >= 4) {
			throw new IllegalStateException("A question can have no more than 4 answers!");
		}
		Answer newAnswer = new Answer(answerText, isCorrect);
		if (isCorrect) {
			Arrays.stream(question.getAnswers()).forEach(qstn -> qstn.setIsCorrect(false));
		}
		question.addAnswer(newAnswer);
		return this;
	}

	public QuizFactory setTitle(String title) {
		this.title = title;
		return this;
	}
}
