package Insurance;


/**
 * @author ansm6
 * @version 1.0
 * @created 08-5-2022 ���� 10:34:18
 */
public class GeneralInsurance extends Insurance {

	private int generalPremiumRate;

	public GeneralInsurance(boolean longTerm, String name, String specialContract, String applyCondition, String compensateCondition, String explanation){
		super(eInsurance.genetal, longTerm, name, specialContract, applyCondition, compensateCondition, explanation);
	}

	public void finalize() throws Throwable {
		super.finalize();
	}
	public void measureStandardFee(){
		this.setStandardFee(10000);
	}

	public void verify(){

	}
}//end GeneralInsurance