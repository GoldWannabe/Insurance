package Insurance;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.GeneralRateDao;
import dao.HouseRateDao;
import dao.RegisterHouseRateDao;

public class HouseInsurance extends Insurance {

	private int housePremiumRate;
	private double[] standardRate = new double[] { 0.002, 0.003, 0.005 };
	private RegisterHouseRateDao registerHouseRateDao;
	private HouseRateDao houseRateDao;

	public HouseInsurance(boolean longTerm) {
		super(EInsurance.house, longTerm);
	}

	public HouseInsurance() {
		super(EInsurance.house);
	}

	public HouseInsurance(String inputString) {
		super(inputString, EInsurance.house);
	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public void measureStandardFee() {
		setStandardFee((int) (1000000000 * standardRate[0]/100));
	}

	public boolean registerRate() {
		this.registerHouseRateDao = new RegisterHouseRateDao();
		if (super.register())
			return this.registerHouseRateDao.create(this);
		return false;
	}

	public void verifyPremium() {

	}

	public int getHousePremiumRate() {
		return housePremiumRate;
	}

	public void setHousePremiumRate(int housePremiumRate) {
		this.housePremiumRate = housePremiumRate;
	}

	public double[] getStandardRate() {
		return standardRate;
	}

	public void setStandardRate(double[] standardRate) {
		this.standardRate = standardRate;
	}

	@Override
	public void setRate() {
		this.registerHouseRateDao = new RegisterHouseRateDao();
		ResultSet resultSet = this.registerHouseRateDao.retriveRate(this.getInsuranceID());
		try {
			for(int i=0; resultSet.next(); i++) {
				standardRate[i] = resultSet.getDouble("housePremiumRate");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean notPermitRate() {
		if(this.registerHouseRateDao.deleteRate(this.getInsuranceID())) {
			return super.notPermitInsurance();
		}
		
		return false;
	}

	@Override
	public boolean permitRate() {
		this.houseRateDao = new HouseRateDao();
		if(this.registerHouseRateDao.deleteRate(this.getInsuranceID())) {
			if(super.permitInsurance()) {
				return this.houseRateDao.create(this);
			}
			
		}
		
		
		return false;
	}

}// end houseInsurance