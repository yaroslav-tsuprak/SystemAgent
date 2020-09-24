package agent.model.hardware;

import agent.model.hardware.interfaces.ICpu;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.ProcessorIdentifier;

/**
 * @author Yaroslav
 */

public final class Cpu implements ICpu {
	
	private static String _cpuName;
	private static String _cpuVendor;
	private static int _logicalCpuCount;
	private static int _physicalCpuCount;
	private static String _cpuIdentifier;
	private static String _cpuId;
	
	public Cpu(SystemInfo sysInfo) {
		load(sysInfo);
	}
	
	public void load(SystemInfo sysInfo) {
		CentralProcessor cpu = sysInfo.getHardware().getProcessor();
		ProcessorIdentifier cpuIdentifier = cpu.getProcessorIdentifier();
		_cpuName = cpuIdentifier.getName();
		_cpuVendor = cpuIdentifier.getVendor();
		_logicalCpuCount = cpu.getLogicalProcessorCount();
		_physicalCpuCount = cpu.getPhysicalPackageCount();
		_cpuIdentifier = cpuIdentifier.getIdentifier();
		_cpuId = cpuIdentifier.getProcessorID();
	}

	@Override
	public String getCpuName() {
		return _cpuName;
	}
	
	@Override
	public String getCpuVendor() {
		return _cpuVendor;
	}
	
	@Override
	public int getLogicalCpuCount() {
		return _logicalCpuCount;
	}

	@Override
	public int getPhysicalCpuCount() {
		return _physicalCpuCount;
	}

	@Override
	public String getCpuIdentifier() {
		return _cpuIdentifier;
	}

	@Override
	public String getCpuId() {
		return _cpuId;
	}
}
