package App;

public class Main {

	public static void main(String[] args) {
		ApiController apiController = new ApiController();
		
		apiController.getQuestions(QuestionCategory.code.name(), QuestionDifficulty.Hard.name(), 3);
	}

}
	