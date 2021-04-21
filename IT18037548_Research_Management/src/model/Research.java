package model;

import java.sql.*;

public class Research {

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

	public String readResearch() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'>" + "<tr><th>RID</th>" + "<th>Title</th>" + "<th>Category</th>"
					+ "<th>description</th>" + "<th>progress</th>" + "<th>estimateBudget</th>" + "<th>addedDate</th>"
					+ "<th>approvalStatus</th>" + "<th>resercherName</th>" + "<th>resercherEmail</th>"

					+ "<th>Update</th>" + "<th>Remove</th></tr>";

			String query = "select * from research";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set

			while (rs.next()) {
				String id = Integer.toString(rs.getInt("id"));
				String title = rs.getString("title");
				String category = rs.getString("category");
				String description = rs.getString("description");
				String progress = Integer.toString(rs.getInt("progress"));
				String estimateBudget = Double.toString(rs.getDouble("estimateBudget"));
				String addedDate = rs.getString("addedDate");
				String approvalStatus = rs.getString("approvalStatus");
				String resercherName = rs.getString("resercherName");
				String resercherEmail = rs.getString("resercherEmail");
				// Add into the html table
				output += "<tr><td>" + id + "</td>";
				output += "<td>" + title + "</td>";
				output += "<td>" + category + "</td>";
				output += "<td>" + description + "</td>";
				output += "<td>" + progress + "</td>";
				output += "<td>" + estimateBudget + "</td>";
				output += "<td>" + addedDate + "</td>";
				output += "<td>" + approvalStatus + "</td>";
				output += "<td>" + resercherName + "</td>";
				output += "<td>" + resercherEmail + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='research.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='itemID' type='hidden' value='" + id + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String insertResearch(String title, String category, String description, String progress,
			String estimateBudget, String addedDate, String approvalStatus, String resercherName,
			String resercherEmail) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into research (`id`,`title`,`category`,`description`,`progress`,`estimateBudget`,`addedDate`,`approvalStatus`,`resercherName`,`resercherEmail`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, title);
			preparedStmt.setString(3, category);
			preparedStmt.setString(4, description);
			preparedStmt.setInt(5, Integer.parseInt(progress));
			preparedStmt.setDouble(6, Double.parseDouble(estimateBudget));
			preparedStmt.setString(7, addedDate);
			preparedStmt.setString(8, approvalStatus);
			preparedStmt.setString(9, resercherName);
			preparedStmt.setString(10, resercherEmail);

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

	public String updateResearchData(String id, String title, String category, String description, String progress,
			String estimateBudget, String resercherName, String resercherEmail) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE research SET title=?,category=?,description=?,progress=?,estimateBudget=?,resercherName=?,resercherEmail=? WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, title);
			preparedStmt.setString(2, category);
			preparedStmt.setString(3, description);
			preparedStmt.setInt(4, Integer.parseInt(progress));
			preparedStmt.setDouble(5, Double.parseDouble(estimateBudget));
			preparedStmt.setString(6, resercherName);
			preparedStmt.setString(7, resercherEmail);
			preparedStmt.setInt(8, Integer.parseInt(id));
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

	public String deleteResearch(String id) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from research where itemID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id));
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
