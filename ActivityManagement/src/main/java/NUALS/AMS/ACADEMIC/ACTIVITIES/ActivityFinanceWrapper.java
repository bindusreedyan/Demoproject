package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.io.Serializable;
import java.util.Date;

public class ActivityFinanceWrapper implements Serializable{
	
	private int activitycode;
    private char externalFunding;
    private char approvedFunding;
    private String annexureFile;
    private char ugcFunded;
    private int UnivBeneficiaries;
    private int localBeneficiaries;
    private int outstnBeneficiaries;
    private int intNatBeneficiaries;
    
    private int UnivResPerson; 
    private int localResPerson;
    private int outstnResPerson;
    private int intNatResPerson;
    
    private double UnivBenTravel;
    private double localBenTravel;
    private double outstnBenTravel;
    private double intNatBenTravel;
    
    private double UnivResTravel;
    private double localResTravel;
    private double outstnResTravel;
    private double intNatResTravel;
    
    
    public double AirTravelResUniv;
    public double AirTravelResLocal;
    public double AirTravelResOutstn;
    public double AirTravelResIntnl;
    
    
    private char HonorMore3000;
    private double totalEstExp;
    
    
    private double totalAdvanceReq;
    private String enteredBy;
    private Date enteredDate;
   
    
    private String approvedBy;
    private Date   approvedDate;
    private double totalAdvApproved;
    private double totalExpApproved;
    private double approvedRemarks;
    private double totalExp;
   
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
