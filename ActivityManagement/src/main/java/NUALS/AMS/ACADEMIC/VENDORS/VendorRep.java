package NUALS.AMS.ACADEMIC.VENDORS;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import NUALS.AMS.ACADEMIC.ACTIVITIES.ExpHeadsMaster;
import NUALS.AMS.ACADEMIC.ADVANCE.AdvanceReqReportClass;

public interface VendorRep extends CrudRepository<VendorMaster, Integer>{
	
	@Query(value="select * from  vendor_master WHERE vendor_status =?1",nativeQuery=true)
    List<VendorMaster> getAllVendorMaster(String status);
	
	@Query(value="select * from  vendor_master WHERE vendor_id =?1",nativeQuery=true)
	VendorMaster getDetailsOfVendorById(int vendorId);
	
	@Query(value="select * from  vendor_master",nativeQuery=true)
    List<VendorMaster> getAllVendorDetailsWithoutStatus();

}
