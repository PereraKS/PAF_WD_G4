package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {


		private Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.cj.jdbc.Driver");

		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection( "jdbc:mysql://localhost/paf?useTimezone=true&serverTimezone=UTC", "root", "");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con;
		 }
		
		
		public String insertUsers( String uname, String umail,String utype)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for inserting."; }
		 // create a prepared statement
		 String query = " INSERT INTO usermanagement(`UserID`, `UserName`, `UserMail`, `UserType`) VALUES (?, ?, ?, ?);";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, uname);
		 preparedStmt.setString(3, umail);
		 preparedStmt.setString(4, utype);
		// execute the statement
		
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while inserting the user";
		 System.err.println(e.getMessage());
		 e.printStackTrace();
		 }
		 return output;
		 }
		
		public String readUsers()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 
		 output = "<table border='1'>"
		 		+ "<tr><th>User ID</th>"
		 		+ "<th>User Name</th>" 
		 		+ "<th>User Mail</th>"
		 		+ "<th>User Type</th>"+
		 		"<th>Update</th><th>Remove</th></tr>";

		 String query = "select * from usermanagement";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String id = rs.getString("UserId");
		 String name= rs.getString("UserName");
		 String mail = rs.getString("UserMail");
		 String type = rs.getString("UserType");

		 // Add into the html table
		 output += "<tr><td>" + id+ "</td>";
		 output += "<td>" + name + "</td>";
		 output += "<td>" + mail + "</td>";
		 output += "<td>" + type + "</td>";
		 
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
		 + "<td><form method='post' action='items.jsp'>"
		 + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
		 + "<input name='itemID' type='hidden' value='" + id
		 + "'>" + "</form></td></tr>";
		 }
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the user";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		public String updateUsers(String UserId, String UserName, String UserMail, String UserType)
		
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE usermanagement SET UserName=?,UserMail=?,UserType=? WHERE UserId=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		
		 preparedStmt.setString(1, UserName);
		 preparedStmt.setString(2, UserMail);
		 preparedStmt.setString(3, UserType);
		 preparedStmt.setString(4, UserId);
		 System.out.println("no error1");
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the user";
		 System.err.println(e.getMessage());
		 e.printStackTrace();
		 }
		 return output;
		 }
		
		public String deleteUsers(String UserId)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from usermanagement where UserId=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1,UserId);
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the user";
		 System.err.println(e.getMessage());
		 e.printStackTrace();
		 }
		 return output;
		 } 
		
		
		
		
		
	}