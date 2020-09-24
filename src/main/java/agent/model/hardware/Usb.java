package agent.model.hardware;

import agent.model.hardware.interfaces.IUsb;
import oshi.SystemInfo;

/**
 * @author Yaroslav
 */

public final class Usb implements IUsb {

	private static String _usbName;
	private static String _usbVendor;
	private static String _usbDeviceId;
	
	public Usb(SystemInfo sysInfo) {
		load(sysInfo);
	}
	
	public void load(SystemInfo sysInfo) {
		sysInfo.getHardware().getUsbDevices(false).forEach(usb -> {
			_usbName = usb.getName();
			_usbVendor = usb.getVendor();
			_usbDeviceId = usb.getUniqueDeviceId();
		});
	}
	
	@Override
	public String getUsbName() {
		return _usbName;
	}
	
	@Override
	public String getUsbVendor() {
		return _usbVendor;
	}

	@Override
	public String getUsbUniqueDeviceId() {
		return _usbDeviceId;
	}
}
