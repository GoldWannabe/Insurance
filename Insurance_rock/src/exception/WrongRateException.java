package exception;

@SuppressWarnings("serial")
public class WrongRateException extends Exception {
	public WrongRateException(int rate1, int rate2) {
		super(rate1 + "급의 요율이 " + rate2 + "급보다 높습니다. 다시 적어주세요.");
	}
}
