<link rel="stylesheet" href="./sfs_public/css/jquery-confirm.min.css">
<script src="./sfs_public/js/jquery-confirm.min.js"></script>
<script>
var particiaptionRequestId;
var userCodeArray=[];
var contactNumberArray=[];
var activityId;
var participateRequestId;	
var onWardJourneyAmountAdmit=0;
var accomAmountAdmit=0;
var returnJourneyAmountAdmit=0;
var accomAmountAdmit=0;
var admitTotal=0;
var activityId;
var finyear;
var centerarray=[];
var participationRequestId;
var centerNameArray=[];
var abstractDetails;
var sectionbHeading;
var asNumberPrefix;
var orderGeneratedDate;
var orderHeading;
var asOrderContent;
var orderRemaining;
var copyTo;
var budheadselect="";
function reportPrinting(divId)
{
	 //  console.log(divId);
	   $('#reportDiv').show();
    var divToPrint = document.getElementById(divId);
    var htmlToPrint = '' +
        '<style type="text/css">' +
        'table th, table td {' +
     'border:0 px solid #000;' +
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


function getAllAsOrderGenerationInformation() {
	console.log("getAllAsOrderGenerationInformation");
	$.ajax({
		"url" : "/academicactivity/getAllAsOrderGenerationInformation",
		"method" : "GET",
		success : function(data) {
			
			console.log(data);
			
			setUpDataTable(data, [ {
				"orderGenId" : "Order Id"
			}, 
			{
				"activityCode":"Activity Code"	
			},
			{
				"participationRequestId":"Participate Requuest Id"	
			},
			
			{
				"asNumberPrefix" : "As No"
			}
		  ], "participantActivityTable1", "select-checkbox");





		},
		error : function(e)
		{
	    }
	});
}

function loadParticipantRequestApproval() {
	console.log("loadParticipantRequestApproval");
	$.ajax({
		"url" : "/academicactivity/getAllParticipantRequestFinalApprovedForas",
		"method" : "GET",
		success : function(data) {
			
			console.log(data);
			
			setUpDataTable(data, [ {
				"participationRequestId" : "Particiaption Request Id"
			}, 
			{
				"activityCode":"Activity Code"	
			},
			
			{
				"title":"Activity Name"
			},
			
			{
				"requestStatus" : "Status"
			}
		  ], "participantActivityTable", "select-checkbox");

			
			onDataTableClick('participantActivityTable', function() {
			
				if(selectedRowFromDataTable != null)
				{
					$('#titleId').val(selectedRowFromDataTable[2]);
					activityId=selectedRowFromDataTable[2];
					particiaptionRequestId=selectedRowFromDataTable[1];
					
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
				   			for( i=0;i<data.length;i++){
				   			
				   			var num=i+1;
				   			var num1="center"+num.toString();
				   			checbxid=num1;
				   			centerarray[i]=data[i]['actvtyCenterKey']['cm']['centre_id'];
				   			centerNameArray[i]=data[i]['actvtyCenterKey']['cm']['centre_name'];
				   			console.log("iiiiiiiiiiii"+centerarray[i]);
				   		 $('#centerdiv').empty();
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
						"url":"/academicactivity/getParticiapntOtherExpensesDetailsByParticipantRequestId",
						"method":"GET",
						data:{"participantRequestId":particiaptionRequestId},
						success:function(data)
						{
							var totalExpense=data['totalAdmitAmount'];
							
							$('#EstExpId').val(totalExpense);
							
						},
						error : function(e)
						{
						}});
				
				}
	
				
			})
			},
			error : function(e)
			{
		    }
		});
}


$(document).ready(function()
		{
	
	loadParticipantRequestApproval();
	getAllAsOrderGenerationInformation();
	
	$.ajax({
		"url" : "/expenditurehead/getAllBudgetHeadDetails",
		"method" : "GET",
		success : function(data) {
			console.log(data);
			setUpDropDown("budHead",data,"budHeadId","budHeadName");
		},
		error : function(e) {
			//showMessage("Error in Getting Programs. Contact Admin");
		}
	});
	
	
	
	
	

$.ajax({
   		"url" : "/expenditurehead/getAllBudgetHeadDetails",
   		"method" : "GET",
   		success : function(data) {
   			
   			
   			budgetlength=data.length;
   		/*	var i;
   			for( i=0;i<data.length;i++){
   			
   			var num=i+1;
   			var num1="bud";
   			num1="bud"+num.toString();
   			//checbxname="center"+num1;
   			 $('#budgetdiv')
   			    .append('<label class="checkbox-inline">')
                .append('<input type="checkbox" id='+num1+' name='+num1+' value='+data[i]['budHeadName']+'>'+data[i]['budHeadName'])
                .append('</label>')
                
   			 }*/
   		},
   		error : function(e) {
   			//showMessage("Error in Getting Programs. Contact Admin");
   		}
   	});
	
	
	$('#asGenerationForm').unbind().bind('submit',function(event)
			{
		           event.preventDefault();
		       	var formData = $(this).serializeArray();
		       	formData.push({"name":"activityCode","value":activityId});
		        formData.push({"name":"participationRequestId","value":particiaptionRequestId});
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
						"url" : "/academicactivity/participationasOrderGeneration",
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
								showMessage("As order is Successfully generated with Order No"+data['asNumberPrefix']);
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
	
	
	 $('#budgetFundDetailsForm').unbind().bind('submit',function(event) 
			   {
			   	        console.log("entrrr in budgetFundDetailsForm");
			   			event.preventDefault();
			   			var budheadId=$('#budHead').val();
			   			var budgetHeadAmount=0;
			   		    var asamountsum=0;
			   		    var asesdifferencesum=0;
			   			$.ajax({
	   						"url" : "/academicactivity/getBudgetFundAmount",
	   						"method" : "GET",
	   						 data : {"budheadId":budheadId,"finyear":finyear},
	   						 success : function(data) {
	   							 
	   							budgetHeadAmount=data['fundAmount'];
	   							console.log("budgetHeadAmount"+budgetHeadAmount);
	   							
	   							
	   							$.ajax({
	   		   						"url" : "/academicactivity/getActivityBudgetHeadAmount",
	   		   						"method" : "GET",
	   		   						 data : {"budheadId":budheadId,"finyear":finyear},
	   		   						 success : function(data1) {
	   		   						
	   		   							
	   		   							if(data1.length>0)
	   		   								{
	   		   								
	   		   								for(var i=0;i<data1.length;i++)
	   		   									{
	   		   									asamountsum=asamountsum+data1['asAmount']
	   		   									}
	   		   							 console.log("asamountsum"+asamountsum);
	   		   								}
	   		   						 },
	   		   						 error:function(e)
	   								 {
	   									 console.log(e);
	   								 }
	   								});
	   							
	   							
	   							$.ajax({
	   		   						"url" : "/academicactivity/getAsEsDifferenceForClosedProgram",
	   		   						"method" : "GET",
	   		   						 data : {"budheadId":budheadId,"finyear":finyear},
	   		   						 success : function(data2) {
	   		   						
	   		   							var asamountsum=0;
	   		   							var esamountsum=0;
	   		   							if(data2.length>0)
	   		   								{
	   		   								
	   		   								for(var i=0;i<data2.length;i++)
	   		   									{
	   		   									asamountsum=asamountsum+data2['as_amount'];
	   		   									esamountsum=esamountsum+data2['es_amount']
	   		   									}
	   		   								
	   		   								asesdifferencesum=asamountsum-esamountsum;
	   		   								console.log("asesdifferencesum"+asesdifferencesum);
	   		   								}
	   		   						 },
	   		   						 error:function(e)
	   								 {
	   									 console.log(e);
	   								 }
	   								});
	   							
	   							var validationAmount=budgetHeadAmount-asamountsum+asesdifferencesum;
	   				   			
	   				   			var amountTobemapped=$('#fundAmountId').val();
	   				   			console.log("amountTobemapped"+amountTobemapped);
	   				   			
	   				   			if(amountTobemapped>validationAmount)
	   				   				{
	   				   				showMessage("cannot be maaped this amount to selected head due to in sufficient balance");
	   				   				}
	   				   				
	   				   			else
	   				   				{
	   				   		   	    var amountTobemapped=$('#fundAmountId').val();
	   				   		   	    
	   				   		   	    
	   				   		  budheadselect=budheadselect+ $("#budHead option:selected").text()+",";
	   				   				var formData = $(this).serializeArray();
	   					   			formData.push({"name":"ac","value":activityId});
	   					   			formData.push({"name":"finYear","value":finyear});
	   					 		    formData.push({"name":"budHeadId","value":budheadId});
	   					 		    formData.push({"name":"asAmount","value":amountTobemapped});
	   					 	        formData.push({"name":"participateRequestId","value":particiaptionRequestId});
	   					 		    
	   					 		    
	   					 		    
	   					   			var formCheck = "success";

	   					   				$('#submitModel').modal('show');

	   					   				$('#confId').unbind().bind('click',function(){

	   					   					console.log("confirm clicked...");
	   				   				
	   					   					$.ajax({
	   					   						"url" : "/academicactivity/saveParticipationActivityBudgetFund",
	   					   						"method" : "POST",
	   					   						 data : formData,
	   					   						 success : function(data) {

	   					   								var data_splits = data.split("-");
	   					   								
	   					   								if(data_splits[0] == "SAVED")
	   					   								{
	   					   									var msg = data_splits[1];
	   					   									centreDialog("Success",msg+"is successfully added","green");
	   					   								
	   					   								}
	   					   								else
	   					   								{
	   					   									var msg = data_splits[1];
	   					   									centreDialog("Error",msg,"red");
	   					   								
	   					   								}

	   					   								$("#budgetFundDetailsForm").trigger('reset');
	   					   								//clearDataTable("activityTypeTable");
	   					   							//	loadCenterFundDetails();
	   					   							}
	   					   						
	   					   							
	   					   						});

	   					   					$('#submitModel').modal('hide');
	   					   					
	   					   				});
	   					   				
	   					   				$('#cancelId').unbind().bind('click',function(){

	   					   					console.log("Cancel clicked...");

	   					   					$('#submitModel').modal('hide');
	   					   					
	   					   				}); 
	   				   				}
	   				   			
	   						 },
	   						 error:function(e)
							 {
								 console.log(e);
							 }
							});
			   				
			   		/* 	*/
			   });
	
	//	});
		
//Report 
$('#printBt').unbind().bind('click',function()
	{
	
	
		   $.ajax({
            "url" : "/academicactivity/getAllAsOrderGenerationInformationByOrderId",
            "method" : "GET",
            "data" : {"orderGenId":orderGenId},
  			success:function(d)
				{
  				//$('#rollno').append(d['rollno']);
  				for(var key in d)
				{
  					console.log("key"+key);
  					//console.log(d[key]);
  					$('#'+key).empty();
				//	$('#'+key).val(d[key]);
					$('#'+key).append(d[key]);
				}
  				     var onwardAmount=0;
  				     var travel=0;
					var returnamnt=0;
					var accmamount=0;
					var otherExpense=0;
					var amountAdmitted=0;
					var sum1=0;
						var sum2=0;
						var sum3=0;
						var sum4=0;
						var sum5=0;;
  				
  				$('#abstractDetails').empty();
  				$('#sectionbHeading').empty();
  				$('#asNumberPrefix').empty();
  				$('#orderGeneratedDate').empty();
  				$('#orderHeading').empty();
  				$('#asOrderContent').empty();
  				$('#orderRemaining').empty();
  				$('#copyTo').empty();
  				
  				//abstractDetails=d['abstractDetails'];
  				
  				
  				$('#abstractDetails').append(d['abstractDetails']);
  				 $('#sectionbHeading').append(d['sectionbHeading']);
  				$('#asNumberPrefix').append(d['asNumberPrefix']);
  				$('#orderGeneratedDate').append(d['orderGeneratedDate']);
  				$('#orderHeading').append(d['orderHeading']);
  				$('#asOrderContent').append(d['asOrderContent']);
  				$('#orderRemaining').append(d['orderRemaining']);
  				$('#copyTo').append(d['copyTo']); 
  				$.ajax({
  					"url" : "/academicactivity/getParticiapntDetailsByParticipantRequestId",
  					"method" : "GET",
  					"async":false,
  					data:{"particiaptionRequestId":particiaptionRequestId},
  					success:function(data)
  					{
  						
  				
  						
  						for(var i=0;i<data.length;i++)
  							{
  							console.log("usercodeeeeeeeeeeeee"+data[i]['userCode']);
  							userCodeArray[i]=data[i]['userCode'];
  							contactNumberArray[i]=data["contactNumber"];
  							//console.log(contactNumberArray[i])
  							
  							onwardAmount=parseFloat(onwardAmount)+parseFloat(data[i]['onwardAmountAdmitted']);
  							returnamnt=parseFloat(returnamnt)+parseFloat(data[i]['returnAmountAdmitted']);
  							
  							travel=onwardAmount+returnamnt;
  							amountAdmitted=parseFloat(amountAdmitted)+data[i]['amountAdmitted'];
  							}
  	                    $.ajax({
  							
  							"url":"/academicactivity/getParticiapntOtherExpensesDetailsByParticipantRequestId",
  							"method":"GET",
  							"async":false,
  							data:{"participantRequestId":particiaptionRequestId},
  							success:function(data)
  							{
  						
  								
  								sum1=parseFloat(data['regFeeAdmitted'])+parseFloat(data['printoutMemAdmitted'])+parseFloat(data['photocopyMemAdmitted']);
  								console.log("sum1="+sum1);
  								console.log("sum=2"+sum2);
  								console.log("sum3=+sum3");
  								sum2=parseFloat(data['bindingAmntAdmit'])+parseFloat(data['costemAdmitted'])+parseFloat(data['printoutComAdmitted']);
  								sum3=parseFloat(data['photocopyComAdmitted'])+parseFloat(data['bindingComAdmitted'])+parseFloat(data['costPaperComAdmitted'])+parseFloat(data['courierChargesAdmitted']);
  							    sum4=sum1+sum2+sum3;
   								sum5=sum5+sum4+travel+amountAdmitted;
  								
  								/* $('#abstractDetails').append(abstractDetails);
  								$('#sectionbHeading').append(sectionbHeading);
  				  				$('#asNumberPrefix').append(asNumberPrefix);
  				  				$('#orderGeneratedDate').append(orderGeneratedDate);
  				  				$('#orderHeading').append(orderHeading);
  				  				$('#asOrderContent').append(asOrderContent);
  				  				$('#orderRemaining').append(orderRemaining);
  				  				$('#copyTo').append(copyTo);
  				  				 */
  				  				
  					
  						 
  										
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
  				
  				$("#estimtab").empty();
  				
  				
  				 $('#estimtab').append('<thead><tr><th scope="col">Head</th><th scope="col">Amount</th></tr></thead>');
					 tr=$('<tr><td>Travel Allowance+</td><td>'+travel+'</td></tr>');
						$('#estimtab').append(tr);
						 tr=$('<tr><td>Lodging & Boarding</td><td>'+amountAdmitted+'</td></tr>');
								$('#estimtab').append(tr);
								
								
								 tr=$('<tr><td>Other Expenses</td><td>'+sum4+'</td></tr>');
									$('#estimtab').append(tr);
									
									 tr=$('<tr><td>Expenditure From  '+budheadselect+'</td><td>'+sum5+'</td></tr>');
									 $('#estimtab').append(tr);
									 
									 var orderRemaining="The expenditure of Rs.     "+sum5+" may be met form the Head of "+budheadselect+" Expenditure (02) Academic programmes and other activities for the Financial Year"+finyear;
									 $('#orderRemaining').append(orderRemaining);
									
  				reportPrinting('reportDiv');
				}
			});
   
			
			   
		
	}); 
		   
		

		   
		
		   
		   
		   
		   
		   
		   
   
  /*  $.ajax({
	          "url":"/expenditurehead/getActivityExpHeadExpensesByActivityIdAndFinyear",
		       "method":"GET",
		        data:{"activityId":activityId,"finyear":finyear},
		 success:function(data)
			{
			 var total=0;
		
			 $('#estimtab').append('<thead><tr><th scope="col">Head</th><th scope="col">Amount</th></tr></thead>');
			 var externfund=$('#externlFundPrtId').val();
			 
			
			 var planfund=0;
			 var extrnlFundAmnt=0;
			 for(var i=0;i<data.length;i++)
				{
				var des=data[i]['actvtyExpHdkey']['exp']['expHeadsKey']['description'];//data[i]["expHeadsKey"]["description"];
				console.log("getExpenditureHeadsByFinYear"+des)
				exheadarray[i]=des;
				finyearexphead=data[i]['actvtyExpHdkey']['exp']['expHeadsKey']['finYear'];             //data[i]["expHeadsKey"]["finYear"]
				console.log("getExpenditureHeadsByFinYear"+finyearexphead);
				var nualsalloctinamount=des+"nualsalloctinamount";
				var estmtdexpdture=des+"estmtdexpdture";
				var deviation=des+"deviation";
				var comments=des+"comments";
				var sino=i+1;
				var headgrantAmnt=des+"headgrantAmnt";
				total=parseFloat(total)+parseFloat(data[i]['estimatedExp']);
			tr=$('<tr><td>'+des+'</td><td>'+data[i]['estimatedExp']+'</td></tr>');
			$('#estimtab').append(tr);
				}
			
			 tr=$('<tr><td>Total</td><td> Rs'+total+'</td></tr>');
			 $('#estimtab').append(tr);
			 
			 if(externfund=="y")
			 {
				 extrnlFundAmnt=$('#externlFundAmntId').val();
			 tr=$('<tr><td>Less Amount Collected as Registration Fee</td><td> Rs'+extrnlFundAmnt+'</td></tr>');
			 $('#estimtab').append(tr);
			 }
			 
			 if(externfund=="n")
				 {
				  extrnlFundAmnt=0;
				 }
			 
			 planfund=parseFloat(total)-parseFloat(extrnlFundAmnt);
			 tr=$('<tr><td>Net Expenditure from Plan Fund</td><td> Rs'+planfund+'</td></tr>');
			 $('#estimtab').append(tr);
			 
			 var orderRemaining="The expenditure of Rs.     "+budgetInvolved+"may be met form the Head of"+budgetInvolved+"Expenditure (02) Academic programmes and other activities for the Financial Year"+finyear;
			 $('#orderRemaining').append(orderRemaining);
				},
			error:function(e)
			{
			
			}
		
	}); */   
  
	

		
		
		
		
		
	});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

</script>


<div id="gridRow" class="card card-info card-outline">
	<div class="card-header">
		<h3>List Of Program Participation For As Generation</h3>

	</div>
	<div class="card-body">
		<div class="table-responsive">
			<table
				class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border"
				id="participantActivityTable">

			</table>
		</div>
	</div>
</div>

<div  id="asGeneration" class="card card-info card-outline">
   <div class="card-header">
      <h3><center>Administrative Sanction Of Program Participation</center></h3>
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
               <label for="activitytype"> AS Granted amount</label> <input type="text"
                     id="EstExpId" name="estimExp" class="form-control" required></input>
               </div> 
                <div class="form-group">
                <label for="sectionbHeadingId">Section B Heading</label> 
                  <input type="text" name="sectionbHeading" class="form-control" id="sectionbHeadingId"
                  required />
                </div>
                 <div class="form-group">
                <label for="activitytype">AsNumber Prefix</label> 
                   <input type="text" name="asNumberPrefix" class="form-control" id="asNumberPrefixId"
                  required />
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
                <label for="activitytype"> References </label> 
                   <textarea id="referencesId" name="asrefer" rows="4" cols="50"
                  class="form-control" required></textarea>
               </div>
            
                  <div class="form-group">
                <label for="activitytype">Order Heading</label> 
                   <input type="text" name="orderHeading" class="form-control" id="orderHeadingId"
                  required />
               </div>
                  <div class="form-group">
                <label for="activitytype">AsOrder Content</label> 
                   <textarea id="asOrderContentId" name="asOrderContent" rows="4" cols="50"
                  class="form-control" required></textarea>
               </div>
               
             <!--   <div class="form-group" id="budgetdiv">
               <label for="activity type"> Budget involved in the Program
               </label>
            </div> -->
            
              <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalLong">
                Add  Budget Mapping
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
						<label for="centerfund">Budget Head Amount<span class="required">*</span></label>
						 		<div style="width:100%">
							 		<div style="display: inline-block;width:80%">
								<input type="number" name="fundAmount" class="form-control" id="fundAmountId" required/>
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
   
   <div id="gridRow" class="card card-info card-outline">
	<div class="card-header">
		<h3>As Sanctioned Activities</h3>

	</div>
	<div class="card-body">
		<div class="table-responsive">
			<table
				class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border"
				id="participantActivityTable1">

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
<table class="table" id="gridTable">
<tr><td colspan="2" style="text-align:center"><b>(Abstract)</b></td></tr>
<tr><td colspan="2" style="text-align:justify" id="abstractDetails"></td></tr>
<tr><td colspan="2" style="text-align:center" id="sectionbHeading"></td></tr>
<tr><td id="asNumberPrefix"></td><td id="orderGeneratedDate"></td></tr>
<tr><td colspan="2" style="text-align:center"><u><b>ORDER</b></u></td></tr>
<tr><td colspan="2" id="orderHeading"></td></tr>
<tr><td colspan="2" style="text-align:justify" id="asOrderContent"></td></tr>
<tr>
<td colspan="2">
  <tr><td><table id="estimtab" class="table table-striped"></table></td></tr> 
</td>
</tr>
<tr><td colspan="2" style="text-align:justify">This administrative approval is subjected to compliance of the relevant Government orders, University regulations, Circulars, SOP's and other advisories  issued in this regard</td></tr>
<tr><td colspan="2" style="text-align:justify" id="orderRemaining"></td></tr>
<tr><td>Copy to</td><td>By order of the Vice-Chancellor</td></tr>
<tr><td rowspan="10" id="copyTo"></tr>
</table>
</div>
</div>
</div>
<!-- <div class="row">
<div class="col-md-4"></div>
<div class="col-md-4"><button class="btn btn-success form-control" id="printRep">Print</button></div>
<div class="col-md-4"></div>
</div> -->
   