<%@ page import="com.model.Hospital" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital Management</title>
<link rel="stylesheet" href="View/bootstrap.min.css">
<script type="text/javascript" src="Components/jquery-3.2.1.js"></script>
<script type="text/javascript" src="Components/hospitals.js"></script>
</head>
<body>
<div class="container">
<div class="row">
<div class="col-6">
<h1>Hospital Management</h1>

<form id="formHospital" name="formHospital">

Hospital Register Number:
<input id="hosRegno" name="hosRegno" type="text" class="form-control form-control-sm">

<br>Hospital Name:
<input id="hosname" name="hosname" type="text" class="form-control form-control-sm">

<br>Hospital Type:
<input id="hostype" name="hostype" type="text" class="form-control form-control-sm">

<br>Hospital Charge:
<input id="hosCharge" name="hosCharge" type="text" class="form-control form-control-sm">

<br>Address:
<input id="address" name="address" type="text" class="form-control form-control-sm">

<br>City:
<input id="city" name="city" type="text" class="form-control form-control-sm">

<br>Email:
<input id="email" name="email" type="text" class="form-control form-control-sm">

<br>
<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
<input type="hidden" id="hidhosIDSave" name="hidhosIDSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>

<div id="divItemGrid">
<%
	Hospital hosObj = new Hospital();
	out.print(hosObj.readBranch());
%>
</div>


</div>
</div>
</div>

</body>
</html>