package exception;

@SuppressWarnings("serial")
public class NotDamageAssessment extends RuntimeException {
	public NotDamageAssessment() {		
		super("손해사정이 완료되지않았습니다.");
		
	}
}
