package exception;

@SuppressWarnings("serial")
public class NonContractException extends Exception{
	public NonContractException() {		
		super("시스템의 문제로 보험의 상세 정보를 불러오지 못했습니다. 잠시후 다시 실행 해주십시오. 해당 문제가 지속적으로 발생할 시에는 사내 시스템 관리팀(1234-5678)에게 문의 주시기바랍니다.");
		
	}
}
