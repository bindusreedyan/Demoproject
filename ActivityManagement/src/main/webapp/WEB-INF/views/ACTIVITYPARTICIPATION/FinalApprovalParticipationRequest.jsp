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
$("#otherExpensesId" ).on('click',function(event) {
	 alert("clicked");
	getTotalExpense();
   });
   
   function changestobeshowed()
   {
	   
   }
   function loadAllparticipationRequestFinallyApprovedBy() {
		$.ajax({
			"url" : "/academicactivity/getAllParticipantFinallyApprovedBy",
			"method" : "GET",
			success : function(data) {
				
				//console.log(data['asNO']);
				
			   setUpDataTable(data, [ 
				    {
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
			  ], "submittedParticipationRequest", "select-checkbox");
			   
			},

			error(e)
			{
				console.log(e)
			}
		
			});

	}
//getTotalActualSpentAndEstimate
function getTotalExpense()
  {
	//alert("advance clicked");
	   var sum=0;
	   var sum1=0;
	   $('.actual').each(function()
		   		{
		    var amnt66=$(this).val();
	        sum=sum+parseFloat(amnt66 ); 
	        $('#otherSpentExpenseTotalId').val(sum);
		   		});
	   
	   $('.estim').each(function()
		   		{
		    var amnt66=$(this).val();
	        sum1=sum1+parseFloat(amnt66 ); 
	        $('#otherEstimeExpenseTotalId').val(sum1);
		   		});
	   //  alert(sum);
  }


	$('#regbyNualsId').change(function()
	 		{
	 			var value=$(this).val();
	 			alert(value);
	 			if(value=="Y")
	 				{
	 				
	 				$('#regfeetr').prop('disabled', true);
	 				$('#regFeeSpentId').prop('disabled', true);
	 				$('#regFeeEstimExpId').prop('disabled', true);
	 		         }
	 			
	 			if(value=="N")
					{
					
					$('#regfeetr').prop('disabled', false);
					$('#regFeeSpentId').prop('disabled', false);
					$('#regFeeEstimExpId').prop('disabled', false);
			       }
	 				
	 		});

function loadParticipantRequestApproval() {
	console.log("loadParticipantRequestApproval");
	$.ajax({
		"url" : "/academicactivity/getAllParticipantRequestFinanceApproved",
		"method" : "GET",
		success : function(data) {
			
			console.log(data);
			
			setUpDataTable(data, [ {
				"particiaptionRequestId" : "Particiaption Request Id"
			}, 
			{
				"activityCode":"Activity Code"	
			},
			
			{
				"title":"Activity Name"
			},
			
			{
				"participationRequestStatus" : "Status"
			}
		  ], "participantActivityTable", "select-checkbox");

			
			onDataTableClick('participantActivityTable', function() {		
				if(selectedRowFromDataTable != null)
				{
					
					$('html, body').animate({ scrollTop: $('#participationRequestForm').offset().top }, 'slow');
					particiaptionRequestId=selectedRowFromDataTable[1];
					participateRequestId=selectedRowFromDataTable[1];
					activityId=selectedRowFromDataTable[2];
					$.ajax({
									"url" : "/academicactivity/getAllParticipantRequestById",
									"method" : "GET",						
									data : {"particiaptionRequestId":particiaptionRequestId},
									success : function(data) 
									{
								         console.log(data['ac']['activityCode']);
										$('#activityId').val(data['ac']['activityCode']);
										$('#hostingInstitutionId').val(data['hostingInstitution']);
										$('#datenotifyId').val(data['dateNotifction']);
										$('#venueId').val(data['activityVenue']);
										$('#competionStart').val(data['competionStart']);
										$('#competionEnd').val(data['competionEnd']);
										$('#travelonwardstart').val(data['travelDateOnwardStart']);
										$('#travelonwardEnd').val(data['travelDateOnwardReturn']);
										$('#travelreturnstart').val(data['travelDateReturnStart']);
										$('#travelreturnEnd').val(data['travelDateReturnEnd']);
										$('#competionType').val(data['competionType']);
										$('#currencyNonINRId').val(data['currencyNonINR']);
										$('#hostingCountryInterId').val(data['hostingCountry']);
										$('#estVisaChargeId').val(data['estVisaCharge']);
										$('#conversionRateId').val(data['conversionRate']);
										$('#estLocalConvId').val(data['estLocalConv']);
										$('#estLoadgingId').val(data['estLoadging']);
										$('#levelOneCheckRemarkId').val(data['recomRemarks']);
										$('#adminstrativeApproveRemarksId').val(data['adminstrativeApproveRemarks']);
										$('#financeApproveRemarksId').val(data['financialApproveRemarks']);
										
										
										var teamMember=data['teamParticipation'];
										//console.log(financeImplied);
										//var financeImplied=data['ac']['activityCode']
										if(teamMember=="yes")
											{
											$( "#yesId" ).prop( "checked", true );
											}
										if(teamMember=="no")
										{
										$( "#noId" ).prop( "checked", true );
										}									 
									},
									error : function(e)
									{
									}
								});
					
					$.ajax({
						"url" : "/academicactivity/getParticiapntDetailsByParticipantRequestId",
						"method" : "GET",
						data:{"particiaptionRequestId":particiaptionRequestId},
						success:function(data)
						{
							for(var i=0;i<data.length;i++)
								{
								console.log("usercodeeeeeeeeeeeee"+data[i]['userCode']);
								userCodeArray[i]=data[i]['userCode'];
								contactNumberArray[i]=data["contactNumber"];
								console.log(contactNumberArray[i])
								
								}
							
							$.ajax({
								"url" : "/academicactivity/getStudentPersonalDetails",
								"method" : "GET",
								data:{"userCodeArray":userCodeArray.toString(),"participationId":particiaptionRequestId},
								success:function(data1)
								{
									setUpDataTable(data1, [ {
										"student_admission_roll_no" : "RollNo"
									}, 
									{
										"student_personal_student_name":"Name"	
									},
									
									{
										"contact_number":"Contact Number"
									}
									
							      ], "participantPersonalable", "select-checkbox");
									
									
									setUpDataTable(data1, [ {
										"student_admission_roll_no" : "RollNo"
									}, 
									{
										"student_personal_student_name":"Name"	
									},
									
									{
										"onward_start_point":"Start Point"
									},
									{
										"onward_start_date":"Start Date"
									},
									{
										"onward_modeof_travel":"Mode of Travel"
									},
									{
										"onward_end_date":"Onward End Date"
									},
									{
										"onward_destination":"Onward Destination"
									},
									{
										"on_ward_est_expenditure":"Onward Expenditure"
									},
									{
										"onward_amount_admitted":"Onward Admitted Expenditure"
									}
									
							      ], "participantOnwardTable", "select-checkbox");
									onDataTableClick('participantOnwardTable',function()
							   				{
										
										   $('#rollNoId').val(selectedRowFromDataTable[1]);
										   $('#stnameId').val(selectedRowFromDataTable[2]);
										   $('#onwardStartPointId').val(selectedRowFromDataTable[3]);
										   $('#onwardStartDateId').val(selectedRowFromDataTable[4]);
										   $('#onwardModeofTravelId').val(selectedRowFromDataTable[5]);
										   $('#onwardEndDateId').val(selectedRowFromDataTable[6]);
										   $('#onwardDestinationId').val(selectedRowFromDataTable[7]);
										   $('#onWardEstExpenditureId').val(selectedRowFromDataTable[8]);
										   $('#onwardAmountAdmittedId').val(selectedRowFromDataTable[9]);
										   
										$('#dialogEditParticipantOnwardJourney').modal({backdrop: 'static',
					   	   				     keyboard: false});
										
							   				});
									//member return details
									setUpDataTable(data1, [ {
										"student_admission_roll_no" : "RollNo"
									}, 
									{
										"student_personal_student_name":"Name"	
									},
									{
									"free_of_cost_accomadation":"Free of cost accomadation"	
									},
									
									{
										"accomodation_exp_head":"Details of Expenditure"
									},
									{
										"accmdation_start_date":"Start Date"
									},
									{
										"accmdation_end_date":"End Date"
									},
									{
										"no_of_days":"No of Days"
									},
									{
										"daily_rate":"Daily Rate"
									},
									{
										"bill_no":"Bill No"
									},
									{
										"accmdtion_estim_expenditure":"Estimated Expenditure"
									},
									{
										"amount_admitted":"Admitted Expenditure"
									}
									
							      ], "participantAccomadationTable", "select-checkbox");
									onDataTableClick('participantAccomadationTable',function()
							   				{									
										$('#dialogEditParticipantAccomodationDetails').modal({backdrop: 'static',
					   	   				     keyboard: false});
										   $('#arollNoId').val(selectedRowFromDataTable[1]);
										   $('#astnameId').val(selectedRowFromDataTable[2]);
										   $('#expendtureDetailsId').val(selectedRowFromDataTable[4]);
										   $('#accomStartDateId').val(selectedRowFromDataTable[5]);
										   $('#accomEndDateId').val(selectedRowFromDataTable[6]);
										   $('#noDaysId').val(selectedRowFromDataTable[7]);
										   $('#dailyRateId').val(selectedRowFromDataTable[8]);
										   $('#billNoId').val(selectedRowFromDataTable[9]);
										   $('#estimExpId').val(selectedRowFromDataTable[10]);
										   $('#amntAdmtId').val(selectedRowFromDataTable[11]);
							   				});
							
									//add member accomadation details
									setUpDataTable(data1, [ {
										"student_admission_roll_no" : "RollNo"
									}, 
									{
										"student_personal_student_name":"Name"	
									},
									
									{
										"return_start_point":"Start Point"
									},
									{
										"return_start_date":"Start Date"
									},
									{
										"return_modeof_travel":"Mode of Travel"
									},
									{
										"return_end_date":"Return End Date"
									},
									{
										"return_destination":"Return Destination"
									},
									{
										"return_est_expenditure":"Rettun Expenditure"
									},
									{
										"return_amount_admitted":"Admitted Expenditure"
									}
									
							      ], "participantReurnTable", "select-checkbox");
									onDataTableClick('participantReurnTable',function()
							   				{
										alert("clicked return table");
										   $('#rrollNoId').val(selectedRowFromDataTable[1]);
										   $('#rstnameId').val(selectedRowFromDataTable[2]);
										   $('#returnStartPointId').val(selectedRowFromDataTable[3]);
										   $('#returnStartDateId').val(selectedRowFromDataTable[4]);
										   $('#returnModeofTravelId').val(selectedRowFromDataTable[5]);
										   $('#returnEndDateId').val(selectedRowFromDataTable[6]);
										   $('#returnDestinationId').val(selectedRowFromDataTable[7]);
										   $('#returnEstExpenditureId').val(selectedRowFromDataTable[8]);
										   
										   $('#returnAmntAdmtId').val(selectedRowFromDataTable[9]);
										$('#dialogEditParticipantReturnJourney').modal({backdrop: 'static',
					   	   				     keyboard: false});
										
							   				});
									
									
								},
								error(e)
								{
							
								}
							});
							
						},
						error(e)
						{
							console.log(e);
						}
							
							
							
					
				         });
					
					//get other expenses details
					$.ajax({
						
						"url":"/academicactivity/getParticiapntOtherExpensesDetailsByParticipantRequestId",
						"method":"GET",
						data:{"participantRequestId":particiaptionRequestId},
						success:function(data)
						{
							$("#regbyNualsId").val(data['regFeeByNuals']);
							$("#regFeeSpentId").val(data['regFeeSpent']);
							$("#regFeeEstimExpId").val(data['regFeeEstimExp']);
							
							$("#regFeeAdmittedId").val(data['regFeeAdmitted']);
							
							
							
							$("#printoutsMemPagesId").val(data['printoutsMemPages']);
							$("#printoutMemSpentId").val(data['printoutMemSpent']);
							$("#printoutMemAmtEstId").val(data['printoutMemAmtEst']);
							$("#printoutMemAdmittedId").val(data['printoutMemAdmitted']);
							
							
							
							$("#photocopyMemPagesId").val(data['photocopyMemPages']);
							$("#photocopyMemSpentId").val(data['photocopyMemSpent']);
							$("#photocopyMemAmtEstId").val(data['photocopyMemAmtEst']);
							$("#photocopyMemAdmittedId").val(data['photocopyMemAdmitted']);	
							
							
							
							$("#bindingAmntSpentId").val(data['bindingAmntSpent']);
							$("#bindingMemAmtEstId").val(data['bindingMemAmtEst']);
							$("#bindingAmntAdmitId").val(data['bindingAmntAdmit']);
							
							
							
							$("#costMemPagesId").val(data['costMemPages']);
							$("#costMemSpentId").val(data['costMemSpent']);
							$("#costMemAmtEstId").val(data['costMemAmtEst']);
							$("#costemAdmittedId").val(data['costemAdmitted']);
							
							
							$("#printoutsComPagesId").val(data['printoutsComPages']);
							$("#printoutComSpentId").val(data['printoutComSpent']);
							$("#printoutComAmtEstId").val(data['printoutComAmtEst']);
							$("#printoutComAdmittedId").val(data['printoutComAdmitted']);
							
							
							
							$("#photocopyComPagesId").val(data['photocopyComPages']);
							$("#photocopyComSpentId").val(data['photocopyComSpent']);
							$("#photocopyComAmtEstId").val(data['photocopyComAmtEst']);
							$("#photocopyComAdmittedId").val(data['photocopyComAdmitted']);	
							
							
							$("#bindingComSpent").val(data['bindingComSpent']);
							$("#bindingComAmtEstId").val(data['bindingComAmtEst']);
							$("#bindingComAdmittedId").val(data['bindingComAdmitted']);
							
							
							
							$("#paperNoCom").val(data['paperNoCom']);
							$("#costPaperComSpentId").val(data['costPaperComSpent']);
							$("#costOfPaperComEstmExpId").val(data['costOfPaperComEstmExp']);
							$("#costPaperComAdmittedId").val(data['costPaperComAdmitted']);
							
							
							
							$("#courierChargesAmntSpentId").val(data['courierChargesAmntSpent']);
							$("#courierChargesEstmExpId").val(data['courierChargesEstmExp']);
							$("#courierChargesAdmittedId").val(data['courierChargesAdmitted']);
							
							
							//other details data
							
							$("#noMemorialsPetitionerId").val(data['noMemorialsPetitioner']);
							$("#noMemorialsRespondentId").val(data['noMemorialsRespondent']);
							$("#pagesPetitionerId").val(data['pagesPetitioner']);
							$("#pagesRespondentId").val(data['pagesRespondent']);
							$("#compendiumsId").val(data['compendiums']);
							$("#pagesCompendiumId").val(data['pagesCompendium']);
							$("#priorAdministrativeSanctionId").val(data['priorAdministrativeSanction']);
							$("#receivedRegFeeId").val(data['receivedRegFee']);
							$("#compendiumLibraryId").val(data['compendiumLibrary']);
							$("#compendiumReceiptId").val(data['compendiumReceipt']);
							$("#reasonOnNoSubmissionId").val(data['reasonOnNoSubmission']);
							
							
							
							$("#modeOfPaymentId").val(data['modeOfPayment']);
							$("#authRecNameId").val(data['authRecName']);
							$("#bankNameId").val(data['bankName']);
							$("#accNoId").val(data['accNo']);
							$("#ifscId").val(data['ifsc']);
							$("#branchId").val(data['branch']);
							$("#totalExpenseProgramId").val(data['totalExpenseProgram']);
							$("#totalAdmitAmountId").val(data['totalAdmitAmount']);
							
						
							
							
							
						},
						error(e)
						{
							console.log("Error while request"+e)
						}
						
						
					});
					
					
					
					//to get active centres
				 	//to get all centress
				 	
			/* 	   	$.ajax({
				   		"url":"/academicfinance/getAllactiviticenters",
				   		"method":"GET",
				   		data:{"activityId":activityId},
				   		success : function(data) {
				   		 $('#centerdiv').empty();
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
				                .append('<input type="checkbox" id='+checbxid+' name='+num1+' checked="true" value='+data[i]['actvtyCenterKey']['cm']['centre_id']+'>'+data[i]['actvtyCenterKey']['cm']['centre_name'] )
				                .append('</label>')
				   			 }
				   			
				   			
				   		},
				   		error : function(e) {
				   			//showMessage("Error in Getting Programs. Contact Admin");
				   		}
				   	}); */
				   
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


$(document).ready(function() {
	
	
	loadAllparticipationRequestFinallyApprovedBy();
	loadParticipantRequestApproval();
	$('#fldset').prop('disabled', true);
	
	$.ajax({
		"url" : "/academicactivity/getAllActivityFinalAndPreApproved",
		"method" : "GET",
		 success : function(data) {
			console.log(data);
		    setUpDropDown("activityId",data,"activityCode","title");
			},
			
			error(e)
			{
			console.log(e);
			}
			});
	
	
	
	
	$('.dp').datepicker({
   	    format: 'yyyy-mm-dd',							   
   		autoclose:true,
   		clearBtn:true
   	});
	
	$('#menuHaed').text("");
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
	
	//Edit master data details
	   $('#paricipationMasterForm').unbind().bind('submit',function(event) 
	   {
	   	        console.log("entrrr in paricipationMasterForm");
	   			event.preventDefault();
	   			var formCheck = "success";
	   			var formData = $(this).serializeArray();
	   		 	formData.push({"name":"particiaptionRequestId","value":particiaptionRequestId});
	   		  
	   				$('#submitModel').modal('show');
	   
	   				$('#confId').unbind().bind('click',function(){
	   
	   					console.log("confirm clicked...");
	                     var i=0;
	   					$.ajax({
	   						"url" : "/academicactivity/editActivityParticiaptionMasterData",
	   						"method" : "POST",
	   						 data : formData,
	   						 success : function(data) {
	   							 
	   							 console.log(formData);
	   							 
	   							particiaptionRequestId=data['particiaptionRequestId'];
	   							showMessage("Parcipation Request is successfully added"+particiaptionRequestId);
	   							loadParticipantRequestApproval();
	                               
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
	//edit activityParticiapnt onward Journey details;
	
	   $('#onwardJourneyDetails').unbind().bind('submit',function(event) 
			   {
			   	        console.log("entrrr in paricipationMasterForm");
			   			event.preventDefault();
			   			var formCheck = "success";
			   			$("#rollNoId").prop("disabled",false);
			   			var formData = $(this).serializeArray();
			   			
			   		
			   			//	$('#submitModel').modal('show');
			   
			   			//	$('#confId').unbind().bind('click',function(){
			   	            formData.push({"name":"participateRequestId","value":participateRequestId});
			   	         formData.push({"name":"activityCode","value":activityId});
			   					console.log("confirm clicked...");
			                     var i=0;
			   					$.ajax({
			   						"url" : "/academicactivity/editActivityParticipantOnwardJourneyDetails",
			   						"method" : "POST",
			   						 data : formData,
			   						 success : function(data) {
			   							 
			   							 console.log(formData);
			   							 showMessage(data);
			   							loadParticipantRequestApproval();
			   							//particiaptionRequestId=data['particiaptionRequestId'];
			   							//showMessage("Parcipation Request is successfully added"+particiaptionRequestId);
			                               
			   						 },
			   						 error(e)
			   						 {
			   							showMessage("Error in approval. Contact Admin"); 
			   						 }
			   						 });
			   					//$('#submitModel').modal('hide');
			   				//});
			   				/*$('#cancelId').unbind().bind('click',function(){
			 				   
								console.log("Cancel clicked...");

								$('#submitModel').modal('hide');
								
							});*/
				  
				

			})	
				//add other expenses details
   		$('#otherExpenseForm').unbind().bind('submit',function(event) {
   			            console.log("other expenses form");
	                    event.preventDefault();
	                    var formData = $(this).serializeArray();
	                    formData.push({"name":"participationRequestId","value":particiaptionRequestId});
	                    $('#submitModel').modal('show');
	        			$('#confId').unbind().bind('click',function(){
	        				console.log("confirm clicked...");
	        				$.ajax({
	        					"url" : "/academicactivity/editActivityParticipantOtherExpenses",
	        					"method" : "POST",
	        					 data : formData,
	        					 success : function(data)
	        					 {
	        						if(data)
	        							{
	        							showMessage("Other Expenses are successfully added");
	        							}
	        						else
	        							{
	        							showMessage("Error in saving. Contact Admin");
	        							}
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
			
	//edit return journey details
	$('#returnJourneyDetails').unbind().bind('submit',function(event) 
			   {
			   	        console.log("entrrr in return journey details");
			   			event.preventDefault();
			   			var formCheck = "success";
			   			$("#rrollNoId").prop("disabled",false);
			   			var formData = $(this).serializeArray();
			   			
			   		
			   			//	$('#submitModel').modal('show');
			   
			   			//	$('#confId').unbind().bind('click',function(){
			   	            formData.push({"name":"participateRequestId","value":participateRequestId});
			   	         formData.push({"name":"activityCode","value":activityId});
			   					console.log("confirm clicked...");
			                     var i=0;
			   					$.ajax({
			   						"url" : "/academicactivity/editActivityParticipantReturnJourneyDetails",
			   						"method" : "POST",
			   						 data : formData,
			   						 success : function(data) {
			   							 
			   							 console.log(formData);
			   							 showMessage(data);
			   							loadParticipantRequestApproval();
			   							//particiaptionRequestId=data['particiaptionRequestId'];
			   							//showMessage("Parcipation Request is successfully added"+particiaptionRequestId);
			                               
			   						 },
			   						 error(e)
			   						 {
			   							showMessage("Error in approval. Contact Admin"); 
			   						 }
			   						 });
			   					//$('#submitModel').modal('hide');
			   				//});
			   				/*$('#cancelId').unbind().bind('click',function(){
			 				   
								console.log("Cancel clicked...");

								$('#submitModel').modal('hide');
								
							});*/
				  
				

			})
		
		//edit return journey details
	$('#AccomadationDetails').unbind().bind('submit',function(event) 
			   {
			   	        console.log("entrrr in accomadation details");
			   			event.preventDefault();
			   			var formCheck = "success";
			   			$("#arollNoId").prop("disabled",false);
			   			var formData = $(this).serializeArray();
			   			
			   		
			   			//	$('#submitModel').modal('show');
			   
			   			//	$('#confId').unbind().bind('click',function(){
			   	            formData.push({"name":"participateRequestId","value":participateRequestId});
			   	            formData.push({"name":"activityCode","value":activityId});
			   					console.log("confirm clicked...");
			                     var i=0;
			   					$.ajax({
			   						"url" : "/academicactivity/editActivityAccomadationDetails",
			   						"method" : "POST",
			   						 data : formData,
			   						 success : function(data) {
			   							 
			   							 console.log(formData);
			   							 showMessage(data);
			   							loadParticipantRequestApproval();
			   							//particiaptionRequestId=data['particiaptionRequestId'];
			   							//showMessage("Parcipation Request is successfully added"+particiaptionRequestId);
			                               
			   						 },
			   						 error(e)
			   						 {
			   							showMessage("Error in approval. Contact Admin"); 
			   						 }
			   						 });
			   					//$('#submitModel').modal('hide');
			   				//});
			   				/*$('#cancelId').unbind().bind('click',function(){
			 				   
								console.log("Cancel clicked...");

								$('#submitModel').modal('hide');
								
							});*/
				  
				

			})		
			
			
	//update other Details
	
			$('#otherDetailsForm').unbind().bind('submit',function(event) {
	   			console.log("other expenses form");
		                    event.preventDefault();
		                    var formData = $(this).serializeArray();
		                    formData.push({"name":"participationRequestId","value":particiaptionRequestId});
		                    $('#submitModel').modal('show');
		        			$('#confId').unbind().bind('click',function(){
		        				console.log("confirm clicked...");
		        				$.ajax({
		        					"url" : "/academicactivity/addActivityParticipantOtherDetails",
		        					"method" : "POST",
		        					 data : formData,
		        					 success : function(data)
		        					 {
		        						if(data)
		        							{
		        							showMessage("Other Details are successfully updated");
		        							}
		        						else
		        							{
		        							showMessage("Error in saving. Contact Admin");
		        							}
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

		
	$('#bankDetailsForm').unbind().bind('submit',function(event) {
			console.log("other expenses form");
                    event.preventDefault();
                    var formData = $(this).serializeArray();
                    formData.push({"name":"participationRequestId","value":particiaptionRequestId});
                    $('#submitModel').modal('show');
        			$('#confId').unbind().bind('click',function(){
        				console.log("confirm clicked...");
        				$.ajax({
        					"url" : "/academicactivity/addActivityBankDetails",
        					"method" : "POST",
        					 data : formData,
        					 success : function(data)
        					 {
        						if(data)
        							{
        							showMessage("Bank details successfully updated");
        							}
        						else
        							{
        							showMessage("Error in saving. Contact Admin");
        							}
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
	
	
	$('#administrativeapproval').unbind().bind('submit',function(event) {
        event.preventDefault();
        var formData = $(this).serializeArray();
        formData.push({"name":"participateRequestId","value":particiaptionRequestId});
        $('#submitModel').modal('show');
		$('#confId').unbind().bind('click',function(){
			console.log("confirm clicked...");
			$.ajax({
				"url" : "/academicactivity/addFinalApprovalDetails",
				"method" : "POST",
				 data : formData,
				 success : function(data)
				 {
					if(data)
						{
						showMessage(data);
						}
					else
						{
						showMessage("Error in saving. Contact Admin");
						}
					loadParticipantRequestApproval();
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

</script>
<div  id="gridRow" class="card card-info card-outline">
	<div class="card-header">
	  <h3>List Of Acitivities</h3>
  
	</div>

<div class="card-body">
   	<div class="table-responsive">
			<table class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border" id="participantActivityTable">
		 
			</table>
	</div>
</div>
</div>

<div class="card card-info card-outline" id="participationRequestForm">
<div class="card-header">
   <h3>Final Approval for Participation Request</h3>
</div>
<div class="card-body">
<div class="row">
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
<div id="tabs">
	  <ul>
	<li style="background-color:#265bab;text-color:white;"><a href="#tabs-1" style="color:#d5e6e4">Participation Master Details</a></li>
	<li style="background-color:#5a2066;text-color:white;"><a href="#tabs-2" style="color:#d5e6e4">Team Members Details</a></li>
	<li style="background-color:#3d5e48;text-color:white;"><a href="#tabs-3" style="color:#d5e6e4">Other Expenses</a></li>
  	<li style="background-color:#d92176;text-color:white;"><a href="#tabs-4" style="color:#d5e6e4">Other Details</a></li>
  	<li style="background-color:#d92176;text-color:white;"><a href="#tabs-5" style="color:#d5e6e4">Remarks/Recommendation</a></li>
	  </ul>
	  <div id="tabs-1">
	   <div id="participationMasterDetId">
	   <form id="paricipationMasterForm">
	   	<fieldset id="fldset">
	   <div class="row">
	    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
	    <div class="form-group" style="width: 100%">
            <label for="activity">Name of the competition<span
               class="required">*</span></label>
            <div style="width: 100%">
               <div style="display: inline-block; width: 100%">
                  <select class="form-control selectpicker" name="ac"
                     id="activityId" required>
                  </select>
               </div>
            </div>
         </div>
          <div class="form-group" style="width: 100%">
               <label for="dateofNotification">Date of Notification</label> <input type="text"
                  id="datenotifyId"  class="form-control dp" name="dateNotifction" required></input>
            </div>
              
   
</div>

            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
	          <div class="form-group" style="width: 100%">
            <label for="activity">Hosting Instituition<span
               class="required">*</span></label>
            <input
               type="text" name="hostingInstitution" class="form-control" id="hostingInstitutionId"
               required />
         </div>
          <div class="form-group" style="width: 100%">
            <label for="venue">Venue of the competitions<span
               class="required">*</span></label>
            <input type="text" name="activityVenue" class="form-control" id="venueId" required />
         </div>
          
       
         </div>
         
         </div>
         
           <div class="row">
            <div class="form-group" style="width: 100%">
               <label for="dateofNotification">Date on which Competion Held</label> 
            </div>
            </div>
           <div class="row">
           <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
           <div class="form-group" style="width: 100%" class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
            <label for="FromDate">From<span
               class="required">*</span></label>
            <input type="text" id="competionStart" name="competionStart" class="form-control dp"  />
         </div>
         </div>
         <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
           
         <div class="form-group" style="width: 100%" class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
            <label for="todate">To<span
               class="required">*</span></label>
            <input type="text"  class="form-control dp" id="competionEnd" name="competionEnd" />
         </div>
         </div>
         </div>
         <div class="row">
            <div class="form-group" style="width: 100%">
               <label for="onward">Travel Dates - Omward</label> 
            </div>
            </div>
                    <div class="row">
           <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
           <div class="form-group" style="width: 100%" class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
            <label for="TravalDateonward">Start<span
               class="required">*</span></label>
            <input type="text"  class="form-control dp" id="travelonwardstart" name="travelDateOnwardStart"required />
         </div>
         </div>
         <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
           
         <div class="form-group" style="width: 100%" class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
            <label for="End">End<span
               class="required">*</span></label>
            <input type="text"  class="form-control dp" id="travelonwardEnd" name="travelDateOnwardReturn" required />
         </div>
         </div>
         </div>
           <div class="row">
            <div class="form-group" style="width: 100%">
               <label for="onward">Travel Dates - Return</label> 
            </div>
            </div>
         
         <div class="row">
           <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
           <div class="form-group" style="width: 100%" class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
            <label for="TravalDatereturnstart">Start<span
               class="required">*</span></label>
            <input type="text"  class="form-control dp" id="travelreturnstart" name="travelDateReturnStart" required />
         </div>
         </div>
         <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
           
         <div class="form-group" style="width: 100%" class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
            <label for="TravalDatereturnend">End<span
               class="required">*</span></label>
            <input type="text"  class="form-control dp" id="travelreturnEnd" name="travelDateReturnEnd" required />
         </div>
         </div>
         </div>
         
           <div class="row">
           <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
           <div class="form-group" style="width: 100%" class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
            <label for="competionTyoe">Competition Type<span
               class="required">*</span></label>
            <input type="text"  class="form-control" id="competionType" name="competionType" required />
         </div>
            <div class="form-group" style="width: 100%" class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
               <label for="teammembership">Team Membership<span
                  class="required">*</span></label>
                <label class="radio-inline"> <input
                  type="radio" id="yesId"  value="yes" name="teamParticipation">YES
               </label> <label class="radio-inline"> <input type="radio" id="noId" value="no" name="teamParticipation">NO
               </label>
            </div>
         </div>
         </div>  
         <div class="row">
         <p>
<a class="btn btn-primary" data-toggle="collapse" href="#internationalparticipant" id="internationalpartbtn" role="button" aria-expanded="false" aria-controls="internationalparticipant">
For International Participants Only
</a>
</p>
  <div class="collapse" id="internationalparticipant">
<div class="card card-body">
<div class="row">
   <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
	                     
          <div class="form-group" style="width: 100%">
            <label for="currentcynoninr">Currency requirement other than INR
          <span class="required"></span></label>
            <input type="text" name="currencyNonINR" class="form-control" id="currencyNonINRId"  />
         </div>
         
          <div class="form-group" style="width: 100%">
            <label for="estVisaCharge">Estimated Visa Charges in INR
          <span class="required">*</span></label>
            <input type="number" name="estVisaCharge" class="form-control" id="estVisaChargeId"  />
         </div>
         
          <div class="form-group" style="width: 100%">
            <label for="estVisaCharge">Estimated Loadging Expenses INR

          <span class="required">*</span></label>
            <input type="number" name="estLoadging" class="form-control" id="estLoadgingId"  />
         </div>
         
         </div>
         
         
           <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
	          <div class="form-group" style="width: 100%">
            <label for="hostcountry">Hostcountry<span
               class="required">*</span></label>
            <input
               type="text" name="hostingCountry" class="form-control" id="hostingCountryInterId"/>
            </div>
            
          <div class="form-group" style="width: 100%">
            <label for="venue">Approximate convertion rate on proposal date</label>
            <input type="number" name="conversionRate" class="form-control" id="conversionRateId"  />
         </div>
         <div class="form-group" style="width: 100%">
            <label for="estVisaCharge">Estimated Local Convayance Expenses INR</label>
            <input type="number" name="estLocalConv" class="form-control" id="estLocalConvId"  />
         </div>
         </div>
         </div>    
</div>
</div>    
</div>

<button type="submit" id="activityPaticipationMastersubmit" class="btn btn-primary">Seen And Verify</button>
	</fieldset>
</form>
</div>
</div>
 <div id="tabs-2">
 <div id="otherExpenseId">
	   <div class="row">
	    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	    
	         <p>
          <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#memberPersonal" aria-expanded="false" aria-controls="collapseExample">
                  View Member Person Details
          </button>
            
             <button class="btn btn-link" type="button"
              data-toggle="collapse" data-target="#memberOnwardDetails" aria-expanded="false" aria-controls="collapseExample">View OnwardTravel Details
            </button>
             <button class="btn btn-link" type="button"
              data-toggle="collapse" data-target="#memberReturnDetails" aria-expanded="false" aria-controls="collapseExample">View Return Travel Details
            </button>
            <button class="btn btn-link" type="button"
            data-toggle="collapse" data-target="#accomadationDetails" aria-expanded="false" aria-controls="collapseExample">View Accomadation Details
            </button>
         </p>

									<div class="collapse" id="memberPersonal">
										<div id="gridRow" class="card card-info card-outline">
											<div class="card-header">
												<h5>Participant Member Personal Details</h5>
											</div>
											<div class="card-body">
												<div class="table-responsive">
													<table
														class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border"
														id="participantPersonalable">

													</table>
												</div>
											</div>
										</div>
									</div>

									<div class="collapse" id="memberOnwardDetails">
										<div id="gridRow" class="card card-info card-outline">
											<div class="card-header">
												<h5>Participant Member Onward Travel Details</h5>
											</div>
											<div class="card-body">
												<div class="table-responsive">
													<table
														class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border"
														id="participantOnwardTable">

													</table>
												</div>
											</div>
										</div>
									</div>


                                   	<div class="collapse" id="memberReturnDetails">
										<div id="gridRow" class="card card-info card-outline">
											<div class="card-header">
												<h5>Participant Member Return Travel Details</h5>
											</div>
											<div class="card-body">
												<div class="table-responsive">
													<table
														class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border"
														id="participantReurnTable">

													</table>
												</div>
											</div>
										</div>
									</div>
 	                                 <div class="collapse" id="accomadationDetails">
										<div id="gridRow" class="card card-info card-outline">
											<div class="card-header">
												<h5>Participant Member Accomadation Details</h5>
											</div>
											<div class="card-body">
												<div class="table-responsive">
													<table
														class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border"
														id="participantAccomadationTable">

													</table>
												</div>
											</div>
										</div>
									</div>
									<div id="TeamMemberPersonalDetailsGrid" class="row" style="display: none">
	              <div class="table-responsive table-striped">
	              <table class="table table-bordered " id="gridTableMapper1">
	              <thead>
                           <tr>
                            <th >SI No</th>
                              <th>Name of the Team Member</th>
                              <th>RollNo</th>
                              <th>Start Point</th>
                              <th>StartDate</th>
                              <th>Destination</th>
                              <th>EndDate</th>
                              <th>Mode of Travel</th>
                              <th>Estimated Expenditure</th>
                              <th>Amount Admitted</th>

                           </tr>
                   </thead>
                        <tbody id="TravelDetailsReturnGridBody">
                        </tbody>
                    
	              </table>
	              </div>
	              </div>
         
         
         <div id="TravelDetailsOnwardGrid" class="row" style="display: none">
	              <div class="table-responsive table-striped">
	              <table class="table table-bordered " id="gridTableMapper">
	              <thead>
                           <tr>
                            <th >SI No</th>
                              <th>Name of the Team Member</th>
                              <th>RollNo</th>
                              <th>Start Point</th>
                              <th>StartDate</th>
                              <th>Destination</th>
                              <th>EndDate</th>
                              <th>Mode of Travel</th>
                              <th>Estimated Expenditure</th>
                              <th>Amount Admitted</th>

                           </tr>
                   </thead>
                        <tbody id="TravelDetailsOnwardGridBody">
                        </tbody>
                    
	              </table>
	              </div>
	              </div>  
         <div id="TravelDetailReturnGrid" class="row" style="display: none">
	              <div class="table-responsive table-striped">
	              <table class="table table-bordered " id="gridTableMapper1">
	              <thead>
                           <tr>
                            <th >SI No</th>
                              <th>Name of the Team Member</th>
                              <th>RollNo</th>
                              <th>Start Point</th>
                              <th>StartDate</th>
                              <th>Destination</th>
                              <th>EndDate</th>
                              <th>Mode of Travel</th>
                              <th>Estimated Expenditure</th>
                              <th>Amount Admitted</th>

                           </tr>
                   </thead>
                        <tbody id="TravelDetailsReturnGridBody">
                        </tbody>
                    
	              </table>
	              </div>
	              </div>

            <div id="TravelDetailReturnGrid" class="row" style="display: none">
               <div class="col-md-12">
                  <div class="table-responsive cell-border">
                     <table id="gridTableMapper" class="table table-striped">
                        <thead>
                           <tr>
                              <th scope="col">SI No</th>
                              <th scope="col">Name of the Team Member</th>
                              <th scope="col">RollNo</th>
                              <th scope="col">Start Point</th>
                              <th scope="col">StartDate</th>
                              <th scope="col">Destination</th>
                              <th scope="col">EndDate</th>
                              <th scope="col">Mode of Travel</th>
                              <th scope="col">Estimated Expenditure</th>
                              <th scope="col">Amount Admitted</th>
                           </tr>
                        </thead>
                        <tbody id="expenditureGridBody">
                        </tbody>
                     </table>
                  </div>
               </div>
            </div>
           </div>
	    </div>
	     <!-- accomadationdetails -->
            
               <div class="row" id="accomdationBynuals" style="display:none">
               <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
               <div class="form-group">
               <label for="type">Weather Provided Free of Cost by the conducting Institution<span class="required">*</span></label>
               <div style="width: 100%">
               <div style="display: inline-block; width: 100%">
                  <select class="form-control selectpicker" name="type" id="accomdationByself" name="freeOfCostAccomadation"
                     required>
                     <option value="se">Select</option>
                     <option value="yes">Yes</option>
                     <option value="no">No</option>
                  </select>
               </div>
            </div>
             </div>
               </div>
               
               </div>
               <div id="AccomadationDetailsGrid" class="row" style="display: none">
               <div class="col-md-12">
                  <div class="table-responsive cell-border">
                     <table id="gridTableMapper" class="table table-striped">
                    <col style="width:10%">
                        <thead>
                           <tr>
                              <th scope="col">SI No</th>
                              <th scope="col">Name of the Team Member</th>
                              <th>RollNo</th>
                              <th scope="col">Details of Expenditure</th>
                              <th scope="col">StartDate</th>
                              <th scope="col">End Date</th>
                              <th scope="col">No of Days</th>
                              <th scope="col">Daily Rate</th>
                              <th scope="col">BillNo</th>
                              <th scope="col">Estimated Expenditure</th>
                          </tr>
                        </thead>
                        <tbody id="accomadationGridBody">
                        </tbody>
                     </table>
                  </div>
               </div>
            </div>
	    
	    
	    
	    </div>
	</div>
	<!-- --------------tab3 -->
	
	<!-- --------------tab3 -->
	
	<div id="tabs-3">
	   <div id="otherExpenseId">
	   <form id="otherExpenseForm">
	   <div class="row">
	    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	        <div class="form-group">
               <label for="type">Registration Fee Wheather Paid by University<span class="required">*</span></label>
               <div style="width: 100%">
                  <div style="display: inline-block; width:50%">
                     <select class="form-control selectpicker" name="regFeeByNuals" id="regbyNualsId"
                        required>
                        <option value="se">Select</option>
                        <option value="Y">yes</option>
                        <option value="N">no</option>
                     </select>
                  </div>
                  </div>

	              </div>
	              <div id="divGridTable3" class="row">
	              <div class="table-responsive table-striped">
	              <table class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Other Expenses</th>
      <th scope="col">No of Pages</th>
      <th scope="col">Amount Estimated</th>
      <th scope="col">Amount Admitted</th>
    </tr>
  </thead>
  <tbody>
      <tr id="regfeetr">
      <th scope="row">1</th>
      <td>Registration Fee</td>
      <td><input type="text" name="regFeeSpent1" id="regFeeSpentId1" disabled="disabled"/></td>
      <td><input type="number" name="regFeeEstimExp" id="regFeeEstimExpId" class="estim" value="0"/></td>
       <td><input type="number" name="regFeeAdmitted" id="regFeeAdmittedId" class="admitted" value="0"/></td>
     </tr>
  

    <tr>
      <th scope="row">2</th>
      <td>Print outs (Memorials)</td>
      <td><input type="number" name="printoutsMemPages" id="printoutsMemPagesId" value="0"/></td>
      <td><input type="number" name="printoutMemAmtEst" id="printoutMemAmtEstId" class="estim" value="0"/></td>
      <td><input type="number" name="printoutMemAdmitted" id="printoutMemAdmittedId" class="admitted" value="0"/></td>
      
    </tr>
     <tr>
      <th scope="row">3</th>
      <td>Photocopy(Memorials)</td>
      <td><input type="number" name="photocopyMemPages" id="photocopyMemPagesId" value="0"/></td>
      <td><input type="number" name="photocopyMemAmtEst" id="photocopyMemAmtEstId" class="estim" value="0"/></td> 
      <td><input type="number" name="photocopyMemAdmitted" id="photocopyMemAdmittedId" class="admitted" value="0"/></td> 
    </tr>
      <tr>
      <th scope="row">4</th>
      <td>Binding(Memorials)</td>
      <td><input type="text" name="" id="" disabled="disabled"/></td>
      <td><input type="number" name="bindingMemAmtEst" id="bindingMemAmtEstId"  class="estim" value="0"/></td>
       <td><input type="number" name="bindingAmntAdmit" id="bindingAmntAdmitId" class="admitted" value="0"/></td>
    </tr>
    <tr>
      <th scope="row">5</th>
      <td>Cost of paper (Memorials)</td>
      <td><input type="number" name="costMemPages" id="costMemPagesId" value="0"/></td>
      <td><input type="number" name="costMemAmtEst" id="costMemAmtEstId" class="estim" value="0"/></td>
      <td><input type="number" name="costemAdmitted" id="costemAdmittedId" class="admitted" value="0"/></td>
    </tr>
      <tr>
      <th scope="row">6</th>
      <td>Print outs (Compendiums)</td>
      <td><input type="number" name="printoutsComPages" id="printoutsComPagesId" value="0"/></td>
      <td><input type="number" name="printoutComAmtEst" id="printoutComAmtEstId" class="estim" value="0"/></td>
      <td><input type="number" name="printoutComAdmitted" id="printoutComAdmittedId" class="admitted" value="0"/></td>
    </tr>
    <tr>
      <th scope="row">7</th>
      <td>Photocopy (Compendiums)</td>
      <td><input type="number" name="photocopyComPages" id="photocopyComPagesId" value="0"/></td>
      <td><input type="number" name="photocopyComAmtEst" id="photocopyComAmtEstId" class="estim" value="0"/></td>
      <td><input type="number" name="photocopyComAdmitted" id="photocopyComAdmittedId" class="admitted" value="0"/></td>
    </tr>
    <tr>
    <tr>
      <th scope="row">8</th>
      <td>Binding(Compendiums)</td>
      <td><input type="number" name="" id="" disabled="disabled"/></td>
      <td><input type="number" name="bindingComAmtEst" id="bindingComAmtEstId" class="estim" value="0"/></td>
       <td><input type="number" name="bindingComAdmitted" id="bindingComAdmittedId" class="admitted" value="0"/></td>
    </tr>
    <tr>
      <th scope="row">9</th>
      <td>Cost of paper (Compendiums)</td>
      <td><input type="number" name="paperNoCom" id="paperNoCom" value="0"/></td>
      <td><input type="number" name="costOfPaperComEstmExp" id="costOfPaperComEstmExpId" class="estim" value="0"/></td>
      <td><input type="number" name="costPaperComAdmitted" id="costPaperComAdmittedId" class="admitted" value="0"/></td>
      
    </tr>
    <tr>
      <th scope="row">10</th>
      <td>Courier charges</td>
      <td><input type="number" name="" id="" disabled="disabled"/></td>
      <td><input type="number" name="courierChargesEstmExp" id="courierChargesEstmExpId" class="estim" value="0"/></td>
      <td><input type="number" name="courierChargesAdmitted" id="courierChargesAdmittedId" class="admitted" value="0"/></td>
    </tr>
     <tr>
      <th scope="row"></th>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
    </tr>
    
     <tr>
      <th scope="row"></th>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
      </tr>
      <tr>
      <th scope="row"></th>
      <td><b></b></td>
      <td></td>
      <td><input type="button" value="getTotal"  id="otherExpensesId"/></td>
      </tr>

 <!--    <tr>
      <th scope="row"></th>
      <td><b>Grant Total of Other Expenses</b></td>
      <td></td>
      <td><input type="text" name="" id="otherEstimeExpenseTotalId"/></td>
    </tr -->>
<tr>
      <th scope="row"></th>
      <td><b>Total Estimated Expenditure for the Particiption</b></td>
      <td></td>
      <td><input type="text" name="totalExpenseProgram" id="totalExpenseProgramId" /></td>
       <td></td>
    </tr>
     <tr>
      <th scope="row"></th>
      <td><b>Total Admitted Amount for the Particiaption</b></td>
      <td></td>
      <td><input type="number" name="totalAdmitAmount" id="totalAdmitAmountId" /></td>
       <td></td>
    </tr>
     <tr>
      <th scope="row"></th>
      <td><b></b></td>
      <td></td>
      <td></td>
      <td><input type="submit" name="" id="" value="Seen And Verify"/></td>
    </tr>
    
  </tbody>
</table>

</div>
</div>	    
</div>
</div>
</form>
</div>
</div>
	
	
	<!-- -              tab3 end -->
	
<div id="tabs-4">
	<form id="otherDetailsForm">
	   <div id="otherDetailsId">
	   <div class="row">
	    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	   <div id="divGridTable3" class="row">
	              <div class="table-responsive table-striped">
	              <table class="table">
<thead>
 <tr>
   <th scope="col">#</th>
   <th scope="col">Other Details</th>
   <th> values</th>
 </tr>
</thead>
<tbody>
   <tr>
   <td>1</td>
   <td>Total No. of Books submitted as Memorials for Petitioner/Plaintiff					
   </td>
   <td><input type="text" name="noMemorialsPetitioner" id="noMemorialsPetitionerId"/></td>
   </tr>
      <tr>
   <td>2</td>
   <td>Total No. of Books submitted as Memorials for Respondent/ Defendant					
					
   </td>
   <td><input type="text" name="noMemorialsRespondent" id="noMemorialsRespondentId"/></td>
   </tr>
    <tr>
   <td>3</td>
   <td>Total No. of pages in a Single Memorial for Petitioner/Plaintiff								
   </td>
   <td><input type="text" name="pagesPetitioner" id="pagesPetitionerId"/></td>
   </tr>
    <tr>
   <td>4</td>
   <td>Total No. of pages in a Single Memorial for Respondent/ Defendant								
   </td>
   <td><input type="text" name="pagesRespondent" id="pagesRespondentId"/></td>
   </tr>
    <tr>
   <td>5</td>
   <td>Total number of compendiums prepared							
   </td>
   <td><input type="text" name="compendiums" id="compendiumsId"/></td>
   </tr>
    <tr>
   <td>6</td>
   <td>Total No. of pages in a single compendium							
   </td>
   <td><input type="text" name="pagesCompendium" id="pagesCompendiumId"/></td>
   </tr>
    <tr>
   <td>7</td>
   <td>Whether the team has obtained prior administrative sanction to participate in the competition								
   </td>
   <td> <select class="form-control selectpicker" name="priorAdministrativeSanction" id="priorAdministrativeSanctionId"
                     required>
                     <option value="se">Select</option>
                     <option value="yes">Yes</option>
                     <option value="no">No</option>
                  </select></td>
   </tr>
    <tr>
   <td>8</td>
   <td>Whether the team has already received the Registration fee to participate in this competition								
   </td>
   <td><select class="form-control selectpicker" name="receivedRegFee" id="receivedRegFeeId"
                     required>
                     <option value="se">Select</option>
                     <option value="yes">Yes</option>
                     <option value="no">No</option>
                  </select></td>
   </tr>
    <tr>
   <td>9</td>
   <td>Whether the team has submitted  the compendium to NUALS Library?								
   </td>
   <td><select class="form-control selectpicker" name="compendiumLibrary" id="compendiumLibraryId"
                     required>
                     <option value="se">Select</option>
                     <option value="yes">Yes</option>
                     <option value="no">No</option>
                  </select></td>
   </tr>
    <tr>
   <td>10</td>
   <td>Whether the team has submitted the receipt  from the NUALS Library on submission of the compendium?</td>
   <td><select class="form-control selectpicker" name="compendiumReceipt" id="compendiumReceiptId"
                     required>
                     <option value="se">Select</option>
                     <option value="yes">Yes</option>
                     <option value="no">No</option>
                  </select></td>
   </tr>
    <tr>
   <td>11</td>
   <td>If the team has not submitted the compendium to NUALS Library reason for the same?</td>
   <td><input type="text" name="reasonOnNoSubmission" id="reasonOnNoSubmissionId"/></td>
   </tr>
   
    <tr>
   <td>11</td>
   <td></td>
   <td><input type="submit" value="Seen And Verify Details"/></td>
   </tr>
   </tbody>
   </table>
   </div>
   </div>
	   </div>
	   </div>
	   </div>
	   </form>
	      <div id="payementparticulars">
	   <div class="row">
	   <form id="bankDetailsForm">
	    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	   <div id="divGridTable3" class="row">
	              <div class="table-responsive table-striped">
	              <table class="table">
	                <thead>
 <tr>
   <th scope="col">#</th>
   <th scope="col">Payment Particulars</th>
   <th> values</th>
 </tr>
</thead>
	    <tr>
   <td>1</td>
   <td>Requested Mode of payment
	 </td>
   <td><select class="form-control selectpicker" name="modeOfPayment"  id="modeOfPaymentId" required>
                        <option value="">Select</option>
                        <option value="Cheque">Cheque</option>
                        <option value="NEFT/RTGS">NEFT/RTGS</option>
                     </select></td>
   </tr>
     <tr>
   <td>2</td>
   <td>Name of the Authorised recipient
	 </td>
   <td><input type="text" name="authRecName" id="authRecNameId"/></td>
   </tr>
   
       <tr>
   <td>3</td>
   <td>Name of the Bank
	 </td>
   <td><input type="text" name="bankName" id="bankNameId"/></td>
   </tr>
   
   <tr>
   <td>4</td>
   <td>Account No
	 </td>
   <td><input type="text" name="accNo" id="accNoId"/></td>
   </tr>
   <tr>
   <td>5</td>
   <td>IFSC 
	 </td>
   <td><input type="text" name="ifsc" id="ifscId"/></td>
   </tr>
   <tr>
   <td>6</td>
   <td>Name of Branch</td>
   <td><input type="text" name="branch" id="branchId"/></td>
   </tr>
   <tr>
   <td></td>
   <td></td>
   <td><input type="submit" value="Seen And Verify"/></td>
   </tr>
	 </table>
	 </div>          
	   </div>
	   </div>
	   </form>
	   </div>
	   </div>
	   </div>
	<div id="tabs-5">
	   <form id="administrativeapproval">
	   <div id="otherDetailsId">
	   <div class="row">
	    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="form-group">
               <label for="remarks"> Remarks For Faculty Recommendation</label>
               <textarea id="levelOneCheckRemarkId" name="facultyRecomRemarks" rows="4" cols="50"
                  class="form-control"></textarea>
            </div>
            
            <div class="form-group">
               <label for="remarks"> Remarks For Administrative Approval</label>
               <textarea id="adminstrativeApproveRemarksId" name="adminstrativeApproveRemarks" rows="4" cols="50"
                  class="form-control"></textarea>
            </div>
            <div class="form-group">
               <label for="remarks"> Remarks For Financial Approval</label>
               <textarea id="financeApproveRemarksId" name="financialApproveRemarks" rows="4" cols="50"
                  class="form-control"></textarea>
            </div>
               <div class="form-group">
               <label for="activityLevel">Final Approval<span
                  class="required">*</span></label>
               <div style="width: 100%">
                  <div style="display: inline-block; width: 100%">
                     <select class="form-control selectpicker" name="finalApprovalStatus"
                        id="activityLevelId" required>
                        <option value="se">Select</option>
                        <option value="finallyapproved">Approve</option>
                        <option value="finallyrejected">Reject</option>
                        
                     </select>
                  </div>
               </div>
            </div>
             <div class="form-group">
               <label for="remarks"> Remarks For Final Approval</label>
               <textarea id="finalApprovedRemarkId" name="finalApprovedRemark" rows="4" cols="50"
                  class="form-control"></textarea>
            </div>
           <!--  <div class="form-group">
               <label>Approve Date</label> <input type="text" id="finalApprovedDate"
                  name="finalApprovedDate" class="form-control dp" required></input>
            </div> -->
            <div class="form-group" style="text-align: center">
               <button type="submit" class="btn btn-primary" id="activityFinance">SUBMIT</button>
            </div>
	    
	    </div>
	    </div>
	    </div>
	    
	    </form>
	   </div>
	
	
<div class="modal fade" id="exampleModalLong" tabindex="-1"
role="dialog" aria-labelledby="exampleModalLongTitle"
aria-hidden="true">
<div class="modal-dialog" role="document">
   <div class="modal-content">
      <div class="modal-header">
         <h5 class="modal-title" id="exampleModalLongTitle">Add Activity Team Member
            Person Details
         </h5>
         <button type="button" class="close" data-dismiss="modal"
            aria-label="Close">
         <span aria-hidden="true">&times;</span>
         </button>
      </div>
      <div class="modal-body">
         <form id="TeamMembersetailsForm">
            <div class="row">
               <div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
               
               <div class="form-group">
					 <label for="accountName">Select Program</label>
					<select class="form-control grouping" id="selectProgram"></select>
					</div>
					<div class="form-group">
						<label for="accountName">Select Batch</label>
						<select class="form-control grouping" id="selectBatch"></select>
						</div>
						
						<div class="form-group">
						<label for="accountName">Select name</label>
						<select class="form-control grouping" name="nameId" id="nameId"></select>
						</div>
						
						
                  <div class="form-group">
                     <label for="activitytype"> RollNo</label> <input type="text"
                        name="rollno" class="form-control" id="rollnoId" required />
                  </div>
                  
                  </div>
                  <div class="form-group">
                     <label for="activitytype"> Contact Number	</label> <input
                        type="text" name="contactNo" class="form-control"
                        id="organisationId" required />
                  </div>
                  </div>
                  <div class="modal-footer">
                  <button type="button" class="btn btn-secondary"
                     data-dismiss="modal">Close</button>
                  <button type="submit" class="btn btn-primary"
                     id="resourceButtonAdd">Submit</button>
               </div>
             </form>
            </div>
      </div>
   </div>
</div>
</div>
	   
</div>
</div>
</div>


<!-- <div  id="gridRow" class="card card-info card-outline">
  	<div class="card-header">
	
  	<div class="card-header">
		  <h3 >List Of Participation Request Finally Verified</h3>
	  
  	</div>


    <div class="card-body">
	   	<div class="table-responsive">
				<table class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border" id="submittedParticipationRequest">
			 
				</table>
		</div>
	</div>
</div>
</div> -->

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
</div>

<div  id="gridRow" class="card card-info card-outline">
  	<div class="card-header">
	
  	<div class="card-header">
		  <h3 >List Of Participation Request Finally Verified</h3>
	  
  	</div>


    <div class="card-body">
	   	<div class="table-responsive">
				<table class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border" id="submittedParticipationRequest">
			 
				</table>
		</div>
	</div>
</div>
</div>

<div class="modal fade" id="dialogEditParticipantOnwardJourney" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Edit Onward Journey Details</h5>
		 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
		<div class="modal-body">
		<form id="onwardJourneyDetails" >
			<div class="form-group">
						<label for="Roll No">Roll No</label> 
						<input type="text" class="form-control" id="rollNoId"  name="rollNo"  disabled="disabled">
		   	</div>
		   	<div class="form-group">
		   		<label for="stdname">Name of the Team Member</label>
		   		<input type="text" class="form-control" id="stnameId"  name="stdname"  disabled="disabled">
		   	</div>
			
			<div class="form-group">
		   		<label for="onwardStartPoint">Start Point</label>
		   		<input type="text" class="form-control" id="onwardStartPointId"  name="onwardStartPoint" >
		   	</div>
		    <div class="form-group">
		   		<label for="onwardStartDate">Start Date</label>
		   		<input type="text" class="form-control dp" id="onwardStartDateId"  name="onwardStartDate">
		   	</div>
		   	
		   		<div class="form-group">
		   		<label for="onwardModeofTravel">Mode of the Travel</label>
		   		<input type="text" class="form-control" id="onwardModeofTravelId"  name="onwardModeofTravel">
		   	</div>
		   	
		   	<div class="form-group">
		   		<label for="onwardEndDate">EndDate</label>
		   		<input type="text" class="form-control dp" id="onwardEndDateId"  name="onwardEndDate">
		   	</div>
		   	
		   	 <div class="form-group">
		   		<label for="Destination">Destination</label>
		   		<input type="text" class="form-control" id="onwardDestinationId"  name="onwardDestination">
		   	</div>
		   	
		   
		   	<div class="form-group">
		   		<label for="Expenditure">Estimated Expenditure</label>
		   		<input type="text" class="form-control" id="onWardEstExpenditureId"  name="onWardEstExpenditure">
		   	</div>
		   	
		   	
		   	 <div class="form-group">
		   		<label for="Expenditure">Admitted Expenditure</label>
		   		<input type="text" class="form-control" id="onwardAmountAdmittedId"  name="onwardAmountAdmitted">
		   	</div>
		   	
		   	 <div class="modal-footer">
   
		   	<button type="submit" class="btn btn-primary">Edit Details</button>
		   	 <button type="button" class="btn btn-secondary"
                        data-dismiss="modal">Close</button>
                        </div>
		   	</form>
		</div>
		      <div class="modal-footer">
      </div>
    </div>
  </div>
</div>
<!-- return journey details -->
<div class="modal fade" id="dialogEditParticipantReturnJourney" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Edit Return Travel Details</h5>
		 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
<div class="modal-body">
		<form id="returnJourneyDetails" >

			<div class="form-group">
						<label for="Roll No">Roll No</label> 
						<input type="text" class="form-control" id="rrollNoId"  name="rollNo"  disabled="disabled">
		   	</div>
		   	<div class="form-group">
		   		<label for="stdname">Name of the Team Member</label>
		   		<input type="text" class="form-control" id="rstnameId"  name="rstdname"  disabled="disabled">
		   	</div>
			
			<div class="form-group">
		   		<label for="returnStartPoint">Return Start Point</label>
		   		<input type="text" class="form-control" id="returnStartPointId"  name="returnStarPoint" >
		   	</div>
		    <div class="form-group">
		   		<label for="onwardStartDate">Return Start Date</label>
		   		<input type="text" class="form-control dp" id="returnStartDateId"  name="returnStartDate">
		   	</div>
		   	
		   		<div class="form-group">
		   		<label for="onwardModeofTravel">Mode of the Travel</label>
		   		<input type="text" class="form-control" id="returnModeofTravelId"  name="returnModeofTravel">
		   	</div>
		   	
		   	<div class="form-group">
		   		<label for="onwardEndDate">Return EndDate</label>
		   		<input type="text" class="form-control dp" id="returnEndDateId"  name="returnEnddate">
		   	</div>
		   	
		   	 <div class="form-group">
		   		<label for="Destination">Return Destination</label>
		   		<input type="text" class="form-control" id="returnDestinationId"  name="returnDestination">
		   	</div>
		   	
		   
		   	<div class="form-group">
		   		<label for="Expenditure">Estimated Expenditure</label>
		   		<input type="text" class="form-control" id="returnEstExpenditureId"  name="returnEstimExp">
		   	</div>
		   	<div class="form-group">
		   		<label for="Expenditure">Amount Admitted</label>
		   		<input type="text" class="form-control" id="returnAmntAdmtId"  name="returnAmntAdmt">
		   	    </div>
		   <div class="modal-footer">
		   	<button type="submit" class="btn btn-primary">Edit Details</button>
		   	<button type="button" class="btn btn-secondary"
                        data-dismiss="modal">Close</button>
		   	</div>
		   	</form>
		</div>
		      <div class="modal-footer">
      </div>
    </div>
  </div>
</div>
<!-- return accomodation details -->
<div class="modal fade" id="dialogEditParticipantAccomodationDetails" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Edit Accomadation Journey Details</h5>
		 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
<div class="modal-body">
		<form id="AccomadationDetails" >
		<div class="row">
		<div class="col-6">
			<div class="form-group">
						<label for="Roll No">Roll No</label> 
						<input type="text" class="form-control" id="arollNoId"  name="rollNo"  disabled="disabled">
		   	</div>
		   	
		   	<div class="form-group">
		   		<label for="accomEndDate">Accom Start Date</label>
		   		<input type="text" class="form-control dp" id="accomStartDateId"  name="accomStartDate">
		   	</div>
		   	
		   	   	 	<div class="form-group">
		   		<label for="expendturedetails">Details of Expenditure</label>
		   		<input type="text" class="form-control" id="expendtureDetailsId"  name="expendtureDetails" >
		   	</div>
		  
		   	<div class="form-group">
		   		<label for="noDays">No of Days</label>
		   		<input type="number" class="form-control" id="noDaysId"  name="noDays" >
		   	</div>
		    	<div class="form-group">
		   		<label for="estimExp">Estimated Expenditure</label>
		   		<input type="number" class="form-control" id="estimExpId"  name="estimExp">
		   	</div>
		   		<div class="form-group">
		   		<label for="estimExp">Amount Admitted</label>
		   		<input type="number" class="form-control" id="amntAdmtId"  name="amntAdmt">
		   	</div>
		</div>
		<div class="col-6">
		
		 	<div class="form-group">
		   		<label for="stdname">Name of the Team Member</label>
		   		<input type="text" class="form-control" id="astnameId"  name="astdname"  disabled="disabled">
		   	</div>
		   	
		   	<div class="form-group">
		   		<label for="accomEndDate">Accomadation End Date</label>
		   		<input type="text" class="form-control dp" id="accomEndDateId"  name="accomEndDate">
		   	</div>
		   
		   
		    	<div class="form-group">
		   		<label for="billNo">Bill No</label>
		   		<input type="text" class="form-control dp" id="billNoId"  name="billNo">
		   	</div>
		   
		   	 	 <div class="form-group">
		   		<label for="dailyRate">Daily Rate</label>
		   		<input type="number" class="form-control" id="dailyRateId"  name="dailyRate">
		   	</div>
		   	<div class="form-group">
		   		<label for="estimExp">Amount Admitted</label>
		   		<input type="number" class="form-control" id="amntAdmtId"  name="amntAdmt">
		   	</div>
		</div>
		</div> 
		<div class="modal-footer">
		   	<button type="submit" class="btn btn-primary">Edit Details</button>
		   	<button type="button" class="btn btn-secondary"
                        data-dismiss="modal">Close</button>
		   	</div>
		   	</form>
		</div>
		      
      </div>
    </div>
  </div>
</div>












