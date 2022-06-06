package exception;

@SuppressWarnings("serial")
public class LackCustomerBank extends Exception{
	public LackCustomerBank() {		
		super("통장 잔액이 1000원 이하입니다. 돈을 충전 후 다시 시도해 주세요.");
//		System.exit(0);
	}
}
