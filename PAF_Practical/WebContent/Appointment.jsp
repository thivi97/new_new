<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.model.Appointment" %>
<%@ page import="com.service.AppointmentService" %>

<%
	if(request.getParameter("date") != null){
		Appointment app = new Appointment();
		String stsMsg = "";
		
		if(request.getParameter("AppointmentIDsave") == ""){
			stsMsg = app.insertAppointment(request.getParameter("date"),request.getParameter("time"),request.getParameter("patientID"),request.getParameter("doctorID"),request.getParameter("paymentID"));
		}
		else{
			stsMsg = app.updateAppointment(request.getParameter("AppointmentIDsave"),request.getParameter("date"),request.getParameter("time"),request.getParameter("patientID"),request.getParameter("doctorID"),request.getParameter("paymentID"),request.getParameter("appointmentStatus"));
		}
		
		session.setAttribute("statusMsg",stsMsg);
		
	}

	if(request.getParameter("hidAppointmentIDDelete") != null){
		Appointment app = new Appointment();
		String stsMsg = app.deleteAppointment(request.getParameter("hidAppointmentIDDelete"));
		
		session.setAttribute("statusMsg",stsMsg);
	}

%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="views/bootstrap.css">
<title>Appointment</title>
</head>
<body>

<form id="formAppointment" name="formAppointment" method="post" action="Appointment.jsp">
Date: <input id="date" name="date" type="text" class="form-control form-control-sm" required> <br>

Time: <input id="time" name="time" type="text" class="form-control form-control-sm" required> <br>

Patient ID: <input id="patientID" name="patientID" type="text" class="form-control form-control-sm" required> <br>

Doctor ID: <input id="doctorID" name="doctorID" type="text" class="form-control form-control-sm" required> <br>

Status: <input id="appointmentStatus" name="appointmentStatus" type="text" class="form-control form-control-sm" required> <br>

Payment ID: <input id="paymentID" name="paymentID" type="text" class="form-control form-control-sm" required> <br>


<input id="btnSave" name="btnSave" type="submit" value="Save" class="btn btn-primary"> 
			
<input type="hidden" id="AppointmentIDsave" name="AppointmentIDsave" value="">
						
</form>

<div id="alertSuccess" class="alert alert-success">
<%
	out.print(session.getAttribute("statusMsg"));
%>
</div>

<%
	Appointment app = new Appointment();
	out.print(app.getAppointment());
%>

<br><br>


</body>
</html>