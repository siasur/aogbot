package me.siasur.areacommunity.aogbot.event;

import java.util.Date;

import me.siasur.areacommunity.aogbot.bridge.BandwithUsage;
import me.siasur.areacommunity.aogbot.bridge.ClientType;
import me.siasur.areacommunity.aogbot.bridge.IAoGChannel;
import me.siasur.areacommunity.aogbot.bridge.IAoGClient;

public class AoGClientJoinEvent extends AoGBaseEvent {

	IAoGClient _client;
	
	protected AoGClientJoinEvent() {

	}

	public IAoGClient getClient() {
		return _client;
	}

	protected void setClient(IAoGClient client) {
		_client = client;
	}

	public static AoGClientJoinEvent forTestingOnly() {
		AoGClientJoinEvent ev = new AoGClientJoinEvent();
		ev.setClient(new IAoGClient() {
			
			@Override
			public void setDescription(String description) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void sendMessage(String message) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void poke(String message) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void kickFromServer(String message) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void kickFromChannel() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isRequestingToTalk() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isRecording() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isPrioritySpeaker() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isOutputOnlyMuted() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isOutputMuted() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isInputMuted() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isChannelCommander() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isAway() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public String getVersion() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getUnreadMessagesCount() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public String getUniqueId() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getTotalConnections() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public long getTimeConnected() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public String getTalkRequestMessage() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getTalkPower() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public String getPhoneticNickname() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getNickname() {
				return "Yup";
			}
			
			@Override
			public int getNeededServerQueryViewPower() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public String getMetaData() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getLoginName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Date getLastConnectedDate() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getIp() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public long getIdleTime() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public int getId() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public BandwithUsage getFiletransferBandwidthUsage() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getDefaultToken() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Date getCreatedDate() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ClientType getClientType() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public IAoGChannel getChannel() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public BandwithUsage getBandwidthUsageLastSecond() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public BandwithUsage getBandwidthUsageLastMinute() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getAwayMessage() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getAvatar() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean canTalk() {
				// TODO Auto-generated method stub
				return false;
			}
		});
		
		return ev;
	}
	
}
