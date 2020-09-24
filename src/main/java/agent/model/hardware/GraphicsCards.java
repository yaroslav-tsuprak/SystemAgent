package agent.model.hardware;

import agent.model.hardware.interfaces.IGraphicsCards;
import oshi.SystemInfo;

public final class GraphicsCards extends HardWare implements IGraphicsCards {
	
	private static long _cardVRam;
	
	public GraphicsCards(SystemInfo sysInfo) {
		load(sysInfo);
	}
	
	public void load(SystemInfo sysInfo) {
		sysInfo.getHardware().getGraphicsCards().forEach(g -> { 
			setGraphicsCardName(g.getName());
			setGraphicsCardVendor(g.getVendor());
			_cardVRam = g.getVRam();
		});
	}
	
	public String getGraphicsCardName() {
		return super.getName();
	}
	
	public void setGraphicsCardName(String cardName) {
		super.setName(cardName);
	}
	
	public String getGraphicsCardVendor() {
		return super.getVendor();
	}
	
	public void setGraphicsCardVendor(String cardVendor) {
		super.setVendor(cardVendor);
	}
	
	@Override
	public long getGraphicsCardVRam() {
		return _cardVRam;
	}

	@Override
	public void setGraphicsCardVRam(long vram) {
		_cardVRam = vram;
	}
}
