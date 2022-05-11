package Channel;

import java.util.ArrayList;

/**
 * @author ansm6 야호
 * @version 1.0
 * @created 08-5-2022 ���� 10:34:18
 */
public class ChannelListImpl implements ChannelList {

	private ArrayList<Channel> channelList;

	public ChannelListImpl(){

	}

	public void finalize() throws Throwable {

	}
	public boolean add(Channel channel){
		if(this.channelList.add(channel)) return true;
		
		return false;

	}

	@SuppressWarnings("unlikely-arg-type")
	public boolean delete(String channelID){
		if(this.channelList.remove(channelID)) return true;
		return false;

	}

	public ArrayList<Channel> get(){
		
		return this.channelList;
	}

//	public void update(){
//
//	}
}//end ChannelListImpl