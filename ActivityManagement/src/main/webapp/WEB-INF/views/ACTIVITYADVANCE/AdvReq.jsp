
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
var totalAdvReq=0;
var requestedByRemarks;
var userCodeValue;
var advReqId;
var reqName;
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

/*nction programApprovalAdvanceReqView()
{	
	$.ajax({
		"url":"/expenditurehead/getActivityAdvanceRequest",
		"method":"GET",
		data:{"activityId":activityCode,"finyear":finyear},
		success:function(data)
			{
			console.log(data);	   
			if(data)
				{
				
					setUpDataTable(data,[{"description":"Expenditure Head"},{"amountAdvance":"Advance Requested"},{"reasonAdvance":"Reason of Advance"}],"advanceReqGridTable",null);
				
				}
				else
					{
					showMessage("no resource persons are added");
					
					}
			
			
			
			},
			error:function(e)
			{
			
			}
		
	});
	 //to display all currently added resource person data;
	 
}*/





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
    		// $(dropdown).empty(); 
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

function loadAllAcademicActivityTypeDetails() {
	$.ajax({
		"url" : "/academicactivity/getAllActivitySubmittedByStudent",
		"method" : "GET",
		success : function(data) {
			
			//console.log(data['asNO']);
			
		   setUpDataTable(data, [ 
			
			{
				"activitycode":"Activity Code"	
			},
			
			{
				"title":"Activity Name"
			},
			{
				"asno":"Administrative Sanction Number"},
			{
				"finyear":"Financial Year"}
			
		  ], "financiallyApprovedActivityTable", "select-checkbox");
			onDataTableClick('financiallyApprovedActivityTable', function() {
					if(selectedRowFromDataTable != null)
					   {
						$('html, body').animate({ scrollTop: $('#advReq').offset().top }, 'slow');
						$('#titleId').val(selectedRowFromDataTable[2]);
						console.log("asno iddddddd"+selectedRowFromDataTable[3]);
						var asNo=selectedRowFromDataTable[3];
						$('#aSNoId').val(asNo);
						activityCode=selectedRowFromDataTable[1];
						//particiaptionRequestId=selectedRowFromDataTable[1];
						finyear=selectedRowFromDataTable[5];
						$.ajax({
							"url":"/academicfinance/getActivityFinance",
							"method":"GET",
							data:{"activityId":activityCode},
							success:function(data)
								{
								console.log(data);
							//	$('#amtSanctionedASId').val(data['totalEstExp']);
								
								//to get the participant memeber details by participant request Id
								
				
								},
								error: function(e)
								{
									console.log(e);
								}
								
					            });//end ajax

					            //getting the total admitted amount
				      
					            
					            

					            var totalAdvReq=0;
					       /*      //to find out the total advance requested amount
					        $.ajax({
					    	   "url":"/expenditurehead/getActivityAdvanceRequest",
								"method":"GET",
								data:{"activityId":activityCode,"finyear":finyear},
							    success:function(data)
								{
								for(var i=0;i<data.length;i++)
									{
									totalAdvReq=totalAdvReq+data[i]['amountAdvance'];
									}
								
								
								  //advance raised through advance request Form
							       var totalRequestedAmnt=0.0;
						     
								
							
								},
								error: function(e)
								{
									console.log(e);
								}					
					            });  
					             */
					       /*  $.ajax({
						    	   "url":"/academicactivity/findTotalRequestedAmnt",
									"method":"GET",
									data:{"activityCode":activityCode,"particiaptionRequestId":particiaptionRequestId},
								    success:function(data)
									{
								    	console.log(data);
								    	for(var i=0;i<data.length;i++)
										{
										totalAdvReq=totalAdvReq+data[i]['advRequiredFig'];
										}
								    	console.log(totalAdvReq);
									
								    	$('#tmntArId').val(totalAdvReq);
										if(totalAdvReq>0)
											{
											$('#finalAdvReqId').val("yes");
										//	$('#programApprovalAdvReq').attr('disabled',false);
											}
										else
											{
											$('#finalAdvReqId').val("no");
										//	$("programApprovalAdvReq").removeAttr('disabled','disabled');
											}
										
										console.log(totalAdvReq); 
								    	
								    
									},
									error: function(e)
									{
										console.log(e);
									}					
						            });   */
							
					            
					            
					          
						            
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
					            
					            
					            
					            
					            
					            
					            
			              }//end if
			  });//on data table click end
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

$("#enteredRemarksId").val(requestedByRemarks);
//getAllPrograms();

$('#advReqForm').unbind().bind('submit',function(event)
{
      var advReqAmnt=$('#advRequiredFigId').val();
      var asAmnt=$('#amtSanctionedASId').val();
      var requestedAmnt=$('#tmntArId').val();
      var balamnttoberequested=asAmnt-requestedAmnt;
      console.log("balance amnt requested"+balamnttoberequested);
//val userCode=$('#nameId').val();
   //if((advReqAmnt-balamnttoberequested)>0)
  // {
  	//showMessage("requested amnt exceeds your advance limit");
 //}
    //  alert("advReqForm is clicked"+balamnttoberequested);
   //   console.log("entrrrr in advReqForm");
	      event.preventDefault();
	  
	  //submi   console.log("entrrr in expHeadTypeDetailsForm");
			event.preventDefault();
			var userCode=$('#rollNoCapacityId').val();
	//		alert("userCode"+userCode);
            rollNo=$("#rollNoCapacityId option:selected").text();
            reqName=$('#nameId').val();
          // alert($('#nameId').val());
			//$var = $("#rollNoCapacityId option:selected").text();
	
			
			var formData = $(this).serializeArray();
		
			//formData.push({"name":"","value":""});
			 	formData.push({"name":"ac","value":activityCode});
			 	formData.push({"name":"reqRasedUserRole","value":"student"});
			 	userCode=$('#rollNoCapacityId').val()
			 	formData.push({"name":"userCode","value":userCode});
			 	formData.push({"name":"rollNoCapacity","value":rollNo});
			 	formData.push({"name":"pr","value":particiaptionRequestId})
			     var formCheck = "success";

				$('#submitModel').modal('show');

				$('#confId').unbind().bind('click',function(){

					console.log("confirm clicked...");

					$.ajax({
						"url" : "/academicactivity/addAdvanceRequestDetails",
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
								var data_splits1 = data.split(":");
								console.log("request id"+data_splits1[1]);
								
								advReqId=data_splits1[1];
								 $("#printBt"). attr("disabled", false);
							//	$("#advReqForm").trigger('reset');
								
								
							
							}
							else
							{
								//var msg = data_splits[1];
								var msg = data_splits[1];
								centreDialog("Error",msg,"red");
							
							}
								
								

								$("#expHeadTypeDetailsForm").trigger('reset');
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
   		
   		
   		  $('#rollNoCapacityId').unbind().bind('change',function()
    		        {
   			  console.log()
   			  var userCode=$('#rollNoCapacityId').val();
   			  
   			  var rollNoSel=rollNo=$("#rollNoCapacityId option:selected").text();
   			  userCodeValue=userCode;
   			
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
	    	   "url":"/academicactivity/findTotalrequestedAmntByActivityIdAndPrcRqIdforstudent",
				"method":"GET",
				data:{"activityCode":activityCode,"particiaptionRequestId":particiaptionRequestId,"rollNo":rollNoSel},
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
				
				},
				error: function(e)
				{
					console.log(e);
				}					
	            });  
    		        });
/* $("#programApprovalAdvReq" ).on('click',function(event) { 
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
}); */

	$("#AdvanceViewAdvReqForm" ).on('click',function(event) { 
		console.log("AdvanceViewAdvReqFormView");
		   rollNo=$("#rollNoCapacityId option:selected").text();
		$.ajax({
			"url":"/academicactivity/findAdvReqByActivityCodeAndstRollNo",
			"method":"GET",
			data:{"activityId":activityCode,"rollno":rollNo},
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
	
	
	
	//change event
	
	$('input[name=myInput]').change(function() {
		
	
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Report 
	$('#printBt').unbind().bind('click',function()
		{
		
	
	   $.ajax({
                "url" : "/academicactivity/getAllAdvRequestInformationByRequestId",
                "method" : "GET",
                "data" : {"advReqId":advReqId},
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
      				$('#reqName').append(reqName);
      				 reportPrinting('reportDiv');
					}
				});
	  
		});
	
	//programApprovalAdvanceReqView();
	   

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
		<h3>Requisition for Advance by Students</h3>
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
						<label for="activitytype"> Name</label> <input type="text" 	name="reqpname" class="form-control" id="nameId" required ></input>
					</div>

					<div class="form-group">
						<label for="aSNoId">University Order No against which
							advance is requested</label> <input type="text" name="aSNo"
							class="form-control" id="aSNoId" required />
					</div>

                        <div class="form-group">
						<label for="amtSanctionedASId">Total amount of Advance Requested for this Activity</label> <input type="number" name="tmntAr" class="form-control" id="tmntArId"></input>
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
			<!-- 	<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
					<div class="form-group">
						<label>Advance Request Submit Date</label> <input type="text" id="enteredDateId" name="enteredDate" class="form-control dp"
							required></input>
					</div>
				</div> -->
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
					<div class="form-group" style="text-align: center">
						<input type="submit" class="btn btn-primary" value="ADD"/>
     			</div>
     			</div>
     			<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
     	   <div class="form-group">
              <button class="btn btn-primary" id="printBt" disabled="disabled" style="text-align: center"> Print</button>
           </div>
           </div>
			</div>
			
		</form>
	</div>
	<div class="row">
	
	       <p>
             <!--   <button class="btn btn-primary" type="button"
                  data-toggle="collapse" data-target="#programapprovalAdvanceRequest"
                  aria-expanded="false" aria-controls="collapseExample"
                  id="programApprovalAdvReq">View Advance Request through Program Approval
               </button> -->
                  <button class="btn btn-primary" type="button"
                  data-toggle="collapse" data-target="#AdvanceViewAdvReqFormView"
                  aria-expanded="false" aria-controls="collapseExample"
                  id="AdvanceViewAdvReqForm">View Requested Advance Details 
               </button>
            </p>
            <div class="collapse" id="AdvanceViewAdvReqFormView">
               <div class="card card-body">
                  <div class="table-responsive">
                     <table class="table table-striped" id="AdvRequestFormGridTable"></table>
                  </div>
               </div>
            </div>
<!--                <div class="collapse" id="programapprovalAdvanceRequest"> -->
<!--                <div class="card card-body"> -->
<!--                   <div class="table-responsive"> -->
<!--                      <table class="table table-striped" id="advanceReqGridTable"></table> -->
<!--                   </div> -->
<!--                </div> -->
<!--             </div> -->
	
	
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
				<tr><td colspan="4"><center>Requisition for Advance  by Students</center></td></tr>
				<tr><td>Name Of the Student</td><td id="reqName"></td></tr>
				<tr><td>Roll No and Capacity</td><td id="rollno"></td></tr>
				<tr><td>Amount Of Advance Required   in Words</td><td id="rswords"></td></tr>
				<tr><td>Amount Of Advance Required   in Figures</td><td id="advfig"></td></tr>
				<tr><td>Purpose for which Advance is Required  </td><td id="purpose"></td></tr>
				<tr><td>Amount for which Administrative Sanction received in Figures</td><td id="asamnt"></td></tr>
				<tr><td>University Order No against which advance is requested</td><td id="asno"></td></tr>
				<tr><td>Any other Advance required for this programme </td><td id="finalreqornot"></td></tr>
				<tr><td id="enteredRemark"  colspan="4"></td></tr>
				</table>
				</div>
				</div>
				</div>
