package agent;

import agent.model.Computer;

/**
 * Hardware system agent
 * @author Yaroslav
 */

public class SystemAgent {

	public static void main(String[] args) {
		Computer comp = Computer.getInstance();

		System.out.println("--- Computer Hash ID ---");
		System.out.println(comp.getComputerHashId());
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
