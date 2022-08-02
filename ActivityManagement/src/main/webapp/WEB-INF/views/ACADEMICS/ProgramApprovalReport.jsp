<script>
var unwanted=['aat']
var centres=new Array();
var resources=new Array();
$(document).ready(function()
{
	$.ajax(
	{
		"url" : "/academicactivity/getAllAcademicApprovalDetailsReport",
		"method" : "GET",						
		"data" : {"activityId":5046},
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
			//$('#dialogShow').modal({backdrop: 'static',keyboard: false});
		}
	});
	$('#printRep').unbind().bind('click',function()
	{
		printReport2("reportDiv");
	});
})
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
				<tr><td colspan="4"><u>Expected Outcome and Quantified Deliverables</u></td></tr>
				<tr><td id="outcome" colspan="4"></td></tr>
				<tr><td colspan="4"><u>Resource Persons </u></td></tr>
			    <tr><td colspan="4"><table id="resourcestab" class="table table-striped"></table> </td></tr>
				<tr><td colspan="4">Observed Deviations from University Rules,regulations and Govt Orders</td></tr>
				<tr><td id="deviationJustification" colspan="4"></td></tr>
				
				
				<tr><td id="deviationJustification" colspan="4"></td></tr>
				<tr><td id="deviationJustification" colspan="4"></td></tr>
				<tr><td>Kalamassery</td><td></td><td></td><td>Director</td></tr>
				<tr><td ></td></tr>
				<tr><td ></td></tr>
				<tr><td  colspan="4"><u>Approval/Remarks of the Vicechancellor/Academic Authority</u></td></tr>
				<tr><td id="adminApprovalRemarks" colspan="4"></td></tr>
				
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