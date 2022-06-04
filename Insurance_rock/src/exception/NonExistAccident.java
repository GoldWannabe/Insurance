package exception;

@SuppressWarnings("serial")
public class NonExistAccident extends Exception{
	public NonExistAccident() {		
		super("사고정보를 찾지못했습니다.사고날짜와 가입자명을 오탈자없이 적어주세요");
		
	}
}
