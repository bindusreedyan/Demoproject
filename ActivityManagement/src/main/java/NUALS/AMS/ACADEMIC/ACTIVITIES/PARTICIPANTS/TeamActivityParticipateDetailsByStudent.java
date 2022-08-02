package NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS;
import java.sql.Date;

public interface TeamActivityParticipateDetailsByStudent {
	 int getActivity_code();
	 String getTitle();
	 Date getOnward_start_date();
	 Date getReturn_end_date();
	 String getRoll_no();
	 
	 String getParticipation_request_status();
}
