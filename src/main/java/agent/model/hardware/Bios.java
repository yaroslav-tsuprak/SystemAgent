package agent.model.hardware;

import agent.model.hardware.interfaces.IBios;
import oshi.SystemInfo;
import oshi.hardware.Firmware;

/**
 * @author Yaroslav
 */

public final class Bios extends HardWare implements IBios {

	private static String _description;
	private static String _releaseData;
	
	public Bios(SystemInfo sysInfo) {
		load(sysInfo);
	}
	
	public void load(SystemInfo sysInfo)
	{
		Firmware bios = sysInfo.getHardware().getComputerSystem().getFirmware();
		setBiosName(bios.getManufacturer());
		setBiosManufacturer(bios.getManufacturer());
		setBiosVersion(bios.getVersion());
		_description = bios.getDescription();
		_releaseData = bios.getReleaseDate();
	}
	
	public String getBiosName() {
		return super.getName();
	}

	public void setBiosName(String biosName) {
		super.setName(biosName);
	}
	
	public String getBiosManufacturer() {
		return super.getManufacturer();
	}
	
	public void setBiosManufacturer(String biosManufacturer) {
		super.setManufacturer(biosManufacturer);
	}

	public String getBiosVersion() {
		return super.getVersion();
	}
	
	public void setBiosVersion(String biosVersion) {
		super.setVersion(biosVersion);
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
