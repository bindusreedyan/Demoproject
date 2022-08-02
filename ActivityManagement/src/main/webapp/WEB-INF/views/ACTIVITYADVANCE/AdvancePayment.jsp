<link rel="stylesheet" href="./sfs_public/css/jquery-confirm.min.css">
<script src="./sfs_public/js/jquery-confirm.min.js"></script>
<script>
var totalProgramApprovalAdvanceSum=0;
var activityId;
var finYear;
var advanceRequestId;
$(document).ready(function()
		{
	
	$('.dp').datepicker({
   	    format: 'yyyy-mm-dd',							   
   		autoclose:true,
   		clearBtn:true
   	});
	$("#programApprovalAdvReq" ).on('click',function(event) {
		$.ajax({
			"url":"/expenditurehead/getAllAdvanceRequestFinallyApproved",
			"method":"GET",
			success:function(data)
			{
			console.log(data);	   
			if(data)
				{
				 $('#AdvanceViewAdvReqFormView').hide();
				 $('#programapprovalAdvanceRequest').show();
					setUpDataTable(data,[{"activity_code":"Activity Code"},{"title":"Title"},{"asno":"asNO"},{"fin_year":"Financial Year"}],"advanceReqGridTable","select-checkbox");
					onDataTableClick('advanceReqGridTable', function() {		
						if(selectedRowFromDataTable != null)
						{
						 activityId=selectedRowFromDataTable[1];
						 finYear=selectedRowFromDataTable[4];
							
							$.ajax({
								"url":"/expenditurehead/getActivityAdvanceRequest",
								"method":"GET",
								data:{"activityId":activityId,"finyear":finYear},
								success:function(data)
									{
									console.log(data);
									$('#advnceRequestGridBody').empty();
									
									for(var i=0;i<data.length;i++)
									{
										var des=data[i]['actvtyExpHdkey']['exp']['expHeadsKey']['description'];//data[i]["expHeadsKey"]["description"];
										var advanceRequired=des+"advanceRequired";
										var requirementReason=des+"requirementReason";
										var sino=i+1;
										var asGrantedAdvanceAmnt=des+"asGrantedAdvanceAmnt";

										 tr=$('<tr><td>'+sino+'</td><td>'+des+'</td><td><input class="expadvreq form-control" id="'+advanceRequired+'" type="number" value="'+data[i]['amountAdvance']+'"></input></td><td><input class="expadvreqreason form-control" id="'+requirementReason+'"  value="'+data[i]['reasonAdvance']+'" ></input></td><td><input class="asgrantedAdvanceRequest form-control" id="'+asGrantedAdvanceAmnt+'" value="'+data[i]['asGranted']+'"></input></td><tr>');
										 $('#advnceRequestGridBody').append(tr);
										
									}   
								 
									},
									error:function(e)
									{
									
									}
								
							});
							$('#dialogAdvancePaymentForProgramApproval').modal({backdrop: 'static',keyboard: false});
							
						}
					});
				}
			else
				{
				}
				},
				error:function(e)
				{
				
				} 

		});
	});
			
		$("#AdvanceViewAdvReqForm" ).on('click',function(event) {
			$.ajax({
				"url":"/academicactivity/getAllAdvanceRequestFinanciallyApprovedAndNotPaid",
				"method":"GET",
				success:function(data1)
						{
						console.log(data1);	   
						if(data1)
							{
								
							$('#programapprovalAdvanceRequest').hide();
							$('#AdvanceViewAdvReqFormView').show();
							setUpDataTable(data1,[{"advreqId":"Advance Request Id"},{"activitycode":"Activity Code"},{"title":"title"},{"asnO":"AS NO"},{"rollnocapacity":"RollNo/Employee Code"},{"sanctionedamt":"Sanction Amount"},{"purpose":"Purpose"}],"AdvRequestFormGridTable","null");
								
							onDataTableClick('AdvRequestFormGridTable', function() {		
								if(selectedRowFromDataTable != null)
								{
									
									$('#dialogAdvancePaymentForAdvanceRequestForm').modal({backdrop: 'static',keyboard: false});
									$('#titleId').val(selectedRowFromDataTable[3]);
									$('#rollNoCapacityId').val(selectedRowFromDataTable[5]);
									$('#sanctionedAmtId').val(selectedRowFromDataTable[6]);
									$('#aSNoId').val(selectedRowFromDataTable[6]);
									$('#purposeId').val(selectedRowFromDataTable[7]);
									advanceRequestId=selectedRowFromDataTable[1];
									activityId=selectedRowFromDataTable[2]
									
								}
							});
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							}//end of if data1
								else
								{
								showMessage("no advance  are added");
								}
							
							},
							error:function(e)
							{
							
							} 
						
				});
       });
		
		
		$("#getTotalAdvProgrmApproval" ).on('click',function(event) {
			alert("clicked getTotalAdvProgrmApproval ");
			totalProgramApprovalAdvanceSum=0;
			$('.asgrantedAdvanceRequest').each(function()
			   		{
		    	   var amnt6=$(this).val();
		    	 
		    	   totalProgramApprovalAdvanceSum=totalProgramApprovalAdvanceSum+parseFloat(amnt6);
		    	   
			   		});
			  alert(totalProgramApprovalAdvanceSum);
			$('#totalAdvancePaidId').val(totalProgramApprovalAdvanceSum);
			
		});
		
		$('#activityadvancePaymentForm').unbind().bind('submit',function(event)
				{
			
			   event.preventDefault();
			   var formData = $(this).serializeArray();
				formData.push({"name":"ac","value":activityId});
				formData.push({"name":"finYear","value":finYear});
				formData.push({"name":"purpose","value":"for conducting activity"});
				$.ajax({
					"url" : "/academicactivity/addProgramApprovalAdvancePayment",
					"method" : "POST",
					 data : formData,
					 success : function(data) {
						if(data!=null)
							{
						showMessage("Advance Payment is done");
							}
						
						if(data==null)
							{
							showMessage("Error in saving");
							}
						}
					
						
					});
			
				});
		
		
		$('#activityadvancePaymentForm2').unbind().bind('submit',function(event)
				{
			   event.preventDefault();
			   var formData = $(this).serializeArray();
				formData.push({"name":"advanceRequestId","value":advanceRequestId});
				formData.push({"name":"ac","value":activityId});
	
				
				$.ajax({
					"url" : "/academicactivity/addAdvancePayment",
					"method" : "POST",
					 data : formData,
					 success : function(data) {
						if(data!=null)
							{
						showMessage(data);
							}
						
						if(data==null)
							{
							showMessage(data);
							}
						}
					
						
					});
			
				});
		});

</script>
<div class="card card-info card-outline" id="AdvancePayment">
<div class="card-header">
   <h3>Advance Payment</h3>
   	<div class="row">
	       <p>
               <button class="btn btn-primary" type="button"  id="programApprovalAdvReq">View Advance Request through Program Approval</button>
               
               <button class="btn btn-primary" type="button" id="AdvanceViewAdvReqForm">View Advance Request through Request Form</button>
            </p>
            <div class="collapse" id="AdvanceViewAdvReqFormView">
               <div class="card card-body">
                  <div class="table-responsive">
                     <table class="table table-striped" id="AdvRequestFormGridTable"></table>
                  </div>
               </div>
               
            </div>
               <div class="collapse" id="programapprovalAdvanceRequest">
               <div class="card card-body">
                  <div class="table-responsive">
                     <table class="table table-striped" id="advanceReqGridTable"></table>
                  </div>
               </div>
               </div>
	</div>
   
</div>
</div>

<!-- advance payment for Program Approval -->
<div class="modal fade" id="dialogAdvancePaymentForProgramApproval" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Advance Payment for Advance Requested through Program Approval Form</h5>
		 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div> 
        <div class="modal-body">
		<form id="activityadvancePaymentForm" >
		         <div id="advnceRequestGrid" class="row">
                  <div class="col-md-12">
                     <div class="table-responsive cell-border">
                        <table id="gridTableMapper" class="table table-striped">
                           <thead>
                              <tr>
                                 <th scope="col">SI No</th>
                                 <th scope="col">Head Of Expenditure</th>
                                 <th scope="col">Advance Required</th>
                                 <th scope="col">Briefly State Requirement with reason</th>
                                 <th scope="col">As Granted Amount</th>
                                 
                              </tr>
                           </thead>
                           <tbody id="advnceRequestGridBody">
                           </tbody>
                        </table>
                     </div>
                  </div>
               </div>
               <div class="row">
               <div class="form-group" style="text-align: center">
                <button class="btn btn-primary" type="button" id="getTotalAdvProgrmApproval">Get Total Advance Granted</button>
               </div>
               </div>
               
                <div class="form-group row">
                  <label for="activitytype" class="col-sm-6 col-form-label">Total Advance Granted</label>
                  <div class="col-sm-6">
                     <input type="text" name="advancePaid" class="form-control"
                        id="totalAdvancePaidId" >
                  </div>
               </div>
                 <div class="row">
				<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
					<label for="advanceInwords">Vouchar No</label> 
					<input type="text" name="voucharNo" class="form-control"
							id="voucharNoId" required />
					</div>
			
				<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
				<label for="advanceInwords">Vouchar Date</label> 
					<input type="text" name="voucharDate" class="form-control dp"
							id="voucharDateId" required />
					</div>
				</div>
			    <div class="row">
				<label for="enteredRemarks">Entered Remarks</label> 	
			   <textarea id="enteredRemarksId" name="enteredRemarks" rows="4" cols="50"
                class="form-control"></textarea>
				
				</div>
				<hr>
				<div class="row">
				<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group" style="text-align: center">
						<input type="submit" class="btn btn-primary" value="ADD"/>
     			</div>
     			<button type="button" class="btn btn-secondary"
                        data-dismiss="modal">Close</button>
			</div>
			</div>
		</form>
		</div>
		</div>
		</div>
		</div>
<!-- advance payment for advance Form-->
<div class="modal fade" id="dialogAdvancePaymentForAdvanceRequestForm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Advance Payment for Advance Requested through Advance Request Form</h5>
		 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div> 
        <div class="modal-body">
		<form id="activityadvancePaymentForm2" >
		<div class="row" id="advReqFormId">
				<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="form-group">
						<label for="activitytype"> Name/Title of the Program</label> <input
							type="text" name="title" class="form-control" id="titleId" 	required />
					</div>
					<div class="form-group">
						<label for="accountName">RollNo/Employee Code</label> <input type"text"
							class="form-control grouping" name="rollNoCapacityId" id="rollNoCapacityId"></select>
					</div>
			<!-- 		<div class="form-group">
						<label for="activitytype"> Name</label> <input type="text" 	name="nameId" class="form-control" id="nameId" required ></input>
					</div> -->
                  <div class="form-group">
						<label for="advanceInwords">Amount of Advance Sanctioned</label> 
						<input type="number" name="advancePaid"
							class="form-control" id="sanctionedAmtId" required />
					</div>
			
				<div class="form-group">
						<label for="aSNoId">University Order No against which
							advance is requested</label> <input type="text" name="aSNo"
							class="form-control" id="aSNoId" required />
					</div>
					
					<div class="form-group">
	                  <textarea id="purposeId" name="purpose" rows="4" cols="50" class="form-control"></textarea>
					</div>
				
			
		
		</div>
		</div>
                <div class="row">
				<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
					<label for="advanceInwords">Vouchar No</label> 
					<input type="text" name="voucharNo" class="form-control"
							id="voucharNoId1" required />
				</div>
			
				<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
				<label for="advanceInwords">Vouchar Date</label> 
					<input type="text" name="voucharDate" class="form-control dp"
							id="voucharDateId1" required />
					</div>
				</div>
			    <div class="row">
				<label for="enteredRemarks">Entered Remarks</label> 	
			   <textarea id="enteredRemarksId2" name="enteredRemarks" rows="4" cols="50"
                class="form-control"></textarea>
				
				</div>
				<hr>
				<div class="row">
				<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group" style="text-align: center">
						<input type="submit" class="btn btn-primary" value="ADD"/>
     			</div>
     			<button type="button" class="btn btn-secondary"
                        data-dismiss="modal">Close</button>
			</div>
			</div>
		</form>
		</div>
		</div>
		</div>
		</div>