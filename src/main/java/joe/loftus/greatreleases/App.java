package joe.loftus.greatreleases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		try {
			Connection conn = DriverManager
					.getConnection("jdbc:sqlite:src/main/java/joe/loftus/greatreleases/testjava.db");
			Statement statement = conn.createStatement();
			statement.execute("CREATE TABLE IF NOT EXISTS contacts " + " (name TEXT, phone INTEGER, email TEXT)");

//			statement
//					.execute("INSERT INTO contacts (name, phone, email) " + "VALUES('Joe', 45632, 'joe@anywhere.com')");
//
//			statement.execute(
//					"INSERT INTO contacts (name, phone, email) " + "VALUES('Jane', 4829484, 'jane@somewhere.com')");
//
//			statement.execute("INSERT INTO contacts (name, phone, email) " + "VALUES('Fido', 9038, 'dog@email.com')");

			statement.execute("SELECT * FROM contacts");
			ResultSet results = statement.getResultSet();
			while (results.next()) {
				System.out.println(
						results.getString("name") + " " + results.getInt("phone") + " " + results.getString("email"));
			}

			results.close();

			statement.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("Something went wrong: " + e.getMessage());
		}
		SpringApplication.run(App.class, args);
	}
}