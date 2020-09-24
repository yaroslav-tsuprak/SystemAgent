package agent.model;

import agent.model.hardware.Bios;
import agent.model.hardware.Cpu;
import agent.model.hardware.Disk;
import agent.model.hardware.GraphicsCards;
import agent.model.hardware.Memory;
import agent.model.hardware.MotherBoard;
import agent.model.hardware.Usb;
import agent.model.network.NetWork;
import agent.model.os.OperationSystem;
import oshi.SystemInfo;

/**
 * @author Yaroslav
 */

public final class Computer {
	
	private static int _id;
	private static String _hashId;

	private static OperationSystem _osInformation;
	private static NetWork _networkInformation;
	private static Bios _biosInformation;
	private static Cpu _cpuInformation;
	private static Disk _diskInformation;
	private static MotherBoard _motherboardInformation;
	private static Usb _usbInformation;
	private static GraphicsCards _graphicsCard;
	private static Memory _memory;

	private Computer() {
		SystemInfo sysInfo = new SystemInfo();		
		_osInformation = new OperationSystem(sysInfo);
		_networkInformation = new NetWork(sysInfo);
		_biosInformation = new Bios(sysInfo);
		_cpuInformation = new Cpu(sysInfo);
		_diskInformation = new Disk(sysInfo);
		_motherboardInformation = new MotherBoard(sysInfo);
		_usbInformation = new Usb(sysInfo);
		_graphicsCard = new GraphicsCards(sysInfo);
		_memory = new Memory(sysInfo);
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
	
	public GraphicsCards getGraphicCardInfo() {
		return _graphicsCard;
	}
	
	public Memory getMemoryInfo() {
		return _memory;
	}
	
	public int getComputerId() {
		return _id;
	}

	public String getComputerHashId() {
		return _hashId;
	}

	public static Computer getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder
	{
		protected static final Computer INSTANCE = new Computer();
	}
}
