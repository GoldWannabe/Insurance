package Insurance;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.GeneralRateDao;
import dao.RegisterGeneralRateDao;
import dao.RegisterHouseRateDao;

public class GeneralInsurance extends Insurance {

	private int generalPremiumRate;
	private double[] standardRate = new double[] { 0.002, 0.003, 0.005 };
	private RegisterGeneralRateDao registerGeneralRateDao;
	private GeneralRateDao generalRateDao;

	public GeneralInsurance(boolean longTerm) {
		super(EInsurance.general, longTerm);
	}

	public GeneralInsurance() {
		super(EInsurance.general);
	}

	public GeneralInsurance(String inputString) {
		super(inputString, EInsurance.general);
	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public void measureStandardFee() {
		setStandardFee((int) (1000000000 * standardRate[0]/100));
	}

	@Override
	public boolean registerRate() {
		this.registerGeneralRateDao =new RegisterGeneralRateDao();
		if (super.register())
			return this.registerGeneralRateDao.create(this);
		return false;
	}

	public void verifyPremium() {

	}

	public int getGeneralPremiumRate() {
		return generalPremiumRate;
	}

	public void setGeneralPremiumRate(int generalPremiumRate) {
		this.generalPremiumRate = generalPremiumRate;
	}

	public double[] getStandardRate() {
		return standardRate;
	}

	public void setStandardRate(double[] standardRate) {
		this.standardRate = standardRate;
	}

	@Override
	public void setRate() {
		this.registerGeneralRateDao = new RegisterGeneralRateDao();
		ResultSet resultSet = this.registerGeneralRateDao.retriveRate(this.getInsuranceID());
		try {
			
			for(int i=0; resultSet.next(); i++) {
				System.out.println(i);
				standardRate[i] = resultSet.getDouble("generalPremiumRate");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public boolean notPermitRate() {
		if(this.registerGeneralRateDao.deleteRate(this.getInsuranceID())) {
			return super.notPermitInsurance();
		}
		
		return false;
	}

	@Override
	public boolean permitRate() {
		this.generalRateDao = new GeneralRateDao();
		
		if(this.registerGeneralRateDao.deleteRate(this.getInsuranceID())) {
			 if(super.permitInsurance()) {
				 return this.generalRateDao.create(this);
			 }
		}
		return false;
		
		
	}

}// end GeneralInsurance