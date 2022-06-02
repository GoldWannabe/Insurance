package Model.Insurance;

import java.util.ArrayList;

import Model.Insurance.Insurance.EInsurance;

/**
 * @author ansm6
 * @version 1.0
 * @created 08-5-2022 ���� 10:34:19
 */
public interface InsuranceList {
	
	public boolean add(Insurance insurance);
	public boolean delete(String insuranceID);
	//public boolean update();
	public ArrayList<Insurance> get(EInsurance insuranceType);
	public Insurance get(String name, EInsurance insuranceType);
	public ArrayList<Insurance> getAll();
	public Insurance get(String name);
	
//	}

}//end InsuranceList