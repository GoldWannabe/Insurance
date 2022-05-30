package Provision;

import java.util.ArrayList;


public class ProvisionListImpl implements ProvisionList {

	private ArrayList<Provision> provisionList;
//	public Provision m_Provision;

	public ProvisionListImpl(){
		this.provisionList = new ArrayList<Provision>();
	}

	public void finalize() throws Throwable {

	}

	@Override
	public boolean add(Provision provision) {
		if(this.provisionList.add(provision)) return true;
		return false;
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public boolean delete(String provisionID) {
		if(this.provisionList.remove(provisionID)) return true;
		return false;
	}

	@Override
	public Provision get(String provisionID) {
		for(Provision provision : this.provisionList) {
			if(provision.getProvisionID().equals(provisionID)) return provision;
		}
		return null;
	}

	@Override
	public boolean update(String provisionID) {
		for(Provision provision : this.provisionList) {
			if(provision.getProvisionID().equals(provisionID)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public ArrayList<Provision> getAll() {
		// TODO Auto-generated method stub
		return this.provisionList;
	}

}//end ProvisionListImpl