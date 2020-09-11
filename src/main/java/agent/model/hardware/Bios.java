package agent.model.hardware;

import agent.model.hardware.interfaces.IBios;

public class Bios extends HardWare implements IBios {

	private static String _description;
	private static String _releaseData;
	
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

	@Override
	public String getBiosDescription() {
		return _description;
	}

	public String getBiosVersion() {
		return super.getVersion();
	}

	@Override
	public String getBiosReleaseDate() {
		return _releaseData;
	}

	@Override
	public void setBiosDescription(String biosDescription) {
		_description = biosDescription;
	}
	
	public void setBiosVersion(String biosVersion) {
		super.setVersion(biosVersion);
	}

	@Override
	public void setBiosReleaseDate(String biosReleaseData) {
		_releaseData = biosReleaseData;
	}
}
