package Provision;

import java.util.ArrayList;


public interface ProvisionList {


	public boolean add(Provision provision);
	public boolean delete(String provisionID);
	public Provision get(String provisionID);
	public boolean update(String provisionID);
	public ArrayList<Provision> getAll();
	
	public void finalize() throws Throwable;
}//end ProvisionList