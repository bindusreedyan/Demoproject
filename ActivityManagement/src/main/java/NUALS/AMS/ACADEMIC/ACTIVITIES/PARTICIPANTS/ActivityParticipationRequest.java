package NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import NUALS.AMS.ACADEMIC.ACTIVITIES.ActivityCenterKey;
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ActivityParticipationRequest {
	
	@EmbeddedId
	private ActivityParticipationRequestKey activityParticptnRequest;
	
	private Date travelDateOnwardStart;
	private Date travelDateOnwardReturn;
	private Date travelDateReturnStart;
	private Date travelDateReturnEnd;
	
	
	@Column(name = "hostingInstitution", nullable = true, length=50)
	private String hostingInstitution;
	
	@Column(name = "hostingCountry", nullable = true, length=50)
	private String hostingCountry;
	private String competionType;
	private String currencyNonINR;
	private double conversionRate;
	private double estVisaCharge;
	private double estLocalConv;
	private double estLoadging;
	
	private String teamParticipation;
	private int teamSize;
	
	private double regFee;
	private double regFeeAdmitted;
	private String regFeeByNuals;
	
	private int printoutsMemPages;
	private double printoutMemAmtEst;
	private double printoutMemAdmitted;
	
	
	private int photocopyMemPages;
	private double photocopyMemAmtEst;
	private double photocopyMemAdmitted;
	
	
	private int paperNoMem;
	private double costOfPaperMem;
	private double costPaperAdmitted;

	private int printoutsComPages;
	private double printoutComAmtEst;
	private double printoutComAdmitted;
	
	
	
	private int photocopyComPages;
	private double photocopyComAmtEst;
	private double photocopyComAdmitted;
	
	private double bindingComAmtEst;
	private double bindingComAdmitted;
	
	
	private int paperNoCom;
	private double costOfPaperCom;
	private double costPaperComAdmitted;
	
	
	private double courierCharges;
	private double courierChargesAdmitted;
	
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
	//bank details
	
	private String modeOfPayment;
	private String AuthRecName;
	private String bankName;
	private String accNo;
	private String ifsc;
	private String branch;
	private String submittedBy;
	private Date submittedDate;
	private String recommendedBy;
	private String recomRemarks;
	private Date recomDate;
	
	

}
