package Insurance;

public class HouseInsurance extends Insurance {

	private int housePremiumRate;

	public HouseInsurance(boolean longTerm){
		super(EInsurance.house, longTerm);
	}
	public HouseInsurance(){
		super(EInsurance.house);
	}
	
	public HouseInsurance(String inputString) {
		super(inputString, EInsurance.house);
	}
	
	public void finalize() throws Throwable {
		super.finalize();
	}
	public void measureStandardFee(){
		this.setStandardFee(10000);
	}

	public void verifyPremium(){

	}
}//end houseInsurance