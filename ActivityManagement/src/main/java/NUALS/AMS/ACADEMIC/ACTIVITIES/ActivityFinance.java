package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class ActivityFinance {
	  @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int activityFinanceCode;
	
	   
	    @NotNull
		@ManyToOne
		@JoinColumn(name="activityCode",unique=true)
	    private ActivityMaster ac;
	    
        private char externalFunding;
	    private char approvedFunding;
	    private String annexureFile;
	    private char ugcFunded;
	    private int univBeneficiaries;
	    private int localBeneficiaries;
	    private int outstnBeneficiaries;
	    private int intNatBeneficiaries;
	    
	    private int univResPerson; 
	    private int localResPerson;
	    private int outstnResPerson;
	    private int intNatResPerson;
	    
	    private double univBenTravel;
	    private double localBenTravel;
	    private double outstnBenTravel;
	    private double intNatBenTravel;
	    
	    private double univResTravel;
	    private double localResTravel;
	    private double outstnResTravel;
	    private double intNatResTravel;
	    
	    
	    public double airTravelResUniv;
	    public double airTravelResLocal;
	    public double airTravelResOutstn;
	    public double airTravelResIntnl;
	    
	    private char honorMore3000;
	    private double totalEstExp;
	    
	   private double totalAdvanceReq;
	   private String enteredBy;
	   private Date enteredDate;
	   
	   private String approvedBy;
   	   
	   private Date approvedDate;
	    
	   private double totalAdvApproved;
	   
	   private double totalExpApproved;
	   
	   private String approvedRemarks;
	   
	   private String approvesStatus;
	    
	   
	   private double total_estimated=0;
	   
	   private String facultyApprovesStatus;
	   
 private String adminofficeapprovesStatus;
	   
	   private String officeApprovedRemarks;
	   
	   
	   private String financeOfficeapprovesStatus;
	   private String financeOfficeApprovedRemarks;
	   
	   private String recommendedBy;
	   private Date recommendedDate;
	   private String recommendedRemarks;
	   
	   
	   
	   
	   
	   public String getFacultyApprovesStatus() {
		return facultyApprovesStatus;
	}


	public void setFacultyApprovesStatus(String facultyApprovesStatus) {
		this.facultyApprovesStatus = facultyApprovesStatus;
	}


	public String getRecommendedBy() {
		return recommendedBy;
	}


	public void setRecommendedBy(String recommendedBy) {
		this.recommendedBy = recommendedBy;
	}


	public Date getRecommendedDate() {
		return recommendedDate;
	}


	public void setRecommendedDate(Date recommendedDate) {
		this.recommendedDate = recommendedDate;
	}


	public String getRecommendedRemarks() {
		return recommendedRemarks;
	}


	public void setRecommendedRemarks(String recommendedRemarks) {
		this.recommendedRemarks = recommendedRemarks;
	}


	public String getAdminofficeapprovesStatus() {
		return adminofficeapprovesStatus;
	}


	public void setAdminofficeapprovesStatus(String adminofficeapprovesStatus) {
		this.adminofficeapprovesStatus = adminofficeapprovesStatus;
	}


	public String getOfficeApprovedRemarks() {
		return officeApprovedRemarks;
	}


	public void setOfficeApprovedRemarks(String officeApprovedRemarks) {
		this.officeApprovedRemarks = officeApprovedRemarks;
	}


	public String getFinanceOfficeapprovesStatus() {
		return financeOfficeapprovesStatus;
	}


	public void setFinanceOfficeapprovesStatus(String financeOfficeapprovesStatus) {
		this.financeOfficeapprovesStatus = financeOfficeapprovesStatus;
	}


	public String getFinanceOfficeApprovedRemarks() {
		return financeOfficeApprovedRemarks;
	}


	public void setFinanceOfficeApprovedRemarks(String financeOfficeApprovedRemarks) {
		this.financeOfficeApprovedRemarks = financeOfficeApprovedRemarks;
	}

	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	  @Column(columnDefinition = "float default 0.0")
	   private double totalAsgranted=0.0;
	   
	   //private double t
	   
	    public String getApprovesStatus() {
		return approvesStatus;
	}



	public double getTotalAsgranted() {
			return totalAsgranted;
		}



		public void setTotalAsgranted(double totalAsgranted) {
			this.totalAsgranted = totalAsgranted;
		}



	public void setApprovesStatus(String approvesStatus) {
		this.approvesStatus = approvesStatus;
	}

		private double totalExp;
	    public int getActivityFinanceCode() {
			return activityFinanceCode;
		}

		public void setActivityFinanceCode(int activityFinanceCode) {
			this.activityFinanceCode = activityFinanceCode;
		}

		public ActivityMaster getAc() {
			return ac;
		}

		public void setAc(ActivityMaster ac) {
			this.ac = ac;
		}

		public double getTotalExp() {
			return totalExp;
		}

		public void setTotalExp(double totalExp) {
			this.totalExp = totalExp;
		}

		public char getExternalFunding() {
			return externalFunding;
		}

		public void setExternalFunding(char externalFunding) {
			this.externalFunding = externalFunding;
		}

		public char getApprovedFunding() {
			return approvedFunding;
		}

		public void setApprovedFunding(char approvedFunding) {
			this.approvedFunding = approvedFunding;
		}

		public String getAnnexureFile() {
			return annexureFile;
		}

		public void setAnnexureFile(String annexureFile) {
			this.annexureFile = annexureFile;
		}

		public char getUgcFunded() {
			return ugcFunded;
		}

		public void setUgcFunded(char ugcFunded) {
			this.ugcFunded = ugcFunded;
		}

		public int getUnivBeneficiaries() {
			return univBeneficiaries;
		}

		public void setUnivBeneficiaries(int univBeneficiaries) {
			this.univBeneficiaries = univBeneficiaries;
		}

		public int getLocalBeneficiaries() {
			return localBeneficiaries;
		}

		public void setLocalBeneficiaries(int localBeneficiaries) {
			this.localBeneficiaries = localBeneficiaries;
		}

		public int getOutstnBeneficiaries() {
			return outstnBeneficiaries;
		}

		public void setOutstnBeneficiaries(int outstnBeneficiaries) {
			this.outstnBeneficiaries = outstnBeneficiaries;
		}

		public int getIntNatBeneficiaries() {
			return intNatBeneficiaries;
		}

		public void setIntNatBeneficiaries(int intNatBeneficiaries) {
			this.intNatBeneficiaries = intNatBeneficiaries;
		}

		public int getUnivResPerson() {
			return univResPerson;
		}

		public void setUnivResPerson(int univResPerson) {
			this.univResPerson = univResPerson;
		}

		public int getLocalResPerson() {
			return localResPerson;
		}

		public void setLocalResPerson(int localResPerson) {
			this.localResPerson = localResPerson;
		}

		public int getOutstnResPerson() {
			return outstnResPerson;
		}

		public void setOutstnResPerson(int outstnResPerson) {
			this.outstnResPerson = outstnResPerson;
		}

		public int getIntNatResPerson() {
			return intNatResPerson;
		}

		public void setIntNatResPerson(int intNatResPerson) {
			this.intNatResPerson = intNatResPerson;
		}

		public double getUnivBenTravel() {
			return univBenTravel;
		}

		public void setUnivBenTravel(double univBenTravel) {
			this.univBenTravel = univBenTravel;
		}

		public double getLocalBenTravel() {
			return localBenTravel;
		}

		public void setLocalBenTravel(double localBenTravel) {
			this.localBenTravel = localBenTravel;
		}

		public double getOutstnBenTravel() {
			return outstnBenTravel;
		}

		public void setOutstnBenTravel(double outstnBenTravel) {
			this.outstnBenTravel = outstnBenTravel;
		}

		public double getIntNatBenTravel() {
			return intNatBenTravel;
		}

		public void setIntNatBenTravel(double intNatBenTravel) {
			this.intNatBenTravel = intNatBenTravel;
		}

		public double getUnivResTravel() {
			return univResTravel;
		}

		public void setUnivResTravel(double univResTravel) {
			this.univResTravel = univResTravel;
		}

		public double getLocalResTravel() {
			return localResTravel;
		}

		public void setLocalResTravel(double localResTravel) {
			this.localResTravel = localResTravel;
		}

		public double getOutstnResTravel() {
			return outstnResTravel;
		}

		public void setOutstnResTravel(double outstnResTravel) {
			this.outstnResTravel = outstnResTravel;
		}

		public double getIntNatResTravel() {
			return intNatResTravel;
		}

		public void setIntNatResTravel(double intNatResTravel) {
			this.intNatResTravel = intNatResTravel;
		}

		public double getAirTravelResUniv() {
			return airTravelResUniv;
		}

		public void setAirTravelResUniv(double airTravelResUniv) {
			this.airTravelResUniv = airTravelResUniv;
		}

		public double getAirTravelResLocal() {
			return airTravelResLocal;
		}

		public void setAirTravelResLocal(double airTravelResLocal) {
			this.airTravelResLocal = airTravelResLocal;
		}

		public double getAirTravelResOutstn() {
			return airTravelResOutstn;
		}

		public void setAirTravelResOutstn(double airTravelResOutstn) {
			this.airTravelResOutstn = airTravelResOutstn;
		}

		public double getAirTravelResIntnl() {
			return airTravelResIntnl;
		}

		public void setAirTravelResIntnl(double airTravelResIntnl) {
			this.airTravelResIntnl = airTravelResIntnl;
		}

		public char getHonorMore3000() {
			return honorMore3000;
		}

		public void setHonorMore3000(char honorMore3000) {
			this.honorMore3000 = honorMore3000;
		}

		public double getTotalEstExp() {
			return totalEstExp;
		}

		public void setTotalEstExp(double totalEstExp) {
			this.totalEstExp = totalEstExp;
		}

		public double getTotalAdvanceReq() {
			return totalAdvanceReq;
		}

		public void setTotalAdvanceReq(double totalAdvanceReq) {
			this.totalAdvanceReq = totalAdvanceReq;
		}

		public String getEnteredBy() {
			return enteredBy;
		}

		public void setEnteredBy(String enteredBy) {
			this.enteredBy = enteredBy;
		}

		public Date getEnteredDate() {
			return enteredDate;
		}

		public void setEnteredDate(Date enteredDate) {
			this.enteredDate = enteredDate;
		}

		public String getApprovedBy() {
			return approvedBy;
		}

		public void setApprovedBy(String approvedBy) {
			this.approvedBy = approvedBy;
		}

		public Date getApprovedDate() {
			return approvedDate;
		}

		public void setApprovedDate(Date approvedDate) {
			this.approvedDate = approvedDate;
		}

		public double getTotalAdvApproved() {
			return totalAdvApproved;
		}

		public void setTotalAdvApproved(double totalAdvApproved) {
			this.totalAdvApproved = totalAdvApproved;
		}

		public double getTotalExpApproved() {
			return totalExpApproved;
		}

		public void setTotalExpApproved(double totalExpApproved) {
			this.totalExpApproved = totalExpApproved;
		}

		public String getApprovedRemarks() {
			return approvedRemarks;
		}

		public void setApprovedRemarks(String approvedRemarks) {
			this.approvedRemarks = approvedRemarks;
		}

		public double getTotal_estimated() {
			return total_estimated;
		}

		public void setTotal_estimated(double total_estimated) {
			this.total_estimated = total_estimated;
		}


		@Override
		public String toString() {
			return "ActivityFinance [activityFinanceCode=" + activityFinanceCode + ", ac=" + ac + ", externalFunding="
					+ externalFunding + ", approvedFunding=" + approvedFunding + ", annexureFile=" + annexureFile
					+ ", ugcFunded=" + ugcFunded + ", univBeneficiaries=" + univBeneficiaries + ", localBeneficiaries="
					+ localBeneficiaries + ", outstnBeneficiaries=" + outstnBeneficiaries + ", intNatBeneficiaries="
					+ intNatBeneficiaries + ", univResPerson=" + univResPerson + ", localResPerson=" + localResPerson
					+ ", outstnResPerson=" + outstnResPerson + ", intNatResPerson=" + intNatResPerson
					+ ", univBenTravel=" + univBenTravel + ", localBenTravel=" + localBenTravel + ", outstnBenTravel="
					+ outstnBenTravel + ", intNatBenTravel=" + intNatBenTravel + ", univResTravel=" + univResTravel
					+ ", localResTravel=" + localResTravel + ", outstnResTravel=" + outstnResTravel
					+ ", intNatResTravel=" + intNatResTravel + ", airTravelResUniv=" + airTravelResUniv
					+ ", airTravelResLocal=" + airTravelResLocal + ", airTravelResOutstn=" + airTravelResOutstn
					+ ", airTravelResIntnl=" + airTravelResIntnl + ", honorMore3000=" + honorMore3000 + ", totalEstExp="
					+ totalEstExp + ", totalAdvanceReq=" + totalAdvanceReq + ", enteredBy=" + enteredBy
					+ ", enteredDate=" + enteredDate + ", approvedBy=" + approvedBy + ", approvedDate=" + approvedDate
					+ ", totalAdvApproved=" + totalAdvApproved + ", totalExpApproved=" + totalExpApproved
					+ ", approvedRemarks=" + approvedRemarks + ", approvesStatus=" + approvesStatus
					+ ", total_estimated=" + total_estimated + ", facultyApprovesStatus=" + facultyApprovesStatus
					+ ", adminofficeapprovesStatus=" + adminofficeapprovesStatus + ", officeApprovedRemarks="
					+ officeApprovedRemarks + ", financeOfficeapprovesStatus=" + financeOfficeapprovesStatus
					+ ", financeOfficeApprovedRemarks=" + financeOfficeApprovedRemarks + ", recommendedBy="
					+ recommendedBy + ", recommendedDate=" + recommendedDate + ", recommendedRemarks="
					+ recommendedRemarks + ", totalAsgranted=" + totalAsgranted + ", totalExp=" + totalExp + "]";
		}

		

		
	
	    
}
