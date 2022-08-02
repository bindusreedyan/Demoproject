<!-- 
<link rel="stylesheet" href="./sfs_public/css/jquery-confirm.min.css">
<script src="./sfs_public/js/jquery-confirm.min.js"></script>
 -->
<style> 

#gridTable {
  border-radius: 5px;
  /*background-image: url('sfs_public/img/background-purple.jpg');*//* sfs_public/img/background-purple.jpg */
  /*background-repeat: contain;*/
  background-color:#5299eb;
  
} 

#tableHeader {
  border-radius: 5px;
  /*background-image: url('sfs_public/img/background-purple.jpg');*//* sfs_public/img/background-purple.jpg */
  /*background-repeat: contain;*/
  background-color:#e88a6f;
  
} 

#tableInfo
{
  color: white;
  padding: 8px;
  font-family: Arial;
  border-radius: 0px;
  background-color: #2196F3;
}


#activityFormRowId
{
  border-radius: 5px;
  border: 1px solid #9ef0e3; 
  background-color:#e1e3f2;
}

</style>


<div class="row">
	<div class=" col-12 col-xs-12">

	<div class="row" id="activityFormRowId">
		<div class=" col-12 col-xs-12">
		<div id="" style="margin-left:20px;">
		  <h2>Activity Types</h2>
		  
				  <form id="ActivityTypeForm">
				  	
				  	<div class="row">				  	
					  	
						<div class=" col-lg-6 col-md-6 col-sm-12 col-xs-12">
						
							<div class="form-group">
								<label for="codeId">Activity Code</label> 
									<input type="text" class="form-control" name="activitycode"  id="activitycodeId"
									aria-describedby="accountHelp" placeholder="Enter Activity Code"> 						
							</div>
							
							<div class="form-group">
								<label for="courseCodeId">Activity Type</label> 
										<select class="form-control selectpicker" name="typeCode" id="typeCodeId">																		  										  
											
										</select>											
							</div>
							
							<div class="form-group shadow-textarea">
								  <label for="remarksId">Description</label>
								  <textarea class="form-control z-depth-1" name="description"  id="descriptionId" rows="3" placeholder="Enter Description......"></textarea>
							</div>
							
							<div class="form-group">
								<label for="">From</label> 
								 <div class="input-group date" id="examDateId">
				                    <input type="text" name="examDate" class="form-control" id="examDateTextId" />
				                    <span class="input-group-addon">
				                        <span class="glyphicon glyphicon-calendar"></span>
				                    </span>
				                </div>
							</div>
							
						</div>
						<div class=" col-lg-6 col-md-6 col-sm-12 col-xs-12">
						
						
						
							<input type="hidden" name="status" value="valid"/>					
							
							<div class="form-group" style="text-align:center">
								<button type="submit" class="btn btn-primary">ADD</button>		
							</div>
								
						</div>				
					</div>
				</form>
			</div> 
		  <p></p>
		</div>
		
		</div>
	</div>
	
	<div class="row">		
		<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
		<br/><br/>	
		</div>
	</div>
	
	</div>

		<div class="row" id="gridRow">
			<div class="col-12 col-xs-12">
				<div class="table-responsive">					
					<table  class="table table-striped cell-border">
						  <tr id="tableHeader">
						    <th></th>
						    <th style="text-align:center;"> <h4 style = "color:#35357a"> <strong><i>LIST OF ACTIVITY TYPES</i> </strong></h4></th>
						    <th></th>
						  </tr>
					</table>
					<table class="table table-sm table-dark" id="gridTable">
					 
					</table>	
					
				</div>
			</div>
		</div>


<script type="text/javascript">

var actArray = new Array();

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


function loadAllActivityTypes() {
	$.ajax({
		"url" : "/academic/getAllActTypes",
		"method" : "GET",
		success : function(data) {
			actArray=data;
			console.log(data);
			
			setUpDataTable(data, [ {
				"code" : "Activity Type Code"
			}, {
				"description" : "Description"
			},
			{
				"budgetCode" : "Budget Code"
			},			
			{
				"status" : "Status"
			} ], "gridTable", "select-checkbox");

			onDataTableClick('gridTable', function() {
				
			})

			
		}
	});

}


$(document).ready(function()
{
				
	$('#menuHaed').text("");
	
	loadAllActivityTypes();
	$('#gridRow').show();
  
$('#ActivityTypeForm').unbind().bind('submit',function(event) 
{
			event.preventDefault();
			var formData = $(this).serializeArray();
			
			var formCheck = "success";
			
			var  codeId= $("#codeId").val();
			var  descriptionId= $("#descriptionId").val();
			var  budgetCodeId= $("#budgetCodeId").val();
			

			
			if(codeId == '' || descriptionId == '' || budgetCodeId =='se')
			{
				formCheck = "fail";				
				showMessage("Provide all the data Required.");
				//centreDialog("Error","Provide all the data Required.","red");
			}
			
					
			if(formCheck == "success")	
			{

					$.ajax({
						"url" : "/academic/saveActivityType",
						"method" : "POST",
						data : formData,																					            							
						success : function(data) {
							console.log(data);

							
							if(data !== null)
							{
								var msg = "Activity Type Added."
								
								showMessage(msg);
								//centreDialog("Success",msg,"green");
	
								$("#ActivityTypeForm").trigger('reset');
							}
							
							loadAllActivityTypes();
							$('#gridRow').show();
							
						},
						error : function(e) {
							//centreDialog("Error in Marking Attendance. Contact Admin"+e);
							showMessage("Error in Marking Attendance. Contact Admin");
							
						}
					});

					
				}
			else
			{
				//showMessage("Select All the Required Fields..");
			}

			
	
});


		
	
});
</script>


