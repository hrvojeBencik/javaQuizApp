package Models;

public class Quiz {
	private int score = 0;
	private Question[] questions;
	
	public Quiz(Question[] questions) {
		this.questions = questions;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void addScore(int amount) {
		this.score += amount;
	}
	
	public Question[] getQuestions() {
		return this.questions;
	}
}
