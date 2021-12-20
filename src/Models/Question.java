package Models;

import java.util.HashMap;

public class Question {
	 public int id;
	 private String question;
	 public String description;
	 private HashMap<String, Boolean> answers;
	 public boolean multipleCorrectAnswers;
	 public HashMap<String, Boolean> correct_answers;
	 public String explanation;
	 public String tip;
	 public String category;
	 
	 public String getQuestion() {
		 return this.question;
	 }
	 
	 public HashMap<String, Boolean> getAnswers() {
		 return this.answers;
	 }
}
