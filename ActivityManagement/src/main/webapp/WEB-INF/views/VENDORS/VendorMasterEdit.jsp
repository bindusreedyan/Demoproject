<link rel="stylesheet" href="./sfs_public/css/jquery-confirm.min.css">
<script src="./sfs_public/js/jquery-confirm.min.js"></script>
<script>

var vendorId;

function loadAllVendorDetails() {
	var status="submitted";
	$.ajax({
		"url" : "/vendors/getAllVendorDetails",
		"method" : "GET",
		 data:{"vendorStatus":status},
		  success : function(data) {
			
			console.log(data);
			
			setUpDataTable(data, [ {
				"vendorId" : "Vendor Id"
			}, 
			{
				"businessName" : "Buisiness"
			},
			
			{
				"vendorStatus" : "Status"
			}
		  ], "vendorMasterTable", "select-checkbox");

			
			onDataTableClick('vendorMasterTable', function() {
				
				vendorId=selectedRowFromDataTable[1];
				
				$.ajax({
						"url" : "/vendors/getDetailsOfVendorById",
						"method" : "GET",
						 data : {"vendorId":vendorId},
						 success : function(d) {
							 for(var key in d)
								{
			      					
			      					$('#'+key+'Id').empty();
							$('#'+key+'Id').val(d[key]);
								
								}
							 var myOptions = {
									 "se" : 'Select',
									    "Savings" : "Savings",
									    "Current" : "Current",
									    "Fixed" : "Fixed",
									};
									$.each(myOptions, function(val, text) {
									    $('#accountTypeId').append( new Option(text,val) );
									});	 
						$('#accountTypeId').val(d['accountType']);
							// $('#accountTypeId').val(d['accountType']);
							 
						 },
						 error(e)
   						 {
   							showMessage("Error in approval. Contact Admin"); 
   						 }
   						 });
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			});

			

		}
	});

}

$(document).ready(function() {

loadAllVendorDetails();
$('#vendorMasterForm').unbind().bind('submit',function(event) 
		   {
		   	        console.log("entrrr in vendorMasterForm");  
		   			event.preventDefault();
		   			var formCheck = "success";
		   			var formData = $(this).serializeArray();
		   		     formData.push({"name":"vendorId","value":vendorId});
		   				$('#submitModel').modal('show');
		   
		   				$('#confId').unbind().bind('click',function(){
		   
		   					console.log("confirm clicked...");
		                     var i=0;
		   					$.ajax({
		   						"url" : "/vendors/editVendorDetails",
		   						"method" : "POST",
		   						 data : formData,
		   						 success : function(data) {
		   					
		   							showMessage(data);
		   							loadAllVendorDetails();
		                               
		   						 },
		   						 error(e)
		   						 {
		   							showMessage("Error in approval. Contact Admin"); 
		   						 }
		   						 });
		   					$('#submitModel').modal('hide');
		   				});
		   				$('#cancelId').unbind().bind('click',function(){
		 				   
							console.log("Cancel clicked...");

							$('#submitModel').modal('hide');
							
						});
		})	
});

</script>


<div id="gridRow" class="card card-info card-outline">
	<div class="card-header">
		<h3>List Of Vendor Details</h3>

	</div>
	<div class="card-body">
		<div class="table-responsive">
			<table    class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border"
				id="vendorMasterTable">

			</table>
		</div>
	</div>
</div>

<div class="card card-info card-outline">
	<div class="card-header">
		<h3>Vendor Details</h3>
	</div>
	<div class="card-body" id="advReq">
	  <fieldset  id="fldset">
		<form id="vendorMasterForm">
		
		        <div class="row" id="advReqFormId">
				<div class=" col-lg-6 col-md-6 col-sm-12 col-xs-12">
				
				<div class="form-group">
						<label for="activitytype"> Business Name</label> <input
							type="text" name="businessName" class="form-control" id="businessNameId" 	required />
					</div>
					<div class="form-group">
						<label for="activitytype"> Constitution</label> <input
							type="text" name="constitution" class="form-control" id="constitutionId" 	 />
					</div>
					<div class="form-group">
						<label for="activitytype">Address</label> <textarea id="addressId" name="address" rows="4" cols="50"
                  class="form-control"></textarea>
					</div>
					<div class="form-group">
						<label for="activitytype">Pincode</label> <input
							type="text" name="pinCode" class="form-control" id="pinCodeId" 	required />
					</div>
					<div class="form-group">
						<label for="activitytype">Contact Phone</label> <input
							type="text" name="contactPhone" class="form-control" id="contactPhoneId" 	required />
					</div>
					<div class="form-group">
						<label for="activitytype">Email</label> <input
							type="text" name="email" class="form-control" id="emailId" 	 />
					</div>
					<div class="form-group">
						<label for="activitytype">Contact Person</label> <input
							type="text" name="contactPerson" class="form-control" id="contactPersonId" 	 />
					</div>
					
					
				</div>
				<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-12">
				     <div class="form-group">
						<label for="activitytype">Pan No</label> <input
							type="text" name="panNo" class="form-control" id="panNoId" 	required />
				      </div>
				
				         <div class="form-group">
						<label for="activitytype">Gst No</label> <input
							type="text" name="gstNo" class="form-control" id="gstNoId" 	required />
				      </div>
				      
				       <div class="form-group">
						<label for="activitytype">Account No</label> <input
							type="text" name="accountNo" class="form-control" id="accountNoId" 	 />
				      </div>
				       <div class="form-group">
						<label for="activitytype">Bank Name</label> <input
							type="text" name="bankName" class="form-control" id="bankNameId" 	 />
				      </div>
				      <div class="form-group">
						<label for="activitytype">branch</label> <input
							type="text" name="branch" class="form-control" id="branchId" />
				      </div>
				      
				      	<div class="form-group">
						<label for="activitytype">Account Type</label>
						<div>
							<select class="form-control selectpicker" name="accountType"
								id="accountTypeId">
								<option value="se">Select</option>
								<option value="Savings">Savings</option>
								<option value="Current">Current</option>
								<option value="Fixed">Fixed</option>
							</select>
						</div>
					</div>
					 <div class="form-group">
						<label for="activitytype">IFSC Code</label> <input
							type="text" name="ifsc" class="form-control" id="ifscId" />
				      </div>
				</div>
				</div>
		
		   <div class="row">
			<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
		    <div class="form-group">
              	<label for="activitytype">Remarks</label> <textarea id="enteredRamarksId" name="enteredRamarks" rows="4" cols="50"
                  class="form-control"></textarea>
            </div>
            </div>
            </div>
					 <div class="row">
				<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						 <button type="submit" id="vendorMasterDetailsBt" class="btn btn-primary" style="text-align: center">UPDATE</button>
						<!--   <button type="button" id="verifyBtn" class="btn btn-primary" style="float:right;display: none;" >VERIFY</button>-->
     			    </div>
			</div>
			</div>
		
		
		</form>
  </fieldset>
</div>
</div>

<!-- Verify Modal HTML Start -->
<div id="submitModel" class="modal fade responsive">
	<div class="modal-dialog modal-confirm">
		<div class="modal-content">
			<div class="modal-header">
				<div class="icon-box">
					<span style='font-size: 50px;'>&#9989;</span>
				</div>
				<h4 class="modal-title">Are you sure?</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
			</div>
			<div class="modal-body">
				<p>Do you really want to Submit ?</p>
			</div>
			<div class="modal-footer">
				<button id="cancelId" type="button" class="btn btn-danger"
					data-dismiss="modal">Cancel</button>
				<button id="confId" type="button" class="btn btn-primary">Confirm</button>
			</div>
		</div>
	</div>
</div>