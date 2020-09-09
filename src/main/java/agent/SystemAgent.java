package agent;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

public class SystemAgent {

	public static void main(String[] args) {

		SystemInfo si = new SystemInfo();
		OperatingSystem os = si.getOperatingSystem();
		System.out.println(os.getBitness());
		System.out.println(os.getFamily());
		System.out.println(os.getVersionInfo());
		System.out.println(os.getManufacturer());
		System.out.println(os.getNetworkParams());
		
		HardwareAbstractionLayer hw = si.getHardware();
		System.out.println(hw.getComputerSystem());
		System.out.println(hw.getDiskStores());
		System.out.println(hw.getDisplays());
		System.out.println(hw.getGraphicsCards());
		System.out.println(hw.getMemory());
		System.out.println(hw.getUsbDevices(true));
		
		CentralProcessor cpu = hw.getProcessor();
		System.out.println(cpu.getLogicalProcessorCount());

	}
}
