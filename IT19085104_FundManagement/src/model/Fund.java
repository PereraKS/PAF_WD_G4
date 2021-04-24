//IT19085104
//Somawansa R.P.

package model;

import java.sql.*;

public class Fund {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget", "root", "raveesha123");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	//insert function
	public String insertFund(String id, String researchID, String name, String price, String comments, String email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into fund(`ID`,`researchID`,`name`,`price`,`comments`,`email`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, id);
			preparedStmt.setString(2, researchID);
			preparedStmt.setString(3, name);
			preparedStmt.setFloat(4, Float.parseFloat(price));
			preparedStmt.setString(5, comments);
			preparedStmt.setString(6, email);

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//read function
	public String readFunds() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Fund Code</th><th>ResearchID</th>" + "<th>Name</th>" + "<th>Price</th>"
					+ "<th>Comments</th>" + "<th>Email</th>" + "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from fund";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String FundID = rs.getString("ID");
				String researchID = rs.getString("researchID");
				String name = rs.getString("name");
				String price = Float.toString(rs.getFloat("price"));
				String comments = rs.getString("comments");
				String email = rs.getString("email");

				// Add into the html table
				output += "<tr><td>" + FundID + "</td>";
				output += "<td>" + researchID + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + price + "</td>";
				output += "<td>" + comments + "</td>";
				output += "<td>" + email + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='funds.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='ID' type='hidden' value='" + FundID + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	//update function
	public String updateFund(String ID, String researchID, String name, String price, String comments, String email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE fund SET researchID=?,name=?,price=?,comments=?,email=? WHERE ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, ID);
			preparedStmt.setString(2, researchID);
			preparedStmt.setString(3, name);
			preparedStmt.setFloat(4, Float.parseFloat(price));
			preparedStmt.setString(5, comments);
			preparedStmt.setString(6, email);

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//delete function
	public String deleteFund(String ID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from fund where ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, ID);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
