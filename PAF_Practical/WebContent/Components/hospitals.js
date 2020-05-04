$(document).ready(function() {  
	
	if ($("#alertSuccess").text().trim() == "")  {   
		$("#alertSuccess").hide();  
		
	}  
	
	$("#alertError").hide();
});

//SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) {  
	
	// Clear status msges-------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation----------------  
	var status = validateItemForm(); 

	// If not valid-------------------  
	if (status != true)  {   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 


	// If valid-------------------------  
	var type = ($("#hidhosIDSave").val() == "") ? "POST" : "PUT"; 
	
	$.ajax( {  
		
		url : "HospitalsAPI",  
		type : type,  
		data : $("#formHospital").serialize(),  
		dataType : "text",  
		complete : function(response, status)  {   
			
			onHospitalSaveComplete(response.responseText, status);  
			
		} 
	}); 
	
}); 

function onHospitalSaveComplete(response, status) {  
	
	if (status == "success")  {   
		
		var resultSet = JSON.parse(response); 

			if (resultSet.status.trim() == "success")   {    
				
				$("#alertSuccess").text("Successfully saved.");    
				$("#alertSuccess").show(); 
				$("#divItemsGrid").html(resultSet.data);   
				
			} else if (resultSet.status.trim() == "error")   {    
				
				$("#alertError").text(resultSet.data);    
				$("#alertError").show();   
				
			} 

	} else if (status == "error")  {   
		
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
		
	} else  {   
		
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
		
	} 

	$("#hidhosIDSave").val("");  $("#formHospital")[0].reset(); } 

//INSERT ============================================ 
function validateItemForm() {  
	
	// REGNO  
	if ($("#hosRegno").val().trim() == "")  {   
		return "Insert Hospital registered NO.";  
		
	} 
	 
	 // NAME  
	if ($("#hosname").val().trim() == "")  {   
		return "Insert Hospital Name.";  
		
	} 
	
	// TYPE  
	if ($("#hostype").val().trim() == "")  {   
		return "Insert Hospital Type.";  
		
	} 
	 
	 // HOSPITAL CHARGE-------------------------------  
	if ($("#hosCharge").val().trim() == "")  {   
		return "Insert Hospital Charge.";  
		
	} 
	 
	 // is numerical value  
	var tmpPrice = $("#hosCharge").val().trim();  
	if (!$.isNumeric(tmpPrice))  {   
		return "Insert a numerical value for Hospital Charge.";  
		
	} 
	 
	 // convert to decimal price  
	$("#hosCharge").val(parseFloat(tmpPrice).toFixed(2)); 
	
	 
	 // ADDRESS------------------------  
	if ($("#address").val().trim() == "")  {   
		return "Insert Address.";  
		
	} 
	// CITY------------------------  
	if ($("#city").val().trim() == "")  {   
		return "Insert City.";  
		
	} 
	
	// EMAIL------------------------  
	if ($("#email").val().trim() == "")  {   
		return "Insert Email address.";  
		
	} 
	 
	 return true; 
	 
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) {     
	
	$("#hidhosIDSave").val($(this).closest("tr").find('#hidhosIDUpdate').val());     
	$("#hosRegno").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#hosname").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#hostype").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#hosCharge").val($(this).closest("tr").find('td:eq(3)').text());
	$("#address").val($(this).closest("tr").find('td:eq(4)').text());
	$("#city").val($(this).closest("tr").find('td:eq(5)').text());
	$("#email").val($(this).closest("tr").find('td:eq(6)').text());
	
	
});


//DELETE==========================================

$(document).on("click", ".btnRemove", function(event) {  
	
	$.ajax(  {   
		
		url : "HospitalsAPI",   
		type : "DELETE",   
		data : "hosID=" + $(this).data("hosid"),   
		dataType : "text",   
		complete : function(response, status)   {    
			onHospitalDeleteComplete(response.responseText, status);   
			
		}  
	}); 
	
}); 

function onHospitalDeleteComplete(response, status) {  
	
	if (status == "success")  {   
		
		var resultSet = JSON.parse(response); 

			if (resultSet.status.trim() == "success")   {    
				
				$("#alertSuccess").text("Successfully deleted.");    
				$("#alertSuccess").show(); 
				$("#divItemsGrid").html(resultSet.data);   
				
			} else if (resultSet.status.trim() == "error")   {    
				
				$("#alertError").text(resultSet.data);    
				$("#alertError").show();   
				
			} 

	} else if (status == "error")  {   
		
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
		
	} else  {   
		
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
		
	} 
	
}