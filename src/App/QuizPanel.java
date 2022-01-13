package App;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Models.Quiz;
import Services.DBManager;
import Services.Queries;
import Services.TableName;
import java.awt.Component;
import java.awt.Dimension;


public class QuizPanel extends JPanel{
	private JFrame frame;
	private DBManager db;
	private String selectedTableName = TableName.threeQuestionRank;
	private String createTableQuery = Queries.create3QuestionsRankingTable;

	private Quiz quiz;
	private int currentQuestion = 0;
	private JButton[] answerButtons = new JButton[6];
	private JLabel questionLabel = new JLabel();
	JButton newQuestionButton = new JButton();

	
	
	public QuizPanel(Quiz quiz, JFrame frame) {
		this.quiz = quiz;
		this.frame = frame;
		
		db = new DBManager();
		int questionNumber = quiz.getQuestions().length;
		switch(questionNumber) {
		case 5:
			selectedTableName = TableName.fiveQuestionRank;
			createTableQuery = Queries.create10QuestionsRankingTable;
			break;
		case 10:
			selectedTableName = TableName.tenQuestionRank;
			createTableQuery = Queries.create10QuestionsRankingTable;
			break;
		default:
			selectedTableName = TableName.threeQuestionRank;
			createTableQuery = Queries.create3QuestionsRankingTable;
			break;
		}
		db.createTable(createTableQuery);
		
		
		setBackground(new Color(25, 25, 112));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBounds(100, 100, 693, 456);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		questionLabel.setText(quiz.getQuestions()[currentQuestion].getQuestion());
		questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		questionLabel.setForeground(new Color(255, 255, 255));
		questionLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		
		add(questionLabel);
		add(Box.createRigidArea(new Dimension(0, 30)));


		initializeAnswerButtons();
		newQuestionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		newQuestionButton.setAlignmentY(JComponent.BOTTOM_ALIGNMENT);
		newQuestionButton.setText("Next question");
		newQuestionButton.setForeground(new Color(255, 255, 255));
		newQuestionButton.setBackground(new Color(100, 149, 237));
		newQuestionButton.setBorderPainted(false);
		newQuestionButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		newQuestionButton.setMaximumSize(new Dimension(200, 40));
		newQuestionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchQuestions();
			}
		});
		
	}
	
	public void initializeAnswerButtons() {
		for(int i = 0; i < quiz.getQuestions()[currentQuestion].getAnswers().size(); i++) {
			answerButtons[i] = new JButton();
			// This line creates array of answers fetched from getAnswers() map values
			String[] answers =  quiz.getQuestions()[currentQuestion].getAnswers().values().toArray(new String[quiz.getQuestions()[currentQuestion].getAnswers().size()]);
			if(answers[i] != null ) {
				answerButtons[i].setAlignmentX(Component.CENTER_ALIGNMENT);

				answerButtons[i].setText(answers[i]);
				answerButtons[i].setFont(new Font("SansSerif", Font.PLAIN, 16));
				answerButtons[i].setForeground(new Color(0, 0, 0));
				answerButtons[i].setBackground(new Color(255, 255, 255));
				

				answerButtons[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String correctAnswer = "";
						String pressedButtonText = e.getActionCommand();
						// Check if answer is correct
						Boolean[] correctAnswers = quiz.getQuestions()[currentQuestion].correct_answers.values().toArray(new Boolean[quiz.getQuestions()[currentQuestion].correct_answers.size()]);
						
						for(int j = 0; j < correctAnswers.length; j++) {
							if(correctAnswers[j]) {
								// Change color of correct answer button
								answerButtons[j].setBackground(new Color(0, 255, 0));
								correctAnswer = answerButtons[j].getText();
							} else {
								answerButtons[j].setBackground(new Color(255, 0, 0));
							}
						}
						
						// Add score if correct button is pressed
						if(pressedButtonText == correctAnswer) {
							quiz.addScore(1);
						}
					}
				});
				add(answerButtons[i]);
			}
			
		}
		if(currentQuestion == quiz.getQuestions().length-1) {
			newQuestionButton.setText("Finish Quiz");
		}
		add(newQuestionButton);
	}
	
	public void switchQuestions() {
		remove(newQuestionButton);
		if(currentQuestion != quiz.getQuestions().length-1) {
			for (JButton component : answerButtons) {
				   component.setVisible(false);
				   remove(component);
				}
			currentQuestion++;
		
			questionLabel.setText(quiz.getQuestions()[currentQuestion].getQuestion());
			initializeAnswerButtons();
		} else {
			// Add score to database and go back to home-screen
			db.insertScore(quiz.getUsername(), quiz.getScore(), selectedTableName);
			
			// Closes current frame and opens new one
			this.frame.dispose();
			HomeScreen frame = new HomeScreen(quiz.getUsername());
			setVisible(false);
			frame.setVisible(true);
		}
	}
	
	
}
