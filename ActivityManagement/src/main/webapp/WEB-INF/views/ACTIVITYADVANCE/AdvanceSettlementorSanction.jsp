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
var activityId;
var advanceSettlementId;
var constrainError;
var vendorStatus;
var vendorStatus;
var centerArrayString="";
var resamnt=0;
var benamnt=0;
var finYear;
var totalExpAmt=0;
var totalExpAmt1=0;
var totalPaidBack1=0;
var totalAdvancePaid1=0;
var totalAdvanceSettled=0;
var remainingpaid=0;
var advrecieved="";

var totalexpprint=0;
var advTotalIdPrint=0;
var amntPaidBackIdPrint=0;
var amntPaidBackIdPrint=0;
var balAmntIdPrint=0;
//var totalAdvancePaid1=0;

function getTotalProgramExpense()
{
	$.ajax({
   		"url" : "/academicactivity/loadAllExpenditureBySettlementId",
   		"method" : "GET",
   		data:{"settlementId":advanceSettlementId},
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

function reportPrinting(divId)
{
	 //  console.log(divId);
	   $('#reportDiv').show();
    var divToPrint = document.getElementById(divId);
    var htmlToPrint = '' +
        '<style type="text/css">' +
        'table th, table td {' +
     
        'padding:0.5em;' +
        '} table{width:100%}' +
        '</style>';
    htmlToPrint += divToPrint.outerHTML;
    var newWin = window.open("");
    newWin.document.write('<html style="width:794px; height:1122px;"><head><title>National University of Advanced Legal Studies</title>');
    newWin.document.write(htmlToPrint);
    newWin.print();
    newWin.close();	
    
    $('#reportDiv').hide();
}

function loadAllAcademicActivityTypeDetails() {
	$.ajax({
		"url" : "/academicactivity/getAllActivityfinallyApprovedByUser",
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
						finYear=selectedRowFromDataTable[4]
						
						
					 	$.ajax({
					   		"url" : "/expenditurehead/loadAllEstimateHeads",
					   		"method" : "GET",
					   		data:{"activityCode":activityId},
					   		success : function(data) {
					   			console.log(data);
					   			setUpDropDown("expHeadNameId",data,"head_id","exp_head_name");
					   		},
					   		error : function(e) {
					   			//showMessage("Error in Getting Programs. Contact Admin");
					   		}
					   	});	
						
						$.ajax({
					   		"url" : "/expenditurehead/loadAllEstimateHeads",
					   		"method" : "GET",
					   		data:{"activityCode":activityId},
					   		success : function(data) {
					   			console.log(data);
					   			setUpDropDown("expHeadNameId1",data,"head_id","exp_head_name");
					   		},
					   		error : function(e) {
					   			//showMessage("Error in Getting Programs. Contact Admin");
					   		}
					   	});	
						
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
								$('#centerdiv').empty();
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
										    $('#asNoPt').val(data['asNO']);
										    
										    $('#asNoId').val(data['asNO']);
											$('#asNoId').attr('disabled',true); 
										    
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
							asSanctionAmnt=data['totalAsgranted'];
							console.log("assanctionamntoooooooooooooo"+asSanctionAmnt);
							$("#sancAmtId").val(asSanctionAmnt);
							
							resamnt=data['univResTravel']+data['localResTravel']+data['outstnResTravel']+data['intNatResTravel'];
							benamnt=data['univBenTravel']+data['localBenTravel']+data['outstnBenTravel']+data['intNatBenTravel'];
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
					   				
					   				advrecieved="no";
					   				}
					   			if(advanceRecievedlength>0)
					   				{
					   				$('#advanceObtainedId').val("yes");
					   				advrecieved="yes";
					   				}
					   			
					   			$('#advnceRequestGridBody').empty();
					   			
					   			var totalAdvancePaid=0;
					   			totalAdvancePaid1=0;
								for(var i=0;i<data.length;i++)
								{
									
									var sino=i+1;
									 tr=$('<tr class="'+sino+'" support="'+data[i]['programApprovalAdvancePaymentId']+'"><td>'+sino+'</td><td>'+data[i]['purpose']+'</td><td>'+centerArrayString+'</td><td>'+data[i]['voucharNo']+'</td><td>'+data[i]['voucharDate']+'</td><td>'+data[i]['advancePaid']+'</td><td>'+data[i]['settledAmount']+'</td></tr>');
									 $('#advnceRequestGridBody').append(tr);
									 $("."+sino).on('click',function(event) {
										// alert("entrrr");
										var advancePaymentId= $(this).attr('support');
									//	alert(advancePaymentId);
										 $.ajax({
												"url":"/academicactivity/getAdvancePaidByAdvancePaymentId",
												"method":"GET",
												data:{"advancePaymentId":advancePaymentId}, 
												success : function(data) {
													
												var advancetobesettled=data['advancePaid']-data['settledAmount'];
													if(advancetobesettled>0)
														{
													$('#advanceSettlementId').modal('show');
													$('#programApprovalAdvancePaymentId1').val(data['programApprovalAdvancePaymentId']);
													$('#purposeId11').val(data['purpose']);
													$('#voucharNoId11').val(data['voucharNo']);
													$('#voucharDateId11').val(data['voucharDate']);
													$('#advancePaidId11').val(data['advancePaid']);
													$('#settledAmountid11').val(data['settledAmount']);
														}
													
												
													
												},
												
												error : function(e) {
										   			//showMessage("Error in Getting Programs. Contact Admin");
										   		}
										   	});
										 
										 
										 
									 });
									
									 totalAdvancePaid=parseFloat(data[i]['advancePaid'])+parseFloat(totalAdvancePaid);
									 totalAdvanceSettled=parseFloat(data[i]['settledAmount'])+parseFloat(totalAdvanceSettled);
									
								}  
								
								
								remainingpaid=parseFloat(totalAdvancePaid)-parseFloat(totalAdvanceSettled);
								
								totalAdvancePaid1=totalAdvancePaid;
								$('#totalAdvanceId').val(totalAdvancePaid);
					   			
					   		},
					   		error : function(e) {
					   			//showMessage("Error in Getting Programs. Contact Admin");
					   		}
					   	});

					 	$.ajax({
							"url":"/expenditurehead/getActivityExpHeadExpensesByActivityIdAndFinyear",
							"method":"GET",
							data:{"activityId":activityId,"finyear":finYear},
							success:function(data)
								{
								console.log(data);
								
							 
								},
								error:function(e)
								{
								
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
						
					/* 	$.ajax({
					   		"url" : "/vendors/getAllVendorDetails",
					   		"method" : "GET",
					   		data:{"vendorStatus":vendorStatus},
					   		success : function(data) {
					   			console.log(data);
					   			setUpDropDown("payeeNameId1",data,"vendorId","businessName");
					   		},
					   		error : function(e) {
					   			//showMessage("Error in Getting Programs. Contact Admin");
					   		}
					   	});	 */
					/* 	
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
					   	});	 */
					 	
					 	
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
			else if(tabid == 'tabs-4')
			{
				
			}
			
	        
	    }
	});
	
	$('.dp').datepicker({
   	    format: 'yyyy-mm-dd',							   
   		autoclose:true,
   		clearBtn:true
   	});
	
	
	  $("#advancerows").click(
		        function(e){
		            //alert("Clicked on row");
		      
		        });
	  
	  
	 $('#pdelivarableDescriptionId').unbind().bind('change',function()
		        {
                var pdval=$(this).val();
                if(pdval=="ResourcePersons")
                	{
                	
                	$("#asGrantedId").val(resamnt);
                	}
                
                if(pdval=="Beneficaries")
            	{
            	
            	$("#asGrantedId").val(benamnt);
            	}
		        });
	 
	 
/* 	 $('#payeeNameId').on('change', function() {
		 if($('#rpId').is(':checked'))
	   				 {
						  var cat = $(this).val();
						  	$.ajax({
					   				"url":"/resource/getResourcepersondetailofById",
					   				"method":"GET",
					   				 data : {"resId":resId},
					   				success:function(data1)
					   				{
									if(data1['panNo']!=null);
									{
										$('#panNoId').va;(data1['panNo']);
										}
										if(data1['passportNo']!=null);
									   {
										$('#passportNoId').va;(data1['passportNo']);
										}
										
										passportNoId
									},
									error : function(e) {
  			//showMessage("Error in Getting Programs. Contact Admin");
  		}
   	});	 
					 }
				 if($('#vpId').is(':checked'))
   				 {

	    $.ajax({
	  		"url" : "/vendors/getDetailsOfVendorById",
	  		"method" : "GET",
	  		data:{"vendorId":vendorId},
	  		success : function(data) {
	  			$('#gstNoId').val(data['gstNo']);
	                   $('#panNoId').val(data['panNo']);
	  		},
	  		error : function(e) {
	  			//showMessage("Error in Getting Programs. Contact Admin");
	  		}
	   	});

                  }				 
	
	}); */
	 
	$('#payeeNameId').on('change', function() {
		
		 var cat = $(this).val();
		 
		 if($('#rpId').is(':checked'))
	   				 {
						 
			 resId=cat;		  
						  
						  	$.ajax({
					   				"url":"/resource/getResourcepersondetailofById",
					   				"method":"GET",
					   				 data : {"resId":resId},
					   				success:function(data1)
					   				{
									if(data1['panNo']!=null);
									{
										$('#panNoId').val(data1['panNo']);
										}
										if(data1['passportNo']!=null);
									   {
										$('#passportNoId').val(data1['passportNo']);
										}
										
									
									},
									error : function(e) {
 			//showMessage("Error in Getting Programs. Contact Admin");
 		}
  	});	 
					 }
				 if($('#vpId').is(':checked'))
  				 {
					 vendorId=cat;	 
	    $.ajax({
	  		"url" : "/vendors/getDetailsOfVendorById",
	  		"method" : "GET",
	  		data:{"vendorId":vendorId},
	  		success : function(data) {
	  			$('#gstNoId').val(data['gstNo']);
	                   $('#panNoId').val(data['panNo']);
	  		},
	  		error : function(e) {
	  			//showMessage("Error in Getting Programs. Contact Admin");
	  		}
	   	});

                 }				 
	
	});
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	  /*  	$('#payeeNameId').on('change', function() {
	   	    var vendorId= $(this).val();
		     $.ajax({
	  		"url" : "/vendors/getDetailsOfVendorById",
	  		"method" : "GET",
	  		data:{"vendorId":vendorId},
	  		success : function(data) {
	  			$('#gstNoId').val(data['gstNo']);
	                   $('#panNoId').val(data['panNo']);
	  		},
	  		error : function(e) {
	  			//showMessage("Error in Getting Programs. Contact Admin");
	  		}
	   	});
	   	}); */
	
	 $('#formAdvanceSetlementDetails').unbind().bind('submit',function(event) 
			   {
			   	        console.log("entrrr in formAdvanceSetlementDetails");  
			   			event.preventDefault();
			   			$('#asNoId').attr('disabled',false); 
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
			   							
			   							$("#AdvanceActivityMasterDetailsBt").prop("disabled",true);
			   						
			                               
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
			   			formData.push({"name":"setId","value":advanceSettlementId});
			   			
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
	   								$("#AmountPaidBackForm").trigger('reset');
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
		  event.preventDefault();
		  var formCheck = "success";
		  var resourceId;
		  var vendorId;
			    var formData = $(this).serializeArray();
	   			formData.push({"name":"ac","value":advanceSettlementId});
	   			
	   			//var vendortype=$('#rpId').val();
	   			//console.log("vendortypeeeeeeeeee"+vendortype)
	   			 if($('#rpId').is(':checked'))
	   				 {
	   				console.log("resourceprsong is checked");
	   		    	resourceId=$('#payeeNameId').val();
	   				formData.push({"name":"resourceId","value":resourceId});
	   				formData.push({"name":"vendorType","value":"resource"});
	   				 }
	   			
	   			 if($('#vpId').is(':checked'))
   				 {
   				console.log("resourceprsong is checked");
   				resourceId=$('#payeeNameId').val();
   				formData.push({"name":"vendorId","value":resourceId});
   				formData.push({"name":"vendorType","value":"vendor"});
   				 }
   			
	   			
	   	   	 
	   			
		    var asamount=$('#sancAmtId').val();
			var totalPaidbackuniversity=$('#totalPaidBackId').val();
			var paidstatus=$('#paidStatusId').val();
		     var currentexp=0;
		     var sumpaid=0;
	  	      $.ajax({
		   		"url" : "/academicactivity/loadAllExpenditureBySettlementId",
		   		"method" : "GET",
		   		data:{"settlementId":advanceSettlementId},
		   		success : function(data) {
		   			var totalExpAmnt=0;
		   			var balAmountTobePaid=0;
					var currentTotal=0;
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
					 currentTotal=parseFloat($('#totalAmtId').val())+parseFloat(totalExpAmnt);
					
				var tobepaidactual=parseFloat(remainingpaid)-parseFloat(totalPaidbackuniversity);
				var actualUnpaid=parseFloat(totalExpAmnt)-parseFloat(tobepaidactual);		
		
				paidstatus=	$('#paidStatusId').val();
		         if(paidstatus=='paid')
			         { 
				           currentexp=currentexp+parseFloat($('#totalAmtId').val())+sumpaid;
			         }	
	                  if(currentTotal>asamount)
				   			{
				   			showMessage("total amount of expenditure  should not be exceed  as granted amount");
				   			}
	
							else if(currentexp>tobepaidactual)
				   				{
				   				showMessage("total amount of expenditure paid and advance amount to be settled cannot be tallied");
				   				}	
							
							else{
								  var exphead=$('#expHeadNameId').text();
	   	        console.log("entrrr in AmountPaidBackForm");  
	   	   
	   	 
	   			//formData.push({"name":"expHead","value":advanceSettlementId});
	   			constrainError="could not execute statement; SQL [n/a]; constraint [uk_dv7ekuwotvvykbxcwxyqas0gl]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement";
	   			$.ajax({
						"url" : "/academicactivity/saveExpenditureDetails",
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
	   					
					}
		   		},
			
			   		
		});

			   });
	 
	 //for editing expenditure

	 	   $('#expenditureDetailsForm1').unbind().bind('submit',function(event) 
					   {
				          var currentamnt=0;
				          var asamount=$('#sancAmtId').val();
							var totalPaidbackuniversity=$('#totalPaidBackId').val();
							var paidstatus=$('#paidStatusId1').val();
						     var currentexp=0;
						     var sumpaid=0;
						     var expenditureId=$('#expId1').val();
                          event.preventDefault();
		                       var formCheck = "success";
		                       var formData = $(this).serializeArray();
			   			       formData.push({"name":"ac","value":settlementId});
		      
			   		    $.ajax({
					   		"url" : "/academicactivity/loadExpenditureByexpenditureId",
					   		"method" : "GET",
					   		data:{"expenditureId":expenditureId},
							success : function(data) {
							currentamnt=data['totalAmt'];
							console.log("current amount");

					 	      $.ajax({
					   		"url" : "/academicactivity/loadAllExpenditureBySettlementId",
					   		"method" : "GET",
					   		data:{"settlementId":settlementId},
					   		success : function(data) {
					   		
					   			
					   
					   			var totalExpAmnt=0;
					   			var balAmountTobePaid=0;
								var currentTotal=0;
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
					   			
								 currentTotal=parseFloat(totalExpAmnt)-parseFloat(currentamnt)+parseFloat($('#totalAmtId1').val());
								
							var tobepaidactual=parseFloat(remainingpaid)-parseFloat(totalPaidbackuniversity);
							
							console.log("tobepaidactuallllllllllll"+tobepaidactual);
							var actualUnpaid=parseFloat(totalExpAmnt)-parseFloat(tobepaidactual);		
					
							paidstatus=	$('#paidStatusId1').val();
					         if(paidstatus=='paid')
						         { 
							           currentexp=currentexp+parseFloat($('#totalAmtId1').val())+sumpaid-parseFloat(currentamnt);
							           console.log("sumpaidddddddd"+sumpaid+"CURRENTTOTAL"+currentTotal+"totalExpAmnt"+currentamnt
							        		   );
						         }	
				                  if(currentTotal>asamount)
							   			{
							   			showMessage("total amount of expenditure  should not be exceed  as granted amount");
							   			}
				
										else if(currentexp>tobepaidactual)
							   				{
							   				showMessage("total amount of expenditure paid and advance amount to be settled cannot be tallied");
							   				}	
										else{
										
											
											var vendorType=$('#vendorTypeId1').val();
											console.log("vendortype"+vendorType);
											var vendorid=$('#payeeNameId1').val();
											if(vendorType=='vendor')
												{
												formData.push({"name":"vendorId","value":vendorid});
												}
												if(vendorType=='resource')
												{
												formData.push({"name":"resourceId","value":vendorid});
												}
										$.ajax({
				   						"url" : "/academicactivity/editExpenditureDetails",
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
				   								$("#expenditureDetailsForm1").trigger('reset');
				   								 }
										},
				   						 error(e)
				   						 {
				   							showMessage("Error in approval. Contact Admin"); 
				   						 }
				   						 });

			                          }
			                         },
				   						 error(e)
				   						 {
				   							showMessage("Error in approval. Contact Admin"); 
				   						 }
				   						 });
							
							},
							   		error:function(e)
							   		{
							   			
							   		}
								});
				     
		                       });
	 
	 
	 
	 
	/*   $('#expenditureDetailsForm1').unbind().bind('submit',function(event) 
			   {
	                   event.preventDefault();
                       var formCheck = "success";
                       var formData = $(this).serializeArray();
	   			       formData.push({"name":"ac","value":advanceSettlementId});
                        var asamount=$('#sancAmtId').val();
			var totalPaidbackuniversity=$('#totalPaidBackId').val();
			var paidstatus=$('#paidStatusId1').val();
		     var currentexp=0;
		     var sumpaid=0;
	  	      $.ajax({
		   		"url" : "/academicactivity/loadAllExpenditureBySettlementId",
		   		"method" : "GET",
		   		data:{"settlementId":advanceSettlementId},
		   		success : function(data) {
		   			var totalExpAmnt=0;
		   			var balAmountTobePaid=0;
					var currentTotal=0;
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
					 currentTotal=parseFloat($('#totalAmtId1').val())+parseFloat(totalExpAmnt);
					
				var tobepaidactual=parseFloat(remainingpaid)-parseFloat(totalPaidbackuniversity);
				var actualUnpaid=parseFloat(totalExpAmnt)-parseFloat(tobepaidactual);		
		
				paidstatus=	$('#paidStatusId1').val();
		         if(paidstatus=='paid')
			         { 
				           currentexp=currentexp+parseFloat($('#totalAmtId1').val())+sumpaid;
			         }	
	                  if(currentTotal>asamount)
				   			{
				   			showMessage("total amount of expenditure  should not be exceed  as granted amount");
				   			}
	
							else if(currentexp>tobepaidactual)
				   				{
				   				showMessage("total amount of expenditure paid and advance amount to be settled cannot be tallied");
				   				}	
							else{
							
								
								var vendorType=$('#vendorTypeId1').val();
								console.log("vendortype"+vendorType);
								var vendorid=$('#payeeNameId1').val();
								if(vendorType=='vendor')
									{
									formData.push({"name":"vendorId","value":vendorid});
									}
									if(vendorType=='resource')
									{
									formData.push({"name":"resourceId","value":vendorid});
									}
							$.ajax({
	   						"url" : "/academicactivity/editExpenditureDetails",
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
	   								$("#expenditureDetailsForm1").trigger('reset');
	   								 }
							},
	   						 error(e)
	   						 {
	   							showMessage("Error in approval. Contact Admin"); 
	   						 }
	   						 });

                          }
                         },
	   						 error(e)
	   						 {
	   							showMessage("Error in approval. Contact Admin"); 
	   						 }
	   						 });

                       }); */
	 
	 
		/* 	
	 	$.ajax({
	   		"url" : "/expenditurehead/loadAllExpHeads",
	   		"method" : "GET",
	   		success : function(data) {
	   			console.log(data);
	   			setUpDropDown("expHeadNameId",data,"headId","expHeadName");
	   		},
	   		error : function(e) {
	   			//showMessage("Error in Getting Programs. Contact Admin");
	   		}
	   	});	 */
	 	
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
		   								$("#goodsReturnForm").trigger('reset');
		   								 }
		   						//	advanceSettlementId=data['setId'];
		   					
		                               
		   						 },
		   						 error(e)
		   						 {
		   							showMessage("Error in approval. Contact Admin"); 
		   						 }
		   						 });
				   			
				   			
				   });	
			
		 $('#physicalDelivarblesForm').unbind().bind('submit',function(event) 
				   {
				   	        console.log("entrrr in physicalDelivarblesForm");  
				   			event.preventDefault();
				   			var formCheck = "success";
				   			var formData = $(this).serializeArray();
				   			formData.push({"name":"ac","value":advanceSettlementId});
				   			
				   			constrainError="could not execute statement; SQL [n/a]; constraint [uk_dv7ekuwotvvykbxcwxyqas0gl]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement";
				   			$.ajax({
		   						"url" : "/academicactivity/physicalDelivarableDetails",
		   						"method" : "POST",
		   						 data : formData,
		   						 success : function(data) {
		   							 
		   							console.log("data after getting physicalDelivarblesForm"+ data);
		   							 
		   								showMessage(data);
		   								
		
		   								$("#physicalDelivarblesForm").trigger('reset');
		   						 },
		   						 error(e)
		   						 {
		   							showMessage("Error in saving physicalDelivarbles details. Contact Admin"); 
		   						 }
		   						 });
				   			
				   			
				   });	
		 $('#remarkssForm').unbind().bind('submit',function(event) 
				   {
			 
			 totalexpprint=$('#totalExpenseAmntId').val();
			 amntPaidBackIdPrint=$('#amntPaidBackId').val();
			 balAmntIdPrint=$('#balAmntId').val();
			 advTotalIdPrint=$('#advTotalId').val();
				   	        console.log("entrrr in remarkssForm");  
				   			event.preventDefault();
				   			var formCheck = "success";
				   			var formData = $(this).serializeArray();
				   			formData.push({"name":"settlementId","value":advanceSettlementId});
				   		  var sumPaid=0;
				   			$.ajax({
						   		"url" : "/academicactivity/loadAllExpenditureBySettlementId",
						   		"method" : "GET",
						   		data:{"settlementId":advanceSettlementId},
						   		success : function(data) {
						   		
						   			//totalExpAmt=0;
						   			//totalExpAmt1=0;
						   			var balAmountTobePaid=0;
						   			for(var i=0;i<data.length;i++)
						   				{
						   			//	totalExpAmt=parseFloat(data[i]['totalAmt'])+parseFloat(totalExpAmt);		   				
						   				if(data[i]['paidStatus']=='paid')
						   					{
						   					//balAmountTobePaid=parseFloat(data[i]['totalAmt'])+parseFloat(balAmountTobePaid);
						   				
						   					sumPaid=parseFloat(data[i]['totalAmt'])+sumPaid;
						   				
						   					}
						   				}
						   		//	var advancetobesettled=totalAdvancePaid1-totalAdvanceSettled;
						   		
						   		
						   		var totalExpnditure=$('#totalExpenseAmntId').val();
						   		
						   		var totalPaidbackuniversity=$('#totalPaidBackId').val();
						   		
						        var tobepaidactual=parseFloat(remainingpaid)-parseFloat(totalPaidbackuniversity);
						   
						        var actualUnpaid=parseFloat(totalExpnditure)-parseFloat(tobepaidactual);
							   
							    var comingunpaid=$('#balAmntId').val();
							    
							    var asamount=$('#sancAmtId').val();
							    
							    console.log("remainingpaid"+remainingpaid)
						   		
							    console.log("sumpaid="+sumPaid+"totalPaidbackuniversity="+totalPaidbackuniversity+"tobepaidactual="+tobepaidactual+"actualUnpaid="+actualUnpaid+"comingunpaid");
							    console.log("totalExpnditureeeeeeeee"+totalExpnditure+"asamount="+asamount);
							    
							    
							    
							    if((parseFloat(totalExpnditure))>(parseFloat(asamount)))
					   			{
					   			showMessage("total amount of expenditure should not be exceed  as granted amount");
					   			}
							   else if(sumPaid!=tobepaidactual)
						   				{
						   				showMessage("total amount of expenditure paid and advance amount to be settled cannot be tallied");
						   				}
						   	   else if(actualUnpaid!=comingunpaid)
						   			{
						   			showMessage("total amount of expenditure unpaid and advance amount  cannot be tallied");
						   			
						   			}
						   		
						   			
						   			/*if(sumPaid!=totalAdvanceSettled)
						   				{
						   				showMessage("total amount of expenditure paid and advance amount to be settled cannot be tallied");
						   				}*/
						   			else
						   				{
						   				constrainError="could not execute statement; SQL [n/a]; constraint [uk_dv7ekuwotvvykbxcwxyqas0gl]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement";
							   			$.ajax({
					   						"url" : "/academicactivity/saveSettlementRemarks",
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
						   				}
						   			console.log("sumpaidddd"+sumPaid);
						   		},
				   			
						   	 error(e)
	   						 {
	   							showMessage("Error in saving physicalDelivarbles details. Contact Admin"); 
	   						 }
	   						 });
				   			
				   			
				   });	

		 $('input[type=radio][name=vendorType1]').change(function() {
			    if (this.value == 'yes') {
			        	$.ajax({
								   		"url" : "/resource/getResourcepersondetailsById",
								   		"method" : "GET",
								   		data:{"activityId":activityId},
								   		success : function(data) {
								   			console.log(data);
								   			setUpDropDown("payeeNameId",data,"res_id","name");
								   		},
								   		error : function(e) {
								   			//showMessage("Error in Getting Programs. Contact Admin");
								   		}
								   	});	
			    }
			    else if (this.value == 'no') {
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
			    }
			});
		 
		 
		 
		 $('#advanceSettlementForm').unbind().bind('submit',function(event) 
				   {
			    console.log("entrrr in remarkssForm");  
	   			event.preventDefault();
	   			var formCheck = "success";
	   			var formData = $(this).serializeArray();
	   			formData.push({"name":"settlementId","value":advanceSettlementId});
	 			$.ajax({
						"url" : "/academicactivity/saveAdvanceSettlement",
						"method" : "POST",
						 data : formData,
						 success : function(data) {
							 
							console.log("advance is settled"+ data);
							 if(data==constrainError)
								 {
								 showMessage("VoucharNumer is already Exists");
								 }
							 else
								 {
								showMessage(data);
								
								$.ajax({
							   		"url" : "/academicactivity/getAllAdvanceRecieved",
							   		"method" : "GET",
							   		data:{"activityId":activityId},
							   		success : function(data) {
							   			var advanceRecievedlength=data.length;
						   			   $('#advnceRequestGridBody').empty();
							   			
							   			var totalAdvancePaid=0;
							   			totalAdvancePaid1=0;
										for(var i=0;i<data.length;i++)
										{
											
											var sino=i+1;
											 tr=$('<tr class="'+sino+'" support="'+data[i]['programApprovalAdvancePaymentId']+'"><td>'+sino+'</td><td>'+data[i]['purpose']+'</td><td>'+centerArrayString+'</td><td>'+data[i]['voucharNo']+'</td><td>'+data[i]['voucharDate']+'</td><td>'+data[i]['advancePaid']+'</td><td>'+data[i]['settledAmount']+'</td></tr>');
											 $('#advnceRequestGridBody').append(tr);
											 $("."+sino).on('click',function(event) {
												// alert("entrrr");
												var advancePaymentId= $(this).attr('support');
											//	alert(advancePaymentId);
												 $.ajax({
														"url":"/academicactivity/getAdvancePaidByAdvancePaymentId",
														"method":"GET",
														data:{"advancePaymentId":advancePaymentId}, 
														success : function(data) {
															
															
															var advancetobesettled=data['advancePaid']-data['settledAmount'];
															if(advancetobesettled>0)
																{
															$('#advanceSettlementId').modal('show');
															$('#programApprovalAdvancePaymentId1').val(data['programApprovalAdvancePaymentId']);
															$('#purposeId11').val(data['purpose']);
															$('#voucharNoId11').val(data['voucharNo']);
															$('#voucharDateId11').val(data['voucharDate']);
															$('#advancePaidId11').val(data['advancePaid']);
															$('#settledAmountid11').val(data['settledAmount']);
																}
															
														
															
														},
														
														error : function(e) {
												   			//showMessage("Error in Getting Programs. Contact Admin");
												   		}
												   	});
												 
												 
												 
											 });
											
											 totalAdvancePaid=parseFloat(data[i]['advancePaid'])+parseFloat(totalAdvancePaid);
											 totalAdvanceSettled=parseFloat(data[i]['settledAmount'])+parseFloat(totalAdvanceSettled);
											 console.log("totalAdvanceSettled"+totalAdvanceSettled+"totalAdvancePaid"+totalAdvancePaid);
											 
											 
											
										}  
										
										totalAdvancePaid1=totalAdvancePaid;
										remainingpaid=totalAdvancePaid-totalAdvanceSettled;
										$('#totalAdvanceId').val(totalAdvancePaid);
							   			
							   		},
							   		error : function(e) {
							   			//showMessage("Error in Getting Programs. Contact Admin");
							   		}
							   	});
								 }
						//	advanceSettlementId=data['setId'];
					
                           
						 },
						 error(e)
						 {
							showMessage("Error in saving remarkssForm. Contact Admin"); 
						 }
						 });
			 
				   });
		 
		 
		 //print 
		 
		  $('#totalAmntBt').unbind().bind('click',function()
				  {
			 var valueamnt= $("#expAmntId").val();
			 var tax=$("#taxId").val(); 
			 
			 var totalAmnt=parseFloat(valueamnt)+parseFloat(tax);
			 $('#totalAmtId').val(totalAmnt);
			  
				  });
			$("#amntpaidbackId" ).on('click',function(event) { 
				console.log("AdvanceViewAdvReqFormView");
					$.ajax({
			   						   		"url" : "/academicactivity/loadAllAmountPaidBackBySettlementId",
			   						   		"method" : "GET",
			   						   		data:{"settlementId":advanceSettlementId},
			   						   		success : function(data) {
			   	                              $('#paidBackGridBody').empty();
			   	                 		  var totalPaidBack=0;
			   	                   				for(var i=0;i<data.length;i++)
			   									{
			   										  var centreid=data[i]['costCenterCode'];
			   								
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
				
				
				});
			
			
			//to view the bill details added
			
				$("#expenditureViewId" ).on('click',function(event) { 
					$.ajax({
				   		"url" : "/academicactivity/loadAllExpenditureBySettlementIdByInteface",
				   		"method" : "GET",
				   		data:{"settlementId":advanceSettlementId},
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
			
			
				$("#expenditureViewIdRp" ).on('click',function(event) { 
					$.ajax({
				   		"url" : "/academicactivity/loadAllExpenditureBySettlementIdByIntefaceForResourcePerson",
				   		"method" : "GET",
				   		data:{"settlementId":advanceSettlementId},
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
												   			if(data1['panNo']!=null);
															{
																$('#panNoId1').val(data1['panNo']);
																}
																if(data1['passportNo']!=null);
															   {
																$('#passportNoId1').val(data1['passportNo']);
																}
												   			
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
			
			
			
				$("#goodsviewbutton" ).on('click',function(event) { 
					console.log("AdvanceViewAdvReqFormView");
			
		              $.ajax({
						   		"url" : "/academicactivity/loadAllGoodsReturnBySettlementId",
						   		"method" : "GET",
						   		data:{"settlementId":advanceSettlementId},
						   		success : function(data) {
						   			setUpDataTable(data,[{"item":"Name of Item Returned"},{"value":"Value"},{"qty":"Quantity"},{"goodsRemarks":"Remarks"}],"goodsReturngridTable","null");
						   		},
						   		error:function(e)
						   		{
						   		
						   		}
						   		});
								});
		
				$("#physicaldelivarbleviewbutton").on('click',function(event){ 
				
					$.ajax({
									   		"url" : "/academicactivity/loadAllphysicalDelivarablesDetails",
									   		"method" : "GET",
									   		data:{"settlementId":advanceSettlementId},
									   		success : function(data) {
									   			setUpDataTable(data,[{"expId":"Deliverable Id"},{"delivarableDescription":"Physical Deliverables Vis a Vis Actual Performance"},{"asGranted":"AS Granted"},{"actual":"Actual Expenditure"},{"deviation":"Deviation"},{"physicalEnteredRemarks":"Remarks"}],"physicalDelivarablesgridTable","null");
									   		/* 	onDataTableClick('physicalDelivarablesgridTable',function()
									   					{
									   					$('#delvId').val(selectedRowFromDataTable[1]);
									   					$('#delivarableDescriptionId').val(selectedRowFromDataTable[2]);
									   					$('#asGrantedId').val(selectedRowFromDataTable[3]);
									   					$('#actualId').val(selectedRowFromDataTable[4]);
									   					$('#deviationId').val(selectedRowFromDataTable[5]);
									   					$('#phyEnteredRemarksId').val(selectedRowFromDataTable[6]);
									   					$('#physicalDelivarablesDivId').modal({backdrop: 'static',keyboard: false});
									   															
									   					}); */
									   		},
									   		error:function(e)
									   		{
									   		
									   		}
									   		});
											});
			
			
	 
		 $('#printRep').unbind().bind('click',function()
					{
			 	$.ajax({
					"url" : "/academicactivity/getAllActivityById",
					"method" : "GET",						
					data : {"activityId":activityId},
					success : function(data) 
					{
						var d=data;
						centres=d["centres"];
						finyear=data['finyear'];
						for(var key in d)
						{
							$('#'+key).val(d[key]);
							$('#'+key).text(d[key]);
						}
						
								var status=data['status']
								if(status=="finalapproved")
									{
									asNo=data['asNO'];
									$("#asopt").val("yes");
								    $('#asNoPt').val(data['asNO']);
								    //$('#approvalObtainedId').val("yes");
									}
								
								  $("#centrestab tr").remove(); 
									
								  	$.ajax({
								   		"url":"/academicfinance/getAllactiviticenters",
								   		"method":"GET",
								   		data:{"activityId":activityId},
								   		success : function(data) {
								   			
								   			//console.log(data);
								   			var i;
								   			$('#centerLength').append(data.length);
								   			
								   			
								   			console.log("daaataaaaaaaaaa in getAllActivityById2"+data);
												$('#centrestab').append('<tr id="centertd">')
												 for(var i=0;i<data.length;i++)
											     {
													 centerarray[i]=data[i]['actvtyCenterKey']['cm']['centre_id'];
													 $('#centertd').append('<td>'+data[i]['actvtyCenterKey']['cm']['centre_name']+'</td>')	
											      }
												$('#centrestab').append('</tr>');
												console.log(centerarray);
											 	$.ajax({
													"url":"/academicactivity/loadAllExpenditureDetailsSubmittedBySettlementId",
													"method":"GET",
													data:{"settlementId":advanceSettlementId},
													success:function(dataexp)
													{
														
														for(var key in dataexp)
														{
															$('#'+key).append(dataexp[key]);
															
														}
														
														
														
											 	},
										   		error : function(e) {
										   			//showMessage("Error in Getting Programs. Contact Admin");
										   		}
										   	});	
								   			 },
								   			 error:function(e)
								   			 {
								   				 console.log("error"+e);
								   			 }
								   			 
								   			 });
								  	
								  	
								
							 	$.ajax({
									"url":"/academicfinance/getActivityFinance",
									"method":"GET",
									data:{"activityId":activityId},
									success:function(data)
										{
										asSanctionAmnt=data['totalEstExp'];
										console.log("assanctionamntoooooooooooooo"+asSanctionAmnt);
										$("#sancAmtIdPt").val(asSanctionAmnt);
										$('#advrecievedid').append(advrecieved);
										$('#advrecievedid').append(advrecieved);
										
										
										
										
										$.ajax({
									   		"url" : "/academicactivity/getAllAdvanceRecieved",
									   		"method" : "GET",
									   		data:{"activityId":activityId},
									   		success : function(data) {
									   			var advanceRecievedlength=data.length;
									   			
									   			
									   		 $('#advpttab').append('<thead><tr><th scope="col">Sl No</th><th scope="col">Purpose for which External Funding / advance Received<th scope="col">Cost Center Code</th><th scope="col">Payment Voucher No</th><th scope="col">Date</th><th scope="col">Amount</th>');
									   			
									   			if(advanceRecievedlength==0)
									   				{
									   				$('#advrecieve').append("no");
									   				}
									   			if(advanceRecievedlength>0)
									   				{
									   				$('#advrecieve').append("yes");
									   				}
									   			
									   		//	$('#advnceRequestGridBody').empty();
									   			
									   			var totalAdvancePaid=0;
									   			totalAdvancePaid1=0;
												for(var i=0;i<data.length;i++)
												{
													
													var sino=i+1;

													 tr=$('<tr id="advancerows"><td>'+sino+'</td><td>'+data[i]['purpose']+'</td><td>'+centerArrayString+'</td><td>'+data[i]['voucharNo']+'</td><td>'+data[i]['voucharDate']+'</td><td>'+data[i]['advancePaid']+'</td></tr>');
												
													 $('#advpttab').append(tr);
												}
													
													 tr=$('<tr><td>Total Advance / Funding Received</td><td>'+advTotalIdPrint+'</td></tr>');
													 $('#advpttab').append(tr);
													
												
												totalAdvancePaid1=totalAdvancePaid;
												$('#totalAdvanceId').val(totalAdvancePaid);
												$.ajax({
											   		"url" : "/academicactivity/loadAllAmountPaidBackBySettlementId",
											   		"method" : "GET",
											   		data:{"settlementId":advanceSettlementId},
											   		success : function(data1) {
						                            
						                              
						                              var totalPaidBack=0;
						                              $('#paidbackpt').append('<thead><tr><th scope="col">Sl No</th><th scope="col">Description </th> <th scope="col">Cost Center Code</th><th scope="col">Receipt Voucher No</th><th scope="col">Date</th><th scope="col">Amount</th>');
						                   				for(var i=0;i<data1.length;i++)
														{
															  var centreid=data[i]['costCenterCode'];
															 
										         		  $.ajax({
															   		"url" : "/academic/getCentreById",
															   		"method" : "GET",
															   		async:false,
															   		data:{"centre_id":centreid},
															   		success : function(data2) {
															   			
															   			crCode=data2['centre_code'];
															   			console.log("crcodeeeeeeeeeee"+crCode);
															   		},
															   		error:function(e)
															   		{
															   			
															   		}
															  });
															
															 var sino=i+1;
															 console.log("crcodeeeeeeeeeee"+crCode);
															
															var tr=$('<tr><td>'+sino+'</td><td>'+data1[i]['paidBackDescription']+'</td><td>'+crCode+'</td><td>'+data1[i]['voucharNo']+'</td><td>'+data1[i]['paymentDate']+'</td><td>'+data1[i]['amountPaidBack']+'</td><tr>');
															 $('#paidbackpt').append(tr);
															 
															
															// tr=$('<tr><td>Total </td><td>'+totalPaidBack+'</td></tr>');
															// $('#paidbackpt').append(tr);
														}  
						                   			 tr=$('<tr><td>Total </td><td>'+amntPaidBackIdPrint+'</td></tr>');
												       $('#paidbackpt').append(tr);
						                   				$.ajax({
													   		"url" : "/academicactivity/loadAllExpenditureBySettlementIdByInteface",
													   		"method" : "GET",
													   		data:{"settlementId":advanceSettlementId},
													   		success : function(data) {
													   			
													   			totalExpAmt=0;
													   			totalExpAmt1=0;
													   			var balAmountTobePaid=0;
													   		 $('#exppt').append('<thead><tr><th scope="col">Sl No</th><th scope="col">Bill No / Voucher No </th> <th scope="col">Value amount</th><th scope="col">Tax amount</th><th scope="col">Total Amount</th><th scope="col">Payee Name</th><th scope="col">GST No</th><th scope="col">PAN No</th><th scope="col">Status</th><th scope="col">Mode of Payment</th><th scope="col">Expenditure Head</th>');
													   			for(var i=0;i<data.length;i++)
													   				{
													   				totalExpAmt=parseFloat(data[i]['advtotal'])+parseFloat(totalExpAmt);		
													   				//expenseHead[i]=data[i]['expHeadName'];
													   								   				
													   				if(data[i]['paidstatus']=='notpaid')
													   					{
													   					balAmountTobePaid=parseFloat(data[i]['advtotal'])+parseFloat(balAmountTobePaid);
													   					}
													   				
													   			
													   			 var sino=i+1;
													   			 tr=$('<tr><td>'+sino+'</td><td>'+data[i]['billno']+'</td><td>'+data[i]['expamnt']+'</td><td>'+data[i]['taxAmnt']+'</td><td>'+data[i]['advtotal']+'</td><td>'+data[i]['payee']+'</td><td>'+data[i]['gstno']+'</td><td>'+data[i]['panno']+'</td><td>'+data[i]['paidstatus']+'</td><td>'+data[i]['modepayment']+'</td><td>'+data[i]['headname']+'</td><tr>');
													   			 $('#exppt').append(tr);
													   			
													   				}
													   			
													   			$.ajax({
															   		"url" : "/academicactivity/loadAllExpenditureBySettlementIdByIntefaceForResourcePerson",
															   		"method" : "GET",
															   		data:{"settlementId":advanceSettlementId},
															   		success : function(data) {
															   			
															   			for(var i=0;i<data.length;i++)
														   				{
														   				totalExpAmt=parseFloat(data[i]['advtotal'])+parseFloat(totalExpAmt);		
														   				//expenseHead[i]=data[i]['expHeadName'];
														   								   				
														   				if(data[i]['paidstatus']=='notpaid')
														   					{
														   					balAmountTobePaid=parseFloat(data[i]['advtotal'])+parseFloat(balAmountTobePaid);
														   					}
														   				
														   			
														   			 var sino=i+1;
														   			 tr=$('<tr><td>'+sino+'</td><td>'+data[i]['billno']+'</td><td>'+data[i]['expamnt']+'</td><td>'+data[i]['taxAmnt']+'</td><td>'+data[i]['advtotal']+'</td><td>'+data[i]['payee']+'</td><td>'+data[i]['gstno']+'</td><td>'+data[i]['panno']+'</td><td>'+data[i]['paidstatus']+'</td><td>'+data[i]['modepayment']+'</td><td>'+data[i]['headname']+'</td><tr>');
														   			 $('#exppt').append(tr);
														   			
														   				}
															   			
															   		 tr=$('<tr><td>Total Expenditure</td><td>'+	totalexpprint+'</td></tr>');
														   			 $('#exppt').append(tr);
														   			 tr=$('<tr><td>Advance / External Funding</td><td>'+advTotalIdPrint+'</td></tr>');
														   			 $('#exppt').append(tr);
														   			 tr=$('<tr><td>Amounts paid Back to the University</td><td>'+amntPaidBackIdPrint+'</td></tr>');
														   			 $('#exppt').append(tr);
														   			 tr=$('<tr><td>Balance Amount to be Paid</td><td>'+balAmntIdPrint+'</td></tr>');
														   			 $('#exppt').append(tr);
														   			 
														   		     $.ajax({
																	   		"url" : "/academicactivity/loadAllGoodsReturnBySettlementId",
																	   		"method" : "GET",
																	   		data:{"settlementId":advanceSettlementId},
																	   		success : function(data) {
																	   		 $('#goodspt').append('<thead><tr><th scope="col">Sl No</th><th scope="col">Name of Item returned </th> <th scope="col"> Value</th><th scope="col"> Qty</th><th scope="col">Remarks</th>');
																	   	
																	   			for(var i=0;i<data.length;i++)
																   				{
																	   		
																	   	 		 var sino=i+1;
																   			 tr=$('<tr><td>'+sino+'</td><td>'+data[i]['item']+'</td><td>'+data[i]['value']+'</td><td>'+data[i]['qty']+'</td><td>'+data[i]['goodsRemarks']+'</td><td><tr>');
																   			$('#goodspt').append(tr);
																   				}
																   			
																   		 reportPrinting('reportDiv');
																   			
																	   		},
																			error(e)
																			{
																					console.log(e);
																			}
																 	
																 	});					
									    					   			 
														   																		   			
															   		},
													   			
																error(e)
																{
																		console.log(e);
																}
													 	
													 	});			
													   		 
													   		//	setUpDataTable(data,[{"expId":"ExpenditureId"},{"billNo":"Bill No/Vouchar No"},{"expAmnt":"Value Amount"},{"tax":"Tax Amount"},{"totalAmt":"Total Amount"},{"businessName":"Payee Name"},{"gstNo":"GST NO"},{"panNo":"PAN NO"},{"paidStatus":"Status"},{"modePayment":"Mode of Payment"},{"expHeadName":"Expenditure Head"}],"expendituregridTable","null");
													 
														
											},
											error(e)
												{
														console.log(e);
												}
									 	
									 	});     
						                   				
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
																			
										},
										error(e)
										{
											console.log(e);
										}
										});
					},
					error(e)
						{
								console.log(e);
						}
			 
					});
		 
		 
					});//function ending
			
});
</script>
<div  id="gridRow" class="card card-info card-outline">
  	<div class="card-header">
		  <h3 >List Of Activities for Expenditure Sanction / Settlement of Advance</h3>
  	</div>
    <div class="card-body">
	   	<div class="table-responsive">
	   					<table class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border" id="financiallyApprovedActivityTable">
			 
				</table>
		</div>
	</div>
</div>

<div class="card card-info card-outline" id="expenditureAdvanceSettlement">
<div class="card-header">
   <h3>Expenditure Sanction / Settlement of Advance - Details of Bills , Vouchers and utilisation of Advance</h3>
</div>
<div class="card-body">
<div class="row">
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
<div id="tabs">
	  <ul>
	<li style="background-color:#265bab;text-color:white;"><a href="#tabs-1" style="color:#d5e6e4"> Settlement Details</a></li>
	<li style="background-color:#5a2066;text-color:white;"><a href="#tabs-2" style="color:#d5e6e4">Details of advance </a></li>
	<li style="background-color:#3d5e48;text-color:white;"><a href="#tabs-3" style="color:#d5e6e4">Details of Expenditure and Goods </a></li>
	<li style="background-color:#3d5e48;text-color:white;"><a href="#tabs-4" style="color:#d5e6e4">Remarks</a></li>

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
              	              <select class="form-control" name="settlementType"
								id="SettlementTypeId" required>
								<option value="">Select</option>
								<option value="expenditure">Expenditure Sanction</option>
								<option value="advance">Advance Settlement</option>
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
            <div class="row">
				<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group" style="text-align: center">
						 <button type="submit" id="AdvanceActivityMasterDetailsBt" class="btn btn-primary">Submit</button>
     			</div>
			</div>
			</div>
           </form>
	   </div>
	   </div> 
	   <div id="tabs-2">
        <div id="advanceRecieveTabId">
        <p>
               <div class="row">
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
                                 <th scope="col">Advance Payment Id</th>
                                 <th scope="col">Purpose for which advance Received</th>
                                 <th scope="col">cost Center Code</th>
                                 <th scope="col">Payment Vouchar Number</th>
                                 <th scope="col">Date</th>
                                 <th scope="col">Amount recieved</th>
                                  <th scope="col">Amount Settled</th>
                                 
                              </tr>
                           </thead>
                           <tbody id="advnceRequestGridBody">
                           </tbody>
                        </table>
                     </div>
                     
                  <div class="row">
						<div class="form-group">
							<label for="refNo">Total Advance Recieved</label> <input
													type="text" name="totalAdvance" class="form-control" id="totalAdvanceId" />
											</div>
										</div>
                  </div>
               </div>
                  </div>
               </div>
            </div>
               </div>
               </p>
               
               
                 <div class="row">
                     <p>
                  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#AmountPaidBackFormId">
                  Add Amount Paid Back to the University
                 </button>
                 </div>
                   <div class="row">
                 <button type="button" class="btn btn-primary" id="amntpaidbackId" data-toggle="collapse" data-target="#amountPaidBackView">
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
													" class="form-control" id="totalPaidBackId"  value="0"/>
											</div>
										</div>
               </div>
            </div>
	        </p>
	       </div>
                  
	</div>
        
        </div>
     
	    <div id="tabs-3">
        <div id="expenditureAndGoodsForm">
          <div class="row">
          <p>
           <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#expenditureDetailsFormId">
                 Add Details of Expenditure
            </button>
            </p>
            </div>
      
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
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#goodsReturnDivId">
                Add Details of Goods Returned to the University
            </button>
            </p>
            </div>
            
                <div class="row">
	       <p>
	         <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#goodsReturnView" id="goodsviewbutton">
                  View  Details of Goods Returned to the University				
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
            <div class="row">
            <p>
             <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#physicalDelivarablesDivId">
               Add Physical Delivarables Information
            </button>
            </p>
            </div>
               <div class="row">
	          <p>
              <button class="btn btn-primary" type="button"   data-toggle="collapse" data-target="#physicalDelivarablesView" id="physicaldelivarbleviewbutton">Physical Deliverables Vis a Vis Actual Performance</button> 
              <div class="collapse" id="physicalDelivarablesView">
               <div class="card card-body">
                  <div class="table-responsive">
                     <table class="table table-striped" id="physicalDelivarablesgridTable"></table>
                  </div>
               </div>
            </div>
               </p>
           </div>
        
        </div>
     
	  <div id="tabs-4">
           <div id="remarkssFormdiv">
           <form id="remarkssForm">
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
              	   <input type="number" name="amntPaidBack" class="form-control" id="amntPaidBackId" value="0"/>
              </div>
              <div class="form-group">
              	<label for="enteredRemarks">Balance Amount to be Paid</label> 
              	   <input type="text" name="balAmnt" class="form-control" id="balAmntId" />
              </div>

             <div class="form-group">
              	<label for="enteredRemarks">Remarks</label> <textarea id="enteredRemarksId" name="enteredRemarks" rows="4" cols="50"
                  class="form-control"></textarea>
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
            <div class="row">
				<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group" style="text-align: center">
						<button class="button btn-success" id="printRep">Print Report</button>
     			</div>
     			
			</div>
			</div>
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
             <div class="row" >
             <div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
				 <div class="form-group">
               <label for="financeImpliedId">vendor type<span
                  class="required">*</span></label> <label class="radio-inline"> <input
                  type="radio" id="rpId"  value="yes" name="vendorType1">ResourcePerson
               </label> <label class="radio-inline"> <input type="radio" id="vpId" value="no" name="vendorType1">Vendor
               </label>
            </div>
				</div>
             
             </div>
           
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
							type="number" name="expAmnt" class="form-control" id="expAmntId" required/>
					</div>
					<div class="form-group">
						<label for="activitytype">Tax amount</label>  <input
							type="number" name="tax" class="form-control" id="taxId" required/>
					</div>
					
					<div class="form-group">
					<button id="totalAmntBt">Get Total</button>
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
                                   <select class="form-control selectpicker" name="payeeId"
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
                        <label for="activitytype"> Passport No</label> <input type="text"
                           name=passportNo class="form-control" id="passportNoId" />
                     </div>
				      
				       <div class="form-group">
						<label for="activitytype">Paid Status</label> 
							<select class="form-control" name="paidStatus"
								id="paidStatusId" required>
								<option value="">Select</option>
								<option value="paid">Paid</option>
								<option value="notpaid">Not Paid</option>
							</select>
				      </div>
				       <div class="form-group">
						<label for="activitytype">Mode of Payment</label>
						<select class="form-control" name="modePayment"
								id="modePaymentId" required>
								<option value="">Select</option>
								<option value="Cash">Cash</option>
								<option value="Check">Check</option>
								<option value="RTGS">RTGS</option>
								<option value="NEFT">NEFT</option>
								<option value="DD">DD</option>
								<option value="Other">Other</option>
							</select>
				      </div>
				  <!--     <div class="form-group">
						<label for="activitytype">Admissible Amount</label> <input
							type="text" name="admsAmt" class="form-control" id="admsAmtId" />
				      </div> -->
			     
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

<!-- Details of Expenditure Details for editing-->

     <!-- Modal HTML End -->
<!-- Modal Resource Person-->
<div class="modal fade" id="expenditureDetailsFormId1" tabindex="-1"
   role="dialog" aria-labelledby="exampleModalLongTitle"
   aria-hidden="true">
   <div class="modal-dialog modal-lg" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLongTitle">Edit Details of Expenditure
            </h5>
            <button type="button" class="close" data-dismiss="modal"
               aria-label="Close">
            <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="modal-body">
           <form id="expenditureDetailsForm1">
        <div class="row" id="advReqFormId">
       
        
		<div class=" col-lg-6 col-md-6 col-sm-12 col-xs-12">
				 <div class="form-group">
						<label for="activitytype"> Expenditure Id</label> <input
							type="text" name="expId" class="form-control" id="expId1" 	required />
		</div>	
			<div class="form-group" style="width: 100%">
               <label for="expendHead">Expenditure Head<span
                  class="required">*</span></label>
               <div style="width: 100%">
                  <div style="display: inline-block; width: 100%">
                     <select class="form-control selectpicker" name="headId"
                        id="expHeadNameId1" required>
                     </select>
                  </div>
               </div>
            </div>
				
				<div class="form-group">
						<label for="activitytype"> Bill No / Voucher No</label> <input
							type="text" name="billNo" class="form-control" id="billNoId1" 	required />
					</div>
					<div class="form-group">
						<label for="expAmntId"> Value Amount</label> <input
							type="number" name="expAmnt" class="form-control" id="expAmntId1" required/>
					</div>
					<div class="form-group">
						<label for="activitytype">Tax amount</label>  <input
							type="number" name="tax" class="form-control" id="taxId1" required/>
					</div>
					
					<div class="form-group">
					<button id="totalAmntBt" disabled="disabled">Get Total</button>
					</div>
					
					<div class="form-group">
						<label for="activitytype">Total Amount</label> <input
							type="text" name="totalAmt" class="form-control" id="totalAmtId1" 	required />
					</div>
					
					
					
							<div class="form-group" style="width: 100%">
                            <label for="vedorid">Payee Name<span
                                         class="required">*</span></label>
                                      <div style="width: 100%">
                                     <div style="display: inline-block; width: 100%">
                                   <select class="form-control selectpicker" name="vendorId11"
                                  id="payeeNameId1" required>
                                </select>
                              </div>
                           </div>
                        </div>
			
				</div>
				<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-12">
				     <div class="form-group">
						<label for="gstNO">GST No </label><input
							type="text" name="gstNO" class="form-control" id="gstNoId1" 	 />
				      </div>
				
				         <div class="form-group">
						<label for="panNo">PAN No</label> <input
							type="text" name="panNo" class="form-control" id="panNoId1" 	 />
				      </div>
				      
				       <div class="form-group">
						<label for="activitytype">Paid Status</label> 
							<select class="form-control" name="paidStatus"
								id="paidStatusId1" required>
								<option value="">Select</option>
								<option value="paid">Paid</option>
								<option value="notpaid">Not Paid</option>
							</select>
				      </div>
				       <div class="form-group">
						<label for="activitytype">Mode of Payment</label>
						<select class="form-control" name="modePayment"
								id="modePaymentId1" required>
								<option value="">Select</option>
								<option value="Cash">Cash</option>
								<option value="Check">Check</option>
								<option value="RTGS">RTGS</option>
								<option value="NEFT">NEFT</option>
								<option value="DD">DD</option>
								<option value="Other">Other</option>
							</select>
				      </div>
				   <div class="form-group">
						<label for="activitytype">Vendor Type</label> <input
							type="text" name="vendorType" class="form-control" id="vendorTypeId1" />
				      </div> 
			     
				</div>
				</div>
		   <div class="row">
			<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
		    <div class="form-group">
              	<label for="activitytype">Remarks</label> <textarea id="remarksId1" name="enteredRemarks" rows="4" cols="50"
                  class="form-control"></textarea>
            </div>
            </div>
            </div>
			<div class="row">
				<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group" style="text-align: center">
						 <button type="submit" id="expenditureDetailsBt1" class="btn btn-primary">Submit</button>
     			</div>
			</div>
			</div>

		</form>
         </div>
      </div>
   </div>
</div>

<!-- --edit resource person details -->

  <!-- Modal HTML End -->
<!-- Modal Resource Person-->
<div class="modal fade" id="rPxpenditureDetailsFormId1" tabindex="-1"
   role="dialog" aria-labelledby="exampleModalLongTitle"
   aria-hidden="true">
   <div class="modal-dialog modal-lg" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLongTitle">Edit Details of Expenditure of Resource Person
            </h5>
            <button type="button" class="close" data-dismiss="modal"
               aria-label="Close">
            <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="modal-body">
           <form id="rPexpenditureDetailsForm1">
        <div class="row" id="advReqFormId">
       
        
		<div class=" col-lg-6 col-md-6 col-sm-12 col-xs-12">
				 <div class="form-group">
						<label for="activitytype"> Expenditure Id</label> <input
							type="text" name="expId" class="form-control" id="expId11" 	required />
		</div>	
			<div class="form-group" style="width: 100%">
               <label for="expendHead">Expenditure Head<span
                  class="required">*</span></label>
               <div style="width: 100%">
                  <div style="display: inline-block; width: 100%">
                     <select class="form-control selectpicker" name="headId"
                        id="expHeadNameId11" required>
                     </select>
                  </div>
               </div>
            </div>
				
				<div class="form-group">
						<label for="activitytype"> Bill No / Voucher No</label> <input
							type="text" name="billNo" class="form-control" id="billNoId11" 	required />
					</div>
					<div class="form-group">
						<label for="expAmntId"> Value Amount</label> <input
							type="number" name="expAmnt" class="form-control" id="expAmntId11" required/>
					</div>
					<div class="form-group">
						<label for="activitytype">Tax amount</label>  <input
							type="number" name="tax" class="form-control" id="taxId11" required/>
					</div>
					
					<div class="form-group">
					<button id="totalAmntBt" disabled="disabled">Get Total</button>
					</div>
					
					<div class="form-group">
						<label for="activitytype">Total Amount</label> <input
							type="text" name="totalAmt" class="form-control" id="totalAmtId11" 	required />
					</div>
					
					
					
							<div class="form-group" style="width: 100%">
                            <label for="vedorid">Payee Name<span
                                         class="required">*</span></label>
                                      <div style="width: 100%">
                                     <div style="display: inline-block; width: 100%">
                                   <select class="form-control selectpicker" name="resourceId"
                                  id="rPpayeeNameId11" required>
                                </select>
                              </div>
                           </div>
                        </div>
			
				</div>
				<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-12">
				     <div class="form-group">
						<label for="gstNO">GST No </label><input
							type="text" name="gstNO" class="form-control" id="gstNoId11" 	 />
				      </div>
				
				         <div class="form-group">
						<label for="panNo">PAN No</label> <input
							type="text" name="panNo" class="form-control" id="panNoId11" 	 />
				      </div>
				      
				       <div class="form-group">
						<label for="activitytype">Paid Status</label> 
							<select class="form-control" name="paidStatus"
								id="paidStatusId1" required>
								<option value="">Select</option>
								<option value="paid">Paid</option>
								<option value="notpaid">Not Paid</option>
							</select>
				      </div>
				       <div class="form-group">
						<label for="activitytype">Mode of Payment</label>
						<select class="form-control" name="modePayment"
								id="modePaymentId1" required>
								<option value="">Select</option>
								<option value="Cash">Cash</option>
								<option value="Check">Check</option>
								<option value="RTGS">RTGS</option>
								<option value="NEFT">NEFT</option>
								<option value="DD">DD</option>
								<option value="Other">Other</option>
							</select>
				      </div>
				  <!--     <div class="form-group">
						<label for="activitytype">Admissible Amount</label> <input
							type="text" name="admsAmt" class="form-control" id="admsAmtId" />
				      </div> -->
			     
				</div>
				</div>
		   <div class="row">
			<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
		    <div class="form-group">
              	<label for="activitytype">Remarks</label> <textarea id="remarksId1" name="enteredRemarks" rows="4" cols="50"
                  class="form-control"></textarea>
            </div>
            </div>
            </div>
			<div class="row">
				<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group" style="text-align: center">
						 <button type="submit" id="expenditureDetailsBt1" class="btn btn-primary">Submit</button>
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

<!-- datails of goods -->
   <!-- Modal HTML End -->
<!-- Modal Resource Person-->
<div class="modal fade" id="physicalDelivarablesDivId" tabindex="-1"
   role="dialog" aria-labelledby="exampleModalLongTitle"
   aria-hidden="true">
   <div class="modal-dialog" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLongTitle">Add Physical Delivarables Expenditure
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
					<div class="form-group" style="width: 100%">
                            <label for="vedorid">Physical Delivarables<span
                                         class="required">*</span></label>
                                      <div style="width: 100%">
                                     <div style="display: inline-block; width: 100%">
                                   <select class="form-control selectpicker" name="delivarableDescription"
                                  id="pdelivarableDescriptionId" required>
                                  <option value="">Select</option>
                                  <option value="ResourcePersons">ResourcePerson</option>
                                  <option value="Beneficaries">Beneficaries</option>
                                  
                                </select>
                              </div>
                           </div>
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
						<label for="enteredRemarksId">Remarks</label> 
						  <textarea id="enteredRemarksId" name="physicalEnteredRemarks" rows="4" cols="50"
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

<!-- Modal Resource Person-->
<div class="modal fade" id="advanceSettleDivId" tabindex="-1"
   role="dialog" aria-labelledby="exampleModalLongTitle"
   aria-hidden="true">
   <div class="modal-dialog" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLongTitle">Details of Advance Settlement
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
						<label for="itemId">Purpose</label> <input
							type="text" name="purpose" class="form-control" id="purposeId" 	required />
					</div>
				
				
				<div class="form-group">
						<label for="valueId">amount </label> <input
							type="number" name="advancePaid" class="form-control" id="advancePaidId" 	required />
					</div>
						     <div class="form-group">
                  <label for="activityType">Weather Advance to be settled</label>
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
						 <button type="submit" id="advanceSettlementBt" class="btn btn-primary">Submit</button>
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
<div class="modal fade" id="advanceSettlementId" tabindex="-1"
   role="dialog" aria-labelledby="exampleModalLongTitle"
   aria-hidden="true">
   <div class="modal-dialog" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLongTitle">Details of Advance to be Settled
            </h5>
            <button type="button" class="close" data-dismiss="modal"
               aria-label="Close">
            <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="modal-body">
           <form id="advanceSettlementForm">
               <div class="row" id="goodsReturnId">
		       <div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="itemId">Advance Payment Id</label> <input
							type="text" name="programApprovalAdvancePaymentId" class="form-control" id="programApprovalAdvancePaymentId1" 	required />
					</div>
					
						<div class="form-group">
						<label for="itemId">Purpose</label> <input
							type="text" name="purpose" class="form-control" id="purposeId11" 	required />
					</div>
					
				<div class="form-group">
						<label for="valueId">Payment Vouchar</label> <input
							type="text" name="voucharNo" class="form-control" id="voucharNoId11" 	required />
					</div>
					<div class="form-group">
						<label for="expAmntId"> Vouchar Date</label> <input
							type="text" name="voucharDate" class="form-control dp" id="voucharDateId11" 	 />
					</div>
					
					<div class="form-group">
						<label for="expAmntId"> Advance Paid Amount</label> <input
							type="number" name="advancePaid" class="form-control" id="advancePaidId11" 	 />
					</div>
					<div class="form-group">
						<label for="settledAmountid11">  Settled Amount</label> <input
							type="number" name="settledAmount" class="form-control" id="settledAmountid11" 	 />
					</div>
					<div class="form-group">
						<label for="expAmntId">Amount to be Settled</label> <input
							type="number" name="settledamount" class="form-control" id="settledamountId11" 	 />
					</div>
					
					<div class="form-group" style="width: 100%">
                            <label for="vedorid">Settlement<span
                                         class="required">*</span></label>
                                      <div style="width: 100%">
                                     <div style="display: inline-block; width: 100%">
                                   <select class="form-control selectpicker" name="settleStatus"
                                  id="settleStatus11" required>
                                  <option value="">Select</option>
                                  <option value="fuly settled">Full</option>
                                  <option value="partially settled">Partial</option>                                </select>
                              </div>
                           </div>
                        </div>
					
				</div>
				</div>
		   <div class="row">
			<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
		    <div class="form-group">
              	<label for="activitytype">Remarks</label> <textarea id="settlementRemarksId" name="settlementRemarks" rows="4" cols="50"
                  class="form-control"></textarea>
            </div>
            </div>
            </div>
			<div class="row">
				<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group" style="text-align: center">
						 <button type="submit" id="advancesBt" class="btn btn-primary">Submit</button>
     			</div>
			</div>
			</div>

		</form>
         </div>
      </div>
   </div>
</div>

























<!-- paid back view -->


            
            
            
<!-- report for printing -->

<div class="row">
	<div class="col-md-12">
		<div class="table-responsive" id="reportDiv" style="display:none">
			<table class="table table-striped" id="repTable">
				<tr><td colspan="4">
				 <img src="../images/logo.jpg" style="width: 40px;">
				</tr>
				<tr td colspan="4"><center>Expenditure Sanction / Settlement of Advance - Details of Bills , Vouchers and utilisation of Advance</center><td></tr>
				<tr><td colspan="4">
				
				</tr>
				<tr><td>Work/Project/Event Description</td><td colspan="3" id="title"></td></tr>
				<tr><td>Number Centres Involved in Organising the Program</td><td id="centerLength"></td></tr>
				<tr><td></td><td ><table id="centrestab" class="table table-striped"></table> </td></tr>
				<tr><td>Weather In-principle approval obtained</td><td id="approvalObtained"></td></tr>
				<tr><td>If Yes , File No/ Reference</td><td id="refNo"></td></tr>
	            <tr><td>Wether Administrative Sanction Obtained</td>><td id="asObtained"></td></tr>
				<tr><td>If Yes , File No/ UO Reference and Sanction Amount</td><td id="asNo"></td><td id="sancAmt"></td></tr>
				<tr><td>Weather External Funding/ Advance Received for the Work/Project/Event</td><td id="advrecieve"></td></tr>
				<tr><td></td></tr>
				<tr><td colspan="4"><u>If Yes, Details of Advance(s) Received</u></td><td id="advrecievedid"></td></tr>
			    <tr><td><table id="advpttab" class="table table-striped"></table></td></tr>  
			   	<tr><td colspan="4"><u>Details of any Amount Paid back to the University/u></td></tr>
			   <tr><td><table id="paidbackpt" class="table table-striped"></table></td></tr> 
			   <tr><td colspan="4"><u>Details of Expenditure</u></td></tr>
			   <tr><td><table id="exppt" class="table table-striped"></table></td></tr> 
			   <tr><td colspan="4"><u>Details of Goods returned to the University</u></td></tr>
			   <tr><td><table id="goodspt" class="table table-striped"></table></td></tr> 
			    <tr><td colspan="7">Certified that the bills and vouchers in this expenditure statement is duly verified. Also certified that the expenditure was incurred for the programme as approved by NUALS.The Items purchased if any were duly verified for quality and quantity and was taken in stock and properly recorded for any further verification that may be required.Any unused balance of stock is returned to the university. The services availed for the above purpose have been fully received used and certified.</td></tr> 
			   
				<tr><td>Kalamassery</td><td></td><td></td><td>Director/Co-ordinator</td></tr>
				<tr><td ></td></tr>
				<tr><td ></td></tr>
				
			</table>	
		</div>
	</div>

</div>


