package Accident;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Stream;
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

	public Accident(){

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
	public void add(){
		//가입자명, 사고날짜, 연락처가 해당 고객과 동일하다면 이어서 사고내역 작성해서 추가하기.
				//사고정보(사고내용, 비용, 종류, 손해정도, 총비용, 책임 비용, 책임비율 )입력창과 계약 목록 선택창과 추가, 취소버튼을 출력.
				Scanner scanner = new Scanner(System.in);
				System.out.println("<사고내용, 종류, 손해정도, 총비용, 책임 비용, 책임비율을 입력하세요>");
				System.out.println("사고내용 :");
				content = scanner.nextLine();
				
				System.out.println("(숫자)손해정도: ");
				damagePer = scanner.nextInt();
				
				System.out.println("종류 : ");
				kindOfCost = scanner.next();
				
				System.out.println("(숫자)총 비용 :");
				totalCost = scanner.nextInt();
				
				System.out.println("(숫자)책임 비용");
				liablityCost = scanner.nextInt();
				
				System.out.println("(숫자)책임 비율");
				liablityRate = scanner.nextInt();


				
				//원래는 고객명과 연락처, 사고날짜로 보험 정보를 요청한후 
				//사고정보(사고내용, 비용, 종류, 손해정도, 총비용, 책임 비용, 책임비율 )입력창과 계약 목록 선택창과 추가, 취소버튼을 출력.
				setContent(content);
				setDamagePer(damagePer);
				setKindOfCost(kindOfCost);
				setTotalCost(totalCost);
				setLiablityCost(liablityCost);
				setLiablityRate(liablityRate);
								
				
		
	}

	public void edit(){

	}

	public void search(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("<가입자명과 연락처 그리고 사고날짜를 입력해 주세요.>");
		System.out.println("가입자명 :");
		customerName = scanner.nextLine();
		
		System.out.println("연락처 :");
		phoneNum = scanner.nextLine();
		
		System.out.println("사고날짜 [년(공백) 월(공백) 일:");
		String date = scanner.nextLine();

		String[] dateArray =date.split(" ");
		int[] intArray = new int[dateArray.length];
		
		for(int i=0; i < intArray.length; i++) { 
			intArray[i] = Integer.parseInt(dateArray[i]);
			
		}
		accidentDate= LocalDate.of(intArray[0],intArray[1], intArray[2]);
		
		
		//맞는지 확인하고 보험 정보 불러오기 get추가해야함.
		setCustomerName(customerName);
		setPhoneNum(phoneNum);
		setAccidentDate(accidentDate);
		
		//사고 내역 가져와서 보여줘야함. ㄱㄷ...
		
		System.out.println("해당 고객이 존재하지 않습니다.");
		// TODO Auto-generated method stub
	}

	public void survey(){

	}
}//end Accident