package App;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Services.DBManager;
import Services.TableName;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class RankScreen extends JFrame {
	private String username;
	private DBManager db = new DBManager();
	private String selectedTableName = TableName.threeQuestionRank;

	private JPanel contentPane;

	public RankScreen(String username) {
		this.username = username;
		String scores = db.getScores(selectedTableName);
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        dispose();
		        HomeScreen frame =  new HomeScreen(username);
				frame.setVisible(true);
		    }
		});
		
		setResizable(false);
		setBounds(100, 100, 500, 650);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea ranks = new JTextArea();
		ranks.setEditable(false);
		ranks.setBounds(30, 76, 420, 524);
		ranks.setText(scores);
		contentPane.add(ranks);
		
		JButton btn3Questions = new JButton("3 QUESTIONS");
		btn3Questions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn3Questions.setBounds(30, 25, 120, 40);
		btn3Questions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedTableName = TableName.threeQuestionRank;
				String scores = db.getScores(selectedTableName);
				ranks.setText(scores);
			}
		});
		contentPane.add(btn3Questions);
		
		JButton btn5Questions = new JButton("5 QUESTIONS");
		btn5Questions.setBounds(180, 25, 120, 40);
		btn5Questions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedTableName = TableName.fiveQuestionRank;
				String scores = db.getScores(selectedTableName);
				ranks.setText(scores);
			}
		});
		contentPane.add(btn5Questions);
		
		JButton btn10Questions = new JButton("10 QUESTIONS");
		btn10Questions.setBounds(330, 25, 120, 40);
		btn10Questions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedTableName = TableName.tenQuestionRank;
				String scores = db.getScores(selectedTableName);
				ranks.setText(scores);
			}
		});
		contentPane.add(btn10Questions);
		
		
	}
}
