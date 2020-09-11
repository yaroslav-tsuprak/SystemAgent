package agent.model.hardware;

import agent.model.hardware.interfaces.ICpu;

public class Cpu extends HardWare implements ICpu {

	public String getCpuName() {
		return super.getName();
	}
	
	public String getCpuVendor() {
		return super.getVendor();
	}
	
	@Override
	public String getLogicalCpuCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPhysicalCpuCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCpuIdentifier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCpuId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogicalCpuCount(String logicalCpuCount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPhysicalCpuCount(String physicalCpuCount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCpuIdentifier(String cpuIdentifier) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCpuId(String cpuId) {
		// TODO Auto-generated method stub
		
	}

}
