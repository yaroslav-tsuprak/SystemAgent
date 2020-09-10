package agent.model.hardware.interfaces;

public interface IUsb {

	String getUsbName();
	
	String getUsbUniqueDeviceId();
	
	String getUsbgetVendor();
	
	void setUsbName(String usbName);
	
	void setUsbUniqueDeviceId(String devId);
	
	void setUsbgetVendor(String usbVendor);
	
}
