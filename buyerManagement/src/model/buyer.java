package model;
import util.database;
import java.awt.PageAttributes.MediaType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



public class buyer {

	//inserting
	
	public String insertBuyer(String name, String email, String phone) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = database.connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 // create a prepared statement
	 String query = " insert into buyer (`id`,`name`,`email`,`phone`)"
	 + " values (?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, name); 
	 preparedStmt.setString(3, email); 
	 preparedStmt.setInt(4, Integer.parseInt(phone));
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while inserting the buyer."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	//read 
	
	
	public String readBuyer() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = database.connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Buyer Name</th><th>Buyer Email</th>" +
	 "<th>Buyer Phone</th>" + 
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from buyer"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String id= Integer.toString(rs.getInt("id")); 
	 String name = rs.getString("name"); 
	 String email = rs.getString("email"); 
	 String phone = rs.getString("phone"); 

	
	 // Add into the html table
	 output += "<td>" + name + "</td>"; 
	 output += "<td>" + email + "</td>"; 
	 output += "<td>" + phone + "</td>"; 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='items.jsp'>"
	 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='itemID' type='hidden' value='" + id
	 + "'>" + "</form></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the buyers."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	public String updateBuyer(String id, String name, String email, String phone)
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = database.connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE buyer SET name=?,email=?,phone=? WHERE id=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, name); 
		 preparedStmt.setString(2, email); 
		 preparedStmt.setInt(3,Integer.parseInt(phone)); 
		 preparedStmt.setInt(4, Integer.parseInt(id)); 

		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while updating the buyer."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
	
	//delete
	public String deleteBuyer(String id) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = database.connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from buyer where id=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(id)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while deleting the buyer."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
	

	
}
