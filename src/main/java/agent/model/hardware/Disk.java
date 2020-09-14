package agent.model.hardware;

import agent.model.hardware.interfaces.IDisk;

/**
 * @author Yaroslav
 */

public class Disk extends HardWare implements IDisk {
	
	private static String _diskSize;

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
	public String getDiskSize() {
		return _diskSize;
	}

	@Override
	public void setDiskSize(String diskSize) {
		_diskSize = diskSize;
	}
	
	public static Disk getInstance() {
		return SingletonHolder.INSTANCE;
	}
	
	private static class SingletonHolder {
		protected static final Disk INSTANCE = new Disk();
	}
}
