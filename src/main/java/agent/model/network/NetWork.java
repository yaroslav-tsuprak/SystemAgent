package agent.model.network;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import agent.model.network.interfaces.INetWork;
import oshi.SystemInfo;
import oshi.software.os.NetworkParams;

/**
 * @author Yaroslav
 */

public final class NetWork implements INetWork {
	private static String _hostName;
	private static String _domainName;
	private static List<String> _adaptersNames = new ArrayList<String>();
	private static List<String> _ipAddress = new ArrayList<String>();
	private static List<String> _macAddress = new ArrayList<String>();
	
	public NetWork(SystemInfo sysInfo) {
		load(sysInfo);
	}
	
	public void load(SystemInfo sysInfo) {
		NetworkParams net = sysInfo.getOperatingSystem().getNetworkParams();
		_hostName = net.getHostName();
		_domainName = net.getDomainName();

		sysInfo.getHardware().getNetworkIFs().forEach(n -> {
			_adaptersNames.add(n.getDisplayName());
			_ipAddress.add(Arrays.toString(n.getIPv4addr()));
			_macAddress.add(n.getMacaddr());
		});
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
	public List<String> getAdaptersNames() {
		return _adaptersNames;
	}

	@Override
	public List<String> getIpAddress() {
		return _ipAddress;
	}

	@Override
	public List<String> getMacAddress() {
		return _macAddress;
	}
}
