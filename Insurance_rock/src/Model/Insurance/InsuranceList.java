package Model.Insurance;

import java.util.ArrayList;

import Model.Insurance.Insurance.EInsurance;


public interface InsuranceList {
	
	public boolean add(Insurance insurance);
	public boolean delete(String insuranceID);
	//public boolean update();
	public ArrayList<Insurance> get(EInsurance insuranceType);
	public Insurance get(String name, EInsurance insuranceType);
	public ArrayList<Insurance> getAll();
	public Insurance get(String name);
	public boolean isEmpty();
	
//	}

}//end InsuranceList