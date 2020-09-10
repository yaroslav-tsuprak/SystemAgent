package agent.model.hardware.interfaces;

public interface IBios {

	String getBiosManufacturer();
	
	String getBiosName();
	
	String getBiosDescription();
	
	String getBiosVersion();
	
	String getBiosReleaseDate();
	
	void setBiosManufacturer(String biosManufacturer);
	
	void setBiosName(String biosName);
	
	void setBiosDescription(String biosDesc);
	
	void setBiosVersion(String biosVersion);
	
	void setBiosReleaseDate(String biosReleaseData);
	
}
