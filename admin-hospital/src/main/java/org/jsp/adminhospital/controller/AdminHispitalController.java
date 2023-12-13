package org.jsp.adminhospital.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.adminhospital.dao.AdminDao;
import org.jsp.adminhospital.dao.HospitalDao;
import org.jsp.adminhospital.dto.Admin;
import org.jsp.adminhospital.dto.Hospital;

public class AdminHispitalController {
	static Scanner s = new Scanner(System.in);
	static AdminDao adminDao = new AdminDao();
	static HospitalDao hospitalDao=new HospitalDao();

	public static void main(String[] args) {
		System.out.println("1.Save Admin");
		System.out.println("2.Update Admin");
		System.out.println("3.Find Admin By Id");
		System.out.println("4.Verify Admin By Phone and password");
		System.out.println("5.Verify Admin By Email and password");
		System.out.println("6.Add Hospital");
		System.out.println("7.Update Hospital");
		System.out.println("8.Find Hospital By User Id");
		System.out.println("9.Find Hospital By gst number");
		System.out.println("enter your choice");
		switch (s.nextInt()) {
		case 1: {
			saveAdmin();
			break;
		}
		case 2: {
			updateAdmin();
			break;
		}
		case 3: {
			findAdminById();
			break;
		}
		case 4: {
			verifyByPhone();
			break;
		}
		case 5: {
			verifyByEmail();
			break;
		}
		case 6: {
			saveHospital();
			break;
		}
		case 7: {
			updateHospital();
			break;
		}
		case 8: {
			findHospitalsByAdminId();
			break;
		}
		case 9: {
			findHospitalByGst();
			break;
		}

		default:
			break;
		}

	}
	
	public static void saveAdmin() {
		System.out.println("Enter the name,phone,email and password to save Admin");
		Admin admin=new Admin();
		admin.setName(s.next());
		admin.setPhone(s.nextLong());
		admin.setEmail(s.next());
		admin.setPassword(s.next());
		admin= adminDao.saveAdmin(admin);
		System.out.println("Adimi saved with Id:" + admin.getId());
	}
	
	public static void updateAdmin() {
		System.out.println("Enter the name,phone,email and password to save Admin");
		Admin admin=new Admin();
		admin.setName(s.next());
		admin.setPhone(s.nextLong());
		admin.setEmail(s.next());
		admin.setPassword(s.next());
		admin= adminDao.updateAdmin(admin);
		System.out.println("Adimi updated successfully");
	}
	public static void findAdminById() {
		System.out.println("Enter the Admin Id find User");
		Admin u = adminDao.findAdminById(s.nextInt());
		if (u != null) {
			System.out.println("Admin Id :" + u.getId());
			System.out.println("Admin Name :" + u.getName());
			System.out.println("Admin Phone :" + u.getPhone());
			System.out.println("Admin Email :" + u.getEmail());
		} else {
			System.err.println("Invalid Admin Id");
		}
	}
	public static void verifyByPhone() {
		System.out.println("Enter phone Number and Password to verify User");
		long phone = s.nextLong();
		String password = s.next();
		Admin a = adminDao.verifyAdmin(phone, password);
		if (a != null) {
			System.out.println("Admin Id :" + a.getId());
			System.out.println("Admin Name :" + a.getName());
			System.out.println("Admin Phone :" + a.getPhone());
			System.out.println("Admin Email :" + a.getEmail());
		} else {
			System.err.println("Invalid Admin Phone number or Password");
		}
	}
	
	public static void verifyByEmail() {
		System.out.println("Enter phone Number and Email to verify User");
		String email=s.next();
		String password = s.next();
		Admin a = adminDao.verifyAdmin(email, password);
		if (a != null) {
			System.out.println("Admin Id :" + a.getId());
			System.out.println("Admin Name :" + a.getName());
			System.out.println("Admin Phone :" + a.getPhone());
			System.out.println("Admin Email :" + a.getEmail());
		} else {
			System.err.println("Invalid Admin Email number or Password");
		}
	}
	public static void saveHospital() {
		System.out.println("Enter the Admin id to add Order");
		int admin_id = s.nextInt();
		System.out.println("Enter the name,gst,year,founder,city");
		Hospital hospital = new Hospital();
		hospital.setName(s.next());
		hospital.setGst_number(s.next());
		hospital.setYear_of_estb(s.nextInt());
		hospital.setFounder(s.next());
		hospital.setCity(s.next());
		hospital=hospitalDao.saveHospital(hospital, admin_id);
		if (hospital!=null) {
			System.out.println("Hos[pital saved with Id:"+hospital.getId());
		} else {
			System.out.println("Invalid admin Id!! cannot add Order");
		}
	}
	public static void updateHospital() {
		System.out.println("Enter the Admin id to add Order");
		int admin_id = s.nextInt();
		System.out.println("Enter the foodItem, cost, address");
		Hospital hospital = new Hospital();
		hospital.setName(s.next());
		hospital.setGst_number(s.next());
		hospital.setYear_of_estb(s.nextInt());
		hospital.setFounder(s.next());
		hospital.setCity(s.next());
		hospital=hospitalDao.updateHospital(hospital, admin_id);
		if (hospital!=null) {
			System.out.println("Hospital updated");
		} else {
			System.out.println("Invalid admin Id!! cannot add Order");
		}
	}
	
	public static void findHospitalsByAdminId() {
		System.out.println("Enter the id of admin to display the hospitals");
		int id=s.nextInt();
		List<Hospital> hospital=hospitalDao.findHospitalsByAdminId(id);
		if(hospital.size()>0) {
			for(Hospital h:hospital) {
				System.out.println("Hospital ID: "+h.getId());
				System.out.println("Name: "+h.getName());
				System.out.println("GST-NUMBER: "+h.getGst_number());
				System.out.println("Year-Of-Established: "+h.getYear_of_estb());
				System.out.println("Founder: "+h.getFounder());
				System.out.println("City: "+h.getCity());
				System.out.println("------------------------------------------------");
			}
		}else {
			System.err.println("No hospitals found");
		}
	}

	public static void findHospitalByGst() {
		System.out.println("Enter the GST number to display the hospital");
		String gst=s.next();
		Hospital h=hospitalDao.findHospitalsByGst(gst);
		if(h!=null) {
			System.out.println("Hospital ID: "+h.getId());
			System.out.println("Name: "+h.getName());
			System.out.println("GST-NUMBER: "+h.getGst_number());
			System.out.println("Year-Of-Established: "+h.getYear_of_estb());
			System.out.println("Founder: "+h.getFounder());
			System.out.println("City: "+h.getCity());
			System.out.println("------------------------------------------------");
		}
		else {
			System.err.println("No hospital found");
		}
	}

	
}
