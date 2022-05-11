package Customer;


/**
 * @author ansm6
 * @version 1.0
 * @created 09-5-2022 ¿ÀÈÄ 6:20:28
 */
public interface CustomerList {

	public void finalize() throws Throwable;
	
	public boolean add(Customer customer);
	
	public boolean delete(String customerID);

	public boolean edit(String customerID);

	public Customer search(String name, String phoneNum);
}//end CustomerList