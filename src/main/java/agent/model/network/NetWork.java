package agent.model.network;

import java.util.Arrays;

import agent.model.network.interfaces.INetWork;
import oshi.SystemInfo;
import oshi.software.os.NetworkParams;

/**
 * @author Yaroslav
 */

public final class NetWork implements INetWork {
	private static String _hostName;
	private static String _domainName;
	private static String _adaptersNames;
	private static String _ipAddress;
	private static String _macAddress;
	
	public NetWork(SystemInfo sysInfo) {
		load(sysInfo);
	}
	
	public void load(SystemInfo sysInfo) {
		NetworkParams net = sysInfo.getOperatingSystem().getNetworkParams();
		_hostName = net.getHostName();
		_domainName = net.getDomainName();

		sysInfo.getHardware().getNetworkIFs().forEach(n -> setAllNetworkParameters(n.getDisplayName(), Arrays.toString(n.getIPv4addr()), n.getMacaddr()));
	}

	public void setAllNetworkParameters(String adapterName, String ipAddress, String macAddress) {
		_adaptersNames = _adaptersNames + " " + adapterName;
		_ipAddress = _ipAddress + " " + ipAddress;
		_macAddress = _macAddress + " " + macAddress;
	}

	@Override
	public String getDomainName() {
		return _domainName;
	}

	@Override
	public String getAdaptersNames() {
		return _adaptersNames;
	}

	@Override
	public String getIpAddress() {
		return _ipAddress;
	}

	@Override
	public String getMacAddress() {
		return _macAddress;
	}
}
