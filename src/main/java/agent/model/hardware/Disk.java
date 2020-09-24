package agent.model.hardware;

import agent.model.hardware.interfaces.IDisk;
import oshi.SystemInfo;

/**
 * @author Yaroslav
 */

public final class Disk implements IDisk {
	
	private static String _diskName;
	private static String _diskModel;
	private static String _diskSerial;
	private static long _diskSize;
	
	public Disk(SystemInfo sysInfo) {
		load(sysInfo);
	}

	public void load(SystemInfo sysInfo) {
		sysInfo.getHardware().getDiskStores().forEach(d -> {
			_diskName = d.getName();
			_diskModel = d.getModel();
			_diskSerial = d.getSerial();
			_diskSize = d.getSize();
		});
	}
	
	@Override
	public String getDiskName() {
		return _diskName;
	}

	@Override
	public String getDiskModel() {
		return _diskModel;
	}
	
	@Override
	public String getDiskSerial() {
		return _diskSerial;
	}
	
	@Override
	public long getDiskSize() {
		return _diskSize;
	}
}
