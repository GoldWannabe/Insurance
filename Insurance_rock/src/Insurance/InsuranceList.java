package Insurance;

import java.util.ArrayList;

/**
 * @author ansm6
 * @version 1.0
 * @created 08-5-2022 ���� 10:34:19
 */
public interface InsuranceList {
	
	public boolean add(Insurance insurance);
	public boolean delete(String insuranceID);
	//public boolean update();
	public ArrayList<Insurance> get(String insuranceID);
	
//	}

}//end InsuranceList