package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.io.Serializable;
import java.util.List;

public class ActivityAdvEstimateWrapper implements Serializable{
	
	int activityId;
	String finyearexphead;
	List<String> exheadarray;
	
	List<Double>expadvreqarray;
	List<String> expadvreqreasonarray;
	List<Double>asGrantedAdvAmntarray;
	public List<Double> getAsGrantedAdvAmntarray() {
		return asGrantedAdvAmntarray;
	}
	public void setAsGrantedAdvAmntarray(List<Double> asGrantedAdvAmntarray) {
		this.asGrantedAdvAmntarray = asGrantedAdvAmntarray;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public String getFinyearexphead() {
		return finyearexphead;
	}
	public void setFinyearexphead(String finyearexphead) {
		this.finyearexphead = finyearexphead;
	}
	public List<String> getExheadarray() {
		return exheadarray;
	}
	public void setExheadarray(List<String> exheadarray) {
		this.exheadarray = exheadarray;
	}
	public List<Double> getExpadvreqarray() {
		return expadvreqarray;
	}
	public void setExpadvreqarray(List<Double> expadvreqarray) {
		this.expadvreqarray = expadvreqarray;
	}
	public List<String> getExpadvreqreasonarray() {
		return expadvreqreasonarray;
	}
	public void setExpadvreqreasonarray(List<String> expadvreqreasonarray) {
		this.expadvreqreasonarray = expadvreqreasonarray;
	}
	
	
	
	

}