package agent.model.hardware.interfaces;

/**
 * @author Yaroslav
 */

public interface ICpu {

	int getLogicalCpuCount();
	
	int getPhysicalCpuCount();
	
	String getCpuIdentifier();
	
	String getCpuId();

}
