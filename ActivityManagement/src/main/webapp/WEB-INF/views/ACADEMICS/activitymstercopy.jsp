<link rel="stylesheet" href="./sfs_public/css/jquery-confirm.min.css">
<script src="./sfs_public/js/jquery-confirm.min.js"></script>
<script>

var centerlength=0;
var financeImplied;

var activityId;

var resourcepers=[];
var resPersCount=0;
var allocatedfundamount=0
var finYear;
var exptilldate=0;
var centerarray=[];
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



/*function loadAllActivityMasterDetails() {
	$.ajax({
		"url" : "/expenditurehead/getAllExpHeadDetails",
		"method" : "GET",
		success : function(data) {
			
			console.log(data);
			
			setUpDataTable(data, [ {
			"description" : "Expenditure Head Name"
			}, 
			{
			 "finYear":"Financial Year"
			},
			{
				 "allocationAmount":"Allocated Amount"
			},
			{
				"status" : "Status"
			}
		  ], "expHeadTable", "select-checkbox");

			
			onDataTableClick('expHeadTable', function() {
				
			})

			

		}
	});

}*/

function getCurrentFinancialYear() {
	  var fiscalyear = "";
	  var today = new Date();
	  if ((today.getMonth() + 1) <= 3) {
	    fiscalyear = (today.getFullYear() - 1) + "-" + today.getFullYear()
	  } else {
	    fiscalyear = today.getFullYear() + "-" + (today.getFullYear() + 1)
	  }
	  return fiscalyear
	}

$(document).ready(function()
{
				
	$('#menuHaed').text("");
	$('.dp').datepicker({
	    format: 'yyyy-mm-dd',							   
		autoclose:true,
		clearBtn:true
	});
	
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
	
	
	
	//loadAllExpHeadDetails();

	//restrictSpecialChars("activityTypeCode");	
	//restrictSpecialChars("description");	
	/*restrictSpecialChars("addressId");		
	restrictSpecialChars_allow_dot_comma("amenitiesId");
	restrictSpecialChars_allow_dot_comma("geoCoordinatesId");*/
	//restrictSpecialChars_allow_dot_comma("remarksId");
	
	
	
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
	$.ajax({
		"url" : "/academicactivity/getAllActivityTypes",
		"method" : "GET",
		success : function(data) {
			console.log(data);
			setUpDropDown("activityTypeCodeId",data,"activityTypeCode","description");
		},
		error : function(e) {
			//showMessage("Error in Getting Programs. Contact Admin");
		}
	});

	
	$.ajax({
		"url" : "/academicactivity/getAllCentres",
		"method" : "GET",
		success : function(data) {
			centerlength=data.length;
			console.log(data);
			var i;
			for( i=0;i<data.length;i++){
			
			var num=i+1;
			var num1="center"+num.toString();
			checbxid=num1;
			//checbxname="center"+num1;
			 $('#centerdiv')
			 .append('<label class="checkbox-inline">')
             .append('<input type="checkbox" id='+checbxid+' name='+num1+' value='+data[i]['centre_id']+'>'+data[i]['centre_name'])
             .append('</label>')
			 }
			
			
		},
		error : function(e) {
			//showMessage("Error in Getting Programs. Contact Admin");
		}
	});


$('#activityMasterDetailsForm').unbind().bind('submit',function(event) 
{
	
	        console.log("entrrr in activityMasterDetailsForm");
			event.preventDefault();
			
			finYear=$("#finYearId"). val();
			var formData = $(this).serializeArray();
		
			//formData.push({"name":"","value":""});
			
			
			if($("#yesId").prop('checked') == true){
				console.log("checked yes");
				
				financeImplied="Y";
			}
			
	if($("#noId").prop('checked') == true){
		console.log("checked false");
				
				financeImplied="N";
			}
	formData.push({"name":"financeImplied","value":financeImplied});
			
			var formCheck = "success";

				$('#submitModel').modal('show');

				$('#confId').unbind().bind('click',function(){

					console.log("confirm clicked...");

					$.ajax({
						"url" : "/academicactivity/addActivityMaster",
						"method" : "POST",
						 data : formData,
						 success : function(data) {
							console.log(data);
                             activityId=data['activityCode'];
									//showMessage("Hostel Floor Details Saved.");
								
								centreDialog("Successfully saved Program Details with activity Id"+activityId,data,"green");

							
								var i=0;
								
								for(i=0;i<resourcepers.length;i++)
								$.ajax({
									
									"url" : "/resource/addActivityResource",
									"method" : "GET",
									 data : {"activityId":activityId,"resId":resourcepers[i]},
									 
									 success : function(data) {
											console.log(data);
											
									 },
								
									 error : function(e) {
											showMessage("Error in saving in resourceperson Details. Contact Admin");
											
										}
									 
								});
								
							//	$("#activityMasterDetailsForm").trigger('reset');
								//to show the center activity model
								
								for(i=0;i<centerlength;i++)
									{
									
									checbxid="center"+i;
									var num=i+1;
									var num1="center"+num.toString();
									//checbxname="center"+num1;			
									if($("#"+num1).prop('checked') == true){
										
										//alert($("#"+num1).val());
										
										var centerid=$("#"+num1).val();
										
										//adding activitycode and center id to the actitivycenter table
										
										$.ajax({
											
											"url" : "/academicfinance/addActivityCenter",
											"method" : "GET",
											 data : {"activityId":activityId,"centerId":centerid},
											 success : function(data) {
													console.log(data);
													
											 },
											 error : function(e) {
													showMessage("Error in saving in activity center add Details. Contact Admin");
													
												}
											 
										});
									
									}
									
									}
								
								if($("#yesId").prop('checked') == true){
									
								}
								
								$("#activityMasterDetailsForm").trigger('reset');
						 },
					
					 error : function(e) {
							showMessage("Error in saving in actiivtymaste Details. Contact Admin");
							
						}
									
		});

					$('#submitModel').modal('hide');
					
				});
				
				$('#cancelId').unbind().bind('click',function(){

					console.log("Cancel clicked...");

					$('#submitModel').modal('hide');
					
				});

});

//submit resource person details


	//to display all resource person data;

/*$("#resourcePersonviewbtId" ).on('click',function(event) {
	
	$.ajax(
			{
				"url":"/resource/getResourcepersondetail",////
				"method":"GET",
				success:function(data)
				{
					if(data)
					{
					
						setUpDataTable(data,[{"name":"Name"},{"designation":"Designation"},{"email":"Email"},{"contactPhone":"Contact Phone"}],"gridTable2",null);
					//	$( "#accordion" ).accordion({active: 0,collapsible: true,active: 'none', heightStyle: "content" ,autoHeight: false,navigation: true });
					}
				}
			});

	
});*/

//to display all currently added resource person data;
$("#resourcePersonviewbtId" ).on('click',function(event) {
	
	$.ajax(
			{
				"url":"/resource/getResourcepersondetailofcurrentActivity",////
				"method":"GET",
				 data : {"resIdarrea":resourcepers.toString()},
				success:function(data)
				{
					if(data)
					{
					
						setUpDataTable(data,[{"name":"Name"},{"designation":"Designation"},{"email":"Email"},{"contactPhone":"Contact Phone"}],"gridTable2",null);
					//	$( "#accordion" ).accordion({active: 0,collapsible: true,active: 'none', heightStyle: "content" ,autoHeight: false,navigation: true });
					}
				}
			});

	
});

$('#ResourcePersonDetailsForm').unbind().bind('submit',function(event)
		{
			event.preventDefault();
			var formData=$(this).serializeArray();
			
		    $.ajax(
	   		{
		    	"url":"/resource/addResource",
				"method":"POST",
	  			 data:formData,
	  			 success:function(data)
	  			{
	  				
	  				showMessage("Saved resource person with id"+data['resId']);
	  				console.log(data);
	  				resourcepers[resPersCount]=data['resId'];
	  		        resPersCount++;
	  			
	  				$("#ResourcePersonDetailsForm").trigger('reset');
	  			},
	  			error:function(e)
	  			{
	  				showMessage("Error in adding resource person. Contact Admin");
	  			}
	   		});
		});
		
		
		//to view center related dataaaaaaaaaa
		
$("#centerActivity" ).on('click',function(event) {
	
	alert("entrrrrrrr");
	var fundAllocated=0.0;
	var expendincurred=0.0;
	
console.log("activityid--------"+activityId);
	$.ajax({
		"url":"/academicfinance/getAllactiviticenters",
		"method":"GET",
		data:{"activityId":activityId},
		 success:function(data)
		 {
			try
			{
				if(data.length>0)
				{	
				$('#divGridTable2').show();
				$('#centerActivityeBody2').empty();
			  						
					for(var i=0;i<data.length;i++)
					{
						
						var currentfinyear=getCurrentFinancialYear();
						var centerId=data[i]["actvtyCenterKey"]["cm"]["centre_id"];	
						var centerName=data[i]["actvtyCenterKey"]["cm"]["centre_name"];
						var fundallocatedId=centerId+"fundallocated";
						var expincuId=centerId+"expincu"
						var Balance_exclude_unassignedGrant=centerId+"blnceexunasgngrnt";
						var estimatedExp=centerId+"estimatedExp";
						var natnlsemnrcount=centerId+"nationalseminarcount";
						var lecturecount=centerId+"lecturecount";
						var tr="";
						centerarray[i]=centerId;
						//var allocatedfundamount=0;
						//var exptilldate=0;
						$.ajax({
							"url":"/centerfund/getAllCenterFundDetailsByCenterIdAndFinYear",
							"method":"GET",
							  data:{"currentfinyear":currentfinyear,"centerId":centerId},
							 success:function(data1)
							 {
							console.log(data1);
						//	var fundallocatedId=centerId+"fundallocated";
							//var expincuId=centerId+"expincu"
							//var Balance_exclude_unassignedGrant=centerId+"blnceexunasgngrnt";
							//var estimatedExp=centerId+"estimatedExp";
							//var natnlsemnrcount=centerId+"nationalseminarcount"
						//	var lecturecount=centerId+"lecturecount";
							
						     allocatedfundamount=data1["fundAmount"];
							console.log(allocatedfundamount);
                             exptilldate=data1["expTillDate"];
                            console.log(exptilldate);
                         //    tr=$('<tr><td>'+centerName+'</td><td><input class="centerHeads form-control" id="'+fundallocatedId+'" type="number" name="'+fundallocatedId+'" value="'+allocatedfundamount+'"></input></td><td><input class="centerHeads form-control" id="'+expincuId+'" type="number" value="'+exptilldate+'" name="'+expincuId+'"></input></td><td><input class="centerHeads form-control" id="'+Balance_exclude_unassignedGrant+'" type="number" name="'+Balance_exclude_unassignedGrant+'"></input></td> <td><input class="centerHeads form-control" id="'+estimatedExp+'" type="number" name="'+estimatedExp+'"></input></td> <td><input class="centerHeads form-control" id="'+natnlsemnrcount+'" type="number" name="'+natnlsemnrcount+'"></input></td><td><input class="centerHeads form-control" id="'+lecturecount+'" type="number" name="'+lecturecount+'"></input></td><tr>');
                            //var tr=$('<tr><td>'+centerName+'</td><td><input class="centerHeads form-control" id="'+fundallocatedId+'" type="number" name="'+fundallocatedId+'"></input></td><td><input class="centerHeads form-control" id="'+expincuId+'" type="number" name="'+expincuId+'"></input></td><td><input class="centerHeads form-control" id="'+Balance_exclude_unassignedGrant+'" type="number" name="'+Balance_exclude_unassignedGrant+'"></input></td><td><input class="centerHeads form-control" id="'+estimatedExp+'" type="number" name="'+estimatedExp+'"></input></td><td><input class="centerHeads form-control" id="'+natnlsemnrcount+'" type="number" name='"+natnlsemnrcount+"'></input></td><td><input class="centerHeads form-control" id="'+lecturecount+'" type="number" name="'+lecturecount+'"></input></td></tr>');
						//	$('#centerActivityeBody2').append(tr);
					 
					         },
						      error:function(e)
					         {
						         showMessage("Error in adding resource person. Contact Admin");
				            }
					    });
						
						
						 tr=$('<tr><td>'+centerName+'</td><td><input class="centerHeads form-control" id="'+fundallocatedId+'" type="number" name="'+fundallocatedId+'" value="'+allocatedfundamount+'"></input></td><td><input class="centerHeads form-control" id="'+expincuId+'" type="number" value="'+exptilldate+'" name="'+expincuId+'"></input></td><td><input class="centerHeads form-control" id="'+Balance_exclude_unassignedGrant+'" type="number" name="'+Balance_exclude_unassignedGrant+'"></input></td> <td><input class="centerHeads form-control" id="'+estimatedExp+'" type="number" name="'+estimatedExp+'"></input></td> <td><input class="centerHeads form-control" id="'+natnlsemnrcount+'" type="number" name="'+natnlsemnrcount+'"></input></td><td><input class="centerHeads form-control" id="'+lecturecount+'" type="number" name="'+lecturecount+'"></input></td><tr>');
						 $('#centerActivityeBody2').append(tr);
						 
						console.log("centerrrrrrrrrrrrrr name"+centerName);
				
					}
				}
				else
					{
					showMessage("no fee heads are found under the selected group");
					$('#centerActivityeBody2').empty();
					}
			}
			catch(e)
			{
				showError("Error in processing information.contact Admin");
			}	 
		 },
	            error:function(e)
                  {
	                   showMessage("Error in adding resource person. Contact Admin");
                  }
	
})
//to get the details of the expenditure heads



$.ajax({
		"url":"/expenditurehead/getExpenditureHeadsByFinYear",
		"method":"GET",
		data:{"finyear":finYear},
		success:function(data)
		 {
			try
			{
                    
				$('#expenditureGrid').show();
				$('#expenditureGridBody').empty();
			  						
					for(var i=0;i<data.length;i++)
					{
						var des=data[i]["expHeadsKey"]["description"];
						var nualsalloctinamount=des+"nualsalloctinamount";
						var estmtdexpdture=des+"estmtdexpdture";
						var deviation=des+"deviation";
						var comments=des+"comments";
						var sino=i+1;

						 tr=$('<tr><td>'+sino+'</td><td>'+data[i]["expHeadsKey"]["description"]+'</td><td><input class="expheads form-control" id="'+nualsalloctinamount+'" type="number"  value=""></input></td><td><input class="expheads form-control" id="'+estmtdexpdture+'" type="number"  value=""></input></td><td><input class="expheads form-control" id="'+deviation+'" type="number"  value=""></input></td><td><input class="expheads form-control" id="'+comments+'" type="number"  value=""></input></td><tr>');
						 $('#expenditureGridBody').append(tr);
						;
					}
					
				//	$('#advnceRequestGrid').show();
					$('#advnceRequestGridBody').empty();
				  						
						for(var i=0;i<data.length;i++)
						{
							var des=data[i]["expHeadsKey"]["description"];
							var advanceRequired=des+"advanceRequired";
							var requirementReason=des+"requirementReason";
							var sino=i+1;

							 tr=$('<tr><td>'+sino+'</td><td>'+data[i]["expHeadsKey"]["description"]+'</td><td><input class="expheads form-control" id="'+advanceRequired+'" type="number"  value=""></input></td><td><input class="expheads form-control" id="'+requirementReason+'" type="number"  value=""></input></td><tr>');
							 $('#advnceRequestGridBody').append(tr);
							;
						}
				
			}
			catch(e)
			{
				
			}
			},
			 error:function(e)
			 {
			 	showMessage("Error in adding resource person. Contact Admin");
			 }

});

























});
	
$('#externalfundSelect').change(function(){ 
    var value = $(this).val();
    if(value=="yes")
    	{
    	$.ajax({
    		"url":"/academicfinance/getAllactiviticenters",
    		"method":"GET",
    		data:{"activityId":activityId},
    		 success:function(data)
    		 {
    			try
    		{
    				
    				if(data.length>0)
    				{	
    				$('#divGridTable3').show();
    				$('#centerActivityeBody3').empty();
    			  						
    					for(var i=0;i<data.length;i++)
    					{
    						
    						//alert(data[i]);
    						
    						console.log(data[i]["actvtyCenterKey"]["cm"]["centre_name"]);

                      var tr=$('<tr><td>'+data[i]["actvtyCenterKey"]["cm"]["centre_name"]+'</td><td><input class="centerHeads form-control" id="externalFund" type="number" name="externalFund"></input></td></tr>');
    						$('#centerActivityeBody3').append(tr);
    					}
    				}
    				else
    					{
    					showMessage("no expheads are found under the selected group");
    					$('#centerActivityeBody3').empty();
    					}
    			}
    			catch(e)
    			{
    				showError("Error in processing information.contact Admin");
    			}	 
    		 },
    	 error:function(e)
    {
    	showMessage("Error in adding resource person. Contact Admin");
    }
    	
    });
    	}
    if(value=="no")
    	{
    	$('#divGridTable3').hide();
		$('#centerActivityeBody3').empty();
    	}
});	
		
		
$('#ugcSelect').change(function(){ 
    var value = $(this).val();
    if(value=="yes")
    	{
    	$.ajax({
    		"url":"/academicfinance/getAllactiviticenters",
    		"method":"GET",
    		data:{"activityId":activityId},
    		 success:function(data)
    		 {
    			try
    		{
    				
    				if(data.length>0)
    				{	
    				$('#divGridTable4').show();
    				$('#centerActivityeBody4').empty();
    			  						
    					for(var i=0;i<data.length;i++)
    					{
    						
    						//alert(data[i]);
    						console.log(data[i]["actvtyCenterKey"]["cm"]["centre_name"]);
    					
                            var tr=$('<tr><td>'+data[i]["actvtyCenterKey"]["cm"]["centre_name"]+'</td><td><input class="centerHeads form-control" id="ugcFund" type="number" name="ugcFund"></input></td></tr>');
    						$('#centerActivityeBody4').append(tr);
    					}
    				}
    				else
    					{
    					showMessage("no centres  heads are found under the selected group");
    					$('#centerActivityeBody4').empty();
    					}
    			}
    			catch(e)
    			{
    				showError("Error in processing information.contact Admin");
    			}	 
    		 },
    	 error:function(e)
    {
    	showMessage("Error in adding resource person. Contact Admin");
    }
    	
    });
    	}
    if(value=="no")
	{
    	$('#divGridTable4').hide();
		$('#centerActivityeBody4').empty();
	}
});	
		
			
});
</script>
<div class="card card-info card-outline">
	<div class="card-header">
		<h3>Academic Approval Details</h3>
	</div>
	<div class="card-body">
		<form id="activityMasterDetailsForm">
			<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
             <div class="form-group" style="width: 100%">
					<label for="activityType">Activity Type<span class="required">*</span></label>
					<div style="width: 100%">
						<div style="display: inline-block; width: 100%">
							<select class="form-control selectpicker" name="aat"
								id="activityTypeCodeId" required>

							</select>
						</div>
					</div>
				</div>
			<div class="form-group">

					<label for="activitytype"> Name/Title of the Program</label>
					<input type="text" name="title" class="form-control"
						id="titleId" required />
				</div>	
				<div class="row">
					<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
					<label for="date from">Date From</label> <input
						type="text" id="dateFromId" name="dateFrom"
						class="form-control dp" required></input>
					</div>
		<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
		<label for="date to">Date To</label> <input
						type="text" id="dateToId" name="dateTo"
						class="form-control dp" required></input>
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
					<label for="activity type"> Centres involved in the Program<label> 

				</div>
				
				
				
				<div class="form-group">

					<label for="brochure"> Brochure</label>
					<input type="text" name="brochureURL" class="form-control"
						id="brochureURLId" required />
				</div>
		
		        <div class="form-group">
		
					<label for="description"> A brief description of the Objects of the Programme</label>
				<textarea id="descriptionId" name="description" rows="4" cols="5" class="form-control"></textarea>
				</div>		
				<div class="form-group">				
	          <label for="targetgroup"> Content,Relevance And Target Group</label>
				<textarea id="targetGroupId" name="targetGroup" rows="4" cols="50" class="form-control"></textarea>
				</div>	

	<div class="form-group">	
	  <label for="Outcome"> Expected Outcome and Quantified Deliverables</label>			
	  <textarea id="outcomeId" name="Outcome" rows="4" cols="50" class="form-control" required></textarea> </div>
	  
	  <p>
 <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalLong">
Add Resource Person 
</button>





  <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample" id="resourcePersonviewbtId">
    View Resource Person Details
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
	  <label for="deviationJustification"> Observed deviations from University rules , regulations and Govt Orders and justifications	</label>			
	  <textarea id="deviationJustificationId" name="deviationJustification" rows="4" cols="50"  class="form-control"required></textarea> 
	</div>


<div class="form-group">
<label for="type">type<span class="required">*</span></label>
					<div style="width: 100%">
						<div style="display: inline-block; width: 100%">
							<select class="form-control selectpicker" name="type"
								id="typeId" required>
  <option value="se">Select</option>
  <option value="internal">Internal</option>
  <option value="external">External</option>

</select>
</div>
</div>
</div>

<div class="form-group">
<label for="activityLevel">Activity Level<span class="required">*</span></label>
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
					  <label for="financeImpliedId">Fiancial Implication Include<span class="required">*</span></label>
					  	<label class="radio-inline">
					      <input type="radio"  id="yesId" checked>YES
					    </label>
					    <label class="radio-inline">
					      <input type="radio"  id="noId">NO
					    </label>
					</div>

	<div class="form-group">				
	          <label for="remarks"> Remarks</label>
				<textarea id="remarksId" name="remarks" rows="4" cols="50" class="form-control"></textarea>
				</div>	
				<div class="form-group">
					<label>Enter Date</label> <input tydespe="text" id="submitDate"
						name="enterDate" class="form-control dp" required></input>
				</div>
				
				<div class="form-group" style="text-align: center">
					<button type="submit" class="btn btn-primary">SUBMIT</button>
				</div>
				
			 <button type="button" id="centerActivity" class="btn btn-primary" data-toggle="modal" data-target="#dialogCenterActivityEdit">
Add Center Activity Finance
</button>

			</div>

		</form>

	</div>
</div>

<br />
<div id="gridRow" class="card card-info card-outline">
	<div class="card-header">
		<h3>List Of Academic Approval Details</h3>

	</div>


	<div class="card-body">
		<div class="table-responsive">
			<table
				class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border"
				id="expHeadTable">

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

<!-- Modal Resource Person-->
<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Add Resource Person Details</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
       
       <form id="ResourcePersonDetailsForm">
       
       <div class="row">

		<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
		<div class="form-group">

					<label for="activitytype"> Name</label>
					<input type="text" name="name" class="form-control"
						id="nameId" required />
				</div>	
				
				<div class="form-group">

					<label for="activitytype"> Designation</label>
					<input type="text" name="designation" class="form-control"
						id="designationId" required />
				</div>
				
				
				<div class="form-group">

					<label for="activitytype"> Organisation</label>
					<input type="text" name="organisation" class="form-control"
						id="organisationId" required />
				</div>
				<div class="form-group">

					<label for="activitytype"> Domain</label>
					<input type="text" name="domain" class="form-control"
						id="domainId" required />
				</div>
					<div class="form-group">

					<label for="activitytype"> Category</label>
					<input type="text" name="category" class="form-control"
						id="categoryId" required />
				</div>
				
						<div class="form-group">

					<label for="activitytype"> Address</label>
					
					 <textarea  class="form-control" rows="4" cols="10"
						id="addressId" name="address" required></textarea>
					
				</div>
				
					<div class="form-group">

					<label for="activitytype"> ContactName</label>
					<input type="text" name="contactName" class="form-control"
						id="contactName" required />
				</div>
					<div class="form-group">
					<label for="activitytype"> ContactPhone</label>
					<input type="text" name=contactPhone class="form-control"
						id="contactPhoneId" required />
				</div>
				
		
		</div>
		
		
		<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
		<div class="form-group">
					<label for="activitytype"> Email</label>
					<input type="text" name=email class="form-control"
						id="emailId" required />
				</div>
				<div class="form-group">
					<label for="activitytype"> PanNo</label>
					<input type="text" name=panNo class="form-control"
						id="panNoId" required />
				</div>
					<div class="form-group">
					<label for="activitytype"> AccountNo</label>
					<input type="text" name=accountNo class="form-control"
						id="accountNoId" required />
				</div>
				<div class="form-group">
					<label for="activitytype"> BankName</label>
					<input type="text" name=bankName class="form-control"
						id="bankNameId" required />
				</div>
				<div class="form-group">
					<label for="activitytype"> Branch</label>
					<input type="text" name=branch class="form-control"
						id="branchId" required />
				</div>
				
				<div class="form-group">
					<label for="activitytype"> AccountType</label>
					<input type="text" name=accountType class="form-control"
						id="accountTypeId" required />
				</div>
				<div class="form-group">
					<label for="activitytype"> IFSC Code</label>
					<input type="text" name=ifsc class="form-control"
						id="ifscId" required />
				</div>
		
		</div>
    
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal" >Close</button>
        <button type="submit" class="btn btn-primary" id="resourceButtonAdd">Submit</button>
      </div>
          </div>
           </form>
    </div>
  </div>
</div>
</div>

<!-- Modal Center Activity Details-->
  <div class="modal fade" id="dialogCenterActivityEdit" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-xl" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Fill the below details for Finance approval</h5>
        		 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                   <span aria-hidden="true">&times;</span>
                 </button>
	   </div>   
	     <div class="modal-body">
        	<form id="formCenterActivityDetails">
			 <div class="form-group">
		         <div id="divGridTable2" class="row" style="display: none">
	                    <div class="col-md-12">
		                <div class="table-responsive cell-border">
			              <table id="gridTableMapper" class="table table-striped">
			                     <thead>
			                         <tr>
			                           <th scope="col">Center</th>
			                           <th scope="col">Fund Allocated to the Centre for the Current Financial Year
                                       </th>
                                      <th scope="col">Expenditure incurred (Including firm committments made) till date

</th>
<th scope="col">Balance Available excluding Unassigned Grant</th>
<th scope="col">Estimated expenditure for this Programme</th>
<th scope="col">National Seminars Organised till date ( Including this)
<th>Extension Lectures/ similar programmes  Organised till date ( Including this)
</th>
</tr>
			  </thead>
			  <tbody id="centerActivityeBody2">
			  
			  </tbody>
			</table>
		</div>	
	</div>
</div>
</div>
  	
  
<div class="form-group row">
	 <label for="comment"  class="col-sm-6 col-form-label">Is their any external funding for the programme ?
     </label>
      <div class="col-sm-6">
 	 <select class="form-control" id="externalfundSelect">
 	  <option value="se">Select</option>
    <option value="yes">Yes</option>
    <option value="no">No</option>
  </select>
  </div>
</div>

	<div id="divGridTable3" class="row" style="display: none">
	                    <div class="col-md-12">
		                <div class="table-responsive cell-border">
			              <table id="gridTableMapper" class="table table-striped">
			                     <thead>
			                         <tr>
			                           <th scope="col">Center</th>
			                           <th scope="col">Proposed Amount of External Funding</th>

                
                                      <tr>
			  </thead>
			  <tbody id="centerActivityeBody3">
			  
			  </tbody>
			</table>
		</div>	
	</div>
</div>

  
<div class="form-group row">
	 <label for="comment"  class="col-sm-6 col-form-label">Is this programme funded by UGC ?</label>
	  <div class="col-sm-6">
 	 <select class="form-control" id="ugcSelect">
 	  <option value="se">Select</option>
    <option value="yes">Yes</option>
    <option value="no">No</option>
  </select>
  </div>
</div>

	<div id="divGridTable4" class="row" style="display: none">
	                    <div class="col-md-12">
		                <div class="table-responsive cell-border">
			              <table id="gridTableMapper" class="table table-striped">
			                     <thead>
			                         <tr>
			                           <th scope="col"></th>
			                           <th scope="col">Amount of External Funding</th>

                
                                      </tr>
			  </thead>
			  <tbody id="centerActivityeBody4">
			  
			  </tbody>
			</table>
		</div>	
	</div>
</div>

			<div class="form-group row">
					<label for="activitytype" class="col-sm-6 col-form-label"> Are such private funding proposals approved by VC/EC
					private fund</label>
					 <div class="col-sm-6">
					 <select class="form-control" id="privateFund">
					 <option value="se">Select</option>
    <option value="yes">Yes</option>
    <option value="no">No</option>
  </select>
</div>
</div>



<div class="form-group row">
    <label for="privateFundJustification" class="col-sm-6 col-form-label">If, No, Is  necessary justification provided as Anexure I ( Academic Approval)</label>
    <div class="col-sm-6">
      <input type="text" name="annexureFile" class="form-control" id="annexureFileId" required>
    </div>
  </div>


<div id="divGridTable7" class="row" >
	                    <div class="col-md-12">
		                <div class="table-responsive cell-border">
			              <table id="gridTableMapper" class="table table-striped">
			                     <thead>
			                         <tr>
			                         <th></th>
			                           <th scope="col">University</th>
			                           <th scope="col">Local</th>
                                      <th scope="col">Outstation</th>
                                      <th scope="col">InterNational</th>
                                     </tr>
			  </thead>
			  <tbody id="BeneficairyResourcePersonCount">
			  <tr><td>No of Beneficiaries</td><td><input type="text" id="UnivBeneficiariescountId" name="UnivBeneficiaries"></input></td><td><input type="text" id="localBeneficiariesId" name="localBeneficiaries"></input></td><td><input type="text" id="outstnBeneficiariesId" name="outstnBeneficiaries"></input></td><td><input type="text" id="intNatBeneficiariesId" name="intNatBeneficiaries"></input></td></tr>
			  <tr><td>Resource Person for the Program</td><td><input type="text" id="UnivResPersoncountId" name="UnivResPerson"></input></td><td><input type="text" id="localResPersonId" name="localResPerson"></input></td><td><input type="text" id="outstnResPersonId" name="outstnResPerson"></input></td><td><input type="text" id="intNatResPersonId" name="intNatResPerson"></input></td></tr>
			  </tbody>
			</table>
		</div>	
	</div>
</div>
<h5 style="text-decoration: underline;">Proposesd expenses for travelling and incidental expenses </h5>
</h5>
<div id="divGridTable7" class="row" >
	                    <div class="col-md-12">
		                <div class="table-responsive cell-border">
			              <table id="gridTableMapper" class="table table-striped">
			                     <thead>
			                         <tr>
			                         <th></th>
			                           <th scope="col">University</th>
			                           <th scope="col">Local</th>
                                      <th scope="col">Outstation</th>
                                      <th scope="col">InterNational</th>
                                     </tr>
			  </thead>
			  <tbody id="BeneficairyResourcePersonCount">
			  <tr><td>For Beneficiaries</td><td><input type="text" id="UnivBenTravelId" name="UnivBenTravel"></input></td><td><input type="text" id="localBenTravelId" name="localBenTravel"></input></td><td><input type="text" id="outstnBenTravelId" name="outstnBenTravel"></input></td><td><input type="text" id="intNatBenTravelId" name="intNatBenTravel"></input></td></tr>
			  <tr><td>For Resource persons for the Programme
</td><td><input type="text" id="UnivResTraveltId" name="UnivResTravel"></input></td><td><input type="text" id="localResTravelId" name="localResTravel"></input></td><td><input type="text" id="outstnResTravelId" name="outstnResTravel"></input></td><td><input type="text" id="intNatResTravelId" name="intNatResTravel"></input></td></tr>
			  <tr><td>Number of Air Travel for resource persons
</td><td><input type="text" id="AirTravelResUnivId" name="AirTravelResUniv"></input></td><td><input type="text" id="AirTravelResLocalId" name="AirTravelResLocal"></input></td><td><input type="text" id="AirTravelResOutstnId" name="AirTravelResOutstn"></input></td><td><input type="text" id="AirTravelResIntnlId" name="AirTravelResIntnl"></input></td></tr>
			  </tbody>
			</table>
		</div>	
	</div>
</div>

			<div class="form-group row">
					<label for="activitytype" class="col-sm-6 col-form-label">Does the programme envisage payment of Honorarium of more than Rs3000/- per day ?
                    </label>
					 <div class="col-sm-6">
			            			 <select class="form-control" id="HonorMore3000" name="HonorMore3000">
					                     <option value="se">Select</option>
                                         <option value="yes">Yes</option>
                                         <option value="no">No</option>
                                       </select>
                     </div>
          </div>
        
           	<div class="form-group row">
					<label for="activitytype" class="col-sm-6 col-form-label">Total Estimated Expenditure for the Programme

                    </label>
					 <div class="col-sm-6">
			            		 <input type="text" name="totalEstExp" class="form-control" id="totalEstExpId" required>	 
                     </div>
          </div>

			<hr>
			
				<div id="expenditureGrid" class="row" style="display: none">
	                    <div class="col-md-12">
		                <div class="table-responsive cell-border">
			              <table id="gridTableMapper" class="table table-striped">
			                     <thead>
			                         <tr>
			                           <th scope="col">SI No</th>
			                            <th scope="col">Head Of Expenditure</th>
			                           <th scope="col">Allocation as per NUALS Guidelines</th>
<th scope="col">Estimated Expenditure</th>
 <th scope="col"> Deviation</th>
 <th scope="col">Comments</th>               
   </tr>
			  </thead>
			  <tbody id="expenditureGridBody">
			  
			  </tbody>
			</table>
		</div>	
	</div>
</div>
<h5 style="text-decoration: underline;">Proposesd expenses for travelling and incidental expenses </h5>
<hr>
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
                                      </tr>
			  </thead>
			  <tbody id="advnceRequestGridBody">
			  
			  </tbody>
			</table>
		</div>	
	</div>
</div>		


				<button type="submit" class="btn btn-primary">Submit</button>
			
			</form>
      </div>
      <div class="modal-footer">

      </div>
    </div>
  </div>
</div>