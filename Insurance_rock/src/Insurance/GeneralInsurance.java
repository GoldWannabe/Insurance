package Insurance;

public class GeneralInsurance extends Insurance {

	private int generalPremiumRate;

	public GeneralInsurance(boolean longTerm){
		super(EInsurance.general, longTerm);
	}
	public GeneralInsurance(){
		super(EInsurance.general);
	}
	
	public GeneralInsurance(String inputString) {
		super(inputString, EInsurance.general);
	}
	
	public void finalize() throws Throwable {
		super.finalize();
	}
	public void measureStandardFee(){
		this.setStandardFee(10000);
	}

	public void verifyPremium(){

	}

	
}//end GeneralInsurance