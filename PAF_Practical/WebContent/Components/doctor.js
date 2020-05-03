$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
			$("#alertSuccess").hide();
	}
	$("#alertError").hide();
}); 

//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	 
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateDoctorForm();
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("hidDoctor_IDSave").val() == "") ? "POST" : "PUT";

	
	event.preventDefault();
	$.ajax(
			{
			 url : "DoctorsAPI",
			 type : type,
			 data : $("#formDoctor").serialize(),
			 dataType : "text",
			 complete : function(response, status)
			 {
			 onDoctorSaveComplete(response.responseText, status);
			 }
	});
	
	
}); 

function onDoctorSaveComplete(response, status)
{
	if (status == "success")
	{
			var resultSet = JSON.parse(response);
			if (resultSet.status.trim() == "success")
			{
				$("#alertSuccess").text("Successfully saved.");
				$("#alertSuccess").show();
				
				$("#divDoctorGrid").html(resultSet.data);
			} else if (resultSet.status.trim() == "error")
			{
				$("#alertError").text(resultSet.data);
				$("#alertError").show();
			}
	} else if (status == "error")
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidDoctor_IDSave").val("");
	$("#formDoctor")[0].reset();
}


//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidDoctor_IDSave").val($(this).closest("tr").find('#hidDoctorIDUpdate').val());
	$("#doctor_name").val($(this).closest("tr").find('td:eq(0)').text());
	$("#specialization").val($(this).closest("tr").find('td:eq(1)').text());
	$("#regNo").val($(this).closest("tr").find('td:eq(2)').text());
	$("#address").val($(this).closest("tr").find('td:eq(3)').text());
	$("#phone").val($(this).closest("tr").find('td:eq(4)').text());
	
	
});

$(document).on("click", ".btnRemove", function(event)
		{
	event.preventDefault();
	 
		 $.ajax(
		 {
			 url : 'DoctorsAPI',
			 type : 'DELETE',
			 data : "doctor_id=" + $(this).data("doctorid"),
			 dataType : 'text',
			 complete : function(response, status)
			 {onDoctorDeleteComplete(response.responseText, status);}
		 });
		});

function onDoctorDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			
			$("#divDoctorGrid").html(resultSet.data);
		}else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if (status == "error")
	{
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	}else
	{
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}


function validateDoctorForm()
{
	if ($("#doctor_name").val().trim() == "")
	 {
	 return "Insert Name!!!";
	 } 
	
	if ($("#specialization").val().trim() == "")
	 {
	 return "Insert specialization!!!";
	 } 
	
	if ($("#regNo").val().trim() == "")
	 {
	 return "Insert Registration Number!!!";
	 } 
	
	if ($("#address").val().trim() == "")
	 {
	 return "Insert Address!!!";
	 } 
	
	if ($("#phone").val().trim() == "")
	 {
	 return "Insert phone!!!";
	 } 
	
	return true;
	

}





















