package Model.Provision;


/**
 * @author ansm6
 * @version 1.0
 * @created 08-5-2022 ���� 10:34:19
 */
public interface ProvisionList {


	public boolean add(Provision provision);
	public boolean delete(String provisionID);
	public Provision get(String provisionID);
	public boolean update(String provisionID);

	public void finalize() throws Throwable;
	public Provision get(String name, String phoneNum);
}//end ProvisionList