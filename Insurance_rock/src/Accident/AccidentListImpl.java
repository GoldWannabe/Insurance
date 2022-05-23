package Accident;

import java.util.ArrayList;


/**
 * @author ansm6
 * @version 1.0
 * @created 08-5-2022 ���� 10:34:18
 */
public class AccidentListImpl implements AccidentList {

	private ArrayList<Accident> accidentList = new ArrayList<Accident>();

	public AccidentListImpl(){

	}

	public void finalize() throws Throwable {

	}
	public boolean add(Accident accident){
		if(this.accidentList.add(accident)) {
			return true;
		}
		return false;
	}

	public boolean delete(String ID){
		if(this.accidentList.remove(ID)) {
			return true;
		}
		return false;
	}

	public void update(){

	}

	public ArrayList<Accident> get(String phoneNum, String customerName) {
		ArrayList<Accident> inforAccident = new ArrayList<Accident>();
		for(Accident accident: this.accidentList) {
			if(accident.getPhoneNum().equals(phoneNum) && accident.getCustomerName().equals(customerName)) {
				inforAccident.add(accident);
			}
		}
		
		if(!(inforAccident.isEmpty())) {
			return inforAccident;
		} 
		// TODO Auto-generated method stub
		return null;
	}
}//end AccidentListImpl