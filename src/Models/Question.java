package Models;

import java.util.HashMap;

public class Question {
	 int id;
	 String question;
	 String description;
	 HashMap<String, Boolean> answers;
	 boolean multipleCorrectAnswers;
	 HashMap<String, Boolean> correct_answers;
	 String explanation;
	 String tip;
	 String category;
}
