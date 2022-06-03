package Model.Accident;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

import Model.Channel.Channel;
import Model.Channel.ChannelListImpl;
import Model.DB.AccidentDao;
import Model.DB.ContractDao;

//121234536456123
/**
 * @author ansm6
 * @version 1.0
 * @created 08-5-2022 ���� 10:34:18
 */
public class Accident {

	private String ID;
	private String contractID;
	private String customerID;
	private String customerName;
	private String phoneNum;
	private LocalDate accidentDate;
	private String content;
	private int totalCost;
	private int damagePer;
	private String kindOfCost;
	private boolean payCompleted;
	private int liablityRate;
	private int liablityCost;	
	private ContractDao contractDAO;
	private AccidentDao accidentDAO;
	private int Num;
	public String getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public Accident() {
		this.accidentDAO = new AccidentDao();
		this.contractDAO = new ContractDao();
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


	public boolean edit() {
		return true;
	}

	public boolean search(String customerName_inser, LocalDate accidentDate_inser, boolean isSearch) {
		// 검색한것을 보여줄려면...? 가입자명과 사고날짜가 같다면. 거기에 해당하는 파일 내용 보여주기.
//			ArrayList<Accident> tempAccidentList = this.accidentList.get(customerName_inser, accidentDate_inser);//내가 적은적과, 내가 적은 Date를 보냉
			
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
			
			//사고날짜는 DB랑 연결되면 이제 비교가능. readLine없다고 생각하고.
			
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

	}

	public void survey() {

	}





	public boolean addaccident() {//사고번호ID, 계약ID ,고객ID,가입자명, 연락처,사고날짜,사고내용 ,총비용,손해정도,비용종류,지급여부,책임비율,책임비용
//		
		return accidentDAO.creat(this);
		// TODO Auto-generated method stub
		
	}

	public ResultSet retriveaccident() {
		// TODO Auto-generated method stub
		return accidentDAO.retriveaccident(this);
		
	}

	public int getNum() {
		return Num;
	}

	public void setNum(int num) {
		Num = num;
	}

	

	public boolean updatedate(LocalDate accidentdate) {
		return accidentDAO.updatedate(this , accidentdate);
	}

	public void updatecontent(String content) {
		accidentDAO.updatecontent(this , content);
		
	}
	public void updatetotal(int totalCost) {
		 accidentDAO.updatetotal(this , totalCost);
		
	}

	public void updateDamage(int damagePer) {
		 accidentDAO.updateDamage(this , damagePer);
		
	}

	public void updateKind(String kindOfCost) {
		 accidentDAO.updateKind(this , kindOfCost);
	}

	public void updateLiablityCost(int liablityCost) {
		 accidentDAO.updateLiablityCost(this , liablityCost);
	}

	public void updateLiablityRate(int liablityRate) {
		 accidentDAO.updateLiablityRate(this , liablityRate);
		
	}


	

	
}// end Accident