package com.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.Date;

import org.apache.tomcat.jni.Time;

public class Appointment {
	
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
	
	public String insertAppointment(String date,String time,String patId,String docId,String payId) {
		String output = "";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			String query = "insert into appointment ('date','time','patientID','doctorID','paymentID') values (?,?,?,?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			preparedStmt.setString(1, date);
			preparedStmt.setString(2, time);
			preparedStmt.setInt(3, Integer.parseInt(patId));
			preparedStmt.setInt(4, Integer.parseInt(docId));
			preparedStmt.setInt(5, Integer.parseInt(payId));
			
			
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}catch(Exception e) {
			output = "Error while inserting the appointment";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	//view appointment details
	
	public String getAppointment(String appointmentID) {
		String output = "";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the database for get appointments.";
			}
			
			output = "<table border=><tr><th>Appointment ID</th><th>Date</th><th>Time</th>" + "<th>Patient ID</th><th>Doctor ID</th><th>Payment ID</th><th>Status</th></tr>";
			
			String query = "select * from appointment where appointmentID="+appointmentID;
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			
			while(rs.next()) {
				String AppID = Integer.toString(rs.getInt("appointmentID"));
				String date = rs.getString("date");
				String time = rs.getString("time");
				String patientID = rs.getString("patientID");
				String doctorID = rs.getString("doctorID");
				String paymentID = Integer.toString(rs.getInt("paymentID"));
				String status = rs.getString("appointmentStatus");
				
				output += "<tr><td>" + AppID + "</td>";
				output += "<tr><td>" + date + "</td>";
				output += "<tr><td>" + time + "</td>";
				output += "<tr><td>" + patientID + "</td>";
				output += "<tr><td>" + doctorID + "</td>";
				output += "<tr><td>" + paymentID + "</td>";
				output += "<tr><td>" + status + "</td>";
				
			}
			
			con.close();
			output += "</table>";
			return output;
		}catch(Exception e) {
			output = "Error while get all appointments.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	
	//
	public String getAppointment() {
		String output = "";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the database for get appointments.";
			}
			
			output = "<table border=><tr><th>Appointment ID</th><th>Date</th><th>Time</th>" + "<th>Patient ID</th><th>Doctor ID</th><th>Payment ID</th><th>Status</th></tr>";
			
			String query = "select * from appointment";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			
			while(rs.next()) {
				String AppID = Integer.toString(rs.getInt("appointmentID"));
				String date = rs.getString("date");
				String time = rs.getString("time");
				String patientID = rs.getString("patientID");
				String doctorID = rs.getString("doctorID");
				String paymentID = Integer.toString(rs.getInt("paymentID"));
				String status = rs.getString("appointmentStatus");
				
				output += "<tr><td><input id=\"hidAppointmentIDUpdate\"name=\"hidAppointmentIDUpdate\"type=\"hidden\" value=\""
						+ AppID + "\">" +  "</td>";
				
				output += "<tr><td>" + AppID + "</td>";
				output += "<tr><td>" + date + "</td>";
				output += "<tr><td>" + time + "</td>";
				output += "<tr><td>" + patientID + "</td>";
				output += "<tr><td>" + doctorID + "</td>";
				output += "<tr><td>" + paymentID + "</td>";
				output += "<tr><td>" + status + "</td>";
				
				output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-warning btnUpdate\"></td>"
						+ "<td><form method=\"post\" action=\"Appointment.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
						+ "<input name=\"hidAppointmentIDDelete\" type=\"hidden\" value=\"" + AppID + "\">"
						+ "</form></td></tr>";
				
			}
			
			con.close();
			output += "</table>";
			return output;
		}catch(Exception e) {
			output = "Error while get all appointments.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String updateAppointment(String appointmentID,String date,String time,String patientID,String doctorID,String paymentID,String appointmentStatus) {
		String output = "";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the database for updating.";
			}
			
			String query = "update appointment set date=?,time=?,patientID=?,doctorID=?,paymentID=?,appointmentStatus=? where appointmentID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//binding values
			preparedStmt.setString(1, date);
			preparedStmt.setString(2, time);
			preparedStmt.setInt(3, Integer.parseInt(patientID));
			preparedStmt.setInt(4, Integer.parseInt(doctorID));
			preparedStmt.setInt(5, Integer.parseInt(paymentID));
			preparedStmt.setString(6, appointmentStatus);
			preparedStmt.setInt(7, Integer.parseInt(appointmentID));
			
			preparedStmt.execute();
			con.close();
			output = "Updated successfully.";
		}catch(Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	//delete appointment
	public String deleteAppointment(String appointmentID) {
		String output = "";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the database for deleting.";
			}
			
			String query = "delete from appointment where appointmentID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			preparedStmt.setInt(1, Integer.parseInt(appointmentID));
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully.";
		}catch(Exception e) {
			output = "Error while deleting the appointment.";
			
			System.err.println(e.getMessage());
		}
		
		return output;
		
	}

}
