package agent;

import agent.model.Computer;
import agent.model.ComputerParameters;
import agent.model.ComputersEquality;
import agent.model.hardware.*;
import agent.model.network.NetWork;
import agent.model.os.OperationSystem;
import agent.sql.impl.ComputersTable;
import agent.utils.ParamsSet;

import java.util.Map;

/**
 * Hardware system agent
 * @author Yaroslav
 */

public class SystemAgent {

	public static void main(String[] args) {
		ComputerParameters computerOnline = new ComputerParameters(new Computer());
		ComputerParameters computerFromDatabase = ComputersTable.getInstance().restore(computerOnline.getParamSet().getInt("hash_id"));
		if (computerFromDatabase != null) {
			ParamsSet computerDiff = new ComputersEquality(computerOnline, computerFromDatabase).getDiff();
			if (!computerDiff.isEmpty() || computerDiff != null) {
				ComputersTable.getInstance().store(computerDiff);
			}
		} else {
			ComputersTable.getInstance().create(computerOnline.getParamSet());
		}
	}
}
