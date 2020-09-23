package agent.model.hardware;

import java.util.List;

import agent.model.hardware.interfaces.IDisk;
import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;

/**
 * @author Yaroslav
 */

public final class Disk extends HardWare implements IDisk {
	
	private static Disk INSTANCE;
	
	private static long _diskSize;
	
	public Disk() {
		INSTANCE = this;
	}

	public Disk load(SystemInfo sysInfo) {
		sysInfo.getHardware().getDiskStores().forEach(d -> {
			setDiskName(d.getName());
			setDiskModel(d.getModel());
			setDiskSerial(d.getSerial());
			_diskSize = d.getSize();
		});
		
		return this;
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
	
	public static Disk getInstance() {
		return INSTANCE;
	}
}
