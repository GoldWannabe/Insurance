package exception;

@SuppressWarnings("serial")
public class WrongInputChannel extends Exception {
	public WrongInputChannel() {
		super("검색된 채널이 없습니다. 정확하게 입력하여 주시기 바랍니다");
	}
}
