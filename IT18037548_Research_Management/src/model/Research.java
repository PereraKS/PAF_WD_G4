package model;

import java.sql.*;
import util.DataBase;

public class Research {

	public String readResearch() {
		String output = "";
		String query = "select * from research";
		try (Connection con = DataBase.connect(); Statement stmt = con.createStatement();) {

			// Prepare the html table to be displayed
			output = "<table border='1'>" + "<tr><th>RID</th>" + "<th>Title</th>" + "<th>Category</th>"
					+ "<th>description</th>" + "<th>progress</th>" + "<th>estimateBudget</th>" + "<th>addedDate</th>"
					+ "<th>approvalStatus</th>" + "<th>resercherName</th>" + "<th>resercherEmail</th>";

			String category = "";
			String approvalStatus = "";

			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set

			while (rs.next()) {
				String id = Integer.toString(rs.getInt("id"));
				String title = rs.getString("title");

				// To get category name from category table
				String queryforcategory = "select categoryName from researchcategory WHERE categoryID = "
						+ rs.getString("category");
				try (Statement stmt1 = con.createStatement(); ResultSet rs1 = stmt1.executeQuery(queryforcategory);) {
					while (rs1.next()) {
						category = rs1.getString("categoryName");
					}
				}

				String description = rs.getString("description");
				String progress = Integer.toString(rs.getInt("progress"));
				String estimateBudget = Double.toString(rs.getDouble("estimateBudget"));
				String addedDate = rs.getString("addedDate");

				// To get Approval Status from Approval table
				String queryforapprovalStatus = "SELECT approval FROM researchstatus WHERE researchID =" + id;
				try (Statement stmt2 = con.createStatement();
						ResultSet rs2 = stmt2.executeQuery(queryforapprovalStatus);) {
					while (rs2.next()) {
						approvalStatus = rs2.getString("approval");
					}
				}

				String resercherName = rs.getString("resercherName");
				String resercherEmail = rs.getString("resercherEmail");

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
		String rid = "";
		String query = " insert into research (`id`,`title`,`category`,`description`,`progress`,`estimateBudget`,`addedDate`,`approvalStatus`,`resercherName`,`resercherEmail`)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection con = DataBase.connect(); PreparedStatement preparedStmt = con.prepareStatement(query);) {

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
			
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}

		// getting research id for send research to approval
		String queryrsmax = "select id from research ORDER BY id DESC LIMIT 1";
		try (Connection con = DataBase.connect(); Statement stmt = con.createStatement();) {

			ResultSet rs = stmt.executeQuery(queryrsmax);
			while (rs.next()) {
				rid = rs.getString("id");
			}

		} catch (Exception e) {
			System.out.print(e);
		}

		// send research to approval
		if (approvalStatus.equals("sendAP")) {
			ResearchApprovalStatus.insertToApproval(rid, progress, approvalStatus);
		}
		return output;
	}

	public String updateResearchData(String id, String title, String category, String description, String progress,
			String estimateBudget, String approvalStatus, String resercherName, String resercherEmail) {
		String output = "";
		String query = "UPDATE research SET title=?,category=?,description=?,progress=?,estimateBudget=?,approvalStatus=?,resercherName=?,resercherEmail=? WHERE id=?";

		try (Connection con = DataBase.connect(); PreparedStatement preparedStmt = con.prepareStatement(query);) {

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
			
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating";
			System.err.println(e.getMessage());
		}

		// send research to approval
		if (approvalStatus.equals("sendAP")) {
			ResearchApprovalStatus.insertToApproval(id, progress, approvalStatus);
		}
		return output;
	}

	public String deleteResearch(String id, String force) {
		String output = "";
		String checkapprovaltable = "";

		// Checking if an approved research is going to be deleted
		checkapprovaltable = ResearchApprovalStatus.checkApprovalTable(id);

		if (checkapprovaltable.equals("") || force.equals("forceDELETE")) {// if not approved or need force delete

			String query = "delete from research where id=?";
			try (Connection con = DataBase.connect(); PreparedStatement preparedStmt = con.prepareStatement(query);) {

				// binding values
				preparedStmt.setInt(1, Integer.parseInt(id));
				// execute the statement
				preparedStmt.execute();
				output = "Deleted successfully";
			} catch (Exception e) {
				output = "Error while deleting";
				System.err.println(e.getMessage());
			}

			// Delete research from Approval table
			ResearchApprovalStatus.deleteResearchFromApproval(checkapprovaltable);

		} else {
			output = "Youre Trying to Delete Approved Research ";
		}

		return output;
	}

}
