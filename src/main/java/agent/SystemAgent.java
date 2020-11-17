package agent;

import agent.model.Computer;
import agent.model.ComputerParameters;
import agent.model.ComputersEquality;
import agent.sql.impl.ComputersTable;
import agent.utils.ParamsSet;

/**
 * Hardware system agent
 * @author Yaroslav
 */

public class SystemAgent {

	public static void main(String[] args) {
		final ComputerParameters computerOnline = new ComputerParameters(new Computer());
		final int _computerHashId = computerOnline.getParamSet().getInt("computer_hash_id");
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
