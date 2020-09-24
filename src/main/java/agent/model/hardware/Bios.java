package agent.model.hardware;

import agent.model.hardware.interfaces.IBios;
import oshi.SystemInfo;
import oshi.hardware.Firmware;

/**
 * @author Yaroslav
 */

public final class Bios implements IBios {

	private static String _name;
	private static String _manufacturer;
	private static String _version;
	private static String _description;
	private static String _releaseData;
	
	public Bios(SystemInfo sysInfo) {
		load(sysInfo);
	}
	
	public void load(SystemInfo sysInfo)
	{
		Firmware bios = sysInfo.getHardware().getComputerSystem().getFirmware();
		_name = bios.getName();
		_manufacturer = bios.getManufacturer();
		_version = bios.getVersion();
		_description = bios.getDescription();
		_releaseData = bios.getReleaseDate();
	}
	
	@Override
	public String getBiosName() {
		return _name;
	}
	
	@Override
	public String getBiosManufacturer() {
		return _manufacturer;
	}

	@Override
	public String getBiosVersion() {
		return _version;
	}

	@Override
	public String getBiosDescription() {
		return _description;
	}

	@Override
	public String getBiosReleaseDate() {
		return _releaseData;
	}
}
