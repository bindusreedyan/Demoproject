
<script type="text/javascript" src="./sfs_public/js/datepicker/bootstrap-datepicker.js"></script>
 <link rel="stylesheet" href="./sfs_public/css/jquery-confirm.min.css">
 <script src="./sfs_public/js/jquery-confirm.min.js"></script> 
<script>
var activityId;
var centerarray=[];
var centerNameArray=[];
var asNo;
var asSanctionAmnt;
var activityId;
var advanceSettlementId;
var constrainError;
var vendorStatus;
var vendorStatus;
var centerArrayString="";
var settlementId;
var crCode;
var expenditureId;
var totalExpAmt=0;
var expHeadArray=[];
var finYear;
var totalPaidBack1=0;
var totalAdvancePaid1=0;
var totalExpAmt1=0;
var totalAdvanceSettled=0;
var expenseHead=[];
var remainingpaid=0;

var checkListid;








function viewProcedureComplaince()
{
	
 	$.ajax({
   		"url" : "/academicactivity/viewComplianceToProceduralRequirements",
   		"method" : "GET",
   		data:{"settlementId":settlementId},
   		success : function(data) {
   			setUpDataTable(data,[{"checkId":"CheckList Id"},{"complDesc":" Procedural Requirements"},{"included":"Yes/No"}],"proceduralRequirementsgridTable","null");
   	onDataTableClick('proceduralRequirementsgridTable',function()
   					{
   		console.log("clicked proceduralRequirementsgridTable");
   		//checkListid=selectedRowFromDataTable[0];
			$('#checkListId').val(selectedRowFromDataTable[1]);
			$('#complDescId').val(selectedRowFromDataTable[2]);
			$('#includedId').val(selectedRowFromDataTable[3]);
			$('#proceduralRequirementDivId1').modal({backdrop: 'static',keyboard: false}); 
   															
   					});
   		},
   		error:function(e)
   		{
   		
   		}
   		});  
	}







function getTotalProgramExpense()
{
	$.ajax({
   		"url" : "/academicactivity/loadAllExpenditureBySettlementId",
   		"method" : "GET",
   		data:{"settlementId":settlementId},
   		success : function(data) {
   			var totalExpAmnt=0;
   			var balAmountTobePaid=0;
			var currentTotal=0;
			var sumpaid=0;
   			for(let i=0;i<data.length;i++)
   				{
   				totalExpAmnt=parseFloat(data[i]['totalAmt'])+parseFloat(totalExpAmnt);	
   				if(data[i]['paidStatus']=='paid')
					{
					//balAmountTobePaid=parseFloat(data[i]['totalAmt'])+parseFloat(balAmountTobePaid);
				
					sumpaid=parseFloat(data[i]['totalAmt'])+sumpaid;
				
					}
				
				if(data[i]['paidStatus']=='notpaid')
					{
					balAmountTobePaid=parseFloat(data[i]['totalAmt'])+parseFloat(balAmountTobePaid);
					}
				}
				$('#totalExpenseAmntId').val(totalExpAmnt);
				
				$('#balAmntId').val(balAmountTobePaid);
				},
				 error(e)
				 {
					showMessage("Error in approval. Contact Admin"); 
				 }
				 });
	
	
	
	}
function loadAllExpenditureDetailsSubmitted() {
	$.ajax({
		"url" : "/academicactivity/loadAllExpenditureDetailsAdministrativeApproved",
		"method" : "GET",
		success : function(data) {

			   setUpDataTable(data, [ 
				{
					"setId":"SettlementId"	
				},
				
				{
					"activityCode":"Activity Code"
				},
				{
					"title":"Activity Name"
				},
				{
					"asNO":"Administrative Sanction Number"
				},
				{
					"finyear":"Financial Year"
				},
				{
				  "settlementType":"Settlement Type"
				 },
				{
					"advSettlementStatus" : "Status"
				}
			  ], "advSettlementDetailsTable", "select-checkbox");
				  onDataTableClick('advSettlementDetailsTable', function() {
					if(selectedRowFromDataTable != null)
					   {
						$('html, body').animate({ scrollTop: $('#formCenterActivityDetailsdiv').offset().top }, 'slow');
						
						settlementId=selectedRowFromDataTable[1];
						$('#titleId').val(selectedRowFromDataTable[3]);
						activityId=selectedRowFromDataTable[2];
						finYear=selectedRowFromDataTable[5];
						$('#activityLevelId').val(selectedRowFromDataTable[7]);
					
					 	$.ajax({
					   		"url":"/academicfinance/getAllactiviticenters",
					   		"method":"GET",
					   		data:{"activityId":activityId},
					   		success : function(data) {
					   			centerlength=data.length;
					   			//console.log(data);
					   			var i;
					   			console.log("daaataaaaaaaaaa in getAllActivityById2"+data);
					   			var centerDetails={};
								/* for(let i=0;i<data.length;i++)
					    		{
                                 centerDetails[data[i]['actvtyCenterKey']['cm']['centre_id']]=data[i]['actvtyCenterKey']['cm']['centre_name]'];
					    			
					    		}
								 var $select = $("select[name='costCenterCode']")
								$select.append($("<option>", { value: "", html:"Select"}));
								for(let key in centerDetails)
								{
									
									console.log(centerDetails[key]);
									  $select.append($("<option>", { value: key, html:centerDetails[key]}));
									
								} */
								var $select = $("select[name='costCenterCode']");
								$select
							    .find('option')
							    .remove()
							 
								$select.append($("<option>", { value: "", html:"Select"}));
					   			
					   			for( i=0;i<data.length;i++){
					   			var num=i+1;
					   			var num1="center"+num.toString();
					   			checbxid=num1;
					   			centerarray[i]=data[i]['actvtyCenterKey']['cm']['centre_id'];
					   			centerNameArray[i]=data[i]['actvtyCenterKey']['cm']['centre_name'];
					   			centerArrayString=centerArrayString+","+data[i]['actvtyCenterKey']['cm']['centre_code'];
					   			console.log("iiiiiiiiiiii"+centerarray[i]);
					   			//checbxname="center"+num1;
					   			 $('#centerdiv')
					   			 .append('<label class="checkbox-inline">')
					                .append('<input type="checkbox" id='+checbxid+' name='+num1+' checked="true" value='+data[i]['actvtyCenterKey']['cm']['centre_id']+'>'+data[i]['actvtyCenterKey']['cm']['centre_name'] )
					                .append('</label>')
					                
					                
					                $select.append($("<option>", { value: data[i]['actvtyCenterKey']['cm']['centre_id'], html:data[i]['actvtyCenterKey']['cm']['centre_name']}));
					                
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
							asSanctionAmnt=data['total_estimated'];
							console.log("assanctionamntoooooooooooooo"+asSanctionAmnt);
							$("#sancAmtId").val(asSanctionAmnt);
							},
							error(e)
							{
								console.log(e);
							}
							});
					 	
					 	$.ajax({
					   		"url" : "/academicactivity/getAllAdvanceRecieved",
					   		"method" : "GET",
					   		data:{"activityId":activityId},
					   		success : function(data) {
	                             var advanceRecievedlength=data.length;
	                           
						   			
						   			if(advanceRecievedlength==0)
						   				{
						   				$('#advanceObtainedId').val("no");
						   				}
						   			if(advanceRecievedlength>0)
						   				{
						   				$('#advanceObtainedId').val("yes");
						   				}
					   			if(advanceRecievedlength==0)
					   				{
					   				$('#advanceObtainedId').val("no");
					   				}
					   			if(advanceRecievedlength>0)
					   				{
					   				$('#advanceObtainedId').val("yes");
					   				}
					   			
					   			
					   			
					   			var totalAdvancePaid=0;
					   			$('#advnceRequestGridBody').empty();
								
								for(var i=0;i<data.length;i++)
								{
									var sino=i+1;

									 tr=$('<tr><td>'+sino+'</td><td>'+data[i]['purpose']+'</td><td>'+centerArrayString+'</td><td>'+data[i]['voucharNo']+'</td><td>'+data[i]['voucharDate']+'</td><td>'+data[i]['advancePaid']+'</td><tr>');
									 $('#advnceRequestGridBody').append(tr);
									 totalAdvancePaid=parseFloat(data[i]['advancePaid'])+parseFloat(totalAdvancePaid);
									 totalAdvanceSettled=parseFloat(data[i]['settledAmount'])+parseFloat(totalAdvanceSettled);
								}  
								$('#totalAdvanceId').val(totalAdvancePaid);
								totalAdvancePaid1=totalAdvancePaid;
								
								
								remainingpaid=parseFloat(totalAdvancePaid)-parseFloat(totalAdvanceSettled);
								
								 $('#advTotalId').val(remainingpaid);
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
					   			
					   			$('#refNoId').val(data['refNo']);
					   			$('#SettlementTypeId').val(data['settlementType']);
					   			$('#enteredRemarksId').val(data['enteredRemarks']);
					   			$('#verifiedL1RemarksId').val(data['verifiedL1Remarks']);
					   			$('#adminRecommendationRemarkId').val(data['verifiedL2Remarks']);
					   			
					   		},
					   		error:function(e)
					   		{
					   			
					   		}
					   		});
					 	
					 	$.ajax({
					   		"url" : "/academicactivity/loadAllAmountPaidBackBySettlementId",
					   		"method" : "GET",
					   		data:{"settlementId":settlementId},
					   		success : function(data) {
                              $('#paidBackGridBody').empty();
                           
                              
                              var totalPaidBack=0;
                   				for(var i=0;i<data.length;i++)
								{
									  var centreid=data[i]['costCenterCode'];
									//  var totalPaidBack=0;
				         		  $.ajax({
									   		"url" : "/academic/getCentreById",
									   		"method" : "GET",
									   		async:false,
									   		data:{"centre_id":centreid},
									   		success : function(data1) {
									   			
									   			crCode=data1['centre_code'];
									   			console.log("crcodeeeeeeeeeee"+crCode);
									   		},
									   		error:function(e)
									   		{
									   			
									   		}
									  });
									
									var sino=i+1;
									console.log("crcodeeeeeeeeeee"+crCode);
									 tr=$('<tr><td>'+sino+'</td><td>'+data[i]['paidBackDescription']+'</td><td>'+crCode+'</td><td>'+data[i]['voucharNo']+'</td><td>'+data[i]['paymentDate']+'</td><td>'+data[i]['amountPaidBack']+'</td><tr>');
									 $('#paidBackGridBody').append(tr);
									 
									 totalPaidBack=parseFloat(data[i]['amountPaidBack'])+parseFloat(totalPaidBack);
								}  
                   				
                   				     
                   				$('#totalPaidBackId').val(totalPaidBack);
                   				totalPaidBack1=totalPaidBack;
					   		},
					   		error:function(e)
					   		{
					   			
					   		}
					   		});
					 	
					 	
					 	
					 	
					 	$("#expenditureViewIdRp" ).on('click',function(event) { 
							$.ajax({
						   		"url" : "/academicactivity/loadAllExpenditureBySettlementIdByIntefaceForResourcePerson",
						   		"method" : "GET",
						   		data:{"settlementId":settlementId},
						   		success : function(data) {
						   			var totalExpAmt=0;
						   			//totalExpAmt1=0;
						   			var balAmountTobePaid=0;
						   			for(var i=0;i<data.length;i++)
						   				{
						   				totalExpAmt=parseFloat(data[i]['advtotal'])+parseFloat(totalExpAmt);		   				
						   				if(data[i]['paidstatus']=='notpaid')
						   					{
						   					balAmountTobePaid=parseFloat(data[i]['advtotal'])+parseFloat(balAmountTobePaid);
						   					}
						   				}
						   			
						   			$('#totalExpenditureRpId').val(totalExpAmt);
						   			//$('#totalExpenseAmntId').val(totalExpAmt);
						   		//	$('#advTotalId').val(totalAdvancePaid1);
						   			 var totlPaidback1=$('#totalPaidBackId').val();
						   			$('#amntPaidBackId').val($('#totalPaidBackId').val());
								    var remainadvance=parseFloat(totalAdvancePaid1)-parseFloat(totalAdvanceSettled);
								    console.log("remainadvance"+remainadvance);
								    console.log("totalExpAmt"+totalExpAmt);
								    $('#advTotalId').val(remainingpaid);
				  			    var amnttobepaid1=parseFloat(remainingpaid)-parseFloat(totalPaidBack1);
								    
								    var amnttobepaid=parseFloat(totalExpAmt)-amnttobepaid1;
								    $('#balAmntId').val(amnttobepaid);
							        totalExpAmt1=totalExpAmt;
						   			//var balAmountTobePaid=totalExpAmt1-(totalAdvancePaid1-totalPaidBack1);
						   			$('#balAmountTobePaidIdRp').val(balAmountTobePaid);
						   			setUpDataTable(data,[{"expId":"ExpenditureId"},{"billno":"Bill No/Vouchar No"},{"expamnt":"Value Amount"},{"taxAmnt":"Tax Amount"},{"advtotal":"Total Amount"},{"paidstatus":"Paid Status"},{"payee":"Payee Name"},{"gstno":"GST NO"},{"panno":"PAN NO"},{"paidstatus":"Status"},{"modepayment":"Mode of Payment"},{"headname":"Expenditure Head"}],"expendituregridTableRp","null");
						   	 	onDataTableClick('expendituregridTableRp', function() {		
										if(selectedRowFromDataTable != null)
										{
											
											expenditureId=selectedRowFromDataTable[1];
											
											$.ajax({
										   		"url" : "/resource/getResourcepersondetailsById",
										   		"method" : "GET",
										   		data:{"activityId":activityId},
										   		success : function(data) {
										   			console.log(data);
										   			setUpDropDown("payeeNameId1",data,"res_id","name");
										   			$.ajax({
												   		"url" : "/academicactivity/loadExpenditureByexpenditureId",
												   		"method" : "GET",
												   		data:{"expenditureId":expenditureId},
												   		success : function(data) {
												   			$('#expHeadNameId1').val(data['headId']['headId']);
												   			$('#payeeNameId1').val(data['resourceId']);
												   			var resourceId=data['resourceId'];
												   			$('#remarksId1').val(data['enteredRemarks']);
												   			$('#vendorTypeId1').val(data['vendorType']);
												   			
												   			$.ajax({
														   		"url" : "/resource/getResourcepersondetailofById",
														   		"method" : "GET",
														   		data:{"resId":resourceId},
														   		success : function(data1) {
														   		//	$('#gstNoId1').val(data1['gstNo']);
														   			$('#panNoId1').val(data1['pan_no']);
														   			
														   		},
														   		error:function(e)
														   		{
														   			
														   		}
															});
														   	
												   			$('#paidStatusId1').val(data['paidStatus']);			
															$('#modePaymentId1').val(data['modePayment']);
															$('#enteredRemarks1').val(data['enteredRemarks']);
												   			
															
												   		},
												   		error:function(e)
												   		{
												   			
												   		}
													});
										   			
										   		},
										   		error : function(e) {
										   			//showMessage("Error in Getting Programs. Contact Admin");
										   		}
										   	});
											
										
											$('#expId1').val(selectedRowFromDataTable[1]);
											$('#expenditureDetailsFormId1').modal({backdrop: 'static',keyboard: false});
											//$('#expHeadNameId').val(selectedRowFromDataTable[11]);
											$('#billNoId1').val(selectedRowFromDataTable[2]);
											$('#expAmntId1').val(selectedRowFromDataTable[3]);
											
											$('#taxId1').val(selectedRowFromDataTable[4]);
											$('#totalAmtId1').val(selectedRowFromDataTable[5]);
										}
									//	getTotalProgramExpense();
										}); 
						   			
						   		},
						   		error:function(e)
						   		{
						   			
						   		}
						   		});
						
							getTotalProgramExpense();
						});	
					 	
					 	
//to view the bill details added
						
						$("#expenditureViewId" ).on('click',function(event) { 
							$.ajax({
						   		"url" : "/academicactivity/loadAllExpenditureBySettlementIdByInteface",
						   		"method" : "GET",
						   		data:{"settlementId":settlementId},
						   		success : function(data) {
						   		
						   			//totalExpAmt1=0;
						   			var totalExpAmt=0;
						   			var balAmountTobePaid=0;
						   			for(var i=0;i<data.length;i++)
						   				{
						   				totalExpAmt=parseFloat(data[i]['advtotal'])+parseFloat(totalExpAmt);		   				
						   				if(data[i]['paidstatus']=='notpaid')
						   					{
						   					balAmountTobePaid=parseFloat(data[i]['advtotal'])+parseFloat(balAmountTobePaid);
						   					}
						   				}
						   			
						   			$('#totalExpenditureId').val(totalExpAmt);
						   		//	$('#totalExpenseAmntId').val(totalExpAmt);
						   		//	$('#advTotalId').val(totalAdvancePaid1);
						   			
						   			var totlPaidback1=$('#totalPaidBackId').val();
						   			$('#amntPaidBackId').val($('#totalPaidBackId').val());
								    var remainadvance=parseFloat(totalAdvancePaid1)-parseFloat(totalAdvanceSettled);
								    console.log("remainadvance"+remainadvance);
								    console.log("totalExpAmt"+totalExpAmt);
								    $('#advTotalId').val(remainingpaid);
				  			    var amnttobepaid1=parseFloat(remainingpaid)-parseFloat(totalPaidBack1);
								    
								 //   var amnttobepaid=parseFloat(totalExpAmt)-amnttobepaid1;
								   // $('#balAmntId').val(amnttobepaid);
							      //  totalExpAmt1=totalExpAmt;
						   			//var balAmountTobePaid=totalExpAmt1-(totalAdvancePaid1-totalPaidBack1);
						   			$('#balAmountTobePaidId').val(balAmountTobePaid);
						   			setUpDataTable(data,[{"expId":"ExpenditureId"},{"billno":"Bill No/Vouchar No"},{"expamnt":"Value Amount"},{"taxAmnt":"Tax Amount"},{"advtotal":"Total Amount"},{"paidstatus":"Paid Status"},{"payee":"Payee Name"},{"gstno":"GST NO"},{"panno":"PAN NO"},{"paidstatus":"Status"},{"modepayment":"Mode of Payment"},{"headname":"Expenditure Head"}],"expendituregridTable","null");
						   	 	onDataTableClick('expendituregridTable', function() {		
										if(selectedRowFromDataTable != null)
										{
											
											expenditureId=selectedRowFromDataTable[1];
											
											$.ajax({
										   		"url" : "/vendors/getAllVendorDetails",
										   		"method" : "GET",
										   		data:{"vendorStatus":vendorStatus},
										   		success : function(data) {
										   			console.log(data);
										   			setUpDropDown("payeeNameId1",data,"vendorId","businessName");
										   			$.ajax({
												   		"url" : "/academicactivity/loadExpenditureByexpenditureId",
												   		"method" : "GET",
												   		data:{"expenditureId":expenditureId},
												   		success : function(data) {
												   			$('#expHeadNameId1').val(data['headId']['headId']);
												   			$('#payeeNameId1').val(data['vendorId']);
												   			var vendorId=data['vendorId'];
												   			$('#remarksId1').val(data['enteredRemarks']);
												   			$.ajax({
														   		"url" : "/vendors/getDetailsOfVendorById",
														   		"method" : "GET",
														   		data:{"vendorId":vendorId},
														   		success : function(data1) {
														   			$('#gstNoId1').val(data1['gstNo']);
														   			$('#panNoId1').val(data1['panNo']);
														   			
														   		},
														   		error:function(e)
														   		{
														   			
														   		}
															});
														   	
												   			$('#paidStatusId1').val(data['paidStatus']);			
															$('#modePaymentId1').val(data['modePayment']);
															$('#enteredRemarks1').val(data['enteredRemarks']);
															$('#vendorTypeId1').val(data['vendorType']);
												   			
												   		},
												   		error:function(e)
												   		{
												   			
												   		}
													});
										   			
										   		},
										   		error : function(e) {
										   			//showMessage("Error in Getting Programs. Contact Admin");
										   		}
										   	});
											
										
											$('#expId1').val(selectedRowFromDataTable[1]);
											$('#expenditureDetailsFormId1').modal({backdrop: 'static',keyboard: false});
											//$('#expHeadNameId').val(selectedRowFromDataTable[11]);
											$('#billNoId1').val(selectedRowFromDataTable[2]);
											$('#expAmntId1').val(selectedRowFromDataTable[3]);
											
											$('#taxId1').val(selectedRowFromDataTable[4]);
											$('#totalAmtId1').val(selectedRowFromDataTable[5]);
											
											
										}
										
										}); 
						   			
						   		},
						   		error:function(e)
						   		{
						   			
						   		}
						   		});
							getTotalProgramExpense();
					
						});
	
					 	
					 	
					 	
					 	
					 	
					 	
					 	
					 	
					 	
					 	
					 	
					 	
					 	
					 	
					 	
					 	     
/* 					  	$.ajax({
					   		"url" : "/academicactivity/loadAllExpenditureBySettlementId",
					   		"method" : "GET",
					   		data:{"settlementId":settlementId},
					   		success : function(data) {
					   			var balAmountTobePaid=0;
					   			for(var i=0;i<data.length;i++)
					   				{
					   				
					   				totalExpAmt=parseFloat(data[i]['totalAmt'])+parseFloat(totalExpAmt);
                                 expenseHead[i]=data[i]['expHeadName'];
					   				
					   				
					   				
					   				if(data[i]['paidStatus']=='notpaid')
					   					{
					   					balAmountTobePaid=parseFloat(data[i]['totalAmt'])+parseFloat(balAmountTobePaid);
					   					}
					   				}
					   			
					   			
					   			
					   			$('#totalExpenseAmntId').val(totalExpAmt);
					   		//	$('#advTotalId').val(totalAdvancePaid1);
					   		
					   		
					   		
					   		    $('#amntPaidBackId').val($('#totalPaidBackId').val());
					   			
					   			$('#totalExpenditureId').val(totalExpAmt);
					   		 var remainadvance=parseFloat(totalAdvancePaid1)-parseFloat(totalAdvanceSettled);
							    
							    console.log("remainadvance"+remainadvance);
							    console.log("totalExpAmt"+totalExpAmt);
							  //  var amnttobepaid=parseFloat(totalExpAmt)-(parseFloat(totalAdvanceSettled)-parseFloat(totalPaidBack1));
							    
							  //  var amnttobepaid=parseFloat(totalExpAmt)-(parseFloat(remainingpaid)-parseFloat(totalPaidBack1));
							    	var totalUniversitypaidBack=$('#totalPaidBackId').val();
							    var amnttobepaid1=parseFloat(remainingpaid)-parseFloat(totalUniversitypaidBack);
						    
						    var amnttobepaid=parseFloat(totalExpAmt)-amnttobepaid1;
							  
							  
							  
							    $('#balAmntId').val(amnttobepaid);
					   			totalExpAmt1=totalExpAmt;
					   		//	var balAmountTobePaid=totalExpAmt1-(totalAdvancePaid1-totalPaidBack1);
					   			$('#balAmountTobePaidId').val(balAmountTobePaid);
					   			
					   			
					   			
					   			
					   			
					   			setUpDataTable(data,[{"expId":"ExpenditureId"},{"billNo":"Bill No/Vouchar No"},{"expAmnt":"Value Amount"},{"tax":"Tax Amount"},{"totalAmt":"Total Amount"},{"businessName":"Payee Name"},{"gstNo":"GST NO"},{"panNo":"PAN NO"},{"paidStatus":"Status"},{"modePayment":"Mode of Payment"},{"expHeadName":"Expenditure Head"}],"expendituregridTable","null");
					   			onDataTableClick('expendituregridTable', function() {		
									if(selectedRowFromDataTable != null)
									{
										
										expenditureId=selectedRowFromDataTable[1];
										$.ajax({
									   		"url" : "/academicactivity/loadExpenditureByexpenditureId",
									   		"method" : "GET",
									   		data:{"expenditureId":expenditureId},
									   		success : function(data) {
									   			$('#expHeadNameId').val(data['headId']['headId']);
									   			$('#payeeNameId').val(data['vendorId']['vendorId']);
									   			$('#remarksId').val(data['enteredRemarks']);
									   			$('#admsAmtId').val(data['admsAmt']);
									   			
									   		},
									   		error:function(e)
									   		{
									   			
									   		}
										});
										
										$('#expenditureDetailsFormId').modal({backdrop: 'static',keyboard: false});
										//$('#expHeadNameId').val(selectedRowFromDataTable[11]);
										$('#billNoId').val(selectedRowFromDataTable[2]);
										$('#expAmntId').val(selectedRowFromDataTable[3]);
										
										$('#taxId').val(selectedRowFromDataTable[4]);
										$('#totalAmtId').val(selectedRowFromDataTable[5]);
										$('#gstNoId').val(selectedRowFromDataTable[7]);
										$('#panNoId').val(selectedRowFromDataTable[8]);
										$('#paidStatusId').val(selectedRowFromDataTable[9]);			
										$('#modePaymentId').val(selectedRowFromDataTable[10]);
										$('#enteredRemarks').val(selectedRowFromDataTable[10]);
										
										
									}
									
									});
					   			
					   		},
					   		error:function(e)
					   		{
					   			
					   		}
					   		}); */ 
					  	
					  	$.ajax({
					   		"url" : "/academicactivity/loadAllGoodsReturnBySettlementId",
					   		"method" : "GET",
					   		data:{"settlementId":settlementId},
					   		success : function(data) {
					   			setUpDataTable(data,[{"item":"Name of Item Returned"},{"value":"Value"},{"qty":"Quantity"},{"goodsRemarks":"Remarks"}],"goodsReturngridTable","null");
					   		},
					   		error:function(e)
					   		{
					   		
					   		}
					   		});
					  	
					  	$.ajax({
					   		"url" : "/academicactivity/loadAllphysicalDelivarablesDetails",
					   		"method" : "GET",
					   		data:{"settlementId":settlementId},
					   		success : function(data) {
					   		//	setUpDataTable(data,[{"expId":"Deliverable Id"},{"delivarableDescription":"Physical Deliverables Vis a Vis Actual Performance"},{"asGranted":"AS Granted"},{"actual":"Actual Expenditure"},{"deviation":"Deviation"},{"recommended":"Justification"}],"physicalDelivarablesgridTable","null");
					   		//setUpDataTable(data,[{"expId":"Deliverable Id"},{"delivarableDescription":"Physical Deliverables Vis a Vis Actual Performance"},{"asGranted":"AS Granted"},{"actual":"Actual Expenditure"},{"deviation":"Deviation"},{"physicalEnteredRemarks":"Remarks"},{"recommended":"Recommended"},{"justification":"Justification"}],"physicalDelivarablesgridTable","null");
					   		
					   		setUpDataTable(data,[{"expId":"Deliverable Id"},{"delivarableDescription":"Physical Deliverables Vis a Vis Actual Performance"},{"asGranted":"AS Granted"},{"actual":"Actual Expenditure"},{"deviation":"Deviation"},{"physicalEnteredRemarks":"Remarks"},{"recommended":"Recommended"},{"justification":"Justification"}],"physicalDelivarablesgridTable","null");
					   			onDataTableClick('physicalDelivarablesgridTable',function()
					   					{
					   					/* $('#delvId').val(selectedRowFromDataTable[1]);
					   					$('#delivarableDescriptionId').val(selectedRowFromDataTable[2]);
					   					$('#asGrantedId').val(selectedRowFromDataTable[3]);
					   					$('#actualId').val(selectedRowFromDataTable[4]);
					   					$('#deviationId').val(selectedRowFromDataTable[5]);
					   					$('#recommendedId').val(selectedRowFromDataTable[6]);
					   					
					   					
					   					$.ajax({
									   		"url" : "/academicactivity/loadAllphysicalDelivarablesDetails",
									   		"method" : "GET",
									   		data:{"settlementId":settlementId},
									   		success : function(data) {
									   			
									   			$('#phyEnteredRemarksId').val(data[0]['physicalEnteredRemarks']);
									   			$('#recommendedId').val(data[0]['recommended']);
							   					
									   			$('#physicalDelivarablesDivId').modal({backdrop: 'static',keyboard: false}); */
									   			$('#delvId').val(selectedRowFromDataTable[1]);
							   					$('#delivarableDescriptionId').val(selectedRowFromDataTable[2]);
							   					$('#asGrantedId').val(selectedRowFromDataTable[3]);
							   					$('#actualId').val(selectedRowFromDataTable[4]);
							   					$('#deviationId').val(selectedRowFromDataTable[5]);
							   					$('#phyEnteredRemarksId').val(selectedRowFromDataTable[6]);
							   					$('#phyrecommendedId').val(selectedRowFromDataTable[7]);
							   					$('#justificationId').val(selectedRowFromDataTable[8]);
							   					
								   					$('#physicalDelivarablesDivId').modal({backdrop: 'static',keyboard: false});
									   			
									   	//	}):
									   		
									   		//});
					   					
					   					
					   					
					   					
					   					
					   															
					   					});
					   		},
					   		error:function(e)
					   		{
					   		
					   		}
					   		});
			  	
					  	$.ajax({
					   		"url" : "/academicactivity/loadAllCompliances",
					   		"method" : "GET",
					   		data:{"settlementId":settlementId},
					   		success : function(data) {
					   			setUpDataTable(data,[{"complId":"Compliance id"},{"expHead":" Compliance to Administrative Sanction"},{"asGranted":"AS Granted"},{"actual":"Actual Expenditure"},{"deviation":"Deviation"},{"complianceJustification":"Justification"},{"complianceJustificationRemarks":"Justification Remarks"}],"complaingridTable","null");
					   			onDataTableClick('complaingridTable',function()
					   					{
					   					$('#complianceId').val(selectedRowFromDataTable[1]);
					   					$('#comExpHeadId').val(selectedRowFromDataTable[2]);
					   					$('#comAsGrantedId').val(selectedRowFromDataTable[3]);
					   					$('#comActualId').val(selectedRowFromDataTable[4]);
					   					$('#comDeviationId').val(selectedRowFromDataTable[5]);
					   					$('#comrecommendedId').val(selectedRowFromDataTable[6]);
					   					$('#comJustificationId').val(selectedRowFromDataTable[7]);
					   					$('#complianceDivId2').modal({backdrop: 'static',keyboard: false});
					   															
					   					});
					   		},
					   		error:function(e)
					   		{
					   		
					   		}
					   		}); 
					 	
					  	$.ajax({
					   		"url" : "/academicactivity/viewComplianceToProceduralRequirements",
					   		"method" : "GET",
					   		data:{"settlementId":settlementId},
					   		success : function(data) {
					   			setUpDataTable(data,[{"checkId":"CheckList Id"},{"complDesc":" Procedural Requirements"},{"included":"Yes/No"}],"proceduralRequirementsgridTable","null");
					   	onDataTableClick('proceduralRequirementsgridTable',function()
					   					{
					   		console.log("clicked proceduralRequirementsgridTable");
					   		//checkListid=selectedRowFromDataTable[0];
			   				$('#checkListId').val(selectedRowFromDataTable[1]);
			   				$('#complDescId').val(selectedRowFromDataTable[2]);
			   				$('#includedId').val(selectedRowFromDataTable[3]);
			   				$('#proceduralRequirementDivId1').modal({backdrop: 'static',keyboard: false}); 
					   															
					   					});
					   		},
					   		error:function(e)
					   		{
					   		
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
	
	loadAllExpenditureDetailsSubmitted();
	$( "#tabs" ).tabs({
	    activate: function (event, ui) {
	        console.log(ui.newPanel[0].id);
			var tabid = ui.newPanel[0].id;

			if(tabid == 'tabs-1')
			{
				
				
			}
			else if(tabid == 'tabs-2')
			{

			}
			else if(tabid == 'tabs-3')
			{
				
			}
			
			
	        
	    }
	});
	
	 $('#formAdvanceSetlementDetails').unbind().bind('submit',function(event) 
			   {
			   	        console.log("entrrr in formAdvanceSetlementDetails");  
			   			event.preventDefault();
			   			var formCheck = "success";
			   			var formData = $(this).serializeArray();
			   			formData.push({"name":"ac","value":activityId});
			   				$('#submitModel').modal('show');
			   
			   				$('#confId').unbind().bind('click',function(){
			   
			   					console.log("confirm clicked...");
			                     var i=0;
			   					$.ajax({
			   						"url" : "/academicactivity/saveAdvanceSettlementBasicDetails",
			   						"method" : "POST",
			   						 data : formData,
			   						 success : function(data) {
			   							 
			   							 console.log(formData);
			   							 
			   							advanceSettlementId=data['setId'];
			   							showMessage("Advance/Expenditure Sanction Master Details are success fully added with SetlementId"+advanceSettlementId);
			   							$("#formAdvanceSetlementDetails").trigger('reset');
			   						 },
			   						 error(e)
			   						 {
			   							showMessage("Error in approval. Contact Admin"); 
			   						 }
			   						 });
			   					$('#submitModel').modal('hide');
			   				});
			   				$('#cancelId').unbind().bind('click',function(){
			 				   
								console.log("Cancel clicked...");

								$('#submitModel').modal('hide');
								
							});
			})	
	
		 $('#AmountPaidBackForm').unbind().bind('submit',function(event) 
			   {
			   	        console.log("entrrr in AmountPaidBackForm");  
			   			event.preventDefault();
			   			var formCheck = "success";
			   			var formData = $(this).serializeArray();
			   			formData.push({"name":"ac","value":activityId});
			   			
			   			constrainError="could not execute statement; SQL [n/a]; constraint [uk_dv7ekuwotvvykbxcwxyqas0gl]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement";
			   			
			   			$.ajax({
	   						"url" : "/academicactivity/saveAmountPaidBacktoUniversityDetails",
	   						"method" : "POST",
	   						 data : formData,
	   						 success : function(data) {
	   							 
	   							console.log("data after getting saveAmountPaidBacktoUniversityDetails"+ data);
	   							 if(data==constrainError)
	   								 {
	   								 showMessage("VoucharNumer is already Exists");
	   								 }
	   							 else
	   								 {
	   								showMessage(data);
	   								 }
	   						//	advanceSettlementId=data['setId'];
	   					
	                               
	   						 },
	   						 error(e)
	   						 {
	   							showMessage("Error in approval. Contact Admin"); 
	   						 }
	   						 });
			   			
			   			
			   });
			
	 
	 $('#expenditureDetailsForm').unbind().bind('submit',function(event) 
			   {
			   	        console.log("entrrr in AmountPaidBackForm");  
			   			event.preventDefault();
			   			var formCheck = "success";
			   			var formData = $(this).serializeArray();
			   			formData.push({"name":"expId","value":expenditureId});
			   			
			   			constrainError="could not execute statement; SQL [n/a]; constraint [uk_dv7ekuwotvvykbxcwxyqas0gl]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement";
			   			$.ajax({
	   						"url" : "/academicactivity/updateExpenditureDetails",
	   						"method" : "POST",
	   						 data : formData,
	   						 success : function(data) {
	   							 
	   							console.log("data after getting ssaveExpenditureDetails"+ data);
	   							 if(data==constrainError)
	   								 {
	   								 showMessage("VoucharNumer is already Exists");
	   								 }
	   							 else
	   								 {
	   								showMessage(data);
	   								$("#expenditureDetailsForm").trigger('reset');
	   								 }
	   						//	advanceSettlementId=data['setId'];
	   					
	                               
	   						 },
	   						 error(e)
	   						 {
	   							showMessage("Error in approval. Contact Admin"); 
	   						 }
	   						 });
			   			
			   			
			   });
			
	 	$.ajax({
	   		"url" : "/expenditurehead/loadAllExpHeads",
	   		"method" : "GET",
	   		success : function(data) {
	   			console.log(data);
	   			setUpDropDown("expHeadNameId",data,"headId","expHeadName");
	   			
	   			for(var i=0;i<data.length;i++)
	   				{
	   				expHeadArray[i]=data[i]['expHeadName'];
	   				}
	   			
	   		},
	   		error : function(e) {
	   			//showMessage("Error in Getting Programs. Contact Admin");
	   		}
	   	});	
	 	
	 	
	 	vendorStatus="submitted";
		$.ajax({
	   		"url" : "/vendors/getAllVendorDetails",
	   		"method" : "GET",
	   		data:{"vendorStatus":vendorStatus},
	   		success : function(data) {
	   			console.log(data);
	   			setUpDropDown("payeeNameId",data,"vendorId","businessName");
	   		},
	   		error : function(e) {
	   			//showMessage("Error in Getting Programs. Contact Admin");
	   		}
	   	});	
			
		 $('#goodsReturnForm').unbind().bind('submit',function(event) 
				   {
				   	        console.log("entrrr in goodsReturnForm");  
				   			event.preventDefault();
				   			var formCheck = "success";
				   			var formData = $(this).serializeArray();
				   			formData.push({"name":"ac","value":advanceSettlementId});
				   			
				   			constrainError="could not execute statement; SQL [n/a]; constraint [uk_dv7ekuwotvvykbxcwxyqas0gl]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement";
				   			$.ajax({
		   						"url" : "/academicactivity/saveGoodsDetails",
		   						"method" : "POST",
		   						 data : formData,
		   						 success : function(data) {
		   							 
		   							console.log("data after getting saveGoodsDetails"+ data);
		   							 if(data==constrainError)
		   								 {
		   								 showMessage("VoucharNumer is already Exists");
		   								 }
		   							 else
		   								 {
		   								showMessage(data);
		   								 }
		   						//	advanceSettlementId=data['setId'];
		   					
		                               
		   						 },
		   						 error(e)
		   						 {
		   							showMessage("Error in approval. Contact Admin"); 
		   						 }
		   						 });
				   			
				   			
				   });	
			
		 
		 $('#remarkssForm').unbind().bind('submit',function(event) 
				   {
				   	        console.log("entrrr in remarkssForm");  
				   			event.preventDefault();
				   			var formCheck = "success";
				   			var bal= $('#balAmntId').val();
				   			var formData = $(this).serializeArray();
				   			formData.push({"name":"settlementId","value":settlementId});
				   			formData.push({"name":"sanctionedAmnt","value":bal})
				   			constrainError="could not execute statement; SQL [n/a]; constraint [uk_dv7ekuwotvvykbxcwxyqas0gl]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement";
				   			$.ajax({
		   						"url" : "/academicactivity/saveSettlementAdministrativeRemarks",
		   						"method" : "POST",
		   						 data : formData,
		   						 success : function(data) {
		   							 
		   							console.log("remarks added successfully added"+ data);
		   							 if(data==constrainError)
		   								 {
		   								 showMessage("VoucharNumer is already Exists");
		   								 }
		   							 else
		   							 {
		   								showMessage(data);
		   								 }
		   							
		   						//	advanceSettlementId=data['setId'];
		   					
		                               
		   						 },
		   						 error(e)
		   						 {
		   							showMessage("Error in saving remarkssForm. Contact Admin"); 
		   						 }
		   						 });
				   			
				   			
				   });	
		 
		 
		 $("#comAdminSanctinId" ).on('click',function(event) {
			 $.ajax({
					"url":"/expenditurehead/getActivityAdvanceRequest",
					"method":"GET",
					data:{"activityId":activityId,"finyear":finYear},
					success:function(data)
						{
              		    $('#comAdminSectionGridBody').empty();
						for(var i=0;i<data.length;i++)
						{
							var des=data[i]['actvtyExpHdkey']['exp']['expHeadsKey']['description'];//data[i]["expHeadsKey"]["description"];
							var advanceRequired=des+"advanceRequired";
							var requirementReason=des+"requirementReason";
							var sino=i+1;
			 				var asGrantedAdvanceAmnt=des+"asGrantedAdvanceAmnt";
			 				var actual1=des+"actual1";
			 				var deviation1=des+"deviation1"
			 				var totalHeadAmnt=0;
			 				if(data[i]['asGranted']>0)
			 					{
			 				  $.ajax({
			 					 "url" : "/academicactivity/getToalAmountExpenditureBySettlementIdAndHeadId",
							   		"method" : "GET",
							   		data:{"settlementId":settlementId,"exphead":des},
							   		async:false,
							   		success : function(data1) {
							   			console.log("rrrrrrrrrrrrrrrrr"+data1.length);
							   			for(var j=0;j<data1.length;j++)
							   				{
							   				console.log("rrrrrrrrrrrrrrrrr"+data1.length);
							   				console.log("totalAmtn"+data1[j]['totalAmt']);
							   				totalHeadAmnt=parseFloat(data1[j]['totalAmt'])+parseFloat(totalHeadAmnt);
							   				}
							   		//	
							   		},
							   		error:function(e)
							   		{
							   			
							   		}
							  });
			 				
			 				  
			 				  
			 				
			 				var deviation=parseFloat(data[i]['asGranted'])-totalHeadAmnt;
			 				

							 tr=$('<tr  class="compliance" support="'+des+'" asgrantAmntsupport="'+data[i]['asGranted']+'"    actualsupport="'+totalHeadAmnt+'"  deviationsupport="'+deviation+'" data-toggle="modal" data-target="#complianceDivId1"><td>'+sino+'</td><td>'+des+'</td><td><input  class="'+asGrantedAdvanceAmnt+'" value="'+data[i]['asGranted']+'"></input></td><td><input  class="'+actual1+'" value="'+totalHeadAmnt+'"></input></td><td><input   value="'+deviation+'" class="'+deviation1+'"></input></td><tr>');
							 $('#comAdminSectionGridBody').append(tr);
							  $(".compliance").on('click',function(event) {   
									
								 // alert($(this).attr('support'));
								  
								 $('#comExpHeadId').val($(this).attr('support'));
								 
								 
								
								
							
								$('#comAsGrantedId').val( $(this).attr('asgrantAmntsupport'));
								 $('#comActualId').val($(this).attr('actualsupport'));
								 $('#comDeviationId').val($(this).attr('deviationsupport'));
								 
								// $('#comAsGrantedId').val($('#'+asGrantId.val()));
								  
								  /* $('#comExpHeadId').val(des);
								  var val=$("#"+asGrantedAdvanceAmnt).val();
								  alert(asGrantedAdvanceAmnt);
								  $('#comAsGrantedId').val(val);
								   */
								 
										});
						}
							
						}   
						
						
					    },
					error(e)
						 {
							showMessage("Error in saving remarkssForm. Contact Admin"); 
						 }
						 });
			 
			
					
			
		 });
		 
		
		 $('#physicalDelivarblesForm').unbind().bind('submit',function(event) 
				   {
				   	        console.log("entrrr in remarkssForm");  
				   			event.preventDefault();
	 
	 var justification=$('#justificationId').val();
	 var expId=$('#delvId').val();
	 var recommended=$('#phyrecommendedId').val()
	 
	 alert(justification+"   "+expId+"  "+recommended);
			 
			
		 		 	 $.ajax({
					"url":"/academicactivity/editPhysicalDelivarableDetails",
					"method":"GET",
					data:{"expId":expId,"recom":recommended,"recomRemarks":justification},
					success:function(data)
						{
						
						showMessage(data);
						$("#physicalDelivarblesForm").trigger('reset');
						 	$.ajax({
					   		"url" : "/academicactivity/loadAllphysicalDelivarablesDetails",
					   		"method" : "GET",
					   		data:{"settlementId":settlementId},
					   		success : function(data) {
					   			setUpDataTable(data,[{"expId":"Deliverable Id"},{"delivarableDescription":"Physical Deliverables Vis a Vis Actual Performance"},{"asGranted":"AS Granted"},{"actual":"Actual Expenditure"},{"deviation":"Deviation"},{"physicalEnteredRemarks":"Remarks"},{"recommended":"Recommended"},{"justification":"Justification"}],"physicalDelivarablesgridTable","null");
					   			onDataTableClick('physicalDelivarablesgridTable',function()
					   					{
					   			$('#delvId').val(selectedRowFromDataTable[1]);
			   					$('#delivarableDescriptionId').val(selectedRowFromDataTable[2]);
			   					$('#asGrantedId').val(selectedRowFromDataTable[3]);
			   					$('#actualId').val(selectedRowFromDataTable[4]);
			   					$('#deviationId').val(selectedRowFromDataTable[5]);
			   					$('#phyEnteredRemarksId').val(selectedRowFromDataTable[6]);
			   					$('#phyrecommendedId').val(selectedRowFromDataTable[7]);
			   					$('#justificationId').val(selectedRowFromDataTable[8]);
			   					$('#physicalDelivarablesDivId').modal({backdrop: 'static',keyboard: false});		
					   									   					
								});
	
					   		},
							error(e)
							{
								
								
							}
							
			 			 });  
			
						},
						error(e)
						{
							
							
						}
						
		 			 });  
		 });
		 
		 
		 $('#complianceForm').unbind().bind('submit',function(event) 
				   {
				   	        console.log("entrrr in complianceForm");  
				   			event.preventDefault();
				   			var formData = $(this).serializeArray();
				   			formData.push({"name":"ac","value":settlementId});

		 		 	 $.ajax({
					"url":"/academicactivity/editCompliance",
					"method":"POST",
					data:formData,
					success:function(data)
						{
						
						
						showMessage(data);
						$("#complianceForm").trigger('reset');
						

					  	$.ajax({
					   		"url" : "/academicactivity/loadAllCompliances",
					   		"method" : "GET",
					   		data:{"settlementId":settlementId},
					   		success : function(data) {
					   			setUpDataTable(data,[{"complId":"Compliance id"},{"expHead":" Compliance to Administrative Sanction"},{"asGranted":"AS Granted"},{"actual":"Actual Expenditure"},{"deviation":"Deviation"},{"complianceJustification":"Justification"},{"complianceJustificationRemarks":"Justification Remarks"}],"complaingridTable","null");
					   			onDataTableClick('complaingridTable',function()
					   					{
					   					$('#complianceId').val(selectedRowFromDataTable[1]);
					   					$('#comExpHeadId').val(selectedRowFromDataTable[2]);
					   					$('#comAsGrantedId').val(selectedRowFromDataTable[3]);
					   					$('#comActualId').val(selectedRowFromDataTable[4]);
					   					$('#comDeviationId').val(selectedRowFromDataTable[5]);
					   					$('#comrecommendedId').val(selectedRowFromDataTable[6]);
					   					$('#comJustificationId').val(selectedRowFromDataTable[7]);
					   					$('#complianceDivId2').modal({backdrop: 'static',keyboard: false});
					   															
					   					});
					   		},
					   		error:function(e)
					   		{
					   		
					   		}
					   		});
						
						
						
						
						
						
						
						
						
						},
						error(e)
						{
							
							
						}
						
		 			 });  
		 });
		 
		 
		 $('#ProceduralcomplianceForm').unbind().bind('submit',function(event) 
				   {
				   	        console.log("entrrr in complianceForm");  
				   			event.preventDefault();
				   			var formData = $(this).serializeArray();
				   			formData.push({"name":"ac","value":settlementId});

		 		 	 $.ajax({
					"url":"/academicactivity/editProceduralcompliance",
					"method":"POST",
					data:formData,
					success:function(data)
						{
						
						
						showMessage(data);
						$("#ProceduralcomplianceForm").trigger('reset');
						viewProcedureComplaince();
						},
						error(e)
						{
							
							
						}
						
		 			 });  
		 });
		 
});
</script>
<div  id="gridRow" class="card card-info card-outline">
  	<div class="card-header">
		  <h3 > Expenditure Sanction / Settlement of Advance Details against Program</h3>
  	</div>
    <div class="card-body">
	   	<div class="table-responsive">
				<table class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border" id="advSettlementDetailsTable">
			 
				</table>
		</div>
	</div>
</div>

<div class="card card-info card-outline" id="expenditureAdvanceSettlement">
<div class="card-header">
   <h3>Edit of Administrative Approval For Expenditure Sanction / Settlement of Advance - Details of Bills , Vouchers and utilisation of Advance</h3>
</div>
<div class="card-body">
<div class="row">
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
<div id="tabs">
	  <ul>
	<li style="background-color:#265bab;text-color:white;"><a href="#tabs-1" style="color:#d5e6e4"> Settlement Details</a></li>
	<li style="background-color:#5a2066;text-color:white;"><a href="#tabs-2" style="color:#d5e6e4">Office Use</a></li>
	<li style="background-color:#3d5e48;text-color:white;"><a href="#tabs-3" style="color:#d5e6e4">Certification/ Recommendation/Remarks </a></li>

	  </ul>
	  
	   <div id="tabs-1">
	   <div id="formCenterActivityDetailsdiv">
	   <form id="formAdvanceSetlementDetails">
	    <div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
             
             <div class="row">
            <div class="form-group" style="width:100%">
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
							class="form-control" id="refNoId"  />
					    </div>
					     <div class="form-group">
                  <label for="activityType">Weather Advance Received for the Work/Project/Event</label>
                  <div>
              	              <select class="form-control" name="advanceObtained"
								id="advanceObtainedId" >
								<option value="se">Select</option>
								<option value="yes">Yes</option>
								<option value="no">No</option>
							</select>
							</div>
       		   </div>
       		   <div class="form-group">
                  <label for="settlmentType">Settlement Type<span
                  class="required">*</span></label>
                  <div>
              	              <select class="form-control" name="SettlementType"
								id="SettlementTypeId" required>
								<option value="">Select</option>
								<option value="expenditure">Expenditure Sanction</option>
								<option value="advance">Advance Settlement</option>
								<option value="finalsettlemnt">Final Settlement</option>
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
						<label for="asNo">If Yes  UO Number</label> 
						<input type="text" name="asNo"
							class="form-control" id="asNoId"  />
					   </div>
					
						<div class="form-group">
						<label for="asNo">Sanction Amount </label> 
						<input type="number" name="sancAmt"
							class="form-control" id="sancAmtId" value="0"/>
					  </div>
            </div>
            </div>
      <!--       <div class="row">
				<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group" style="text-align: center">
						 <button type="submit" id="AdvanceActivityMasterDetailsBt" class="btn btn-primary">Submit</button>
     			</div>
			</div>
			</div> -->
           </form>
           
	   </div>
	   <div id="advanceRecieveTabId">
          <div class="row">
	
	       <p>
             
                  <button class="btn btn-primary" type="button"
                  data-toggle="collapse" data-target="#AdvanceViewAdvReqFormView"
                  aria-expanded="false" aria-controls="collapseExample"
                  id="AdvanceViewAdvReqForm">Details of Advance(s) Received
               </button>
          <div class="collapse" id="AdvanceViewAdvReqFormView">
               <div class="card card-body">
                  <div class="table-responsive">
                      <div id="advnceRequestGrid" class="row">
                  <div class="col-md-12">
                     <div class="table-responsive cell-border">
                        <table id="gridTableMapper" class="table table-striped">
                           <thead>
                              <tr>
                                 <th scope="col">SI No</th>
                                 <th scope="col">Purpose for which advance Received</th>
                                 <th scope="col">cost Center Code</th>
                                 <th scope="col">Payment Vouchar Number</th>
                                 <th scope="col">Date</th>
                                 <th scope="col">Amount</th>
                                 
                              </tr>
                           </thead>
                           <tbody id="advnceRequestGridBody">
                           </tbody>
                        </table>
                     </div>
                  </div>
               </div>
                  </div>

										<div class="row">
											<div class="form-group">
												<label for="refNo">Total Advance Recieved</label> <input
													type="text" name="totalAdvance" class="form-control" id="totalAdvanceId" />
											</div>
										</div>



									</div>
            </div>
         </p>
        </div>
	   </div> 
	   
	     <div class="row">
	       <p>
	       <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#amountPaidBackView">
                  Details of any Amount Paid back to the University				
            </button>
               <div class="collapse" id="amountPaidBackView">
               <div class="card card-body">
                 <div id="advnceRequestGrid" class="row">
                  <div class="col-md-12">
                     <div class="table-responsive cell-border">
                        <table id="gridTableMapper" class="table table-striped">
                           <thead>
                              <tr>
                                 <th scope="col">SI No</th>
                                 <th scope="col">Description</th>
                                 <th scope="col">cost Center Code</th>
                                 <th scope="col">Reciept Vouchar Number</th>
                                 <th scope="col">Date</th>
                                 <th scope="col">Amount</th> 
                              </tr>
                           </thead>
                           <tbody id="paidBackGridBody">
                           </tbody>
                        </table>
                     </div>
                  </div>
               </div>
               <div class="row">
											<div class="form-group">
												<label for="refNo">Total Amount Paid Back</label> <input
													type="text" name="totalPaidBack
													" class="form-control" id="totalPaidBackId" />
											</div>
										</div>
               </div>
            </div>
	       </p>
	       </div>
	<!--      <div class="row">
	       <p>
	         <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#expenditureView">
                  Details of Expenditure			
             </button>
             <div class="collapse" id="expenditureView">
               <div class="card card-body">
                  <div class="table-responsive">
                     <table class="table table-striped" id="expendituregridTable"></table>
                  </div>
               </div>
										<div class="row">
											<div class="form-group">
												<label for="refNo">Total Expenditure</label> <input
													type="text" name="totalExpenditure" class="form-control" id="totalExpenditureId" />
											</div>
											<div class="form-group">
												<label for="refNo">Balance Amount to be Paid</label> <input
													type="text" name="balAmountTobePaid" class="form-control" id="balAmountTobePaidId" />
											</div>
										</div>
               
            </div>
	       </p>
	       </div> -->
	       
	        <div class="row">
            <p>
        <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#expenditureView" id="expenditureViewId">
                 View Details of Vendors Expenditure 		
             </button>
             
             
              <div class="collapse" id="expenditureView">
               <div class="card card-body">
                  <div class="table-responsive">
                     <table class="table table-striped" id="expendituregridTable"></table>
                  </div>
               </div>
										<div class="row">
											<div class="form-group">
												<label for="refNo">Total Vendors Expenditure</label> <input
													type="text" name="totalExpenditure" class="form-control" id="totalExpenditureId" />
											</div>
										<div class="form-group">
												<label for="refNo">Total Unpaid VendorExpenditure</label> <input
													type="text" name="balAmountTobePaid" class="form-control" id="balAmountTobePaidId" />
											</div> 
										</div>
										
										
										
               
            </div>
            </p>
            </div>
              <div class="row">
            <p>
        <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#expenditureViewRp" id="expenditureViewIdRp">
                 View Details of Resource Person Expenditure 		
             </button>
             
             
              <div class="collapse" id="expenditureViewRp">
               <div class="card card-body">
                  <div class="table-responsive">
                     <table class="table table-striped" id="expendituregridTableRp"></table>
                  </div>
               </div>
										<div class="row">
											<div class="form-group">
												<label for="refNo">Total Resource Person Expenditure</label> <input
													type="text" name="totalExpenditureRp" class="form-control" id="totalExpenditureRpId" />
											</div>
											<div class="form-group">
												<label for="refNo">Total Unpaid ResourceExpenditure</label> <input
													type="text" name="balAmountTobePaidRp" class="form-control" id="balAmountTobePaidIdRp" />
											</div>
										</div>
										
										
										
               
            </div>
            </p>
            </div>
	       
	       
	       
	       
	       
	       
	       
	       
	       
	           <div class="row">
	       <p>
	         <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#goodsReturnView">
                   Details of Goods Returned to the University				
             </button>
             <div class="collapse" id="goodsReturnView">
               <div class="card card-body">
                  <div class="table-responsive">
                     <table class="table table-striped" id="goodsReturngridTable"></table>
                  </div>
               </div>
            </div>
	       </p>
	       </div>
	   </div>
	   <div id="tabs-2">
        <div id="officeUseDiv">
          <div class="row">
	          <p>
              <button class="btn btn-primary" type="button"  id="comAdminSanctinId" data-toggle="collapse" data-target="#physicalDelivarablesView">Physical Deliverables Vis a Vis Actual Performance</button> 
              <div class="collapse" id="physicalDelivarablesView">
               <div class="card card-body">
                  <div class="table-responsive">
                     <table class="table table-striped" id="physicalDelivarablesgridTable"></table>
                  </div>
               </div>
            </div>
               </p>
           </div>
            <div class="row">
	             <p>
                 <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#complianceDivId">
                  Compliance to Administrative Sanction				
                 </button>
                 
                 
                   <div class="collapse" id="complianceDivId">
               <div class="card card-body">
                  <div class="table-responsive">
                     <table class="table table-striped" id="complaingridTable"></table>
                  </div>
               </div>
            </div>
                 
                 
                 
                 
                 
                 
                 
                 
                  <div class="collapse" id="complianceDivId1">
               <div class="card card-body">
                 	<form id="activityadvancePaymentForm" >
		         <div id="advnceRequestGrid" class="row">
                  <div class="col-md-12">
                     <div class="table-responsive cell-border">
                        <table id="gridTableMapper" class="table table-striped">
                           <thead>
                              <tr>
                                 <th scope="col">SI No</th>
                                 <th scope="col">Compliance to Administrative Sanction</th>
                                 <th scope="col">AS Granted</th>
                                 <th scope="col">Actual Expenditure</th>
                                 <th scope="col">Deviations</th>
                              </tr>
                           </thead>
                           <tbody id="comAdminSectionGridBody">
                           </tbody>
                        </table>
                     </div>
                  </div>
               </div>
               </form>
               </div>
            </div>
                 </p>
             </div>
                 <div class="row">
	              <p>
                  <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#proceduralRequirementsDiv">
                   Compliance to Procedural Requirements Check List		
                 </button>
         <div class="collapse" id="proceduralRequirementsDiv">
               <div class="card card-body">
                  <div class="table-responsive">
                     <table class="table table-striped" id="proceduralRequirementsgridTable"></table>
                  </div>
               </div>
            </div>
            </p>
      
                </div>
        </div>
	  </div>
	  
	  	  	  <div id="tabs-3">
           <div id="remarkssFormdiv">
           <form id="remarkssForm">
            
           <div class="row">
          <div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
          
           <div class="form-group">
              	<label for="enteredRemarks">Total Expenditure</label> 
              	   <input type="text" name="totalExpenseAmnt" class="form-control" id="totalExpenseAmntId" />
             </div>
               <div class="form-group">
              	<label for="enteredRemarks">Advance/External Funding(Not Settled)</label> 
              	   <input type="text" name="advanceTotal" class="form-control" id="advTotalId" />
              </div>
               <div class="form-group">
              	<label for="enteredRemarks">Amounts Paid Back to the University</label> 
              	   <input type="text" name="amntPaidBack" class="form-control" id="amntPaidBackId" />
              </div>
              <div class="form-group">
              	<label for="enteredRemarks">Balance Amount to be Paid</label> 
              	   <input type="text" name="balAmnt" class="form-control" id="balAmntId" />
              </div>
          
          
          
               <div class="form-group">
              	<label for="enteredRemarks"> Remarks</label> <textarea id="enteredRemarksId" name="enteredRemarks" rows="4" cols="50"
                  class="form-control"></textarea>
             </div>
             <div class="form-group">
              	<label for="enteredRemarks">Section Officer Remarks</label> <textarea id="verifiedL1RemarksId" name="officeRecommendationRemark" rows="4" cols="50"
                  class="form-control"></textarea>
             </div>
          <div class="form-group">
               <label for="activityLevel">Administrative Approval<span
                  class="required">*</span></label>
               <div style="width: 100%">
                  <div style="display: inline-block; width: 100%">
                     <select class="form-control selectpicker" name="recommendationStatus"
                        id="activityLevelId" required>
                        <option value="">Select</option>
                        <option value="AdministrativeApproved">Approve</option>
                        <option value="AdministrativeRejected">Reject</option>
                        
                     </select>
                  </div>
               </div>
            </div>
        

             <div class="form-group">
              	<label for="enteredRemarks">Administrative Approval Remarks</label> <textarea id="adminRecommendationRemarkId" name="adminRecommendationRemark" rows="4" cols="50"
                  class="form-control"></textarea>
             </div>


          </div>
          </div>
          <div class="row">
				<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group" style="text-align: center">
						 <button type="submit" id="remarkssFormBt" class="btn btn-primary">Submit</button>
     			</div>
			</div>
			</div>
          </form>
          </div>
        </div>
	 
	  </div>
	  </div>
	  </div>
	  </div>
	  </div>
	 
	 

	  <!-- Details of any Amount Paid back to the University Details -->
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
            <form id="AmountPaidBackForm">
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
                     <div class="form-group">
                        <label for="activitytype"> Remarks</label> <input type="text"
                           name="enteredRemarks" class="form-control" id="enteredRemarksId"  />
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



<!-- Details of Expenditure Details -->

     <!-- Modal HTML End -->
<!-- Modal Resource Person-->
<div class="modal fade" id="expenditureDetailsFormId" tabindex="-1"
   role="dialog" aria-labelledby="exampleModalLongTitle"
   aria-hidden="true">
   <div class="modal-dialog modal-lg" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLongTitle">Details of Expenditure
            </h5>
            <button type="button" class="close" data-dismiss="modal"
               aria-label="Close">
            <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="modal-body">
           <form id="expenditureDetailsForm">
        <div class="row" id="advReqFormId">
		<div class=" col-lg-6 col-md-6 col-sm-12 col-xs-12">
				
			<div class="form-group" style="width: 100%">
               <label for="expendHead">Expenditure Head<span
                  class="required">*</span></label>
               <div style="width: 100%">
                  <div style="display: inline-block; width: 100%">
                     <select class="form-control selectpicker" name="headId"
                        id="expHeadNameId" required>
                     </select>
                  </div>
               </div>
            </div>
				
				<div class="form-group">
						<label for="activitytype"> Bill No / Voucher No</label> <input
							type="text" name="billNo" class="form-control" id="billNoId" 	required />
					</div>
					<div class="form-group">
						<label for="expAmntId"> Value Amount</label> <input
							type="number" name="expAmnt" class="form-control" id="expAmntId" 	 />
					</div>
					<div class="form-group">
						<label for="activitytype">Tax amount</label>  <input
							type="number" name="tax" class="form-control" id="taxId"/>
					</div>
					<div class="form-group">
						<label for="activitytype">Total Amount</label> <input
							type="text" name="totalAmt" class="form-control" id="totalAmtId" 	required />
					</div>
	
							<div class="form-group" style="width: 100%">
                            <label for="vedorid">Payee Name<span
                                         class="required">*</span></label>
                                      <div style="width: 100%">
                                     <div style="display: inline-block; width: 100%">
                                   <select class="form-control selectpicker" name="vendorId"
                                  id="payeeNameId" required>
                                </select>
                              </div>
                           </div>
                        </div>
			
				</div>
				<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-12">
				     <div class="form-group">
						<label for="gstNO">GST No </label><input
							type="text" name="gstNO" class="form-control" id="gstNoId" 	 />
				      </div>
				
				         <div class="form-group">
						<label for="panNo">PAN No</label> <input
							type="text" name="panNo" class="form-control" id="panNoId" 	 />
				      </div>
				      
				       <div class="form-group">
						<label for="activitytype">Paid Status</label> <input
							type="text" name="paidStatus" class="form-control" id="paidStatusId" 	 />
				      </div>
				       <div class="form-group">
						<label for="activitytype">Mode of Payment</label> <input
							type="text" name="modePayment" class="form-control" id="modePaymentId" 	 />
				      </div>
				      <div class="form-group">
						<label for="activitytype">Admissible Amount</label> <input
							type="text" name="admsAmt" class="form-control" id="admsAmtId" />
				      </div>
			     
				</div>
				</div>
		   <div class="row">
			<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
		    <div class="form-group">
              	<label for="activitytype">Remarks</label> <textarea id="remarksId" name="enteredRemarks" rows="4" cols="50"
                  class="form-control"></textarea>
            </div>
            </div>
            </div>
			<div class="row">
				<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group" style="text-align: center">
						 <button type="submit" id="expenditureDetailsBt" class="btn btn-primary">Submit</button>
     			</div>
			</div>
			</div>

		</form>
         </div>
      </div>
   </div>
</div>

<!-- datails of goods -->
   <!-- Modal HTML End -->
<!-- Modal Resource Person-->
<div class="modal fade" id="goodsReturnDivId" tabindex="-1"
   role="dialog" aria-labelledby="exampleModalLongTitle"
   aria-hidden="true">
   <div class="modal-dialog" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLongTitle">Details of Goods returned to the University
            </h5>
            <button type="button" class="close" data-dismiss="modal"
               aria-label="Close">
            <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="modal-body">
           <form id="goodsReturnForm">
               <div class="row" id="goodsReturnId">
		       <div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="itemId">Item</label> <input
							type="text" name="item" class="form-control" id="itemId" 	required />
					</div>
				
				
				<div class="form-group">
						<label for="valueId">Value</label> <input
							type="number" name="value" class="form-control" id="valueId" 	required />
					</div>
					<div class="form-group">
						<label for="expAmntId"> Value Amount</label> <input
							type="number" name="qty" class="form-control" id="qtyId" 	 />
					</div>
					
				</div>
				</div>
		   <div class="row">
			<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
		    <div class="form-group">
              	<label for="activitytype">Remarks</label> <textarea id="goodsRemarksId" name="goodsRemarks" rows="4" cols="50"
                  class="form-control"></textarea>
            </div>
            </div>
            </div>
			<div class="row">
				<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group" style="text-align: center">
						 <button type="submit" id="goodsDetailssBt" class="btn btn-primary">Submit</button>
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
<!-- Modal Resource Person-->
<div class="modal fade" id="physicalDelivarablesDivId" tabindex="-1"
   role="dialog" aria-labelledby="exampleModalLongTitle"
   aria-hidden="true">
   <div class="modal-dialog" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLongTitle">Physical Delivarables Expenditure Details
            </h5>
            <button type="button" class="close" data-dismiss="modal"
               aria-label="Close">
            <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="modal-body">
           <form id="physicalDelivarblesForm">
               <div class="row" id="physicalDelivarblesFormId">
		       <div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">	
		       <div class="form-group">
						<label for="valueId">Physical Deliverable Id</label> <input
							type="number" name="expId" class="form-control" id="delvId" 	required />
					</div>
		            <div class="form-group">
						<label for="valueId">Physical Deliverables</label> <input
							type="text" name="delivarableDescription" class="form-control" id="delivarableDescriptionId" 	required />
					</div>
				
					<div class="form-group">
						<label for="valueId">AsGranted Amount</label> <input
							type="number" name="asGranted" class="form-control" id="asGrantedId" 	required />
					</div>
				
					<div class="form-group">
						<label for="expAmntId">Actual Expenditure</label> <input
							type="actualId" name="actual" class="form-control" id="actualId"/>
					</div>
					<div class="form-group">
						<label for="deviationId">Deviation</label> <input
							type="number" name="deviation" class="form-control" id="deviationId"/>
					</div>
					<div class="form-group">
						<label for="phyEnteredRemarksId">Remarks</label> 
						  <textarea id="phyEnteredRemarksId"" name="physicalEnteredRemarks" rows="4" cols="50"
                         class="form-control"></textarea>
					</div>
					<div class="form-group" style="width: 100%">
                            <label for="vedorid">Justification<span
                                         class="required">*</span></label>
                                      <div style="width: 100%">
                                     <div style="display: inline-block; width: 100%">
                                   <select class="form-control selectpicker" name="recommended"
                                  id="phyrecommendedId" required>
                                  <option value="">Select</option>
                                  <option value="yes">Yes</option>
                                  <option value="No">No</option>
                                  
                                </select>
                              </div>
                           </div>
                        </div>
					<div class="form-group">
						<label for="recomRemarksId">Recommendation Remarks</label> 
						  <textarea id="justificationId" name="justification" rows="4" cols="50"
                         class="form-control"></textarea>
					</div>
				</div>
				</div>
			<div class="row">
				<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group" style="text-align: center">
						 <button type="submit" id="physicalDelivarblesFormBt" class="btn btn-primary">Submit</button>
     			</div>
			</div>
			</div>

		</form>
         </div>
      </div>
   </div>
</div>

<div class="modal fade" id="complianceDivId2" tabindex="-1"
   role="dialog" aria-labelledby="exampleModalLongTitle"
   aria-hidden="true">
   <div class="modal-dialog" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLongTitle">Add Compliance to Administrative Sanction Details
            </h5>
            <button type="button" class="close" data-dismiss="modal"
               aria-label="Close">
            <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="modal-body">
           <form id="complianceForm">
               <div class="row" id="complianceFormId">
		       <div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">	
		       
		         <div class="form-group">
						<label for="valueId">Compliance Id</label> <input
							type="text" name="complId" class="form-control" id="complianceId" 	required />
					</div>
		       <div class="form-group">
						<label for="valueId">Compliance to Administrative Sanction</label> <input
							type="text" name="expHead" class="form-control" id="comExpHeadId" 	required />
					</div>
		            <div class="form-group">
						<label for="asGrantedId">As Granted</label> <input
							type="text" name="asGranted" class="form-control" id="comAsGrantedId" 	required />
					</div>
				
					<div class="form-group">
						<label for="valueId">Actual Expenditure</label> <input
							type="text" name="actual" class="form-control" id="comActualId" 	required />
					</div>
				
					<div class="form-group">
						<label for="deviationId">Deviation</label> <input
							type="number" name="deviation" class="form-control" id="comDeviationId"/>
					</div>
					<div class="form-group" style="width: 100%">
                            <label for="vedorid">Justification<span
                                         class="required">*</span></label>
                                      <div style="width: 100%">
                                     <div style="display: inline-block; width: 100%">
                                   <select class="form-control selectpicker" name="complianceJustification"
                                  id="comrecommendedId" required>
                                  <option value="">Select</option>
                                  <option value="yes">Yes</option>
                                  <option value="No">No</option>
                                  
                                </select>
                              </div>
                           </div>
                        </div>
					<div class="form-group">
						<label for="recomRemarksId">Recommendation Remarks</label> 
						  <textarea id="comJustificationId" name="complianceJustificationRemarks" rows="4" cols="50"
                         class="form-control"></textarea>
					</div>
				</div>
				</div>
			<div class="row">
				<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group" style="text-align: center">
						 <button type="submit" id="comPhysicalDelivarblesFormBt" class="btn btn-primary">Submit</button>
     			</div>
			</div>
			</div>

		</form>
         </div>
      </div>
   </div>
</div>


<div class="modal fade" id="proceduralRequirementDivId1" tabindex="-1"
   role="dialog" aria-labelledby="exampleModalLongTitle"
   aria-hidden="true">
   <div class="modal-dialog" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLongTitle">Compliance to Procedural Requirements Check List
            </h5>
            <button type="button" class="close" data-dismiss="modal"
               aria-label="Close">
            <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="modal-body">
           <form id="ProceduralcomplianceForm">
               <div class="row" id="proceduralRequirementId">
		       <div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">	
		              <div class="form-group">
						<label for="asGrantedId">CheckList Id</label> <input
							type="text" name="checkId" class="form-control" id="checkListId" 	required />
					</div>
		        <div class="form-group">
						<label for="asGrantedId">Procedural Requirements Description</label> <input
							type="text" name="complDesc" class="form-control" id="complDescId" 	required />
					</div>
		     	<div class="form-group">
						<label for="activitytype">Procedural Requirements is Available</label>
						<div>
							<select class="form-control" name="included"
								id="includedId">
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
					<div class="form-group" style="text-align: center">
						 <button type="submit" id="proceduralRequirementFormBt" class="btn btn-primary">Submit</button>
     			</div>
			</div>
			</div>

		</form>
         </div>
      </div>
   </div>
</div>



