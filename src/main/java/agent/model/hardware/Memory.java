package agent.model.hardware;

import agent.model.hardware.interfaces.IMemory;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;

public class Memory implements IMemory {
	
	private static long _memoryTotal;
	private static String _memoryBankLabel;
	private static long _memoryCapacity;
	private static String _memoryManufacturer;
	private static String _memoryType;
	
	public Memory(SystemInfo sysInfo) {
		load(sysInfo);
	}
	
	public void load(SystemInfo sysInfo) {
		GlobalMemory memory = sysInfo.getHardware().getMemory();
		_memoryTotal = memory.getTotal();
		memory.getPhysicalMemory().forEach(m -> {
			_memoryBankLabel = m.getBankLabel();
			_memoryCapacity = m.getCapacity();
			_memoryManufacturer = m.getManufacturer();
			_memoryType = m.getMemoryType();
		});
	}
	
	@Override
	public String getMemoryManufacturer() {
		return _memoryManufacturer;
	}
	
	@Override
	public long getMemoryTotal() {
		return _memoryTotal;
	}

	@Override
	public String getMemoryBankLabel() {
		return _memoryBankLabel;
	}

	@Override
	public long getMemoryCapacity() {
		return _memoryCapacity;
	}

	@Override
	public String getMemoryType() {
		return _memoryType;
	}
}
