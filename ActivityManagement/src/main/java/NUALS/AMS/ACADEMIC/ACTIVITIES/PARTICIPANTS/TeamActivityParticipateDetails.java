package NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS;

import java.sql.Date;

public interface TeamActivityParticipateDetails {
	
	 int getActivity_code();
	 Date getTravel_date_onward_start();
	 Date getTravel_date_return_end();
	 String getRoll_no();
	// String getParticipation_request_status();
	

}
