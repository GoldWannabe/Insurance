package Insurance;

import java.time.LocalDate;
import java.util.Scanner;

public class Insurance {
    private String insuranceID;
    public enum EInsurance{general, house}; 
	private EInsurance insuranceType;
	private boolean longTerm;
	private String insuranceName;
	private String specialContract;
	private String applyCondition;
	private String compensateCondition;
	private String explanation;
	private int standardFee;
	private LocalDate releaseDate;
	
	public Insurance() {
		
	}
	
	public Insurance(EInsurance insuranceType, boolean longTerm){
		this.insuranceType = insuranceType;
		this.longTerm = longTerm;
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
	
	public EInsurance getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(EInsurance insuranceType) {
		this.insuranceType = insuranceType;
	}

	public void finalize() throws Throwable {

	}
	
	public boolean apply(){
		return true;
	}

	public void design(){
		Scanner scanner = new Scanner(System.in);
		this.insuranceID = "0l"; //입력 받는 방식이 아닌 자동으로 번호 매겨짐
		System.out.println("이름을 입력해주세요.");
		this.insuranceName = scanner.next();
		System.out.println("특약을 입력해주세요.");		
		this.specialContract = scanner.next();
		System.out.println("가입조건을 입력해주세요.");
		this.applyCondition = scanner.next();
		System.out.println("보상 조건을 입력해주세요.");
		this.compensateCondition = scanner.next();
		System.out.println("설명을 입력해주세요.");
		this.explanation = scanner.next();
		
//		while(checkName(this.insuranceName)){
//			this.insuranceName = scanner.next();
//		}; 검색을 위함
		
		
	}

	public boolean examine(){
		return true;
	}

	public void measureStandardFee(){

	}

	public void permitInsurance(){
		//DB 저장
	}

	public void register(){

	}

	public void sell(){

	}

	public void verify(){

	}
}//end Insurance