package NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS;

import java.util.Date;

public class ValidActivityParticipantDetails {
	
	public int activity_code;
	public int getActivity_code() {
		return activity_code;
	}
	public void setActivity_code(int activity_code) {
		this.activity_code = activity_code;
	}
	public Date getTravel_date_onward_start() {
		return travel_date_onward_start;
	}
	public void setTravel_date_onward_start(Date travel_date_onward_start) {
		this.travel_date_onward_start = travel_date_onward_start;
	}
	public Date getTravel_date_onward_return() {
		return travel_date_onward_return;
	}
	public void setTravel_date_onward_return(Date travel_date_onward_return) {
		this.travel_date_onward_return = travel_date_onward_return;
	}
	public String getRoll_no() {
		return roll_no;
	}
	public void setRoll_no(String roll_no) {
		this.roll_no = roll_no;
	}
	public Date travel_date_onward_start;
	public Date travel_date_onward_return;
	public String roll_no;

}
