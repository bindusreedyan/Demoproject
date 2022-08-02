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

var status;
var headId;
function loadAllExpHeads() {
	$.ajax({
		"url" : "/expenditurehead/getAllExpHead",
		"method" : "GET",
		success : function(data) {
			
			console.log(data);
			
			setUpDataTable(data, [ {
				"headId" : "Expenditure Head Id"
			}, {
				"expHeadName" : "Expediture Head"
			},
			
			{
				"expHeadStatus" : "Status"
			}
		  ], "expHeadTable", "select-checkbox");

			
			onDataTableClick('expHeadTable', function() {
				
				$('#headIdId').val(selectedRowFromDataTable[1]);
				headId=selectedRowFromDataTable[1];
				
				$('#expHeadNameId').val(selectedRowFromDataTable[2]);
				$.ajax({
					"url":"/expenditurehead/getExpHeadDetailsbyHeadid",
					"method":"get",
					"data":{"headId":headId},
					success:function(data)
					{
						$('#verifiedRemarkId').val(data['verifiedRemark'])
					},
					error:function(e)
					{
						console.log(e)
					}
					
				});
				
				status=selectedRowFromDataTable[3];
				 if(status=="verified")
				 {
				 $('#verifybtnid').attr('disabled',true);
				 }
			 else
				 {
				 $('#verifybtnid').attr('disabled',false);
				 }
			})

			

		}
	});

}

$(document).ready(function()
{
				
	$('#menuHaed').text("");
	$('.dp').datepicker({
	    format: 'yyyy-mm-dd',							   
		autoclose:true,
		clearBtn:true
	});
	
	loadAllExpHeads();
	


	restrictSpecialChars("activityTypeCode");	
	restrictSpecialChars("description");	
	/*restrictSpecialChars("addressId");		
	restrictSpecialChars_allow_dot_comma("amenitiesId");
	restrictSpecialChars_allow_dot_comma("geoCoordinatesId");*/
	//restrictSpecialChars_allow_dot_comma("remarksId");
	
	
	
	/*$("#areaOfHostelId").keypress(function (e) {
        var length = jQuery(this).val().length;
      if(length > 9) {
           return false;
      } 
      else if((length > 0) && (e.which == 46)) { //This condition is required for floating point numbers...
          return true;
     } 
      else if(e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
           return false;
      } else if((length == 0) && (e.which == 48)) {
           return false;
      }
      
      
	});*/
   
$('#expHeadForm').unbind().bind('submit',function(event) 
{
	
	        console.log("entrrr in expHeadForm");
			event.preventDefault();
			var formData = $(this).serializeArray();
		
			//formData.push({"name":"","value":""});
			
			var formCheck = "success";

				$('#submitModel').modal('show');

				$('#confId').unbind().bind('click',function(){

					console.log("confirm clicked...");

					$.ajax({
						"url" : "/expenditurehead/approveExpenditureHeadNames",
						"method" : "POST",
						 data : formData,
						 success : function(data) {
							console.log(data);

							
							if(data=='Error in verifying Expenditure Head Details')
								{
								centreDialog("Error",data,"red");
								}
							else
								{
								centreDialog("Success",data,"green");
								}
									//showMessage("Hostel Floor Details Saved.");
								
								

								$("#expHeadForm").trigger('reset');
								//clearDataTable("activityTypeTable");
								loadAllExpHeads();
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

<div class="card card-info card-outline">
 	<div class="card-header">
		   <h3>Expenditure Head Details</h3>
  	</div>
    <div class="card-body">
	  <form id="expHeadForm">
	  		
				<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
				
					<!--  <div class="form-group">
						<label for="activityTypeCodeId">Activity Type Code</label> 
								<input type="text" name="activityTypeCode" class="form-control" id="activityTypeCodeId" required/>											
					</div>-->
					
				<div class="form-group" style="width: 100%">
               <label for="activityType">Expenditure Head Id<span
                  class="required">*</span></label>
               <div style="width: 50%">
                  <div style="display: inline-block; width: 100%">
                  <input type="text" id="headIdId"  name="headId" class="form-control " required></input>
                  </div>
               </div>
            </div>			
				
			<div class="form-group" style="width: 100%">
               <label for="activityType">Expenditure Head Name<span
                  class="required">*</span></label>
               <div style="width: 50%">
                  <div style="display: inline-block; width: 100%">
                  <input type="text" id="expHeadNameId"  name="expHeadName" class="form-control " required></input>
                  </div>
               </div>
            </div>
            		<div class="row">
			<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
		    <div class="form-group">
              	<label for="activitytype">Verify Remarks</label> <textarea id="verifiedRemarkId" name="verifiedRemark" rows="4" cols="50"
                  class="form-control"></textarea>
            </div>
            </div>
            </div>
            <div class="row">
			<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
		    <div class="form-group">
              	<label for="activitytype">Approve Remarks</label> <textarea id="verifiedRemarkId" name="verifiedRemark" rows="4" cols="50"
                  class="form-control"></textarea>
            </div>
            </div>
            </div>
			
	        <div class="form-group" style="text-align:center">
						<button type="submit" class="btn btn-primary" id="verifybtnid">APPROVE</button>		
			</div>
			
				</div>
  
	  </form>
	  
	</div>
</div>

 <br/>
<div  id="gridRow" class="card card-info card-outline">
  	<div class="card-header">
		  <h3>Expenditure Head</h3>
	  
  	</div>


    <div class="card-body">
	   	<div class="table-responsive">
				<table class="table table-bordered table-hover dataTable dtr-inline collapsed table-sm cell-border" id="expHeadTable">
			 
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
					<span style='font-size:50px;'>&#9989;</span>
				</div>				
				<h4 class="modal-title">Are you sure?</h4>	
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			</div>
			<div class="modal-body">
				<p>Do you really want to Submit ?</p>
			</div>
			<div class="modal-footer">
				<button id="cancelId" type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
				<button id="confId" type="button" class="btn btn-primary">Confirm</button>
			</div>
		</div>
	</div>
</div> 
<!-- Modal HTML End -->
