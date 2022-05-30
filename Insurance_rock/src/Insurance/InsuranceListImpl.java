package Insurance;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Insurance.Insurance.EInsurance;

public class InsuranceListImpl implements InsuranceList {
	private ArrayList<Insurance> insuranceList;

	public InsuranceListImpl(){
		init();
	}

	private void init() {
		try {
			BufferedReader insuranceFile = new BufferedReader(new FileReader(".//DB//Insurance.txt"));
			this.insuranceList = new ArrayList<Insurance>();
			while (insuranceFile.ready()) {
				String insurance = insuranceFile.readLine();
				if (!insurance.equals("")) {
					String[] temp = insurance.split(" ");
					if(temp[2].equals("general")) this.insuranceList.add(new GeneralInsurance(insurance));
					else if(temp[2].equals("house")) this.insuranceList.add(new HouseInsurance(insurance));
					else System.out.println("잘못된 type의 insurance 존재");
				}
			}
			insuranceFile.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void refresh() {
		init();
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

	// insurance type 선택시 해당하는 보험 목록 불러오기
	@SuppressWarnings("unlikely-arg-type")
	public ArrayList<Insurance> get(EInsurance insuranceType){
		ArrayList<Insurance> tempInsurance = new ArrayList<Insurance>();
		for(Insurance insurance:this.insuranceList) {
			if(insurance.getInsuranceType().equals(insuranceType)) {
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

	@Override
	public Insurance get(String name, EInsurance insuranceType) {
		for(Insurance insurance : this.insuranceList) {
			if(insurance.getInsuranceType() == insuranceType && insurance.getInsuranceName().equals(name)) {
				return insurance;
			}
		}
		return null;
	}

}//end InsuranceListImpl