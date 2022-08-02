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
function loadBudgetFundDetails() {
	$.ajax({
		"url" : "/expenditurehead/getAllBudgetFundDetails",
		"method" : "GET",
		success : function(data) {
			console.log(data);
			setUpDataTable(data, [ 
		    {
			"budHeadName" : "Budget Head"
			}, 
			{
				"finYear" : "Financial Year"
			},
			{
				"fundAmount" : "Allocated Fund"
			},
			{
				"budStatus" : "Status"
			}
		  ], "BudgetFundTable", "select-checkbox");

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
	loadBudgetFundDetails();
	$.ajax({
		"url" : "/expenditurehead/getAllBudgetHeadDetails",
		"method" : "GET",
		success : function(data) {
			console.log(data);
			setUpDropDown("budHead",data,"budHeadId","budHeadName");
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
$('#budgetFundDetailsForm').unbind().bind('submit',function(event) 
{
	        console.log("entrrr in budgetFundDetailsForm");
			event.preventDefault();
			var formData = $(this).serializeArray();
			//formData.push({"name":"","value":""});
			var formCheck = "success";

				$('#submitModel').modal('show');

				$('#confId').unbind().bind('click',function(){

					console.log("confirm clicked...");

					$.ajax({
						"url" : "/expenditurehead/saveBudgetFund",
						"method" : "POST",
						 data : formData,
						 success : function(data) {

								var data_splits = data.split("-");
								
								if(data_splits[0] == "SAVED")
								{
									var msg = data_splits[1];
									centreDialog("Success",msg+"is successfully added","green");
								
								}
								else
								{
									var msg = data_splits[1];
									centreDialog("Error",msg,"red");
								
								}
								
								

								$("#budgetFundDetailsForm").trigger('reset');
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
		   <h3>Budget Fund Details</h3>
	  		 
  	</div>
    <div class="card-body">
	  <form id="budgetFundDetailsForm">
	  		
				<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group" style="width:100%">
						<label for="centerName">Budget Head <span class="required">*</span></label>
						 		<div style="width:100%">
							 		<div style="display: inline-block;width:80%">
									<select class="form-control selectpicker"  name="budHeadId" id="budHead" required>																		  										  
																												
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
						<label for="centerfund">Budget Head Amount<span class="required">*</span></label>
						 		<div style="width:100%">
							 		<div style="display: inline-block;width:80%">
								<input type="number" name="fundAmount" class="form-control" id="fundAmountId" required/>
					</div>
								</div>					
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
		  <h3>List Of Budget Fund</h3>
  	</div>


    <div class="card-body">
	   	<div class="table-responsive">
				<table class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border" id="BudgetFundTable">
			 
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