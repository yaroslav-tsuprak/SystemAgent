package agent.model.hardware;

import agent.model.hardware.interfaces.IGraphicsCards;
import oshi.SystemInfo;

public final class GraphicsCards implements IGraphicsCards {
	
	private static String _cardName;
	private static String _cardVendor;
	private static long _cardVRam;
	
	public GraphicsCards(SystemInfo sysInfo) {
		load(sysInfo);
	}
	
	public void load(SystemInfo sysInfo) {
		sysInfo.getHardware().getGraphicsCards().forEach(g -> { 
			_cardName = g.getName();
			_cardVendor = g.getVendor();
			_cardVRam = g.getVRam();
		});
	}
	
	@Override
	public String getGraphicsCardName() {
		return _cardName;
	}
	
	@Override
	public String getGraphicsCardVendor() {
		return _cardVendor;
	}
	
	@Override
	public long getGraphicsCardVRam() {
		return _cardVRam;
	}
}
