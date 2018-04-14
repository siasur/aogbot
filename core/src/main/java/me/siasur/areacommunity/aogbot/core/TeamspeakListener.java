package me.siasur.areacommunity.aogbot.core;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.event.ChannelCreateEvent;
import com.github.theholywaffle.teamspeak3.api.event.ChannelDeletedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ChannelDescriptionEditedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ChannelEditedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ChannelMovedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ChannelPasswordChangedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;
import com.github.theholywaffle.teamspeak3.api.event.ClientLeaveEvent;
import com.github.theholywaffle.teamspeak3.api.event.ClientMovedEvent;
import com.github.theholywaffle.teamspeak3.api.event.PrivilegeKeyUsedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ServerEditedEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3Listener;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;

public class TeamspeakListener implements TS3Listener {

	private TS3Api _ts3Api;
	private int _myself;
	
	public TeamspeakListener(TS3Query ts3Query) {
		_ts3Api = ts3Query.getApi();
	}
	
	@Override
	public void onChannelCreate(ChannelCreateEvent channelCreateEvent) {
		

	}

	@Override
	public void onChannelDeleted(ChannelDeletedEvent channelDeletedEvent) {


	}

	@Override
	public void onChannelDescriptionChanged(ChannelDescriptionEditedEvent channelDescriptionEditedEvent) {


	}

	@Override
	public void onChannelEdit(ChannelEditedEvent channelEditedEvent) {


	}

	@Override
	public void onChannelMoved(ChannelMovedEvent channelMovedEvent) {


	}

	@Override
	public void onChannelPasswordChanged(ChannelPasswordChangedEvent channelPasswordChangedEvent) {


	}

	@Override
	public void onClientJoin(ClientJoinEvent clientJoinEvent) {


	}

	@Override
	public void onClientLeave(ClientLeaveEvent clientLeaveEvent) {


	}

	@Override
	public void onClientMoved(ClientMovedEvent clientMovedEvent) {


	}

	@Override
	public void onPrivilegeKeyUsed(PrivilegeKeyUsedEvent privilegeKeyUsedEvent) {


	}

	@Override
	public void onServerEdit(ServerEditedEvent serverEditedEvent) {


	}

	@Override
	public void onTextMessage(TextMessageEvent textMessageEvent) {


	}

}
