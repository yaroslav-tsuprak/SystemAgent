package agent.model.hardware;

import agent.model.hardware.interfaces.IHardWare;

/**
 * @author Yaroslav
 */

public abstract class HardWare implements IHardWare {

	public String _deviceName;

	String getDeviceName() {
		return _deviceName;
	}
	
	void setDeviceName(String devName) {
		_deviceName = devName;
	}
	
}
