package exception;

@SuppressWarnings("serial")
public class NonExistContract extends Exception{
	public NonExistContract() {		
		super("해당고객이 가입한 계약이 존재하지않습니다. 다시입력해주세요");
	}

}
