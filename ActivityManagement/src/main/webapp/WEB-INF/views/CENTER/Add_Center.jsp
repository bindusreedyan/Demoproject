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


function loadacenterDetails() {
	$.ajax({
		"url" : "/center/getAllCenters",
		"method" : "GET",
		success : function(data) {
			
			console.log(data);
			
			setUpDataTable(data, [ {
				"centreCode" : "Center Code"
			}, {
				"centerName" : "Center Name"
			},
			{
				"address" : "Center Address"
			},
			{
				"hod" : "Center Hod"
			},
			{
				"status" : "Status"
			}
		  ], "centerTable", "select-checkbox");

			
			onDataTableClick('centerTable', function() {
				
			})

			

		}
	});

}

$(document).ready(function()
{
				
	$('#menuHaed').text("");
	$('.dp').datepicker({
	    format: 'yyyy-mm-dd',							   
		autoclose:true,
		clearBtn:true
	});
	
	loadacenterDetails();

	restrictSpecialChars("centerNameId");	
	restrictSpecialChars("hodId");	
	/*restrictSpecialChars("addressId");		
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
   
	

	
$('#centerDetailsForm').unbind().bind('submit',function(event) 
{
	
	        console.log("entrrr in centerDetailsForm");
			event.preventDefault();
			var formData = $(this).serializeArray();
		
			//formData.push({"name":"","value":""});
			
			var formCheck = "success";

				$('#submitModel').modal('show');

				$('#confId').unbind().bind('click',function(){

					console.log("confirm clicked...");

					$.ajax({
						"url" : "/center/saveCenter",
						"method" : "POST",
						 data : formData,
						 success : function(data) {
							console.log(data);

									//showMessage("Hostel Floor Details Saved.");
								
								centreDialog("Success",data,"green");

								$("#centerDetailsForm").trigger('reset');
								//clearDataTable("activityTypeTable");
								loadacenterDetails();
							}
						
							
						});

					$('#submitModel').modal('hide');
					
				});
				
				$('#cancelId').unbind().bind('click',function(){

					console.log("Cancel clicked...");

					$('#submitModel').modal('hide');
					
				});


											
				
	
				
		
			
	
});

	
	
});
</script>

<div class="card card-info card-outline">
 	<div class="card-header">
		   <h3>Center  Details</h3>
	  		 
  	</div>
    <div class="card-body">
	  <form id="centerDetailsForm">
	  		
				<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
				
					<div class="form-group">
						<label for="activityTypeCodeId">Center Name</label> 
								<input type="text" name="centerName" class="form-control" id=centerNameId" required/>											
					</div>
					<div class="form-group">
						<label for="hostelNameId">Center Address</label> 
							<textarea  class="form-control" rows="5" id="addressId" name="address" required></textarea>											
					</div>
					
						<div class="form-group">
						<label for="hostelNameId">Center Co-ordinator</label> 
							<input type="text" name="hod" class="form-control" id="hodId" required/>											
					</div>
					
					<div class="form-group">
					<label>Enter Date</label>
			        <input type="text" id="submitDate"  name="createDateTime" class="form-control dp"></input>
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
		  <h3>List Of Centeres</h3>
	  
  	</div>


    <div class="card-body">
	   	<div class="table-responsive">
				<table class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border" id="centerTable">
			 
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
