package NUALS.AMS.ACADEMIC.VENDORS;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import NUALS.AMS.ACADEMIC.ACTIVITIES.ExpHeadsMaster;

@Service
public class VendorService {
	
	
	@Autowired 
	VendorRep vr;
	public String addVendorDetails(VendorMaster vm,String userCode)
	{
		VendorMaster af=null;
		String msg=null;
		try
		{
			vm.setEnteredBy(userCode);
			vm.setEnteredDate(new java.util.Date());
			vm.setVendorStatus("submitted");
			af=vr.save(vm);
			if(af!=null)
			{
				msg="Vendor Details are Successfully added with vendorId"+af.getVendorId();
			}
			else
			{
				msg="Error is occured while adding details";
			}
		}
		catch(Exception e)
		{
			msg="Error is occured while adding details";
		}
		return msg;
	}
	
	
	public String editVendorDetails(VendorMaster vm,String userCode)
	{
		VendorMaster af=null;
		String msg=null;
		try
		{
			//vm.setEnteredBy(userCode);
		//	vm.setEnteredDate(new java.util.Date());
		//	vm.setVendorStatus("submitted");
			  af=vr.findById(vm.getVendorId()).orElse(null);
			  af.setBusinessName(vm.getBusinessName());
			  af.setPanNo(vm.getPanNo());
			  af.setConstitution(vm.getConstitution());
			  af.setGstNo(vm.getGstNo());
			  af.setAddress(vm.getAddress());
			  af.setAccountNo(vm.getAccountNo());
			  af.setBankName(vm.getBankName());
			  af.setPinCode(vm.getPinCode());
			  af.setBranch(vm.getBranch());
			  af.setContactPhone(vm.getContactPhone());
			  af.setAccountType(vm.getAccountType());
			  af.setEmail(af.getEmail());
			  af.setIfsc(af.getIfsc());
			  af.setContactPerson(af.getContactPerson());
			  af.setEnteredRamarks(af.getEnteredRamarks());
			  VendorMaster af1=vr.save(af);
			if(af1!=null)
			{
				msg="Vendor Details are Successfully edited with vendorId"+af1.getVendorId();
			}
			else
			{
				msg="Error is occured while editing details";
			}
		}
		catch(Exception e)
		{
			msg="Error is occured while adding details";
		}
		return msg;
	}
	
	
	public String checkVendorDetails(VendorMaster vm,String userCode)
	{
		VendorMaster af=null;
		String msg=null;
		try
		{
			//vm.setEnteredBy(userCode);
		//	vm.setEnteredDate(new java.util.Date());
		//	vm.setVendorStatus("submitted");
			  af=vr.findById(vm.getVendorId()).orElse(null);
			  af.setBusinessName(vm.getBusinessName());
			  af.setPanNo(vm.getPanNo());
			  af.setConstitution(vm.getConstitution());
			  af.setGstNo(vm.getGstNo());
			  af.setAddress(vm.getAddress());
			  af.setAccountNo(vm.getAccountNo());
			  af.setBankName(vm.getBankName());
			  af.setPinCode(vm.getPinCode());
			  af.setBranch(vm.getBranch());
			  af.setContactPhone(vm.getContactPhone());
			  af.setAccountType(vm.getAccountType());
			  af.setEmail(vm.getEmail());
			  af.setIfsc(vm.getIfsc());
			  af.setContactPerson(vm.getContactPerson());
			  af.setEnteredRamarks(vm.getEnteredRamarks());
			  af.setVendorCheckedBy(userCode);
			  af.setVendorCheckedRemarks(vm.getVendorCheckedRemarks());
			  af.setVendorCheckedDate(new Date());
			  af.setVendorStatus("checked");
			  VendorMaster af1=vr.save(af);
			if(af1!=null)
			{
				msg="Vendor Details are Successfully checked"+af1.getVendorId();
			}
			else
			{
				msg="Error is occured while checking details";
			}
		}
		catch(Exception e)
		{
			msg="Error is occured while adding details";
		}
		return msg;
	}
	
	public String verifyVendorDetails(VendorMaster vm,String userCode)
	{
		VendorMaster af=null;
		String msg=null;
		try
		{
			//vm.setEnteredBy(userCode);
		//	vm.setEnteredDate(new java.util.Date());
		//	vm.setVendorStatus("submitted");
			  af=vr.findById(vm.getVendorId()).orElse(null);
			  af.setBusinessName(vm.getBusinessName());
			  af.setPanNo(vm.getPanNo());
			  af.setConstitution(vm.getConstitution());
			  af.setGstNo(vm.getGstNo());
			  af.setAddress(vm.getAddress());
			  af.setAccountNo(vm.getAccountNo());
			  af.setBankName(vm.getBankName());
			  af.setPinCode(vm.getPinCode());
			  af.setBranch(vm.getBranch());
			  af.setContactPhone(vm.getContactPhone());
			  af.setAccountType(vm.getAccountType());
			  af.setEmail(vm.getEmail());
			  af.setIfsc(vm.getIfsc());
			  af.setContactPerson(vm.getContactPerson());
			  af.setEnteredRamarks(af.getEnteredRamarks());
			  af.setVendorVerifiedBy(userCode);
			  af.setVendorVerifiedRemarks(vm.getVendorVerifiedRemarks());
			  af.setVendorVerifiedDate(new Date());
			  af.setVendorStatus("verified");
			  VendorMaster af1=vr.save(af);
			if(af1!=null)
			{
				msg="Vendor Details are Successfully verified"+af1.getVendorId();
			}
			else
			{
				msg="Error is occured while verifying details";
			}
		}
		catch(Exception e)
		{
			msg="Error is occured while adding details";
		}
		return msg;
	}
	public String approveVendorDetails(VendorMaster vm,String userCode)
	{
		VendorMaster af=null;
		String msg=null;
		try
		{
			//vm.setEnteredBy(userCode);
		//	vm.setEnteredDate(new java.util.Date());
		//	vm.setVendorStatus("submitted");
			/*  af=vr.findById(vm.getVendorId()).orElse(null);
			  af.setBusinessName(vm.getBusinessName());
			  af.setPanNo(vm.getPanNo());
			  af.setConstitution(vm.getConstitution());
			  af.setGstNo(vm.getGstNo());
			  af.setAddress(vm.getAddress());
			  af.setAccountNo(vm.getAccountNo());
			  af.setBankName(vm.getBankName());
			  af.setPinCode(vm.getPinCode());
			  af.setBranch(vm.getBranch());
			  af.setContactPhone(vm.getContactPhone());
			  af.setAccountType(vm.getAccountType());
			  af.setEmail(af.getEmail());
			  af.setIfsc(af.getIfsc());
			  af.setContactPerson(af.getContactPerson());*/
			//  af.setEnteredRamarks(af.getEnteredRamarks());
			  af=vr.findById(vm.getVendorId()).orElse(null);
			  af.setVendorApprovedBy(userCode);
			  af.setVendorApprovedRemarks(af.getVendorApprovedRemarks());
			  af.setVendorApprovedDate(new Date());
			  af.setVendorStatus(vm.getApprovedStatus());
			  VendorMaster af1=vr.save(af);
			if(af1!=null)
			{
				msg="Vendor Details are Successfully Approved"+af1.getVendorId();
			}
			else
			{
				msg="Error is occured while approving details";
			}
		}
		catch(Exception e)
		{
			msg="Error is occured while adding details";
		}
		return msg;
	}
	
	
	
	
	
	

	//to get the details of all expheads
		public List<VendorMaster> getAllVendorDetails(String status)
		{
			List<VendorMaster> cmlist = new ArrayList<VendorMaster>();
			
			cmlist=vr.getAllVendorMaster(status);
			return cmlist;
		}
	
	
}
