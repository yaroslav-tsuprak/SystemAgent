package agent.model.os;

/**
 * @author Yaroslav
 */

public interface IOperationSystem {

	String getOsManufacturer();
	
	String getOsFamily();
	
	String getOsVersionInfo();
	
	String getOsBitness();
	
	void setOsManufacturer(String osManufacturer);
	
	void setOsFamily(String osFamily);
	
	void setOsVersionInfo(String osVersionInfo);
	
	void setOsBitness(String osBitness);
}
