package agent.model.network.interfaces;

import java.util.List;

/**
 * @author Yaroslav
 */

public interface INetWork {

	String getHostName();

	String getDomainName();
	
	List<String> getAdaptersNames();
	
	List<String> getIpAddress();
	
	List<String> getMacAddress();

}
