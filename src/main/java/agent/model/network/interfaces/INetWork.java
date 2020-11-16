package agent.model.network.interfaces;

import java.util.List;

/**
 * @author Yaroslav
 */

public interface INetWork {

	String getHostName();

	String getDomainName();
	
	String getAdaptersNames();
	
	String getIpAddress();
	
	String getMacAddress();

}
