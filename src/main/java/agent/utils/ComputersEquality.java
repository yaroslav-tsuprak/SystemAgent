package agent.utils;

import agent.model.ComputerParameters;
import agent.utils.ParamsSet;

/**
 * @author Yaroslav
 */

public class ComputersEquality {

    private ParamsSet pcDiff = new ParamsSet();

    public ComputersEquality(ComputerParameters pcNow, ComputerParameters pcDB) {
        checkEquality(pcNow.getParamSet(), pcDB.getParamSet());
    }

    public void checkEquality(ParamsSet pcNow, ParamsSet pcDB) {
        pcNow.getMap().forEach((k, v) -> {
            if (!v.equals(pcDB.getMap().get(k))) {
                pcDiff.set(k.toString(), v);
            }
        });
    }

    public ParamsSet getDiff() {
        return pcDiff;
    }
}
