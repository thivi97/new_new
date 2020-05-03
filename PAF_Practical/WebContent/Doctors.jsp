<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.model.Doctor" %>
<%@page import="com.servlet.DoctorAPI" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Doctor Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script type="Components/jquery-3.2.1.js"></script>
<script src="Components/doctor.js"></script>
</head>
<body>

<div class="container">
	<div class="row">
		<div class="col-8">
			<h1 class="m-3">Doctor Details</h1>
			<form id="formDoctor" name="formDoctor">
				Doctor Name:
				<input id="doctor_name" name="doctor_name" type="text" class="form-control form-control-sm">
				
				<br> Specialization:
				<input id="specialization" name="specialization" type="text" class="form-control form-control-sm">
				
				<br> Registration Number:
				<input id="regNo" name="regNo" type="text" class="form-control form-control-sm">
				
				<br> Address:
				<input id="address" name="address" type="text" class="form-control form-control-sm">
				
				<br> Phone Number:
				<input id="phone" name="phone" type="text" class="form-control form-control-sm">
				
				<br>
				<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
				
				<input type="hidden" id="hidDoctor_IDSave" name="hidDoctor_IDSave" value="">
				
				
			</form>
			
			<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			
			<br>
			<div id="divDoctorGrid">
				<%
					Doctor obj = new Doctor();
					out.print(obj.readDoctors());
				%>
			</div>
			
		</div>
	</div>
</div>

</body>
</html>