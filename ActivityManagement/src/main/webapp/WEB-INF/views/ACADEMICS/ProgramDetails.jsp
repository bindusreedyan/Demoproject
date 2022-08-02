<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="modal fade" id="programDeatils" tabindex="-1"
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

</body>
</html>