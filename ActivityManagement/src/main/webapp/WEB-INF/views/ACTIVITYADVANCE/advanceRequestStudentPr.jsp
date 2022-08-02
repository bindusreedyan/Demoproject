
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
var totalAdvReq=0;
var totalAdvReq1=0;
var totalAdvanceRequested=0;
var advReqId;
var aSNo;
var nameStaff;
var userCode;
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
function loadAllAdvanceRequestSubmittedDetails() {
	$.ajax({
		"url" : "/academicactivity/findAdvReqByBylogin",
		"method" : "GET",
		success : function(data) {
			
		/* 	for(var i=0;i<data.length;i++)
				{
				
				if(data[i]['adv_paid']==0)
					{
					data[i]['adv_paid']='Not Paid'
					}
				if(data[i]['adv_paid']==1)
				{
				data[i]['adv_paid']='Paid'
				}
				} */
			
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
				"adv_paid" : "Status"
			}
			
		  ], "advanceRequestActivityTable", "select-checkbox");
		},
		error:function(e)
		{
		}
	})
}
function loadAllAcademicActivityTypeDetails() {
	$.ajax({
		"url" : "/academicactivity/getAllActivitySubmittedByStudent",
		"method" : "GET",
		success : function(data) {
			
			//console.log(data['asNO']);
			console.log("enterrrred in getAllActivitySubmittedByStudent");
			
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
				"finyear":"Financial Year"}
			
		  ], "financiallyApprovedActivityTable", "select-checkbox");
			onDataTableClick('financiallyApprovedActivityTable', function() {
					if(selectedRowFromDataTable != null)
					   {
						$('html, body').animate({ scrollTop: $('#advReq').offset().top }, 'slow');
						$('#titleId').val(selectedRowFromDataTable[2]);
						$('#titleId').attr('disabled',true); 
						console.log("asno iddddddd"+selectedRowFromDataTable[2]);
				        asNo=selectedRowFromDataTable[3];
						$('#aSNoId').val(asNo);
						$('#aSNoId').attr('disabled',true); 
						activityCode=selectedRowFromDataTable[1];
						finyear=selectedRowFromDataTable[4];
						
						$.ajax({
							"url":"/academicfinance/getActivityFinance",
							"method":"GET",
							data:{"activityId":activityCode},
							success:function(data)
								{
								console.log(data);
								$('#amtSanctionedASId').val(data['total_estimated']);
								
								var advanceeligible=parseFloat(data['total_estimated'])/2;
								$('#tmntArId').val(advanceeligible);
								$('#amtSanctionedASId').attr('disabled',true); 
								},
								error: function(e)
								{
									console.log(e);
								}					
					            }); 
						
						     totalAdvReq=0;
					            //to find out the total advance requested amount throgh program approval
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
							     
								//   $("#tmntArId").val(totalAdvReq);
								//  totalAdvanceRequested=totalAdvReq+totalAdvReq1;
								  if(totalAdvanceRequested>0)
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
								      //adavance taken through advance request form
							        $.ajax({
								    	   "url":"/academicactivity/findTotalRequestedAmntByFaculty",
											"method":"GET",
											data:{"activityCode":activityCode},
										    success:function(data)
											{
										    	totalAdvReq1=0;
										    	for(var i=0;i<data.length;i++)
												{
										    		totalAdvReq1=totalAdvReq1+data[i]['advRequiredFig'];
												}
										    	console.log("totalAdvReq1="+totalAdvReq1);
										    	totalAdvanceRequested=0;
										    //	$('#tmntArId').val(totalAdvReq);
										    
										     totalAdvanceRequested=totalAdvReq+totalAdvReq1;
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
										    	
										    
											},
											error: function(e)
											{
												console.log(e);
											}					
								            });  
								      
								      
							   	 var userCode=$('#usercode').val();
							        $.ajax({
						   		    	"url":"/students/getStudentsDetails-V4",
						   				"method":"GET",
						   				data:{"userCodes":userCode},
						   	  			 success:function(data)
						   	  			   {
						   	  				 console.log("student name"+data);
						   	  				 console.log(data[0]['studentName']);
						   	  				 $('#nameId').val(data[0]['studentName']);
					   },
						   	  			   error:function(e)
						   	  			   {
						   	  				   
						   	  			   }
						   	  			   
					   			        }); 
								
				},
								error: function(e)
								{
									console.log(e);
								}					
					            });  
					      
		           
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
		    loadAllAdvanceRequestSubmittedDetails();
		    nameStaff=$('#empNameId').val();
		    $('#advReqForm').unbind().bind('submit',function(event)
				{
		    	console.log("entrrrr in advReqForm facultyyyyyyyyyyyyyyyyyyyyy");
			     event.preventDefault();
		    	$('#titleId').attr('disabled',false); 
		    	$('#empNameId').attr('disabled',false); 
		    	$('#designationId').attr('disabled',false); 
		    	$('#aSNoId').attr('disabled',false); 
		    	$('#amtSanctionedASId').attr('disabled',false); 
		    	
				      var advReqAmnt=$('#advRequiredFigId').val();
				      var asAmnt=$('#amtSanctionedASId').val();
				      var eligbleamnt=$('#tmntArId').val();
				  //    var balamnttoberequested=asAmnt-requestedAmnt;
				    //  console.log("balance amnt requested"+balamnttoberequested);
				var totalAdvanceRequestedupdated=totalAdvanceRequested+parseFloat($('#totalAdvanceRequestedId').val());
				      
				      if((totalAdvanceRequestedupdated>eligbleamnt))
				      {
				    	  
				    	var r= confirm("requested amnt exceeds your advance limit pls enter the reason in remarks field")
				    	if(r==true)
				    		{
				    		  var formData = $(this).serializeArray();
							    //formData.push({"name":"","value":""});
							 	formData.push({"name":"ac","value":activityCode});
								formData.push({"name":"reqRasedUserRole","value":"faculty"});
								//formData.push({"name":"rollNoCapacity","value":activityCode});
							 	
							     var formCheck = "success";

								$('#submitModel').modal('show');

								$('#confId').unbind().bind('click',function(){

									console.log("confirm clicked...");

									$.ajax({
										"url" : "/academicactivity/addAdvanceRequestDetailsofFaculty",
										"method" : "POST",
										 data : formData,
										 success : function(data) {
											console.log(data);
													//showMessage("Hostel Floor Details Saved.");
												//centreDialog("Info",data,"green");
											console.log(data);
						   	   	  			var data_splits = data.split("-");
						   	   	   	var data_splits1 = data.split(":");
						   	   	  	
						   	   	  	advReqId=data_splits1[1];
						   	   	  	console.log("advrequest iddd"+advReqId);
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
												//$("#advReqForm").trigger('reset');
												//clearDataTable("activityTypeTable");
												loadAllAcademicActivityTypeDetails();
												loadAllAdvanceRequestSubmittedDetails();
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

			    		
				    		}
				    		
				    		
				    		 
				      }
				      
				      else
				    	  {
				    		var formData = $(this).serializeArray();
						    //formData.push({"name":"","value":""});
						 	formData.push({"name":"ac","value":activityCode});
							formData.push({"name":"reqRasedUserRole","value":"student"});
							
							
							
							//formData.push({"name":"rollNoCapacity","value":activityCode});
						 	
						     var formCheck = "success";

							$('#submitModel').modal('show');

							$('#confId').unbind().bind('click',function(){

								console.log("confirm clicked...");

								$.ajax({
									"url" : "/academicactivity/addAdvanceRequestDetailsofStudent",
									"method" : "POST",
									 data : formData,
									 success : function(data) {
										console.log(data);
												//showMessage("Hostel Floor Details Saved.");
											//centreDialog("Info",data,"green");
										console.log(data);
					   	   	  			var data_splits = data.split("-");
					   	   	  	var data_splits1 = data.split(":");
					   	   	  	
					   	   	  	advReqId=data_splits1[1];
					   	   	  	console.log("advrequest iddd"+advReqId);
					   	   	  	
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
										//	$("#advReqForm").trigger('reset');
											//clearDataTable("activityTypeTable");
											loadAllAcademicActivityTypeDetails();
											loadAllAdvanceRequestSubmittedDetails();
										}
									
										
									});

								$('#submitModel').modal('hide');
								
							});
							
							$('#cancelId').unbind().bind('click',function(){

								console.log("Cancel clicked...");

								$('#submitModel').modal('hide');
								
							});
				    	  }
				    	 
				  	   //showMessage("requested amnt exceeds your advance limit pls enter the reason in remarks field");
				       //}
					     	 
					  
				});
		    
		    
		    	$("#AdvanceViewAdvReqForm" ).on('click',function(event) { 
				console.log("AdvanceViewAdvReqFormView");
				 rollno=$('#rollNoCapacityId').val();
				$.ajax({
					"url":"/academicactivity/getAllAdvanceRequestRaisedByRollNo",
					"method":"GET",
					data:{"activityId":activityCode,"rollno":rollno},
					success:function(data)
						{
						console.log(data);	   
						if(data)
							{
							
							 $('#programapprovalAdvanceRequest').hide();
							 $('#AdvanceViewAdvReqFormView').show();
								setUpDataTable(data,[{"purpose":"Purpose of Advance Request"},{"advRequiredFig":"Advance Requested"},{"advanceReqStatus":"Advance Request Status"}],"AdvRequestFormGridTable",null);
							
							}
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
		<h3>Requisition for Advance by Student</h3>
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
						<label for="accountName">Name of the Student</label> <input type="text"
							class="form-control grouping" name="empName" id="empNameId"></select>
					</div>
					<div class="form-group">
						<label for="activitytype">Roll NO</label> <input type="text" 	name="rollNoCapacity" class="form-control" id="rollNoCapacityId" required ></input>
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
				<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
					<div class="form-group" style="text-align: right">
						<input type="submit" class="btn btn-primary" value="ADD"/>
     			</div>
			</div>
				<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
     	   <div class="form-group">
              <button class="btn btn-primary" id="printBt">Print</button>
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
                  id="programApprovalAdvReq">View Advance Request through Program Approval For this Activity
               </button>
               </div>
               <div class="row">
                  <button class="btn btn-primary" type="button"
                  data-toggle="collapse" data-target="#AdvanceViewAdvReqFormView"
                  aria-expanded="false" aria-controls="collapseExample"
                  id="AdvanceViewAdvReqForm">View Advance Request through Request Form
               </button>
               </div>
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

<div id="gridRow" class="card card-info card-outline">
	<div class="card-header">
		<h3>List Of Advance Request Submitted</h3>

	</div>
	<div class="card-body">
		<div class="table-responsive">
			<table
				class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border"
				id="advanceRequestActivityTable">

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
				<tr><td colspan="4"><center>Requisition for Advance  Faculty/ Staff</center></td></tr>
				<tr><td>Name Of the Staff</td><td id="reqName"></td></tr>
				<tr><td>Designation</td><td id="designation"></td></tr>
				<tr><td>Amount Of Advance Required   in Words</td><td id="advRequiredWords"></td></tr>
				<tr><td>Amount Of Advance Required   in Figures</td><td id="advRequiredFig"></td></tr>
				<tr><td>Purpose for which Advance is Required  </td><td id="purpose"></td></tr>
				<tr><td>Amount for which Administrative Sanction received in Figures</td><td id="amtSanctionedAS"></td></tr>
				<tr><td>University Order No against which advance is requested</td><td id="aSNo"></td></tr>
				<tr><td>Any other Advance required for this programme </td><td id="finalAdvReq"></td></tr>
				<tr><td id="enteredRemarks"  colspan="4"></td></tr>
				<tr><td  colspan="4">Signature of the Staff:</td></tr>
				<tr> <td id="dateId"> </td><td  colspan="3" id="designation">Designation:</td>
				</table>
				</div>
				</div>
				</div>