
<script>
var studentDetails={};

var unwanted=['aat']
var centres=new Array();
function makeFullCapableDataTable(tablename)
{
$("#"+tablename).DataTable().destroy();
    $('#'+tablename+ ' tfoot th').each( function ()
    {
        var title = $(this).text();
        console.log(">>>>>>>>>>>>>");
        console.log(title);
        $(this).html( '<input type="text" placeholder="Search '+title+'" />' );
    });

    var dtable=$('#'+tablename).DataTable({
        dom: 'Bfrtip',
        buttons:
        [
      {
        extend: 'print',
        exportOptions: {
      columns: ':visible'
                 }
             },
              {
                extend: 'copyHtml5',
                exportOptions: {
                columns: ':visible'
                }
            },
            {
                extend: 'excelHtml5',
                exportOptions: {
                    columns: ':visible'
                }
            },
            {
                extend: 'pdfHtml5',
                exportOptions: {
                columns: ':visible'
                }
            },
            'colvis'
        ],
        columnDefs: [ {
            targets: -1,
            visible: true
        } ],
        initComplete: function () {
            // Apply the search
            this.api().columns().every( function () {
                var that = this;
 
                $( 'input', this.footer() ).on( 'keyup change clear', function () {
                    if ( that.search() !== this.value ) {
                        that
                            .search( this.value )
                            .draw();
                    }
                } );
            } );
        }
    } );
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
$(document).ready(function()
{
	
	getAllPrograms();
	$('#btnCallStudents').on('click',function()
			{
		var centerCode=$('#centreCodeId').val();
		alert(centerCode);
		$.ajax(
				{
				"url" : "/academicactivity/getAllAcademicProgramReportByCenter",
				"method" : "GET",
				data:{"center":centerCode},
				success : function(data)
				{
					setUpDataTable(data,[{"programCat":"programCategory"},{"programType":"programType"},{"title":"title"},{"center":"Conducted By"},{"from":"From"},{"to":"To"},{"totalExp":"Total Expenditure"},{"programStatus":"Current Status"}],"gridTable",null);
				makeFullCapableDataTable('gridTable');
				}
				});
			});
	$.ajax({
		"url" : "/academicactivity/getAllCentres",
		"method" : "GET",
		success : function(data) {
			console.log(data);
			setUpDropDown("centreCodeId",data,"centre_id","centre_name");
		},
		error : function(e) {
			//showMessage("Error in Getting Programs. Contact Admin");
		}
	});
	$('#printRep').unbind().bind('click',function()
			{
			printReport2("reportDiv");
			});

});

</script>

<div class="row well" id="selectionPanel">
		<div class="col-md-4">
		     <label for="accountName">Select Center</label>
		  <select class="form-control selectpicker"  name="centreCode" id="centreCodeId" required></select>
		</div>
	
		
	 </div> 
	 <div class="row">
		<div class="col-md-4"></div>
		<div class="col-md-4"><button id="btnCallStudents" class="btn btn-primary form-control">Apply</button></div>
		<div class="col-md-4"></div>
	</div>
<hr>
	
   <div class="row">
<div class="col-md-12">
<div class="table-responsive">
<table class="table table-striped" id="gridTable">
</table>
</div>
</div>
</div>        
               

		
		
