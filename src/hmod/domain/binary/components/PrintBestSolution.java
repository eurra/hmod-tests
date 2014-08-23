
package hmod.domain.binary.components;

import hmod.core.Operator;
import hmod.core.AlgorithmException;
import optefx.util.output.OutputManager;

public class PrintBestSolution implements Operator
{
    private BinarySolutionHandler solutionHandler;

    public void setSolutionData(BinarySolutionHandler handler)
    {
        this.solutionHandler = handler;
    }
    
    @Override
    public void execute() throws AlgorithmException
    {
        OutputManager.println(OutputIds.RESULT_INFO, "Best solution: " + solutionHandler.getBestSolution());
    }
}
