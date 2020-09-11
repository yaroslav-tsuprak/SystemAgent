package agent.model.hardware;

import agent.model.hardware.interfaces.IHardWare;

/**
 * @author Yaroslav
 */

public class HardWare implements IHardWare {

	private static String _deviceName;
	private static String _devManufacturer;
	private static String _devModel;
	private static String _devSerial;
	private static String _devVendor;
	private static String _devVersion;
	

	String getName() {
		return _deviceName;
	}
	
	String getManufacturer() {
		return _devManufacturer;
	}
	String getModel() {
		return _devModel;
	}
	
	String getSerial() {
		return _devSerial;
	}
	
	String getVendor() {
		return _devVendor;
	}
	
	String getVersion() {
		return _devVersion;
	}
	
	void setName(String devName) {
		_deviceName = devName;
	}
	
	void setManufacturer(String devManufacturer) {
		_devManufacturer = devManufacturer;
	}
	
	void setModel(String devModel) {
		_devModel = devModel;
	}
	
	void setSerial(String devSerial) {
		_devSerial = devSerial;
	}
	
	void setVendor(String devVendor) {
		_devVendor = devVendor;
	}
	
	void setVersion(String devVersion) {
		_devVersion = devVersion;
	}
	
}
