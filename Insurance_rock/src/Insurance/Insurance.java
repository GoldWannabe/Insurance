package Insurance;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author ansm6
 * @version 1.0
 * @created 08-5-2022 오후 10:34:19
 */

public class Insurance {
    private String insuranceID;
    protected enum eInsurance{genetal, house}; 
	private eInsurance insuranceType;
	private boolean longTerm;
	private String insuranceName;
	private String specialContract;
	private String applyCondition;
	private String compensateCondition;
	private String explanation;
	private int standardFee;
	private LocalDate releaseDate;
	
	public Insurance(eInsurance insuranceType, boolean longTerm, String name, String specialContract, String applyCondition, String compensateCondition, String explanation){
		this.insuranceType = insuranceType;
		this.longTerm = longTerm;
		this.insuranceName = name;
		this.specialContract = specialContract;
		this.applyCondition = applyCondition;
		this.compensateCondition = compensateCondition;
		this.explanation = explanation;
	}

	public String getInsuranceID() {
		return insuranceID;
	}
	
	public void setInsuranceID(String insuranceID) {
		this.insuranceID = insuranceID;
	}
	
	public String getInsuranceName() {
		return insuranceName;
	}
	
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
	
	public String getApplyCondition() {
		return applyCondition;
	}
	
	public void setApplyCondition(String applyCondition) {
		this.applyCondition = applyCondition;
	}
	public String getCompensateCondition() {
		return compensateCondition;
	}
	
	public void setCompensateCondition(String compensateCondition) {
		this.compensateCondition = compensateCondition;
	}
	
	public String getExplanation() {
		return explanation;
	}
	
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	
	public boolean isLongTerm() {
		return longTerm;
	}

	public void setLongTerm(boolean longTerm) {
		this.longTerm = longTerm;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getSpecialContract() {
		return specialContract;
	}

	public void setSpecialContract(String specialContract) {
		this.specialContract = specialContract;
	}

	public int getStandardFee() {
		return standardFee;
	}

	public void setStandardFee(int standardFee) {
		this.standardFee = standardFee;
	}
	
	public eInsurance getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(eInsurance insuranceType) {
		this.insuranceType = insuranceType;
	}

	public void finalize() throws Throwable {

	}
	
	public void apply(){

	}

	public void design(){

	}

	public void examine(){

	}

	public void measureStandardFee(){

	}

	public void permitInsurance(){

	}

	public void register(){

	}

	public void sell(){

	}

	public void verify(){

	}
}//end Insurance