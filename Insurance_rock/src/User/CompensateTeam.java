package User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import com.sun.tools.javac.Main;

import Accident.Accident;
import Account.Account;

//488548123456789jyjjyy
/**
 * @author ansm6
 * @version 1.0
 * @created 08-5-2022 ���� 10:34:18
 */
public class CompensateTeam {

	/////////////////////
	
	public Account m_Account;
	public Accident accident; 
	public CompensateTeam() {
		Accident accident = new Accident();
		this.accident = accident;
	}

	public void finalize() throws Throwable {

	}

	public void add() {
		accident.search();
		accident.add();
		
		File file = new File(".//DB//Accident_DB.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				FileWriter fileWriter = new FileWriter(file, true);
				BufferedWriter writer = new BufferedWriter(fileWriter);
				
				
				writer.write(accident.getCustomerName()+" "+accident.getPhoneNum()+" "+accident.getAccidentDate()+" "+accident.getContent()+" "+
						accident.getDamagePer()+" "+accident.getKindOfCost()+" "+accident.getTotalCost()+" "+accident.getLiablityCost()+" "+
						accident.getLiablityRate()+"\n");					
			
				writer.close();
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public void edit() {
		//보험사 홈화면으로 돌아가기

	}

	public void payCost() {

	}



	public void search() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("1. 사고검색  2. 사고추가, 3. 취소");
		String selectNum = scanner.next();


		switch (selectNum) {
		case "1":
			accident.search();
			break;
		case "2":
			add();
			break;
		case "3":
			edit();
			break;
		default:
			System.out.println("선택 이상함");
			break;
		}
	}




	public void start() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("1. 손해사정 2. 취소");
		String selectNum = scanner.next();

		switch (selectNum) {
		case "1":
			search();
			break;
		case "2":

			break;

		default:
			System.out.println("선택 이상함");
			break;
		}
	}// end CompensateTeam
}