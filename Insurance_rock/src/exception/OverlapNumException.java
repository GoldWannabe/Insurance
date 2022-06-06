package exception;

@SuppressWarnings("serial")
public class OverlapNumException extends Exception {
	public OverlapNumException() {
		super("채널번호가 중복됩니다. 다른 번호로 지정해 주시기 바랍니다.");
		
	}
}
