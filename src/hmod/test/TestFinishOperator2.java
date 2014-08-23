
package hmod.test;

import hmod.core.Operator;
import hmod.core.AlgorithmException;
import optefx.util.output.OutputManager;

public class TestFinishOperator2 implements Operator
{
    @Override
    public void execute() throws AlgorithmException
    {
        OutputManager.println("test", "Finishing heuristic 2!");
    }
}
