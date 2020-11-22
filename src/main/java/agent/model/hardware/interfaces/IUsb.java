package agent.model.hardware.interfaces;

/**
 * @author Yaroslav
 */

public interface IUsb {
	
	String getUsbName();
	
	String getUsbVendor();
	
	String getUsbUniqueDeviceId();
}
