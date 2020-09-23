package agent.model.os;

import agent.model.os.interfaces.IOperationSystem;
import oshi.SystemInfo;
import oshi.software.os.OperatingSystem;

/**
 * @author Yaroslav
 */

public final class OperationSystem implements IOperationSystem {
	
	private static OperationSystem INSTANCE;

	private static String _osManufacturer;
	private static String _osFamily;
	private static String _osVersionInfo;
	private static int _osBitness;

	public OperationSystem() {
		INSTANCE = this;
	}
	
	public OperationSystem load(SystemInfo sysInfo)
	{
		OperatingSystem os = sysInfo.getOperatingSystem();
		
		_osManufacturer = os.getManufacturer();
		_osFamily = os.getFamily();
		_osVersionInfo = os.getVersionInfo().getVersion();
		_osBitness = os.getBitness();
		
		return this;
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

	public int getOsBitness() {
		return _osBitness;
	}

	public static OperationSystem getInstance() {
		return INSTANCE;
	}
}
