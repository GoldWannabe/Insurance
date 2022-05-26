package Customer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.UUID;

public class Rank {
	private String rankID;
	private String customerID;
	private EMaterial material;
	private float fireFacilities;
	private boolean height;
	private int scale;
	private float surroundingFacilities;
	
	private enum EMaterial{rock, wood, gold, money};
	
	public Rank(){

	}
	
	public Rank(String inputString) {
		StringTokenizer stringTokenizer = new StringTokenizer(inputString);
		this.rankID = stringTokenizer.nextToken();
		this.customerID = stringTokenizer.nextToken();
		this.setMaterial(stringTokenizer.nextToken());
		this.fireFacilities = Float.parseFloat(stringTokenizer.nextToken());
		this.height = Boolean.parseBoolean(stringTokenizer.nextToken());
		this.scale = Integer.parseInt(stringTokenizer.nextToken());
		this.surroundingFacilities = Float.parseFloat(stringTokenizer.nextToken());
	}

	public void finalize() throws Throwable {

	}
	
	public void setRankID() {
		this.rankID = UUID.randomUUID().toString();
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
	
	public void register() {
		try {
			File file = new File(".//DB//Rank.txt");
			FileWriter fileWriter = new FileWriter(file, true);
			fileWriter.write(this.rankID + " "+this.customerID+" " + this.material + " "
					+ this.fireFacilities + " " + this.height + " " + this.scale + " " + this.surroundingFacilities
					+"\n");
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("DB 접근 오류: 정보 접근에 실패하였습니다. 해당 문제가 계속 발생할 시에는 사내 시스템 관리팀(1234-5678)에게 문의 주시기 바랍니다.");
			e.printStackTrace();
		}
	}


}//end Rank