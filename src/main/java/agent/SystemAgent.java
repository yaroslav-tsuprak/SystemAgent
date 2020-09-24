package agent;

import agent.model.Computer;

/**
 * Hardware system agent
 * @author Yaroslav
 */

public class SystemAgent {

	public static void main(String[] args) {
		Computer comp = Computer.getInstance();
		System.out.println(comp.getOperationSystemInfo().getOsFamily());
	}
}
