package App;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import Models.User;
import Services.DBManager;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginScreen extends JFrame {

	private JPanel loginScreenPane;
	private JTextField usernameField;
	private JTextField passwordField;

	public LoginScreen() {
		setResizable(false);
		setFont(new Font("SansSerif", Font.PLAIN, 12));
		setForeground(new Color(25, 25, 112));
		setBackground(new Color(25, 25, 112));
		setTitle("QuizApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		loginScreenPane = new JPanel();
		loginScreenPane.setBackground(new Color(25, 25, 112));
		loginScreenPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(loginScreenPane);
		loginScreenPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome!");
		lblNewLabel.setBounds(144, 46, 152, 42);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginScreenPane.add(lblNewLabel);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("SansSerif", Font.PLAIN, 14));
		usernameField.setBounds(144, 99, 152, 30);
		loginScreenPane.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("SansSerif", Font.PLAIN, 14));
		passwordField.setColumns(10);
		passwordField.setBounds(144, 140, 152, 30);
		loginScreenPane.add(passwordField);
		
		JButton loginBtn = new JButton("Login/Register");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBManager db = new DBManager();
				// Get email and password values, if there is no such user-name in the DB show pop-up for creating new account if user-name is existing log the user in.
				final String username = usernameField.getText().trim();
				final String password = passwordField.getText().trim();
				
				if(!username.isEmpty() && !password.isEmpty() && password.length() > 5) {
					User user = db.checkIfUserExists(username);
					if(user != null) {
						if(db.checkIfPasswordMatches(password, user.getPassword())) {
							// User exists and good password is entered, logging in
							db.updateUsersSignedInDate(user.getUsername());
							new HomeScreen().setVisible(true);
							dispose();
						} else {
							// Wrong password
							JOptionPane.showMessageDialog(loginScreenPane, "You have entered wrong passwod", "Error", JOptionPane.ERROR_MESSAGE);
							passwordField.setText("");
						}
					} else {
						// User does not exist, ask him if he wants to register
						int result = JOptionPane.showConfirmDialog(loginScreenPane, "There is no user with this credentials would you like to register?", "No user found", JOptionPane.YES_NO_OPTION);

					    if (result == JOptionPane.YES_OPTION ) {
					       db.insertUser(username, password);
					       new HomeScreen().setVisible(true);
					       dispose();
					     } else{
					    	 // User pressed no
					    	usernameField.setText("");
							passwordField.setText("");
					     }
					}
				} else {
					JOptionPane.showMessageDialog(loginScreenPane, "You need to fill all input fields and password must be longer than 6 characters.", "Wrong input", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		loginBtn.setForeground(new Color(255, 255, 255));
		loginBtn.setBackground(new Color(100, 149, 237));
		loginBtn.setBorderPainted(false);
		loginBtn.setOpaque(true);
		loginBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
		loginBtn.setBounds(144, 181, 152, 30);
		loginScreenPane.add(loginBtn);
	}
}
