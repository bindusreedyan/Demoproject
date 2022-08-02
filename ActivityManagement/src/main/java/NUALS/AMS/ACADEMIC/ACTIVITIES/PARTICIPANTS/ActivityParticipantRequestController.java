package NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/academicactivity")
@RestController
public class ActivityParticipantRequestController {
	
	
	@RequestMapping(value="/activityparticipationrequest")
	public ModelAndView activityparticipationrequestview(ModelAndView mv) {	
		System.out.println("entrrr in activityparticipationrequestview");
		mv.setViewName("ACTIVITYPARTICIPATION/ActivityParticipation");
		return mv;
	}
	
	
	@RequestMapping(value="/administrativeApprovalViewPr")
	public ModelAndView administrativeApprovalViewPr(ModelAndView mv) {	
		System.out.println("entrrr in activityparticipationrequestview");
		mv.setViewName("ACTIVITYPARTICIPATION/AdminApprovalPartRequest");
		return mv;
	}
	
	@RequestMapping(value="/financialApprovalViewPr")
	public ModelAndView financialApprovalViewPr(ModelAndView mv) {	
		System.out.println("entrrr in financial approval");
		mv.setViewName("ACTIVITYPARTICIPATION/ParticipateRequestFinanceapproval");
		return mv;
	}
	
	@RequestMapping(value="/finalApprovalViewPr")
	public ModelAndView finalApprovalViewPr(ModelAndView mv) {	
		System.out.println("entrrr in final approval");
		mv.setViewName("ACTIVITYPARTICIPATION/FinalApprovalParticipationRequest");
		return mv;
	}
	

}


///academicactivity/activityparticipationrequest