package Services;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.google.gson.*;

import Models.Question;

public class ApiController {
	final private String apiKey = "U0ji5HjwPC75LrxgK9kD6A6RaYuJAV57UKd8tEGV";
	final private String baseUrl = "https://quizapi.io/api/v1/";
	// Questions end point is created separately because in the future there will be more end points so it will be easier to implement
	final private String questionsEndPoint = "questions";
	
	// GET request, if category and difficulty are not declared they will be randomized and if limit is not declared it will be 10
	// Response is JSON string that will be converted into Question objects
	public Question[] getQuestions(String category, String difficulty, int limit) {
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

				try {
					Gson g = new Gson();
					Question[] questions = g.fromJson(response.toString(), Question[].class);
					return questions;
				} catch(Exception e) {
					System.out.println("Trying to fetch data exception: " + e.getMessage());
				    return null;
				}
			} else {
				System.out.println("GET request not worked");
			    return null;
			}
		} catch(Exception e) {
			System.out.println("Exception: " + e.getMessage());
		    return null;
		} finally {
			if(connection != null) {
				connection.disconnect();
				System.out.println("Connection is closed.");
			}
		}
	}
}
