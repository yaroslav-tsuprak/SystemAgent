package agent.model.hardware;

import agent.model.hardware.interfaces.IDisk;
import oshi.SystemInfo;

/**
 * @author Yaroslav
 */

public final class Disk extends HardWare implements IDisk {
	
	private static long _diskSize;
	
	public Disk(SystemInfo sysInfo) {
		load(sysInfo);
	}

	public void load(SystemInfo sysInfo) {
		sysInfo.getHardware().getDiskStores().forEach(d -> {
			setDiskName(d.getName());
			setDiskModel(d.getModel());
			setDiskSerial(d.getSerial());
			_diskSize = d.getSize();
		});
	}
	
	public String getDiskName() {
		return super.getName();
	}

	public String getDiskModel() {
		return super.getModel();
	}
	
	public String getDiskSerial() {
		return super.getSerial();
	}
	
	public void setDiskName(String diskName) {
		super.setName(diskName);
	}
	
	public void setDiskModel(String diskModel) {
		super.setModel(diskModel);
	}
	
	public void setDiskSerial(String diskSerial) {
		super.setSerial(diskSerial);
	}
	
	@Override
	public long getDiskSize() {
		return _diskSize;
	}
}
