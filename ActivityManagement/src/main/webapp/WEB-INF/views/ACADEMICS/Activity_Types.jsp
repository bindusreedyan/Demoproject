<link rel="stylesheet" href="./sfs_public/css/jquery-confirm.min.css">
<script src="./sfs_public/js/jquery-confirm.min.js"></script>


<script>


/*
function  centreDialog(titelData,contentData,typeData)
{
	$.confirm({
	    title: titelData,
	    content: contentData,
	    type: typeData,
	    typeAnimated: true,
	    buttons: {	        
	        close: function () {
	        }
	    }
	});
};
*/


function loadAllAcademicActivityTypeDetails() {
	$.ajax({
		"url" : "/academicactivity/getAllActivityTypes",
		"method" : "GET",
		success : function(data) {
			
			console.log(data);
			
			setUpDataTable(data, [ {
				"activityTypeCode" : "Acitivity Type Code"
			}, {
				"description" : "Description"
			},
			
			{
				"status" : "Status"
			}
		  ], "activityTypeTable", "select-checkbox");

			
			onDataTableClick('activityTypeTable', function() {
				
			})

			

		}
	});

}

$(document).ready(function()
{
				
	$('#menuHaed').text("");

	
	loadAllAcademicActivityTypeDetails();

	/*restrictSpecialChars("hstlId");	
	restrictSpecialChars("hostelNameId");	
	restrictSpecialChars("addressId");		
	restrictSpecialChars_allow_dot_comma("amenitiesId");
	restrictSpecialChars_allow_dot_comma("geoCoordinatesId");*/
	//restrictSpecialChars_allow_dot_comma("remarksId");
	
	
	
	/*$("#areaOfHostelId").keypress(function (e) {
        var length = jQuery(this).val().length;
      if(length > 9) {
           return false;
      } 
      else if((length > 0) && (e.which == 46)) { //This condition is required for floating point numbers...
          return true;
     } 
      else if(e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
           return false;
      } else if((length == 0) && (e.which == 48)) {
           return false;
      }
      
      
	});*/
   
	

	
$('#academicActivityTypeDetailsForm').unbind().bind('submit',function(event) 
{
			event.preventDefault();
			var formData = $(this).serializeArray();
		
			//formData.push({"name":"","value":""});
			
			var formCheck = "success";

			var htype = $("#hostelTypeId").val();
			var floors = $("#noOfFloorsId").val();

			
			if(htype == 'se' || floors == 'se')
			{
				formCheck = "fail";
				
			}
				
			if(formCheck == "success")	
			{
				console.log("");	

				$('#submitModel').modal('show');

				$('#confId').unbind().bind('click',function(){

					console.log("confirm clicked...");

					$.ajax({
						"url" : "/hms/saveHostelMaster",
						"method" : "POST",
						data : formData,
						success : function(data) {
							console.log(data);

							var datasplit = data.split("-");
							
							if(datasplit[0] == "SAVED")
							{
								//showMessage("Hostel Floor Details Saved.");
								
								centreDialog("Success","Hostel Saved. Details: <br/>"+datasplit[1],"green");

								$("#hostelMasterDetailsForm").trigger('reset');
								clearDataTable("hostelTable");
								loadAllHostelDetails();
							}
							else if(datasplit[0] == "NOTSAVED")
							{
								centreDialog("Error","Error or Hostel is already available.<br/>Please take further Steps.","red");
							}
							
						},
						error : function(e) {
							//showMessage("Error in Uploading Hostel Master Details. Contact Admin");
							
						}
					});

					$('#submitModel').modal('hide');
					
				});
				
				$('#cancelId').unbind().bind('click',function(){

					console.log("Cancel clicked...");

					$('#submitModel').modal('hide');
					
				});


											
				
	
				}
			else
			{
				centreDialog("Error","Select All the Required Fields..","red");				
			}
			
	
});

	
	
});
</script>

<div class="card card-info card-outline">
 	<div class="card-header">
		   <h3>Academic Activity Type  Details</h3>
	  		 
  	</div>
    <div class="card-body">
	  <form id="academicActivityTypeDetailsForm">
	  		
				<div class=" col-lg-6 col-md-6 col-sm-12 col-xs-12">
				
					<div class="form-group">
						<label for="hstlId">Activity Type Code</label> 
								<input type="text" name="activityTypeCode" class="form-control" id="activityTypeCodeId" required/>											
					</div>
					<div class="form-group">
						<label for="hostelNameId">Description</label> 
								<input type="text" name="description" class="form-control" id="descriptionId" required/>											
					</div>
					
						<div class="form-group" style="text-align:center">
						<button type="submit" class="btn btn-primary">SUBMIT</button>		
					</div>
			
				</div>
  
	  </form>
	  
	</div>
</div>

 <br/>
<div  id="gridRow" class="card card-info card-outline">
  	<div class="card-header">
		  <h3>List Of Academic Activity Type</h3>
	  
  	</div>


    <div class="card-body">
	   	<div class="table-responsive">
				<table class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border" id="activityTypeTable">
			 
				</table>
		</div>
	</div>
</div>

<!-- Verify Modal HTML Start -->
<div id="submitModel" class="modal fade responsive">
	<div class="modal-dialog modal-confirm">
		<div class="modal-content">
			<div class="modal-header">
				<div class="icon-box">
					<span style='font-size:50px;'>&#9989;</span>
				</div>				
				<h4 class="modal-title">Are you sure?</h4>	
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			</div>
			<div class="modal-body">
				<p>Do you really want to Submit ?</p>
			</div>
			<div class="modal-footer">
				<button id="cancelId" type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
				<button id="confId" type="button" class="btn btn-primary">Confirm</button>
			</div>
		</div>
	</div>
</div> 
<!-- Modal HTML End -->
