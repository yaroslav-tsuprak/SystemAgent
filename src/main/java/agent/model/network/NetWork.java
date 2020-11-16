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

		sysInfo.getHardware().getNetworkIFs().forEach(n -> {
			_adaptersNames.join(n.getDisplayName());
			_ipAddress.join(Arrays.toString(n.getIPv4addr()));
			_macAddress.join(n.getMacaddr());
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
