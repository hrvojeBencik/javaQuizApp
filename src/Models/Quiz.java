package Models;

public class Quiz {
	private int score = 0;
	private String username;
	private Question[] questions;
	
	public Quiz(Question[] questions, String username) {
		this.questions = questions;
		this.username = username;
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
	
	public String getUsername() {
		return this.username;
	}
}
