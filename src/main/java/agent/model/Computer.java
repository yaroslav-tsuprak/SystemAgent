package agent.model;

import agent.model.hardware.Bios;
import agent.model.hardware.Cpu;
import agent.model.hardware.Disk;
import agent.model.hardware.MotherBoard;
import agent.model.hardware.Usb;
import agent.model.network.NetWork;
import agent.model.os.OperationSystem;
import oshi.SystemInfo;

/**
 * @author Yaroslav
 */

public final class Computer {
	
	private static Computer INSTANCE;
	
	private static int _id;
	private static String _hashId;

	private static OperationSystem _osInformation;
	private static NetWork _networkInformation;
	private static Bios _biosInformation;
	private static Cpu _cpuInformation;
	private static Disk _diskInformation;
	private static MotherBoard _motherboardInformation;
	private static Usb _usbInformation;

	public Computer() {
		INSTANCE = this;
		SystemInfo sysInfo = new SystemInfo();		
		_osInformation = OperationSystem.getInstance().load(sysInfo);
		_networkInformation = NetWork.getInstance().load(sysInfo);
		_biosInformation = Bios.getInstance().load(sysInfo);
		_cpuInformation = Cpu.getInstance().load(sysInfo);
		_diskInformation = Disk.getInstance().load(sysInfo);
		_motherboardInformation = MotherBoard.getInstance().load(sysInfo);
		_usbInformation = Usb.getInstance().load(sysInfo);
		// TODO
		_id = 0;
		_hashId = "0";
	}

	public OperationSystem getOperationSystemInfo() {
		return _osInformation;
	}
	
	public NetWork getNetworkInfo() {
		return _networkInformation;
	}

	public Bios getBiosInfo() {
		return _biosInformation;
	}
	
	public Cpu getCpuInfo() {
		return _cpuInformation;
	}
	
	public Disk getDiskInfo() {
		return _diskInformation;
	}
	
	public MotherBoard getMotherboardInfo() {
		return _motherboardInformation;
	}
	
	public Usb getUsbInfo() {
		return _usbInformation;
	}
	
	public int getComputerId() {
		return _id;
	}

	public String getComputerHashId() {
		return _hashId;
	}

	public static Computer getInstance() {
		return INSTANCE;
	}
}
