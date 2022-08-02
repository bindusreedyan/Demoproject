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
var adminapprovalremarks;
var printEnterRemark;

var activityId;
var brochureFilePath;




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

function loadAllActivityForAcademicApproval() {
	console.log("loadAllActivityForAcademicApproval");
	$.ajax({
		"url" : "/academicactivity/getAllActivityForAcademicApproved",
		"method" : "GET",
		success : function(data) {
			
			console.log(data);
			
			setUpDataTable(data, [ {
				"activity_type_description" : "Acitivity Type"
			}, 
			{
				"activityCode":"Activity Code"	
			},
			
			{
				"title":"Activity Name"
			},
			{
				"facultyCode":"facultyCode"
			},
			{
				"status" : "Status"
			}
		  ], "activityTable", "select-checkbox");

			
			onDataTableClick('activityTable', function() {		
				if(selectedRowFromDataTable != null)
				{
					$('html, body').animate({ scrollTop: $('#activityApproval').offset().top }, 'slow');
					activityId=selectedRowFromDataTable[2];
					$.ajax({
									"url" : "/academicactivity/getAllActivityById",
									"method" : "GET",						
									data : {"activityId":activityId},
									success : function(data) 
									{
									    console.log(data['aat']['activity_type_description']);
										$('#activityTypeCodeId').val(data['aat']['activityTypeCode']);
										$('#activityCodeId').val(activityId);
										$('#titleId').val(data['activityId']);
										$('#titleId').val(data['title']);
										
										
										var dateFrom=data['dateFrom'];
										var dateTo=data['dateTo'];
										var dateFrom1 = dateFrom.split("T")[0];
										var dateTo1=dateTo.split("T")[0];
										
										
										$('#dateFromId').val(dateFrom1);
										$('#dateToId').val(dateTo1);
										$('#finYearId').val(data['finyear']);
										$('#descriptionId').val(data['description']);
										$('#targetGroupId').val(data['targetGroup']);
										$('#outcomeId').val(data['outcome']);
										$('#deviationJustificationId').val(data['deviationJustification']);
										$('#typeId').val(data['type']);
										$('#activityLevelId').val(data['activityLevel']);
										brochureFilePath=data['brochureURL'];
										$('#remarksId').val(data['remarks']);
										var financeImplied=data['financeImplied'];
										console.log(financeImplied);
										if(financeImplied=="Y")
											{
											$( "#yesId" ).prop( "checked", true );
											}
										if(financeImplied=="N")
										{
										$( "#nosId" ).prop( "checked", true );
										}
									
									},
									error : function(e)
									{
									}
								});
					
					
					//to get active centres
				 	//to get all centress
				 	
				   	$.ajax({
				   		"url":"/academicfinance/getAllactiviticenters",
				   		"method":"GET",
				   		data:{"activityId":activityId},
				   		success : function(data) {
				   		 $('#centerdiv').empty();
				   			centerlength=data.length;
				   			console.log("centerlength"+centerlength);
				   			var i;
				   			for( i=0;i<data.length;i++){
				   			
				   			var num=i+1;
				   			var num1="center"+num.toString();
				   			checbxid=num1;
				   			//checbxname="center"+num1;
				   			
				   		
				   			 $('#centerdiv')
				   			 .append('<label class="checkbox-inline">')
				                .append('<input type="checkbox" id='+checbxid+' name='+num1+' checked="true" value='+data[i]['actvtyCenterKey']['cm']['centre_id']+'>'+data[i]['actvtyCenterKey']['cm']['centre_name'] )
				                .append('</label>')
				   			 }
				   			
				   			
				   		},
				   		error : function(e) {
				   			//showMessage("Error in Getting Programs. Contact Admin");
				   		}
				   	});
				   
						}
				else
					{
					
					}
				
			})
	},
		error : function(e)
			{
			}
	});

}//function ends here

function loadAllActivityForAcademicApprovedByLogin() {
	console.log("loadAllActivityAcademicallyApproved");
	$.ajax({
		"url" : "/academicactivity/getAllActivityAcadmicApprovedByLogin",
		"method" : "GET",
		success : function(data) {
				
			setUpDataTable(data, [ {
				"activity_type_description" : "Acitivity Type"
			}, 
			{
				"activityCode":"Activity Code"	
			},
			
			{
				"title":"Activity Name"
			},
			{
				"finyear":"Financial Year"},
		    {
					"facultyCode":"facultyCode"
			},
			
			{
				"status" : "Status"
			}
		  ], "academicapproveactivityTable", "select-checkbox");

		},
		error : function(e)
		{
		}
	});

		}








$(document).ready(function()
{			
	$('#menuHaed').text("");
   	var mySelect = $('#finYearId');
 	  var currentYear = (new Date()).getFullYear();
 	var startYear = currentYear+1;
 	var prevYear = currentYear;
 	  mySelect.append(
 	   $('<option></option>').val("se").html("Select"));
 	for (var i = 0; i < 30; i++) {
 	 
 	  mySelect.append(
 	
 	    $('<option></option>').val(prevYear + "-" + startYear).html(prevYear + "-" +startYear )
 	  );
 	  startYear = startYear - 1;
 	  prevYear = prevYear - 1;
 	}
 		
	loadAllActivityForAcademicApproval();
	loadAllActivityForAcademicApprovedByLogin();
	//$('#activityTypeDetails').hide();
   $("#adminApprovedDate").datepicker().datepicker("setDate", new Date());
	$('.dp').datepicker({
	    format: 'yyyy-mm-dd',							   
		autoclose:true,
		clearBtn:true
	});
	
	
	
	//brochure view
	$( "#viewBrochureId" ).unbind().bind('click',function() {
		alert(brochureFilePath);

			var urlGen="filerepo_public/minioFiles/nuals/"+brochureFilePath;
					console.log(urlGen);
					$('#imageFile').remove();
					$('#objectDiv').empty();
					var imgf=$('<object  class="img-responsive" style="width: 450px; height: 450px;" id="imageFile"></object >')
					$('#objectDiv').append(imgf);
					$('#imageFile').removeAttr('data');
					$('#imageFile').removeAttr('src');
					 $('#imageFile').attr('data',urlGen);
					 $('#imageModal').modal();



			
		});
	
	
	
	
	
	
	
	
	$('#approvedStatusId').change(function(){ 
			var val=$(this).val();
			//alert(val);
			if(val=="academicapproved")
				{
				 adminapprovalremarks="Certified that the content, relevance and the approprientness of the programme and resource persons are examined, verified and certified by an appropriate academic forum/authority.";
				$('#adminremarksId').val(adminapprovalremarks);
				}
			
			else
			{
				$('#adminremarksId').val("");
			}
			
		
});
	
	
	
   	$.ajax({
   		"url" : "/academicactivity/getAllActivityTypes",
   		"method" : "GET",
   		success : function(data) {
   			console.log(data);
   			setUpDropDown("activityTypeCodeId",data,"activityTypeCode","activity_type_description");
   		},
   		error : function(e) {
   			//showMessage("Error in Getting Programs. Contact Admin");
   		}
   	});
	
	/*$("#areaOfHostelId").keypress(function (e) {
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
      
      
	});*/
   
//$('#fldset').prop('disabled', true);
	
	/*$( "#formRowId" ).on("dblclick",function() {
		console.log("Double clicked...");

		if($('#fldset').prop('disabled') == false)
		{
			$('#fldset').prop('disabled', true);
		}
		else
		{
			$('#fldset').prop('disabled', false);
		}
			
	});*/

	$( "#cancelbt" ).on("click",function() {
		console.log("Double clicked...");
		$('#activityTypeDetails').hide();
				
	});

//$( "#verifyBtn" ).on('click',function(event) {
		
	//	event.preventDefault();

		/****** To set status based on verification type  *******/
		
		 /* var verifyType = $("#verifyType").val();

		  if(verifyType == "")
	      {
	    	  depstatus = "l1check";
	      }
		  else if(verifyType == "vf")
	      {
	    	  depstatus = "forward";
	      }
	      else if(verifyType == "a")
	      {
	    	  depstatus = "valid";
	      }*/

	    
		/********************************************/

	
		/*if(dc == "")
		{
			centreDialog("Error","Select Record to Verify...","red");
		}*/
		
			/*$('#verifyModel').modal('show');

			$('#confId').unbind().bind('click',function(){
				//$('#activityTypeCodeId').prop('disabled', false);
				console.log("confirm clicked...");
			console.log($('#activityTypeCodeId').val());
				var l1rem =  $("#u_v_remarksId").val();
				console.log(dc);
				console.log(l1rem);
				$.ajax({
					"url" : "/academic/approveActivityType",
					"method" : "GET",
					data : {"hostelmasterid":dc,"hstlmasterstatus":depstatus,"level1Remarks":l1rem},
					success : function(data) {
						
						var datasplit = data.split('-');
						if(datas == "NOTVERIFIED")
						{
							
							
							
							centreDialog("Error",data,"red");
						
							
						//	var msg = datasplit[1]+"Hostel ID: "+did+"<br/>Hostel Name : "+ds+"<br/> Address: "+df;
							
						}
						else
						{
							
							centreDialog("Success",data,"green");

							clearDataTable("activityTypeTable");
							loadAllHostelDetails();

							$('#hostelMasterDetails').hide();
							$("#u_v_remarksId").val("");
							
						
						}
						
					},
					error : function(e) {
						//showMessage("Error in editing Department. Contact Admin");
					}
				});
				

				$('#verifyModel').modal('hide');
				
			});
			
			$('#cancelId').on('click',function(){

				console.log("Cancel clicked...");

			

				$('#verifyModel').modal('hide');
				
			});
			

			
	

		
			
	});*/
	
	
	   $("#resourcePersonviewbtId" ).on('click',function(event) {
		   	
		   	$.ajax(
		   			{
		   				"url":"/resource/getResourcepersondetailofcurrentActivityByActivityId",////
		   				"method":"GET",
		   				 data : {"activityId":activityId},
		   				success:function(data)
		   				{
		   					if(data)
		   					{
		   						console.log(data.length);
		   					
		   						setUpDataTable(data,[{"resId":"resourcePersonId"},{"name":"Name"},{"designation":"Designation"},{"email":"Email"},{"contactPhone":"Contact Phone"}],"gridTable2",null);
		   						onDataTableClick('gridTable2', function() {		
		   							if(selectedRowFromDataTable != null)
		   							{
		   									   	
		   						       resId=selectedRowFromDataTable[0];
		   								$("#resourceId").val(selectedRowFromDataTable[0]);
		   							//	$("#nameId").val(selectedRowFromDataTable[1]);
		   								//$("#designationId").val(selectedRowFromDataTable[2]);
		   								
		   								$.ajax({
		   							   				"url":"/resource/getResourcepersondetailofById",
		   							   				"method":"GET",
		   							   				 data : {"resId":resId},
		   							   				success:function(data1)
		   							   				{
		   							   					if(data1)
		   							   					{
		   							   						alert(data1['name']);
		   							   					$("#nameId1").val(data1['name']);
		   				   								$("#designationId1").val(data1['designation']);
		   							   					$("#organisationId1").val(data1['organisation']);
		   				   								$("#domainId1").val(data1['domain']);
		   				   								$("#categoryId1").val(data1['category']);
		   				   								$("#addressId1").val(data1['address']);
		   				   								$("#contactName1").val(data1['contactName']);
		   				   								$("#contactPhoneId1").val(data1['contactPhone']);
		   				   								$("#emailId1").val(data1['email']);
		   				   								$("#panNoId1").val(data1['panNo']);
		   				   							$("#passportNoId1").val(data1['passportNo']);
		   				   								$("#accountNoId1").val(data1['accountNo']);
		   				   								$("#bankNameId1").val(data1['bankName']);
		   				   								$("#branchId1").val(data1['branch']);
		   				   								$("#accountTypeId1").val(data1['accountType']);
		   				   								$("#ifscId1").val(data1['ifsc']);
		   				   							
		   				   								$('#exampleModalLong1').modal({backdrop: 'static',keyboard: false});
		   							   					}
		   							   					
		   							   				},
		   							   			error : function(e) {
		   						   		   			//showMessage("Error in Getting Programs. Contact Admin");
		   						   		   		}
		   						   			});
		   								
		   								
		   								
		   							

		   								
		   							}
		   						
		   					});
		   					
		   					}
		   					
		   					else
		   						{
		   						showMessage("no Resource Persons");
		   						}
		   				},
		   				error : function(e) {
		   		   			//showMessage("Error in Getting Programs. Contact Admin");
		   		   		}
		   			});
		   
		   	
		   });
	
	

	
	
	
	
	
$('#activityMasterDetailsForm').unbind().bind('submit',function(event) {
	                    event.preventDefault();
	                    var formData = $(this).serializeArray();
  		              if($("#yesId").prop('checked') == true){
			          console.log("checked yes");
		
		              	financeImplied="Y";
		                 }
		
if($("#noId").prop('checked') == true){
	console.log("checked false");
			
			financeImplied="N";
		}
formData.push({"name":"financeImplied","value":financeImplied});
formData.push({"name":"activityCode","value":activityId});
formData.push({"name":"brochureURL","value":brochureFilePath});
		var formCheck = "success";
			$('#submitModel').modal('show');
			$('#confId').unbind().bind('click',function(){
				console.log("confirm clicked...");
				$.ajax({
					"url" : "/academicactivity/AcademicApprove",
					"method" : "POST",
					 data : formData,
					 success : function(data)
					 {
						 console.log(data);
						// showMessage("academically approved");
						centreDialog("Success","academically approved","green");
						 $('#approvedSubmit').prop("disabled",true);
							
							printEnterRemark=$('#remarksId').val();
							
							adminapprovalremarks=$('#adminremarksId').val();
							loadAllActivityForAcademicApproval();
					 },
					 error : function(e) {
		   		   		showMessage("Error in approval. Contact Admin");
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
$('#printBt').unbind().bind('click',function()
		{
	  
	//   var titleText="REQUEST ACADEMICAPPROVAL FOR PROGRAMMES CONDUCTED BY NUALS/ VARIOUS  CENTRES OF STUDY AND RESEARCH";
	  // reportPrinting('reportDiv',titleText,activityId);
	  // $('#reportDiv').show();
	   $.ajax({
					"url" : "/academicactivity/getAllAcademicApprovalDetailsReport",
					"method" : "GET",						
					"data" : {"activityId":activityId},
					success:function(data)
					{
						var d=data;
						centres=d["centres"];
						resources=d['resourcePersons']
						console.log("checking centres");
						console.log(centres);
						for(var key in d)
						{
							$('#'+key).val(d[key]);
							$('#'+key).text(d[key]);
						}
						
						for(let i=0;i<centres.length;i++)
						{
							$('#centrestab').append('<tr><td colspan="1"><td colspan="2">'+centres[i]+'</td></tr>');	
						}
						 for(let i=0;i<resources.length;i++)
						{
							$('#resourcestab').append('<tr><td colspan="2">'+resources[i]+'</td></tr>');	
						} 
						//$('#dialogShow').modal({backdrop: 'static',keyboard: false});
						
						$('#remarksPr').append(printEnterRemark);
						$('#adminRemarksPr').append(adminapprovalremarks);
						   reportPrinting('reportDiv');
					}
				});

		});
</script>
<div  id="gridRow" class="card card-info card-outline">
  	<div class="card-header">
		  <h3>List Of Activities</h3>
	  
  	</div>
    <div class="card-body">
	   	<div class="table-responsive">
				<table class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border" id="activityTable">
			 
				</table>
		</div>
	</div>
</div>

<div  id="activityApproval" class="card card-info card-outline">
   <div class="card-header">
      <h3>Academic Approval Details</h3>
   </div>
   <div class="card-body">
      <form id="activityMasterDetailsForm" enctype="multipart/form-data">
         <div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="form-group" style="width: 100%">
               <label for="activityType">Activity Type<span
                  class="required">*</span></label>
               <div style="width: 100%">
                  <div style="display: inline-block; width: 100%">
                     <select class="form-control selectpicker" name="aat"
                        id="activityTypeCodeId" required>
                     </select>
                  </div>
               </div>
            </div>
            
                      
            <div class="form-group">
               <label for="activitytype"> Name/Title of the Program</label> <input
                  type="text" name="title" class="form-control" id="titleId"
                  required />
            </div>
            <div class="row">
               <div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
                  <label for="date from">From</label> <input type="text"
                     id="dateFromId" name="dateFrom" class="form-control dp" required></input>
               </div>
               <div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
                  <label for="date to">To</label> <input type="text"
                     id="dateToId" name="dateTo" class="form-control dp" required></input>
               </div>
            </div>
            <div class="form-group" style="width: 100%">
               <label for="finYear">Financial Year<span class="required">*</span></label>
               <div style="width: 100%">
                  <div style="display: inline-block; width: 100%">
                     <select class="form-control selectpicker" name="finyear"
                        id="finYearId" required>
                     </select>
                  </div>
               </div>
            </div>
            <div class="form-group" id="centerdiv">
               <label for="activity type"> Centres involved in the Program
               <label>
            </div>
<!--             <div class="form-group"> -->
<!--             <label for="brochure"> Brochure</label> <input type="text" -->
<!--                name="brochureURL" class="form-control" id="brochureURLId" -->
<!--                required /> -->
<!--             </div> -->
            
            <div class="form-group">
<!--             <label for="brochure" class="col-sm-3 col-form-label"> Upload Brochure</label>  -->
<!--              <input class="file"  id="brochureURLId" name="brochureURL" type="file" ext="jpg" required> -->
                  <button doc="brochureImage" class="btn btn-primary upload" id="viewBrochureId">View Brochures</button>     
             </div>
            
            
            <div class="form-group">
               <label for="description"> A brief description of the
               Objects of the Programme</label>
               <textarea id="descriptionId" name="description" rows="4" cols="5"
                  class="form-control"></textarea>
            </div>
            <div class="form-group">
               <label for="targetgroup"> Content, Relevance And Target
               Group</label>
               <textarea id="targetGroupId" name="targetGroup" rows="4" cols="50"
                  class="form-control"></textarea>
            </div>
            <div class="form-group">
               <label for="Outcome"> Expected Outcome and Quantified
               Deliverables</label>
               <textarea id="outcomeId" name="outcome" rows="4" cols="50"
                  class="form-control" required></textarea>
            </div>
            <p>
            <button class="btn btn-primary" type="button"
                  data-toggle="collapse" data-target="#collapseExample"
                  aria-expanded="false" aria-controls="collapseExample"
                  id="resourcePersonviewbtId">View Resource Person Details
               </button>
          
            </p>
            <div class="collapse" id="collapseExample">
               <div class="card card-body">
                  <div class="table-responsive">
                     <table class="table table-striped" id="gridTable2"></table>
                  </div>
               </div>
            </div>
            <div class="form-group">
               <label for="deviationJustification"> Observed deviations
               from University rules , regulations and Govt Orders and
               justifications </label>
               <textarea id="deviationJustificationId"
                  name="deviationJustification" rows="4" cols="50"
                  class="form-control" required></textarea>
            </div>
            <div class="form-group">
               <label for="type">type<span class="required">*</span></label>
               <div style="width: 100%">
                  <div style="display: inline-block; width: 100%">
                    <select class="form-control selectpicker" name="type" id="typeId">
                        <option value="organising">Organising</option>
                     </select>
                  </div>
               </div>
            </div>
            <div class="form-group">
               <label for="activityLevel">Activity Level<span
                  class="required">*</span></label>
               <div style="width: 100%">
                  <div style="display: inline-block; width: 100%">
                     <select class="form-control selectpicker" name="activityLevel"
                        id="activityLevelId" required>
                        <option value="se">Select</option>
                        <option value="local">Local</option>
                        <option value="interuniversity">Inter University</option>
                        <option value="national">National</option>
                        <option value="international">International</option>
                     </select>
                  </div>
               </div>
            </div>
            <div class="form-group">
               <label for="financeImpliedId">Financial Implication Include<span
                  class="required">*</span></label> <label class="radio-inline"> <input
                  type="radio" id="yesId"  value="yes" name="finance">YES
               </label> <label class="radio-inline"> <input type="radio" id="noId" value="no" name="finance">NO
               </label>
            </div>
              <div class="form-group">
               <label for="remarks">Remarks</label>
               <textarea id="remarksId" name="remarks" rows="4" cols="50"
                  class="form-control"></textarea>
            </div>
            
               <div class="form-group">
               <label for="activityLevel">Academic Approval<span
                  class="required">*</span></label>
               <div style="width: 100%">
                  <div style="display: inline-block; width: 100%">
                     <select class="form-control selectpicker" name="status"
                        id="approvedStatusId" required>
                        <option value="">Select</option>
                        <option value="academicapproved">Approve</option>
                        <option value="academicrejected ">Reject</option>
                        
                     </select>
                  </div>
               </div>
            </div>
            
            <div class="form-group">
               <label for="remarks"> Remarks For Academic Approval</label>
               <textarea id="adminremarksId" name="adminApprovalRemarks" rows="4" cols="50"
                  class="form-control"></textarea>
            </div>
            <div class="form-group" style="text-align: center">
               <button type="submit" class="btn btn-primary" id="approvedSubmit">SUBMIT</button>
            </div>
           <!--  <button type="button" id="centerActivity" class="btn btn-primary"
               data-toggle="modal" data-target="#dialogCenterActivityEdit" style=>
            Add Center Activity Finance</button>
         </div> -->
         </div>
      </form>
             <div class="form-group">
              <button class="btn btn-primary" id="printBt">PRINT</button>
           </div>
         
   </div>
</div>

<div  id="gridRow" class="card card-info card-outline">
  	<div class="card-header">
		  <h3>List Of Activities Academically Approved</h3>
	  
  	</div>
    <div class="card-body">
	   	<div class="table-responsive">
				<table class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border" id="academicapproveactivityTable">
			 
				</table>
		</div>
	</div>
</div>



<!-- Modal Resource Person-->
<div class="modal fade" id="exampleModalLong1" tabindex="-1"
   role="dialog" aria-labelledby="exampleModalLongTitle"
   aria-hidden="true">
   <div class="modal-dialog" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLongTitle">Edit Resource
               Person Details
            </h5>
            <button type="button" class="close" data-dismiss="modal"
               aria-label="Close">
            <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="modal-body">
            <form id="ResourcePersonDetailsFormEdit">
               <div class="row">
                  <div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
                      <div class="form-group">
                        <label for="activitytype"> Resource Person Id</label> <input type="text"
                           name="resId" class="form-control" id="resourceId" required />
                     </div>
                     <div class="form-group">
                        <label for="activitytype"> Name</label> <input type="text"
                           name="name" class="form-control" id="nameId1" required />
                     </div>
                     <div class="form-group">
                        <label for="activitytype"> Designation</label> <input
                           type="text" name="designation" class="form-control"
                           id="designationId1"  />
                     </div>
                     <div class="form-group">
                        <label for="activitytype"> Organisation</label> <input
                           type="text" name="organisation" class="form-control"
                           id="organisationId1"  />
                     </div>
                     <div class="form-group">
                        <label for="activitytype"> Domain</label> <input type="text"
                           name="domain" class="form-control" id="domainId1"  />
                     </div>
                     <div class="form-group">
                        <label for="activitytype"> Category</label> <input type="text"
                           name="category" class="form-control" id="categoryId1"  />
                     </div>
                     <div class="form-group">
                        <label for="activitytype"> Address</label>
                        <textarea class="form-control" rows="4" cols="10" id="addressId1"
                           name="address" ></textarea>
                     </div>
                     <div class="form-group">
                        <label for="activitytype"> Contact Name</label> <input
                           type="text" name="contactName" class="form-control"
                           id="contactName1"  />
                     </div>
                     <div class="form-group">
                        <label for="activitytype"> Contact Phone</label> <input
                           type="text" name=contactPhone class="form-control"
                           id="contactPhoneId1" required />
                     </div>
                  </div>
                  <div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
                     <div class="form-group">
                        <label for="activitytype"> Email</label> <input type="text"
                           name=email class="form-control" id="emailId1" required />
                     </div>
                     <div class="form-group">
                        <label for="activitytype"> Pan No</label> <input type="text"
                           name=panNo class="form-control" id="panNoId1"  />
                     </div>
                  <div class="form-group">
                        <label for="activitytype"> Passport No</label> <input type="text"
                           name=passportNo class="form-control" id="passportNoId1" />
                     </div>
                     <div class="form-group">
                        <label for="activitytype"> Account No</label> <input type="text"
                           name=accountNo class="form-control" id="accountNoId1"  />
                     </div>
                     <div class="form-group">
                        <label for="activitytype"> Bank Name</label> <input type="text"
                           name=bankName class="form-control" id="bankNameId1"  />
                     </div>
                     <div class="form-group">
                        <label for="activitytype"> Branch</label> <input type="text"
                           name=branch class="form-control" id="branchId1"  />
                     </div>
                     <div class="form-group">
                        <label for="activitytype"> Account Type</label> <input
                           type="text" name=accountType class="form-control"
                           id="accountTypeId1"  />
                     </div>
                     <div class="form-group">
                        <label for="activitytype"> IFSC Code</label> <input type="text"
                           name=ifsc class="form-control" id="ifscId1"  />
                     </div>
                  </div>
                  <div class="modal-footer">
                     <button type="button" class="btn btn-secondary"
                        data-dismiss="modal">Close</button>
                     <button type="submit" class="btn btn-primary"
                        id="resourceButtonAdd" >Edit</button>
                        <button type="button" class="btn btn-primary"
                        id="deleteResource">Delete</button>
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

<div  autofocus class="modal fade" id="imageModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">File Display</h5>
        		 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
	  </div>   
	   <div class="modal-body" id="objectDiv">
		<object  class="img-responsive" style="width: 450px; height: 450px;" id="imageFile"></object >
      </div>
      <div class="modal-footer">

      </div>
    </div>
  </div>
</div>

<div class="row">
	<div class="col-md-12">
		<div class="table-responsive" id="reportDiv" style="display: none">
			<table class="table table-striped" id="repTable">
				<tr><td colspan="4">
				 <img src="../images/logo.jpg" style="width: 40px;">
				</tr>
				<tr><td colspan="4">REQUEST FOR ACADEMIC APPROVE FOR PROGRAMMES CONDUCTED BY NUALS/ VARIOUS  CENTRES OF STUDY AND RESEARCH</tr>
				<tr><td>Name/Title of the Programme</td><td colspan="3" id="title"></td></tr>
				<tr><td>Date of Event/Program</td><td id="dateFrom"></td><td id="dateTo" colspan="2"></td></tr>
				<tr><td colspan="4">Number Centres Involved in Organising the Programme</td></tr>
				<tr><td><table id="centrestab" class="table table-striped"></table></td></tr>
				<tr><td colspan="4"><u> A Brief Description of the Program </u></td></tr>
				<tr><td id="description" colspan="4"></td></tr>
				<tr><td colspan="4"><u>Content,Relevance And Target Group</u></td></tr>
				<tr><td id="targetGroup" colspan="4"></td></tr>
				<tr><td colspan="4"><u>Expected Outcome and Quantified Deliverables</u></td></tr>
				<tr><td id="outcome" colspan="4"></td></tr>
				<tr><td colspan="4"><u>Resource Persons </u></td></tr>
			    <tr><td colspan="4"><table id="resourcestab" class="table table-striped"></table> </td></tr>
				<tr><td colspan="4">Observed Deviations from University Rules,regulations and Govt Orders</td></tr>
				<tr><td id="deviationJustification" colspan="4"></td></tr>
				<tr><td  colspan="4"><u>Remarks</u></td></tr>
				<tr><td id="remarksPr" colspan="4"></td></tr>
					<tr><td  colspan="4"><u>Academic Remarks</u></td></tr>
				<tr><td id="adminRemarksPr" colspan="4"></td></tr>
				<tr><td>Kalamassery</td><td>    </td><td>Director</td><td></td></tr>
				<tr><td>Date:</td><td id="currentDate" ></td><td>Center</td><td></td></tr>
			</table>	
		</div>
	</div>

</div>
