package Services;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import Models.User;

import java.sql.ResultSet;


// As database I am using SQLite, so I am storing data locally

public class DBManager {
	private static final String dbUrl = "jdbc:sqlite:C:/sqlite/db/QuizApp.db";
	private Connection conn = null;
	private Statement stmt = null;
	
	public DBManager() {
		openConnection();
	}
	 
	public void openConnection() {
		try {
			conn = connect();
			stmt = conn.createStatement();
			createDatabase();
			createTable(Queries.createUsersTable);		
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	 
    private void createDatabase() {
        try {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbUrl);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void createTable(String sql) {     
        try {
            stmt.execute(sql);
            System.out.println("Table created successfully!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteTable(String tableName) {
    	String sql = String.format("DROP TABLE IF EXISTS %s", tableName);
    	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.executeUpdate();
            System.out.println(String.format("Table: %s is deleted successfully!", tableName));
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }
   
    
    // User CRUD operations
    public  void insertUser(String username, String password) {
        String sql = String.format("INSERT INTO %s(username, password) VALUES(?, ?)", TableName.users);

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            String hashedPassword = hashPassword(password);
            pstmt.setString(2, hashedPassword);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteUser(String username) {
    	String sql = "DELETE FROM users WHERE username = ?";
    	
    	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.executeUpdate();
            System.out.println(String.format("Successfully delete user: %s", username));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void updateUsersSignedInDate(String username) {
    	String sql = "UPDATE users SET lastSignedAt = ? WHERE username = ?";
    	
    	 try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		 pstmt.setTimestamp(1,  new Timestamp(System.currentTimeMillis()));
    		 pstmt.setString(2, username);
             pstmt.executeUpdate();
         } catch (SQLException e) {
             System.out.println(e.getMessage());
         }
    }
    public User checkIfUserExists(String username) {
    	String sql = String.format("SELECT username, password, lastSignedAt FROM users WHERE username = '%s'", username);
        
        try ( Statement stmt  = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
        	// This function checks if there is result with this user-name
        	if (!rs.isBeforeFirst() ) {    
        	    return null;
        	}  else {
        		while (rs.next()) {
        	        User user = new User(rs.getString("username"), rs.getString("password"), rs.getDate("lastSignedAt"));
        	        return user;
        	    }
        	}
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return null;
    }
    
    public void insertScore(String username, int score, String tableName) {
    	String sql = String.format("INSERT INTO %s(username, score) VALUES(?, ?)", tableName);

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setInt(2, score);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public String getScores(String tableName) {
    	String sql = String.format("SELECT * FROM %s ORDER BY score DESC", tableName);
    	  try ( Statement stmt  = conn.createStatement();
    	             ResultSet rs = stmt.executeQuery(sql)){
		    		  
		    		  String rankString = "";
		    		  int i = 1;
		    		  while (rs.next()) {
		    			  rankString = rankString.concat(i +  "\t" + 
		                                     rs.getString("username") + "\t" +
		                                     rs.getInt("score") + "\n");
		    			  i++;
		              }
		    		  return rankString;
    	  		} catch (SQLException e) {
    	            System.out.println(e.getMessage());
    	        }
    	  
    	  return "";
    	        
    }
    
    // This function creates hashed password using MD5 algorithm
    private String hashPassword(String password) {
    	String generatedPassword = null;
    	 try 
    	    {
    	      MessageDigest md = MessageDigest.getInstance("MD5");

    	      md.update(password.getBytes());

    	      byte[] bytes = md.digest();

    	      StringBuilder sb = new StringBuilder();
    	      for (int i = 0; i < bytes.length; i++) {
    	        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
    	      }

    	       generatedPassword = sb.toString();
    	    } catch (NoSuchAlgorithmException e) {
    	      e.printStackTrace();
    	    }
    	 
    	 return generatedPassword;
    }
    
    public Boolean checkIfPasswordMatches(String rawPassword, String hashedPassword) {
    	if(hashPassword(rawPassword).equals(hashedPassword)) return true;
    	return false;
    }
}
