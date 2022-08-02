package NUALS.AMS.ACADEMIC.ADVANCE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/academicactivity")
@RestController
public class AdvanceRequestFacultyController {
	
	@Autowired
	AdvanceRequestFacultyService aas;

}
