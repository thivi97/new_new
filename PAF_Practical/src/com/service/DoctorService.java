package com.service;

import javax.ws.rs.PathParam;
//For rest service
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//for xml
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.model.Doctor;

//json
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/Doctors")
public class DoctorService {

	Doctor obj = new Doctor();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertDoctor(@FormParam("doctor_name")String doctor_name,@FormParam("specialization")String specialization,@FormParam("regNo")String regNo,@FormParam("address")String address,@FormParam("phone")String phone) {
		String output = obj.insertDoctor(doctor_name, specialization, regNo, address, phone);
		
		return output;
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readDoctors() {
		return obj.readDoctors();
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateDoctorDetails(String doctorRec) {
		JsonObject itemObject = new JsonParser().parse(doctorRec).getAsJsonObject();
		
		String doctor_id = itemObject.get("doctor_id").getAsString();
		String name = itemObject.get("doctor_name").getAsString();
		String spec = itemObject.get("specialization").getAsString();
		String reg = itemObject.get("regNo").getAsString();
		String addr = itemObject.get("address").getAsString();
		String phn = itemObject.get("phone").getAsString();
		
		String output = obj.updateDoctorDetails(doctor_id, name, spec, reg, addr, phn);
		
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteDoctorDetails(String doctorRec) {
		Document doc = Jsoup.parse(doctorRec,"",Parser.xmlParser());
		
		String doctor_id = doc.select("doctor_id").text();
		String output = obj.deleteDoctorDetails(doctor_id);
		return output;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_HTML)
	public String readSpecifiedDoctor(@PathParam("id")String d_id) {
		return obj.readSpecifiedDoctors(d_id);
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAppointmentsForSpecifiedDoctors(@PathParam("id")String d_id) {
		return obj.readAppointmentsForSpecifiedDoctors(d_id);
	}
	
}
