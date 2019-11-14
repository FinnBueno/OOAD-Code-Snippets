package me.finnbon.kahoot.quiz;

/**
 * @author Finn Bon
 */
public class Answer {
	private String text;
	private boolean correct;

	public Answer(String text, boolean correct) {
		this.text = text;
		this.correct = correct;
	}

	public String getText() {
		return text;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setIsCorrect(boolean b) {
		this.correct = b;
	}

	@Override
	public String toString() {
		return (isCorrect() ? '+' : '-') + getText();
	}
}
