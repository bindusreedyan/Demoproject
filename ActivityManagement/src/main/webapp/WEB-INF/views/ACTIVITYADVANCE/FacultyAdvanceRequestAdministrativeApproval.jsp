
<link rel="stylesheet" href="./sfs_public/css/jquery-confirm.min.css">
<script src="./sfs_public/js/jquery-confirm.min.js"></script>
<script>
var activityCode;
var activityLevel;
var studentDetails={};
var programId;
var batchId;
var particiaptionRequestId;
var nameArray=[];
var rollNoArray=[];
var savedPartipntcount=0;
var vardata;
var finyear;
var rollno;
var requestedByRemarks;
var advanceRequestId;
var totalAdvReq=0;
var totalAdvReq1=0;
var totalAdvanceRequested=0;
var empCode;
var advReqId;
function reportPrinting(divId)
{
	 //  console.log(divId);
	   $('#reportDiv').show();
    var divToPrint = document.getElementById(divId);
    var htmlToPrint = '' +
        '<style type="text/css">' +
        'table th, table td {' +
     'border:1px solid #000;' +
        'padding:0.5em;' +
        '} table{width:100%}' +
        '</style>';
    htmlToPrint += divToPrint.outerHTML;
    var newWin = window.open("");
    newWin.document.write('<html style="width:794px; height:1122px; border: .5px solid"><head><title>National University of Advanced Legal Studies</title>');
    newWin.document.write(htmlToPrint);
    newWin.print();
    newWin.close();	
    
    $('#reportDiv').hide();
}



function loadAllAcademicActivityTypeDetails() {
	$.ajax({
		"url" : "/academicactivity/getAllActivityAdminOfficeApprovedFaculty",
		"method" : "GET",
		success : function(data) {
			
			//console.log(data['asNO']);
			
		   setUpDataTable(data, [ 
			   {
					"advReqId":"Advance Request Id"	
				},
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
					"userCode":"Employee Code"},
			{
				"advanceReqStatus" : "Status"
			}
		  ], "financiallyApprovedActivityTable", "select-checkbox");
			onDataTableClick('financiallyApprovedActivityTable', function() {
					if(selectedRowFromDataTable != null)
					   {
						$('html, body').animate({ scrollTop: $('#advReq').offset().top }, 'slow');
						$('#titleId').val(selectedRowFromDataTable[3]);
						$('#titleId').attr('disabled',true); 
						console.log("asno iddddddd"+selectedRowFromDataTable[2]);
						var asNo=selectedRowFromDataTable[4];
						$('#aSNoId').val(asNo);
						$('#aSNoId').attr('disabled',true); 
						activityCode=selectedRowFromDataTable[2];
						finyear=selectedRowFromDataTable[5];
						advanceRequestId=selectedRowFromDataTable[1];
						
						empCode=selectedRowFromDataTable[6];
						$.ajax({
							"url":"/academicfinance/getActivityFinance",
							"method":"GET",
							data:{"activityId":activityCode},
							success:function(data)
								{
								console.log(data);
								var advanceeligible=parseFloat(data['total_estimated'])/2;
								$('#tmntArId').val(advanceeligible);
								$('#amtSanctionedASId').val(data['total_estimated']);
								$('#amtSanctionedASId').attr('disabled',true); 
								},
								error: function(e)
								{
									console.log(e);
								}					
					            }); 
						
						
						$.ajax({
			    			"url":"/academicactivity/getAllAdvanceRequestRaisedByAdvanceRequestId",
			    			"method":"GET",
			    			data:{"advanceRequestId":advanceRequestId},
			    			success:function(data)
			    				{
			    				
			    				console.log("data length"+data.length);
			    				   
			    				if(data)
			    					{	
                                           $('#designationId').val(data['designation']);
                                           var userCode=data['userCode'];
                                           $('#advRequiredFigId').val(data['advRequiredFig']);
                                           $('#advRequiredWordsId').val(data['advRequiredWords']);
                                           $('#purposeId').val(data['purpose']);
                                           $('#finalAdvReqId').val(data['finalAdvReq']);
                                           $('#enteredRemarksId').val(data['enteredRemarks']);
                                           $('#adminOfficeApprovedRemarksId').val(data['adminOfficeApprovedRemark']);
                                           
                                           $('#sanctionedAmtId').val(data['sanctionedAmt']);
                                           
                                           $.ajax({
                   							"url":"/academicactivity/getUserInfoByUserCode",
                   							"method":"GET",
                   							data:{"userCode":userCode},
                   							success:function(userdata)
                   								{
                   								console.log(userdata);
                   								
                   								$('#empNameId').val(userdata['emp_name']);
                   								$('#empNameId').attr('disabled',true)
                   								$('#designationId').val(userdata['designation']['design_fullform']);
                   								$('#designationId').attr('disabled',true); 
                   								
                   								
                   								
                   								},
                   								error: function(e)
                   								{
                   									console.log(e);
                   								}					
                   					            }); 
                                    }
                                   },
                              error:function(e)
                                {

                                   }
                                });	
						
						
					          //  var totalAdvReq=0;
					            //to find out the total advance requested amount
					        $.ajax({
					    	   "url":"/expenditurehead/getActivityAdvanceRequest",
								"method":"GET",
								data:{"activityId":activityCode,"finyear":finyear},
							    success:function(data)
								{
							    	
							    	totalAdvReq=0;
								for(var i=0;i<data.length;i++)
									{
									totalAdvReq=totalAdvReq+data[i]['amountAdvance'];
									}
								
								   console.log(totalAdvReq);
								  //advance raised through advance request Form
							       var totalRequestedAmnt=0.0;
							//	   $("#tmntArId").val(totalAdvReq);
								  
								  if(totalAdvReq>0)
									  {
									  
									  
									  }
								  
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
									
									  $.ajax({
								    	   "url":"/academicactivity/findTotalRequestedAmntByFacultyForApproval",
											"method":"GET",
											data:{"activityCode":activityCode,"userCode":empCode},
										    success:function(data)
											{
										    	console.log(data);
										    	totalAdvReq1=0;
										    	for(var i=0;i<data.length;i++)
												{
												totalAdvReq1=totalAdvReq1+data[i]['advRequiredFig'];
												}
										    	console.log(totalAdvReq);
											
										    	  totalAdvanceRequested=totalAdvReq+totalAdvReq1;
										    	//$('#tmntArId').val(totalAdvReq);
												if(totalAdvanceRequested>0)
													{
													$('#finalAdvReqId').val("yes");
													$('#programApprovalAdvReq').attr('disabled',false);
													}
												else
													{
													$('#finalAdvReqId').val("no");
													$("programApprovalAdvReq").removeAttr('disabled','disabled');
													}
												
												console.log(totalAdvReq); 
												$('#totalAdvanceRequestedId').val(totalAdvanceRequested);
										    	/* var totalRequestedAmnt = data;
										    	totalAdvReq=totalAdvReq +totalRequestedAmnt;
										    	console.log(totalAdvReq);
										    	 */
											},
											error: function(e)
											{
												console.log(e);
											}					
								            }); 
									
									
									
								
								},
								error: function(e)
								{
									console.log(e);
								}					
					            });  
					            
					       
					        
					       /*  $.ajax({
								"url":"/expenditurehead/getActivityAdvanceRequest",
								"method":"GET",
								data:{"activityId":activityCode,"finyear":finYear},
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
								
							}); */
					        
					        
							   
					            
			              }//end if
			  });//on data table click end
         },
         error: function(e)
			{
				console.log(e);
			}
	      });
}
$(document).ready(function()
		{

			$('.dp').datepicker({
		   	    format: 'yyyy-mm-dd',							   
		   		autoclose:true,
		   		clearBtn:true
		   	});
			requestedByRemarks="I do hereby inform that there is no advance for more than 15 days pending settement in my name  and undertake to refund the balance amount, if any and to settle the advance immediately after the purpose is over.";
		    loadAllAcademicActivityTypeDetails();
		    $('#advReqForm').unbind().bind('submit',function(event)
				{
		    	
		    	event.preventDefault();
		    	$('#titleId').attr('disabled',false); 
		    	$('#empNameId').attr('disabled',false); 
		    	$('#designationId').attr('disabled',false); 
		    	$('#aSNoId').attr('disabled',false); 
		    	$('#amtSanctionedASId').attr('disabled',false);
		    	  var eligbleamnt=$('#tmntArId').val();
				      var advReqAmnt=$('#advRequiredFigId').val();
				      var asAmnt=$('#amtSanctionedASId').val();
				      var requestedAmnt=$('#tmntArId').val();
				      var balamnttoberequested=asAmnt-requestedAmnt;
				  	  var totalAdvanceRequestedupdated=totalAdvanceRequested+parseFloat($('#totalAdvanceRequested').val());
				      
				      if((totalAdvanceRequestedupdated>eligbleamnt)>0)
				      {
				    	  
				    	  confirm("requested amnt exceeds the advance limit ");
				  	   //showMessage("requested amnt exceeds your advance limit pls enter the reason in remarks field");
				       }
					     
					  	
							     var formData = $(this).serializeArray();
							    //formData.push({"name":"","value":""});
							    formData.push({"name":"advReqId","value":advanceRequestId})
							 	formData.push({"name":"ac","value":activityCode});
								formData.push({"name":"reqRasedUserRole","value":"faculty"});
							 	
							     var formCheck = "success";

								$('#submitModel').modal('show');

								$('#confId').unbind().bind('click',function(){

									console.log("confirm clicked...");

									$.ajax({
										"url" : "/academicactivity/adminApprovalAdvanceRequest",
										"method" : "POST",
										 data : formData,
										 success : function(data) {
											console.log(data);
													//showMessage("Hostel Floor Details Saved.");
												//centreDialog("Info",data,"green");
												
												  var data_splits1 = data.split(":");
						   	   	  	
						   	   	  	     advReqId=data_splits1[1];
											console.log(data);
						   	   	  			var data_splits = data.split("-");
											if(data_splits[0] == "SAVED")
											{
												var msg = data_splits[1];
												centreDialog("Success",msg,"green");
											
											}
											else
											{
												//var msg = data_splits[1];
												var msg = data_splits[1];
												centreDialog("Error",msg,"red");
											
											}
												$("#advReqForm").trigger('reset');
												//clearDataTable("activityTypeTable");
												loadAllAcademicActivityTypeDetails();
											}
										
											
										});

									$('#submitModel').modal('hide');
									
								});
								
								$('#cancelId').unbind().bind('click',function(){

									console.log("Cancel clicked...");

									$('#submitModel').modal('hide');
									
								});
					  
				});
		    
		    
		  //Report 
		    $('#printBt').unbind().bind('click',function()
		    	{
		         event.preventDefault();

		       $.ajax({
		                "url" : "/academicactivity/getAllAdvanceRequestRaisedByAdvanceRequestId",
		                "method" : "GET",
		                "data" : {"advanceRequestId":advReqId},
		      			success:function(d)
		    				{
		      				//$('#rollno').append(d['rollno']);
		      				for(var key in d)
		    				{
		      					
		      					$('#'+key).empty();
		    				//	$('#'+key).val(d[key]);
		    					$('#'+key).append(d[key]);
		    				}
		      				
		      				
		      				
		      				
		      			
		      				console.log("reqNameeeee   "+reqName);
		      				$('#reqName').append($('#empNameId').val());
		      				//$('#reqName').append($('#empNameId').val());
		      				 reportPrinting('reportDiv');
		    				}
		    			});
		      
		    	});
		});


</script>
<div id="gridRow" class="card card-info card-outline">
	<div class="card-header">
		<h3>List Of Activities For Advance Request</h3>

	</div>
	<div class="card-body">
		<div class="table-responsive">
			<table
				class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border"
				id="financiallyApprovedActivityTable">

			</table>
		</div>
	</div>
</div>
<div class="card card-info card-outline">
	<div class="card-header">
		<h3>Administrative Approval For Advance Request By Faculty</h3>
	</div>
	<div class="card-body" id="advReq">
		<form id="advReqForm">
			<div class="row" id="advReqFormId">
				<div class=" col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="activitytype"> Name/Title of the Program</label> <input
							type="text" name="title" class="form-control" id="titleId" 	required />
					</div>
					<div class="form-group">
						<label for="accountName">Name of the Staff</label> <input type="text"
							class="form-control grouping" name="empName" id="empNameId"></select>
					</div>
					<div class="form-group">
						<label for="activitytype">Designation</label> <input type="text" 	name="designation" class="form-control" id="designationId" required ></input>
					</div>

					<div class="form-group">
						<label for="aSNoId">University Order No against which
							advance is requested</label> <input type="text" name="aSNo"
							class="form-control" id="aSNoId" required />
					</div>

                        <div class="form-group">
						<label for="amtSanctionedASId">Total amount of Advance Eligible</label> <input type="number" name="tmntAr" class="form-control" id="tmntArId" required></input>
					</div>
				</div>
				<div class=" col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="advanceInwords">Amount of Advance Required in
							Figure</label> <input type="number" name="advRequiredFig"
							class="form-control" id="advRequiredFigId" required />
					</div>
					
					<div class="form-group">
						<label for="advanceInwords">Amount of Advance Required in
							Words</label> <input type="text" name="advRequiredWords"
							class="form-control" id="advRequiredWordsId" required />
					</div>

					<div class="form-group">
						<label for="advanceInwords">Purpose for which Advance is
							Required</label> <input type="text" name="purpose" class="form-control"
							id="purposeId" required />
					</div>
					<div class="form-group">
						<label for="amtSanctionedASId">Amount for Which
							Administrative Sanction Recieved In figures</label> <input type="number"
							name="amtSanctionedAS" class="form-control"
							id="amtSanctionedASId" required />
					</div>
					<div class="form-group">
						<label for="amtSanctionedASId">Total Amount of advance Requested Yet</label> <input type="number"
							name="totalAdvanceRequested" class="form-control"
							id="totalAdvanceRequestedId" required />
					</div>
				</div>
			</div>

			<div class="row">
				<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
					<div class="form-group">
						<label for="activitytype">Any other Advance required for
							this programme</label>
						<div>
							<select class="form-control" name="finalAdvReq"
								id="finalAdvReqId">
								<option value="se">Select</option>
								<option value="yes">Yes</option>
								<option value="no">No</option>
							</select>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
			<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
		    <div class="form-group">
               <label for="remarks"> Remarks</label>
               <textarea id="enteredRemarksId" name="enteredRemarks" rows="4" cols="50"
                  class="form-control"></textarea>
            </div>
            </div>
            </div>
            	<div class="row">
			<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
		    <div class="form-group">
               <label for="remarks">Admin Office Approval Remarks</label>
               <textarea id="adminOfficeApprovedRemarksId" name="adminOfficeApprovedRemark" rows="4" cols="50"
                  class="form-control"></textarea>
            </div>
            </div>
            </div>
             <div class="row">
			<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
			 <div class="form-group">
               <label for="activityLevel">Administrative Approval<span
                  class="required">*</span></label>
                  <div style="width: 100%">
                  <div style="display: inline-block; width: 100%">
                     <select class="form-control selectpicker" name="advanceReqStatus"
                        id="advanceReqStatusId" required>
                        <option value="se">Select</option>
                        <option value="AdminApproved">Approve</option>
                        <option value="AdminRejected">Reject</option>
                        
                     </select>
                  </div>
               </div>
            </div>
            </div>
            </div>
              <div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
            	<div class="form-group">
						<label for="advanceInwords">Amount of Advance Sanctioned</label> 
						<input type="number" name="sanctionedAmt"
							class="form-control" id="sanctionedAmtId" required />
					</div>
            </div>
			<div class="row">
			<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
		    <div class="form-group">
               <label for="remarks">Admin Approval Remarks</label>
               <textarea id="adminApprovedRemarksId" name="adminApprovedRemarks" rows="4" cols="50"
                  class="form-control"></textarea>
            </div>
            </div>
            </div>
			<div class="row">
				<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
					<div class="form-group" style="text-align: right">
						<input type="submit" class="btn btn-primary" value="ADD"/>
     			</div>
			</div>
				<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
     	   <div class="form-group">
              <button class="btn btn-primary" id="printBt">PRINT</button>
           </div>
           </div>
			</div>
           
		</form>
	</div>
	<div class="row">
	
	       <p>
               <button class="btn btn-primary" type="button"
                  data-toggle="collapse" data-target="#programapprovalAdvanceRequest"
                  aria-expanded="false" aria-controls="collapseExample"
                  id="programApprovalAdvReq">View Advance Request through Program Approval
               </button>
                  <button class="btn btn-primary" type="button"
                  data-toggle="collapse" data-target="#AdvanceViewAdvReqFormView"
                  aria-expanded="false" aria-controls="collapseExample"
                  id="AdvanceViewAdvReqForm">View Advance Request through Request Form
               </button>
            </p>
            <div class="collapse" id="AdvanceViewAdvReqFormView">
               <div class="card card-body">
                  <div class="table-responsive">
                     <table class="table table-striped" id="AdvRequestFormGridTable"></table>
                  </div>
               </div>
            </div>
               <div class="collapse" id="programapprovalAdvanceRequest">
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
<!-- Modal HTML End -->
<div class="row">
	<div class="col-md-12">
		<div class="table-responsive" id="reportDiv" style="display: none">
			<table class="table table-striped" id="repTable">
				<tr><td colspan="4">
				 <img src="../images/logo.jpg" style="width: 40px;">
				</tr>
				<tr><td colspan="4"><center>Administrative Approval of Requisition for Advance  Faculty/ Staff</center></td></tr>
				<tr><td>Name Of the Staff</td><td id="reqName"></td></tr>
				<tr><td>Designation</td><td id="designation"></td></tr>
				<tr><td>Amount Of Advance Required   in Words</td><td id="advRequiredWords"></td></tr>
				<tr><td>Amount Of Advance Required   in Figures</td><td id="advRequiredFig"></td></tr>
				<tr><td>Purpose for which Advance is Required  </td><td id="purpose"></td></tr>
				<tr><td>Amount for which Administrative Sanction received in Figures</td><td id="amtSanctionedAS"></td></tr>
				<tr><td>University Order No against which advance is requested</td><td id="aSNo"></td></tr>
				<tr><td>Any other Advance required for this programme </td><td id="finalAdvReq"></td></tr>
				<tr><td id="enteredRemarks"  colspan="4"></td></tr>
				<tr><td>Administrative office Remarks</td></tr>
				<tr><td id="adminOfficeApprovedRemark"  colspan="4"></td></tr>
				<tr><td>Administrative  Remarks</td></tr>
				<tr><td id="adminApprovedRemarks"  colspan="4"></td></tr>
				<tr><td  colspan="4">Signature of the Staff:</td></tr>
				</table>
				</div>
				</div>
				</div>