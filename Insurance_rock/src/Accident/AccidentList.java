package Accident;

import java.util.ArrayList;


/**
 * @author ansm6
 * @version 1.0
 * @created 08-5-2022 ���� 10:34:18
 */
public interface AccidentList {
	public boolean add(Accident accident);
	public boolean delete(String ID);
	public void update();
	
	public ArrayList<Accident> get(String phoneNum, String customerName);
	
	
}//end AccidentList