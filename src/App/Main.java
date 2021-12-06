package App;

import Models.QuestionDifficulty;
import Services.ApiController;

public class Main {

	public static void main(String[] args) {
		ApiController apiController = new ApiController();
		
		apiController.getQuestions(null, QuestionDifficulty.Hard.name(), 3);
	}

}
	