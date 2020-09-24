package agent.model.hardware;

import agent.model.hardware.interfaces.IUsb;
import oshi.SystemInfo;

/**
 * @author Yaroslav
 */

public final class Usb extends HardWare implements IUsb {

	private static String _usbDeviceId;
	
	public Usb(SystemInfo sysInfo) {
		load(sysInfo);
	}
	
	public void load(SystemInfo sysInfo) {
		sysInfo.getHardware().getUsbDevices(false).forEach(usb -> {
			setUsbName(usb.getName());
			setUsbgetVendor(usb.getVendor());
			_usbDeviceId = usb.getUniqueDeviceId();
		});
	}
	
	public String getUsbName() {
		return super.getName();
	}
	
	public void setUsbName(String usbName) {
		super.setName(usbName);
	}
	
	public String getUsbgetVendor() {
		return super.getVendor();
	}
	
	public void setUsbgetVendor(String usbVendor) {
		super.setVendor(usbVendor);
	}

	@Override
	public String getUsbUniqueDeviceId() {
		return _usbDeviceId;
	}
}
