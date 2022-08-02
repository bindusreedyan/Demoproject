<link rel="stylesheet" href="./sfs_public/css/jquery-confirm.min.css">
<script src="./sfs_public/js/jquery-confirm.min.js"></script>
<script>
var centerarray=[];
var centerNameArray=[];
var exteralFundAmount=0;
var  activityId;
var budgetlength=0;
var orderGenId;
var finyear;
var exheadarray=[];
var budgetInvolved;
var settlementId;
var activityBudgetFundId=0;
var map={};
var budstring="";
function reportPrinting(divId)
{
	 //  console.log(divId);
	   $('#reportDiv').show();
    var divToPrint = document.getElementById(divId);
    var htmlToPrint = '' +
        '<style type="text/css">' +
        'table th, table td {' +
     'border:0px solid #000;' +
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
function loadAllEsGenerationDetails() {
	$.ajax({
		"url" : "/academicactivity/loadAllEsGeneration",
		"method" : "GET",
		success : function(data) {
			setUpDataTable(data,[ 
				{
					"orderGenId":"Order Generation Id"
				},
				{
					"setId":"Settlement Id"
				},
				
				{
					"activityCode":"Activity Code"	
				},
				
				{
					"title":"Activity Name"
				},
				{
					"estimExp":"Estimated Expenditure"
				},
				{
					"sanctionedAmount":"Sanctioned Amount"
				},
				{
					"esNumberPrefix":"Es Number Prefix"
				}
				
				 ],"asGeneratedActivitiesTable","select-checkbox");
		},
		error : function(e)
		{
		}
		});
	
}
function loadAllAcademicActivityTypeDetails() {
	$.ajax({
		"url" : "/academicactivity/loadAllExpenditureDetailsSanctioned",
		"method" : "GET",
		success : function(data) {
			
			//console.log(data['asNO']);
			
		   setUpDataTable(data, [ 
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
				"setId":"SettlementId"	
			},
			{
				"status" : "Status"
			}
		  ], "finallySettledActivityTable", "select-checkbox");
			onDataTableClick('finallySettledActivityTable', function() {
				$('html, body').animate({ scrollTop: $('#asGenerationForm').offset().top }, 'slow');
				$('#titleId').val(selectedRowFromDataTable[2]);
				activityId=selectedRowFromDataTable[1];
				finyear=selectedRowFromDataTable[3];
				settlementId=selectedRowFromDataTable[5];
				//alert(settlementId);
						$.ajax({
								"url" : "/academicactivity/getAllActivityById",
								"method" : "GET",						
								data : {"activityId":activityId},
								success : function(data) 
								{
									console.log("daaataaaaaaaaaa in getAllActivityById1"+data);
									finyear=data['finyear'];
								
									var data_splits1 =data['dateFrom'].split("T");
									var data_splits2 =data['dateTo'].split("T");
									$('#dateFromId').val(data_splits1[0]);
									$('#dateToId').val(data_splits2[0]);
									
								},
								error : function(e)
								{
								}
							});
				$.ajax({
			   		"url":"/academicfinance/getAllactiviticenters",
			   		"method":"GET",
			   		data:{"activityId":activityId},
			   		success : function(data) {
			   			centerlength=data.length;
			   			exteralFundAmount=0;
			   			//console.log(data);
			   			var i;
			   			console.log("daaataaaaaaaaaa in getAllActivityById2"+data);
			   		 $('#centerdiv').empty();
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
			                
			                exteralFundAmount=data[i]['externalFund']+exteralFundAmount;
			   			 }
			   			$('#externlFundAmntId').val(exteralFundAmount);
			   			
			   			
			   		},
					error : function(e)
					{
					}});
				$.ajax({
					"url":"/academicfinance/getActivityFinance",
					"method":"GET",
					data:{"activityId":activityId},
					success:function(data)
						{
						console.log(data);
						$('#EstExpId').val(data['totalAsgranted']);
						//alert(data['externalFunding']);
						$('#externlFundPrtId').val(data['externalFunding']);
						
						if(data['externalFunding']=='y')
							{
							
							$('#advReleasedId').attr('disabled',false);
							}
						if(data['externalFunding']=='n')
						{
						
						$('#advReleasedId').attr('disabled',true);
						}
				
					
						},
						error: function(e)
						{
							console.log(e);
						}					
			            }); 
				
				  /* $.ajax({
			    	   "url":"/expenditurehead/advanceReleasedDetailsPerActivity",
						"method":"GET",
						data:{"activityId":activityId},
					    success:function(data)
						{
					    	
					    	totalAdvReq=0;
						for(var i=0;i<data.length;i++)
							{
							totalAdvReq=totalAdvReq+data[i]['asGranted'];
							}
						$('#advReleasedId').val(totalAdvReq);
						},
						error: function(e)
						{
							console.log(e);
						}					
			            }); */
			            
			            
			            $.ajax({
					   		"url" : "/academicactivity/getAllAdvanceRecieved",
					   		"method" : "GET",
					   		data:{"activityId":activityId},
					   		success : function(data) {
					   		//	var advanceRecievedlength=data.length;
					   			
					   			//if(advanceRecievedlength==0)
					   			//	{
					   			//	$('#advanceObtainedId').val("no");
					   			///	}
					   			//if(advanceRecievedlength>0)
					   			//	{
					   				//$('#advanceObtainedId').val("yes");
					   				//}
					   			
					   			//$('#advnceRequestGridBody').empty();
					   			
					   			//var totalAdvancePaid=0;
					   			//totalAdvancePaid1=0;
					   			
					   			var totalAdvancePaid=0;
								for(var i=0;i<data.length;i++)
								{
									
									var sino=i+1;

									// tr=$('<tr id="advancerows"><td>'+sino+'</td><td>'+data[i]['purpose']+'</td><td>'+centerArrayString+'</td><td>'+data[i]['voucharNo']+'</td><td>'+data[i]['voucharDate']+'</td><td>'+data[i]['advancePaid']+'</td><td>'+data[i]['settledAmount']+'</td></tr>');
									// $('#advnceRequestGridBody').append(tr);
									 totalAdvancePaid=parseFloat(data[i]['advancePaid'])+parseFloat(totalAdvancePaid);
									 //totalAdvanceSettled=parseFloat(data[i]['settledAmount'])+parseFloat(totalAdvanceSettled);
								}  
								
								//totalAdvancePaid1=totalAdvancePaid;
								$('#advReleasedId').val(totalAdvancePaid);
					   			
					   		},
					   		error : function(e) {
					   			//showMessage("Error in Getting Programs. Contact Admin");
					   		}
					   	});
			            
			        	$.ajax({
			        		"url" : "/academicactivity/getActivityBudHeadDetails",
			        		"method" : "GET",
			        		data :{"activityId":activityId},
			        		success : function(data) {
			        			console.log(data);
			        			setUpDropDown("budHead",data,"bud_head_id","bud_head_name");
			        		},
			        		error : function(e) {
			        			//showMessage("Error in Getting Programs. Contact Admin");
			        		}
			        	});
			    
					$.ajax({
				   		"url" : "/academicactivity/loadAllExpenditureDetailsSubmittedBySettlementId",
				   		"method" : "GET",
				   		data:{"settlementId":settlementId},
				   		success : function(data) {
				   			console.log("sanctiondedamnt"+data['balAmountTobePaid']);
				   			
				   			$('#sanctionedAmountId').val(data['balAmountTobePaid']);
				   			
				   		}
				   		,
						error: function(e)
						{
							console.log(e);
						}					
			            }); 
					
					
					
					$.ajax({
				   		"url" : "/academicactivity/loadAllAmountPaidBackBySettlementId",
				   		"method" : "GET",
				   		data:{"settlementId":settlementId},
				   		success : function(data) {
                         
                       
                          
                          var totalPaidBack=0;
               				for(var i=0;i<data.length;i++)
							{									 totalPaidBack=parseFloat(data[i]['amountPaidBack'])+parseFloat(totalPaidBack);
							}  
               				
               				     
               				$('#totalPaidBackId').val(totalPaidBack);
               				
               				
				   		},
				   		error:function(e)
				   		{
				   			
				   		}
				   		});
					
					
					$.ajax({
				   		"url" : "/academicactivity/loadAllExpenditureBySettlementId",
				   		"method" : "GET",
				   		data:{"settlementId":settlementId},
				   		success : function(data) {
				   			
				   			var totalExpAmt=0;
				   			
				   			for(var i=0;i<data.length;i++)
				   				{
				   				totalExpAmt=parseFloat(data[i]['totalAmt'])+parseFloat(totalExpAmt);		
				   				
								
								}
								$('#totalExpenditureAmntId').val(totalExpAmt);
						},
							error:function(e)
								   {
								   			
								   	}
							});
					
					
				
			});
		
		},
		error: function(e)
		{
			console.log(e);
		}	
		});


}

$(document).ready(function()
		{
	loadAllAcademicActivityTypeDetails();
	loadAllEsGenerationDetails();
/*	$.ajax({
		"url" : "/expenditurehead/budHeadId",
		"method" : "GET",
		success : function(data) {
			console.log(data);
			setUpDropDown("budHead",data,"budHeadId","budHeadName");
		},
		error : function(e) {
			//showMessage("Error in Getting Programs. Contact Admin");
		}
	});*/
	$.ajax({
   		"url" : "/expenditurehead/getAllBudgetHeadDetails",
   		"method" : "GET",
   		success : function(data) {
   			
   			
   			budgetlength=data.length;
   			var i;
   			for( i=0;i<data.length;i++){
   			
   			var num=i+1;
   			var num1="bud";
   			num1="bud"+num.toString();
   			//checbxname="center"+num1;
   			 $('#budgetdiv')
   			    .append('<label class="checkbox-inline">')
                .append('<input type="checkbox" id='+num1+' name='+num1+' value='+data[i]['budHeadName']+'>'+data[i]['budHeadName'])
                .append('</label>')
                
   			 }
   		},
   		error : function(e) {
   			//showMessage("Error in Getting Programs. Contact Admin");
   		}
   	});
	
	$('#asGenerationForm').unbind().bind('submit',function(event)
			{
		           event.preventDefault();
		       	var formData = $(this).serializeArray();
		       	formData.push({"name":"ac","value":activityId});
		     	formData.push({"name":"ae","value":settlementId});
		       	budgetInvolved="";
				for(i=0;i<budgetlength;i++)
					{
					
					//checbxid="bud"+i;
					var num=i+1;
					var num1="bud"+num.toString();
					//checbxname="center"+num1;			
					if($("#"+num1).prop('checked') == true){
						
						//alert($("#"+num1).val());
						
						budgetInvolved=budgetInvolved+$("#"+num1).val();
					}
					}
				
				
				formData.push({"name":"budgetInvolved","value":budgetInvolved});
				
				$('#submitModel').modal('show');
		    	

				$('#confId').unbind().bind('click',function(){

					console.log("confirm clicked...");

					$.ajax({
						"url" : "/academicactivity/esOrderGeneration",
						"method" : "POST",
						 data : formData,
						 success : function(data) {
							console.log(data);
							if(data==null)
								{
								showMessage("Error in As order Generation")
								}
							else
								{
								showMessage("Es order is Successfully generated with Order No"+data['esNumberPrefix']);
								orderGenId=data['orderGenId'];
								 $("#printBt"). attr("disabled", false);
								 loadAllAcademicActivityTypeDetails();
								}
							
					   		},
						 error:function(e)
						 {
							 console.log(e);
						 }
						});
$('#submitModel').modal('hide');
					
				});
				
				$('#cancelId').unbind().bind('click',function(){

					console.log("Cancel clicked...");

					$('#submitModel').modal('hide');
					
				});
						
						 
		       	
			});
	
	
	 $('#budHead').on('change', function() {
		  alert("budhead details");
			var budheadId=$('#budHead').val();
		 
			$.ajax({
					"url" : "/academicactivity/getBudgetHeadAmountForAnactivity",
					"method" : "GET",
					 data : {"budheadId":budheadId,"finyear":finyear,"activityId":activityId},
					 success : function(data) {
						 
						 $('#fundAmountId').val(data['asAmount']);
						 activityBudgetFundId=data['activityBudgetFundId'];
						 
						 
					 },
					 error:function(e)
				 {
					 console.log(e);
				 }
				});
   			
		  
		  
	  });
	
	  $('#budgetFundDetailsForm').unbind().bind('submit',function(event) 
			   {
			   	        console.log("entrrr in budgetFundDetailsForm");
			   			event.preventDefault();
			   			var budheadId=$('#budHead').val();
			   			
			   		/* 	$.ajax({
	   						"url" : "/academicactivity/getBudgetFundAmount",
	   						"method" : "POST",
	   						 data : {"budheadId":budheadId,"finyear":finyear},
	   						 success : function(data) {
	   							 
	   							 
	   							 
	   							 
	   						 },
	   						 error:function(e)
							 {
								 console.log(e);
							 }
							}); */
			   			
			   			   var amountTobemapped=$('#esAmountId').val();
							
							 var fundAmount=$('#fundAmountId').val();
							 
							 if(amountTobemapped>fundAmount)
								 {
								 showMessage("cannot map due to insufficient fund");
								 }
							 else
								 {
			   			    var formData = $(this).serializeArray();
			   			    formData.push({"name":"ac","value":activityId});
				   			formData.push({"name":"finYear","value":finyear});
				   			formData.push({"name":"activityBudgetFundId","value":activityBudgetFundId});
				   			
				   			
				 		   // formData.push({"name":"budHeadId","value":budheadId});
				 		 //   formData.push({"name":"esAmount","value":amountTobemapped});
			   			var formCheck = "success";

			   				$('#submitModel').modal('show');

			   				$('#confId').unbind().bind('click',function(){

			   					console.log("confirm clicked...");
			   					
			   							   			
			   					$.ajax({
			   						"url" : "/academicactivity/updateActivityBudgetFund",
			   						"method" : "POST",
			   						 data : formData,
			   						 success : function(data) {

			   								var data_splits = data.split("-");
			   								
			   								if(data_splits[0] == "SAVED")
			   								{
			   									var msg = data_splits[1];
			   									centreDialog("Success",msg+"is successfully added","green");
			   									var headdname=$("#budHead option:selected").text();
			   									budstring=budstring+headdname+"-"+amountTobemapped+"<br>";
			   								
			   								}
			   								else
			   								{
			   									var msg = data_splits[1];
			   									centreDialog("Error",msg,"red");
			   								
			   								}

			   								$("#budgetFundDetailsForm").trigger('reset');
			   								//clearDataTable("activityTypeTable");
			   								loadCenterFundDetails();
			   							}
			   						
			   							
			   						});

			   					$('#submitModel').modal('hide');
			   					
			   				});
			   				
			   				$('#cancelId').unbind().bind('click',function(){

			   					console.log("Cancel clicked...");

			   					$('#submitModel').modal('hide');
			   					
			   				});
			   				
			   }
			   });
	  
	  
	  
	  
	  	
	$('#printBt').unbind().bind('click',function()
		{
	
	   $.ajax({
                "url" : "/academicactivity/getEsOrderGenerationInformation",
                "method" : "GET",
                "data" : {"orderGenId":orderGenId},
      			success:function(d)
					{
      				//$('#rollno').append(d['rollno']);
      				for(var key in d)
					{
      					
      					$('#'+key).empty();
					//	$('#'+key).val(d[key]);
						$('#'+key).append(d[key]);
					}
      				 $.ajax({
 						 "url" : "/academicactivity/loadAllCompliances",
 		   		         "method" : "GET",
 		   		           data:{"settlementId":settlementId},
 						success:function(data)
 							{
 							 $('#exptab').append('<thead><tr><th>SL No</th><th scope="col">Head</th><th scope="col">Expenditure Incurred</th><th>Anticipated Expenditure</th></tr></thead>');
 							for(var i=0;i<data.length;i++)
 								{
 								
 							var sino=i+1; 
 						    var des=data[i]['expHead'];
  				            var asGrantedAdvanceAmnt=data[i]['asGranted'];
  				            var actualAmnt=data[i]['actual'];
  						
                  var	tr=$('<tr><td>'+sino+'</td><td>'+des+'</td><td>'+asGrantedAdvanceAmnt+'</td><td>'
                  +actualAmnt+'</td><tr>');
  								 $('#exptab').append(tr);
  								 }
 							
 							$.ajax({
						   		"url" : "/academicactivity/loadAllExpenditureBySettlementIdByInteface",
						   		"method" : "GET",
						   		data:{"settlementId":settlementId},
								success:function(data1)
     							{
     							 $('#expendtab').append('<thead><tr><th>SL No</th><th scope="col">Name</th><th scope="col">Invoice Number</th><th>Head </th><th>Amount</th><th>Status</th></tr>');
								 
								 for(var i=0;i<data1.length;data1++)
								 {
								 	var sino=i+1; 
 						    var name=data1[i]['payee'];
  				            var billno=data1[i]['billno'];
  				            var head=data1[i]['headname'];
							var amount=data1[i]['advtotal'];
							var status=data1[i]['modepayment'];
  						
                  var	tr=$('<tr><td>'+sino+'</td><td>'+name+'</td><td>'+billno+'</td><td>'
                  +head+'</td><td>'+amount+'</td><td>'+status+'</td><tr>');
  								 $('#expendtab').append(tr);
								 
								 }
								 budheadcontent.append(budstring) ;
	 			      				
 			      				 reportPrinting('reportDiv');
								},
     				error:function(e)
     				{
     				
     				}
     			
     		});
 							
 							
 							
 							
 							
 							
 							
 								
 							},   							
 							error:function(e)
 							{
 							
 							}
 						
 					});
      				
      				
      				
      				
      				
      				
					}
				});
	   
   
	  
		});
	
	});
</script>
<div id="gridRow" class="card card-info card-outline">
	<div class="card-header">
		<h3>List Of Activities For Es Generation</h3>

	</div>
	<div class="card-body">
		<div class="table-responsive">
			<table
				class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border"
				id="finallySettledActivityTable">

			</table>
		</div>
	</div>
</div>

<div  id="asGeneration" class="card card-info card-outline">
   <div class="card-header">
      <h3><center>Expenditure Sanction</center></h3>
   </div>
   <div class="card-body" id="asGenerationFormId">
     <div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
             <form id="asGenerationForm">
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
            <div class="row">
               <div class="form-group" id="centerdiv">
               <label for="activity type"> Centres involved in the Program
               </label>
              </div>
              </div>
               <div class="row">
               <div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
               <div class="form-group">
               <label for="activitytype"> Estimated Expenditure</label> <input type="text"
                     id="EstExpId" name="estimExp" class="form-control" required></input>
               </div> 
                <div class="form-group">
                <label for="sectionbHeadingId">Section B Heading</label> 
                  <input type="text" name="sectionbHeading" class="form-control" id="sectionbHeadingId"
                  required />
                </div>
                 <div class="form-group">
                <label for="activitytype">EsNumber Prefix</label> 
                   <input type="text" name="esNumberPrefix" class="form-control" id="esNumberPrefixId"
                  required />
               </div>
              <div class="form-group">
                <label for="activitytype">External Fund Amount</label> 
                <input type="text" name="externlFundAmnt" class="form-control" id="externlFundAmntId" />
               </div>
               
               <div class="form-group">
                <label for="activitytype">University Paid Back amount</label> 
                <input type="text" name="totalPaidBack" class="form-control" id="totalPaidBackId" />
               </div>
               
                <div class="form-group">
                <label for="activitytype">Total Expenditure Amount</label> 
                <input type="text" name="totalExpenditureAmnt" class="form-control" id="totalExpenditureAmntId" />
               </div>
               
               </div>
 
               <div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
               
               <div class="form-group">
                <label for="activitytype">Advance Released</label> 
                <input type="text" name="advReleased" class="form-control" id="advReleasedId"
                  required />
               </div>
                     <div class="form-group">
                 <label for="externlFunding">External Funding</label> 
                  <select class="form-control" name="externlFunding"
								id="externlFundPrtId">
								<option value="">Select</option>
								<option value="y">Yes</option>
								<option value="n">No</option>
							</select>
               </div>
               <div class="form-group">
                <label for="activitytype">Enternal Fund Type</label> 
                <input type="text" name="externlFundType" class="form-control" id="externlFundTypeId" />
               </div>
               
                 <div class="form-group">
                 <label for="activitytype">Sanctioned Amount</label> 
                 <input type="text" name="sanctionedAmount" class="form-control" id="sanctionedAmountId" />
                </div>
        
               </div>
            </div>
               <div class="row">
                <div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
               <div class="form-group">
                <label for="activitytype">Astract Details</label> 
                   <textarea id="abstractDetailsId" name="abstractDetails" rows="4" cols="50"
                  class="form-control" required></textarea>
               </div>
                <div class="form-group">
                <label for="activitytype"> Section B Details</label> 
                   <textarea id="referencesId" name="esrefer" rows="4" cols="50"
                  class="form-control" required></textarea>
               </div>
            
                  <div class="form-group">
                <label for="activitytype">Order Heading</label> 
                   <input type="text" name="orderHeading" class="form-control" id="orderHeadingId"
                  required />
               </div>
                  <div class="form-group">
                <label for="activitytype">esOrderContent Content</label> 
                   <textarea id="esOrderContentId" name="esOrderContent" rows="4" cols="50"
                  class="form-control" required></textarea>
               </div>
               
            <!--    <div class="form-group" id="budgetdiv">
               <label for="activity type"> Budget involved in the Program
               </label>
              </div> -->
              
               <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalLong">
                Add Activity Budget Mapping
               </button>
               </div>
               <div class="form-group">
                <label for="activitytype">Copy To</label> 
                   <textarea id="copyToId" name="copyTo" rows="4" cols="50"
                  class="form-control" required></textarea>
               </div>
              </div>
              <div class="row">
				<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
					<div class="form-group" style="text-align: center">
						<input type="submit" class="btn btn-primary" value="ADD"/>
     			</div>
     				
			</div>
			</div>

              </form>
              <div class="row">
					<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
     	   <div class="form-group" style="text-align: center">
              <button class="btn btn-primary" id="printBt" >Print</button>
              
           </div>
           </div>
          </div>
          </div>
   </div>
   </div>
   
   <div id="gridRow" class="card card-info card-outline">
	<div class="card-header">
		<h3>Es Sanctioned Activities</h3>

	</div>
	<div class="card-body">
		<div class="table-responsive">
			<table
				class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border"
				id="asGeneratedActivitiesTable">

			</table>
		</div>
	</div>
</div>
   
   <!-- Modal Budget Head-->
<div class="modal fade" id="exampleModalLong" tabindex="-1"
   role="dialog" aria-labelledby="exampleModalLongTitle"
   aria-hidden="true">
   <div class="modal-dialog" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLongTitle">Add Budget Amount Mapping for Activity
               
            </h5>
            <button type="button" class="close" data-dismiss="modal"
               aria-label="Close">
            <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="modal-body">
            <form id="budgetFundDetailsForm">
               <div class="row">
                  <div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
                     <div class="form-group" style="width:100%">
						<label for="centerName">Budget Head<span class="required">*</span></label>
						 		<div style="width:100%">
							 		<div style="display: inline-block;width:80%">
									<select class="form-control selectpicker"  name="budHeadId" id="budHead" required>																		  										  
																												
									</select>
									</div>
								
								</div>					
					</div>
					 	<div class="form-group" style="width:100%">
						<label for="centerfund">Budget Head Amount Allocated In As Sanction<span class="required">*</span></label>
						 		<div style="width:100%">
							 		<div style="display: inline-block;width:80%">
								<input type="number" name="fundAmount" class="form-control" id="fundAmountId" required/>
					</div>
								</div>					
					</div>
					
					 	<div class="form-group" style="width:100%">
						<label for="centerfund">Expenditure Sanction Amount Allocated<span class="required">*</span></label>
						 		<div style="width:100%">
							 		<div style="display: inline-block;width:80%">
								<input type="number" name="esAmount" class="form-control" id="esAmountId" required/>
					</div>
								</div>					
					</div>
					
					
						<div class="form-group" style="text-align:center">
						<button type="submit" class="btn btn-primary">SUBMIT</button>		
					</div>
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
<!-- Modal HTML End -->




<div class="row">
<div class="col-md-12">
<div class="table-responsive" id="reportDiv" style="display: none">
<table class="table" id="gridTable">
<tr><td colspan="2" style="text-align:center"><b>(Abstract)</b></td></tr>
<tr><td colspan="2" id="abstractDetails" style="text-align:justify"></td></tr>
<tr><td colspan="2" style="text-align:center" id="sectionbHeading"></td></tr>
<tr><td id="esNumberPrefix"></td><td id="orderGeneratedDate"></td></tr>
<tr><td id="esrefer"></td></tr>
<tr><td colspan="2" style="text-align:center"><u><b>ORDER</b></u></td></tr>
<tr><td colspan="2" id="esOrderContent"></td></tr>


<tr><td colspan="2" style="text-align:center"><u><b>Headwise expenditure incurred is as follows</b></u></td></tr>
<tr>
<td colspan="2">
  <tr><td colspan="2" style="text-align:center"><table id="exptab" class="table table-striped"></table></td></tr> 
</td>
</tr>
<tr><td colspan="2" style="text-align:center"><b>Budget Mapping Details</b></td></tr>
<tr><td colspan="2" style="text-align:justify" id="budheadcontent"></td></tr>
<tr><td></td><td>By order of the Vice-Chancellor</td></tr>
<tr><td></td><td></td></tr>
<tr><td></td><td></td></tr>
<tr><td></td><td>Registrar</td></tr>
<tr><td>Copy to</td><td></td></tr>
<tr><td rowspan="10" id="copyTo"></tr>
<tr><td colspan="2" style="text-align:center"><u><b>ANNEXURE</b></u></td></tr>
<tr>
<td colspan="2">
  <tr><td colspan="2" style="text-align:center"><table id="expendtab" class="table table-striped"></table></td></tr> 
</td>
</tr>


</table>
</div>
</div>
</div>
<!-- <div class="row">
<div class="col-md-4"></div>
<div class="col-md-4"><button class="btn btn-success form-control" id="printRep">Print</button></div>
<div class="col-md-4"></div>
</div> -->
