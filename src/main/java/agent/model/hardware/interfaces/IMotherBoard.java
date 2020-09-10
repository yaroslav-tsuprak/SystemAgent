package agent.model.hardware.interfaces;

public interface IMotherBoard {

	String getMotherBoardManufacturer();
	
	String getMotherBoardModel();
	
	String getMotherBoardVersion();
	
	String getMotherBoardSerial();
	
	void setMotherBoardManufacturer(String manufacturer);
	
	void setMotherBoardModel(String model);
	
	void setMotherBoardVersion(String version);
	
	void setMotherBoardSerial(String serial);

}
