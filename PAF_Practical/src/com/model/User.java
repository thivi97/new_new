package com.model;

import java.sql.*;

public class User {

	private Connection connect() {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3305/practical","root","msdhoni07@T");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public String readUsers() {
		String output = "";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			//prepare the html tables to be displayed
			output = "<table border=1 padding=10><tr style=\"text-align:center;\"><th>User ID</th><th>First Name</th><th>Last Name</th>"+"<th>Age</th><th>Gender</th><th>Email</th><th>Address</th><th>Phone</th><th>Username</th><th>Password</th><th>Update</th><th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from user";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//duplicate the rows 
			while(rs.next()) {
				String userID = Integer.toString(rs.getInt("userID"));
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String age = Integer.toString(rs.getInt("age"));
				String gender = rs.getString("gender");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String phoneNumber = rs.getString("phoneNumber");
				String username = rs.getString("username");
				String password = rs.getString("password");
				
				
			
			//add into the html table 
				output += "<tr><td><input id='hiUserIDSave' name='hiUserIDSave' type='hidden' value= '" + userID + "'>" + userID + "</td>";
				
				output += "<td>" + firstName + "</td>";
				output += "<td>" + lastName + "</td>";
				output += "<td>" + age + "</td>";
				output += "<td>" + gender + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + phoneNumber + "</td>";
				output += "<td>" + username + "</td>";
				output += "<td>" + password + "</td>";
				
			//buttons
				output  += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>" + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-userid= '" + userID + "'>" + "</td></tr>";
				
			}
			
			con.close();
			
			//complete the html table
			output += "</table>";
			
			
		}catch(Exception e) {
			output = "Error while reading the users.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String insertUser(String firstName,String lastName,String age,String gender,String email,String address,String phoneNumber,String username,String password) {
		String output = "";
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			//create prepared statement
			String query = "insert into user(userID,firstName,lastName,age,gender,email,address,phoneNumber,username,password)" + "values(?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2,firstName);
			preparedStmt.setString(3, lastName);
			preparedStmt.setInt(4, Integer.parseInt(age));
			preparedStmt.setString(5, gender);
			preparedStmt.setString(6, email);
			preparedStmt.setString(7, address);
			preparedStmt.setString(8, phoneNumber);
			preparedStmt.setString(9, username);
			preparedStmt.setString(10, password);
			
			
			//execute the statement
			preparedStmt.execute();
			con.close();
			
			String newUsers = readUsers();
			
			output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";
			
		} catch(Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the user.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String updateUser(String ID, String firstName, String lastName, String age, String gender, String email, String address, String phoneNumber, String username, String password) {
		
		String output = "";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the database for updating.";
			}
			
			//creating a prepared statement
			
			String query = "update user set firstName=?, lastName=?, age=?, gender=?,email=?,address=?,phoneNumber=?,username=?,password=? where userID =?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//binding values
			preparedStmt.setString(1, firstName);
			preparedStmt.setString(2, lastName);
			preparedStmt.setInt(3, Integer.parseInt(age));
			preparedStmt.setString(4, gender);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, address);
			preparedStmt.setString(7, phoneNumber);
			preparedStmt.setString(8, username);
			preparedStmt.setString(9, password);
			preparedStmt.setInt(10, Integer.parseInt(ID));
			
			//execute the statement
			preparedStmt.execute();
			con.close();
			
			String newUsers = readUsers();
			output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";
			
		}catch(Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while updating the user.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String deleteUser(String userID) {
		String output = "";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the database for deleting.";
			}
			
			//create a prepared statement
			String query = "delete from user where userID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1, Integer.parseInt(userID));
			
			//execute the statement
			preparedStmt.execute();
			con.close();
			
			
			String newUsers = readUsers();
			output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";
		}catch(Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the user.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
}
