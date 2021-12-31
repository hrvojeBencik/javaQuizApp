package App;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Models.Question;
import Models.Quiz;
import Services.ApiController;
import Services.DBManager;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeScreen extends JFrame {
	private String loggedInUsername;

	private JPanel contentPane;

	public HomeScreen(String username) {
		this.loggedInUsername = username;
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 506);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(25, 25, 112));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("QuizApp");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 32));
		lblNewLabel.setBounds(257, 55, 181, 42);
		contentPane.add(lblNewLabel);
		
		JButton btn3Q = new JButton("3 QUESTIONS");
		btn3Q.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startQuiz(3);
			}
		});
		btn3Q.setForeground(new Color(255, 255, 255));
		btn3Q.setBackground(new Color(100, 149, 237));
		btn3Q.setBorderPainted(false);
		btn3Q.setFont(new Font("SansSerif", Font.BOLD, 16));
		btn3Q.setBounds(257, 151, 181, 42);
		contentPane.add(btn3Q);
		
		JButton btn5Q = new JButton("5 QUESTIONS");
		btn5Q.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startQuiz(5);
			}
		});
		btn5Q.setForeground(new Color(255, 255, 255));
		btn5Q.setOpaque(true);
		btn5Q.setFont(new Font("SansSerif", Font.BOLD, 16));
		btn5Q.setBorderPainted(false);
		btn5Q.setBackground(new Color(100, 149, 237));
		btn5Q.setBounds(257, 204, 181, 42);
		contentPane.add(btn5Q);
		
		JButton btn10Q = new JButton("10 QUESTIONS");
		btn10Q.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startQuiz(10);
			}
		});
		btn10Q.setForeground(new Color(255, 255, 255));
		btn10Q.setOpaque(true);
		btn10Q.setFont(new Font("SansSerif", Font.BOLD, 16));
		btn10Q.setBorderPainted(false);
		btn10Q.setBackground(new Color(100, 149, 237));
		btn10Q.setBounds(257, 257, 181, 42);
		contentPane.add(btn10Q);
		
		JButton btnHighScores = new JButton("HIGH SCORES");
		btnHighScores.setForeground(new Color(255, 255, 255));
		btnHighScores.setOpaque(true);
		btnHighScores.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnHighScores.setBorderPainted(false);
		btnHighScores.setBackground(new Color(100, 149, 237));
		btnHighScores.setBounds(257, 310, 181, 42);
		btnHighScores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RankScreen frame =  new RankScreen(loggedInUsername);
				frame.setVisible(true);
			}
		});
		contentPane.add(btnHighScores);
		
		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginScreen frame =  new LoginScreen();
				frame.setVisible(true);
			}
		});
		btnLogout.setForeground(new Color(255, 255, 255));
		btnLogout.setOpaque(true);
		btnLogout.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnLogout.setBorderPainted(false);
		btnLogout.setBackground(new Color(220, 20, 60));
		btnLogout.setBounds(257, 414, 181, 42);
		contentPane.add(btnLogout);
	}
	
	public void startQuiz(int questionAmount) {
		ApiController apiController = new ApiController();
		Question[] questions = apiController.getQuestions(null, null, questionAmount);
		if(questions != null && questions.length > 0) {
			Quiz quiz = new Quiz(questions, loggedInUsername);
			JPanel quizPanel = new QuizPanel(quiz, this);
			setContentPane(quizPanel);
			validate();
		}
		
	}
}
