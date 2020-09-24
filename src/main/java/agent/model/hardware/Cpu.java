package agent.model.hardware;

import agent.model.hardware.interfaces.ICpu;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.ProcessorIdentifier;

/**
 * @author Yaroslav
 */

public final class Cpu extends HardWare implements ICpu {
	
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
		setCpuName(cpuIdentifier.getName());
		setCpuVendor(cpuIdentifier.getVendor());
		_logicalCpuCount = cpu.getLogicalProcessorCount();
		_physicalCpuCount = cpu.getPhysicalPackageCount();
		_cpuIdentifier = cpuIdentifier.getIdentifier();
		_cpuId = cpuIdentifier.getProcessorID();
	}

	public String getCpuName() {
		return super.getName();
	}
	
	public String getCpuVendor() {
		return super.getVendor();
	}
	
	public void setCpuName(String cpuName) {
		super.setName(cpuName);
	}
	
	public void setCpuVendor(String cpuVendor) {
		super.setVendor(cpuVendor);
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
