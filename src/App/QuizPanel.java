package App;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Models.Quiz;

public class QuizPanel extends JPanel{
	private Quiz quiz;
	private int currentQuestion = 0;
	private JButton[] answerButtons = new JButton[6];
	
	public QuizPanel(Quiz quiz) {
		this.quiz = quiz;
		
		setBackground(new Color(25, 25, 112));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel qustionLabel = new JLabel(quiz.getQuestions()[currentQuestion].getQuestion());
		qustionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		qustionLabel.setForeground(new Color(255, 255, 255));
		qustionLabel.setFont(new Font("SansSerif", Font.PLAIN, 32));
		qustionLabel.setBounds(257, 55, 181, 42);
		add(qustionLabel);
		
		initializeAnswerButtons();
	}
	
	public void initializeAnswerButtons() {
		System.out.println(quiz.getQuestions()[currentQuestion].getAnswers().size());
		for(int i = 0; i < quiz.getQuestions()[currentQuestion].getAnswers().size(); i++) {
			answerButtons[i] = new JButton();
			answerButtons[i].setText(quiz.getQuestions()[currentQuestion].getAnswers().entrySet().toArray()[i].toString());
			answerButtons[i].setFont(new Font("SansSerif", Font.PLAIN, 16));
			answerButtons[i].setForeground(new Color(0, 0, 0));
			answerButtons[i].setBackground(new Color(255, 255, 255));
			add(answerButtons[i]);
		}
	}
	
	
}
