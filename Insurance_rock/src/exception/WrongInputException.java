package exception;

@SuppressWarnings("serial")
public class WrongInputException extends Exception {
	public WrongInputException() {
		super("입력이 잘못 되었습니다. 다시 입력해주세요.");
	}
}
