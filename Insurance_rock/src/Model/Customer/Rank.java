package Model.Customer;

import java.util.StringTokenizer;
import java.util.UUID;

import Model.DB.IRankDao;

public class Rank {
	private String rankID;
	private String customerID;
	private EMaterial material;
	private float fireFacilities;
	private boolean height;
	private int scale;
	private float surroundingFacilities;
	private enum EMaterial{rock, wood, gold, money};
	private String purpose;
	
	private IRankDao rankDao; 
	
	public Rank(){
		this.rankID = UUID.randomUUID().toString();
	}

	public void finalize() throws Throwable {

	}
	
	public String getRankID() {
		return this.rankID;
	}
	
	public String getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public float getFireFacilities() {
		return fireFacilities;
	}

	public void setFireFacilities(float fireFacilities) {
		this.fireFacilities = fireFacilities;
	}

	public boolean isHeight() {
		return height;
	}

	public void setHeight(boolean height) {
		this.height = height;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public float getSurroundingFacilities() {
		return surroundingFacilities;
	}

	public void setSurroundingFacilities(float surroundingFacilities) {
		this.surroundingFacilities = surroundingFacilities;
	}
	
	public String getMaterial() {
		return this.material.toString();
	}

	public void setMaterial(String material) {
		if(material.equals(EMaterial.rock.toString())) {
			this.material = EMaterial.rock;
		} else if(material.equals(EMaterial.wood.toString())){
			this.material = EMaterial.wood;
		} else if(material.equals(EMaterial.gold.toString())) {
			this.material = EMaterial.gold;
		} else if(material.equals(EMaterial.money.toString())) {
			this.material = EMaterial.money;
		} else {
			this.material = EMaterial.rock;
			System.out.println("잘 못 입력함 디폴드 사용");
		}
		
	}
	
	public String getPurpose() {
		return this.purpose;
	}
	
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	public void register() {
		this.rankDao = new IRankDao();
		this.rankDao.create(this);
	}





}//end Rank