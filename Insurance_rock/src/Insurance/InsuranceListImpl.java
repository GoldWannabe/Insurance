package Insurance;

import java.util.ArrayList;

/**
 * @author ansm6
 * @version 1.0
 * @created 08-5-2022 ���� 10:34:19
 */
public class InsuranceListImpl implements InsuranceList {
	private ArrayList<Insurance> insuranceList = new ArrayList<Insurance>();
//	public Insurance m_Insurance;

	public InsuranceListImpl(){

	}

	public void finalize() throws Throwable {

	}
	public boolean add(Insurance insurance){
		if(this.insuranceList.add(insurance)) return true;
		return false;

	}

	@SuppressWarnings("unlikely-arg-type")
	public boolean delete(String insuranceID){
		if(this.insuranceList.remove(insuranceID)) return true;
		return false;
	}

	public ArrayList<Insurance> get(String insuranceID){
		ArrayList<Insurance> tempInsurance = new ArrayList<Insurance>();
		for(Insurance insurance:this.insuranceList) {
			if(insurance.getInsuranceID().equals(insuranceID)) {
				tempInsurance.add(insurance);
			}
		}
		if(!(tempInsurance.isEmpty())) {
			return tempInsurance;
		}
		return null;
	}

	public void update(){

	}

}//end InsuranceListImpl