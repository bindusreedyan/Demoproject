<link rel="stylesheet" href="./sfs_public/css/jquery-confirm.min.css">
<script src="./sfs_public/js/jquery-confirm.min.js"></script>
<script>

var unassignedGrantAmnt=[];
var finyear;
var brochureFilePath;
var centerarray=[];
var activityId;
var allocatedfundamountArray=[];
var exptilldateArray=[];
var Balance_exclude_unassignedGrantArray=[];
var estimatedExpArray=[];
var natnlsemnrcountArray=[];
var lecturecountArray=[];
var externalFundArray=[];
var ugcFundArray=[];
var ugcFunding;
var centerNameArray=[];
var externalfunding;
var approveFunding;
var honorMore3000data;
var exheadarray=[];
var centerlength=0;
var financeImplied;
var activityId;
var resourcepers=[];
//var externlfundArray=[];
var ugcfund=[];
var resPersCount=0;
var allocatedfundamount=0
var finYear;
var exptilldate=0;
var centerarray=[];
var approvedFunding
var ugcFunded;
var externalFunding;
var approvefunding;
var exheadarray=[];
var finyearexphead;
var allocationheadsarray=[];
var estimateheadsarray1=[];
var deviationheadsarray=[];
var commentsheadsarray=[];
var expadvreqarray=[];
var expadvreqreasonarray=[];
var updateActivityCenterFinanceflag="false";
var addActivityFinanceflag="false";
var addActivityEstimateflag="false";
var addActivityAdvanceEstimateRequestflag="false";
var financemap={};
var fileFormData;
var asno;
var financeFileFormData;
var externalfundamntIdArray=[];
var ugcFundIdArray=[];
var activityFinanceId;
var asGrantedAdvAmnt=[];
var asAmntGrantArray=[];
var brochureFilePath;
var finalRemark;
var expIncurred=[];
function reportPrinting(divId)
{
	 //  console.log(divId);
	   $('#reportDiv').show();
    var divToPrint = document.getElementById(divId);
    var htmlToPrint = '' +
        '<style type="text/css">' +
        'table th, table td {' +
    //  'border:1px solid #000;' +
        'padding:0.5em;' +
        '} table{width:100%}' +
        '</style>';
    htmlToPrint += divToPrint.outerHTML;
    var newWin = window.open("");
 //   newWin.document.write('<html style="width:794px; height:1122px; border: .5px solid"><head><title>National University of Advanced Legal Studies</title>');
    newWin.document.write(htmlToPrint);
    newWin.print();
    newWin.close();	
    
    $('#reportDiv').hide();
}

function getTotalEstimatedExpenditure()
{

	   var sum=0;
	   var sum1=0;
	   var sum2=0;
	   var sum3=0;
	   $('.estimateheads1').each(function()
		   		{
	    	
		   var amnt66=$(this).val();
	        sum=sum+parseFloat(amnt66 ); 
	        $('#totalEstExpIdd').val(sum);
	        totalEstimRep=sum
	       
		   		});
	   
	    $('.allocationheads').each(function()
		   		{
	    	
		   var amnt66=$(this).val();
	        sum1=sum1+parseFloat(amnt66 ); 
	        $('#totalallocationId').val(sum1);
	        totalNualsAllocationRep=sum1;
		   		});
	   
	   
	   $('.deviationheads').each(function()
		   		{
	    	
		   var amnt66=$(this).val();
	        sum2=sum2+parseFloat(amnt66 ); 
	        $('#totaldeviationId').val(sum2);
	        totaldeviationIdRep=sum2;
		   		}); 
	   
	   $('.asgrantedEstimHeads').each(function()
		   		{
	    	
		   var amnt66=$(this).val();
	        sum3=sum3+parseFloat(amnt66 ); 
	        $('#totalAsGrantedId').val(sum3);
	        totalAsGrantedRep=sum3
		   		}); 
	   
	      
	   
	  //   alert(sum);
	   
}

function loadAllActivityForFinalApproval() {
	
	$.ajax({
		"url" : "/academicactivity/getAllActivitesForCancel",
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
				"status" : "Status"
			}
		  ], "financiallyApprovedActivityTable", "select-checkbox");
		   onDataTableClick('financiallyApprovedActivityTable', function() {		
				if(selectedRowFromDataTable != null)
				{
					$('html, body').animate({ scrollTop: $('#activityApproval').offset().top }, 'slow');
					activityId=selectedRowFromDataTable[1];
					finyear=selectedRowFromDataTable[3];
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
									//	$('#dateFromId').val(data['dateFrom']);
										//$('#dateToId').val(data['dateTo']);
										
								    var data_splits1 =data['dateFrom'].split("T");
									var data_splits2 =data['dateTo'].split("T");
									$('#dateFromId').val(data_splits1[0]);
									$('#dateToId').val(data_splits2[0]);
										
										$('#finYearId').val(data['finyear']);
										$('#descriptionId').val(data['description']);
										$('#targetGroupId').val(data['targetGroup']);
										$('#outcomeId').val(data['outcome']);
										$('#deviationJustificationId').val(data['deviationJustification']);
										$('#typeId').val(data['type']);
										$('#activityLevelId').val(data['activityLevel']);
										$('#adminApprovalRemarksId').val(data['adminApprovalRemarks']);
										$('#titleFinanceId').val(data['title']);
										$('#dateFromFinanceId').val(data['dateFrom']);
										$('#dateToFinanceId').val(data['dateTo']);
										$('#financialApprovedRemarksId').val(data['financialApprovedRemarks']);
										brochureFilePath=data['brochureURL'];
										var financeImplied=data['financeImplied'];
										console.log(financeImplied);
										if(financeImplied=="Y")
											{
											$( "#yesId" ).prop( "checked", true );
											$("#academicfinalApprovedDateId").prop("disabled",true);
											$("#finalApprovedRemarksId").prop("disabled",true);
											
											}
										if(financeImplied=="N")
										{
										$( "#nosId" ).prop( "checked", true );
										$("#academicfinalApprovedDateId").prop("disabled",false);
										$("#finalApprovedRemarksId").prop("disabled",false);
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
				   	});
					
					
					//get Activityfinance data
					$.ajax({
						"url":"/academicfinance/getActivityFinance",
						"method":"GET",
						data:{"activityId":activityId},
						success:function(data)
							{
							activityFinanceId=data['activityFinanceCode'];
							externalfunding=data['externalFunding'];
							if(externalfunding=='y')
								{
								$('#externalfundSelect').val("yes")
								}
							if(externalfunding=='n')
							{
							$('#externalfundSelect').val("no")
							}
							
							ugcFunding=data['ugcFunded'];
							if(ugcFunding=='y')
								{
								$('#ugcSelect').val("yes");
								}
							if(ugcFunding=='n')
							{
							$('#ugcSelect').val("no");
							}
							approveFunding=data['approvedFunding'];
							if(approveFunding=='y')
							{
							$('#privateFund').val("yes");
							}
						if(approveFunding=='n')
						{
						$('#privateFund').val("no");
						}
							
						
						HonorMore3000data=data['honorMore3000'];
						if(HonorMore3000data=='y')
						{
						$('#HonorMore3000').val("yes");
						}
					if(HonorMore3000data=='n')
					{
					$('#HonorMore3000').val("no");
					}
				
						
						
						$('#UnivBeneficiariescountId').val(data['univBeneficiaries']);	
						$('#localBeneficiariesId').val(data['localBeneficiaries']);		
						$('#outstnBeneficiariesId').val(data['outstnBeneficiaries']);
						$('#intNatBeneficiariesId').val(data['intNatBeneficiaries']);
						$('#UnivResPersoncountId').val(data['univResPerson']);
						$('#localResPersonId').val(data['localResPerson']);
						$('#outstnResPersonId').val(data['outstnResPerson']);
						$('#intNatResPersonId').val(data['intNatResPerson']);
						
						$('#UnivBenTravelId').val(data['univBenTravel']);
						$('#localBenTravelId').val(data['localBenTravel']);
						$('#outstnBenTravelId').val(data['outstnBenTravel']);
						$('#intNatBenTravelId').val(data['intNatBenTravel']);
						$('#UnivResTraveltId').val(data['univResTravel']);
						$('#localResTravelId').val(data['localResTravel']);
						$('#outstnResTravelId').val(data['outstnResTravel']);
						$('#intNatResTravelId').val(data['intNatResTravel']);
						$('#AirTravelResUnivId').val(data['airTravelResUniv']);
						$('#AirTravelResLocalId').val(data['airTravelResLocal']);
						$('#AirTravelResOutstnId').val(data['airTravelResOutstn']);
						$('#AirTravelResIntnlId').val(data['airTravelResIntnl']);
						//total activityHeadExpense
						$('#totalEstExpId').val(data['totalEstExp']);
						$('#totalAdvanceReqId').val(data['totalAdvanceReq']);
						
						$('#totalEstExpIdd').val(data['total_estimated']);
							console.log(data);
							},
							error:function(e)
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
				   			centerlength=data.length;
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
				   			//checbxname="center"+num1;
				   			 $('#centerdiv1')
				   			 .append('<label class="checkbox-inline">')
				                .append('<input type="checkbox" id='+checbxid+' name='+num1+' checked="true" value='+data[i]['actvtyCenterKey']['cm']['centre_id']+'>'+data[i]['actvtyCenterKey']['cm']['centre_name'] )
				                .append('</label>')
				   			 }
				   			
				   			
				   		 $.ajax({
								"url":"/centerfund/getAllCenterFundDetailsByCenterIdsAndFinYear",
								"method":"GET",
								  data:{"currentfinyear":finyear,"centerarray":centerarray.toString()},
								 success:function(data1)
								 {
									    $('#divGridTable2').show();
						   				$('#centerActivityeBody2').empty();
						   			 var centerId1;
								//console.log(data1.length);	
								for(var i=0;i<data1.length;i++)
							         {
								     centerId1=data1[i]["centreFundKey"]["cm"]["centre_id"];
								 console.log("centerrrrrrid"+data1[i]["centreFundKey"]["cm"]["centre_id"]);
							 	     fundallocatedId=centerId1+"fundallocated";
								 	 expincuId=centerId1+"expincu";
									 Balance_exclude_unassignedGrant=centerId1+"blnceexunasgngrnt";
									 estimatedExp=centerId1+"estimatedExp";
									 natnlsemnrcount=centerId1+"nationalseminarcount";
							    	 lecturecount=centerId1+"lecturecount";
								     var centername=data1[i]["centreFundKey"]["cm"]["centre_name"];
								
								//array from centrefund;
								
								allocatedfundamountArray[i]=data1[i]["fundAmount"];
								console.log(allocatedfundamountArray[i]);
								var allocatedfundamount=data1[i]["fundAmount"];
								var exptilldate=data1[i]["expTillDate"];
								exptilldateArray[i]=data1[i]["expTillDate"];
								 unassignedGrantAmnt[i]=data1[i]["unAssignGrantBal"];
							//	 var tr=$('<tr><td>'+centername+'</td><td><input class="centerHeads form-control" id="'+fundallocatedId+'" type="number" name="'+fundallocatedId+'" value="'+allocatedfundamount+'" readonly></input></td><td><input class="centerHeads form-control" id="'+expincuId+'" type="number" value="'+exptilldate+'" name="'+expincuId+'" readonly></input></td><td><input class="balassign form-control" id="'+Balance_exclude_unassignedGrant+'" type="number" name="'+Balance_exclude_unassignedGrant+'" value="0"></input></td> <td><input class="estimateHeads form-control" id="'+estimatedExp+'" type="number" name="'+estimatedExp+'" value="0"></input></td> <td><input class="seminarHeads form-control" id="'+natnlsemnrcount+'" type="number" name="'+natnlsemnrcount+'" value="0"></input></td><td><input class="lectureHeads form-control" id="'+lecturecount+'" type="number" name="'+lecturecount+'" value="0"></input></td><tr>');
								//	 $('#centerActivityeBody2').append(tr);
							          }
						 
		      					},
								 error(e)
								 {
							     }
								 });

				   	  
   					     $.ajax({
						        "url":"/academicfinance/getAllActiveCenterByCenterIdsAndactiviy",
						         "method":"GET",
						         data:{"activityId":activityId,"centerarray":centerarray.toString()},
							 success:function(data2)
							 {
								 var j;
								 console.log("data2 length"+data2.length);
								 for(j=0;j<data2.length;j++)
									 {
									 console.log(data2[j]["balance_exclude_unassignedGrant"]);
									 Balance_exclude_unassignedGrantArray[j]=data2[j]["balance_exclude_unassignedGrant"];
									 console.log(Balance_exclude_unassignedGrantArray[j])
									 estimatedExpArray[j]=data2[j]["estimatedExp"];
									 natnlsemnrcountArray[j]=data2[j]["national_seminar_count"];
									 lecturecountArray[j]=data2[j]["extension_lectures_count"];
									 externalFundArray[j]=data2[j]["externalFund"];
									 ugcFundArray[j]=data2[j]["ugcFund"];
									 expIncurred[j]=data2[j]['expTillDate'];
									 }
							
							 console.log("getAllCenterFundDetailsByCenterIdsAndFinYear"+data2);
							 
							 $('#divGridTable4').show();
	       	       				$('#centerActivityeBody4').empty();
	       	       			 $('#divGridTable3').show();
				       			$('#centerActivityeBody3').empty();
							 for(var j=0;j<data2.length;j++)
							 {
								 
								 console.log("data2[j]['actvtyCenterKey']['ac']['activityCode']);"+data2[j]['actvtyCenterKey']['ac']['activityCode']);
								 
   					             var centerid1=data2[j]['actvtyCenterKey']['cm']['centre_id'];
								 fundallocatedId=centerid1+"fundallocated";
							 	 expincuId=centerid1+"expincu";
								 Balance_exclude_unassignedGrant=centerid1+"blnceexunasgngrnt";
								 estimatedExp=centerid1+"estimatedExp";
								 natnlsemnrcount=centerid1+"nationalseminarcount";
						    	 lecturecount=centerid1+"lecturecount";
						    	 var externalfundamnt=centerid1+"externalfund";
						    	 var unassignGrant=centerid1+"unassignGrant";
						    	 externalfundamntIdArray[j]=externalfundamnt;
						    	 
								 console.log("centerNameArray[j]"+centerNameArray[j]);
								 console.log("centerNameArray[j]"+allocatedfundamountArray[j]);

								 console.log("centerNameArray[j]"+exptilldateArray[j]);

								 console.log("centerNameArray[j]"+Balance_exclude_unassignedGrantArray[j]);
								 console.log("centerNameArray[j]"+estimatedExpArray[j]);
								 console.log("centerNameArray[j]"+natnlsemnrcountArray[j]);
								 console.log("centerNameArray[j]"+lecturecountArray[j]);
								 console.log("centerNameArray[j]"+centerNameArray[j]);
								 console.log("centerNameArray[j]"+fundallocatedId);
								 console.log("centerNameArray[j]"+fundallocatedId);

								 
								 console.log("centerNameArray[j]"+expincuId);
								 console.log("centerNameArray[j]"+Balance_exclude_unassignedGrant);
								 console.log("centerNameArray[j]"+estimatedExp);
								 console.log("centerNameArray[j]"+natnlsemnrcount);
								 console.log("centerNameArray[j]"+lecturecount);
								 console.log("centerNameArray[j]"+externalfundamnt);
							 var tr=$('<tr><td>'+centerNameArray[j]+'</td><td><input class="centerHeads form-control" id="'+fundallocatedId+'" type="number" name="'+fundallocatedId+'" value="'+allocatedfundamountArray[j]+'" readonly></input></td><td><input class="centerHeads form-control" id="'+expincuId+'" type="number" value="'+ expIncurred[j]+'" name="'+expincuId+'" ></input></td><td><input class="balassign form-control" id="'+Balance_exclude_unassignedGrant+'" type="number" name="'+Balance_exclude_unassignedGrant+'" value="'+Balance_exclude_unassignedGrantArray[j]+'"></input></td> <td><input class="estimateHeads form-control" id="'+estimatedExp+'" type="number" name="'+estimatedExp+'"  value="'+estimatedExpArray[j]+'"></input></td> <td><input class="seminarHeads form-control" id="'+natnlsemnrcount+'" type="number" name="'+natnlsemnrcount+'"  value="'+natnlsemnrcountArray[j]+'"></input></td><td><input class="lectureHeads form-control" id="'+lecturecount+'" type="number" name="'+lecturecount+'"  value="'+lecturecountArray[j]+'"></input></td><td><input class="unassigrant form-control" id="'+unassignGrant+'" type="number" name="'+unassignGrant+'" value="'+unassignedGrantAmnt[j]+'"></input></td><tr>');
							 $('#centerActivityeBody2').append(tr);
						
							 
							 var tr=$('<tr><td>'+centerNameArray[j]+'</td><td><input class="externlfunds form-control" id="'+externalfundamnt+'" type="number" name="externalFund" value="'+externalFundArray[j]+'"></input></td></tr>');
	       						$('#centerActivityeBody3').append(tr);
	       						console.log("external fundddddddddddddddd");
	       						//ugc fund shown
	       						var ugcFundAmnt=centerid1+"ugcFund";
	       						ugcFundIdArray[j]=ugcFundAmnt;
	       						
                               var tr=$('<tr><td>'+centerNameArray[j]+'</td><td><input class="ugcFunds form-control" id="'+ugcFundAmnt+'" type="number" name="ugcFund" value="'+ugcFundArray[j]+'"></input></td></tr>');
       						   $('#centerActivityeBody4').append(tr);
	       					}
							 	 
							 //get all activityfinance details
														 
							 },
							 error(e)
							 {
								console.log("error"+e);
								
							 }
							 });
				   		 
   					  $.ajax({
   						"url":"/expenditurehead/getActivityExpHeadExpensesByActivityIdAndFinyear",
   						"method":"GET",
   						data:{"activityId":activityId,"finyear":finyear},
   						success:function(data)
   							{
   							
   							$('#expenditureGrid').show();
   			   				$('#expenditureGridBody').empty();

   							
   							for(var i=0;i<data.length;i++)
   								{
   								console.log("head length"+data.length);
   							console.log("exphead description"+data[i]['actvtyExpHdkey']['exp']['expHeadsKey']['description']);
   							
   							var des=data[i]['actvtyExpHdkey']['exp']['expHeadsKey']['description'];//data[i]["expHeadsKey"]["description"];
   	   						console.log("getExpenditureHeadsByFinYear"+des)
   	   						exheadarray[i]=des;
   	   						finyearexphead=data[i]['actvtyExpHdkey']['exp']['expHeadsKey']['finYear'];             //data[i]["expHeadsKey"]["finYear"]
   	   						console.log("getExpenditureHeadsByFinYear"+finyearexphead)
   	   						var nualsalloctinamount=des+"nualsalloctinamount";
   	   						var estmtdexpdture=des+"estmtdexpdture";
   	   						var deviation=des+"deviation";
   	   						var comments=des+"comments";
   	   						var sino=i+1;
   	   					    var headgrantAmnt=des+"headgrantAmnt";
   	  		   					
   	   						 tr=$('<tr><td>'+sino+'</td><td>'+des+'</td><td><input class="allocationheads form-control" id="'+nualsalloctinamount+'" type="number"  value="'+data[i]['nualsAllocationAmt']+'"></input></td><td><input class="estimateheads1 form-control" id="'+estmtdexpdture+'" type="number"  value="'+data[i]['estimatedExp']+'"></input></td><td><input class="deviationheads form-control" id="'+deviation+'" type="number"  value="'+data[i]['deviation']+'"></input></td><td><input class="commentsheads form-control" id="'+comments+'"  value="'+data[i]['comment']+'"></input></td><td><input class="asgrantedEstimHeads form-control" id="'+headgrantAmnt+'" value="'+data[i]['asGrantedAmnt']+'"></input></td><tr>');
   	   						 //console.log("getExpenditureHeadsByFinYear"+tr);
   	   						 $('#expenditureGridBody').append(tr);
   	   						

   							
   							
   							
   							
   								}
   							getTotalEstimatedExpenditure();
   							},
   							error:function(e)
   							{
   							
   							}
   						
   					});
   					
   					
   					//get All activityAdvanceRequest
   					//get Activityfinance data
   						$.ajax({
   							"url":"/expenditurehead/getActivityAdvanceRequest",
   							"method":"GET",
   							data:{"activityId":activityId,"finyear":finyear},
   							success:function(data)
   								{
   								console.log(data);
   								$('#advnceRequestGridBody').empty();
   								
   								for(var i=0;i<data.length;i++)
   								{
   									var des=data[i]['actvtyExpHdkey']['exp']['expHeadsKey']['description'];//data[i]["expHeadsKey"]["description"];
   									var advanceRequired=des+"advanceRequired";
   									var requirementReason=des+"requirementReason";
   									var sino=i+1;
   									var asGrantedAdvanceAmnt=des+"asGrantedAdvanceAmnt";
   									 tr=$('<tr><td>'+sino+'</td><td>'+des+'</td><td><input class="expadvreq form-control" id="'+advanceRequired+'" type="number" value="'+data[i]['amountAdvance']+'"></input></td><td><input class="expadvreqreason form-control" id="'+requirementReason+'"  value="'+data[i]['reasonAdvance']+'"></input></td><td><input class="asgrantedAdvanceRequest form-control" id="'+asGrantedAdvanceAmnt+'" value="'+data[i]['asGranted']+'"></input></td> <tr>');
   									 $('#advnceRequestGridBody').append(tr);
   									;
   								}   
   							   	
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
		
					
					
				   
						}
				else
					{
					
					}
				
			})
}
	
	});
	
	
	$('#printBt').unbind().bind('click',function()
			{
		
		
		//$('#reportDiv').show();
				
		$.ajax({
			"url" : "/academicactivity/getAllActivityById",
			"method" : "GET",						
			"data" : {"activityId":activityId},
			success:function(data)
			{
				var d=data;
				centres=d["centres"];
				finyear=data['finyear'];
				console.log(finyear);	
				$('#adminApprovalRemarkstd').append(data['administrativeApprovalRemarks']);
				
			for(var key in d)
				{
					$('#'+key).val(d[key]);
					$('#'+key).text(d[key]);
				}
				
			},
			 error:function(e)
				 {
					 console.log("error"+e);
				 }
				 });
		
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
			   			 },
			   			 error:function(e)
			   			 {
			   				 console.log("error"+e);
			   			 }
			   			 
			   			 });
			  	
			  	
			  	 $.ajax({
						"url":"/centerfund/getAllCenterFundDetailsByCenterIdsAndFinYear",
						"method":"GET",
						  data:{"currentfinyear":finyear,"centerarray":centerarray.toString()},
						 success:function(data1)
						 {
							 for(var i=0;i<data1.length;i++)
						     {
							 $('#fundallocated').append('<td>'+ data1[i]["fundAmount"]+'</td>')	
							 $('#expincu').append('<td>'+ data1[i]["expTillDate"]+'</td>')	
						     }
						
						 },
						  error:function(e)
				   			 {
				   				 console.log("error"+e);
				   			 }
				   			 });
			  	 
			  	 
			  	  $.ajax({
				        "url":"/academicfinance/getAllActiveCenterByCenterIdsAndactiviy",
				         "method":"GET",
				         data:{"activityId":activityId,"centerarray":centerarray.toString()},
					       success:function(data2)
					        {
						 for(var j=0;j<data2.length;j++)
						 {
							 
							 $('#balexclude').append('<td>'+ data2[j]["balance_exclude_unassignedGrant"]+'</td>')	
							 $('#estExptr').append('<td>'+ data2[j]["estimatedExp"]+'</td>')
							 $('#nstr').append('<td>'+ data2[j]["national_seminar_count"]+'</td>')
							 $('#eltr').append('<td>'+ data2[j]["extension_lectures_count"]+'</td>')
							 $('#exfn').append('<td>'+ data2[j]["externalFund"]+'</td>')
							 $('#ugcfn').append('<td>'+ data2[j]["ugcFund"]+'</td>')
						 }
						 
					 },
					 error:function(e)
					 { 
					   console.log(e);
					 }
					 });
							 
							 $.ajax({
									"url":"/academicfinance/getActivityFinance",
									"method":"GET",
									data:{"activityId":activityId},
									success:function(data)
										{		 
										var externalfunding=data['externalFunding'];
										// externalfunding='y';
										console.log(externalfunding);
										if(externalfunding=='y')
											{
											 $('#exfnptr').append('<td>'+'Yes'+'</td>')
											}
										if(externalfunding=='n')
										{
											 $('#exfnptr').append('<td>'+'No'+'</td>')
										}
										
										
										var ugcFunding=data['ugcFunded'];
										//ugcFunding='y'
										if(ugcFunding=='y')
											{
											 $('#ugcfnp').append('<td>'+'Yes'+'</td>')
											}
										if(ugcFunding=='n')
										{
											 $('#ugcfnp').append('<td>'+'No'+'</td>')
										}
										var approveFunding=data['approvedFunding'];
										console.log("approve funding"+approveFunding);
										//approveFunding='y';
										if(approveFunding=='y')
										{ 
											$('#prfntr').append('<td>'+'Yes'+'</td>');
										
										
										}
									     if(approveFunding=='n')
									    {
									    	 alert("entrrr");
										$('#prfntr').append('<td>'+'No'+'</td>');
									    }
									 	$('#uniBenfc').append(data['univBeneficiaries']);
										$('#lBenfc').append(data['localBeneficiaries']);
										$('#oBenfc').append(data['outstnBeneficiaries']);
										$('#iBenfc').append(data['intNatBeneficiaries']);
										
										$('#uR').append(data['univResPerson']);
										$('#lR').append(data['localResPerson']);
										$('#oR').append( data['outstnResPerson']);
										$('#iR').append(data['intNatResPerson']);
										
										$('#uniBenfce').append( data['univBenTravel']);
										$('#lBenfce').append( data['localBenTravel']);
										$('#oBenfce').append(data['outstnBenTravel']);
										$('#iBenfce').append( data['intNatBenTravel']);
										
										$('#uRe').append(data['univResTravel']);
										$('#lRe').append( data['localResTravel']);
										$('#oRe').append(data['outstnResTravel']);
										$('#iRe').append( data['intNatResTravel']);
										
										
										$('#auRe').append(data['airTravelResUniv']);
										$('#alRe').append(data['airTravelResLocal']);
										$('#aoRe').append(data['airTravelResOutstn']);
										$('#aiRe').append( data['airTravelResIntnl']);
										
									
										
										var honorMore3000=data['honorMore3000'];
										
										if(honorMore3000=='y')
										{ 
											$('#honor').append('<td>'+'Yes'+'</td>');
										
										
										}
									     if(honorMore3000=='n')
									    {
									    	
										$('#honor').append('<td>'+'No'+'</td>');
									    }
										
										
									
							},
										 error:function(e)
										 { 
										   console.log(e);
										 }
										 });

								$.ajax({
									"url":"/expenditurehead/getActivityExpHeadExpensesByActivityIdAndFinyear",
									"method":"GET",
									 data:{"activityId":activityId,"finyear":finyear},
									 success:function(data)
										{
									
										 $('#estimtab').append('<thead><tr><th scope="col">Head Of Expenditure</th><th scope="col">Allocation as per NUALS Guidelines<th scope="col">Estimated Expenditure</th><th scope="col">Deviation</th><th scope="col">Comments</th><th scope="col">As Granted</th>');
										 
			                          
										for(var i=0;i<data.length;i++)
											{
											
										console.log("head length"+data.length);
										console.log("exphead description"+data[i]['actvtyExpHdkey']['exp']['expHeadsKey']['description']);
										
										var des=data[i]['actvtyExpHdkey']['exp']['expHeadsKey']['description'];//data[i]["expHeadsKey"]["description"];
				   						console.log("getExpenditureHeadsByFinYear"+des)
				   						exheadarray[i]=des;
				   						finyearexphead=data[i]['actvtyExpHdkey']['exp']['expHeadsKey']['finYear'];             //data[i]["expHeadsKey"]["finYear"]
				   						console.log("getExpenditureHeadsByFinYear"+finyearexphead)
				   						var nualsalloctinamount=des+"nualsalloctinamount";
				   						var estmtdexpdture=des+"estmtdexpdture";
				   						var deviation=des+"deviation";
				   						var comments=des+"comments";
				   						var sino=i+1;
				   						var headgrantAmnt=des+"headgrantAmnt";
				   					 tr=$('<tr><td>'+des+'</td><td>'+data[i]['nualsAllocationAmt']+'</td><td>'+data[i]['estimatedExp']+'</td><td>'+data[i]['deviation']+'</td><td>'+data[i]['comment']+'</td><td>0</td></tr>');
				   						 //console.log("getExpenditureHeadsByFinYear"+tr);
				   					   $('#estimtab').append(tr);
											}
										
									var	tr1=$('<tr><td>Total Estimated Expenditure</td><td>'+totalNualsAllocationRep+'</td><td>'+totalEstimRep+'</td><td>'+totaldeviationIdRep+'</td><td>'+'</td><td>'+totaldeviationIdRep+'</td><td></tr>');
										 $('#estimtab').append(tr1);
										},
										error:function(e)
										{
										
										}
									
								});   
								//get All activityAdvanceRequest
								//get Activityfinance data
									$.ajax({
										"url":"/expenditurehead/getActivityAdvanceRequest",
										"method":"GET",
										data:{"activityId":activityId,"finyear":finyear},
										success:function(data)
											{
											console.log(data);
	                 $('#advtab').append('<thead><tr><th scope="col">Head Of Expenditure</th><th scope="col">Advance Required</th><th scope="col">Briefly State Requirement with reason</th><th scope="col">As Granted Amount</th>');
											
											for(var i=0;i<data.length;i++)
											{
												var des=data[i]['actvtyExpHdkey']['exp']['expHeadsKey']['description'];//data[i]["expHeadsKey"]["description"];
												var advanceRequired=des+"advanceRequired";
												var requirementReason=des+"requirementReason";
												var sino=i+1;
												var asGrantedAdvanceAmnt=des+"asGrantedAdvanceAmnt";
										  var	tr=$('<tr><td>'+des+'</td><td>'+data[i]['amountAdvance']+'</td><td>'+data[i]['reasonAdvance']+'</td><td>0</td></tr>');
												
												
												//tr=$('<tr><td>'+sino+'</td><td>'+des+'</td><td><input class="expadvreq form-control" id="'+advanceRequired+'" type="number" value="'+data[i]['amountAdvance']+'"></input></td><td><input class="expadvreqreason form-control" id="'+requirementReason+'"  value="'+data[i]['reasonAdvance']+'" ></input></td><td><input class="asgrantedAdvanceRequest form-control" id="'+asGrantedAdvanceAmnt+'" value="0"></input></td><tr>');
												// $('#advnceRequestGridBody').append(tr);
												  $('#advtab').append(tr);
											}
												
											var	tr1=$('<tr><td>Total Advance Required</td><td>'+totalAdv+'</td></tr>');
											 $('#advtab').append(tr1);
											},
											error:function(e)
											{
											
											}
										
									});
								
								
								//	$('#adminApprovalRemarkstd').append(adminstRemark);
								//	$('#financialRemarkstd').append(finalRemark);
								
								// $('#printBt').attr('disabled', false);
 	                           //   finalRemark=$('#financialApprovedRemarksId').val();		
									
								
									
			});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
$(document).ready(function()
		{
	//from academic approval form
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
 		
	//loadAllActivityForAcademicApproval();
	//$('#activityTypeDetails').hide();
   
	$('.dp').datepicker({
	    format: 'yyyy-mm-dd',							   
		autoclose:true,
		clearBtn:true
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	loadAllActivityForFinalApproval();
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
		   					
		   						setUpDataTable(data,[{"name":"Name"},{"designation":"Designation"},{"email":"Email"},{"contactPhone":"Contact Phone"}],"gridTable2",null);
		   					
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
	   
	   $("#viewBrochureId").on('click',function(event) {
		   alert("entrrr"+brochureFilePath);
		   

				var urlGen="filerepo_public/minioFiles/nuals/"+brochureFilePath;
						console.log(urlGen);
						$('#imageFile').remove();
						//$('#objectDiv').remove();
						$('#objectDiv').empty();
						var imgf=$('<object  class="img-responsive" style="width: 450px; height: 450px;" id="imageFile"></object >')
						$('#objectDiv').append(imgf);
						$('#imageFile').removeAttr('data');
						$('#imageFile').removeAttr('src');
						 $('#imageFile').attr('data',urlGen);
						 $('#imageModal').modal();



				
			});
	
	   //final approving and asno generation
	     $("#activityFinalApproval" ).on('click',function(event) {
	    	 var cancelApprovalRemarks=$('#cancelApprovedRemarksId').val();
	             // console.log("activityFinalApproval button clicked"+finalApprovalRemarks);
	              var userCode=$('#usercode').val();
	              console.log("userCode"+userCode);
		   	$.ajax({
		   				"url":"/academicactivity/activityCancel",////
		   				"method":"GET",
		   				 data : {"activityId":activityId,"cancelRemarks":cancelApprovalRemarks},
		   				success:function(data)
		   				{
		   					if(data)
		   					{
		   						centreDialog("Success",data,"green");
		   						
		   						
		   					 $('#printBt').attr('disabled', false);
		   				 	 finalRemark=$('#financialfinalApprovedRemarksId').val();
		   					}
		   					
		   					
		   				/*    $.ajax({
		   				   	"url":"/academicfinance/updateActivityCenterFund",
		   				   	"method":"GET",
		   				   	 data:{"activityId":activityId,"centerarray":centerarray.toString(),"balassignArray":balassignArray.toString(),"estimateHeadsArray":estimateHeadsArray.toString(),"seminarHeadsArray":seminarHeadsArray.toString(),"lectureHeadsArray":lectureHeadsArray.toString(),"externlfundArray":externlfundArray.toString(),"ugcFundArray":ugcFundArray.toString()},
		   				   	 success:function(data)
		   				   	 {
		   				   		 
		   				   		 console.log("update activity fianceeeeee"+data);
		   				   		 updateActivityCenterFinanceflag ="true";
		   				   		 
		   				   	 },
		   				   	 error:function(e)
		   				        {
		   				   		 updateActivityCenterFinanceflag ="false"; 
		   				   		 console.log("error in activity finance"+e);
		   				       }	
		   				   		});	 */
		   					
		   				},		
		   				error : function(e) {
		   		   			//showMessage("Error in Getting Programs. Contact Admin");
		   		   		}
		   			});
	 	});
	   
	     $('#printBt').unbind().bind('click',function()
	    			{
	    		
	    		$('#reportDiv').show();
	    				
	    		$.ajax({
	    			"url" : "/academicactivity/getAllActivityById",
	    			"method" : "GET",						
	    			"data" : {"activityId":activityId},
	    			success:function(data)
	    			{
	    				var d=data;
	    				centres=d["centres"];
	    				finyear=data['finyear'];
	    				console.log(finyear);
	    				
	    				for(var key in d)
	    				{
	    					$('#'+key).val(d[key]);
	    					$('#'+key).text(d[key]);
	    				}
	    				
	    			},
	    			 error:function(e)
	    				 {
	    					 console.log("error"+e);
	    				 }
	    				 });
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
	    			   			 },
	    			   			 error:function(e)
	    			   			 {
	    			   				 console.log("error"+e);
	    			   			 }
	    			   			 
	    			   			 });
	
	    			  	 $.ajax({
	    						"url":"/centerfund/getAllCenterFundDetailsByCenterIdsAndFinYear",
	    						"method":"GET",
	    						  data:{"currentfinyear":finyear,"centerarray":centerarray.toString()},
	    						 success:function(data1)
	    						 {
	    							 for(var i=0;i<data1.length;i++)
	    						     {
	    							 $('#fundallocated').append('<td>'+ data1[i]["fundAmount"]+'</td>')	
	    							 $('#expincu').append('<td>'+ data1[i]["expTillDate"]+'</td>')	
	    						     }
	    						
	    						 },
	    						  error:function(e)
	    				   			 {
	    				   				 console.log("error"+e);
	    				   			 }
	    				   			 });
	    			  	 
	    			  	 
	    			  	  $.ajax({
	    				        "url":"/academicfinance/getAllActiveCenterByCenterIdsAndactiviy",
	    				         "method":"GET",
	    				         data:{"activityId":activityId,"centerarray":centerarray.toString()},
	    					       success:function(data2)
	    					        {
	    						 for(var j=0;j<data2.length;j++)
	    						 {
	    							 
	    							 $('#balexclude').append('<td>'+ data2[j]["balance_exclude_unassignedGrant"]+'</td>')	
	    							 $('#estExptr').append('<td>'+ data2[j]["estimatedExp"]+'</td>')
	    							 $('#nstr').append('<td>'+ data2[j]["national_seminar_count"]+'</td>')
	    							 $('#eltr').append('<td>'+ data2[j]["extension_lectures_count"]+'</td>')
	    							 $('#exfn').append('<td>'+ data2[j]["externalFund"]+'</td>')
	    							 $('#ugcfn').append('<td>'+ data2[j]["ugcFund"]+'</td>')
	    						 }
	    						 
	    					 },
	    					 error:function(e)
	    					 { 
	    					   console.log(e);
	    					 }
	    					 });
	    							 
	    							 $.ajax({
	    									"url":"/academicfinance/getActivityFinance",
	    									"method":"GET",
	    									data:{"activityId":activityId},
	    									success:function(data)
	    										{		 
	    										var externalfunding=data['externalFunding'];
	    										// externalfunding='y';
	    										console.log(externalfunding);
	    										if(externalfunding=='y')
	    											{
	    											 $('#exfnptr').append('<td>'+'Yes'+'</td>')
	    											}
	    										if(externalfunding=='n')
	    										{
	    											 $('#exfnptr').append('<td>'+'No'+'</td>')
	    										}
	    										
	    										
	    										var ugcFunding=data['ugcFunded'];
	    										//ugcFunding='y'
	    										if(ugcFunding=='y')
	    											{
	    											 $('#ugcfnp').append('<td>'+'Yes'+'</td>')
	    											}
	    										if(ugcFunding=='n')
	    										{
	    											 $('#ugcfnp').append('<td>'+'No'+'</td>')
	    										}
	    										var approveFunding=data['approvedFunding'];
	    										console.log("approve funding"+approveFunding);
	    										//approveFunding='y';
	    										if(approveFunding=='y')
	    										{ 
	    											$('#prfntr').append('<td>'+'Yes'+'</td>');
	    										
	    										
	    										}
	    									     if(approveFunding=='n')
	    									    {
	    									    	 alert("entrrr");
	    										$('#prfntr').append('<td>'+'No'+'</td>');
	    									    }
	    									 	$('#uniBenfc').append(data['univBeneficiaries']);
	    										$('#lBenfc').append(data['localBeneficiaries']);
	    										$('#oBenfc').append(data['outstnBeneficiaries']);
	    										$('#iBenfc').append(data['intNatBeneficiaries']);
	    										
	    										$('#uR').append(data['univResPerson']);
	    										$('#lR').append(data['localResPerson']);
	    										$('#oR').append( data['outstnResPerson']);
	    										$('#iR').append(data['intNatResPerson']);
	    										
	    										$('#uniBenfce').append( data['univBenTravel']);
	    										$('#lBenfce').append( data['localBenTravel']);
	    										$('#oBenfce').append(data['outstnBenTravel']);
	    										$('#iBenfce').append( data['intNatBenTravel']);
	    										
	    										$('#uRe').append(data['univResTravel']);
	    										$('#lRe').append( data['localResTravel']);
	    										$('#oRe').append(data['outstnResTravel']);
	    										$('#iRe').append( data['intNatResTravel']);
	    										
	    										
	    										$('#auRe').append(data['airTravelResUniv']);
	    										$('#alRe').append(data['airTravelResLocal']);
	    										$('#aoRe').append(data['airTravelResOutstn']);
	    										$('#aiRe').append( data['airTravelResIntnl']);
	    										
	    									
	    										
	    										var honorMore3000=data['honorMore3000'];
	    										
	    										if(honorMore3000=='y')
	    										{ 
	    											$('#honor').append('<td>'+'Yes'+'</td>');
	    										
	    										
	    										}
	    									     if(honorMore3000=='n')
	    									    {
	    									    	
	    										$('#honor').append('<td>'+'No'+'</td>');
	    									    }
	    										
	    										
	    									
	    							},
	    										 error:function(e)
	    										 { 
	    										   console.log(e);
	    										 }
	    										 });

	    								$.ajax({
	    									"url":"/expenditurehead/getActivityExpHeadExpensesByActivityIdAndFinyear",
	    									"method":"GET",
	    									 data:{"activityId":activityId,"finyear":finyear},
	    									 success:function(data)
	    										{
	    									
	    										 $('#estimtab').append('<thead><tr><th scope="col">Head Of Expenditure</th><th scope="col">Allocation as per NUALS Guidelines<th scope="col">Estimated Expenditure</th><th scope="col">Deviation</th><th scope="col">Comments</th><th scope="col">As Granted</th>');
	    										 
	    			                          
	    										for(var i=0;i<data.length;i++)
	    											{
	    											
	    										console.log("head length"+data.length);
	    										console.log("exphead description"+data[i]['actvtyExpHdkey']['exp']['expHeadsKey']['description']);
	    										
	    										var des=data[i]['actvtyExpHdkey']['exp']['expHeadsKey']['description'];//data[i]["expHeadsKey"]["description"];
	    				   						console.log("getExpenditureHeadsByFinYear"+des)
	    				   						exheadarray[i]=des;
	    				   						finyearexphead=data[i]['actvtyExpHdkey']['exp']['expHeadsKey']['finYear'];             //data[i]["expHeadsKey"]["finYear"]
	    				   						console.log("getExpenditureHeadsByFinYear"+finyearexphead)
	    				   						var nualsalloctinamount=des+"nualsalloctinamount";
	    				   						var estmtdexpdture=des+"estmtdexpdture";
	    				   						var deviation=des+"deviation";
	    				   						var comments=des+"comments";
	    				   						var sino=i+1;
	    				   						var headgrantAmnt=des+"headgrantAmnt";
	    				   					 tr=$('<tr><td>'+des+'</td><td>'+data[i]['nualsAllocationAmt']+'</td><td>'+data[i]['estimatedExp']+'</td><td>'+data[i]['deviation']+'</td><td>'+data[i]['comment']+'</td><td>0</td></tr>');
	    				   						 //console.log("getExpenditureHeadsByFinYear"+tr);
	    				   					   $('#estimtab').append(tr);
	    											}
	    										
	    									var	tr1=$('<tr><td>Total Estimated Expenditure</td><td>'+totalNualsAllocationRep+'</td><td>'+totalEstimRep+'</td><td>'+totaldeviationIdRep+'</td><td>'+'</td><td>'+totaldeviationIdRep+'</td><td></tr>');
	    										 $('#estimtab').append(tr1);
	    										},
	    										error:function(e)
	    										{
	    										
	    										}
	    									
	    								});   
	    								//get All activityAdvanceRequest
	    								//get Activityfinance data
	    									$.ajax({
	    										"url":"/expenditurehead/getActivityAdvanceRequest",
	    										"method":"GET",
	    										data:{"activityId":activityId,"finyear":finyear},
	    										success:function(data)
	    											{
	    											console.log(data);
	    	                 $('#advtab').append('<thead><tr><th scope="col">Head Of Expenditure</th><th scope="col">Advance Required</th><th scope="col">Briefly State Requirement with reason</th><th scope="col">As Granted Amount</th>');
	    											
	    											for(var i=0;i<data.length;i++)
	    											{
	    												var des=data[i]['actvtyExpHdkey']['exp']['expHeadsKey']['description'];//data[i]["expHeadsKey"]["description"];
	    												var advanceRequired=des+"advanceRequired";
	    												var requirementReason=des+"requirementReason";
	    												var sino=i+1;
	    												var asGrantedAdvanceAmnt=des+"asGrantedAdvanceAmnt";

	    												
	    												
	    												
	    											var	tr=$('<tr><td>'+des+'</td><td>'+data[i]['amountAdvance']+'</td><td>'+data[i]['reasonAdvance']+'</td><td>0</td></tr>');
	    												
	    												
	    												//tr=$('<tr><td>'+sino+'</td><td>'+des+'</td><td><input class="expadvreq form-control" id="'+advanceRequired+'" type="number" value="'+data[i]['amountAdvance']+'"></input></td><td><input class="expadvreqreason form-control" id="'+requirementReason+'"  value="'+data[i]['reasonAdvance']+'" ></input></td><td><input class="asgrantedAdvanceRequest form-control" id="'+asGrantedAdvanceAmnt+'" value="0"></input></td><tr>');
	    												// $('#advnceRequestGridBody').append(tr);
	    												  $('#advtab').append(tr);
	    											}
	    												
	    											var	tr1=$('<tr><td>Total Advance Required</td><td>'+totalAdv+'</td></tr>');
	    											 $('#advtab').append(tr1);
	    											},
	    											error:function(e)
	    											{
	    											
	    											}
	    										
	    									});
	    							
	    									$('#finalRemarkstd').append(finalRemark);
	    									
	    									
	    									 reportPrinting('reportDiv');	
	    			});
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
		});
</script>

<div  id="gridRow" class="card card-info card-outline">
  	<div class="card-header">
		  <h3 >Final Approving</h3>
	  
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
      <h3> Activity For Cancel</h3>
   </div>
<div class="card-body">
<div class="row">
 <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

	<div id="tabs">
	  <ul>
	    <li style="background-color:#265bab;text-color:white;"><a href="#tabs-1" style="color:#d5e6e4">Academic Approval</a></li>
	    <li style="background-color:#5a2066;text-color:white;"><a href="#tabs-2" style="color:#d5e6e4">Financial Approval</a></li>
	  </ul>
	  <div id="tabs-1">
	  <div  id="activityApproval" class="card card-info card-outline">
   <div class="card-header">
      <h3>Activity Details for Cancel</h3>
   </div>
   <div class="card-body">
     <!--  <form id="activityMasterDetailsForm" enctype="multipart/form-data"> -->
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

                  <button doc="brochureImage" class="btn btn-primary upload" id="viewBrochureId" >View Brochures</button>     
             </div>
             
            <div class="form-group">
               <label for="description"> A brief description of the
               Objects of the Programme</label>
               <textarea id="descriptionId" name="description" rows="4" cols="5"
                  class="form-control"></textarea>
            </div>
            <div class="form-group">
               <label for="targetgroup"> Content,Relevance And Target
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
               <label for="remarks"> Academic Approved Remarks</label>
               <textarea id="adminApprovalRemarksId" name="adminApprovalRemarks" rows="4" cols="50"
                  class="form-control"></textarea>
            </div>
         <!--       <div class="form-group">
               <label>Final Approved Date</label> <input type="text" id="academicfinalApprovedDateId"
                  name="finalApprovedDate" class="form-control dp" required></input>
            </div>
            <div class="form-group">
               <label for="remarks"> Remarks For Final Approval</label>
               <textarea id="finalApprovedRemarksId" name="finalApprovedRemarks" rows="4" cols="50"
                  class="form-control"></textarea>
            </div>
            <div class="form-group" style="text-align: center">
               <button type="submit" class="btn btn-primary" id="activityFinalApproval">Approve</button>
            </div> -->
          
          
           <!--  <button type="button" id="centerActivity" class="btn btn-primary"
               data-toggle="modal" data-target="#dialogCenterActivityEdit" style=>
            Add Center Activity Finance</button>
         </div> -->
         </div>
      </form>
         
   </div>
</div>
      </div>
	  <div id="tabs-2">
	   <div  id="activityApproval" class="card card-info card-outline">
   <div class="card-header">
      <h3><center>Activity Financial Approval</center></h3>
   </div>
   <div class="card-body">
      <form id="formCenterActivityDetails" enctype="multipart/form-data">
         <div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
             
            <div class="form-group">
               <label for="activitytype"> Name/Title of the Program</label> <input
                  type="text" name="title" class="form-control" id="titleFinanceId"
                  required />
            </div>
             <div class="row">
               <div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
                  <label for="date from">Date From</label> <input type="text"
                     id="dateFromFinanceId" name="dateFrom" class="form-control dp" required></input>
               </div>
               <div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
                  <label for="date to">Date To</label> <input type="text"
                     id="dateToFinanceId" name="dateTo" class="form-control dp" required></input>
               </div>
            </div>
            <div class="row">
               <div class="form-group" id="centerdiv1">
               <label for="activity type"> Centres involved in the Program
               <label>
              </div>
              </div>
              
              <div class="form-group">
                  <div id="divGridTable2" class="row" style="display: none">
                     <div class="col-md-12">
                        <div class="table-responsive cell-border">
                           <table id="gridTableMapper" class="table table-striped">
                              <thead>
                                 <tr>
                                    <th scope="col">Center</th>
                                    <th scope="col">Fund Allocated to the Centre for the
                                       Current Financial Year
                                    </th>
                                    <th scope="col">Expenditure incurred (Including firm
                                       committments made) till date
                                    </th>
                                    <th scope="col">Balance Available excluding Unassigned
                                       Grant
                                    </th>
                                    <th scope="col">Estimated expenditure for this
                                       Programme
                                    </th>
                                    <th scope="col">National Seminars Organised till date (
                                       Including this)
                                    <th>Extension Lectures/ similar programmes Organised
                                       till date ( Including this)
                                    </th>
                                     <th scope="col">Unassigned Grant Available</th>
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
                  <label for="comment" class="col-sm-6 col-form-label">Is
                  their any external funding for the programme ? </label>
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
                  <label for="comment" class="col-sm-6 col-form-label">Is
                  this programme funded by UGC ?</label>
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
                                 <th scope="col">Amount of UGC Funding</th>
                              </tr>
                           </thead>
                           <tbody id="centerActivityeBody4">
                           </tbody>
                        </table>
                     </div>
                  </div>
               </div>
                 <div class="form-group row">
                  <label for="activitytype" class="col-sm-6 col-form-label">
                  Are such private funding proposals approved by VC/EC private fund</label>
                  <div class="col-sm-6">
                     <select class="form-control" id="privateFund">
                        <option value="se">Select</option>
                        <option value="yes">Yes</option>
                        <option value="no">No</option>
                     </select>
                  </div>
               </div>
                  <div class="form-group row" id="privatefundingjustification" style="display:none">
                  <label for="privateFundJustification"
                     class="col-sm-6 col-form-label">If, No, Is necessary
                  justification provided as Anexure I ( Academic Approval)</label>
                  <div class="col-sm-6"> 
                           <input class="file"  id="annexureFileId" name="annexureFile" type="file" ext="jpg" ></input> 
                  </div>
               </div>
              <div class="form-group">
            <label for="brochure" class="col-sm-3 col-form-label">View Annexure </label> 
            <!--  <input class="file"  id="brochureURLId" name="brochureURL" type="file" ext="jpg" required></input>   -->
             </div>
               
                <div id="divGridTable7" class="row">
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
                              <tr>
                                 <td>No of Beneficiaries</td>
                                 <td><input type="text" id="UnivBeneficiariescountId"
                                    name="univBeneficiaries" value="0"></input></td>
                                 <td><input type="text" id="localBeneficiariesId"
                                    name="localBeneficiaries" value="0"></input></td>
                                 <td><input type="text" id="outstnBeneficiariesId"
                                    name="outstnBeneficiaries" value="0"></input></td>
                                 <td><input type="text" id="intNatBeneficiariesId"
                                    name="intNatBeneficiaries" value="0"></input></td>
                              </tr>
                              <tr>
                                 <td>Resource Person for the Program</td>
                                 <td><input type="text" id="UnivResPersoncountId"
                                    name="univResPerson" value="0"></input></td>
                                 <td><input type="text" id="localResPersonId"
                                    name="localResPerson" value="0"></input></td>
                                 <td><input type="text" id="outstnResPersonId"
                                    name="outstnResPerson" value="0"></input></td>
                                 <td><input type="text" id="intNatResPersonId"
                                    name="intNatResPerson" value="0"></input></td>
                              </tr>
                           </tbody>
                        </table>
                     </div>
                  </div>
               </div>
               <h5 style="text-decoration: underline;">Proposesd expenses for
                  travelling and incidental expenses
               </h5>
               
               <div id="divGridTable7" class="row">
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
                              <tr>
                                 <td>For Beneficiaries</td>
                                 <td><input type="text" id="UnivBenTravelId"
                                    name="univBenTravel" value="0"></input></td>
                                 <td><input type="text" id="localBenTravelId"
                                    name="localBenTravel" value="0"></input></td>
                                 <td><input type="text" id="outstnBenTravelId"
                                    name="outstnBenTravel" value="0"></input></td>
                                 <td><input type="text" id="intNatBenTravelId"
                                    name="intNatBenTravel" value="0"></input></td>
                              </tr>
                              <tr>
                                 <td>For Resource persons for the Programme</td>
                                 <td><input type="text" id="UnivResTraveltId"
                                    name="univResTravel" value="0"></input></td>
                                 <td><input type="text" id="localResTravelId"
                                    name="localResTravel" value="0"></input></td>
                                 <td><input type="text" id="outstnResTravelId"
                                    name="outstnResTravel" value="0"></input></td>
                                 <td><input type="text" id="intNatResTravelId"
                                    name="intNatResTravel" value="0"></input></td>
                              </tr>
                              <tr>
                                 <td>Number of Air Travel for resource persons</td>
                                 <td><input type="text" id="AirTravelResUnivId"
                                    name="airTravelResUniv" value="0"></input></td>
                                 <td><input type="text" id="AirTravelResLocalId"
                                    name="airTravelResLocal" value="0"></input></td>
                                 <td><input type="text" id="AirTravelResOutstnId"
                                    name="airTravelResOutstn" value="0"></input></td>
                                 <td><input type="text" id="AirTravelResIntnlId"
                                    name="airTravelResIntnl" value="0"></input></td>
                              </tr>
                           </tbody>
                        </table>
                     </div>
                  </div>
               </div>
              <div class="form-group row">
                  <label for="activitytype" class="col-sm-6 col-form-label">Does
                  the programme envisage payment of Honorarium of more than
                  Rs3000/- per day ? </label>
                  <div class="col-sm-6">
                     <select class="form-control" id="HonorMore3000">
                        <option value="">Select</option>
                        <option value="yes">Yes</option>
                        <option value="no">No</option>
                     </select>
                  </div>
               </div>
                    <div class="form-group row">
                  <label for="activitytype" class="col-sm-6 col-form-label">Total Estimated Expenditure For the Program</label>
                  <div class="col-sm-6">
                    <input type="number"  class="form-control  col-sm-4" name="totalEstExpenditure" id="totalEstExpId" ></input>
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
                                 <th scope="col">Deviation</th>
                                 <th scope="col">Comments</th>
                                 <th scope="col">As Granted Amount</th>
                              </tr>
                           </thead>
                           <tbody id="expenditureGridBody">
                           </tbody>
                        </table>
                     </div>
                  </div>
               </div>
              <!-- <div class="form-group row"  class="col-sm-3"> <button type="button" id="totalExpenditure" class="btn btn-primary">Get Total EstimateExpenditure</button></div> -->
               <!--   <div class="form-group row">
                  <label for="activitytype" class="col-sm-6 col-form-label">Total
                  Estimated Expenditure </label>
                  <div class="col-sm-6">
                     <input type="text" name="total_estimated" class="form-control"
                        id="totalEstExpIdd" required>
                  </div>
               </div> -->
               
                 <div class="form-group row">
                 <label for="activitytype" class="col-sm-2 col-form-label">Total Nuals Allocation Amount
                  </label>
                  
                   <div class="col-sm-2">
                     <input type="text" name="totalallocation" class="form-control"
                        id="totalallocationId" required>
                  </div>
                   <label for="activitytype" class="col-sm-2 col-form-label">Total Estimated Expenditure
                  </label>
                  <div class="col-sm-2">
                     <input type="text" name="total_estimated" class="form-control"
                        id="totalEstExpIdd" required>
                  </div>
                   <label for="activitytype" class="col-sm-2 col-form-label">Total Deviations Amount
                  </label>
                  <div class="col-sm-2">
                     <input type="text" name="totaldeviation" class="form-control"
                        id="totaldeviationId" required>
                  </div>
                   <label for="activitytype" class="col-sm-2 col-form-label">Total As Granted Amount
                  </label>
                  <div class="col-sm-2">
                     <input type="text" name="totalAsgranted" class="form-control"
                        id="totalAsGrantedId" required>
                  </div>
               </div>
               
               
               
             </div>
             
              <h5 style="text-decoration: underline;">Details of advance Requirement if Any
               </h5>
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
                                 <th scope="col">As Granted Amount</th>
                              </tr>
                           </thead>
                           <tbody id="advnceRequestGridBody">
                           </tbody>
                        </table>
                     </div>
                  </div>
               </div>
             
<!--                 <div class="form-group row"  class="col-sm-3"> <button type="button" id="totaladvancereq1" class="btn btn-primary">Get Total</button></div> -->
               <div class="form-group row">
                  <label for="activitytype" class="col-sm-6 col-form-label">Total
                  Advance Required </label>
                  <div class="col-sm-6">
                     <input type="text" name="totalAdvanceReq" class="form-control"
                        id="totalAdvanceReqId" required>
                  </div>
               </div>
               
            <div class="form-group">
               <label for="remarks"> Remarks For Financial Approval</label>
               <textarea id="financialApprovedRemarksId" name="financialApprovedRemarks" rows="4" cols="50"
                  class="form-control"></textarea>
            </div>
            <div class="form-group">
               <label for="remarks"> Remarks For Final Approval</label>
               <textarea id="financialfinalApprovedRemarksId" name="finalApprovedRemarks" rows="4" cols="50"
                  class="form-control"></textarea>
            </div>
            
            <div class="form-group">
               <label for="remarks"> Remarks For Cancel</label>
               <textarea id="cancelApprovedRemarksId" name="cancelRemarks" rows="4" cols="50"
                  class="form-control"></textarea>
            </div>
            
            <div class="form-group" style="text-align: center">
               <input type="button" class="btn btn-primary" id="activityFinalApproval" value="Cancel"></button>
            </div>
               </form>
             <div class="form-group" style="text-align: center">
             
                <button class="btn btn-primary" id="printBt" disabled>Print</button>
            </div>
           <!--  <button type="button" id="centerActivity" class="btn btn-primary"
               data-toggle="modal" data-target="#dialogCenterActivityEdit" style=>
            Add Center Activity Finance</button>
         </div> -->
   
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
		  <h3>List Of Activities Finally Approved</h3>
	  
  	</div>
    <div class="card-body">
	   	<div class="table-responsive">
				<table class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border" id="administrativepproveactivityTable">
			 
				</table>
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
		<div class="table-responsive" id="reportDiv" style="display:none">
			<table class="table table-striped" id="repTable">
				<tr><td colspan="4">
				 <img src="../images/logo.jpg" style="width: 40px;">
				</tr>
				<tr td colspan="4"><center> REQUEST FORADMINISTRATIVE SANCTION FOR PROGRAMMES CONDUCTED BY NUALS/ VARIOUS  CENTRES OF STUDY AND RESEARCH </center><td></tr>
				<tr><td colspan="4">
				
				</tr>
				<tr><td>Name/Title of the Program</td><td colspan="3" id="title"></td></tr>
				<tr><td>Date of Event/Program</td><td id="dateFrom"></td><td id="dateTo"></td></tr>
				<tr><td>Number Centres Involved in Organising the Program</td><td id="centerLength"></td></tr>
				<tr><td></td><td ><table id="centrestab" class="table table-striped"></table> </td></tr>
				
				<tr id="fundallocated"><td>Fund Allocated to the Center for the Current Financial Year</td></tr>
				<tr id="expincu"><td>Fund Allocated to the Center for the Current Financial YearExpenditure incurred (Including firm committments made) till date</td></tr>
				<tr id="balexclude"><td>Balance Available excluding Unassigned Grant</td></tr>
				<tr id="estExptr"><td>Estimated expenditure for this Program</td></tr>
				<tr id="nstr"><td>National Seminars Organised till date ( Including this)</td></tr>
			
				<tr id="eltr"><td>Extension Lectures/ similar programmes  Organised till date ( Including this)</td></tr>
				
				<tr id="exfnptr"><td>Is their any external funding for the programme ? ( Including this)</td></tr>
				<tr id="exfn"><td>If , Yes the proposed Amount of External Funding ( Including this)</td></tr>
				<tr id="prfntr"><td>Are such private funding proposals approved by VC/EC</td></tr>
				<tr id="ugcfnp"><td>Is this program funded by UGC ?</td></tr>
				<tr id="ugcfn"><td>If, Yes, amount of UGC funding</td></tr>
				<tr id="prfntr"><td>Are such private funding proposals approved by VC/EC</td></tr>
				<tr><td></td><td>Universities</td><td>Local</td><td>Outstation</td><td>International</td></tr>
				<tr><td>No of Beneficiaries</td><td id="uniBenfc"> </td><td id="lBenfc"> </td><td id="oBenfc"> </td><td id="iBenfc"> </td> </tr>	
				<tr><td>Resource persons for the Program</td><td id="uR"> </td><td id="lR"> </td><td id="oR"> </td><td id="iR"> </td> </tr>
				<tr><td>Proposesd expenses for travelling and incidental expenses </td><td>Universities</td><td>Local</td><td>Outstation</td><td>International</td></tr>
				
				
				<tr><td>For  Beneficiaries</td><td id="uniBenfce"> </td><td id="lBenfce"> </td><td id="oBenfce"> </td><td id="iBenfce"> </td> </tr>	
				<tr><td>For Resource persons for the Programme</td><td id="uRe"> </td><td id="lRe"> </td><td id="oRe"> </td><td id="iRe"> </td> </tr>
				<tr><td>Number of Air Travel for resource persons</td><td id="auRe"> </td><td id="alRe"> </td><td id="aoRe"> </td><td id="aiRe"> </td> </tr>
				<tr><td>Does the programme envisage payment of Honorarium of more than Rs3000/- per day ?</td><td id="honor"> </td><td> </td><td> </td><td> </td> </tr>
				
                <tr><td><table id="estimtab" class="table table-striped"></table></td></tr> 
                 <tr><td>Details of Advance Required if Any</td></tr> 
                 <tr><td><table id="advtab" class="table table-striped"></table></td></tr>   
				<tr><td  colspan="4"><u>Administrative Approval Remarks </u></td></tr>   
				<tr><td id="adminApprovalRemarkstd" colspan="4"></td></tr>  
				<tr><td  colspan="4"><u>Financial Approval Remarks </u></td></tr>   
				<tr><td id="financialRemarkstd" colspan="4"></td></tr>  
				<tr><td  colspan="4"><u>Final Approval Remarks </u></td></tr>   
				<tr><td id="finalRemarkstd" colspan="4"></td></tr> 
				
				<tr><td>Kalamassery</td><td></td><td></td><td>Director</td></tr>
				<tr><td ></td></tr>
				<tr><td ></td></tr>
				     
                                
			</table>	
		</div>
	</div>
</div>
