package agent.model.hardware.interfaces;

/**
 * @author Yaroslav
 */

public interface ICpu {
	
	String getCpuName();
	
	String getCpuVendor();

	int getLogicalCpuCount();
	
	int getPhysicalCpuCount();
	
	String getCpuIdentifier();
	
	String getCpuId();

}
