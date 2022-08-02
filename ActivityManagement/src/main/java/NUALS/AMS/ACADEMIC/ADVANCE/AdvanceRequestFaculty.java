package NUALS.AMS.ACADEMIC.ADVANCE;

import java.sql.Date;

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

import NUALS.AMS.ACADEMIC.ACTIVITIES.ActivityMaster;
@Entity
@EntityListeners(AuditingEntityListener.class)
public class AdvanceRequestFaculty {

	@Id
	@Column(name = "advReqId", nullable = false, length = 50)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int advReqId;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "activityCode")
	private ActivityMaster ac;

	@Column(nullable = false)
	private double advRequiredFig;

	@Column(nullable = false)
	private String purpose;

	@Column(nullable = false)
	private double amtSanctionedAS;

	@Column(nullable = false)
	private String aSNo;

	// yes or no
	private String finalAdvReq;

	@Column(nullable = true)
	private String designation;

	@Column(nullable = true)
	private String empCode;

	@Column(nullable = true)
	private String enteredBy;
	@Column(nullable = true)
	private Date enteredDate;


	@Column(nullable = true)
	private String adminApprovedBy;

	@Column(nullable = true)
	private Date adminApprovedDate;

	@Column(nullable = true)
	private String adminApprovedRemarks;

	@Column(nullable = true)
	private Date sanctionDate;
	@Column(nullable = true)
	private String sanctionedBy;

	@Column(nullable = true)
	private String sanctionedRemarks;

	// boolean
	@Column(nullable = true)
	private String PendingSettlement;

	@Column(nullable = true)
	private double sanctionedAmt = 0.0;
	@Column(nullable = true)
	private String voucherNo;

	@Column(nullable = true)
	private Date voucherDate;

	@Column(nullable = false)
	private String advanceReqStatus;// Raised/sanctioned/settled/closed

	@Column(nullable = true)
	private String enteredRemarks;// Raised/sanctioned/settled/closed

	@Column(nullable = true)
	private int adv_paid;

}
