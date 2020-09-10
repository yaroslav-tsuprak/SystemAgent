package agent.model.hardware.interfaces;

public interface IDisk {

	String getDiskName();
	
	String getDiskModel();
	
	String getDiskSerial();
	
	String getDiskSize();
	
	void setDiskName(String diskName);
	
	void setDiskModel(String diskModel);
	
	void setDiskSerial(String diskSerial);
	
	void setDiskSize(String diskSize);
	
}
