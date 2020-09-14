package agent.model.hardware;

import agent.model.hardware.interfaces.IUsb;

/**
 * @author Yaroslav
 */

public class Usb extends HardWare implements IUsb {
	
	private static String _usbDeviceId;
	
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

	@Override
	public void setUsbUniqueDeviceId(String devId) {
		_usbDeviceId = devId;
	}
	
	public static Usb getInstance() {
		return SingletonHolder.INSTANCE;
	}
	
	private static class SingletonHolder {
		protected static final Usb INSTANCE = new Usb();
	}
}
