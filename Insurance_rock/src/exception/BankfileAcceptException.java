package exception;

@SuppressWarnings("serial")
public class BankfileAcceptException extends Exception{
	public BankfileAcceptException() {		
		super("통장에 문제가 생겼습니다. 관련팀(1234-5678)에 최대한 빠르게 연락바랍니다.");
	}
}
