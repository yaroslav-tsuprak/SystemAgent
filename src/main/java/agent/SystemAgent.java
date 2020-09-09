package agent;

import agent.model.Computer;
import agent.model.os.Linux;
import agent.model.os.OperationSystem;
import agent.model.os.Windows;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

/**
 * Hardware system agent
 * 
 * @author Yaroslav
 */

public class SystemAgent {
	
	private static OperationSystem _osType;

	public static void main(String[] args) {

		SystemInfo si = new SystemInfo();
		OperatingSystem os = si.getOperatingSystem();

		if (os.getFamily().contains("Windows"))
		{
			_osType = new Windows();
		}
		else if (os.getFamily().contains("Linux"))
		{
			_osType = new Linux();
		}
		
		_osType.setOsManufacturer(os.getManufacturer());
		_osType.setOsFamily(os.getFamily());
		_osType.setOsVersionInfo(os.getVersionInfo().getVersion());
		_osType.setOsBitness(Integer.toString(os.getBitness()));
		
		Computer comp = Computer.getInstance();
		comp.storeOperationSystem(_osType);

		System.out.println(comp.getOperationSystemInformation().getOsManufacturer());

//		// Network
//		System.out.println(os.getNetworkParams());
//
//		HardwareAbstractionLayer hw = si.getHardware();
//		// PC
//		System.out.println(hw.getComputerSystem());
//		// Hardware
//		System.out.println(hw.getDiskStores());
//		System.out.println(hw.getDisplays());
//		System.out.println(hw.getGraphicsCards());
//		System.out.println(hw.getMemory());
//		System.out.println(hw.getUsbDevices(true));
//		CentralProcessor cpu = hw.getProcessor();
//		System.out.println(cpu.getLogicalProcessorCount());

	}
}
