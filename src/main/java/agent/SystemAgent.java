package agent;

import agent.model.Computer;
import agent.model.ComputerParameters;
import agent.utils.ComputersEquality;
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
		final ParamsSet computerOnlineParamSet = computerOnline.getParamSet();
		// Looking PC in db
		if (ComputersTable.getInstance().selectComputer(_computerHashId)) {
			// Have this PC then get all PC's parameters
			final ComputerParameters computerFromDatabase = ComputersTable.getInstance().selectComputerParameters(_computerHashId);
			if (computerFromDatabase != null) {
				// Looking for differences
				ParamsSet computerDiff = new ComputersEquality(computerOnline, computerFromDatabase).getDiff();
				// If we have differences
				if (!computerDiff.isEmpty()) {
					// Save diff to db
					ComputersTable.getInstance().saveComputerDiff(_computerHashId, computerDiff);
				}
			} else {
				// Save all PC's parameters
				ComputersTable.getInstance().createComputerParameters(computerOnlineParamSet);
			}
		} else {
			// Create a new PC with all parameters
			ComputersTable.getInstance().createComputerWithParameters(_computerHashId, computerOnlineParamSet);
		}
		// Refresh PC's last active
		ComputersTable.getInstance().saveLastActive(_computerHashId);
	}
}
