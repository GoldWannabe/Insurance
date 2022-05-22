package Channel;

import java.util.ArrayList;

/**
 * @author ansm6 야호
 * @version 1.0
 * @created 08-5-2022 ���� 10:34:18
 */
public class ChannelListImpl implements ChannelList {

	private ArrayList<Channel> channelList;

	public ChannelListImpl() {
		this.channelList = new ArrayList<Channel>();
	}

	public void finalize() throws Throwable {

	}

	public boolean add(Channel channel) {
		if (this.channelList.add(channel))
			return true;

		return false;

	}

	@SuppressWarnings("unlikely-arg-type")
	public boolean delete(String channelID) {
		if (this.channelList.remove(get(channelID)))
			return true;
		return false;

	}

	@Override
	public ArrayList<Channel> getAll() {
		return this.channelList;
	}

	@Override
	public Channel get(String channelID) {
		for (Channel channel : this.channelList) {
			if (channel.getChannelID().equals(channelID)) {
				return channel;
			}
		}
		return null;
	}

	public ArrayList<Channel> getName(String channelName) {
		ArrayList<Channel> tempChannel = new ArrayList<Channel>();
		for (Channel channel : this.channelList) {
			if (channel.getChannelName().equals(channelName)) {
				tempChannel.add(channel);
			}
		}
		if (!(tempChannel.isEmpty())) {
			return tempChannel;
		}
		return null;
	}
//	public void update(){
//
//	}
}// end ChannelListImpl