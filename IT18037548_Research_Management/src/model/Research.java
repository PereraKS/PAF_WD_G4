package model;

import java.sql.*;
import util.DataBase;

public class Research {


	public String readResearch() {
		String output = "";
		try {
			Connection con = DataBase.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'>" + "<tr><th>RID</th>" + "<th>Title</th>" + "<th>Category</th>"
					+ "<th>description</th>" + "<th>progress</th>" + "<th>estimateBudget</th>" + "<th>addedDate</th>"
					+ "<th>approvalStatus</th>" + "<th>resercherName</th>" + "<th>resercherEmail</th>";

			String category="";
			String approvalStatus="";
			
			String query = "select * from research";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set

			while (rs.next()) {
				String id = Integer.toString(rs.getInt("id"));
				String title = rs.getString("title");
				//To get category name from category table 
				String queryforcategory = "select categoryName from researchcategory WHERE categoryID = "+ rs.getString("category");
				Statement stmt1 = con.createStatement();
				ResultSet rs1 = stmt1.executeQuery(queryforcategory);
				while (rs1.next()) {
					category = rs1.getString("categoryName");
				}
				String description = rs.getString("description");
				String progress = Integer.toString(rs.getInt("progress"));
				String estimateBudget = Double.toString(rs.getDouble("estimateBudget"));
				String addedDate = rs.getString("addedDate");
				
				String queryforapprovalStatus = "SELECT approval FROM researchstatus WHERE researchID ="+ id;
				Statement stmt2 = con.createStatement();
				ResultSet rs2 = stmt2.executeQuery(queryforapprovalStatus);
				while (rs2.next()) {
					approvalStatus = rs2.getString("approval");
				}
				
//				String approvalStatus = rs.getString("approvalStatus");
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
				output += "<td>" + resercherEmail + "</td></tr>";
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

	//Send for Approval
	public String insertToApproval(String id,String progress,String approvalStatus) {
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
	
	public String insertResearch(String title, String category, String description, String progress,
			String estimateBudget, String addedDate, String approvalStatus, String resercherName,
			String resercherEmail) {
		String output = "";
		String RID="";
		
		try {
			Connection con = DataBase.connect();
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
			output = output + "Inserted successfully" + approvalStatus;
		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		
		
		try {
			Connection con = DataBase.connect();
			
			String queryrsmax = "select id from research ORDER BY id DESC LIMIT 1";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(queryrsmax);
			while (rs.next()) {
				RID = rs.getString("id");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// to send research to approval
		if(approvalStatus.equals("sendAP") ) {
			insertToApproval(RID, progress, approvalStatus);
		}
		return output;
	}

	public String updateResearchData(String id, String title, String category, String description, String progress,
			String estimateBudget,String approvalStatus, String resercherName, String resercherEmail) {
		String output = "";
		try {
			Connection con = DataBase.connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE research SET title=?,category=?,description=?,progress=?,estimateBudget=?,approvalStatus=?,resercherName=?,resercherEmail=? WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, title);
			preparedStmt.setString(2, category);
			preparedStmt.setString(3, description);
			preparedStmt.setInt(4, Integer.parseInt(progress));
			preparedStmt.setDouble(5, Double.parseDouble(estimateBudget));
			preparedStmt.setString(6, approvalStatus);
			preparedStmt.setString(7, resercherName);
			preparedStmt.setString(8, resercherEmail);
			preparedStmt.setInt(9, Integer.parseInt(id));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating";
			System.err.println(e.getMessage());
		}
		
		if(approvalStatus.equals("sendAP") ) {
			insertToApproval(id, progress, approvalStatus);
		}
		return output;
	}

	public String deleteResearch(String id) {
		String output = "";
		try {
			Connection con = DataBase.connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from research where id=?";
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
