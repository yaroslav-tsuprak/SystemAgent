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
		// Looking PC in db
		final ComputerParameters computerFromDatabase = ComputersTable.getInstance().selectComputerWithParams(_computerHashId);
		// Have this PC
		if (!computerFromDatabase.getParamSet().isEmpty()) {
			// Looking for differences
			ParamsSet computerDiff = new ComputersEquality(computerOnline, computerFromDatabase).getDiff();
			// If we have differences
			if (!computerDiff.isEmpty()) {
				// Save diff to db
				ComputersTable.getInstance().saveComputerDiff(_computerHashId, computerDiff);
			}
			else
			{
				// Change last_active in db
				ComputersTable.getInstance().saveLastActive(_computerHashId);
			}
		} else {
			// Create new PC in db
			ComputersTable.getInstance().createComputerWithParameters(_computerHashId, computerParamSet);
		}
	}
}
