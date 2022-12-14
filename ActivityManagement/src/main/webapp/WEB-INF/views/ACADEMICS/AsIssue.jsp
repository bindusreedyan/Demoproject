<script>

function getAllAdvRequestInformation(advReqId)
{
//change the url for your call
$.ajax(
{
"url" : "/academicactivity/getAllAdvRequestInformationByRequestId",
"method" : "GET",
"data" : {"advReqId":advReqId},
success : function(data)
{
//code for filling data
}
});
}
$(document).ready(function()
{
getAllAdvRequestInformation(5018);
$('#printRep').unbind().bind('click',function()
{
printReport2("reportDiv");
});
})
</script>
<div class="row">
<div class="col-md-12">
<div class="table-responsive" id="reportDiv">
<table class="table" id="gridTable">
<tr><td colspan="2" style="text-align:center"><b>(Abstract)</b></td></tr>
<tr><td colspan="2">Conduct of a Three day International Seminar on Changing Contours of Economic Reforms - Impact on Emerging Economies on October 16th , 2019 to October18th,  2019  with an anticipatory expenditure of Rs. 5,99,384/-.and release of  an advance of Rs. 2,70,000/-  conducted by the CPSLR  -- Sanctioned -- Orders issued.</td></tr>
<tr><td colspan="2" style="text-align:center"><b>Academic (B) Section</b></td></tr>
<tr><td>No. NUALS/67/CPSLR/2019 </td><td>Dated: 23/09/2019</td></tr>
<tr><td colspan="2" style="text-align:center"><u><b>ORDER</b></u></td></tr>
<tr><td colspan="2"><b>Read:-  Proposal dtd 17-09-2019 submitted by Dr. Anil R Nair</b></td></tr>
<tr><td colspan="2">Sanction has been accorded for  conducting a Three day International Seminar on Changing Contours of Economic Reforms - Impact on Emerging Economies on October 16th 2019 to October18th2019  with an anticipatory expenditure of Rs. 5,99,384/-. (Rupees five lakh ninety nine thousand three hundred and eighty four only)and to release an advance of Rs. 2,70,000/- (Rupees two lakh seventy thousand only) conducted by the CPSLR. The anticipatory expenditure as follows:</td></tr>
<tr>
<td colspan="2">
<table class="table" id="gridTable2">
<tr><td>Remove this row and</td><td>fill your data in table</td></tr>
<tr><td>Remove this row and</td><td>fill your data in table</td></tr>
<tr><td>Remove this row and</td><td>fill your data in table</td></tr>
</table>
</td>
</tr>
<tr><td colspan="2">This administrative approval is subjected to compliance of the relevant Government orders, University regulations, Circulars, SOP's and other advisories issued in this regard</td></tr>
<tr><td colspan="2">The expenditure of Rs. 5,84,315/- may be met form the Head of VII - Plan Fund Expenditure (02) Academic programmes and other activities for the Financial Year 2019-20</td></tr>
<tr><td>Copy to</td><td>By order of the Vice-Chancellor</td></tr>
<tr><td rowspan="10">Dr Anil</td><td>Registrar</td></tr>
</table>
</div>
</div>
</div>
<div class="row">
<div class="col-md-4"></div>
<div class="col-md-4"><button class="btn btn-success form-control" id="printRep">Print</button></div>
<div class="col-md-4"></div>
</div>
