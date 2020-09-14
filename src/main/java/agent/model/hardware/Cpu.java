package agent.model.hardware;

import agent.model.hardware.interfaces.ICpu;

/**
 * @author Yaroslav
 */

public class Cpu extends HardWare implements ICpu {
	
	private static int _logicalCpuCount;
	private static int _physicalCpuCount;
	private static String _cpuIdentifier;
	private static String _cpuId;

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

	@Override
	public void setLogicalCpuCount(int logicalCpuCount) {
		_logicalCpuCount = logicalCpuCount;
	}

	@Override
	public void setPhysicalCpuCount(int physicalCpuCount) {
		_physicalCpuCount = physicalCpuCount;
	}

	@Override
	public void setCpuIdentifier(String cpuIdentifier) {
		_cpuIdentifier = cpuIdentifier;
	}

	@Override
	public void setCpuId(String cpuId) {
		_cpuId = cpuId;
	}
	
	public static Cpu getInstance() {
		return SingletonHolder.INSTANCE;
	}
	
	private static class SingletonHolder {
		protected static final Cpu INSTANCE = new Cpu();
	}
}
