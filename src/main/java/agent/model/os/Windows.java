package agent.model.os;

/**
 * @author Yaroslav
 */

public class Windows extends OperationSystem {

	public Windows() {

	}
	
	public Windows(String osManufacturer, String osFamily, String osVersionInfo, String osBitness) {
		super(osManufacturer, osFamily, osVersionInfo, osBitness);
	}

	public String getOsManufacturer() {
		return super.getOsManufacturer();
	}

	public String getOsFamily() {
		return super.getOsFamily();
	}

	public String getOsVersionInfo() {
		return super.getOsVersionInfo();
	}

	public String getOsBitness() {
		return super.getOsBitness();
	}
}
