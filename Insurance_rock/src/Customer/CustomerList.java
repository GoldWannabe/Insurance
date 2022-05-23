package Customer;

public interface CustomerList {

	public void finalize() throws Throwable;
	
	public boolean add(Customer customer);
	
	public boolean delete(String customerID);

	public boolean edit(String customerID);

	public Customer search(String name, String phoneNum);

}//end CustomerList