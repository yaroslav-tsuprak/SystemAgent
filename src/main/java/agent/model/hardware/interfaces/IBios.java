package agent.model.hardware.interfaces;

public interface IBios {
	
	String getBiosDescription();
	
	String getBiosReleaseDate();
	
	void setBiosDescription(String biosDesc);
	
	void setBiosReleaseDate(String biosReleaseData);
	
}
