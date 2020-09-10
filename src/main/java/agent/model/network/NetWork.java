package agent.model.network;

import java.util.List;

import agent.model.network.interfaces.INetWork;

/**
 * @author Yaroslav
 */

public final class NetWork implements INetWork {

	private static String _hostName;
	private static String _domainName;
	private static List<String> _adapterName;
	private static List<String> _ipAddress;
	private static String _macAddress;

	public NetWork() {
		
	}
	
	public NetWork(String hostName, String domainName, List<String> adapterName, List<String> ipAddress, String macAddress) {
		_hostName = hostName;
		_domainName = domainName;
		_adapterName.addAll(adapterName);
		_ipAddress.addAll(ipAddress);
		_macAddress = macAddress;
	}

	@Override
	public String getHostName() {
		return _hostName;
	}

	@Override
	public String getDomainName() {
		return _domainName;
	}

	@Override
	public List<String> getAdapterName() {
		return _adapterName;
	}

	@Override
	public List<String> getIpAddress() {
		return _ipAddress;
	}

	@Override
	public String getMacAddress() {
		return _macAddress;
	}

	@Override
	public void setHostName(String hostName) {
		_hostName = hostName;
	}

	@Override
	public void setDomainName(String domainName) {
		_domainName = domainName;
	}

	@Override
	public void setAdapterName(List<String> adapterName) {
		_adapterName.addAll(adapterName);
	}

	@Override
	public void setIpAddress(List<String> ipAddress) {
		_ipAddress.addAll(ipAddress);
	}

	@Override
	public void setMacAddress(String macAddress) {
		_macAddress = macAddress;
	}
}
