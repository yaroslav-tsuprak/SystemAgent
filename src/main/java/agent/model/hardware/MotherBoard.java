package agent.model.hardware;

import agent.model.hardware.interfaces.IMotherBoard;

/**
 * @author Yaroslav
 */

public class MotherBoard extends HardWare implements IMotherBoard {
	
	public String getMotherBoardManufacturer() {
		return super.getManufacturer();
	}
	
	public String getMotherBoardModel() {
		return super.getModel();
	}
	
	public String getMotherBoardVersion() {
		return super.getVersion();
	}
	
	public String getMotherBoardSerial() {
		return super.getSerial();
	}
	
	public void setMotherBoardManufacturer(String manufacturer) {
		super.setManufacturer(manufacturer);
	}
	
	public void setMotherBoardModel(String model) {
		super.setModel(model);
	}
	
	public void setMotherBoardVersion(String version) {
		super.setVersion(version);
	}
	
	public void setMotherBoardSerial(String serial) {
		super.setSerial(serial);
	}
	
	public static MotherBoard getInstance() {
		return SingletonHolder.INSTANCE;
	}
	
	private static class SingletonHolder {
		protected static final MotherBoard INSTANCE = new MotherBoard();
	}
}
