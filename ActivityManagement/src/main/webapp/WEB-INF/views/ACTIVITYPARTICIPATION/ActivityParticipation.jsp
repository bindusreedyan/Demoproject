
<link type="text/css" rel="stylesheet" href="./sfs_public/css/datepicker/bootstrap-datepicker.css">

<script type="text/javascript" src="./sfs_public/js/datepicker/bootstrap-datepicker.js"></script>
 
 <link rel="stylesheet" href="./sfs_public/css/jquery-confirm.min.css">
 <script src="./sfs_public/js/jquery-confirm.min.js"></script> 
 
<!--  <style>
table {
  table-layout: fixed ;
  width: 100% ;
}
td {
  width: 60% ;
}
</style> -->
 
 
<script>

var userCodeArray=[];
var teamActivity=null;
var onwardTotal=0;
var returnTotal=0;
var accomTotal=0;
var financeImplied=null;
var sumaccom=0;
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
var sumonward=0;
var sumreturn=0;
var sumaccom=0;

//reports
var onwardrollnoadded=[];
var returnrollnoadded=[];
var accomrollnoadded=[];
var loginRollNo;

var namertArr=[];
var rollrtArr=[];
var contArr=[];
var onwardStartPointArr=[];
var onwardStartDateArr=[];
var onwarddestinationArr=[];
var onwardenddateArr=[];
var onwardmdtravelArray=[];
var onwardestimexpArray=[];

var returnStartPointArr=[];
var returnStartDateArr=[];
var returndestinationArr=[];
var returnenddateArr=[];
var returnModeoftravelArray=[];
var returnestimexpArray=[];

var accomodation_exp_headArr=[];
var accmdation_start_dateArr=[];
var accmdation_end_dateArr=[];
var returndestinationArr=[];
var bill_noArray=[];
var daily_rateArray=[];
var no_of_daysArray=[];
var accmdtion_estim_expenditureArray=[];
//for reports
var userCodeArray=[];
var contactNumberArray=[];
var title;
var accomAmountEstim=0;
var returnJourneyAmountEstim=0;


var onWardJourneyAmountEstim=0;
var returnJourneyAmountEstim=0;
var accomAmountEstim=0;
var addedafterRollno=[];

var returnaddedafterRollno=[];

var accomaddedafterRollno=[];
function getPersonalDetails(particiaptionRequestId)
{

$.ajax({
					"url" : "/academicactivity/getParticiapntDetailsByParticipantRequestId",
					"method" : "GET",
					data:{"particiaptionRequestId":particiaptionRequestId},
					success:function(data)
					{
						
					 accomAmountEstim=0;
					 returnJourneyAmountEstim=0;
						  
						onWardJourneyAmountEstim=0;
						for(var i=0;i<data.length;i++)
							{
							console.log("usercodeeeeeeeeeeeee"+data[i]['userCode']);
							userCodeArray[i]=data[i]['userCode'];
							contactNumberArray[i]=data["contactNumber"];
							//console.log(contactNumberArray[i])
							
							}
						
					     $.ajax({
								"url" : "/academicactivity/getStudentPersonalDetails",
								"method" : "GET",
								 async :false,
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
									
							      ], "participantOnwardTable");
								setUpDataTable(data1, [ {
										"student_admission_roll_no" : "RollNo"
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
									}
									
							      ], "participantAccomadationTable");
								  setUpDataTable(data1, [ {
										"student_admission_roll_no" : "RollNo"
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
									
							      ], "participantReurnTable");
								},
								
								error(e)
								{
									console.log("Error while request"+e)
								}
				  
			  });
			  
					},
								
								error(e)
								{
									console.log("Error while request"+e)
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




function loadAllparticipationRequest() {
	$.ajax({
		"url" : "/academicactivity/getAllParticipantRequestByParticipant",
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


function loadAllAcademicActivityTypeDetails() {
	$.ajax({
		"url" : "/academicactivity/getAllActivityApproved",
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
						$('html, body').animate({ scrollTop: $('#activityParticipationReq').offset().top }, 'slow');
						$('#activityId').val(selectedRowFromDataTable[1]);
						 $('#activityId').prop('disabled', true);
						console.log("asno iddddddd"+selectedRowFromDataTable[3]);
						var asNo=selectedRowFromDataTable[3];
						$('#aSNoId').val(asNo);
						activityCode=selectedRowFromDataTable[1];
						var finyear=selectedRowFromDataTable[4];
						 var userCode=$('#usercode').val();
						 
						 title=selectedRowFromDataTable[2];
					/* 	$.ajax({
							"url":"/academicfinance/getActivityFinance",
							"method":"GET",
							data:{"activityId":activityCode},
							success:function(data)
								{
								console.log(data);
								$('#amtSanctionedASId').val(data['totalEstExp']);
								
								},
								error: function(e)
								{
									console.log(e);
								}
								
					            }); *///end ajax
					            
					            
					       
					            
					            
						$.ajax({
							"url" : "/academicactivity/getAllActivityById",
							"method" : "GET",						
							data : {"activityId":activityCode},
							success : function(data) 
							{
								var dateFrom=data['dateFrom'];
								var dateTo=data['dateTo'];
								var dateFrom1 = dateFrom.split("T")[0];
								var dateTo1=dateTo.split("T")[0];
								
								
								$('#competionStart').val(dateFrom1);
								 $('#competionStart').prop('disabled', true);
								$('#competionEnd').val(dateTo1);
								 $('#competionEnd').prop('disabled', true);
								 $('#competionTypeId').val(data['aat']['activity_type_description']);
								 $('#competionTypeId').prop('disabled', true);
								var activityLevel=data['activityLevel'];
								if(activityLevel=="international")
									{
									$('#internationalpartbtn'). prop('disabled', false);
									}
								else
									{
									//alert("no");
									$('#internationalpartbtn'). prop('disabled', true);
								 //    $(".ui-button").prop('disabled', true); 
									}
								
							},
							error(e)
							{
								console.log(e)
							}
						
				   		});//ajax end     
		                
				   		$.ajax({
							"url" : "/academicactivity/getActivityDetails",
							"method" : "GET",						
							data : {"activityId":activityCode},
							success : function(data) 
							{
								
							},
							error(e)
							{
								console.log(e)
							}
						});
				   		
				   	
				   		$.ajax({
							"url":"/students/getStudentsDetails-V3",
							"method":"GET",
					         data:{"userCodes":userCode},
							  success:function(userdata)
								{
							     
							   
									nameArray[savedPartipntcount]=userdata[userCode]['studentName'];
		   		   	  				rollNoArray[savedPartipntcount]=userdata[userCode]['rollNo'];
		   		   	  			     savedPartipntcount++;
		   		   	  			      loginRollNo=userdata[userCode]['rollNo'];
		   		   	  			 //    Console.log()
							    
								},
								error: function(e)
								{
									console.log(e);
								}					
					            }); 
				   		
				   		//toshow details of the program
				   		$.ajax(
				   				{
				   					"url" : "/academicactivity/getAllAcademicApprovalDetailsReport",
				   					"method" : "GET",						
				   					"data" : {"activityId":activityCode},
				   					success:function(data)
				   					{
				   						var d=data;
				   						centres=d["centres"];
				   						resources=d['resourcePersons']
				   						console.log("checking centres");
				   						console.log(centres)
				   						for(var key in d)
				   						{
				   							$('#'+key).val(d[key]);
				   							$('#'+key).text(d[key]);
				   						}
				   						for(let i=0;i<centres.length;i++)
				   						{
				   							$('#centrestab').append('<tr><td colspan="1">Centre Code</td><td colspan="2">'+centres[i]+'</td></tr>');	
				   						}
				   						 for(let i=0;i<resources.length;i++)
				   						{
				   							$('#resourcestab').append('<tr><td colspan="2">'+resources[i]+'</td></tr>');	
				   						} 
				   						 
				   					
				   					$('#programDeatilsmodal').modal({backdrop: 'static',keyboard: false});
				   					}
				   				});
				   		
				   		
				   		
				   		
				   		
				   		
				   		
				   		
				   		
				   		
				   		
				   		
				   		
				   		
				   		
				   		
				   		
				   		
				   		
				   		
				   		

				   		
				   		
			              }//end if
			  });//on data table click end
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
    		  
    		  
    		  var studentcode=$('#nameId').val();
    		  
    		  $.ajax({
    		      method:"GET",
    		      url: "/students/getStudentRecord-v1",
    		      data:{"studentCode":studentcode},
    		      success:function(data)
    		      {
    		    	  $('#phNoId').val(data['mobileno']);
    		      },
    		      error(e)
    		      {
    		    	  console.log(e);
    		    	  }
    		      });
    		  
    		  
    		  
    		  
    		  
    		  if(rollNo1!=null)
    			  {
    		  console.log("rollno"+rollNo1);
    		  $("#rollnoId").val(studentDetails[$(this).val()]['rollNo']);
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
 
 //getTotalActualSpentAndEstimate
 
 function getTotalExpense()
   {
	//alert("advance clicked");
	   var sum=0;
	   var sum1=0;
	 
	  /*  $('.actual').each(function()
		   		{
	    	
		   var amnt66=$(this).val();
	        sum=sum+parseFloat(amnt66 ); 
	        $('#otherSpentExpenseTotalId').val(sum);
		   		}); */
		   		
		   		
		   		
		   		$.ajax({
					"url" : "/academicactivity/getParticiapntDetailsByParticipantRequestId",
					"method" : "GET",
					data:{"particiaptionRequestId":particiaptionRequestId},
					success:function(data)
					{
						
					 accomAmountEstim=0;
					 returnJourneyAmountEstim=0;
						  
						onWardJourneyAmountEstim=0;
						for(var i=0;i<data.length;i++)
							{
							console.log("usercodeeeeeeeeeeeee"+data[i]['userCode']);
							userCodeArray[i]=data[i]['userCode'];
							contactNumberArray[i]=data["contactNumber"];
							//console.log(contactNumberArray[i])
							
							}
						
					     $.ajax({
								"url" : "/academicactivity/getStudentPersonalDetails",
								"method" : "GET",
								 async :false,
								data:{"userCodeArray":userCodeArray.toString(),"participationId":particiaptionRequestId},
								success:function(data)
								{
									console.log("daaaaaaaaaaata"+data+data.length);
									
									if(data.length>0)
										{
									for(var i=0;i<data.length;i++)
										{
										onWardJourneyAmountEstim=parseFloat(onWardJourneyAmountEstim)+parseFloat(data[i]['on_ward_est_expenditure']);
										returnJourneyAmountEstim=parseFloat(returnJourneyAmountEstim)+parseFloat(data[i]['return_est_expenditure']);
										accomAmountEstim=parseFloat(accomAmountEstim)+parseFloat(data[i]['accmdtion_estim_expenditure']);
										}
										}
									else
										{
										onWardJourneyAmountEstim=0;
										returnJourneyAmountEstim=0;
										accomAmountEstim=0;
										}
								
										
									  $('.estim').each(function()
										   		{
									    	
										   var amnt66=$(this).val();
									        sum1=sum1+parseFloat(amnt66 ); 
									        $('#otherEstimeExpenseTotalId').val(sum1);
										   		});
									 
									 //  alert(sumonward);
									   console.log("onWardJourneyAmountEstim="+onWardJourneyAmountEstim+"accomAmountEstim");
									   var total= sum1+onWardJourneyAmountEstim+returnJourneyAmountEstim+accomAmountEstim;

									   $('#totalExpenseProgramId').val(total);
								},
								error(e)
								{
									console.log("Error while request"+e)
								}
				  
			  });
					
					},
					
					error(e)
					{
				
					}
				});
					
	   
	 
	   
	  
   }
 
 function getTravelAndAccomdationGrandtotal()
 {
	 sumonward=0;
	 sumreturn=0;
	 sumaccom=0;
	 onwardTotal=0;
	 returnTotal=0;
	 accomTotal=0;
	  $('.onwardamntadmtheads').each(function()
		   		{
	    	
		   var amnt66=$(this).val();
		   sumonward=sumonward+parseFloat(amnt66 );
		   		});
	  
	  $('.returnestimexpheads').each(function()
		   		{
	    	
		   var amnt66=$(this).val();
		   sumreturn=sumreturn+parseFloat(amnt66 ); 
		   		});
	 
	  $('.estimExpHeads').each(function()
		   		{
		   var amnt66=$(this).val();
		   sumaccom=sumreturn+parseFloat(amnt66 ); 
		 
		   		});
	  
	  onwardTotal=sumonward;
	  returnTotal=sumreturn;
	  accomTotal =sumaccom;
	  
	  
 }
 
$(document).ready(function() {
	
	$('.dp').datepicker({
	   	    format: 'yyyy-mm-dd',							   
	   		autoclose:true,
	   		clearBtn:true
	   	});
	
	getAllPrograms();
	loadAllAcademicActivityTypeDetails();
	loadAllparticipationRequest();
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

//add master data details

   $('#paricipationMasterForm').unbind().bind('submit',function(event) 
   {
   	    /*     console.log("entrrr in paricipationMasterForm");
   	     if($("#yesId").prop('checked') == true){
    	    	financeImplied='y';
    	     }
    	     
    		if($("#noId").prop('checked') == true){
    	   		console.log("checked false");
    	   				
    	   				financeImplied="N";
    	   			} */
    		
    		
    	/* 	if($('#teamyes').is(':checked'))
    			{
    			
    			alert("it's checked teamyes");
    			}
    		if($('#teamyno').is(':checked'))
			{
			
			alert("it's checked teamno");
			}
    		
    		
    	    if($("#teamyes").prop('checked') == true){
    	    	teamActivity='y';
    	     }
    	     
    		if($("#teamyno").prop('checked') == true){
    	   		
    	   				
    	   		teamActivity="N";
    	   			}
    		
    		console.log("teamactivity"+teamActivity+"fi"+financeImplied); */
   	           	        
   	  //   if((financeImplied==null)||(teamActivity==null))
        	//{
        	
        	//showMessage("please fill all fields.");
        	//event.preventDefault();
        
        	//}
   	    // else
   	    	// {
   			event.preventDefault();
   		 $('#activityId').prop('disabled', false);
   		 $('#competionStart').prop('disabled', false);
   		 $('#competionEnd').prop('disabled', false);
   		$('#competionTypeId').prop('disabled', false);
   			var formCheck = "success";
   			var formData = $(this).serializeArray();
   			formData.push({"name":"rollNo","value":loginRollNo});
   				$('#submitModel').modal('show');
   
   				$('#confId').unbind().bind('click',function(){
   
   					console.log("confirm clicked...");
                     var i=0;
   					$.ajax({
   						"url" : "/academicactivity/saveActivityParticiaptionMasterData",
   						"method" : "POST",
   						 data : formData,
   						 success : function(data) {
   							 
   							 console.log(formData);
   							 
   							particiaptionRequestId=data['particiaptionRequestId'];
   							showMessage("Parcipation Request is successfully added"+particiaptionRequestId);
   							
   							
   						
   							
   							
   							
                               
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
                //  }
})	

//adding team member details
$('#TeamMembersetailsForm').unbind().bind('submit',function(event)
   		{
	        console.log("entrrrr in TeamMembersetailsForm");
   			event.preventDefault();
   			var formData=$(this).serializeArray();
   			activityCode=$("#activityId"). val();
   			formData.push({"name":"activityCode","value":activityCode});
   			var i=0;
   			var rollno= $("#rollnoId").val();
   			
   			var value = $("#nameId option:selected"); 
   			
   			var contactNo=  $('#phNoId').val();
   			
   			
   			//var value = $('#nameId').val(); 
   			var stname=value.text();
   			formData.push({"name":"participateRequestId","value":particiaptionRequestId});
   			var userCode=$("#nameId").val();
   			formData.push({"name":"userCode","value":userCode});
   		//	formData.push({"name":"contactNo","value":contactNo});
   			
   			
   		    $.ajax(
   	   		{
   		    	"url":"/academicactivity/addMemberPersonDeatails",
   				"method":"POST",
   	  			 data:formData,
   	  			 success:function(data)
   	  			{
   	  				
   	  				showMessage("Saved particpant");
   	  				console.log(data);
   	  				
   	  			    console.log("savedPartipntcount"+savedPartipntcount);
   	  				nameArray[savedPartipntcount]=stname;
   	  				rollNoArray[savedPartipntcount]=rollno;
   	  			     savedPartipntcount++;
   	  				//resourcepers[resPersCount]=data['resId'];
   	  		       // resPersCount++;
   	  			
   	  				$("#TeamMembersetailsForm").trigger('reset');
   	  			},
   	  			error:function(e)
   	  			{
   	  				showMessage("Error in adding resource person. Contact Admin");
   	  			}
   	   		});
   		});
   		
   		
   		//to get the active level of selected activity
   		$('#activityId').change(function(){ 
   			activityCode = $(this).val();
              // console.log("activityIdddddddddd"+value);
      
      $.ajax({
			"url" : "/academicactivity/getAllActivityById",
			"method" : "GET",						
			data : {"activityId":activityCode},
			success : function(data) 
			{
				var dateFrom=data['dateFrom'];
				var dateTo=data['dateTo'];
				var dateFrom1 = dateFrom.split("T")[0];
				var dateTo1=dateTo.split("T")[0];
				
				
				$('#competionStart').val(dateFrom1);
				$('#competionEnd').val(dateTo1);
				
				var activityLevel=data['activityLevel'];
				if(activityLevel=="international")
					{
					$('#internationalpartbtn'). prop('disabled', false);
					}
				else
					{
					//alert("no");
					$('#internationalpartbtn'). prop('disabled', true);
				 //    $(".ui-button").prop('disabled', true); 
					}
				
			},
			error(e)
			{
				console.log(e)
			}

   		});
   		});
   		
   	//add onwardstartpoint button
 		  $("#onWardTravelDetailsBtnId" ).on('click',function(event) {
 		        $('#TravelDetailsOnwardGrid').show();
 				$('#TravelDetailsOnwardGridBody').empty();
 				$('#TravelDetailReturnGrid').hide();
 				$("#accomdationBynuals").hide();
 			    $("#AccomadationDetailsGrid").hide();
     	  	    console.log("savedPartipntcount"+savedPartipntcount)
     	  	    var added=0;
     	  	 var addedafterRollnolength;
				for(var i=0;i<nameArray.length;i++)
				{
					
					var sino=i+1;
					var onwardstarpoint=rollNoArray[i]+"onwardstartpoint";
					var onwardstartDate=rollNoArray[i]+"onwardstartDate";
					var onwarddestination=rollNoArray[i]+"onwarddestrination";
					var onwardenddate=rollNoArray[i]+"onwardenddate";
					var onwardmdtravel=rollNoArray[i]+"onwardmdtravel";
					var onwardestimexp=rollNoArray+"onwardestimexp";
					var onwardamntadmt=rollNoArray+"onwardamntadmt";
					console.log(nameArray[i]);
					console.log(rollNoArray[i]);
					
					if(rollNoArray[i]!=onwardrollnoadded[i])
						{
						addedafterRollno[added]=rollNoArray[i];
						
						added++;
					 tr=$('<tr><td>'+sino+'</td><td>'+nameArray[i]+'</td><td>'+rollNoArray[i]+'</td><td><input type="text" class="onwardstarpointheads form-control" id="'+onwardstarpoint+'" size="50"></input></td><td><input type="text" class="onwardstartDateheads form-control dp" id="'+onwardstartDate+'" size="50"></input></td><td><input type="text" class="onwarddestinationheads form-control" id="'+onwarddestination+'" size="50"></input></td><td><input type="text" class="onwardenddateheads dp form-control" id="'+onwardenddate+'" size="50"></input></td><td><input type="text" class="onwardmdtravelheads  form-control" id="'+onwardmdtravel+'" size="50"></input></td><td><input type="text" class="onwardestimexpheads form-control" id="'+onwardestimexp+'" size="50"></input></td><td><input type="text" class="onwardamntadmtheads dp form-control" id="'+onwardamntadmt+'" disabled="disabled" value="0" size="50"></input></td><tr>');
					 $('#TravelDetailsOnwardGridBody').append(tr);
						}
					
					
				}
 			  	   
     	  		$('.dp').datepicker({
     		   	    format: 'yyyy-mm-dd',							   
     		   		autoclose:true,
     		   		clearBtn:true
     		   	});
     	  	    
     	  	      /*   tr=$('<tr><td></td><td><td></td><td><td></td><td><input type="button" id="travelDetailsOnwardButton" value="Add"/></td><td></td><td></td><td></td><td></td></tr>');
 			  	     $('#TravelDetailsOnwardGridBody').append(tr); */
 			  	  
 			  	     
 			   	     $("#travelDetailsOnwardButton").on('click',function(event) {   
 			   		 console.log("entrrrrr in travelDetailsOnwardButton");
 			   		var i=0;
 			   	   	var j=0;
 			   	   	var k=0;
 			   	   	var l=0;
 			   	   	var m=0;
 			   	   	var n=0;
 			   	   	var o=0;
 			   	   	var sumonward=0;
 			   	   	
 			   		studentDetails={};  
  			   		   for(var p=0;p<addedafterRollno.length;p++)
  			   			   {
  			   		
  			   			  var studrollno=addedafterRollno[p];
  			   			  studentDetails[studrollno]={};
  			   			  studentDetails[studrollno]['rollNo']=studrollno;
  		 	 			  studentDetails[studrollno]['participateRequestId']=particiaptionRequestId;
  		 	 			   studentDetails[studrollno]['activityCode']=activityCode;
  			   			  
  			   			   }
  			   			 
  			   	 	 	   $('.onwardstarpointheads').each(function()
  	 			   	   			{
  			   	 	 		   
  			   	 	 		   var studrollno=addedafterRollno[i];
  			   	 	 	   	   var amnt=$(this).val();
  			   	 	 	   	
  			   	 	 	        studentDetails[studrollno]['onwardStartPoint']=amnt;
  			   	 	 	        
  			   	 	 	         onwardStartPointArr[i]=amnt;
  			   	 	 	        
  			   	 	 	        
  	 			   	    	     i++;
  	 			   	   			});
  	 			   	 	
  	 			   	 $('.onwardstartDateheads').each(function()
  	 				   			{
  	 			   	        var studrollno=addedafterRollno[j];
  	 				      	var amnt=$(this).val();
  	 				        studentDetails[studrollno]['onwardStartDate']=amnt;
  	 				          onwardStartDateArr[j]=amnt;
  	 				        j++;
  	 				     
  	 				   			});
  	 			   	 
  	 			   	 
  	 			   	$('.onwarddestinationheads').each(function()
  	 			   			{
  	 			   		
  	 			   	    var studrollno=addedafterRollno[k];
  	 			      	var amnt=$(this).val();
  	 			        studentDetails[studrollno]['onwardDestination']=amnt;
  	 			          onwarddestinationArr[k]=amnt;
  	 			      	k++;
  	 			   			});

  	 			   	$('.onwardenddateheads').each(function()
  	 			   			{
  	 			   	    var studrollno=addedafterRollno[l];
  	 			      	var amnt=$(this).val();
  	 			        studentDetails[studrollno]['onwardEndDate']=amnt;
  	 			         onwardenddateArr[l]=amnt;
  	 			     	l++;
  	 			   			});

  	 			   	$('.onwardmdtravelheads').each(function()
  	 			 			{
  	 			   	    var studrollno=addedafterRollno[m];
  	 			 		var amnt=$(this).val();
  	 			 	    studentDetails[studrollno]['onwardModeofTravel']=amnt;
  	 			 	     onwardmdtravelArray[m]=amnt;
  	 			 		m++;
  	 			 			});

  	 			   	$('.onwardestimexpheads').each(function()
  	 			 			{
  	 			   	    var studrollno=addedafterRollno[n];
  	 			 		var amnt=$(this).val();
  	 			 	    studentDetails[studrollno]['onWardEstExpenditure']=amnt;
  	 			 	     onwardestimexpArray[n]=amnt;
  	 			 		n=n+1;
  	 				   var amnt66=$(this).val();
  	 				  // alert(amnt66);
  	 				   sumonward=sumonward+parseFloat(amnt66 );

  	 			 		
  	 			 		
  	 			 			});
  	 			 	       
  	 			   	$('.onwardamntadmtheads').each(function()
  	 			 			{
  	 			   	    var studrollno=addedafterRollno[o];
  	 			 		var amnt=$(this).val();
  	 			 	    studentDetails[studrollno]['onwardAmountAdmitted']=amnt;
  	 			 		o=o+1;
  	 			 		
  	 			 			});
 			   			
 			   		
 			   		
 			   	var formDataArray=new Array();
 			 //before submit
 			 	for (let key in studentDetails) 
 			 	{
 			 		if (studentDetails.hasOwnProperty(key)) 
 			 		{
 			 			formDataArray.push(studentDetails[key])	
 			 		}
 			 	}
 			 		 $.ajax({
 						"url" : "/academicactivity/addMemberOnwardDetails",
 						"method" : "POST",
 						 data : {'participantJouneryTravelWrapper':formDataArray},
 						 success : function(data) {
 							 
 							showMessage("Onward Journey details is successfully added");
 							
 							onwardrollnoadded = rollNoArray.slice()
 							
 							 $('#TravelDetailsOnwardGridBody').empty();
 							 console.log("success"+data);
 						 },
 						 error(e)
 						 {
 							 
 						 }
 						 });
 				
 			   	 });	         
 		  });
 		
 		$('#regbyNualsId').change(function()
 		{
 			var value=$(this).val();
 			//alert(value);
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
 		 
 		
 		//to get the total of other spent and estim expense
 		
 		
   		
 		 $("#otherExpensesId" ).on('click',function(event) {
 			 
 			// alert("clicked");
 		 	  
 			getTotalExpense();
 		   });
   		//add travelDetails Return
   				//add onwardstartpoint button
   		  $("#returnTravelDetailsBtnId" ).on('click',function(event) {
   			
                       
   				$('#TravelDetailReturnGrid').show();
   				$('#TravelDetailsReturnGridBody').empty();
   				$('#TravelDetailsOnwardGrid').hide();
   				$("#accomdationBynuals").hide();
   			    $("#AccomadationDetailsGrid").hide();
   			  var added1=0;
   			//	$('#TravelDetailsOnwardGrid').empty();
   			//$('#TravelDetailsOnwardGrid').empty();
   			  	     console.log("savedPartipntcount"+savedPartipntcount.length)
   					for(var i=0;i<nameArray.length;i++)
   					{
   						
   						var sino=i+1;
   						var returnstarpoint=rollNoArray[i]+"returnstartpoint";
   						var returnstartDate=rollNoArray[i]+"returnstartDate";
   						var returndestination=rollNoArray[i]+"returndestrination";
   						var returnenddate=rollNoArray[i]+"returnenddate";
   						var returnmdtravel=rollNoArray[i]+"returnmdtravel";
   						var returnestimexp=rollNoArray+"returnestimexp";
   						var returnamntadmt=rollNoArray+"returnamntadmt";
   						console.log(nameArray[i]);
   						console.log(rollNoArray[i]);
   						if(rollNoArray[i]!=returnrollnoadded[i])
                           {
   							
   							returnaddedafterRollno[added1]=rollNoArray[i];
   							added1++;
   						 tr=$('<tr><td>'+sino+'</td><td>'+nameArray[i]+'</td><td>'+rollNoArray[i]+'</td><td><input type="text" class="returnstarpointheads form-control" id="'+returnstarpoint+'" size="15"></input></td><td><input type="text" class="returnstartDateheads dp form-control" id="'+returnstartDate+'" size="15"></input></td><td><input type="text" class="returndestinationheads form-control" id="'+returndestination+'" size="15"></input></td><td><input type="text" class="returnEnddateheads dp form-control" id="'+returnenddate+'" size="15"></input></td><td><input type="text" class="returnmodeoftravelheads  form-control" id="'+returnmdtravel+'" size="15"></input></td><td><input type="text" class="returnestimexpheads  form-control" id="'+returnestimexp+'" size="15"></input></td><td><input type="text" class="returnamntadmtheads  form-control" id="'+returnamntadmt+'" size="15" value="0" disabled="disabled"></input></td><tr>');
   						 $('#TravelDetailsReturnGridBody').append(tr);
   					       }
   					}
   			  	     
   			  	$('.dp').datepicker({
				   	    format: 'yyyy-mm-dd',							   
				   		autoclose:true,
				   		clearBtn:true
				   	});
   			  	   /*   tr=$('<tr><td></td><td><td></td><td><td></td><td><input type="button" value="Add Return details" id="travelDetailsReturnButton"/></td><td></td><td></td><td></td><td></td></tr>'); */
   			  	    /*  $('#TravelDetailsReturnGridBody').append(tr); */
   			  	     
   			  	     
   			  	     
   			  	     
   			  	  $("#travelDetailsReturnButton").on('click',function(event) {   
   			  		var sumreturn=0;
  			   		 console.log("entrrrrr in travelDetailsReturnButton");
  			   		var i=0;
  			   	   	var j=0;
  			   	   	var k=0;
  			   	   	var l=0;
  			   	   	var m=0;
  			   	   	var n=0;
  			   	   	var o=0;
  			 var returnaddedafterRollnolength;
  			   		studentDetails={};  
   			   		   for(var p=0;p<returnaddedafterRollno.length;p++)
   			   			   {
   			   			  var studrollno=returnaddedafterRollno[p];
   			   			  studentDetails[studrollno]={};
   			   			  studentDetails[studrollno]['rollNo']=studrollno;
   		 	 			  studentDetails[studrollno]['participateRequestId']=particiaptionRequestId;
   		 	 			  studentDetails[studrollno]['activityCode']=activityCode;
   			   			   }
   			   			 
   			   	 	 	   $('.returnstarpointheads').each(function()
   	 			   	   			{
   			   	 	 		   
   			   	 	 		   var studrollno=returnaddedafterRollno[i];
   			   	 	 	   	   var amnt=$(this).val();
   			   	 	 	   	
   			   	 	 	        studentDetails[studrollno]['returnStarPoint']=amnt;
   	 			   	    	     i++;
   	 			   	   			});
   	 			   	 	
   	 			   	 $('.returnstartDateheads').each(function()
   	 				   			{
   	 			   	        var studrollno=returnaddedafterRollno[j];
   	 				      	var amnt=$(this).val();
   	 				        studentDetails[studrollno]['returnStartDate']=amnt;
   	 				        j++;
   	 				     
   	 				   			});
   	 			
   	 			   	 
   	 			   	$('.returndestinationheads').each(function()
   	 			   			{
   	 			   		
   	 			   	    var studrollno=returnaddedafterRollno[k];
   	 			      	var amnt=$(this).val();
   	 			        studentDetails[studrollno]['returnDestination']=amnt;
   	 			      	k++;
   	 			   			});

   	 			   	$('.returnEnddateheads').each(function()
   	 			   			{
   	 			   	    var studrollno=returnaddedafterRollno[l];
   	 			      	var amnt=$(this).val();
   	 			        studentDetails[studrollno]['returnEnddate']=amnt;
   	 			     	l++;
   	 			   			});

   	 				 
   	 			   	$('.returnmodeoftravelheads').each(function()
   	 			 			{
   	 			   	    var studrollno=returnaddedafterRollno[m];
   	 			 		var amnt=$(this).val();
   	 			 	    studentDetails[studrollno]['returnModeofTravel']=amnt;
   	 			 		m++;
   	 			 			});

   	 			   	$('.returnestimexpheads').each(function()
   	 			 			{
   	 			   	    var studrollno=returnaddedafterRollno[n];
   	 			 		var amnt=$(this).val();
   	 			 	    studentDetails[studrollno]['returnEstimExp']=amnt;
   	 			 		n=n+1;
   	 			 		
   	 				   var amnt66=$(this).val();
   	 			        sumreturn=sumreturn+parseFloat(amnt66 ); 

   	 			 			});
   	 			 	       
   	 			   	$('.returnamntadmtheads').each(function()
   	 			 			{
   	 			   	    var studrollno=returnaddedafterRollno[o];
   	 			 		var amnt=$(this).val();
   	 			 	    studentDetails[studrollno]['returnAmntAdmt']=amnt;
   	 			 		o=o+1;
   	 			 			});
  			   		
  			   		
  			   	var formDataArray=new Array();
  			 //before submit
  			 	for (let key in studentDetails) 
  			 	{
  			 		if (studentDetails.hasOwnProperty(key)) 
  			 		{
  			 			formDataArray.push(studentDetails[key])	
  			 		}
  			 	}
  			 		 $.ajax({
  						"url" : "/academicactivity/addMemberReturnDetails",
  						"method" : "POST",
  						 data : {'participantJouneryReturnTravelWrapper':formDataArray},
  						 success : function(data) {
  							 
  							showMessage("Return Journey details is successfully added");
  							returnrollnoadded = rollNoArray.slice()
                            $('#TravelDetailsReturnGridBody').empty();
  							 console.log("success"+data);
  						 },
  						 error(e)
  						 {
  							 
  						 }
  						 });
  				 
   	 	 });
   		  });
   		
   		  $("#accomadationDetailsDetailsBtnId").on('click',function(event) {
   			  
   			 // alert("accomadation");
   			  try
   			  {
   				$("#accomdationBynuals").show();
   				$('#TravelDetailReturnGrid').hide();
   				$('#TravelDetailsOnwardGrid').hide();
   				
   				
   			  }
   			  catch(e)
   			  {
   				  console.log(e);
   			  }
   			
   		  
   	         $("#accomdationByself").change(function(){ 
		       var accomdationByself = $(this).val();
		       console.log("accomadation by self is clicked");
		       if(accomdationByself=="no")
		    	   {
		    	   $("#AccomadationDetailsGrid").show();
		    	   $('#accomadationGridBody').empty();
		    	   
		    	   
		    	  var added2=0;
		    	   for(var i=0;i<nameArray.length;i++)
  					{
		    	        var sino=i+1;
						var expendtureDetails=rollNoArray[i]+"expendtredtails";
						var accomStartDate=rollNoArray[i]+"accomStartDate";
						var accomEndDate=rollNoArray[i]+"accomEndDate";
						var noDays=rollNoArray[i]+"noDays";
						var dailyRate=rollNoArray[i]+"dailyRate";
						var billNo=rollNoArray[i]+"billNo";
						var estimExp=rollNoArray[i]+"estimExp";
						var amntAdmt=rollNoArray[i]+"amntAdmt";
						if(rollNoArray[i]!=accomrollnoadded[i])
							{
							
							accomaddedafterRollno[added2]=rollNoArray[i];
							
						 tr=$('<tr><td>'+sino+'</td><td>'+nameArray[i]+'</td><td>'+rollNoArray[i]+'</td><td><input type="text" class="expendtredtailsHeads form-control" id="'+expendtureDetails+'" ></input></td><td><input type="text" class="accomStartDateHeads dp form-control" id="'+accomStartDate+'" ></input></td><td><input type="text" class="accomEndDateHeads dp form-control" id="'+accomEndDate+'" ></input></td><td><input type="text" class="noDaysheads  form-control" id="'+noDays+'"></input></td><td><input type="text" class="dailyRateHeads form-control" id="'+dailyRate+'"></input></td><td><input type="text" class="billNoheads  form-control" id="'+billNo+'"></input></td><td><input type="text"  class="estimExpHeads  form-control" id="'+estimExp+'"></input> </td><tr>');
   						 $('#accomadationGridBody').append(tr);
   						 added2++;
  					      }
   					
  					}
		    	   
		    	 //  tr=$('<tr><td></td><td><td></td><td><td></td><td><input type="button" value="Add" id="accomadationDetails"/></td><td></td><td></td><td></td><td></td></tr>');
 			  	 //  $('#accomadationGridBody').append(tr);
 			  	$('.dp').datepicker({
				   	    format: 'yyyy-mm-dd',							   
				   		autoclose:true,
				   		clearBtn:true
				   	});
    	   
 			  	   
 			  	   
 			  	  $("#accomadationDetails").on('click',function(event) {   
   			   		 console.log("entrrrrr in accomadationDetails");
   			   		var i=0;
   			   	   	var j=0;
   			   	   	var k=0;
   			   	   	var l=0;
   			   	   	var m=0;
   			   	   	var n=0;
   			   	   	var o=0;
   			   	   	var sum=0;
   			   	    var sumaccom=0;
   			   		studentDetails={};  
    			   		   for(var p=0;p<accomaddedafterRollno.length;p++)
    			   			   {

    			   			  var studrollno=accomaddedafterRollno[p];
    			   			  studentDetails[studrollno]={};
    			   			  studentDetails[studrollno]['rollNo']=studrollno;
    		 	 			  studentDetails[studrollno]['participateRequestId']=particiaptionRequestId;
    		 	 			  studentDetails[studrollno]['activityCode']=activityCode;
    		 	 		      var accm=$('#accomdationByself').val();
        	 			   	
    	    	 			   studentDetails[studrollno]['freeOfCostAccomadation']=accm;
    			   			   }
    			   			 
    			   	 	 	   $('.expendtredtailsHeads').each(function()
    	 			   	   			{
    			   	 	 		   
    			   	 	 		   var studrollno=accomaddedafterRollno[i];
    			   	 	 	   	   var amnt=$(this).val();
    			   	 	 	   	
    			   	 	 	        studentDetails[studrollno]['expendtureDetails']=amnt;
    	 			   	    	     i++;
    	 			   	   			});
    	 			   	 	
    	 			   	 $('.accomStartDateHeads').each(function()
    	 				   			{
    	 			   	        var studrollno=accomaddedafterRollno[j];
    	 				      	var amnt=$(this).val();
    	 				        studentDetails[studrollno]['accomStartDate']=amnt;
    	 				        j++;
    	 				     
    	 				   			});
    	 			
    	 			   	 
    	 			   	$('.accomEndDateHeads').each(function()
    	 			   			{
    	 			   		
    	 			   	    var studrollno=accomaddedafterRollno[k];
    	 			      	var amnt=$(this).val();
    	 			        studentDetails[studrollno]['accomEndDate']=amnt;
    	 			      	k++;
    	 			   			});

    	 			   	$('.noDaysheads').each(function()
    	 			   			{
    	 			   	    var studrollno=accomaddedafterRollno[l];
    	 			      	var amnt=$(this).val();
    	 			        studentDetails[studrollno]['noDays']=amnt;
    	 			     	l++;
    	 			   			});

    	 				 
    	 			   	$('.dailyRateHeads').each(function()
    	 			 			{
    	 			   	    var studrollno=accomaddedafterRollno[m];
    	 			 		var amnt=$(this).val();
    	 			 	    studentDetails[studrollno]['dailyRate']=amnt;
    	 			 		m++;
    	 			 			});

    	 			   	$('.billNoheads').each(function()
    	 			 			{
    	 			   	    var studrollno=accomaddedafterRollno[n];
    	 			 		var amnt=$(this).val();
    	 			 	    studentDetails[studrollno]['billNo']=amnt;
    	 			 		n=n+1;
    	 			 			});
    	 			 	       
    	 			   	$('.estimExpHeads').each(function()
    	 			 			{
    	 			   	    var studrollno=accomaddedafterRollno[o];
    	 			 		var amnt=$(this).val();
    	 			 	    studentDetails[studrollno]['estimExp']=amnt;
    	 			 		o=o+1;
    	 				   var amnt66=$(this).val();
    	 				   sumaccom=sumaccom+parseFloat(amnt66 ); 

    	 			 			});
    	 			
   			   		
   			   		
   			   	var formDataArray=new Array();
   			 //before submit
   			 	for (let key in studentDetails) 
   			 	{
   			 		if (studentDetails.hasOwnProperty(key)) 
   			 		{
   			 			formDataArray.push(studentDetails[key])	
   			 		}
   			 	}
   			 
   			 
   			 		 $.ajax({
   						"url" : "/academicactivity/addMemberAccomadationDetails",
   						"method" : "POST",
   						 data : {'participantAccomadationWrapper':formDataArray},
   						 success : function(data) {
   							 
   							showMessage("Accomadation details is successfully added");
   							accomrollnoadded = rollNoArray.slice();
   							console.log("success"+data);
   						 $('#accomadationGridBody').empty();
   						 },
   						 error(e)
   						 {
   							 
   						 }
   						 });
   				
   			   	 });	 
   	         }
 			  	  if(accomdationByself=="yes")
		    	   {
 			  		  
 			  		  
 			  		studentDetails={};  
 			  	   $("#AccomadationDetailsGrid").hide();
 			  		  $("#accomadationDetails").on('click',function(event) {   
 	   			   		 console.log("entrrrrr in travelDetailsReturnButton");
 	   			   		var i=0;
 	   			   	   	var j=0;
 	   			   	   	var k=0;
 	   			   	   	var l=0;
 	   			   	   	var m=0;
 	   			   	   	var n=0;
 	   			   	   	var o=0;
 	   			   	   	var added3=0;
 	   			   		studentDetails={};  
 	   			    for(var i=0;i<nameArray.length;i++)
  					{
 	   				if(rollNoArray[i]!=accomrollnoadded[i])
					{
					
					accomaddedafterRollno[added3]=rollNoArray[i];
					added3++;
 	   			    	
  					}
  					}
 	   			  
 	   			   		if(accomaddedafterRollno.length!=0)
 	   			   		{
 	   			   		
 	    			   		   for(var p=0;p<accomaddedafterRollno.length;p++)
 	    			   			   {

 	    			   			  var studrollno=accomaddedafterRollno[p];
 	    			   			  studentDetails[studrollno]={};
 	    			   			  studentDetails[studrollno]['rollNo']=studrollno;
 	    		 	 			  studentDetails[studrollno]['participateRequestId']=particiaptionRequestId;
 	    		 	 			  studentDetails[studrollno]['activityCode']=activityCode;
 	    			   			   }
 	   			   		
 			  		  }
 	   			   		
 	   			   		else
 	   			   			
 	   			   			{
 	   			   			
 	   			   	 for(var p=0;p<rollNoArray.length;p++)
		   			   {

		   			  var studrollno=rollNoArray[p];
		   			  studentDetails[studrollno]={};
		   			  studentDetails[studrollno]['rollNo']=studrollno;
	 	 			  studentDetails[studrollno]['participateRequestId']=particiaptionRequestId;
	 	 			  studentDetails[studrollno]['activityCode']=activityCode;
	 	 			  
	 	 	 	 	var accm=$('#accomdationByself').val();
 	 			   	
  	 			   studentDetails[studrollno]['freeOfCostAccomadation']=accm;
		   			   }
 	   			   			}
 	   			   			
 	   			   			
 	   			   			
 	    			  
 	   			   			
 	   			   		
 	   			   	var formDataArray=new Array();
 	   			 //before submit
 	   			 	for (let key in studentDetails) 
 	   			 	{
 	   			 		if (studentDetails.hasOwnProperty(key)) 
 	   			 		{
 	   			 			formDataArray.push(studentDetails[key])	
 	   			 		}
 	   			 	}
 	   			 
 	   			 
 	   			 		 $.ajax({
 	   			 			 
 	   						"url" : "/academicactivity/addMemberAccomadationDetails",
 	   						"method" : "POST",
 	   						 data : {'participantAccomadationWrapper':formDataArray},
 	   						 success : function(data) {
 	   							 
 	   							showMessage("Accomadation details is successfully added");
 	   							 console.log("success"+data);
 	   							accomrollnoadded = rollNoArray.slice();
 	   						     $('#accomadationGridBody').empty();
 	   						 },
 	   						 error(e)
 	   						 {
 	   							 
 	   						 }
 	   						 });
 	   			
		    	   });
 			  	  }
   	         });
   	         
   		  });
		      /*  else
		    	   {
		    	   $('#accomadationGridBody').empty();
		    	   tr=$('<tr><td></td><td><td></td><td><td></td><td><input type="button" value="Add" id="accomadationDetails"/></td><td></td><td></td><td></td><td></td></tr>');
 			  	   $('#accomadationGridBody').append(tr);
 			  	
		    	   } */
   	 //  });
 			  		  //}
   	         

   		
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
	        					"url" : "/academicactivity/addActivityParticipantOtherExpenses",
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
	        							showMessage("Other Details are successfully added");
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
	        							showMessage("Bank details successfully added");
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
   		
   		$('#remarksForm').unbind().bind('submit',function(event) {
   	        event.preventDefault();      
   	        var remarks=$("#enteredRemarksId").val();
   		 $('#competionTypeId').prop('disabled', false);
   	       
   	        $('#submitModel').modal('show');
   			$('#confId').unbind().bind('click',function(){
   				console.log("confirm clicked...");
   				$.ajax({
   					"url" : "/academicactivity/addRemaks",
   					"method" : "GET",
   					 data : {"particiaptionRequestId":particiaptionRequestId,"remarks":remarks},
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
   						loadAllAcademicActivityTypeDetails();
   					 },
   					 error : function(e) {
   		   		   		showMessage("Error in approval. Contact Admin");
   		   		   		}

   	             });
   				$('#submitModel').modal('hide');
   			//	loadParticipantRequestApproval();
   				
   			});
   			$('#cancelId').unbind().bind('click',function(){
   				   
   					console.log("Cancel clicked...");

   					$('#submitModel').modal('hide');
   					
   				});
	
   		});
   		
   		$('#viewonwardbt').unbind().bind('click',function(){
   			
   			
   			getPersonalDetails(particiaptionRequestId);
   			
   			
   		});
   		
   		
  		$('#viewperbt').unbind().bind('click',function(){
   			
   			
   			getPersonalDetails(particiaptionRequestId);
   			
   			
   		});
   		
   		

	
  		
	
	$('#viewreturnbt').unbind().bind('click',function(){
		
		
		getPersonalDetails(particiaptionRequestId);
		
		
	});
  		
$('#viewaccombt').unbind().bind('click',function(){
		
		
		getPersonalDetails(particiaptionRequestId);
		
		
	});
	
  		
   		
   		$('#printBt').click(function()
   				{
   			$('#reportDiv').show();
   			$.ajax({
					"url" : "/academicactivity/getAllParticipantRequestById",
					"method" : "GET",						
					data : {"particiaptionRequestId":particiaptionRequestId},
					success : function(data) 
					{
						$('#titletd').append(title);
						$('#hostingInstitutiontd').append(data['hostingInstitution']);
						$('#datenotifytd').append(data['dateNotifction']);
						$('#competionStarttd').append(data['competionStart']);
						$('#competionEndtd').append(data['competionEnd']);
						$('#travelonwardstarttd').append(data['travelDateOnwardStart']);
						$('#travelonwardEndtd').append(data['travelDateOnwardReturn']);
						$('#travelreturnstarttd').append(data['travelDateReturnStart']);
						$('#travelreturnEndtd').append(data['travelDateReturnEnd']);
						
						$('#venuecell').append(data['activityVenue']);
						
						    $('#currencyNonINRtd').append(data['currencyNonINR']);
							$('#hostingCountryIntertd').append(data['hostingCountry']);
							$('#estVisaChargetd').append(data['estVisaCharge']);
							$('#conversionRatetd').append(data['conversionRate']);
							$('#estLocalConvtd').append(data['estLocalConv']);
							$('#estLoadgingtd').append(data['estLoadging']);
			
					
   			
   	   	  
   		   
   	    /* for(var p=0;p<rollNoArray.length;p++)
			   {
			   
			
			  var studrollno=rollNoArray[p];
			  studentDetails[studrollno]={};
			  studentDetails[studrollno]['rollNo']=studrollno;
			   } */
			   $('#partiper').empty();
			   $('#partiper').append('<thead><tr><th scope="col">Name of the Team members</th><th scope="col">Roll No</th><th scope="col">Contact Number</th></tr>');
   		/*  for(var i=0;i<nameArray.length;i++)
			{
	         var sino=i+1;
			 var tr=$('<tr><td>'+nameArray[i]+'</td><td>'+rollNoArray[i]+'</td><td></td></tr>');
				 $('#partiper').append(tr);
		     } */
			
   		     $('#onwardTb').empty();
		     $('#returnTb').empty();    
		     $('#accomTb').empty();  
		   
   	    $('#onwardTb').append('<thead><tr><th scope="col">Name of the Team members</th><th scope="col">Start Point</th><th scope="col">Start Date</th><th scope="col">Destination</th><th scope="col">End date</th><th scope="col">Mode of Travel</th><th>Estimated Expenditure</th></tr></thead>');
   	 $('#returnTb').append('<thead><tr><th scope="col">Name of the Team members</th><th scope="col">Start Point</th><th scope="col">Start Date</th><th scope="col">Destination</th><th scope="col">End date</th><th scope="col">Mode of Travel</th><th>Estimated Expenditure</th></tr></thead>');
   	 $('#accomTb').append('<thead><tr><th scope="col">If No , details of Expenditure</th><th scope="col">Start Date</th><th scope="col">End Date</th><th scope="col">No of Days</th><th scope="col">Daily Rate</th><th scope="col">Bill No</th><th>Estimated Expenditure</th></tr></thead>');
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
						
						var freeofcost;
						for(var j=0;j<data1.length;j++)
							{
							
						namertArr[j]=data1[j]['student_personal_student_name'];
						console.log("namertArr[j]"+namertArr[j]);
						
						rollrtArr[j]=data1[j]['student_admission_roll_no'];
						console.log("rollrtArr[j]"+rollrtArr[j]);
						
						contArr[j]=data1[j]['contact_number'];
						console.log("contArr[j]"+contArr[j]);
						
						onwardStartPointArr[j]=data1[j]['onward_start_point'];
						console.log("onwardStartPointArr[j]"+onwardStartPointArr[j]);
						
						onwardStartDateArr[j]=data1[j]['onward_start_date'];
						console.log("onwardStartDateArr[j]"+onwardStartDateArr[j]);
						
						onwarddestinationArr[j]=data1[j]['onward_destination'];
						console.log("onwarddestinationArr[j]"+onwarddestinationArr[j]);
						
						onwardenddateArr[j]=data1[j]['onward_end_date'];
						console.log("onwardenddateArr[j]"+onwardenddateArr[j]);
						
						onwardmdtravelArray[j]=data1[j]['onward_modeof_travel'];
						console.log("onwardmdtravelArray[j]"+onwardmdtravelArray[j]);
						
						onwardestimexpArray[j]=data1[j]['on_ward_est_expenditure'];
						console.log("onwardestimexpArray[j]"+onwardestimexpArray[j]);
						
						
						 returnStartPointArr[j]=data1[j]['return_start_point'];
						 returnStartDateArr[j]=data1[j]['return_start_date'];
						 returndestinationArr[j]=data1[j]['return_destination'];
						 returnenddateArr[j]=data1[j]['return_end_date'];
						 returnModeoftravelArray[j]=data1[j]['return_modeof_travel'];
					      returnestimexpArray[j]=data1[j]['return_est_expenditure'];

					      freeofcost=data1[j]['free_of_cost_accomadation'];
						
					      $('#accombySelfreprt').append(freeofcost);

						
						 var tr=$('<tr><td>'+namertArr[j]+'</td><td>'+rollrtArr[j]+'</td><td>'+contArr[j]+'</td></tr>');
						 $('#partiper').append(tr);
						
						
						var tr=$('<tr><td>'+namertArr[j]+'</td><td>'+onwardStartPointArr[j]+'</td><td>'+onwardStartDateArr[j]+'</td><td>'+onwarddestinationArr[j]+'</td><td>'+onwardenddateArr[j]+'</td><td>'+onwardmdtravelArray[j]+'</td><td>'+onwardestimexpArray[j]+'</td><tr>');
						console.log(tr);
						 $('#onwardTb').append(tr);
						 
						 
							var tr=$('<tr><td>'+namertArr[j]+'</td><td>'+returnStartPointArr[j]+'</td><td>'+returnStartDateArr[j]+'</td><td>'+returndestinationArr[j]+'</td><td>'+returnenddateArr[j]+'</td><td>'+returnModeoftravelArray[j]+'</td><td>'+returnestimexpArray[j]+'</td><tr>');
							console.log(tr);
							 $('#returnTb').append(tr);
							 
							 
							 
							 accomodation_exp_headArr[j]=data1[j]['accomodation_exp_head'];
							 accmdation_start_dateArr[j]=data1[j]['accmdation_start_date'];
							
							 accmdation_end_dateArr[j]=data1[j]['accmdation_end_date'];
							 bill_noArray[j]=data1[j]['bill_no'];
							 daily_rateArray[j]=data1[j]['daily_rate'];		 
							 no_of_daysArray[j]=data1[j]['no_of_days'];
							 accmdtion_estim_expenditureArray[j]=data1[j]['accmdtion_estim_expenditure'];
                             var tr=$('<tr><td>'+accomodation_exp_headArr[j]+'</td><td>'+accmdation_start_dateArr[j]+'</td><td>'+accmdation_end_dateArr[j]+'</td><td>'+no_of_daysArray[j]+'</td><td>'+daily_rateArray[j]+'</td><td>'+bill_noArray[j]+'</td><td>'+accmdtion_estim_expenditureArray[j]+'</td><tr>');
								console.log(tr);
								 $('#accomTb').append(tr);
							
							}
						
					},
					error : function(e)
					{
					}
				});
   		 
			},
			error : function(e)
			{
			}
		});
   		$.ajax({
			
			"url":"/academicactivity/getParticiapntOtherExpensesDetailsByParticipantRequestId",
			"method":"GET",
			data:{"participantRequestId":particiaptionRequestId},
			success:function(d)
			{
				for(var key in d)
				{
  					
  					$('#'+key).empty();
				//	$('#'+key).val(d[key]);
					$('#'+key).append(d[key]);
				}
				
				
				
				 
			},
			error : function(e)
			{
			}
		});
   		
   		
   	 reportPrinting('reportDiv');
					},
					error : function(e)
					{
					}
				});
//    		 for(var i=0;i<nameArray.length;i++)
// 			{
//    		  var studrollno=rollNoArray[i];
   			 
//    		tr=$('<tr><td>'+nameArray[i]+'</td><td>'+rollNoArray[i]+'</td><td>'+onwardStartPointArr[i]+'</td><td>'+onwardStartDateArr[i]+'</td><td>'+'</td><td>'+onwarddestinationArr[i]+'</td><td>'+onwardenddateArr[i]+'</td><td>'+onwardmdtravelArray[i]+'</td><td>'+onwardestimexpArray+'</td><td><tr>');
//    	 $('#onwardTb').append
// 			}
   		
});
   		});
			
</script>
<div  id="gridRow" class="card card-info card-outline">
  	<div class="card-header">
		  <h3 >List Of Activities For Participation Request</h3>
	  
  	</div>


    <div class="card-body">
	   	<div class="table-responsive">
				<table class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border" id="financiallyApprovedActivityTable">
			 
				</table>
		</div>
	</div>
</div>

<div class="card card-info card-outline">
   <div class="card-header">
      <h3>Program Participation Request</h3>
   </div>
<div class="card-body" id="activityParticipationReq">
<div class="row">
 <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	<div id="tabs">
	  <ul>
	    <li style="background-color:#265bab;text-color:white;"><a href="#tabs-1" style="color:#d5e6e4" >Program Details</a></li>
	    <li style="background-color:#5a2066;text-color:white;"><a href="#tabs-2" style="color:#d5e6e4" >Team Members Details</a></li>
	    <li style="background-color:#3d5e48;text-color:white;"><a href="#tabs-3" style="color:#d5e6e4" >Other Expenses</a></li>
     	<li style="background-color:#d92176;text-color:white;"><a href="#tabs-4" style="color:#d5e6e4">Other Details</a></li>
     	<li style="background-color:#d92176;text-color:white;"><a href="#tabs-5" style="color:#d5e6e4">Remarks</a></li>
	  </ul>
	  <div id="tabs-1">
	   <div id="participationMasterDetId">
	   <form id="paricipationMasterForm">
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
            
              <!-- <div class="row">
               <div class="form-group" style="width: 100%" class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                  <label for="dateofNotification"> Date on which Competion Held</label> 
               </div>
               </div> -->
              <div class="row">
              <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                <label for="dateofNotification"> Date on which Competion Held</label> 
              <div class="form-group" style="width: 100%" class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
               <label for="FromDate">From<span
                  class="required">*</span></label>
               <input type="text" id="competionStart" name="competionStart" class="form-control dp"  required />
            </div>
             </div>
            
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              
            <div class="form-group" style="width: 100%" class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
               <label for="todate">To<span
                  class="required">*</span></label>
               <input type="text"  class="form-control dp" id="competionEnd" name="competionEnd" required />
            </div>
            </div>
            </div>
         
              <div class="row">
              <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="form-group" style="width: 100%" class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
            
                <label for="TravalDateonward">Onward Start Date<span
                  class="required">*</span></label>
               <input type="text"  class="form-control dp" id="travelonwardstart" name="travelDateOnwardStart"required />
            </div>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              
            <div class="form-group" style="width: 100%" class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
               <label for="End">Onward End Date<span
                  class="required">*</span></label>
               <input type="text"  class="form-control dp" id="travelonwardEnd" name="travelDateOnwardReturn" required />
            </div>
            </div>
            </div>
           
            <div class="row">
              <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="form-group" style="width: 100%" class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
               <label for="TravalDatereturnstart">Return Start Date<span
                  class="required">*</span></label>
               <input type="text"  class="form-control dp" id="travelreturnstart" name="travelDateReturnStart" required />
            </div>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              
            <div class="form-group" style="width: 100%" class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
               <label for="TravalDatereturnend">Return End Date<span
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
               <input type="text"  class="form-control" id="competionTypeId" name="competionType"  />
            </div>
            
              <div class="form-group" style="width: 100%" class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
               <label for="competionTyoe">Team Activity<span
                  class="required">*</span></label>
                <label class="radio-inline"> <input
                  type="radio" id="teamyes"  value="yes" name="teamParticipation" required>YES
               </label> <label class="radio-inline"> <input type="radio" id="teamno" value="no" name="teamParticipation" required>NO
               </label>
            </div>
              <div class="form-group">
               <label for="financeImpliedId">Financial Implication Include<span
                  class="required">*</span></label> <label class="radio-inline"> <input
                  type="radio" id="yesId"  value="yes" name="financialImplications" required>YES
               </label> <label class="radio-inline"> <input type="radio" id="noId" value="no" name="financialImplications" required>NO
               </label>
            </div>
            
            
           <!--    <div class="form-group">
               <label for="financeImpliedId">Financial Implication Include<span
                  class="required">*</span></label> <label class="radio-inline"> <input
                  type="radio" id="yesId"  value="yes" name="finance">YES
               </label> <label class="radio-inline"> <input type="radio" id="noId" value="no" name="finance">NO
               </label>
            </div> -->
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
 
   <button type="submit" id="activityPaticipationMastersubmit" class="btn btn-primary">Submit</button>
 </form>
   </div>
   </div>
    <div id="tabs-2">
    <div id="otherExpenseId">
	   <div class="row">
	    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	         <p>
               <button type="button" class="btn btn-link" data-toggle="modal"
                  data-target="#exampleModalLong">Add Team Members Person Details</button>
               
                <button type="button" class="btn btn-link" data-toggle="modal"
                  data-target="#onwardsJourney" id="onWardTravelDetailsBtnId">Add Onward Travel Details</button>
               
                 <button type="button" class="btn btn-link" data-toggle="modal"
                  data-target="#returnJourney"  id="returnTravelDetailsBtnId">Add Return Travel Details</button>
                  
                  <button type="button" class="btn btn-link" data-toggle="modal"
                  data-target="#accom"  id="accomadationDetailsDetailsBtnId">Add Accomadation Details</button>
               
                <!--  <button class="btn btn-link" type="button"
                 id="onWardTravelDetailsBtnId">Add Onward Travel Details
               </button> -->
            <!--     <button class="btn btn-link" type="button"
                 id="returnTravelDetailsBtnId">Add Return Travel Details
               </button> -->
             
            </p>
            </div>
            </div>
             <div class="row">
	    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <p>
            
                  <button class="btn btn-link" type="button" id="viewperbt" data-toggle="collapse" data-target="#memberPersonal" aria-expanded="false" aria-controls="collapseExample">
                  View Member Person Details
          </button>
                   <button class="btn btn-link" type="button" id="viewonwardbt"
              data-toggle="collapse" data-target="#memberOnwardDetails"  aria-expanded="false" aria-controls="collapseExample">View OnwardTravel Details
            </button>
             <button class="btn btn-link" type="button"
              data-toggle="collapse" data-target="#memberReturnDetails"   id="viewreturnbt" aria-expanded="false" aria-controls="collapseExample">View Return Travel Details
            </button>
            <button class="btn btn-link" type="button"
            data-toggle="collapse" data-target="#accomadationDetailsgrid"  id="viewaccombt" aria-expanded="false" aria-controls="collapseExample">View Accomadation Details
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
 	                                 <div class="collapse" id="accomadationDetailsgrid">
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
     
	    </div>
	    </div>
	    
	    
	    </div>
	</div>
	

	
	<!-- --------------tab3-->
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
                        <option value="">Select</option>
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
      <th scope="col">Amount Spent</th>
      <th scope="col">Estimated Expenditure</th>
    </tr>
  </thead>
  <tbody>
      <tr id="regfeetr">
      <th scope="row">1</th>
      <td>Registration Fee</td>
      <td><input type="text" name="regFeeSpent1" id="regFeeSpentId1" disabled="disabled"/></td>
      <td><input type="number" name="regFeeSpent" id="regFeeSpentId" class="actual" value="0" disabled="disabled"/></td>
      <td><input type="number" name="regFeeEstimExp" id="regFeeEstimExpId" class="estim" value="0"/></td>
     </tr>
  

    <tr>
      <th scope="row">2</th>
      <td>Print outs (Memorials)</td>
      <td><input type="number" name="printoutsMemPages" id="printoutsMemPagesId" value="0"/></td>
      <td><input type="number" name="printoutMemSpent" id="printoutMemSpentId" class="actual" value="0" disabled="disabled"/></td>
      <td><input type="number" name="printoutMemAmtEst" id="printoutMemAmtEstId" class="estim" value="0"/></td>
    </tr>
     <tr>
      <th scope="row">3</th>
      <td>Photocopy(Memorials)</td>
      <td><input type="number" name="photocopyMemPages" id="photocopyMemPagesId" value="0"/></td>
      <td><input type="number" name="photocopyMemSpent" id="photocopyMemSpentId" class="actual" value="0" disabled="disabled"/></td>
      <td><input type="number" name="photocopyMemAmtEst" id="photocopyMemAmtEstId" class="estim" value="0"/></td> 
    </tr>
      <tr>
      <th scope="row">4</th>
      <td>Binding(Memorials)</td>
      <td><input type="text" name="" id="" disabled="disabled"/></td>
      <td><input type="number" name="bindingAmntSpent" id="bindingAmntSpentId" class="actual" value="0" disabled="disabled"/></td>
      <td><input type="number" name="bindingMemAmtEst"  id="bindingMemAmtEstId" class="estim" value="0"/></td>
    </tr>
    <tr>
      <th scope="row">5</th>
      <td>Cost of paper (Memorials)</td>
      <td><input type="number" name="costMemPages" id="costMemPagesId" value="0"/></td>
      <td><input type="number" name="costMemSpent" id="costMemSpentId" class="actual" value="0" disabled="disabled"/></td>
      <td><input type="number" name="costMemAmtEst" id="costMemAmtEstId" class="estim" value="0"/></td>
    </tr>
      <tr>
      <th scope="row">6</th>
      <td>Print outs (Compendiums)</td>
      <td><input type="number" name="printoutsComPages" id="printoutsComPagesId" value="0"/></td>
      <td><input type="number" name="printoutComSpent" id="printoutComSpentId" class="actual" value="0" disabled="disabled"/></td>
      <td><input type="number" name="printoutComAmtEst" id="printoutComAmtEstId" class="estim" value="0"/></td>
    </tr>
    <tr>
      <th scope="row">7</th>
      <td>Photocopy (Compendiums)</td>
      <td><input type="number" name="photocopyComPages" id="photocopyComPagesId" value="0"/></td>
      <td><input type="number" name="photocopyComSpent" id="photocopyComSpentId" class="actual" value="0" disabled="disabled"/></td>
      <td><input type="number" name="photocopyComAmtEst" id="photocopyComAmtEstId" class="estim" value="0"/></td>
    </tr>
    <tr>
    <tr>
      <th scope="row">8</th>
      <td>Binding(Compendiums)</td>
      <td><input type="number" name="" id="" disabled="disabled"/></td>
      <td><input type="number" name="bindingComSpent" id="bindingComSpent" class="actual" value="0" disabled="disabled"/></td>
      <td><input type="number" name="bindingComAmtEst" id="bindingComAmtEstId" class="estim" value="0"/></td>
    </tr>
    <tr>
      <th scope="row">9</th>
      <td>Cost of paper (Compendiums)</td>
      <td><input type="number" name="paperNoCom" id="paperNoCom" value="0"/></td>
      <td><input type="number" name="costPaperComSpent" id="costPaperComSpentId" class="actual" value="0" disabled="disabled"/></td>
      <td><input type="number" name="costOfPaperComEstmExp" id="costOfPaperComEstmExpId" class="estim" value="0"/></td>
    </tr>
    <tr>
      <th scope="row">10</th>
      <td>Courier charges</td>
      <td><input type="number" name="" id="" disabled="disabled"/></td>
      <td><input type="number" name="courierChargesAmntSpent" id="courierChargesAmntSpentId" class="actual" value="0" disabled="disabled"/></td>
      <td><input type="number" name="courierChargesEstmExp" id="courierChargesEstmExpId" class="estim" value="0"/></td>
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
       <td></td>
      </tr>
    <tr>
      <th scope="row"></th>
      <td><b>Grant Total of Other Expenses</b></td>
      <td></td>
    <!--   <td><input type="text" name="otherExpenseTotal" id="otherSpentExpenseTotalId" /></td> -->
      <td><input type="text" name="" id="otherEstimeExpenseTotalId"/></td>
    </tr>
     <tr>
      <th scope="row"></th>
      <td><b>Total Estimated Expenditure for the Particiption</b></td>
      <td></td>
      <td><input type="text" name="totalExpenseProgram" id="totalExpenseProgramId" /></td>
       <td></td>
    </tr>
     <tr>
      <th scope="row"></th>
      <td><b></b></td>
      <td></td>
      <td></td>
      <td><input type="submit" name="" id=""/></td>
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
	
	<!-- tab 4 -->
	
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
      <td> <select class="form-control selectpicker" name="priorAdministrativeSanction" id="priorAdministrativeSanctionId" required>
                        <option value="">Select</option>
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
                        <option value="">Select</option>
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
                        <option value="">Select</option>
                        <option value="yes">Yes</option>
                        <option value="no">No</option>
                     </select></td>
      </tr>
       <tr>
      <td>10</td>
      <td>Whether the team has submitted the receipt  from the NUALS Library on submission of the compendium?</td>
      <td><select class="form-control selectpicker" name="compendiumReceipt" id="compendiumReceiptId"
                        required>
                        <option value="">Select</option>
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
      <td></td>
      <td></td>
      <td><input type="submit" value="Add"/></td>
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
	  <form id="bankDetailsForm">
	    <div class="row">
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
      <td><input type="submit" value="Add"/></td>
      </tr>
	 </table>
	 </div>          
	   </div>
	   </div>
	   </div>
	   </form>

	
	   </div>
	   </div>
	   
	<div id="tabs-5">
   <form id="remarksForm">
	   <div id="otherDetailsId">
	   <div class="row">
	    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="form-group">
               <label for="remarks"> Remarks</label>
               <textarea id="enteredRemarksId" name="enteredRemarks" rows="4" cols="50"
                  class="form-control"></textarea>
            </div>
            <div class="form-group" style="text-align: center">
               <button type="submit" class="btn btn-primary" id="activityFinance">SUBMIT</button>
               
              
               <button type="button"  class="btn btn-primary" id="printBt">Print</button>
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
                           id="phNoId" required />
                     </div>
                     </div>
                     <div class="modal-footer">
                     <button type="button" class="btn btn-secondary"
                        data-dismiss="modal">Close</button>
                     <button type="submit" class="btn btn-primary"
                        id="resourceButtonAdd">Submit</button>
                        <button class="btn btn-primary" id="printBt">Print</button>
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
</div>



<div  id="gridRow" class="card card-info card-outline">
  	<div class="card-header">
	
  	<div class="card-header">
		  <h3 >List Of Requested Program Participation</h3>
	  
  	</div>


    <div class="card-body">
	   	<div class="table-responsive">
				<table class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border" id="submittedParticipationRequest">
			 
				</table>
		</div>
	</div>
</div>
	  
  	</div>


    <div class="card-body">
	   	<div class="table-responsive">
				<table class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border" id="financiallyApprovedActivityTable">
			 
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


<!-- ------------------------------ onward journey details ------------->
<div class="modal fade" id="onwardsJourney" tabindex="-1"
   role="dialog" aria-labelledby="exampleModalLongTitle"
   aria-hidden="true">
   <div class="modal-dialog modal-xl" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLonggTitle">Add Member Onward Journey Details
            </h5>
            <button type="button" class="close" data-dismiss="modal"
               aria-label="Close">
            <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="modal-body">
               <div class="row">
                  <div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
                           <div id="TravelDetailsOnwardGrid" class="row" style="display: none">
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
                           <tbody id="TravelDetailsOnwardGridBody">
                           </tbody>
                        </table>
                     </div>
                  </div>
               </div>
                   </div>
                   </div>
                     <div class="modal-footer">
                     <button type="button" class="btn btn-secondary"
                        data-dismiss="modal">Close</button>
                     <button type="submit" class="btn btn-primary"
                        id="travelDetailsOnwardButton">Submit</button>
                  </div>
               </div>
         </div>
      </div>
   </div>
<!-- return details -->
<div class="modal fade" id="returnJourney" tabindex="-1"
   role="dialog" aria-labelledby="exampleModalLongTitle"
   aria-hidden="true">
   <div class="modal-dialog modal-xl" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLonggTitle">Add Member Return Journey Details
            </h5>
            <button type="button" class="close" data-dismiss="modal"
               aria-label="Close">
            <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="modal-body">
               <div class="row">
                  <div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
                  <div id="TravelDetailReturnGrid" class="row" style="display: none">
                  <div class="col-md-12">
                     <div class="table-responsive cell-border">
                        <table id="gridTableMapper1" class="table table-striped">
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
               </div>
                   </div>
                   </div>
                     <div class="modal-footer">
                     <button type="button" class="btn btn-secondary"
                        data-dismiss="modal">Close</button>
                     <button type="submit" class="btn btn-primary"
                        id="travelDetailsReturnButton">Submit</button>
                  </div>
               </div>
         </div>
      </div>
   </div>
   <!-- accomadation details -->
<div class="modal fade" id="accom" tabindex="-1"
   role="dialog" aria-labelledby="exampleModalLongTitle"
   aria-hidden="true">
   <div class="modal-dialog modal-xl" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLonggTitle">Add Member Accomadation Details
            </h5>
            <button type="button" class="close" data-dismiss="modal"
               aria-label="Close">
            <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="modal-body">
         
               <div class="row" id="accomdationBynuals" style="display:none">
                  <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                  <div class="form-group">
                  <label for="type">Weather Provided Free of Cost by the conducting Institution<span class="required">*</span></label>
                  <div style="width: 100%">
                  <div style="display: inline-block; width: 100%">
                     <select class="form-control selectpicker" name="type" id="accomdationByself" name="freeOfCostAccomadation"
                        required>
                        <option value="">Select</option>
                        <option value="yes">Yes</option>
                        <option value="no">No</option>
                     </select>
                  </div>
               </div>
                </div>
                  </div>
                  
                  </div>
               <div class="row">
                  <div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
                  <div id="AccomadationDetailsGrid" class="row" style="display:none">
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
                     <div class="modal-footer">
                     <button type="button" class="btn btn-secondary"
                        data-dismiss="modal">Close</button>
                     <button type="submit" class="btn btn-primary"
                        id="accomadationDetails">Submit</button>
                  </div>
               </div>
         </div>
      </div>
   </div>
   
   
   
   <div class="modal fade" id="programDeatilsmodal" tabindex="-1"
role="dialog" aria-labelledby="exampleModalLongTitle"
aria-hidden="true">
<div class="modal-dialog" role="document">
   <div class="modal-content">
      <div class="modal-header">
         <h5 class="modal-title" id="exampleModalLongTitle">Program Details
            Person Details
         </h5>
         <button type="button" class="close" data-dismiss="modal"
            aria-label="Close">
         <span aria-hidden="true">&times;</span>
         </button>
      </div>
      <div class="modal-body">
<div class="row">
	<div class="col-md-12">
		<div class="table-responsive" id="reportDiv">
			<table class="table table-striped" id="repTable">
				<tr><td colspan="4">
				 <img src="../images/logo.jpg" style="width: 40px;">
				</tr>
				<tr><td colspan="4">
				
				</tr>
				<tr><td>Name/Title of the Programme</td><td colspan="3" id="title"></td></tr>
				<tr><td colspan="2">Date of Event/Program</td><td id="dateFrom"></td><td id="dateTo"></td></tr>
				<tr><td colspan="4">Number Centres Involved in Organising the Programme</td></tr>
				<tr><td colspan="4"><table id="centrestab" class="table table-striped"></table> </td></tr>
				<tr><td colspan="4"><u> A Brief Description of the Program </u></td></tr>
				<tr><td id="description" colspan="4"></td></tr>
				<tr><td colspan="4"><u>Content,Relevance And Target Group</u></td></tr>
				<tr><td id="targetGroup" colspan="4"></td></tr>

			</table>	
		</div>
	</div>

</div>
           </div>
      </div>
   </div>
</div>
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   <!-- advance payment for advance Form-->
<div class="modal fade" id="dialogAdvancePaymentForAdvanceRequestForm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Program Details</h5>
		 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div> 
        <div class="modal-body">
        	<form id="activityMasterDetailsForm" >
		   <div class="row">
		   
		   </div>
		   </form>
        </div>
        </div>
        </div>
        </div>
        
        
        <!-- report start here -->
        
        
        <div class="row">
	    <div class="col-md-12">
		<div class="table-responsive" id="reportDiv" style="display:none">
			<table class="table table-striped" id="repTable">
				<tr><td colspan="4">
				 <img src="../images/logo.jpg" style="width: 40px;">
				</tr>
				<tr td colspan="4"><center> Request for Administrative approval for Program Participation</center><td></tr>
				<tr><td colspan="4">
				</tr>
				<tr><td>Name/Title of the Program</td><td colspan="3" id="titletd"></td></tr>
				<tr><td>Hosting Institution</td><td  colspan="3" id="hostingInstitutiontd"></td></tr>
				<tr><td>Date on Notification</td><td colspan="3" id="datenotifytd"></td></tr>
				<tr><td>Dates on which the competition held</td><td id="competionStarttd"></td><td id="competionEndtd"></td></tr>
				<tr><td>Travel Dates - Onward</td><td id="travelonwardstarttd"></td><td id="travelonwardEndtd"></td></tr>
				<tr><td>Travel Dates - Return</td><td id="travelreturnstarttd"></td><td id="travelreturnEndtd"></td></tr>
				<tr><td>Venue of the competition</td><td colspan="3" id="venuecell"></td></tr>
				<tr><td><b>For International Participants Only</b></td><td id="centerLength" colspan="3" ></td></tr>
				<tr><td>Hostin Institution</td><td id="hostingInstitutiontd" colspan="3" ></td></tr>
				<tr><td>Host Country</td><td id="hostingCountryIntertd" colspan="3" ></td></tr>
				<tr><td>Currency requirement other than INR</td><td id="currencyNonINRtd" colspan="3" ></td></tr>
				<tr><td>Approximate convertion rate on proposal date INR</td><td id="conversionRatetd" colspan="3" ></td></tr>
				<tr><td>Estimated Visa Charges in INR</td><td id="estVisaChargetd" colspan="3" ></td></tr>
				<tr><td>Estimated Local Convayance Expenses INR</td><td id="estLocalConvtd" colspan="3" ></td></tr>
				<tr><td>Estimated Loadging Expenses INR</td><td id="estLoadgingtd" colspan="3" ></td></tr>
				<tr><td><table id="partiper" class="table table-striped"></table></td></tr>
				<tr><td  colspan="3">Travel Details - Onward</td></tr>
				<tr><td><table id="onwardTb" class="table table-striped"></table></td></tr>
				<tr><td  colspan="3">Travel Details - Return</td></tr>
				<tr><td><table id="returnTb" class="table table-striped"></table></td></tr>
				<tr><td  colspan="3">Accomadation - Details</td></tr>
				<tr><td>Weather Provided Free of Cost by the conducting Institution</td><td id="accombySelfreprt"></td></tr>
				<tr><td><table id="accomTb" class="table table-striped"></table></td></tr>
				<tr>
			<th>Other Expenses</th>
			<th>No of Pages</th>
			<th></th>
			<th>Estimated Expenditure</th>
			<th>Amount Admitted</th>
		</tr><tr>
			<td>Registration Fee</td>
			<td>Whether paid by University</td>
			<td id="regFeeByNuals"></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td>Print outs (Memorials)</td>
			<td id="printoutsMemPages"></td>
			<td></td>
			<td id="printoutMemAmtEst"></td>
			<td></td>
		</tr>
		<tr>
			<td>Photocopy (Memorials)</td>
		    <td id="photocopyMemPages"></td>
		    <td></td>
			<td id="photocopyMemAmtEst"></td>
			<td></td>
		</tr>
		<tr>
			<td>Binding (Memorials)</td>
			<td id=""></td>
			<td></td>
			<td id="bindingMemAmtEst"></td>
			<td></td>
		</tr>
		<tr>
			<td>Cost of paper (Memorials)</td>
		    <td id="costMemPages"></td>
		    <td></td>
			<td id="costMemAmtEst"></td>
			<td></td>
		</tr>
		<tr>
			<td>Print outs (Compendiums)</td>
			 <td id="printoutsComPages"></td>
			 <td></td>
			<td id="printoutComAmtEst"></td>
			<td></td>
		</tr>
		<tr>
			<td>Photocopy (Compendiums)</td>
			 <td id="photocopyComPages"></td>
			 <td></td>
			<td id="photocopyComAmtEst"></td>
			<td></td>
		</tr>
		<tr>
			<td>Binding (Compendiums)</td>
			 <td id=""></td>
			 <td></td>
			<td id="bindingComAmtEst"></td>
			<td></td>
		</tr>
		<tr>
			<td>Cost of paper (Compendiums)</td>
		    <td id="paperNoCom"></td>
		    <td></td>
			<td id="costOfPaperComEstmExp"></td>
			<td></td>
		</tr>
		<tr>
			<td>Courier charges</td>
			 <td id=""></td>
			 <td></td>
			<td id="courierChargesEstmExp"></td>
			<td></td>
		</tr>
		<th>Other Details</th>
		<th>Values</th>	
			<tr>
			<td>Total No. of Books submitted as Memorials for Petitioner/Plaintiff</td>
			<td id="noMemorialsPetitioner"></td>
		</tr>
		<tr>
			<td>Total No. of Books submitted as Memorials for Respondent/ Defendant</td>
			<td id="noMemorialsRespondent"></td>
		</tr>
		<tr>
			<td>Total No. of pages in a Single Memorial for Petitioner/Plaintiff</td>
			<td id="pagesPetitioner"></td>
		</tr>
		<tr>
			<td>Total No. of pages in a Single Memorial for Respondent/ Defendant</td>
			<td id="pagesRespondent"></td>
		</tr>
		<tr>
			<td>Total number of compendiums prepared</td>
			<td id="compendiums"></td>
		</tr>
		<tr>
			<td>Total No. of pages in a single compendium</td>
			<td id="pagesCompendium"></td>
		</tr>
		<tr>
			<td>Whether the team has obtained prior administrative sanction to participate in the competition</td>
			<td id="priorAdministrativeSanction"></td>
		</tr>
		<tr>
			<td>Whether the team has already received the Registration fee to participate in this competition</td>
			<td id="receivedRegFee"></td>
		</tr>
		<tr>
			<td>Whether the team has submitted  the compendium to NUALS Library?</td>
			<td id="compendiumLibrary"></td>
		</tr>
		<tr>
			<td>Whether the team has submitted the receipt  from the NUALS Library on submission of the compendium?</td>
			<td id="compendiumReceiptId" ></td>
		</tr>
			<tr>
			<td>If the team has not submitted the compendium to NUALS Library reason for the same</td>
			<td id="reasonOnNoSubmission"></td>
		   </tr>	
		   <th>Payment Particulars</th>
		   <th></th>	
		   
		   <tr>
			<td>Requested Mode of payment</td>
			<td id="modeOfPayment"></td>
		</tr>
		
		<tr>
			<td>Name of the Authorised recipient</td>
			<td id="authRecName"></td>
		</tr>
			<tr>
			<td>Name of the Bank</td>
			<td id="bankName"></td>
		</tr>
		<tr>
			<td>Account No</td>
			<td id="accNo"></td>
		</tr>
		<tr>
			<td>IFSC </td>
			<td id="ifsc"></td>
		</tr>
			<tr>
			<td>Name of Branch </td>
			<td id="branch"></td>
		</tr> 
		<tr><td>Remarks </td></tr>
		
		<tr><td id="enteredRemarks"></td></tr>
		<tr><td>Signature of the Participants</td></tr>
		<tr><td>-</td></tr>
		<tr><td>-</td></tr>
		<tr><td>-</td></tr>
		<tr><td>-</td></tr>
		<tr><td>-</td></tr>
		<tr><td>Recommendations / Remarks of the Faculty Co-ordinator</td></tr>
		<tr><td>Kalamassery,</td><td></td><td></td>Faculty Co-ordinator<td></td></tr>
		<tr><td>Date</td><td></td><td></td><td></td></tr>
		<tr><td></td><td></td><td></td><td>Moot Court Society</td></tr>
	</table>	
		</div>
	</div>

</div>