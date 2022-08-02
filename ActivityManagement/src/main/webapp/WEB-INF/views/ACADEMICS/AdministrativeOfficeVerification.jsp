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
var unassignedGrantAmnt=[];
var finyear;
var centerarray=[];
var activityId;
var allocatedfundamountArray=[];
var exptilldateArray=[];
var Balance_exclude_unassignedGrantArray=[];

var expIncurred=[];
var estimatedExpArray=[];
var natnlsemnrcountArray=[];
var lecturecountArray=[];
var printEnterRemark;
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

var asAmntGrantArray=[];
var asGrantedAdvAmnt=[];
var annexurePath;

//for report
var adminstRemark;

var totalAdv;
var totalEstimRep;
var totalNualsAllocationRep;
var totaldeviationIdRep;
var totalAsGrantedRep;
var adminstrativeApprovalRemarks;
function getTotalAdvanceRequired()
{
	//alert("advance clicked");
	   var sum=0;
	   $('.expadvreq').each(function()
		   		{
	    	
		   var amnt66=$(this).val();
	        sum=sum+parseFloat(amnt66 ); 
	        $('#totalAdvanceReqId').val(sum);
		   		});
	   //  alert(sum);
	   
}

function removeWhiteSpaceCharacter(desString)
{
	
	desString.replace(/ /g,'').replace(/\./g,'-').replace(/#/g,'-').replace(/ /g,'')+' iden="'+d[i]+'"">'+d[i]+' </h3><div id="div-'+d[i].replace(/ /g,'').replace(/\./g,'-').replace(/#/g,'-').replace(/\//g)
}


function getAllActivityAdministrativecApprovedByLogin() {
	console.log("loadAllActivityAcademicallyApproved");
	$.ajax({
		"url" : "/academicactivity/findAllActivityAdministrativeApprovedByLogin",
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
		  ], "administrativepproveactivityTable", "select-checkbox");

		},
		error : function(e)
		{
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
function getTotalEstimatedExpense()
{
	//alert("advance clicked");
	   var sum=0;
	   $('.estimateHeads').each(function()
		   		{
	    	
		   var amnt66=$(this).val();
	        sum=sum+parseFloat(amnt66 ); 
	        $('#totalEstExpId').val(sum);
		   		});
	   //  alert(sum);
	   
}


function getAllExpHeadDetails()
{
	   var a=0;
	   var b=0;
	   var c=0;
	   var d=0;
	   var e=0;
	   var f=0;
	   var g=0;
	   var h=0;
	   var i=0;
	   $('.allocationheads').each(function()
		   		{
		   var amnt6=$(this).val();
		 
		   allocationheadsarray[a]=amnt6;
		   a=a+1;
		   		}); 
	   $('.estimateheads1').each(function()
		   		{
		   var amnt6=$(this).val();
		
		   estimateheadsarray1[b]=amnt6;
		   b=b+1; 
		   		}); 
	   
	   $('.deviationheads').each(function()
		   		{
		   var amnt6=$(this).val();
	
		   deviationheadsarray[c]=amnt6;
		   c=c+1; 
		   		}); 
	   $('.commentsheads').each(function()
		   		{
		   var amnt6=$(this).val();
		  
		   commentsheadsarray[d]=amnt6;
		   d=d+1; 
		   		}); 
	   
	   
	   
	   //advance request
	    $('.expadvreq').each(function()
		   		{
	    	
	    	
		   var amnt6=$(this).val();
		//  alert(amnt6);
		   expadvreqarray[e]=amnt6;
		   e=e+1; 
		   		});
	   
	    $('.expadvreqreason').each(function()
		   		{
		   var amnt6=$(this).val();
		//alert(amnt6);
		   expadvreqreasonarray[g]=amnt6;
		   g=g+1; 
		   		});
	    
	    
	    $('.asgrantedEstimHeads').each(function()
		   		{
		   
	    	   var amnt6=$(this).val();
		       asAmntGrantArray[h]=amnt6;
		       h=h+1; 
		   		}); 
	    
	    $('.asgrantedAdvanceRequest').each(function()
		   		{
		   
	    	   var amnt6=$(this).val();
	    	   asGrantedAdvAmnt[i]=amnt6;
		       i=i+1; 
		   		}); 
	    
	    
	    
		   	}
	    
function loadAllActivityForAcademicApproval() {
	console.log("loadAllActivityForAcademicApproval");
	$.ajax({
		"url" : "/academicactivity/getAllActivityForAdministrativeOfficeApproved",
		"method" : "GET",
		success : function(data) {
			
			console.log("finyear"+finyear+"activitycode"+data['activityCode']);
			
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
				"finyear":"Financial Year"
			},
				
			{
			"facultyCode":"facultyCode"
			},
			
			{
				"status" : "Status"
			}
		  ], "activityTable1", "select-checkbox");
			onDataTableClick('activityTable1', function() {		
				if(selectedRowFromDataTable != null)
				{
					$('html, body').animate({ scrollTop: $('#activityApproval').offset().top }, 'slow');
					activityId=selectedRowFromDataTable[2];
					finyear=selectedRowFromDataTable[4];
					
					$.ajax({
									"url" : "/academicactivity/getAllActivityById",
									"method" : "GET",						
									data : {"activityId":activityId},
									success : function(data) 
									{
										console.log("daaataaaaaaaaaa in getAllActivityById1"+data);
										finyear=data['finyear'];
										$('#titleId').val(data['title']);
										
										
										var data_splits1 =data['dateFrom'].split("T");
										var data_splits2 =data['dateTo'].split("T");
										$('#dateFromId').val(data_splits1[0]);
										$('#dateToId').val(data_splits2[0]);
										//$('#dateFromId').val(data['dateFrom']);
										//$('#dateToId').val(data['dateTo']);
										printEnterRemark=data['remarks'];
										$('#remarksId').val(data['remarks']);
									},
									error : function(e)
									{
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
						$('#privatefundingjustification').show();
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
						
						annexurePath=data['annexureFile'];
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
						    	 externalfundamntIdArray[j]=externalfundamnt;
						    	 var unassignGrant=centerid1+"unassignGrant";

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
							 var tr=$('<tr><td>'+centerNameArray[j]+'</td><td><input class="centerHeads form-control" id="'+fundallocatedId+'" type="number" name="'+fundallocatedId+'" value="'+allocatedfundamountArray[j]+'" readonly></input></td><td><input class="exptill form-control" id="'+expincuId+'" type="number" value="'+expIncurred[j]+'" name="'+expincuId+'" disabled="disabled"></input></td><td><input class="balassign form-control" id="'+Balance_exclude_unassignedGrant+'" type="number" name="'+Balance_exclude_unassignedGrant+'" value="'+Balance_exclude_unassignedGrantArray[j]+'" disabled="disabled"></input></td> <td><input class="estimateHeads form-control" id="'+estimatedExp+'" type="number" name="'+estimatedExp+'"  value="'+estimatedExpArray[j]+'"></input></td> <td><input class="seminarHeads form-control" id="'+natnlsemnrcount+'" type="number" name="'+natnlsemnrcount+'"  value="'+natnlsemnrcountArray[j]+'"></input></td><td><input class="lectureHeads form-control" id="'+lecturecount+'" type="number" name="'+lecturecount+'"  value="'+lecturecountArray[j]+'"></input></td><td><input class="unassigrant form-control" id="'+unassignGrant+'" type="number" name="'+unassignGrant+'" value="'+unassignedGrantAmnt[j]+'"></input></td><tr>');
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
				   		 
				   		 
				   		 
				   		 
				   		 
				   		 
				   		},
				   		error : function(e) {
				   			//showMessage("Error in Getting Programs. Contact Admin");
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
   						//des=removeWhiteSpaceCharacter(des);
   						exheadarray[i]=des;
   						finyearexphead=data[i]['actvtyExpHdkey']['exp']['expHeadsKey']['finYear'];             //data[i]["expHeadsKey"]["finYear"]
   						console.log("getExpenditureHeadsByFinYear"+finyearexphead)
   						var nualsalloctinamount=des+"nualsalloctinamount";
   						var estmtdexpdture=des+"estmtdexpdture";
   						var deviation=des+"deviation";
   						var comments=des+"comments";
   						var sino=i+1;
   						var headgrantAmnt=des+"headgrantAmnt";
   						 tr=$('<tr><td>'+sino+'</td><td>'+des+'</td><td><input class="allocationheads form-control" id="'+nualsalloctinamount+'" type="number"  value="'+data[i]['nualsAllocationAmt']+'"></input></td><td><input class="estimateheads1 form-control" id="'+estmtdexpdture+'" type="number"  value="'+data[i]['estimatedExp']+'"></input></td><td><input class="deviationheads form-control" id="'+deviation+'" type="number"  value="'+data[i]['deviation']+'"></input></td><td><input class="commentsheads form-control" id="'+comments+'"  value="'+data[i]['comment']+'"></input></td><td><input class="asgrantedEstimHeads form-control" id="'+headgrantAmnt+'" value="0"></input></td><tr>');
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

								 tr=$('<tr><td>'+sino+'</td><td>'+des+'</td><td><input class="expadvreq form-control" id="'+advanceRequired+'" type="number" value="'+data[i]['amountAdvance']+'"></input></td><td><input class="expadvreqreason form-control" id="'+requirementReason+'"  value="'+data[i]['reasonAdvance']+'" ></input></td><td><input class="asgrantedAdvanceRequest form-control" id="'+asGrantedAdvanceAmnt+'" value="0"></input></td><tr>');
								 $('#advnceRequestGridBody').append(tr);
								
							}   
						 
							},
							error:function(e)
							{
							
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
	});//getllactivytdetails

}//function ends here

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
	getAllActivityAdministrativecApprovedByLogin();
	//$('#activityTypeDetails').hide();
   
	$('.dp').datepicker({
	    format: 'yyyy-mm-dd',							   
		autoclose:true,
		clearBtn:true
	});
   
	


	$( "#cancelbt" ).on("click",function() {
		console.log("Double clicked...");
		$('#activityTypeDetails').hide();
				
	});
	
	 $('#externalfundSelect').change(function(){ 
	       var value = $(this).val();
	       alert(value);
	       if(value=="no")
	       	{
	       	for(var i=0;i<externalFundArray.length;i++)
	       		{
	       		console.log("externalfundamntIdArray"+externalfundamntIdArray[i]);
	       		var extrnid="#"+externalfundamntIdArray[i];
	       	       $(extrnid).val("0");
	       		}
	       		
	       	
	       	
	       	}
	      
	 });
	 
	 
	       	
	 $('#ugcSelect').change(function(){ 
	       var value = $(this).val();
	       alert(value);
	       if(value=="no")
	       	{
	       	for(var i=0;i<ugcFundIdArray.length;i++)
	       		{
	       		console.log("ugcFundIdArrayArray"+ugcFundIdArray[i]);
	       		var extrnid="#"+ugcFundIdArray[i];
	       	       $(extrnid).val("0");
	       		}
	       		
	       	
	       	
	       	}
	      
	 });   
		
	   $("#totalExpenditure" ).on('click',function(event) {
		 	  
		   getTotalEstimatedExpenditure();
		 	  
		 	  
		   });

	   
	   $("#totaladvancereq1" ).on('click',function(event) {
	 	  
	 	  getTotalAdvanceRequired();
	 	  
	 	  
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
		var formCheck = "success";
			$('#submitModel').modal('show');
			$('#confId').unbind().bind('click',function(){
				console.log("confirm clicked...");
				$.ajax({
					"url" : "/academicactivity/AdministrativeApprove",
					"method" : "POST",
					 data : formData,
					 success : function(data)
					 {
						 console.log(data);
					 },
					 error : function(e) {
		   		   			//showMessage("Error in Getting Programs. Contact Admin");
		   		   		}

                 });
			});
         });
         
         //adding finance approval details
//enter activity finance details
$("#activityFinance" ).unbind().bind('click',function(event) {
	$('.balassign').attr('disabled',false);
	$('.exptill').attr('disabled',false);
	var i=0;
	var j=0;
	var k=0;
	var l=0;
	var m=0;
	var n=0;
	var o=0;
	var balassignArray=[];
	var estimateHeadsArray=[];
	var seminarHeadsArray=[];
	var lectureHeadsArray=[];
	var externlfundArray=[];
	var ugcFundArray=[];
	var asGrantedArray=[];
	var expIncurred=[];
	$('.balassign').each(function()
			{
   	var amnt=$(this).val();
	    balassignArray[i]=amnt;
  	i=i+1;
			});

	 $('.exptill').each(function()
		   		{
		   var amnt5=$(this).val();
		   expIncurred[o]=amnt5;
		   o=o+1;
		   		}); 
	$('.estimateHeads').each(function()
			{
 	var amnt1=$(this).val();
  	estimateHeadsArray[j]=amnt1;
    	j=j+1;
			});
	
	$('.seminarHeads').each(function()
			{
   	var amnt2=$(this).val();
	    seminarHeadsArray[k]=amnt2;
  	k=k+1;
			});
	
$('.lectureHeads').each(function()
			{
	var amnt3=$(this).val();
	lectureHeadsArray[l]=amnt3;
	l=l+1;
			});

var value1 = $('#externalfundSelect').val();
if(value1=="yes")
	{						
$('.externlfunds').each(function()
		{
var amnt5=$(this).val();
externlfundArray[m]=amnt5;
m=m+1;
		}); 	
	}
if(value1=="no")
{	
	var r;
	for(r=0;r<centerarray.length;r++)
		{
	externlfundArray[r]=0;
	
	}
}	
		
var value = $('#ugcSelect').val();
if(value=="yes")
	{			
$('.ugcFunds').each(function()
		{
var amnt6=$(this).val();
ugcFundArray[n]=amnt6;
n=n+1;
		}); 
	}	
if(value=="no")	
	{
	
	var p;
	for(p=0;p<centerarray.length;p++)
		{
		ugcFundArray[p]=0;
	
	    }
	}

$.ajax({
	"url":"/academicfinance/updateActivityCenterFinance",
	"method":"GET",
	 data:{"activityId":activityId,"centerarray":centerarray.toString(),"expIncurredArray":expIncurred.toString(),"balassignArray":balassignArray.toString(),"estimateHeadsArray":estimateHeadsArray.toString(),"seminarHeadsArray":seminarHeadsArray.toString(),"lectureHeadsArray":lectureHeadsArray.toString(),"externlfundArray":externlfundArray.toString(),"ugcFundArray":ugcFundArray.toString()},
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
		});	
//$('#formCenterActivityDetails').submit();

$( "#formCenterActivityDetails" ).submit(function( event ) {
	//alert( "Handler for .submit() called." );
	    $('.balassign').attr('disabled',false);
	    	$('.exptill').attr('disabled',false);
	    var privateFundValue = $('#privateFund').val();
	    if(privateFundValue=='yes')
	    	{
	    	approvedFunding='y';
	    	}
	    if(privateFundValue=='no')
	    	{
	    	approvedFunding='n';
	    	}
	    
	    var honorMore = $('#HonorMore3000').val();
	    var honor;
	    if(honorMore=='yes')
	    	{
	    	honor='y';
	    	}
	    if(honorMore=='no')
	    	{
	    	honor='n';
	    	}
	    
	    externalFunding=$('#externalfundSelect').val();
	    if(externalFunding=="yes")
	    	{
	    	externalFunding='y';
	    	}
	    
	    if(externalFunding=="no")
    	{
    	externalFunding='n';
    	}
   
	    
	    ugcFunded=$('#ugcSelect').val();
	    if(ugcFunded=="yes")
    	{
	    ugcFunded='y';
    	}
	    
	    if(ugcFunded=="no")
    	{
	    ugcFunded='n';
    	}
	    
	    adminstrativeApprovalRemarks=$('#administrativeOfficeApprovalRemarksId').val();
	    
	    console.log("hhhhhhhhhh"+honor);
	    console.log("eeeeeeeeee"+externalFunding);
	    console.log("approvedFunding"+approvedFunding);
	    console.log("ugcFunded"+ugcFunded);
	    console.log("HonorMore3000"+honor);
	    console.log("ac"+activityId);
		var formData2 = $(this).serializeArray();
	  	formData2.push({"name":"externalFunding","value":externalFunding});
		formData2.push({"name":"ugcFunded","value":ugcFunded});
		formData2.push({"name":"approvedFunding","value":approvedFunding});
		formData2.push({"name":"honorMore3000","value":honor});
		formData2.push({"name":"ac","value":activityId});
		formData2.push({"name":"activityFinanceCode","value":activityFinanceId});
		
	    event.preventDefault();
	     $.ajax({
		   	"url":"/academicfinance/administrativeOfficeApproval",
		   	"method":"POST",
		   	"data":formData2,
		    success:function(data)
		   	 {
		   		console.log("success fully insert activity finance"+data);
		   		addActivityFinanceflag="true";
		   	//	adminstrativeApprovalRemarks=$('#administrativeOfficeApprovalRemarksId').val();
		   		console.log("adminstrativeApprovalRemarks"+adminstrativeApprovalRemarks);
		   		
		   	 },
		   	 error:function(e)
		        {
		   		addActivityFinanceflag="false";
		   	 console.log("error in addActivityFinanceflag"+e);
		       }
		   	
		   	
	   });
	   
	 });

$( "#formCenterActivityDetails" ).submit();

getAllExpHeadDetails();
$.ajax({
	   	"url":"/expenditurehead/updateActivityEstimate",
	   	"method":"GET",
	   	 data:{"activityId":activityId,"finyearexphead":finyearexphead,"exheadarray":exheadarray.toString(),"allocationheadsarray":allocationheadsarray.toString(),"estimateheadsarray":estimateheadsarray1.toString(),"deviationheadsarray":deviationheadsarray.toString(),"commentsheadsarray":commentsheadsarray.toString(),"externlfundArray":externlfundArray.toString(),"asAmntGrantArray":asAmntGrantArray.toString()},
	   	 success:function(data)
	   	 {
	   		 //??
	   				 console.log("addActivityEstimate"+data);
	   		addActivityEstimateflag="true"; 
	   	 },
	   	 error:function(e)
	        {
	   		addActivityEstimateflag="false"; 
	   		 
	   	    console.log("error in addActivityEstimateflag"+e);
	       }	
	   		});	

//add advance estimate request ajax call
	var formData1=new Array();
	var map={};
					map['activityId']=activityId;
					map['finyearexphead']=finyearexphead;
					map['exheadarray']=exheadarray;
				    map['expadvreqarray']=expadvreqarray
				    map['expadvreqreasonarray']=expadvreqreasonarray
				    map['asGrantedAdvAmntarray']=asGrantedAdvAmnt
				
				    formData1.push(map);
   $.ajax({
	   	"url":"/expenditurehead/updateActivityAdvanceEstimateRequest",
	   	"method":"POST",
	   	data:{"activtyEstimateAdvncewrapper":formData1},
	   	 success:function(data)
	   	 {
	   		 console.log("updateActivityAdvanceEstimateRequest"+data);
	   		 
	   		addActivityAdvanceEstimateRequestflag ="true";
	   		 //addActivityAdvanceEstimateRequest ??
	   		 
	   	 },
	   	 error:function(e)
	        {
	   		addActivityAdvanceEstimateRequestflag="false"; 
	   		console.log("addActivityAdvanceEstimateRequest"+e);
	       }	
	   		});	
    
   centreDialog("Success","Administrative Office Approval successfully done","green");
   
 	  //showMessage("Administrative Approval successfully done "+activityId);
 	// adminstrativeApprovalRemarks=$('#administrativeApprovalRemarksId').val();
 	 
 	 totalAdv=$('#totalAdvanceReqId').val();
 	  $("#formCenterActivityDetails").trigger('reset');
   

});
$("#totalEstimatedExpenditure" ).on('click',function(event) {
	  
	   getTotalEstimatedExpense();
	 	  
	 	  
	   });  
	   
$('#printBt').unbind().bind('click',function()
		{
		
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
			//	$('#adminApprovalRemarkstd').append(data['administrativeApprovalRemarks']);
			//	$('#adminApprovalRemarkstd').append(adminstrativeApprovalRemarks);
														
			for(var key in d)
				{
					$('#'+key).val(d[key]);
					$('#'+key).text(d[key]);
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
		
						 },
						  error:function(e)
				   			 {
				   				 console.log("error"+e);
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
									    	// alert("entrrr");
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
				   					 tr=$('<tr><td>'+des+'</td><td>'+data[i]['nualsAllocationAmt']+'</td><td>'+data[i]['estimatedExp']+'</td><td>'+data[i]['deviation']+'</td><td>'+data[i]['comment']+'</td><td>'+data[i]['asGrantedAmnt']+'</td></tr>');
				   						 //console.log("getExpenditureHeadsByFinYear"+tr);
				   					   $('#estimtab').append(tr);
											}
										
									var	tr1=$('<tr><td>Total Estimated Expenditure</td><td>'+totalNualsAllocationRep+'</td><td>'+totalEstimRep+'</td><td>'+totaldeviationIdRep+'</td><td>'+'</td><td>0</td><td></tr>');
										 $('#estimtab').append(tr1);
										 
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
												  var	tr=$('<tr><td>'+des+'</td><td>'+data[i]['amountAdvance']+'</td><td>'+data[i]['reasonAdvance']+'</td><td>'+data[i]['asGranted']+'</td></tr>');
														
														
														//tr=$('<tr><td>'+sino+'</td><td>'+des+'</td><td><input class="expadvreq form-control" id="'+advanceRequired+'" type="number" value="'+data[i]['amountAdvance']+'"></input></td><td><input class="expadvreqreason form-control" id="'+requirementReason+'"  value="'+data[i]['reasonAdvance']+'" ></input></td><td><input class="asgrantedAdvanceRequest form-control" id="'+asGrantedAdvanceAmnt+'" value="0"></input></td><tr>');
														// $('#advnceRequestGridBody').append(tr);
														  $('#advtab').append(tr);
													}
														
													var	tr1=$('<tr><td>Total Advance Required</td><td>'+totalAdv+'</td></tr>');
													 $('#advtab').append(tr1);
														$('#remarksPr').append(printEnterRemark);
														
								 $('#adminApprovalRemarkstd').append(adminstrativeApprovalRemarks);
														
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
										
									
							},
										 error:function(e)
										 { 
										   console.log(e);
										 }
										 });
			
			},
			 error:function(e)
				 {
					 console.log("error"+e);
				 }
				 });
		
		});
              
});
	
</script>

<div  id="gridRow" class="card card-info card-outline">
  	<div class="card-header">
		  <h3>List Of Activities</h3>
	  
  	</div>


    <div class="card-body">
	   	<div class="table-responsive">
				<table class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border" id="activityTable1">
			 
				</table>
		</div>
	</div>
</div>

<div  id="activityApproval" class="card card-info card-outline">
   <div class="card-header">
      <h3><center>Administrative Office Verififcation of Activities/Programmes</center></h3>
   </div>
   <div class="card-body">
      <form id="formCenterActivityDetails" >
         <div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="form-group">
               <label for="activitytype"> Name/Title of the Program</label> <input
                  type="text" name="title" class="form-control" id="titleId"
                  required />
            </div>
             <div class="row">
               <div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
                  <label for="date from"> From</label> <input type="text"
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
              
              <div class="form-group">
                  <div id="divGridTable2" class="row" style="display: none">
                     <div class="col-md-12">
                        <div class="table-responsive cell-border">
                           <table id="gridTableMapper" class="table table-striped">
                              <thead>
                                 <tr>
                                    <th scope="col">Centre</th>
                                    <th scope="col">Fund Allocated to the Centre for the
                                       Current Financial Year
                                    </th>
                                    <th scope="col">Expenditure incurred (Including firm
                                       commitments made) till date
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
                                 <th scope="col">Centre</th>
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
                   <div class="form-group">

              <button doc="annextureImage" class="btn btn-primary upload" id="viewId" >View Brochures</button>     
                </div>
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
                                 <th scope="col">International</th>
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
               <h5 style="text-decoration: underline;">Proposed expenses for
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
                                 <th scope="col">International</th>
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
                        <option value="se">Select</option>
                        <option value="yes">Yes</option>
                        <option value="no">No</option>
                     </select>
                  </div>
               </div>
                     <div class="form-group row"  class="col-sm-3"> <button type="button" id="totalEstimatedExpenditure" class="btn btn-primary">Get Total Programme Estimate Expenditure </button></div>
               
                     <div class="form-group row">
                  <label for="activitytype" class="col-sm-6 col-form-label">Total Estimated Expenditure For the Programme</label>
                  <div class="col-sm-6">
                    <input type="number"  class="form-control  col-sm-4" name="totalEstExp" id="totalEstExpId" ></input>
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
                                  <th scope="col">As Granted</th>
                              </tr>
                           </thead>
                           <tbody id="expenditureGridBody">
                           </tbody>
                        </table>
                     </div>
                  </div>
               </div>
              <div class="form-group row"  class="col-sm-3"> <button type="button" id="totalExpenditure" class="btn btn-primary">Get Total EstimateExpenditure</button></div>
               
                        <!--  <div class="form-group row">
                   <label for="activitytype" class="col-sm-2 col-form-label">Total Estimated Expenditure
                  </label>
                  <div class="col-sm-2">
                      <input type="text" name=total_estimated class="form-control"
                        id="totalEstExpIdd" >
                  </div>
                   <label for="activitytype" class="col-sm-2 col-form-label">Total AsGranted Amount
                  </label>
                  <div class="col-sm-2">
                     <input type="text" name="totalAsGrantedAmnt" class="form-control"
                        id="totalAsGrantedAmntId" required>
                  </div>
                 
               </div> -->
               <div class="form-group row">
                 <label for="activitytype" class="col-sm-2 col-form-label">Total NUALS Allocation Amount
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
                   <label for="activitytype" class="col-sm-2 col-form-label">Total AS Granted Amount
                  </label>
                  <div class="col-sm-2">
                     <input type="text" name="totalAsgranted" class="form-control"
                        id="totalAsGrantedId" required>
                  </div>
               </div>
               
               
             </div>
             
              <h5 style="text-decoration: underline;">Details of Advance Requirement if Any
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
             
                <div class="form-group row"  class="col-sm-3"> <button type="button" id="totaladvancereq1" class="btn btn-primary">Get Total</button></div>
               <div class="form-group row">
                  <label for="activitytype" class="col-sm-6 col-form-label">Total
                  Advance Required </label>
                  <div class="col-sm-6">
                     <input type="text" name="totalAdvanceReq" class="form-control"
                        id="totalAdvanceReqId" required>
                  </div>
               </div>
                <div class="form-group">
               <label for="remarks">Remarks</label>
               <textarea id="remarksId" name="remarks" rows="4" cols="50"
                  class="form-control"></textarea>
            </div>
                  <div class="form-group">
               <label for="activityLevel">Administrative Office Approval<span
                  class="required">*</span></label>
               <div style="width: 100%">
                  <div style="display: inline-block; width: 100%">
                     <select class="form-control selectpicker" name="adminofficeapprovesStatus"
                        id="adminofficeapproveId" required>
                        <option value="">Select</option>
                        <option value="administrative office verified">Approve</option>
                        <option value="administrative office rejected">Reject</option>
                        
                     </select>
                  </div>
               </div>
            </div>
            
            <div class="form-group">
               <label for="remarks"> Remarks For Administrative Office Approval</label>
               <textarea id="administrativeOfficeApprovalRemarksId" name="officeApprovedRemarks" rows="4" cols="50"
                  class="form-control"></textarea>
            </div>
            <div class="form-group" style="text-align: center">
               <button type="submit" class="btn btn-primary" id="activityFinance">SUBMIT</button>
               
            </div>
       
      </form>
       <div class="form-group" style="text-align: center">
               
                <button class="btn btn-primary" id="printBt">PRINT</button>
            </div>
   </div>
</div>
<div  id="gridRow" class="card card-info card-outline">
  	<div class="card-header">
		  <h3>List Of Activities Administrative Approved</h3>
	  
  	</div>
    <div class="card-body">
	   	<div class="table-responsive">
				<table class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border" id="administrativepproveactivityTable">
			 
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
			<table  id="repTable">
				<tr><td colspan="4">
				 <img src="../images/logo.jpg" style="width: 40px;">
				</tr>
				<tr td colspan="4"><center> REQUEST FOR ADMINISTRATIVE OFFICE VERIFICATION FOR PROGRAMMES CONDUCTED BY NUALS/ VARIOUS  CENTRES OF STUDY AND RESEARCH </center><td></tr>
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
                <tr><td  colspan="4"><u>Remarks</u></td></tr>
				<tr><td id="remarksPr" colspan="4"></td></tr>   
				<tr><td  colspan="4"><u>Administrative Office Approval Remarks </u></td></tr>   
				<tr><td id="adminApprovalRemarkstd" colspan="4"></td></tr>  
				<tr><td>Kalamassery</td><td></td><td></td><td>Director</td></tr>
				<tr><td ></td></tr>
				<tr><td ></td></tr>
				     
                                
			</table>	
		</div>
	</div>

</div>