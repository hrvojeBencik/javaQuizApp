package App;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class ApiController {
	final String apiKey = "U0ji5HjwPC75LrxgK9kD6A6RaYuJAV57UKd8tEGV";
	final String baseUrl = "https://quizapi.io/api/v1/";
	// Questions end point is created separately because in the future there will be more end points so it will be easier to implement
	final String questionsEndPoint = "questions";
	
	// GET request, if category and difficulty are not declared they will be randomized and if limit is not declared it will be 10
	// Response is JSON string that will be converted into Question objects
	public void getQuestions(String category, String difficulty, int limit) {
		HttpURLConnection connection = null;
		String rawUrl = this.baseUrl + this.questionsEndPoint + "?apiKey=" + this.apiKey;
		
		if(category != null) {
			rawUrl = rawUrl + "&category=" + category;
		}
		
		if(difficulty != null) {
			rawUrl = rawUrl + "&difficulty=" + difficulty;
		}
		
		if(limit == 0) {
			limit = 10;
		} 
		
		rawUrl = rawUrl + "&limit=" + limit;
		
		System.out.println("Raw URL: " + rawUrl);
		
		try {
			URL url = new URL(rawUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Language", "en-US");  
			
		    int responseCode = connection.getResponseCode();
			System.out.println("GET Response Code :: " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				System.out.println(response.toString());
			} else {
				System.out.println("GET request not worked");
			}
		} catch(Exception e) {
			System.out.println("Exception: " + e.getMessage());
		} finally {
			if(connection != null) {
				connection.disconnect();
				System.out.println("Connection is closed.");
			}
		}
	}
}
