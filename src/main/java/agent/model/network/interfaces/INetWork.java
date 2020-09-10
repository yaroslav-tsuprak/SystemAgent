package agent.model.network.interfaces;

import java.util.List;

/**
 * @author Yaroslav
 */

public interface INetWork {

	String getHostName();

	String getDomainName();
	
	List<String> getAdapterName();
	
	List<String> getIpAddress();
	
	String getMacAddress();
	
	void setHostName(String hostName);

	void setDomainName(String domainName);
	
	void setAdapterName(List<String> adapterName);
	
	void setIpAddress(List<String> ipAddress);
	
	void setMacAddress(String macAddress);
}
