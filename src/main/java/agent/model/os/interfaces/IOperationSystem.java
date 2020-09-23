package agent.model.os.interfaces;

/**
 * @author Yaroslav
 */

public interface IOperationSystem {

	String getOsManufacturer();
	
	String getOsFamily();
	
	String getOsVersionInfo();
	
	int getOsBitness();

}
