package Model.Accident;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 * @author ansm6
 * @version 1.0
 * @created 08-5-2022 ���� 10:34:18
 */
public interface AccidentList {
	public boolean add(Accident accident);
	public boolean delete(String ID);
	public ResultSet update();
	
	public ArrayList<Accident> get(String customerName_inser, String phoneNum_inser);
	public ArrayList<Accident>  getAll();
	
	
}//end AccidentList