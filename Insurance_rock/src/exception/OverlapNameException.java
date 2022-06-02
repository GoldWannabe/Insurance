package exception;

@SuppressWarnings("serial")
public class OverlapNameException extends Exception {
	public OverlapNameException() {
		super("중복되는 이름이 존재합니다. 다시 입력해주세요.");
	}
}
