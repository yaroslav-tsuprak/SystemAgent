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
		final ComputerParameters computerOnline = new ComputerParameters(new Computer());
		final String _computerHashId = computerOnline.getParamSet().getString("computer_hash_id");
		final ParamsSet computerParamSet = computerOnline.getParamSet();
		final ComputerParameters computerFromDatabase = ComputersTable.getInstance().selectComputerWithParams(_computerHashId);
		if (computerFromDatabase != null) {
			ParamsSet computerDiff = new ComputersEquality(computerOnline, computerFromDatabase).getDiff();
			if (!computerDiff.isEmpty() || computerDiff != null) {
				ComputersTable.getInstance().saveComputerDiff(computerParamSet, computerDiff);
			}
		} else {
			ComputersTable.getInstance().createComputerWithParameters(_computerHashId, computerParamSet);
		}
	}
}
