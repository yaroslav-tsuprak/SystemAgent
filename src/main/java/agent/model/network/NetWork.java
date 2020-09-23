package agent.model.network;

import java.util.Arrays;
import java.util.List;

import agent.model.network.interfaces.INetWork;
import oshi.SystemInfo;
import oshi.hardware.NetworkIF;
import oshi.software.os.NetworkParams;

/**
 * @author Yaroslav
 */

public final class NetWork implements INetWork {
	
	private static NetWork INSTANCE;

	private static String _hostName;
	private static String _domainName;
	private static List<String> _adapterName;
	private static List<String> _ipAddress;
	private static String _macAddress;

	public NetWork() {
		INSTANCE = this;
	}
	
	public NetWork load(SystemInfo sysInfo) {
		NetworkParams net = sysInfo.getOperatingSystem().getNetworkParams();
		_hostName = net.getHostName();
		_domainName = net.getDomainName();

		sysInfo.getHardware().getNetworkIFs().forEach(n -> {
			_adapterName.add(n.getDisplayName());
			_ipAddress.add(Arrays.toString(n.getIPv4addr()));
			_macAddress = n.getMacaddr();
		});
		
		return this;
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
	
	public static NetWork getInstance() {
		return INSTANCE;
	}
}
