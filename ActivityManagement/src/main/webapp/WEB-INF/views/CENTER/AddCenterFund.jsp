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
function loadCenterFundDetails() {
	$.ajax({
		"url" : "/centerfund/getAllCenterFundDetails",
		"method" : "GET",
		success : function(data) {
			console.log(data);
			setUpDataTable(data, [ {
				"centre_name" : "Center Name"
			}, {
				"finYear" : "Financial Year"
			},
			{
				"fundAmount" : "Allocated Fund"
			},
			{
				"commitedTotal" : "Committed Total Amount" 
			},
			{
				"currentBalance" : "Balance Amount"
			},
			{
				"expTillDate":"Expenditure Till Date"
			},
			{
				"status" : "Status"
			}
		  ], "centerFundTable", "select-checkbox");

			onDataTableClick('centerFundTable', function() {
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
	loadCenterFundDetails();
	$.ajax({
		"url" : "/academicactivity/getAllCentres",
		"method" : "GET",
		success : function(data) {
			console.log(data);
			setUpDropDown("centreCodeId",data,"centre_id","centre_name");
		},
		error : function(e) {
			//showMessage("Error in Getting Programs. Contact Admin");
		}
	});
	var mySelect = $('#finYearId');
	  var currentYear = (new Date()).getFullYear();
	var startYear = currentYear+1;
	var prevYear = currentYear;
	  mySelect.append(
	   $('<option></option>').val("").html("Select"));
	for (var i = 0; i < 30; i++) {
	 
	  mySelect.append(
	
	    $('<option></option>').val(prevYear + "-" + startYear).html(prevYear + "-" +startYear )
	  );
	  startYear = startYear - 1;
	  prevYear = prevYear - 1;
	}
	
	
	//restrictSpecialChars("centerNameId");	
	//restrictSpecialChars("hodId");	
	/*restrictSpecialChars("addressId");		
	restrictSpecialChars_allow_dot_comma("amenitiesId");
	restrictSpecialChars_allow_dot_comma("geoCoordinatesId");*/
	//restrictSpecialChars_allow_dot_comma("remarksId")
	$("#fundAmount").keypress(function (e) {
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
      
      
	});
$('#centerFundDetailsForm').unbind().bind('submit',function(event) 
{
	        console.log("entrrr in centerFundDetailsForm");
			event.preventDefault();
			var formData = $(this).serializeArray();
			//formData.push({"name":"","value":""});
			var formCheck = "success";

				$('#submitModel').modal('show');

				$('#confId').unbind().bind('click',function(){

					console.log("confirm clicked...");

					$.ajax({
						"url" : "/centerfund/saveCenterFund",
						"method" : "POST",
						 data : formData,
						 success : function(data) {

								var data_splits = data.split("-");
								
								if(data_splits[0] == "SAVED")
								{
									var msg = data_splits[1];
									centreDialog("Success",msg,"green");
								
								}
								else
								{
									var msg = data_splits[1];
									centreDialog("Error",msg,"red");
								
								}
								
								

								$("#centerFundDetailsForm").trigger('reset');
								//clearDataTable("activityTypeTable");
								loadCenterFundDetails();
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
		   <h3>Centre Fund Details</h3>
	  		 
  	</div>
    <div class="card-body">
	  <form id="centerFundDetailsForm">
	  		
				<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group" style="width:100%">
						<label for="centerName">Centre Name<span class="required">*</span></label>
						 		<div style="width:100%">
							 		<div style="display: inline-block;width:80%">
									<select class="form-control selectpicker"  name="centreCode" id="centreCodeId" required>																		  										  
																												
									</select>
									</div>
								
								</div>					
					</div>
					<div class="form-group" style="width:100%">
						<label for="finYear">Financial Year<span class="required">*</span></label>
						 		<div style="width:100%">
							 		<div style="display: inline-block;width:80%">
									<select class="form-control selectpicker"  name="finYear" id="finYearId"  min="0" required>																		  										  
																												
									</select>
					</div>
								</div>					
					</div>
					
					<div class="form-group" style="width:100%">
						<label for="centerfund">Centre Fund<span class="required">*</span></label>
						 		<div style="width:100%">
							 		<div style="display: inline-block;width:80%">
								<input type="number" name="fundAmount" class="form-control" id="fundAmountId" required/>
					</div>
								</div>					
					</div>
					
						<div class="form-group" style="width:100%">
						<label for="centerfund">Un Assign Grant<span class="required">*</span></label>
						 		<div style="width:100%">
							 		<div style="display: inline-block;width:80%">
								<input type="number" name="unAssignGrant" class="form-control" id="unAssignGrantId" required/>
					</div>
								</div>					
					</div>
					
				<!-- 	<div class="form-group" style="width:100%">
						<label for="submitdate">Enter Date<span class="required">*</span></label>
						 		<div style="width:100%">
							 		<div style="display: inline-block;width:80%">
								<input type="text" id="submitDate"  name="enteredDate" class="form-control dp" required></input>
					</div>
								</div>					
					</div> -->
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
		  <h3>List Of Centres Fund</h3>
  	</div>


    <div class="card-body">
	   	<div class="table-responsive">
				<table class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border" id="centerFundTable">
			 
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
