package agent.model.hardware;

import oshi.SystemInfo;
import oshi.hardware.Baseboard;

/**
 * @author Yaroslav
 */

public final class MotherBoard extends HardWare {
	
	private static MotherBoard INSTANCE;
	
	public MotherBoard() {
		INSTANCE = this;
	}
	
	public MotherBoard load(SystemInfo sysInfo) {
		Baseboard mb = sysInfo.getHardware().getComputerSystem().getBaseboard();
		setMotherBoardManufacturer(mb.getManufacturer());
		setMotherBoardModel(mb.getModel());
		setMotherBoardSerial(mb.getSerialNumber());
		setMotherBoardVersion(mb.getVersion());
		return this;
	}
	
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
		return INSTANCE;
	}
}
