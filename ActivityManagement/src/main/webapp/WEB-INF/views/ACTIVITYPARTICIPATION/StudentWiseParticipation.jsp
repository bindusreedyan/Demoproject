<script>
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

function getAcademicProgramData()
{
$.ajax(
{
"url" : "/academicactivity/getStudentWiseReport",
"method" : "GET",
success : function(data)
{
setUpDataTable(data,[{"participationRequestId":"Particiption Request Id "},{"title":"title"},{"av":"Activity Venue"},{"hi":"Hosting Instituition"},{"competion_start":"Start"},{"competion_end":"competion_end"},{"requestStatus":"Request Status"}],"gridTable",null);
makeFullCapableDataTable('gridTable');
}
});
}
$(document).ready(function()
{
getAcademicProgramData();
$('#printRep').unbind().bind('click',function()
{
printReport2("reportDiv");
});
})
</script>

<div class="row">
<div class="col-md-12">
<div class="table-responsive">
<table class="table table-striped" id="gridTable">
</table>
</div>
</div>
</div>