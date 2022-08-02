<link rel="stylesheet" href="./sfs_public/css/jquery-confirm.min.css">
<script src="./sfs_public/js/jquery-confirm.min.js"></script>
<script>


function loadAllExpenditureDetails() {
	var status="submitted";
	$.ajax({
		"url" : "/vendors/getAllEpenditureDetails",
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
				
			})

			

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
		   			//formData.push({"name":"ac","value":activityId});
		   				$('#submitModel').modal('show');
		   
		   				$('#confId').unbind().bind('click',function(){
		   
		   					console.log("confirm clicked...");
		                     var i=0;
		   					$.ajax({
		   						"url" : "/vendors/addVendorDetails",
		   						"method" : "POST",
		   						 data : formData,
		   						 success : function(data) {
		   					
		   							showMessage(data);
		                               
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
				id="expenditureDetailsTable">

			</table>
		</div>
	</div>
</div>

<div class="card card-info card-outline">
	<div class="card-header">
		<h3>Expenditure Details</h3>
	</div>
	<div class="card-body" id="advReq">
		<form id="vendorMasterForm">
        <div class="row" id="advReqFormId">
		<div class=" col-lg-6 col-md-6 col-sm-12 col-xs-12">
				
			<div class="form-group" style="width: 100%">
               <label for="expendHead">Expenditure Head<span
                  class="required">*</span></label>
               <div style="width: 100%">
                  <div style="display: inline-block; width: 100%">
                     <select class="form-control selectpicker" name="expHead"
                        id="expHeadNameId" required>
                     </select>
                  </div>
               </div>
            </div>
				
				<div class="form-group">
						<label for="activitytype"> Bill No / Voucher No</label> <input
							type="text" name="businessName" class="form-control" id="businessNameId" 	required />
					</div>
					<div class="form-group">
						<label for="expAmntId"> Value Amount</label> <input
							type="number" name="expAmnt" class="form-control" id="expAmntId" 	 />
					</div>
					<div class="form-group">
						<label for="activitytype">Tax amount</label>  <input
							type="number" name="tax" class="form-control" id="taxId"/>
					</div>
					<div class="form-group">
						<label for="activitytype">Total Amount</label> <input
							type="text" name="totalAmt" class="form-control" id="totalAmtId" 	required />
					</div>
					<div class="form-group">
						<label for="payeeName">Payee Name</label> <input
							type="text" name="payeeName" class="form-control" id="payeeNameId" 	required />
					</div>
					
				</div>
				<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-12">
				     <div class="form-group">
						<label for="activitytype">GST No <input
							type="text" name="gstNO" class="form-control" id="gstNoId" 	 />
				      </div>
				
				         <div class="form-group">
						<label for="activitytype">PAN No</label> <input
							type="text" name="panNo" class="form-control" id="panNoId" 	 />
				      </div>
				      
				       <div class="form-group">
						<label for="activitytype">Paid Status</label> <input
							type="text" name="paidStatus" class="form-control" id="paidStatusId" 	 />
				      </div>
				       <div class="form-group">
						<label for="activitytype">Mode of Payment</label> <input
							type="text" name="modePayment" class="form-control" id="modePaymentId" 	 />
				      </div>
				      <div class="form-group">
						<label for="activitytype">Admissible Amount</label> <input
							type="text" name="admsAmt" class="form-control" id="admsAmtId" />
				      </div>
			     
				</div>
				</div>
		   <div class="row">
			<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
		    <div class="form-group">
              	<label for="activitytype">Remarks</label> <textarea id="remarksId" name="remarks" rows="4" cols="50"
                  class="form-control"></textarea>
            </div>
            </div>
            </div>
			<div class="row">
				<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group" style="text-align: center">
						 <button type="submit" id="expenditureDetailsBt" class="btn btn-primary">Submit</button>
     			</div>
			</div>
			</div>
		
		
		</form>
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