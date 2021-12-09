package Models;

import java.sql.Date;

public class User {
	private String username;
	private String password;
	private Date lastSignedAt;
	
	public User(String username, String password, Date lastSignedAt) {
		this.username = username;
		this.password = password;
		this.lastSignedAt = lastSignedAt;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public Date getLastSignedAt() {
		return this.lastSignedAt;
	}
	
	public void setLastSignedIn(Date lastSignedIn) {
		this.lastSignedAt = lastSignedIn;
	}
}
