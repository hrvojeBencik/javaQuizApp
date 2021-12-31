package Models;

import java.util.Map;

public class Question {
	 public int id;
	 private String question;
	 public String description;
	 private Map<String, String> answers;
	 public Boolean multipleCorrectAnswers;
	 public Map<String, Boolean> correct_answers;
	 public String explanation;
	 public String tip;
	 public String category;
	 
	 public String getQuestion() {
		 return this.question;
	 }
	 
	 public Map<String, String> getAnswers() {
		 return this.answers;
	 }
}
