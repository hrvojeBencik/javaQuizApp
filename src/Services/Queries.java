package Services;

public class Queries {
	public static String createUsersTable = "CREATE TABLE IF NOT EXISTS users (\n"
            + "	id integer PRIMARY KEY,\n"
            + "	username text NOT NULL,\n"
            + "	password text NOT NULL,\n"
            + "	createdAt DATETIME DEFAULT CURRENT_TIMESTAMP, \n"
            + "	lastSignedAt DATETIME DEFAULT CURRENT_TIMESTAMP \n"
            + ");";
}