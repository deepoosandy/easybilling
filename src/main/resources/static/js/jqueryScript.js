$(document).ready(function() {
	
	// DO GET
	$.ajax({
		type : "GET",
		url : "list/listallitesm",
		success: function(result){
			$.each(result, function(i, listItemDetails){
				var customerRow = '<tr>' +
									'<td>' + listItemDetails.itemCode + '</td>' +
									'<td>' + listItemDetails.itemName.toUpperCase() + '</td>' +
									'<td>' + listItemDetails.itemDescription + '</td>' +
									'<td>' + listItemDetails.uploadedDate + '</td>' +
									'<td>' + listItemDetails.itemUnitPrice.effDate + '</td>' +
									'<td>' + listItemDetails.itemUnitPrice.thruDate + '</td>' +
								  '</tr>';
				
				$('#customerTable tbody').append(customerRow);
				
	        });
			
			$( "#customerTable tbody tr:odd" ).addClass("info");
			$( "#customerTable tbody tr:even" ).addClass("success");
		},
		error : function(e) {
			alert("ERROR: ", e);
			console.log("ERROR: ", e);
		}
	});
	
	// do Filter on View
	$("#inputFilter").on("keyup", function() {
	    var inputValue = $(this).val().toLowerCase();
	    $("#customerTable tr").filter(function() {
	    	$(this).toggle($(this).text().toLowerCase().indexOf(inputValue) > -1)
	    });
	});
})