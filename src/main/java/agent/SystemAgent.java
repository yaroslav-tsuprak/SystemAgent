package agent;

import agent.model.Computer;
import agent.model.hardware.*;
import agent.model.network.NetWork;
import agent.model.os.OperationSystem;
import agent.sql.impl.ComputersTable;

import java.util.Map;

/**
 * Hardware system agent
 * @author Yaroslav
 */

public class SystemAgent {

	private static Map<String, String> changes = null;

	public static void main(String[] args) {
		Computer comp = Computer.getInstance();
		ComputersTable.ComputerEntry computerEntry = ComputersTable.getInstance().restore(comp.getComputerHashId());
		if (computerEntry != null) {
			comp.getHardwareList().forEach(o -> {
				if (o instanceof OperationSystem) {
					((OperationSystem) o).getParamsList().forEach(param -> {
						if (!param.equals(computerEntry.getOSFullName())) {
							changes.put("os_name", param);
						}
					});
				}
				if (o instanceof NetWork) {

				}
				if (o instanceof Bios) {

				}
				if (o instanceof Cpu) {

				}
				if (o instanceof Disk) {

				}
				if (o instanceof MotherBoard) {

				}
				if (o instanceof Usb) {

				}
				if (o instanceof GraphicsCards) {

				}
				if (o instanceof Memory) {

				}
			});
		}

		System.out.println("--- Computer Hash ID ---");
		System.out.println(comp.getComputerHashId());
		System.out.println("--- Computer OS ---");
		System.out.println(comp.getOperationSystemInfo().getOSFullName());
		System.out.println("--- BIOS ---");
		System.out.println(comp.getBiosInfo().getBiosDescription());
		System.out.println(comp.getBiosInfo().getBiosManufacturer());
		System.out.println(comp.getBiosInfo().getBiosName());
		System.out.println(comp.getBiosInfo().getBiosReleaseDate());
		System.out.println(comp.getBiosInfo().getBiosVersion());
		System.out.println("--- CPU ---");
		System.out.println(comp.getCpuInfo().getCpuId());
		System.out.println(comp.getCpuInfo().getCpuIdentifier());
		System.out.println(comp.getCpuInfo().getCpuName());
		System.out.println(comp.getCpuInfo().getCpuVendor());
		System.out.println(comp.getCpuInfo().getLogicalCpuCount());
		System.out.println(comp.getCpuInfo().getPhysicalCpuCount());
		System.out.println("--- MotherBoard ---");	
		System.out.println(comp.getMotherboardInfo().getMotherBoardManufacturer());
		System.out.println(comp.getMotherboardInfo().getMotherBoardModel());
		System.out.println(comp.getMotherboardInfo().getMotherBoardSerial());
		System.out.println(comp.getMotherboardInfo().getMotherBoardVersion());
		System.out.println("--- DISK ---");		
		System.out.println(comp.getDiskInfo().getDiskModel());
		System.out.println(comp.getDiskInfo().getDiskName());
		System.out.println(comp.getDiskInfo().getDiskSerial());
		System.out.println(comp.getDiskInfo().getDiskSize());
		System.out.println("--- USB ---");
		System.out.println(comp.getUsbInfo().getUsbName());
		System.out.println(comp.getUsbInfo().getUsbUniqueDeviceId());
		System.out.println(comp.getUsbInfo().getUsbVendor());
		System.out.println("--- GraphicsCards ---");
		System.out.println(comp.getGraphicCardInfo().getGraphicsCardName());
		System.out.println(comp.getGraphicCardInfo().getGraphicsCardVendor());
		System.out.println(comp.getGraphicCardInfo().getGraphicsCardVRam());
		System.out.println("--- MEMORY ---");
		System.out.println(comp.getMemoryInfo().getMemoryBankLabel());
		System.out.println(comp.getMemoryInfo().getMemoryCapacity());
		System.out.println(comp.getMemoryInfo().getMemoryManufacturer());
		System.out.println(comp.getMemoryInfo().getMemoryTotal());
		System.out.println(comp.getMemoryInfo().getMemoryType());
		System.out.println("--- NETWORK ---");
		comp.getNetworkInfo().getAdapterName().forEach(System.out::println);
		comp.getNetworkInfo().getIpAddress().forEach(System.out::println);
		comp.getNetworkInfo().getMacAddress().forEach(System.out::println);
		System.out.println(comp.getNetworkInfo().getDomainName());
	}
}
