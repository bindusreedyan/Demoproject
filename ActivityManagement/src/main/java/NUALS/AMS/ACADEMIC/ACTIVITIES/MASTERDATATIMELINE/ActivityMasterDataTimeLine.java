package NUALS.AMS.ACADEMIC.ACTIVITIES.MASTERDATATIMELINE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class ActivityMasterDataTimeLine
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int mdtlId;
	private String mdtlProcessName;
	private String mdtlProcessId;
	private String mdtllUserCode;
	private String mdtltlStatus;
	private Date mdtlDate;
	@Column(length=50000)
	private String sfmftlRemarks;
	private String activityLog;
	public int getMdtlId() {
		return mdtlId;
	}
	public void setMdtlId(int mdtlId) {
		this.mdtlId = mdtlId;
	}
	public String getMdtlProcessName() {
		return mdtlProcessName;
	}
	public void setMdtlProcessName(String mdtlProcessName) {
		this.mdtlProcessName = mdtlProcessName;
	}
	public String getMdtllUserCode() {
		return mdtllUserCode;
	}
	public void setMdtllUserCode(String mdtllUserCode) {
		this.mdtllUserCode = mdtllUserCode;
	}
	public String getMdtltlStatus() {
		return mdtltlStatus;
	}
	public void setMdtltlStatus(String mdtltlStatus) {
		this.mdtltlStatus = mdtltlStatus;
	}
	public Date getMdtlDate() {
		return mdtlDate;
	}
	public void setMdtlDate(Date mdtlDate) {
		this.mdtlDate = mdtlDate;
	}
	public String getSfmftlRemarks() {
		return sfmftlRemarks;
	}
	public void setSfmftlRemarks(String sfmftlRemarks) {
		this.sfmftlRemarks = sfmftlRemarks;
	}
	public String getActivityLog() {
		return activityLog;
	}
	public void setActivityLog(String activityLog) {
		this.activityLog = activityLog;
	}
	public String getMdtlProcessId() {
		return mdtlProcessId;
	}
	public void setMdtlProcessId(String mdtlProcessId) {
		this.mdtlProcessId = mdtlProcessId;
	}
	public ActivityMasterDataTimeLine() {
		super();
	}
	
	
	}
