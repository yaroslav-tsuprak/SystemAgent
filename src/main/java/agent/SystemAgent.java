package agent;

import agent.model.Computer;
import agent.model.os.Linux;
import agent.model.os.OperationSystem;
import agent.model.os.Windows;
import oshi.SystemInfo;
import oshi.software.os.OperatingSystem;

/**
 * Hardware system agent
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

//		System.out.println(comp.getOperationSystemInformation().getOsManufacturer());
//		System.out.println(comp.getOperationSystemInformation().getOsFamily());
//		System.out.println(comp.getOperationSystemInformation().getOsVersionInfo());
//		System.out.println(comp.getOperationSystemInformation().getOsBitness());
//		System.out.println(comp.getComputerHostName());
//		System.out.println(comp.getComputerFullHostName());

//
//		HardwareAbstractionLayer hw = si.getHardware();
//		// PC
//		System.out.println(hw.getComputerSystem().getManufacturer());
//		System.out.println(hw.getComputerSystem().getModel());
//		System.out.println(hw.getComputerSystem().getSerialNumber());

//		System.out.println(hw.getComputerSystem().getBaseboard());
//		System.out.println(hw.getComputerSystem().getFirmware().getReleaseDate());
//		// Hardware
//		System.out.println(hw.getDiskStores());
//		System.out.println(hw.getDisplays());
//		System.out.println(hw.getGraphicsCards());
//		System.out.println(hw.getMemory());
//		System.out.println(hw.getUsbDevices(true));

//		List<NetworkIF> net = hw.getNetworkIFs();

//		net.forEach(n -> {
//			System.out.println(n.getDisplayName());
//			System.out.println(Arrays.toString(n.getIPv4addr()));
//			System.out.println(n.getMacaddr());
//
//		});

//		hw.getDiskStores().forEach(d -> {
//			System.out.println(d.getName());
//			System.out.println(d.getModel());
//			System.out.println(d.getSerial());
//			System.out.println(d.getSize());
//		});
		
//		CentralProcessor cpu = hw.getProcessor();
//		System.out.println(cpu.getLogicalProcessorCount());  //Core 
//		System.out.println(cpu.getPhysicalProcessorCount()); //CPU
//		System.out.println(cpu.getProcessorIdentifier().getIdentifier());
//		System.out.println(cpu.getProcessorIdentifier().getName());
//		System.out.println(cpu.getProcessorIdentifier().getProcessorID());
//		System.out.println(cpu.getProcessorIdentifier().getVendor());
		
//		hw.getUsbDevices(false).forEach(usb -> {
//			System.out.println(usb.getName());
//			System.out.println(usb.getUniqueDeviceId());
//			System.out.println(usb.getVendor());
//		});;
	}
}
