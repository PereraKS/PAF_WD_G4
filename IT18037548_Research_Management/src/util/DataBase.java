package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBase {

	public static Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/gadgetbadgetdb?useTimezone=true&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return con;
	}
}
