package Model.Insurance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Model.Insurance.Insurance.EInsurance;

public class InsuranceListImpl implements InsuranceList {
	private ArrayList<Insurance> insuranceList;

	public InsuranceListImpl() {
		this.insuranceList = new ArrayList<Insurance>();
	}

	public void finalize() throws Throwable {

	}

	public boolean add(Insurance insurance) {
		if (this.insuranceList.add(insurance))
			return true;
		return false;

	}

	@SuppressWarnings("unlikely-arg-type")
	public boolean delete(String insuranceID) {
		if (this.insuranceList.remove(insuranceID))
			return true;
		return false;
	}

	// insurance type 선택시 해당하는 보험 목록 불러오기
	public ArrayList<Insurance> get(EInsurance insuranceType) {
		ArrayList<Insurance> tempInsurance = new ArrayList<Insurance>();
		for (Insurance insurance : this.insuranceList) {
			if (insurance.getInsuranceType().equals(insuranceType)) {
				tempInsurance.add(insurance);
			}
		}
		if (!(tempInsurance.isEmpty())) {
			return tempInsurance;
		}
		return null;
	}

	public void update() {

	}

	@Override
	public Insurance get(String name, EInsurance insuranceType) {
		for (Insurance insurance : this.insuranceList) {
			if (insurance.getInsuranceType() == insuranceType && insurance.getInsuranceName().equals(name)) {
				return insurance;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Insurance> getAll() {

		return this.insuranceList;
	}

	@Override
	public Insurance get(String name) {
		for (Insurance insurance : this.insuranceList) {
			if (insurance.getInsuranceName().equals(name)) {
				return insurance;
			}
		}
		return null;
	}
}// end InsuranceListImpl