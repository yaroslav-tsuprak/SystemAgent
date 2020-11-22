package agent.model.hardware.interfaces;

public interface IMemory {
	
	long getMemoryTotal();
	
	String getMemoryBankLabel();
	
	long getMemoryCapacity();
	
	String getMemoryManufacturer();
	
	String getMemoryType();
}
