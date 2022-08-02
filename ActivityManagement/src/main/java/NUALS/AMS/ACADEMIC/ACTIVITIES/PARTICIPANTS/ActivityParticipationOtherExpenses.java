package NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ActivityParticipationOtherExpenses {
	@Id	
    private int participationRequestId;
	//other details fields
	private int noMemorialsPetitioner;
	private int noMemorialsRespondent;
	
	
	private int pagesPetitioner;
	private int pagesRespondent;
	
	private int compendiums;
	private int pagesCompendium;
	
	
	private String priorAdministrativeSanction;
	private String receivedRegFee;
	private String compendiumLibrary;
	private String compendiumReceipt;
	private String reasonOnNoSubmission;
	
	//payment fields
	private String modeOfPayment;
	private String authRecName;
	private String bankName;
	private String accNo;
	private String ifsc;
	private String branch;
	
	
	private String regFeeByNuals;//whether regfee by Nuals or Not
	
	private double regFeeSpent;
	private double regFeeEstimExp;
	private double regFeeAdmitted;
	@Column(nullable=true)
	private double regFeeClaimed;
	public double getRegFeeClaimed() {
		return regFeeClaimed;
	}
	public void setRegFeeClaimed(double regFeeClaimed) {
		this.regFeeClaimed = regFeeClaimed;
	}
	private String regFeeBillNo;
	
	
	private int printoutsMemPages;
	private double printoutMemAmtEst;
	private double printoutMemAdmitted;
	private double printoutMemSpent;
	
	private double printoutMemClaimed;
	public String getRegFeeBillNo() {
		return regFeeBillNo;
	}
	public void setRegFeeBillNo(String regFeeBillNo) {
		this.regFeeBillNo = regFeeBillNo;
	}
	public double getPrintoutMemClaimed() {
		return printoutMemClaimed;
	}
	public void setPrintoutMemClaimed(double printoutMemClaimed) {
		this.printoutMemClaimed = printoutMemClaimed;
	}
	public String getPrintoutMemBillNo() {
		return printoutMemBillNo;
	}
	public void setPrintoutMemBillNo(String printoutMemBillNo) {
		this.printoutMemBillNo = printoutMemBillNo;
	}
	public double getPhotocopyMemClaimed() {
		return photocopyMemClaimed;
	}
	public void setPhotocopyMemClaimed(double photocopyMemClaimed) {
		this.photocopyMemClaimed = photocopyMemClaimed;
	}
	public String getPhotocopyMemBillNo() {
		return photocopyMemBillNo;
	}
	public void setPhotocopyMemBillNo(String photocopyMemBillNo) {
		this.photocopyMemBillNo = photocopyMemBillNo;
	}
	public double getBindingAmntClaimed() {
		return bindingAmntClaimed;
	}
	public void setBindingAmntClaimed(double bindingAmntClaimed) {
		this.bindingAmntClaimed = bindingAmntClaimed;
	}
	public String getBindingAmntBillNo() {
		return bindingAmntBillNo;
	}
	public void setBindingAmntBillNo(String bindingAmntBillNo) {
		this.bindingAmntBillNo = bindingAmntBillNo;
	}
	public double getCostMemClaimed() {
		return costMemClaimed;
	}
	public void setCostMemClaimed(double costMemClaimed) {
		this.costMemClaimed = costMemClaimed;
	}
	public String getCostMemBillNo() {
		return costMemBillNo;
	}
	public void setCostMemBillNo(String costMemBillNo) {
		this.costMemBillNo = costMemBillNo;
	}
	public double getPrintoutComClaimed() {
		return printoutComClaimed;
	}
	public void setPrintoutComClaimed(double printoutComClaimed) {
		this.printoutComClaimed = printoutComClaimed;
	}
	public String getPrintoutComBillNo() {
		return printoutComBillNo;
	}
	public void setPrintoutComBillNo(String printoutComBillNo) {
		this.printoutComBillNo = printoutComBillNo;
	}
	public double getPhotocopyComClaimed() {
		return photocopyComClaimed;
	}
	public void setPhotocopyComClaimed(double photocopyComClaimed) {
		this.photocopyComClaimed = photocopyComClaimed;
	}
	public String getPhotocopyComBillNo() {
		return photocopyComBillNo;
	}
	public void setPhotocopyComBillNo(String photocopyComBillNo) {
		this.photocopyComBillNo = photocopyComBillNo;
	}
	public double getBindingComClaimed() {
		return bindingComClaimed;
	}
	public void setBindingComClaimed(double bindingComClaimed) {
		this.bindingComClaimed = bindingComClaimed;
	}
	public String getBindingComBillNo() {
		return bindingComBillNo;
	}
	public void setBindingComBillNo(String bindingComBillNo) {
		this.bindingComBillNo = bindingComBillNo;
	}
	public double getCostPaperComClaimed() {
		return costPaperComClaimed;
	}
	public void setCostPaperComClaimed(double costPaperComClaimed) {
		this.costPaperComClaimed = costPaperComClaimed;
	}
	public String getCostPaperComBillNo() {
		return costPaperComBillNo;
	}
	public void setCostPaperComBillNo(String costPaperComBillNo) {
		this.costPaperComBillNo = costPaperComBillNo;
	}
	public double getCourierChargesAmntClaimed() {
		return courierChargesAmntClaimed;
	}
	public void setCourierChargesAmntClaimed(double courierChargesAmntClaimed) {
		this.courierChargesAmntClaimed = courierChargesAmntClaimed;
	}
	public String getCourierChargesBillNo() {
		return courierChargesBillNo;
	}
	public void setCourierChargesBillNo(String courierChargesBillNo) {
		this.courierChargesBillNo = courierChargesBillNo;
	}
	private String printoutMemBillNo;
	
	
	private int photocopyMemPages;
	private double photocopyMemAmtEst;
	private double photocopyMemAdmitted;
	private double photocopyMemSpent;

	private double photocopyMemClaimed;
	private String photocopyMemBillNo;
	
	private double bindingMemAmtEst;
	private double bindingAmntAdmit;
	private double bindingAmntSpent;
	
	private double bindingAmntClaimed;
	private String bindingAmntBillNo;
	
	private int costMemPages;
	private double costMemAmtEst;
	private double costemAdmitted;
	private double costMemSpent;

	private double costMemClaimed;
	private String costMemBillNo;
	
	private int printoutsComPages;
	private double printoutComAmtEst;
	private double printoutComAdmitted;
	private double printoutComSpent;

	private double printoutComClaimed;
	private String printoutComBillNo;
	
	
	private int photocopyComPages;
	private double photocopyComAmtEst;
	private double photocopyComAdmitted;
	private double photocopyComSpent;

	private double photocopyComClaimed;
	private String photocopyComBillNo;
	
	
	
	
	private double bindingComAmtEst;
	private double bindingComAdmitted;
	private double bindingComSpent;
	
	private double bindingComClaimed;
	private String bindingComBillNo;
	
	
	private int paperNoCom;
	private double costOfPaperComEstmExp;
	private double costPaperComAdmitted;
	private double costPaperComSpent;

	private double costPaperComClaimed;
    private String 	costPaperComBillNo;
 
	
	private double courierChargesEstmExp;
	private double courierChargesAdmitted;
	private double courierChargesAmntSpent;
	
	private double courierChargesAmntClaimed;
	private String courierChargesBillNo;
	
	private double otherExpenseTotal;
	@Column(nullable = true)
	private double totalExpenseProgram;
	
	
	
	  @Column(nullable = true)
	  private double totalAdmitAmount;
	  
	  
	  @Column(name="totalClaimedAmount",columnDefinition = "float default 0")
	  private double totalClaimedAmount;
	  
	
	public double getTotalClaimedAmount() {
		return totalClaimedAmount;
	}
	public void setTotalClaimedAmount(double totalClaimedAmount) {
		this.totalClaimedAmount = totalClaimedAmount;
	}
	public int getParticipationRequestId() {
		return participationRequestId;
	}
	public void setParticipationRequestId(int participationRequestId) {
		this.participationRequestId = participationRequestId;
	}
	public String getRegFeeByNuals() {
		return regFeeByNuals;
	}
	public void setRegFeeByNuals(String regFeeByNuals) {
		this.regFeeByNuals = regFeeByNuals;
	}
	public double getRegFeeSpent() {
		return regFeeSpent;
	}
	public void setRegFeeSpent(double regFeeSpent) {
		this.regFeeSpent = regFeeSpent;
	}
	public double getRegFeeEstimExp() {
		return regFeeEstimExp;
	}
	public void setRegFeeEstimExp(double regFeeEstimExp) {
		this.regFeeEstimExp = regFeeEstimExp;
	}
	public double getRegFeeAdmitted() {
		return regFeeAdmitted;
	}
	public void setRegFeeAdmitted(double regFeeAdmitted) {
		this.regFeeAdmitted = regFeeAdmitted;
	}
	public int getPrintoutsMemPages() {
		return printoutsMemPages;
	}
	public void setPrintoutsMemPages(int printoutsMemPages) {
		this.printoutsMemPages = printoutsMemPages;
	}
	public double getPrintoutMemAmtEst() {
		return printoutMemAmtEst;
	}
	public void setPrintoutMemAmtEst(double printoutMemAmtEst) {
		this.printoutMemAmtEst = printoutMemAmtEst;
	}
	public double getPrintoutMemAdmitted() {
		return printoutMemAdmitted;
	}
	public void setPrintoutMemAdmitted(double printoutMemAdmitted) {
		this.printoutMemAdmitted = printoutMemAdmitted;
	}
	public double getPrintoutMemSpent() {
		return printoutMemSpent;
	}
	public void setPrintoutMemSpent(double printoutMemSpent) {
		this.printoutMemSpent = printoutMemSpent;
	}
	public int getPhotocopyMemPages() {
		return photocopyMemPages;
	}
	public void setPhotocopyMemPages(int photocopyMemPages) {
		this.photocopyMemPages = photocopyMemPages;
	}
	public double getPhotocopyMemAmtEst() {
		return photocopyMemAmtEst;
	}
	public void setPhotocopyMemAmtEst(double photocopyMemAmtEst) {
		this.photocopyMemAmtEst = photocopyMemAmtEst;
	}
	public double getPhotocopyMemAdmitted() {
		return photocopyMemAdmitted;
	}
	public void setPhotocopyMemAdmitted(double photocopyMemAdmitted) {
		this.photocopyMemAdmitted = photocopyMemAdmitted;
	}
	public double getPhotocopyMemSpent() {
		return photocopyMemSpent;
	}
	public void setPhotocopyMemSpent(double photocopyMemSpent) {
		this.photocopyMemSpent = photocopyMemSpent;
	}
	public double getBindingMemAmtEst() {
		return bindingMemAmtEst;
	}
	public void setBindingMemAmtEst(double bindingMemAmtEst) {
		this.bindingMemAmtEst = bindingMemAmtEst;
	}
	public double getBindingAmntAdmit() {
		return bindingAmntAdmit;
	}
	public void setBindingAmntAdmit(double bindingAmntAdmit) {
		this.bindingAmntAdmit = bindingAmntAdmit;
	}
	public double getBindingAmntSpent() {
		return bindingAmntSpent;
	}
	public void setBindingAmntSpent(double bindingAmntSpent) {
		this.bindingAmntSpent = bindingAmntSpent;
	}
	public int getCostMemPages() {
		return costMemPages;
	}
	public void setCostMemPages(int costMemPages) {
		this.costMemPages = costMemPages;
	}
	public double getCostMemAmtEst() {
		return costMemAmtEst;
	}
	public void setCostMemAmtEst(double costMemAmtEst) {
		this.costMemAmtEst = costMemAmtEst;
	}
	public double getCostemAdmitted() {
		return costemAdmitted;
	}
	public void setCostemAdmitted(double costemAdmitted) {
		this.costemAdmitted = costemAdmitted;
	}
	public double getCostMemSpent() {
		return costMemSpent;
	}
	public void setCostMemSpent(double costMemSpent) {
		this.costMemSpent = costMemSpent;
	}
	public int getPrintoutsComPages() {
		return printoutsComPages;
	}
	public void setPrintoutsComPages(int printoutsComPages) {
		this.printoutsComPages = printoutsComPages;
	}
	public double getPrintoutComAmtEst() {
		return printoutComAmtEst;
	}
	public void setPrintoutComAmtEst(double printoutComAmtEst) {
		this.printoutComAmtEst = printoutComAmtEst;
	}
	public double getPrintoutComAdmitted() {
		return printoutComAdmitted;
	}
	public void setPrintoutComAdmitted(double printoutComAdmitted) {
		this.printoutComAdmitted = printoutComAdmitted;
	}
	public double getPrintoutComSpent() {
		return printoutComSpent;
	}
	public void setPrintoutComSpent(double printoutComSpent) {
		this.printoutComSpent = printoutComSpent;
	}
	public int getPhotocopyComPages() {
		return photocopyComPages;
	}
	public void setPhotocopyComPages(int photocopyComPages) {
		this.photocopyComPages = photocopyComPages;
	}
	public double getPhotocopyComAmtEst() {
		return photocopyComAmtEst;
	}
	public void setPhotocopyComAmtEst(double photocopyComAmtEst) {
		this.photocopyComAmtEst = photocopyComAmtEst;
	}
	public double getPhotocopyComAdmitted() {
		return photocopyComAdmitted;
	}
	public void setPhotocopyComAdmitted(double photocopyComAdmitted) {
		this.photocopyComAdmitted = photocopyComAdmitted;
	}
	public double getPhotocopyComSpent() {
		return photocopyComSpent;
	}
	public void setPhotocopyComSpent(double photocopyComSpent) {
		this.photocopyComSpent = photocopyComSpent;
	}
	public double getBindingComAmtEst() {
		return bindingComAmtEst;
	}
	public void setBindingComAmtEst(double bindingComAmtEst) {
		this.bindingComAmtEst = bindingComAmtEst;
	}
	public double getBindingComAdmitted() {
		return bindingComAdmitted;
	}
	public void setBindingComAdmitted(double bindingComAdmitted) {
		this.bindingComAdmitted = bindingComAdmitted;
	}
	public double getBindingComSpent() {
		return bindingComSpent;
	}
	public void setBindingComSpent(double bindingComSpent) {
		this.bindingComSpent = bindingComSpent;
	}
	public int getPaperNoCom() {
		return paperNoCom;
	}
	public void setPaperNoCom(int paperNoCom) {
		this.paperNoCom = paperNoCom;
	}
	public double getCostOfPaperComEstmExp() {
		return costOfPaperComEstmExp;
	}
	public void setCostOfPaperComEstmExp(double costOfPaperComEstmExp) {
		this.costOfPaperComEstmExp = costOfPaperComEstmExp;
	}
	public double getCostPaperComAdmitted() {
		return costPaperComAdmitted;
	}
	public void setCostPaperComAdmitted(double costPaperComAdmitted) {
		this.costPaperComAdmitted = costPaperComAdmitted;
	}
	public double getCostPaperComSpent() {
		return costPaperComSpent;
	}
	public void setCostPaperComSpent(double costPaperComSpent) {
		this.costPaperComSpent = costPaperComSpent;
	}
	public double getCourierChargesEstmExp() {
		return courierChargesEstmExp;
	}
	public void setCourierChargesEstmExp(double courierChargesEstmExp) {
		this.courierChargesEstmExp = courierChargesEstmExp;
	}
	public double getCourierChargesAdmitted() {
		return courierChargesAdmitted;
	}
	public void setCourierChargesAdmitted(double courierChargesAdmitted) {
		this.courierChargesAdmitted = courierChargesAdmitted;
	}
	public double getCourierChargesAmntSpent() {
		return courierChargesAmntSpent;
	}
	public void setCourierChargesAmntSpent(double courierChargesAmntSpent) {
		this.courierChargesAmntSpent = courierChargesAmntSpent;
	}
	public int getNoMemorialsPetitioner() {
		return noMemorialsPetitioner;
	}
	public void setNoMemorialsPetitioner(int noMemorialsPetitioner) {
		this.noMemorialsPetitioner = noMemorialsPetitioner;
	}
	public int getNoMemorialsRespondent() {
		return noMemorialsRespondent;
	}
	public void setNoMemorialsRespondent(int noMemorialsRespondent) {
		this.noMemorialsRespondent = noMemorialsRespondent;
	}
	public int getPagesPetitioner() {
		return pagesPetitioner;
	}
	public void setPagesPetitioner(int pagesPetitioner) {
		this.pagesPetitioner = pagesPetitioner;
	}
	public int getPagesRespondent() {
		return pagesRespondent;
	}
	public void setPagesRespondent(int pagesRespondent) {
		this.pagesRespondent = pagesRespondent;
	}
	public int getCompendiums() {
		return compendiums;
	}
	public void setCompendiums(int compendiums) {
		this.compendiums = compendiums;
	}
	public int getPagesCompendium() {
		return pagesCompendium;
	}
	public void setPagesCompendium(int pagesCompendium) {
		this.pagesCompendium = pagesCompendium;
	}
	public String getPriorAdministrativeSanction() {
		return priorAdministrativeSanction;
	}
	public void setPriorAdministrativeSanction(String priorAdministrativeSanction) {
		this.priorAdministrativeSanction = priorAdministrativeSanction;
	}
	public String getReceivedRegFee() {
		return receivedRegFee;
	}
	public void setReceivedRegFee(String receivedRegFee) {
		this.receivedRegFee = receivedRegFee;
	}
	public String getCompendiumLibrary() {
		return compendiumLibrary;
	}
	public void setCompendiumLibrary(String compendiumLibrary) {
		this.compendiumLibrary = compendiumLibrary;
	}
	public String getCompendiumReceipt() {
		return compendiumReceipt;
	}
	public void setCompendiumReceipt(String compendiumReceipt) {
		this.compendiumReceipt = compendiumReceipt;
	}
	public String getReasonOnNoSubmission() {
		return reasonOnNoSubmission;
	}
	public void setReasonOnNoSubmission(String reasonOnNoSubmission) {
		this.reasonOnNoSubmission = reasonOnNoSubmission;
	}
	public String getModeOfPayment() {
		return modeOfPayment;
	}
	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}
	public String getAuthRecName() {
		return this.authRecName;
	}
	public void setAuthRecName(String authRecName) {
		this.authRecName = authRecName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	
	public double getOtherExpenseTotal() {
		return otherExpenseTotal;
	}
	public void setOtherExpenseTotal(double otherExpenseTotal) {
		this.otherExpenseTotal = otherExpenseTotal;
	}
	public double getTotalExpenseProgram() {
		return totalExpenseProgram;
	}
	public void setTotalExpenseProgram(double totalExpenseProgram) {
		this.totalExpenseProgram = totalExpenseProgram;
	}
	public double getTotalAdmitAmount() {
		return totalAdmitAmount;
	}
	public void setTotalAdmitAmount(double totalAdmitAmount) {
		this.totalAdmitAmount = totalAdmitAmount;
	}

	
	
	
	
	
	
	
	

}
