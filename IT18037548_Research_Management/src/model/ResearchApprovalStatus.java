package model;

import java.sql.*;
import util.DataBase;

public class ResearchApprovalStatus {

	
	public static String checkApprovalTable(String id) {
		String output = "";
		try {
			Connection con = DataBase.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			String query = "SELECT approvalid FROM researchstatus WHERE researchID = " + id + "&& approval = 'yes' ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				output = Integer.toString(rs.getInt("approvalid"));
			}
			con.close();
		} catch (Exception e) {
			output = "Error while reading ";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String readApprovalstatus() {
		String output = "";
		try {
			Connection con = DataBase.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>researchID</th>" + "<th>progress</th>"
					+"<th>approval</th></tr>";

			String query = "select * from researchstatus";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				String researchID =  Integer.toString(rs.getInt("researchID"));
				String progress = Integer.toString(rs.getInt("progress"));
				String approval = rs.getString("approval");
				// Add into the html table
				output += "<td>" + researchID + "</td>";
				output += "<td>" + progress + "</td>";
				output += "<td>" + approval + "</td>";
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
	
	public static String insertToApproval(String id,String progress,String approvalStatus) {
		String output = "";
		try {
			Connection con = DataBase.connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into researchstatus (`approvalid`, `researchID`, `progress`, `approval`)"
					+ " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setInt(2, Integer.parseInt(id));
			preparedStmt.setInt(3, Integer.parseInt(progress));
			preparedStmt.setString(4, approvalStatus);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Sent for Approval";
		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		return output;
		
	}

	public static String deleteResearchFromApproval(String ID) {
		String output = "";
		try {
			Connection con = DataBase.connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from researchstatus where approvalid=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(ID));
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
