package model;

import java.sql.*;
import util.DataBase;

public class MembershipLevels {

	
	public String readMembershipLevels() {
		String output = "";
		try {
			Connection con = DataBase.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Membership_Name</th>" + "<th>Pricing</th>"
					+ "<th>Benefits</th>" + "<th>ResearchID</th>" ;

			String query = "select * from membershiplevels";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set

			while (rs.next()) {
				String id = Integer.toString(rs.getInt("id"));
				String membership_Name = rs.getString("membership_Name");
				String pricing = Double.toString(rs.getDouble("progress"));
				String benefits = rs.getString("benefits");
				String researchID = Integer.toString(rs.getInt("researchID"));
				// Add into the html table
				output += "<td>" + id + "</td>";
				output += "<td>" + membership_Name + "</td>";
				output += "<td>" + pricing + "</td>";
				output += "<td>" + benefits + "</td>";
				output += "<td>" + researchID + "</td>";
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading ";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String insertMembershipLevels(String membership_Name, String pricing, String benefits, String researchID) {
		String output = "";
		try {
			Connection con = DataBase.connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into membershiplevels (`id`,`membership_Name`,`pricing`,`benefits`,`researchID`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, membership_Name);
			preparedStmt.setDouble(3, Double.parseDouble(pricing));
			preparedStmt.setString(4, benefits);
			preparedStmt.setInt(5, Integer.parseInt(researchID));
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

	public String updateMembershipLevels(String id,String membership_Name, String pricing, String benefits, String researchID) {
		String output = "";
		try {
			Connection con = DataBase.connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE membershiplevels SET membership_Name=?,pricing=?,benefits=?,researchID=? WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, membership_Name);
			preparedStmt.setDouble(2, Double.parseDouble(pricing));
			preparedStmt.setString(3, benefits);
			preparedStmt.setInt(4, Integer.parseInt(researchID));
			preparedStmt.setInt(5, Integer.parseInt(id));
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

	public String deleteMembershipLevels(String id) {
		String output = "";
		try {
			Connection con = DataBase.connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from membershiplevels where refID=?";
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
