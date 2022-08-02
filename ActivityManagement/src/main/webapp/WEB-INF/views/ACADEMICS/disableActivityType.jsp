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
	console.log("entrrr in loadAllAcademicActivityTypeDetails");
	$.ajax({
		"url" : "/academic/getAllActivityTypesbyApproved",
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
				
				if(selectedRowFromDataTable != null)
				{
					console.log("entr in data table click");
					$('#activityTypeCodeId').val(selectedRowFromDataTable[1]);
					//$('#hostelNameId').val(selectedRowFromDataTable[2]);
					$('#descriptionId').val(selectedRowFromDataTable[2]);
					$('#activityTypeDetails').show();	
				}
				else
					{
					
					}
				
			})

			

		}
	});

}

$(document).ready(function()
{
				
	$('#menuHaed').text("");

	
	loadAllAcademicActivityTypeDetails();
	$('#activityTypeDetails').hide();


	
	
	
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
   
//$('#fldset').prop('disabled', true);
	
	/*$( "#formRowId" ).on("dblclick",function() {
		console.log("Double clicked...");

		if($('#fldset').prop('disabled') == false)
		{
			$('#fldset').prop('disabled', true);
		}
		else
		{
			$('#fldset').prop('disabled', false);
		}
			
	});*/

	$( "#cancelbt" ).on("click",function() {
		console.log("Double clicked...");
		$('#activityTypeDetails').hide();
				
	});

//$( "#verifyBtn" ).on('click',function(event) {
		
	//	event.preventDefault();

		/****** To set status based on verification type  *******/
		
		 /* var verifyType = $("#verifyType").val();

		  if(verifyType == "")
	      {
	    	  depstatus = "l1check";
	      }
		  else if(verifyType == "vf")
	      {
	    	  depstatus = "forward";
	      }
	      else if(verifyType == "a")
	      {
	    	  depstatus = "valid";
	      }*/

	    
		/********************************************/

	
		/*if(dc == "")
		{
			centreDialog("Error","Select Record to Verify...","red");
		}*/
		
			/*$('#verifyModel').modal('show');

			$('#confId').unbind().bind('click',function(){
				//$('#activityTypeCodeId').prop('disabled', false);
				console.log("confirm clicked...");
			console.log($('#activityTypeCodeId').val());
				var l1rem =  $("#u_v_remarksId").val();
				console.log(dc);
				console.log(l1rem);
				$.ajax({
					"url" : "/academic/approveActivityType",
					"method" : "GET",
					data : {"hostelmasterid":dc,"hstlmasterstatus":depstatus,"level1Remarks":l1rem},
					success : function(data) {
						
						var datasplit = data.split('-');
						if(datas == "NOTVERIFIED")
						{
							
							
							
							centreDialog("Error",data,"red");
						
							
						//	var msg = datasplit[1]+"Hostel ID: "+did+"<br/>Hostel Name : "+ds+"<br/> Address: "+df;
							
						}
						else
						{
							
							centreDialog("Success",data,"green");

							clearDataTable("activityTypeTable");
							loadAllHostelDetails();

							$('#hostelMasterDetails').hide();
							$("#u_v_remarksId").val("");
							
						
						}
						
					},
					error : function(e) {
						//showMessage("Error in editing Department. Contact Admin");
					}
				});
				

				$('#verifyModel').modal('hide');
				
			});
			
			$('#cancelId').on('click',function(){

				console.log("Cancel clicked...");

			

				$('#verifyModel').modal('hide');
				
			});
			

			
	

		
			
	});*/



	
	
$('#ActivityTypesDetailsForm').unbind().bind('submit',function(event) {
	event.preventDefault();
	var formData = $(this).serializeArray();
	console.log(formData);
	



	
			if($('#fldset').prop('disabled') == false)
			{
				$('#updateModel').modal('show');

				$('#updateconfId').unbind().bind('click',function(){

					console.log("confirm clicked...");
					
					//activityTypeCodeId
					//Updating data......
					var upremarks =  $("#u_v_remarksId").val();
					//formData.push({"name":"verifiedRemarks","value":upremarks});
					$.ajax({
						"url" : "/academic/deactivateActivityType",
						"method" : "POST",
						data : formData,
						success : function(data) {

							//var datasplit = data.split('-');
							if(data == "NOTDEACTIVATED")
							{
								
								centreDialog("Error","some thing error in approve","red");
								
								
							}
							else
							{
								centreDialog("Success","Deactivated success fully" ,"green");

								$("#ActivityTypesDetailsForm").trigger('reset');
								clearDataTable("activityTypeTable");
								loadAllAcademicActivityTypeDetails();

								$('#activityTypeDetails').hide();
							}


						},
						error : function(e) {
							//showMessage("Error in editing Designation. Contact Admin");
						}
					});
								


					$('#updateModel').modal('hide');
					
				});
				
				$('#updatecancelId').unbind().bind('click',function(){

					console.log("Cancel clicked...");

					$('#updateModel').modal('hide');
					
				});
				
			}
			else
			{
				centreDialog("Error","Enable Fields and Submit Again....","red");
			}
	
	
	
});

});
	
</script>

<div  id="gridRow" class="card card-info card-outline">
  	<div class="card-header">
		  <h3>List Of Acitivity Types</h3>
	  
  	</div>


    <div class="card-body">
	   	<div class="table-responsive">
				<table class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border" id="activityTypeTable">
			 
				</table>
		</div>
	</div>
</div>




<div id="activityTypeDetails"  class="card card-info card-outline">
 	<div class="card-header">
		   <h3>Activity Type  Details</h3>
	  		 
  	</div>
    <div class="card-body">
	  <form id="ActivityTypesDetailsForm">
	   <fieldset  id="fldset">
	  		<div class="row" id="activityTypeRowId">
				<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
				
					<div class="form-group">
						<label for="activityTypeCodeId">Activity Type Code</label> 
								<input type="text" name="activityTypeCode" class="form-control" id="activityTypeCodeId" required/>											
					</div>
					<div class="form-group">
						<label for="hostelNameId">Description</label> 
						<textarea  class="form-control" rows="5" id="descriptionId" name="description" required></textarea> 
																			
					</div>
					
												
				</div>
				<div class=" col-lg-6 col-md-6 col-sm-12 col-xs-12">
								
				
				</div>
					</div>
					  	</fieldset>
		
					<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
		  	<label for="geoCoordinatesId"> Remarks for Deactivate<span class="required">*</span></label>			  
			<textarea  class="form-control" rows="5" id="u_v_remarksId" name="deactivateRemarks" required></textarea> 
		    </div>	
		 	<div class="form-group" >			
			<button type="submit" class="btn btn-danger" style="float:left">Disable</button>				
			<!-- <button type="button" id="verifyBtn" class="btn btn-primary" style="float:right">VERIFY</button>-->
		</div>
			<div class="form-group" >			
			<button type="button" class="btn btn-danger" id="cancelbt" style="float:right">Cancel</button>				
			<!-- <button type="button" id="verifyBtn" class="btn btn-primary" style="float:right">VERIFY</button>-->
		</div>
		</div>
	 	<!--  <input type="hidden" name="hmstatus" value="submitted"/>-->
		</form>		
					
				</div>				
			</div>  
	
<!-- Verify Modal HTML Start -->
<div id="verifyModel" class="modal fade responsive">
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
				<p>Do you really want to confirm this record?</p>
			</div>
			<div class="modal-footer">
				<button id="cancelId" type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
				<button id="confId" type="button" class="btn btn-primary">Confirm</button>
			</div>
		</div>
	</div>
</div> 
<!-- Modal HTML End -->


<!-- Update Modal HTML Start -->
<div id="updateModel" class="modal fade responsive">
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
				<p>Do you really want to Update this record?</p>
			</div>
			<div class="modal-footer">
				<button id="updatecancelId" type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
				<button id="updateconfId" type="button" class="btn btn-primary">Confirm</button>
			</div>
		</div>
	</div>
</div> 
<!-- Update HTML End -->
