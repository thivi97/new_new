package com.service;

import com.model.Appointment;
import java.sql.Date;
import java.sql.Time;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Path("/appointment")
public class AppointmentService {

	Appointment appObj = new Appointment();
	
	@GET
	@Path("/")
	@Produces({MediaType.TEXT_HTML})
	public String getAppointment() {
		return appObj.getAppointment();
	}
	
	//view appointment by passing a appointment ID as parameter
	
	@GET
	@Path("/single")
	@Produces({MediaType.TEXT_HTML})
	@Consumes(MediaType.APPLICATION_JSON)
	public String getSingleAppointment(String id) {
		JsonObject appObject = new JsonParser().parse(id).getAsJsonObject();
		
		String appointmentID = appObject.get("appointmentID").getAsString();
		
		return appObj.getAppointment(appointmentID);
	}
	
	//inserting appointment details
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAppointment(@FormParam("date") String date,@FormParam("time") String time,@FormParam("patientID") String patientID,@FormParam("doctorID") String doctorID,@FormParam("paymentID") String paymentID) {
		String output = appObj.insertAppointment(date, time, patientID, doctorID, paymentID);
		
		return output;
	}
	
	
	//updating appointment details
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAppointment(String appData) {
		JsonObject appointmentObj = new JsonParser().parse(appData).getAsJsonObject();
		
		String appointmentID = appointmentObj.get("appointmentID").getAsString();
		String date = appointmentObj.get("date").getAsString();
		String time = appointmentObj.get("time").getAsString();
		String patientID = appointmentObj.get("patientID").getAsString();
		String doctorID = appointmentObj.get("doctorID").getAsString();
		String paymentID = appointmentObj.get("paymentID").getAsString();
		String appointmentStatus = appointmentObj.get("appointmentStatus").getAsString();
		
		String output = appObj.updateAppointment(appointmentID, date, time, patientID, doctorID, paymentID, appointmentStatus);
		
		return output;
		
		
	}
	
	
	//deleting appointment
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAppointment(String appID) {
		Document doc = Jsoup.parse(appID,"",Parser.xmlParser());
		
		String AppId = doc.select("appID").text();
		String output = appObj.deleteAppointment(AppId);
		return output;
	}
	
	
}
