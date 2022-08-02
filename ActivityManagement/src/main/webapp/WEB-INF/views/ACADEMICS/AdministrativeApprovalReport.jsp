<script>
var unwanted=['aat']
var centres=new Array();
var resources=new Array();
var activityId;
var finyear='2020-2021';
var centerarray=[1446];
var exheadarray=[];
var finyearexphead;
$(document).ready(function()
{
	activityId=5023;
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
										},
										error:function(e)
										{
										
										}
									
								});
				
	$('#printRep').unbind().bind('click',function()
	{
		printReport2("reportDiv");
	});
	
})
function reportPrinting(divId,titleText)
{
   var divToPrint = document.getElementById(divId);
    var htmlToPrint = '' +
        '<style type="text/css">' +
        'table th, table td {' +
       // 'border:1px solid #000;' +
        'padding:0.5em;' +
        '} table{width:100%}' +
        '</style>';
    htmlToPrint += divToPrint.outerHTML;
    var newWin = window.open("");
    newWin.document.write('<html style="width:794px; height:1122px; border: .5px solid"><head><title>National University of Advanced Legal Studies</title>');
    newWin.document.write(htmlToPrint);
    newWin.print();
    newWin.close();	
}
</script>
<div class="row">
	<div class="col-md-8"></div>
	<div class="col-md-4"><button class="button btn-success" id="printRep">Print Report</button></div>
</div>
<hr>
<div class="row">
	<div class="col-md-12">
		<div class="table-responsive" id="reportDiv">
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
				 <tr><td ></td></tr>
				 <tr><td ></td></tr>
				<tr><td  colspan="4"><u>Approval/Remarks of the Vicechancellor/Academic< Authority</u></td></tr>        
                                
			</table>	
		</div>
	</div>

</div>
<!-- 
<div class="modal fade" id="dialogShow" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Activity details</h5>
		 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
		<div class="modal-body" id="display">
			<form id="displayform">
			
			</form>
		</div>
	    <div class="modal-footer"></div>
    </div>
  </div>
</div> -->