package Accident;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//121234536456123
/**
 * @author ansm6
 * @version 1.0
 * @created 08-5-2022 ���� 10:34:18
 */
public class Accident {

	private LocalDate accidentDate;
	private String content;
	private String contractID;
	private String customerName;
	private int damagePer;
	private String ID;
	private String kindOfCost;
	private int liablityCost;
	private int liablityRate;
	private boolean payCompleted;
	private String phoneNum;
	private int totalCost;
	private File file;

//	AccidentListImpl accidentListimpl;
	public Accident() {

	}

	public LocalDate getAccidentDate() {
		return accidentDate;
	}

	public void setAccidentDate(LocalDate accidentDate) {
		this.accidentDate = accidentDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContractID() {
		return contractID;
	}

	public void setContractID(String contractID) {
		this.contractID = contractID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getDamagePer() {
		return damagePer;
	}

	public void setDamagePer(int damagePer) {
		this.damagePer = damagePer;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getKindOfCost() {
		return kindOfCost;
	}

	public void setKindOfCost(String kindOfCost) {
		this.kindOfCost = kindOfCost;
	}

	public int getLiablityCost() {
		return liablityCost;
	}

	public void setLiablityCost(int liablityCost) {
		this.liablityCost = liablityCost;
	}

	public int getLiablityRate() {
		return liablityRate;
	}

	public void setLiablityRate(int liablityRate) {
		this.liablityRate = liablityRate;
	}

	public boolean isPayCompleted() {
		return payCompleted;
	}

	public void setPayCompleted(boolean payCompleted) {
		this.payCompleted = payCompleted;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public void finalize() throws Throwable {

	}

	public void add() {

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				FileWriter fileWriter = new FileWriter(file, true);
				int payCompletedCheck = 0;
				if (this.payCompleted) {
					payCompletedCheck = 1;
				}
				// 사고번호ID, 계약번호 ,가입자명, 연락처,사고날짜,사고내용 ,손해정도,비용종류,지급여부,총비용,책임비용,책임비율
				fileWriter.write(this.getID() + " " + this.getContractID() + " " + this.getCustomerName() + " "
						+ this.getPhoneNum() + " " + this.getAccidentDate() + " " + this.getContent() + " "
						+ this.getDamagePer() + " " + this.getKindOfCost() + " " + payCompletedCheck + " "
						+ this.getTotalCost() + " " + this.getLiablityCost() + " " + this.getLiablityRate() + "\n");

				fileWriter.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public boolean edit() {
		return true;
	}

	public boolean search(String customerName_inser, LocalDate accidentDate_inser, boolean isSearch) {
		// 검색한것을 보여줄려면...? 가입자명과 사고날짜가 같다면. 거기에 해당하는 파일 내용 보여주기.
		Path path = Paths.get(".//DB//Accident_DB.txt");
		Charset cs = StandardCharsets.UTF_8;
		List<String> list = new ArrayList<String>();
		String[] readfilesplit = null;
		try {
			list = Files.readAllLines(path,cs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String readLine : list) {
			readfilesplit = readLine.split(" ");
			String strDate = readfilesplit[4];
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate strToLocalDateTime = LocalDate.parse(strDate, format);
		
			if(readfilesplit[2].equals(customerName_inser) && strToLocalDateTime.isEqual(accidentDate_inser)) {
				System.out.println(readLine);
				System.out.println(customerName_inser+"님의 해당 사고날짜의 정보가 출력되었습니다.");
				return isSearch = false;
			}
		}
		return isSearch;

		// 다읽어보구... 해당하는것만 set해서 넣어... 그리고 get으로 가져와.
		// set의 정보와 VectorList의 비교는 다름! set한것을 Vertor에 넣기!

//		//사고번호ID, 계약번호 ,가입자명, 연락처,사고날짜,사고내용 ,손해정도,비용종류,지급여부,총비용,책임비용,책임비율
//		this.setID(readfilesplit[0]);
//		this.setContractID(readfilesplit[1]);
//		this.setCustomerName(readfilesplit[2]);
//		this.setPhoneNum(readfilesplit[3]);
//		this.setAccidentDate(readfilesplit[4]);
//		this.setContent(readfilesplit[5]);
//		this.setDamagePer(readfilesplit[6]);
//		this.setKindOfCost(readfilesplit[7]);
//		this.setPayCompleted(readfilesplit[8]);
//		this.setTotalCost(readfilesplit[9]);
//		this.setLiablityCost(readfilesplit[10]);
//		this.setLiablityRate(readfilesplit[11]);

	}

	public void survey() {

	}
}// end Accident