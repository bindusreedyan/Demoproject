
<link rel="stylesheet" href="./sfs_public/css/jquery-confirm.min.css">
<script src="./sfs_public/js/jquery-confirm.min.js"></script>
<script>
var fileFormData;
var activityId;
function getAllActivityType(category)
{
	
	alert(category);
 	$.ajax({
   		"url" : "/academicactivity/getAllActivityTypesByCategory",
   		"method" : "GET",
   		data:{"cat":category},
   		success : function(data) {
   			console.log(data);
   			setUpDropDown("activityTypeCodeId",data,"activityTypeCode","activity_type_description");
   		},
   		error : function(e) {
   			//showMessage("Error in Getting Programs. Contact Admin");
   		}
   	})
}
 	//to get all cordiator
 	
	
	
	
$(document).ready(function()
		   {
	
	$('.dp').datepicker({
   	    format: 'yyyy-mm-dd',							   
   		autoclose:true,
   		clearBtn:true
   	});
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

	$("#ActivityTypeGroup").change(function() {
		
		   var cat=$(this).val();
		   alert(cat);
		   getAllActivityType(cat);
});
	
	$.ajax({
   		"url" : "/ems/getAllFacultyMasterDetails",
   		"method" : "GET",
   		success : function(data) {
   			console.log(data);
   			setUpDropDown("cordinatorId",data,"emp_id","emp_name");
   		},
   		error : function(e) {
   			//showMessage("Error in Getting Programs. Contact Admin");
   		}
	
		   });
	
	
	
	
	

	
	
	
	
	
	
	
	//adding preapproved activities
	   $('#activityMasterDetailsForm').unbind().bind('submit',function(event) 
			 {
		
			var formData = $(this).serializeArray();
		 	formData.push({"name":"type","value":"participating"});
			var formCheck = "success";
		
			alert("enter in activityMasterDetailsForm");
			var formData = $(this).serializeArray();
		 	formData.push({"name":"type","value":"participating"});
			var formCheck = "success";
			event.preventDefault();
				$('#submitModel').modal('show');

				$('#confId').unbind().bind('click',function(){
					
					console.log("confirm clicked...");
			        $.ajax({
					"url" : "/academicactivity/addActivityMasterforPreapproved",
					"method" : "POST",
					 data : formData,
					 success : function(data) {
						 
						 console.log("data"+data);
						 activityId=data['activityCode'];
						 centreDialog("Successfully saved Program Details with activity Id"+activityId,data,"green");
						 console.log($('#brochureURLId').val());
					      var fileName=activityId+"_"+"brochure_"+$('#brochureURLId').val();
					     // fileFormData.append('activityId',activityId);
					      
					  	    console.log($('#brochureURLId').val());
							var fileName=activityId+"_"+"brochure_"+$('#brochureURLId').val();
						//	var urlGen=String(pgm)+"/"+String(batch)+"/"+String(SelectedOrMappedUser);
							//fileFormData.append('fileUrl',urlGen);
							//fileFormData.append('brochure_file',fileName);
						    fileFormData.append('activityId',activityId);
							$.ajax(
							  {
								"url" : "/academicactivity_public/saveDocs",
								"method" : "POST",						
								data : fileFormData,
								processData: false, // Don't process the files
								contentType: false, 
								enctype:"multipart/form-data",
								success : function(data) 
								{
									if(data=="success")
									{
										showMessage("File saved successfully");
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
			        
			        
			        });//end /academicactivity/addActivityMaster
			    	$('#submitModel').modal('hide');
					
				});//end of confId
		   
				$('#cancelId').unbind().bind('click',function(){

					console.log("Cancel clicked...");

					$('#submitModel').modal('hide');
					
				});
		   
		   
		   
		   
			 });  //end activityMasterDetailsForm
		$('.file').unbind().bind('change', function(){
			var ext=$(this).attr('ext');
			var files = $(this).get(0).files;
			if (files.length > 0)
			{
				fileFormData = new FormData();
				fileFormData.forEach(function(val, key, fD){
				    // here you can add filtering conditions
				    fileFormData.delete(key)
				    });
				// loop through all the selected files
				for (var i = 0; i < files.length; i++) {
				var file = files[i];
				fileType = file["type"];
				var fullFileName=file.name;
				if(fullFileName.match(/\./g).length>1)
				{
					showMessage("Invalid File Type.Correct your file name or upload only file with extension "+ext);
					$(this).val('');
					return false;
				}
				else
				{
					var uploadExt=file.name.split('.')[1];
					if(uploadExt==ext)
					{
						fileFormData.append('file', file, file.name);
						//fileFormData.append('activityId',activityId);
					}
					else
					{
						showMessage("Invalid File Type.Correct your file name or upload only file with extension "+ext);
						$(this).val('');
					}
					//fileFormData.append()
				}
				}
			  }
		 });
			   
		   });
</script>


<div class="card card-info card-outline">
	<div class="card-header">
		<h3>PreApproved Activity Details</h3>
		<form id="activityMasterDetailsForm" enctype="multipart/form-data">
		<div class="row">
         <div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
         	<div class="form-group" style="width: 100%">
               <label for="activityType">Activity Type Category<span
                  class="required">*</span></label>
               <div style="width: 100%">
                  <div style="display: inline-block; width: 100%">
                    <select class="form-control" id="ActivityTypeGroup" name="ActivityTypeGroup">
                        <option value="se">Select</option>
                        <option value="academic">Academic</option>
                        <option value="others">Others</option>
                     </select>
                  </div>
               </div>
            </div>
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
          
       
             <div class="form-group">
                  <label for="date from">Date From</label> <input type="text"
                     id="dateFromId" name="dateFrom" class="form-control dp" required></input>
               </div>
            <div class="form-group">
               <label for="activitytype"> Topic to be Presented</label> <input
                  type="text" name="topic" class="form-control" id="topicId"
                  required />
            </div>
               <div class="form-group">
                  <label for="date to">Upload Brochure</label>
                   <input class="file"  id="brochureURLId" name="brochureURL" type="file" ext="jpg" ></input>  
               </div>
              
             
         </div>
		
		   <div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
                <div class="form-group">
               <label for="activityLevel">Activity/Event Level<span
                  class="required">*</span></label>
               <div style="width: 100%">
                  <div style="display: inline-block; width: 100%">
                     <select class="form-control selectpicker" name="activityLevel"
                        id="activityLevelId" required>
                        <option value="se">Select</option>
                        <option value="local">Local</option>
                        <option value="national">National</option>
                        <option value="international">International</option>
                     </select>
                  </div>
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
               <div class="form-group">
               <label for="activityLevel">Faculty Co-ordinator<span
                  ></span></label>
               <div style="width: 100%">
                  <div style="display: inline-block; width: 100%">
                     <select class="form-control selectpicker" name="facultyCode"
                        id="cordinatorId" >
                       
                     </select>
                  </div>
               </div>
            </div>
            <div class="form-group">
                  <label for="date to">Date To</label> <input type="text"
                     id="dateToId" name="dateTo" class="form-control dp" required></input>
               </div>
         </div>
         </div>
         <div class="row">
           <div class="form-group">
               <label for="remarks"> Remarks</label>
               <textarea id="remarksId" name="remarks" rows="4" cols="200"
                  class="form-control"></textarea>
            </div>
                 <div class="form-group" style="text-align: center">
               <button type="submit" class="btn btn-primary">SUBMIT</button>
            </div>
            </div>
         </form>

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
<!-- Modal HTML End -->