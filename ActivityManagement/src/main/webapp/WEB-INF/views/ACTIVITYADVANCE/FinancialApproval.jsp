
<link rel="stylesheet" href="./sfs_public/css/jquery-confirm.min.css">
<script src="./sfs_public/js/jquery-confirm.min.js"></script>
<script>
var userCode;
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
var advanceRequestId;
var advanceReqId;
var financeComents;
var userCode;
function getAllPrograms()
{
  $.ajax(
  {
      url:'/course/getAllCourses',
      method:'GET',
      success:function(data)
      {
      setUpDropDown("selectProgram",data,"courseId","courseCode");
      //setUpDropDown("selectProgram1",data,"courseId","courseCode");
      $('#selectProgram').unbind().bind('change',function()
      {
    	  programId=$(this).val();
    	  
      getAllActiveBatchesOfProgram($(this).val());
      });
      },
      error:function(err)
      {
      showMessage(err);
      }
      });
}

function getAllRollNos()
{
  $.ajax(
  {
      url:'/academicactivity/getAllRollNosByActivityId',
      method:'GET',
      success:function(data)
      {
      setUpDropDown("selectProgram",data,"courseId","courseCode");
      //setUpDropDown("selectProgram1",data,"courseId","courseCode");
      $('#selectProgram').unbind().bind('change',function()
      {
    	  programId=$(this).val();
    	  
      getAllActiveBatchesOfProgram($(this).val());
      });
      },
      error:function(err)
      {
      showMessage(err);
      }
      });
}


function getAllActiveBatchesOfProgram(programId)
{
$.ajax(
{
  url:'/batch/getAllActiveBatchesInAProgram',
    method:'GET',
    data:{"courseId":programId},
    success:function(data)
    {
    console.log(data);
    setUpDropDown('selectBatch',data,'batchId','batchCode');
      $('#selectBatch').unbind().bind('change',function()
        {
    	  
    batchId=$(this).val();
    console.log(batchId);
    $.ajax({
      method:"GET",
      url: "/students/getStudentsDetailsByProgramAndBatch-V2",
      data:{"programId":programId,"batchId":batchId},
      success:function(data)
      {
    	  for(let i=0;i<data.length;i++)
    		{
    			studentDetails[data[i]['studentCode']]=data[i];
    			
    		}
    	 
    	  setUpDropDown("nameId",data,"studentCode","studentName");
    	  $('#nameId').unbind().bind('change',function()
    		        {
    		 // console.log($(this.val());
    		// alert($(this).val());
    		 
    		  var rollNo1=studentDetails[$(this).val()]['rollNo'];
    		  if(rollNo1!=null)
    			  {
    		  console.log("rollno"+rollNo1);
    		  $("#rollNoCapacityId").val(studentDetails[$(this).val()]['rollNo']);
    			  }
    		  else
    			  {
    			  showMessage("rollno is not added now");
    			  }

    		        });	  
      },
      error(e)
      {
    	  console.log(e);
    	  }
      });

        });
    },
    error:function(err)
    {
    showMessage(err);
    }
  });  
 } 

function loadAllAdminApprovedAdvanceRequest() {
	$.ajax({
		"url" : "/academicactivity/getAllAdvanceRequestForAdminApproval",
		"method" : "GET",
		success : function(data) {

		   setUpDataTable(data, [ 
				 {
					   "adv_req_id":"Advance Request Id"
				 },
				 {
					 "particiaption_request_id":"Participated Request Id"  
				 },
				{
					"activity_code":"Activity Code"	
				},
				
				{
					"title":"Activity Name"
				},
				{ "asno":"Administrative Sanction Number"},
				{
				"financial_year":"Financial Year"
				},
					
				{
					"roll_no_capacity":"RollNo"},
				{
					"advance_req_status" : "Status"
				}
		
		  ], "financiallyApprovedActivityTable", "select-checkbox");
			onDataTableClick('financiallyApprovedActivityTable', function() {
					if(selectedRowFromDataTable != null)
					   {
						$('html, body').animate({ scrollTop: $('#advReq').offset().top }, 'slow');
						
						advanceReqId=selectedRowFromDataTable[1];
						$('#titleId').val(selectedRowFromDataTable[4]);
						
						//$( "#titleId" ).prop( "disabled", true );
						//$( "#rollNoCapacityId" ).prop( "disabled", true );
						
						//$( "#nameId" ).prop( "disabled", true );
					//	$( "#aSNoId" ).prop( "disabled", true );
						
						
					//	$( "#finalAdvReqId" ).prop( "disabled", true );
					//	$( "#tmntArId" ).prop( "disabled", true );
						///
						//$( "#amtSanctionedASId" ).prop( "disabled", true );
						console.log("asno iddddddd"+selectedRowFromDataTable[4]);
						var asNo=selectedRowFromDataTable[5];
						$('#aSNoId').val(asNo);
						userCode=$('#rollNoCapacityId').val();
						activityCode=selectedRowFromDataTable[3];
						particiaptionRequestId=selectedRowFromDataTable[2];
						finyear=selectedRowFromDataTable[6];
						rollno=selectedRowFromDataTable[7];
						advanceRequestId=selectedRowFromDataTable[1];
						$.ajax({
							"url":"/academicfinance/getActivityFinance",
							"method":"GET",
							data:{"activityId":activityCode},
							success:function(data)
								{
								console.log(data);
								//$('#amtSanctionedASId').val(data['totalEstExp']);
								
								//to get the participant memeber details by participant request Id
								
								$.ajax({
									"url" : "/academicactivity/getAllParticipantDetailsByRequestId",
									"method" : "GET",						
									data : {"particiaptionRequestId":particiaptionRequestId},
									success : function(data) 
									{
									
									},
									error : function(e)
									{
									}
								});
								},
								error: function(e)
								{
									console.log(e);
								}
								
					            });//end ajax

					            var totalAdvReq=0;
					        
					            
					            $.ajax({
					 	    	   "url":"/academicactivity/findTotalrequestedAmntByActivityIdAndPrcRqIdforstudent",
					 				"method":"GET",
					 				data:{"activityCode":activityCode,"particiaptionRequestId":particiaptionRequestId,"rollNo":rollno},
					 			    success:function(data)
					 				{
					 			    	console.log(data);
					 			    	var totalAdvReqested=0;
					 			    	for(var i=0;i<data.length;i++)
					 					{
					 			    		totalAdvReqested=totalAdvReqested +data[i]['advRequiredFig'];
					 					}
					 			    	console.log(totalAdvReq);
					 				
					 			    	$('#tmntArId').val(totalAdvReqested);
					 			   //	$( "#rollNoCapacityId" ).prop( "disabled", true );
					 				},
					 				error: function(e)
					 				{
					 					console.log(e);
					 				}					
					 	            });
					            
					            
					            
					            
					            
					            
					            
					            
					            
					            
					            
					            
					            
					            
					            
					            
					            
					            
					            
					            
					    
					        $.ajax({
					    		"url":"/academicactivity/getParticiapntOtherExpensesDetailsByParticipantRequestId",
					    		"method":"GET",
					    		data:{"participantRequestId":particiaptionRequestId},
					    		success:function(data)
					    		{
					    										
					    		$("#amtSanctionedASId").val(data['totalAdmitAmount']);
					    	       $( "#amtSanctionedASId" ).prop( "disabled", true );
					    		},
					    		error : function(e)
					    	        {
					    		}
					    	   });  
					        
					        
					        
					      //get advance request by roll no and activityId
			            	$.ajax({
			    			"url":"/academicactivity/getAllAdvanceRequestRaisedByAdvanceRequestId",
			    			"method":"GET",
			    			data:{"advanceRequestId":advanceRequestId},
			    			success:function(data)
			    				{
			    				
			    				console.log("data length"+data.length);
			    			//	console.log(data['userCode']+"rollnoCapacity"+data['rollnoCapacity']);	   
			    				if(data)
			    					{					
									var $select = $("select[name='rollNoCapacityId']")
									$select.append($("<option>", { value: data['userCode'], html:data['rollNoCapacity']}));
										
			    					$('#finalAdvReqId').val(data['finalAdvReq']);
			    					$('#advRequiredFigId').val(data['advRequiredFig']);
			    					$('#advRequiredWordsId').val(data['advRequiredWords']);
			    					$('#purposeId').val(data['purpose']);
			    					$('#rollNoCapacityId').val(data['userCode']);
			    					 userCode=$('#rollNoCapacityId').val();
			    					$('#adminApprovedRemarksId').val(data['adminApprovedRemarks']);
			    					$('#sanctionedAmtId').val(data['sanctionedAmt']);
			    					$('#FacultyRecomRemarksId').val(data['recommendedRemarks']);
			    					 var userCode=$('#rollNoCapacityId').val();
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
							   	    $.ajax({
								    	   "url":"/academicactivity/findTotalRequestedAmnt",
											"method":"GET",
											data:{"activityCode":activityCode,"particiaptionRequestId":particiaptionRequestId,"userCode":userCode},
										    success:function(data)
											{
										    	console.log(data);
										    	var totalAdvReqested=0;
										    	for(var i=0;i<data.length;i++)
												{
										    		totalAdvReqested=totalAdvReqested+data[i]['advRequiredFig'];
												}
										    	console.log(totalAdvReq);
											
										    	$('#tmntArId').val(totalAdvReqested);
											
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
			            	$.ajax({
				    			"url":"/academicactivity/getAdvRequestNotSettled",
				    			"method":"GET",
				    			 data:{"rollno":rollno},
				    			 success:function(data)
				    				{
				    				 
				    				 var count=0;
				    			     console.log("settlemnt"+data);
				    			     
				    			     for(var i=0;i<data.length;i++)
				    			    	 {
				    			    	 count++;
				    			    	 }
				    			     if(count>0)
				    			    	 {
				    			    	 console.log("pending settlement"+count);
				    			    	 $("#pendingSettlementId").val("yes");
				    			    	 $("#suggestionId").show();
				    			    	 
				    			    	 }
				    			     if(count==0)
				    			    	 {
				    			    	 $("#pendingSettlementId").val("no");
				    			    	 $("#suggestionId").hide();
				    			    	 }
				    				},
				    				error:function(e)
				    				{
				    				
				    				} 
				    	           });      
					      
					
					      
					       
			              }//end if
			  });//on data table click end
         },
         error:function(e)
			{
			
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

	loadAllAdminApprovedAdvanceRequest();
//getAllPrograms();
   $("#AdvanceViewAdvReqForm" ).on('click',function(event) { 
					    		console.log("AdvanceViewAdvReqFormView");
					    		   rollno=$("#rollNoCapacityId option:selected").text();
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
					    	//to know the advarequest which are not settled or closed
					    	
					    	}); 
$('#advReqForm').unbind().bind('submit',function(event)
{
      var advReqAmnt=$('#advRequiredFigId').val();
      var asAmnt=$('#amtSanctionedASId').val();
      var requestedAmnt=$('#tmntArId').val();
      var balamnttoberequested=asAmnt-requestedAmnt;
      console.log("balance amnt requested"+balamnttoberequested);
//val userCode=$('#nameId').val();
  /* if((advReqAmnt-balamnttoberequested)>0)
   {
  	showMessage("requested amnt exceeds your advance limit");
 }*/
    //  alert("advReqForm is clicked"+balamnttoberequested);
   //   console.log("entrrrr in advReqForm");
	      event.preventDefault();
	  
	  //submi   console.log("entrrr in expHeadTypeDetailsForm");
			event.preventDefault();
			var userCode=$('#rollNoCapacityId').val();
            rollNo=$("#rollNoCapacityId option:selected").text();
			
			//$var = $("#rollNoCapacityId option:selected").text();
	
			
			var formData = $(this).serializeArray();
		
			//formData.push({"name":"","value":""});
			
			//var finalAdvRequest=$('#finalAdvReqId').val();
			
			
			 	//formData.push({"name":"ac","value":activityCode});
			 //	formData.push({"name":"reqRasedUserRole","value":"student"});
			 //	formData.push({"name":"userCode","value":userCode});
			 //	formData.push({"name":"rollNoCapacity","value":rollNo});
			 //	formData.push({"name":"advReqId","value":advanceReqId})
			 	//formData.push({"name":"finalAdvReq","value":finalAdvRequest})
			 	formData.push({"name":"advReqId","value":advanceReqId})
			     var formCheck = "success";

				$('#submitModel').modal('show');

				$('#confId').unbind().bind('click',function(){

					console.log("confirm clicked...");

					$.ajax({
						"url" : "/academicactivity/financialApprovalAdvanceRequest",
						"method" : "POST",
						 data : formData,
						 success : function(data) {
							console.log(data);
									//showMessage("Hostel Floor Details Saved.");
								//centreDialog("Info",data,"green");
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
								loadAllAdminApprovedAdvanceRequest();
							}
						
							
						});

					$('#submitModel').modal('hide');
					
				});
				
				$('#cancelId').unbind().bind('click',function(){

					console.log("Cancel clicked...");

					$('#submitModel').modal('hide');
					
				});
});
   		
   		
   		  $('#rollNoCapacityId').unbind().bind('change',function()
    		        {
   			  console.log()
   			  var userCode=$('#rollNoCapacityId').val();
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
    		        });
$("#programApprovalAdvReq" ).on('click',function(event) { 
	$.ajax({
		"url":"/expenditurehead/getActivityAdvanceRequest",
		"method":"GET",
		data:{"activityId":activityCode,"finyear":finyear},
		success:function(data)
			{
			console.log(data);	   
			if(data)
				{
				 $('#AdvanceViewAdvReqFormView').hide();
				 $('#programapprovalAdvanceRequest').show();
					setUpDataTable(data,[{"description":"Expenditure Head"},{"amountAdvance":"Advance Requested"},{"reasonAdvance":"Reason of Advance"}],"advanceReqGridTable",null);
				
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

	/*$("#AdvanceViewAdvReqForm" ).on('click',function(event) { 
		console.log("AdvanceViewAdvReqFormView");
		   rollno=$("#rollNoCapacityId option:selected").text();
		$.ajax({
			"url":"/academicactivity/getAllAdvanceRequestRaised",
			"method":"GET",
			data:{"activityId":activityCode,"rollno":rollno},
			success:function(data)
				{
				console.log(data);	   
				if(data)
					{
					
					 $('#programapprovalAdvanceRequest').hide();
					 $('#AdvanceViewAdvReqFormView').show();
						setUpDataTable(data,[{"purpose":"Purpose of Advance Request"},{"advRequiredFig":"Advance Requested"}],"AdvRequestFormGridTable",null);
					
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
	});*/
		
		 $('#advanceReqStatusId').change(function(){ 
			//var adminApprovalRemarks="Based on the information provided and the undertaking made approval is accorded to make an advance payment of Rs.................."
			    var financeComents="Advance released is entered as Serial no                          on page                           Of the Advance Register for the Financial Year 2018-19  with Advance Reference No ";				
		        var value = $(this).val();
		        alert(value);
		        if(value=="FinanciallyApproved")
				  {
				  $('#commentsFinanceId').val(financeComents)
				  }
		        if(value=="FinanciallyRejected")
				  {
				  $('#commentsFinanceId').val("");
				  }
		        
		    });
	
	
	
	$("#AdvanceViewAdvReqForm" ).on('click',function(event) { 
		console.log("AdvanceViewAdvReqFormView");
		   rollno= rollNo=$("#rollNoCapacityId option:selected").text();
		$.ajax({
			"url":"/academicactivity/findAdvReqByActivityCodeAndstRollNo",
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
	//programApprovalAdvanceReqView();
	   

});
</script>
<div id="gridRow" class="card card-info card-outline">
	<div class="card-header">
		<h3>Payment Approval</h3>
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
		<h3>Payment Approval of Advance Request </h3>
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
						<label for="accountName">RollNo</label> <select
							class="form-control grouping" name="rollNoCapacityId" id="rollNoCapacityId"></select>
					</div>
					<div class="form-group">
						<label for="activitytype"> Name</label> <input type="text" 	name="nameId" class="form-control" id="nameId" required ></input>
					</div>

					<div class="form-group">
						<label for="aSNoId">University Order No against which
							advance is requested</label> <input type="text" name="aSNo"
							class="form-control" id="aSNoId" required />
					</div>
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
						<label for="amtSanctionedASId">Total amount of Advance Requested For This Activity</label> <input type="number" name="tmntAr" class="form-control" id="tmntArId" required></input>
					</div>
				</div>
			</div>

            <div class="row">
			<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
		    <div class="form-group">
               <label for="remarks">Faculty Recommendation</label>
               <textarea id="FacultyRecomRemarksId" name="FacultyRecomRemarks" rows="4" cols="50"
                  class="form-control"></textarea>
            </div>
            </div>
            </div>



			<div class="row">
			<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
		    <div class="form-group">
               <label for="remarks">Admin Approval Comments</label>
               <textarea id="adminApprovedRemarksId" name="adminApprovedRemarks" rows="4" cols="50"
                  class="form-control"></textarea>
            </div>
            </div>
            </div>
            <div class="row">
			<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
                  <div class="form-group">
						<label for="activitytype">Any other advance pending settlement against payee</label>
						<div>
							<select class="form-control" name="pendingSettlement"
								id="pendingSettlementId">
								<option value="se">Select</option>
								<option value="yes">Yes</option>
								<option value="no">No</option>
							</select>
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
            </div>
            <div class="row">
			<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12" >
                  <div class="form-group">
						<label for="activitytype">if Yes Detail with Suggestion</label>
						<div>
					<textarea id="suggestionId" name="suggestions" rows="4" cols="200"
                  class="form-control"></textarea>
            </div>
						</div>
					</div>
            </div>
    
             <div class="row">
			<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
			 <div class="form-group">
               <label for="activityLevel">Financial Approval<span
                  class="required">*</span></label>
                  <div style="width: 100%">
                  <div style="display: inline-block; width: 100%">
                     <select class="form-control selectpicker" name="advanceReqStatus" id="advanceReqStatusId" required>
                        <option value="">Select</option>
                        <option value="FinanciallyApproved">Approve</option>
                        <option value="FinanciallyRejected">Reject</option>
                     </select>
                  </div>
               </div>
            </div>
            </div>
            </div>
            <div class="row">
            <div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12" >
                  <div class="form-group">
						<label for="activitytype">Advances Sanction Comments</label>
					<div>
					<div>
					<textarea id="commentsFinanceId" name="commentsFinance" rows="4" cols="50"
                       class="form-control"></textarea>
                   </div>
                 </div>
			 </div>
			 </div>
			 </div>  
		
			<div class="row">
				<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group" style="text-align: center">
						<input type="submit" class="btn btn-primary" value="ADD" id="advReqBt"/>
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
                  id="programApprovalAdvReq" disabled="disabled">View Advance Request through Program Approval
               </button>
                  <button class="btn btn-primary" type="button"
                  data-toggle="collapse" data-target="#AdvanceViewAdvReqFormView"
                  aria-expanded="false" aria-controls="collapseExample"
                  id="AdvanceViewAdvReqForm">View All Requested Advance Details
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
               <div class="card card-body">
                  <div class="table-responsive">
                     <table class="table table-striped" id="advanceReqGridTable"></table>
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