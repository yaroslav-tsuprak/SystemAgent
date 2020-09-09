package agent.model.os;

/**
 * @author Yaroslav
 */

public abstract class OperationSystem implements IOperationSystem {

	private static String _osManufacturer;
	private static String _osFamily;
	private static String _osVersionInfo;
	private static String _osBitness;

	public OperationSystem() {
		
	}
	
	public OperationSystem(String osManufacturer, String osFamily, String osVersionInfo, String osBitness)
	{
		setOsManufacturer(osManufacturer);
		setOsFamily(osFamily);
		setOsVersionInfo(osVersionInfo);
		setOsBitness(osBitness);
	}
	
	public String getOsManufacturer() {
		return _osManufacturer;
	}

	public String getOsFamily() {
		return _osFamily;
	}

	public String getOsVersionInfo() {
		return _osVersionInfo;
	}

	public String getOsBitness() {
		return _osBitness;
	}

	public void setOsManufacturer(String osManufacturer) {
		_osManufacturer = osManufacturer;
	}

	public void setOsFamily(String osFamily) {
		_osFamily = osFamily;
	}

	public void setOsVersionInfo(String osVersionInfo) {
		_osVersionInfo = osVersionInfo;
	}

	public void setOsBitness(String osBitness) {
		_osBitness = osBitness;
	}
}
