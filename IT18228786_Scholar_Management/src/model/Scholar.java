package model;

import java.sql.*;

public class Scholar {

	private Connection connect() {
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

	public String readresearchstatus() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			output = "<table border='1'><tr><th>Ref ID</th><th>researchID</th>" + "<th>progress</th>"
					+ "<th>comment</th>" + "<th>approval</th></tr>";

			String query = "select * from researchstatus";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String refID = Integer.toString(rs.getInt("refID"));
				String researchID = Integer.toString(rs.getInt("progress"));
				String progress = Integer.toString(rs.getInt("progress"));
				String comment = rs.getString("comment");
				String approval = rs.getString("approval");
				// Add into the html table
				output += "<td>" + refID + "</td>";
				output += "<td>" + researchID + "</td>";
				output += "<td>" + progress + "</td>";
				output += "<td>" + comment + "</td>";
				output += "<td>" + approval + "</td>";
				output += "<td><form method='post' action='researchstatus.jsp'>"
						+ "<input name='sid' type='hidden' value='" + refID + "'>" + "</form></td></tr>";
			}
			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading ";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String insertresearchstatus(String researchID, String progress, String comment, String approval) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into researchstatus (`refID`,`researchID`,`progress`,`comment`,`approval`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setInt(2, Integer.parseInt(researchID));
			preparedStmt.setInt(3, Integer.parseInt(progress));
			preparedStmt.setString(4, comment);
			preparedStmt.setString(5, approval);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatesresearchstatus(String refID, String researchID, String progress, String comment,
			String approval) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE researchstatus SET researchID=?,progress=?,comment=?,approval=? WHERE refID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(researchID));
			preparedStmt.setInt(2, Integer.parseInt(progress));
			preparedStmt.setString(3, comment);
			preparedStmt.setString(4, approval);
			preparedStmt.setInt(5, Integer.parseInt(refID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteresearchstatus(String refID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from researchstatus where refID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(refID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
