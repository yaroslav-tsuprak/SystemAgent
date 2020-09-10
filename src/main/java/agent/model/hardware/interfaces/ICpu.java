package agent.model.hardware.interfaces;

public interface ICpu {

	String getLogicalCpuCount();
	
	String getPhysicalCpuCount();
	
	String getCpuIdentifier();
	
	String getCpuId();
	
	String getCpuVendor();

	void setLogicalCpuCount(String logicalCpuCount);
	
	void setPhysicalCpuCount(String physicalCpuCount);
	
	void setCpuIdentifier(String cpuIdentifier);
	
	void setCpuId(String cpuId);
	
	void setCpuVendor(String puVendor);

}
