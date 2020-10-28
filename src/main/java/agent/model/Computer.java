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

import java.util.List;

/**
 * @author Yaroslav
 */

public final class Computer {
	
	private static int _hashId;
	private static String _inventarNumber;

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
		_hashId = createComputerHashId();
	}

	public List<Object> getHardwareList() {
		return List.of(getOperationSystemInfo(), getNetworkInfo(), getBiosInfo(), getCpuInfo(), getDiskInfo(), getMotherboardInfo(), getUsbInfo(), getGraphicCardInfo(), getMemoryInfo());
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

	public int getComputerHashId() {
		return _hashId;
	}
	
	private final int createComputerHashId() {
		String code = _motherboardInformation.getMotherBoardManufacturer() + _cpuInformation.getCpuId() + _biosInformation.getBiosDescription() + _biosInformation.getBiosVersion();
		return code.hashCode();
	}

	public void setInventarNumber(String invNumber) {
		_inventarNumber = invNumber;
	}

	public String getInventarNumber() {
		return _inventarNumber;
	}

	public static Computer getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder
	{
		protected static final Computer INSTANCE = new Computer();
	}
}
