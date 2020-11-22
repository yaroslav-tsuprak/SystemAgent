package agent.model.network.interfaces;

/**
 * @author Yaroslav
 */

public interface INetWork {

	String getDomainName();
	
	String getAdaptersNames();
	
	String getIpAddress();
	
	String getMacAddress();
}
