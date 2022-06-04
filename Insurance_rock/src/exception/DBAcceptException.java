package exception;

@SuppressWarnings("serial")
public class DBAcceptException extends RuntimeException {
	public DBAcceptException() {
		super("DB 접근 오류: 정보 접근에 실패하였습니다. 해당 문제가 계속 발생할 시에는 사내 시스템 관리팀(1234-5678)에게 문의 주시기 바랍니다.");
		System.exit(0);
	}
}
