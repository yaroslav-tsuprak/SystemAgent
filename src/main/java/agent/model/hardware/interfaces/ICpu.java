package agent.model.hardware.interfaces;

/**
 * @author Yaroslav
 */

public interface ICpu {

	int getLogicalCpuCount();
	
	int getPhysicalCpuCount();
	
	String getCpuIdentifier();
	
	String getCpuId();

	void setLogicalCpuCount(int logicalCpuCount);
	
	void setPhysicalCpuCount(int physicalCpuCount);
	
	void setCpuIdentifier(String cpuIdentifier);
	
	void setCpuId(String cpuId);

}
