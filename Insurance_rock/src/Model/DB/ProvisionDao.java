package Model.DB;

import java.sql.ResultSet;

import Model.Provision.Provision;

public class ProvisionDao  extends Dao {
	public ProvisionDao() {
		super.connect();
	}

	public boolean create() {
		String query = "";

		return super.create(query);
	}

	public ResultSet retrive() {
		String query = "select * from provision;";

		return super.retrive(query);
	}

	public boolean update() {
		String query = "";

		return super.update(query);
	}

	public boolean delete() {
		String query = "";

		return super.delete(query);
	}

	public boolean creatNew(Provision provision) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("insert into Provision values(")
				.append("\'" + provision.getProvisionID() + "\',")
				.append("\'" + provision.getCustomerID() + "\',")
				.append("\'" + provision.getCustomerName() + "\',")
				.append("\'" + provision.getPhoneNum() + "\',")
				.append("\'" + provision.getAccountNum() + "\',")
				.append("\'" + provision.getBankName() + "\',")
				.append( + provision.getCompensation() + ",")
				.append("\'" + provision.getCompensationDate() + "\',")
				.append("\'" + provision.getInsuranceName() + "\',")
				.append(  provision.isLongTerm() + ",")
				.append("\'" + provision.getInsuranceType() + "\',")
				.append("\'" + provision.getContractID()+ "\')")
				.toString();

		return super.create(query);
	}
}
