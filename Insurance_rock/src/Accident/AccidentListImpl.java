package Accident;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import Contract.Contract;
import dao.AccidentDao;
import dao.ContractDao;


/**
 * @author ansm6
 * @version 1.0
 * @created 08-5-2022 ���� 10:34:18
 */
public class AccidentListImpl implements AccidentList {

	private ArrayList<Accident> accidentList = new ArrayList<Accident>();
	private AccidentDao accidentDAO;

	public AccidentListImpl(){
		this.accidentDAO = new AccidentDao();
		
//		this.setAccident();
		
	}

		private void setAccident() {
		ResultSet resultSet = accidentDAO.retrive();

			//사고번호ID, 계약ID ,고객ID,가입자명, 연락처,사고날짜,사고내용 ,총비용,손해정도,비용종류,지급여부,책임비율,책임비용
			
			
		
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

	

	public ArrayList<Accident> get(String customerName_inser, String phoneNum_inser) {
		ArrayList<Accident> inforAccident = new ArrayList<Accident>();
		for(Accident accident: this.accidentList) {
			if(accident.getCustomerName().equals(customerName_inser) && accident.getPhoneNum().equals(phoneNum_inser)) {
				inforAccident.add(accident);
			}
		}
		
		if(!(inforAccident.isEmpty())) {
			return inforAccident;
		} 
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Accident> getAll() {
		return this.accidentList;
	}

	public void getcontract() {
		
		// TODO Auto-generated method stub
		
	}

	

	public ArrayList<Accident> get(String customerName_inser, LocalDate accidentDate_inser) {
		ArrayList<Accident> inforAccident = new ArrayList<Accident>();
		for(Accident accident: this.accidentList) {
			if(accident.getCustomerName().equals(customerName_inser) && accident.getAccidentDate().isEqual(accidentDate_inser)) {
				inforAccident.add(accident);
			}
		}
		
		if(!(inforAccident.isEmpty())) {
			return inforAccident;
		} 
		// TODO Auto-generated method stub
		return null;
	}

	public Accident getNum(int num) {
		for(Accident accident: this.accidentList) {
			if(accident.getNum() == num ) {
				return accident;
			}
		}
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public ResultSet update() {
		// TODO Auto-generated method stub
		return null;
	}



	



}//end AccidentListImpl