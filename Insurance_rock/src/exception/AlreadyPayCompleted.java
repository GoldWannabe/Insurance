package exception;

@SuppressWarnings("serial")
public class AlreadyPayCompleted extends Exception{
	public AlreadyPayCompleted() {		
		super("이미 보상금 지급이 완료되었습니다.");
	}
}
