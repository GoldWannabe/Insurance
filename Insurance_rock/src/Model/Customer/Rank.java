package Model.Customer;

import java.sql.ResultSet;
import java.util.UUID;

import Model.DB.IRankDao;

public class Rank {
	private String rankID;
	private EMaterial material;
	private double fireFacilities;
	private boolean height;
	private int scale;
	private double surroundingFacilities;

	private enum EMaterial {
		wood, rock, concrete, iron
	};

	private enum EPurpose {
		living, factory, culturalAsset, store, office, carPark
	};

	private EPurpose purpose;

	private IRankDao rankDao;

	public Rank() {

	}

	public void finalize() throws Throwable {

	}

	public String getRankID() {
		return this.rankID;
	}

	public IRankDao getRankDao() {
		return rankDao;
	}

	public void setRankDao(IRankDao rankDao) {
		this.rankDao = rankDao;
	}

	public void setRankID(String rankID) {
		this.rankID = rankID;
	}

	public double getFireFacilities() {
		return fireFacilities;
	}

	public void setFireFacilities(double fireFacilities) {
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

	public double getSurroundingFacilities() {
		return surroundingFacilities;
	}

	public void setSurroundingFacilities(double surroundingFacilities) {
		this.surroundingFacilities = surroundingFacilities;
	}

	public String getMaterial() {
		return this.material.toString();
	}

	public void setMaterial(String material) {
		if (material.equals(EMaterial.rock.toString())) {
			this.material = EMaterial.rock;
		} else if (material.equals(EMaterial.wood.toString())) {
			this.material = EMaterial.wood;
		} else if (material.equals(EMaterial.concrete.toString())) {
			this.material = EMaterial.concrete;
		} else if (material.equals(EMaterial.iron.toString())) {
			this.material = EMaterial.iron;
		}

	}

	public EPurpose getPurpose() {
		return this.purpose;
	}

	public void setPurpose(String purpose) {
		if (purpose.equals(EPurpose.living.toString())) {
			this.purpose = EPurpose.living;
		} else if (purpose.equals(EPurpose.factory.toString())) {
			this.purpose = EPurpose.factory;
		} else if (purpose.equals(EPurpose.culturalAsset.toString())) {
			this.purpose = EPurpose.culturalAsset;
		} else if (purpose.equals(EPurpose.store.toString())) {
			this.purpose = EPurpose.store;
		}else if (purpose.equals(EPurpose.office.toString())) {
			this.purpose = EPurpose.office;
		} else if (purpose.equals(EPurpose.carPark.toString())) {
			this.purpose = EPurpose.carPark;
		}
	}

	public void register() {
		this.rankID = UUID.randomUUID().toString();
		this.rankDao = new IRankDao();
		this.rankDao.create(this);
	}

	public ResultSet retriveByID(String rankID) {
		this.rankDao = new IRankDao();
		return this.rankDao.retriveByID(rankID);
	}

}// end Rank