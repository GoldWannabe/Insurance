package exception;

@SuppressWarnings("serial")
public class LackInsuranceBank extends Exception{
	public LackInsuranceBank() {		
		super("보험통장의 잔액이 부족합니다.");
		System.exit(0);
	}
}
