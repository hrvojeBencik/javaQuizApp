package Services;

public class Queries {
	public static String createUsersTable = "CREATE TABLE IF NOT EXISTS users (\n"
            + "	id integer PRIMARY KEY,\n"
            + "	username text NOT NULL,\n"
            + "	password text NOT NULL,\n"
            + "	createdAt DATETIME DEFAULT CURRENT_TIMESTAMP, \n"
            + "	lastSignedAt DATETIME DEFAULT CURRENT_TIMESTAMP \n"
            + ");";
	
	public static String create3QuestionsRankingTable = "CREATE TABLE IF NOT EXISTS three_questions_rank (\n"
            + "	id integer PRIMARY KEY,\n"
            + "	username text NOT NULL,\n"
            + " score INTEGER NOT NULL,\n"
            + "	createdAt DATETIME DEFAULT CURRENT_TIMESTAMP \n"
            + ");";
	
	public static String create5QuestionsRankingTable = "CREATE TABLE IF NOT EXISTS five_questions_rank (\n"
            + "	id integer PRIMARY KEY,\n"
            + "	username text NOT NULL,\n"
            + " score INTEGER NOT NULL,\n"
            + "	createdAt DATETIME DEFAULT CURRENT_TIMESTAMP \n"
            + ");";
	
	public static String create10QuestionsRankingTable = "CREATE TABLE IF NOT EXISTS ten_questions_rank (\n"
            + "	id integer PRIMARY KEY,\n"
            + "	username text NOT NULL,\n"
            + " score INTEGER NOT NULL,\n"
            + "	createdAt DATETIME DEFAULT CURRENT_TIMESTAMP \n"
            + ");";
}