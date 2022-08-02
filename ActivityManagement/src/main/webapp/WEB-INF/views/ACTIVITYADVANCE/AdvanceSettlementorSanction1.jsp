<link type="text/css" rel="stylesheet" href="./sfs_public/css/datepicker/bootstrap-datepicker.css">
<script type="text/javascript" src="./sfs_public/js/datepicker/bootstrap-datepicker.js"></script>
 <link rel="stylesheet" href="./sfs_public/css/jquery-confirm.min.css">
 <script src="./sfs_public/js/jquery-confirm.min.js"></script> 
<script>
var activityId;
var centerarray=[];
var centerNameArray=[];
var asNo;
var asSanctionAmnt;
function loadAllAcademicActivityTypeDetails() {
	$.ajax({
		"url" : "/academicactivity/getAllActivityApproved",
		"method" : "GET",
		success : function(data) {

			   setUpDataTable(data, [ 
				{
					"activityCode":"Activity Code"	
				},
				
				{
					"title":"Activity Name"
				},
				{
					"asNO":"Administrative Sanction Number"},
				{
					"finyear":"Financial Year"},
				{
					"status" : "Status"
				}
			  ], "financiallyApprovedActivityTable", "select-checkbox");
				  onDataTableClick('financiallyApprovedActivityTable', function() {
					if(selectedRowFromDataTable != null)
					   {
						$('html, body').animate({ scrollTop: $('#formCenterActivityDetailsdiv').offset().top }, 'slow');
						
						
						$('#titleId').val(selectedRowFromDataTable[2]);
						activityId=selectedRowFromDataTable[1];
					 	$.ajax({
					   		"url":"/academicfinance/getAllactiviticenters",
					   		"method":"GET",
					   		data:{"activityId":activityId},
					   		success : function(data) {
					   			centerlength=data.length;
					   			//console.log(data);
					   			var i;
					   			console.log("daaataaaaaaaaaa in getAllActivityById2"+data);
					   			for( i=0;i<data.length;i++){
					   			var num=i+1;
					   			var num1="center"+num.toString();
					   			checbxid=num1;
					   			centerarray[i]=data[i]['actvtyCenterKey']['cm']['centre_id'];
					   			centerNameArray[i]=data[i]['actvtyCenterKey']['cm']['centre_name'];
					   			console.log("iiiiiiiiiiii"+centerarray[i]);
					   			//checbxname="center"+num1;
					   			 $('#centerdiv')
					   			 .append('<label class="checkbox-inline">')
					                .append('<input type="checkbox" id='+checbxid+' name='+num1+' checked="true" value='+data[i]['actvtyCenterKey']['cm']['centre_id']+'>'+data[i]['actvtyCenterKey']['cm']['centre_name'] )
					                .append('</label>')
					   			 }
					   },
						error(e)
						{
							console.log(e)
						}
					
					
				});
					 	
					 	$.ajax({
							"url" : "/academicactivity/getAllActivityById",
							"method" : "GET",						
							data : {"activityId":activityId},
							success : function(data) 
							{
									
										var status=data['status']
										if(status=="finalapproved")
											{
											asNo=data['asNO'];
											$("#asObtainedId").val("yes");
										    $('#asNoId').val(data['asNO']);
										    $('#approvalObtainedId').val("yes");
											}
										
							},
							error(e)
								{
										console.log(e);
								}
					 	
					 	});
					 	//get activity finance details to get the sanction amnt
					 	$.ajax({
						"url":"/academicfinance/getActivityFinance",
						"method":"GET",
						data:{"activityId":activityId},
						success:function(data)
							{
							asSanctionAmnt=data['totalEstExp'];
							console.log("assanctionamntoooooooooooooo"+asSanctionAmnt);
							$("#sancAmtId").val(asSanctionAmnt);
							},
							error(e)
							{
								console.log(e);
							}
							});
		}
		
	});
	
		},
		error(e)
		{
				console.log(e);
		}
	
	});
}


$(document).ready(function() {
	
	loadAllAcademicActivityTypeDetails();
	
	
	
});


</script>

<div  id="gridRow" class="card card-info card-outline">
  	<div class="card-header">
		  <h3 >List Of Activities For Participation Request</h3>
  	</div>
    <div class="card-body">
	   	<div class="table-responsive">
				<table class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border" id="financiallyApprovedActivityTable">
			 
				</table>
		</div>
	</div>
</div>



   <div class="card card-info card-outline">
   <div class="card-header">
      <h3>Expenditure Sanction / Settlement of Advance - Details of Bills , Vouchers and utilisation of Advance</h3>
   </div>
   <div class="card-body" id="formCenterActivityDetailsdiv">
         <form id="formAdvanceSetlementDetails" enctype="multipart/form-data">
         <div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
             
             <div class="row">
            <div class="form-group" style="width:50%">
               <label for="activitytype"> Name/Title of the Program</label> <input
                  type="text" name="title" class="form-control" id="titleId"
                  required />
            </div>
            </div>
            <div class="row">
               <div class="form-group" id="centerdiv">
               <label for="activity type"> Centres involved in the Program
               </label>
              </div>
              </div>
            </div>
            <div class="row">
            <div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <div class="form-group">
						<label for="inprincipleapproval">Weather In-principle approval obtained </label>
						<div>
							<select class="form-control" name="approvalObtained"
								id="approvalObtainedId">
								<option value="se">Select</option>
								<option value="yes">Yes</option>
								<option value="no">No</option>
							</select>
						</div>
						</div>
						<div class="form-group">
						<label for="refNo">If Yes , File No/ Reference</label> 
						<input type="text" name="refNo"
							class="form-control" id="refNoId" required />
					    </div>
					     <div class="form-group">
                  <label for="activityType">Weather Advance Received for the Work/Project/Event<span
                  class="required">*</span></label>
                  <div>
              	              <select class="form-control" name="advanceObtained"
								id="advanceObtainedId" >
								<option value="se">Select</option>
								<option value="yes">Yes</option>
								<option value="no">No</option>
							</select>
							</div>
       		   </div>
			</div>
            <div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
             <div class="form-group">
                      <label for="asObtained">Weather Administrative Sanction obtained </label>
						<div>
                               <select class="form-control" name="asObtained"
								id="asObtainedId">
								<option value="se">Select</option>
								<option value="yes">Yes</option>
								<option value="no">No</option>
							</select>
						</div>
						</div>
						<div class="form-group">
						<label for="asNo">If Yes , File No/ UO Reference</label> 
						<input type="text" name="asNo"
							class="form-control" id="asNoId" required />
					   </div>
					
						<div class="form-group">
						<label for="asNo">Sanction Amount </label> 
						<input type="number" name="sancAmt"
							class="form-control" id="sancAmtId" required />
					  </div>
            </div>
            </div>
           
            </form>
            <div class="row">
	
	       <p>
             
                  <button class="btn btn-primary" type="button"
                  data-toggle="collapse" data-target="#AdvanceViewAdvReqFormView"
                  aria-expanded="false" aria-controls="collapseExample"
                  id="AdvanceViewAdvReqForm">Details of Advance(s) Received
               </button>
 <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#AmountPaidBackFormId">
Add Amount Paid Back to the University
</button>
              
              
              
              
                <!--  <button class="btn btn-primary" type="button"
                  data-toggle="collapse" data-target="#AmountPaidBackFormId"
                  aria-expanded="false" aria-controls="collapseExample"
                  id="goodsReturnDetails">Details of Goods returned to the University
               </button> -->
            </p>
            <div class="collapse" id="AdvanceViewAdvReqFormView">
               <div class="card card-body">
                  <div class="table-responsive">
                     <table class="table table-striped" id="AdvRequestFormGridTable"></table>
                  </div>
               </div>
            </div>
            <div class="collapse" id="amountPaidBackView">
               <div class="card card-body">
                  <div class="table-responsive">
                     <table class="table table-striped" id="amountPaidBackUniverisityViewGridTable"></table>
                  </div>
               </div>
            </div>
            <div class="collapse" id="goodsReturnDetailsView">
               <div class="card card-body">
                  <div class="table-responsive">
                     <table class="table table-striped" id="goodsReturnDetailsViewGridTable"></table>
                  </div>
               </div>
            </div>
	</div>
           
            
   
   </div>
   </div>
   <!-- Modal HTML End -->
<!-- Modal Resource Person-->
<div class="modal fade" id="AmountPaidBackFormId" tabindex="-1"
   role="dialog" aria-labelledby="exampleModalLongTitle"
   aria-hidden="true">
   <div class="modal-dialog" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLongTitle">Details of any Amount Paid back to the University
            </h5>
            <button type="button" class="close" data-dismiss="modal"
               aria-label="Close">
            <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="modal-body">
            <form id="AmountPaidBackFormId">
               <div class="row">
                  <div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
                     <div class="form-group">
                        <label for="paidBackDescriptionId">Description</label> <input type="text"
                           name="paidBackDescription" class="form-control" id="paidBackDescriptionId" required />
                     </div>
                     <div class="form-group" style="width:100%">
						<label for="centerName">Cost Center Code<span class="required">*</span></label>
						 		<div style="width:100%">
							 		<div>
									<select class="form-control selectpicker"  name="costCenterCode" id="costCenterCodeId" required>																		  										  
																												
									</select>
									</div>
								
								</div>					
					</div>
                     <div class="form-group">
                        <label for="voucharNo">Receipt/Vouchar No</label> <input
                           type="text" name="voucharNo" class="form-control"
                           id="voucharNoId"  />
                     </div>
                     <div class="form-group">
                        <label for="paymentDateId"> Vouchar Date</label> <input type="text"
                           name="paymentDate" class="form-control dp" id="paymentDateId"  />
                     </div>
                     <div class="form-group">
                        <label for="activitytype"> Amount</label> <input type="number"
                           name="amountPaidBack" class="form-control" id="amountPaidBackId"  />
                     </div>
                  </div>
                  <div class="modal-footer">
                     <button type="button" class="btn btn-secondary"
                        data-dismiss="modal">Close</button>
                     <button type="submit" class="btn btn-primary"
                        id="resourceButtonAdd">Submit</button>
                  </div>
               </div>
            </form>
         </div>
      </div>
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