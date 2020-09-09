package agent.model;

import agent.model.os.Linux;
import agent.model.os.OperationSystem;
import agent.model.os.Windows;

/**
 * @author Yaroslav
 */

public class Computer {
	
	private static OperationSystem _osInformation;

	public Computer() {
		
	}
	
	public Computer(Object osType) {
		storeOperationSystem(osType);
	}
	
	
	public void storeOperationSystem(Object osType)
	{
		OperationSystem os = null;
		
		if (osType instanceof Windows)
		{
			os = new Windows();
			os.setOsManufacturer(((Windows) osType).getOsManufacturer());
			os.setOsFamily(((Windows) osType).getOsFamily());
			os.setOsVersionInfo(((Windows) osType).getOsVersionInfo());
			os.setOsBitness(((Windows) osType).getOsBitness());
		}
		else if (osType instanceof Linux)
		{
			os = new Linux();
			os.setOsManufacturer(((Linux) osType).getOsManufacturer());
			os.setOsFamily(((Linux) osType).getOsFamily());
			os.setOsVersionInfo(((Linux) osType).getOsVersionInfo());
			os.setOsBitness(((Linux) osType).getOsBitness());
		}
		_osInformation = os;
	}
	
	public OperationSystem getOperationSystemInformation() {
		return _osInformation;
	}
	
	public static Computer getInstance()
	{
		return SingletonHolder.INSTANCE;
	}
	
	private static class SingletonHolder
	{
		protected static final Computer INSTANCE = new Computer();
	}
}
