package agent.model.hardware;

import agent.model.hardware.interfaces.IMotherBoard;
import oshi.SystemInfo;
import oshi.hardware.Baseboard;

/**
 * @author Yaroslav
 */

public final class MotherBoard implements IMotherBoard {
	
	private static String _motherboardManufacturer;
	private static String _motherboardModel;
	private static String _motherboardSerial;
	private static String _motherboardVersion;

	public MotherBoard(SystemInfo sysInfo) {
		load(sysInfo);
	}
	
	public void load(SystemInfo sysInfo) {
		Baseboard mb = sysInfo.getHardware().getComputerSystem().getBaseboard();
		_motherboardManufacturer = mb.getManufacturer();
		_motherboardModel = mb.getModel();
		_motherboardSerial = mb.getSerialNumber();
		_motherboardVersion = mb.getVersion();
	}
	
	@Override
	public String getMotherBoardManufacturer() {
		return _motherboardManufacturer;
	}
	
	@Override
	public String getMotherBoardModel() {
		return _motherboardModel;
	}
	
	@Override
	public String getMotherBoardSerial() {
		return _motherboardSerial;
	}
	
	@Override
	public String getMotherBoardVersion() {
		return _motherboardVersion;
	}
}
