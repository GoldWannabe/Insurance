package Control.SalesTeam;

import java.util.Scanner;

import Model.Channel.Channel;

public class ChannelManagement {

	Channel channel;

	public ChannelManagement() {
		this.channel = new Channel();
		this.channel.setChnnel();
	}

	public void viewChannel() {
		
		showChannel();
		selectChannelManagement();

	}

	private void showChannel() {
		this.channel.showChannel();
	}

	private void selectChannelManagement() {
		Scanner scanner = new Scanner(System.in);
		boolean correctInput = false;

		while (!correctInput) {
			System.out.println("판매 채널 관리 메뉴를 선택해주세요.");
			System.out.println("1. 검색 2. 추가 3. 수정 4. 삭제 0. 취소");

			String select = scanner.next();
			if (select.equals("1") || select.equals("검색")) {
				searchChannel();
			} else if (select.equals("2") || select.equals("추가")) {
				addChannel();
			} else if (select.equals("3") || select.equals("수정")) {
				editChannel();
			} else if (select.equals("4") || select.equals("삭제")) {
				deleteChannel();
			} else if (select.equals("0") || select.equals("취소")) {
				System.out.println("취소되었습니다. 선택창으로 돌아갑니다.");
				correctInput = true;
			} else {
				System.out.println("형식에 맞지 않습니다. 다시 입력해주세요.");
			}
		}
	}

	private void deleteChannel() {
		Scanner scanner = new Scanner(System.in);
		String channelID = null;
		boolean isSearch = false;
		while (!isSearch) {
			System.out.println("삭제할 채널번호(ID)을 입력해주세요.");
			channelID = scanner.next();
			if (this.channel.searchID(channelID) != null) {
				isSearch = true;
			} else {
				System.out.println("해당 채널이 존재하지 않습니다. 다시 입력해주세요.");
			}
		}

		System.out.println("채널ID: " + channelID + "를 정말 삭제 하시겠습니까? 삭제를 원하면 1을 입력해주시고 아니면 아무키나 눌러주세요.");
		if (scanner.next().equals("1")) {
			this.channel.deleteChannel(channelID);
			System.out.println("삭제가 완료되었습니다.");
		} else {
			System.out.println("삭제가 취소되었습니다. 메인화면으로 돌아갑니다.");
		}
	}

	private void editChannel() {
		Scanner scanner = new Scanner(System.in);
		boolean isSearch = false;
		String channelID = null;
		Channel tempChannel = null;
		while (!isSearch) {
			System.out.println("수정할 채널번호(ID)을 입력해주세요.");
			channelID = scanner.next();
			tempChannel = this.channel.searchID(channelID);
			if (tempChannel != null) {
				isSearch = true;
			} else {
				System.out.println("해당 채널이 존재하지 않습니다. 다시 입력해주세요.");
			}
		}
		
		this.channel.editChannel(tempChannel);
	}

	private void addChannel() {
		this.channel.addChannel();
		this.channel.saveChannel();
		System.out.println("판매채널이 추가되었습니다.");
	}

	private void searchChannel() {
		Scanner scanner = new Scanner(System.in);
		boolean isSearch = false;
		String channelName;
		while (!isSearch) {
			System.out.println("채널이름을 입력해주세요.");
			channelName = scanner.next();
			isSearch = this.channel.searchChannel(channelName);
		}

	}

}
