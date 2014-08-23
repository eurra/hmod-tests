
package hmod.domain.binary.components;

import hmod.core.Operator;
import hmod.core.AlgorithmException;
import hmod.domain.binary.BinarySolution;
import optefx.util.output.OutputManager;

public abstract class BinaryConversion implements Operator
{
    private BinarySolutionHandler solutionHandler;

    public void setSolutionData(BinarySolutionHandler solutionData)
    {
        this.solutionHandler = solutionData;
    }

    @Override
    public void execute() throws AlgorithmException
    {        
        BinarySolution currSol = solutionHandler.getInputSolution();
        BinarySolution newSol = new BinarySolution(currSol);
        convert(newSol);
        OutputManager.println(OutputIds.OUT_INFO, "Generated solution: " + newSol);
        solutionHandler.setInputSolution(newSol);
        BinarySolution bestSol = solutionHandler.getBestSolution();
        
        if(bestSol == null || bestSol.fitness() < newSol.fitness())
            solutionHandler.setBestSolution(newSol);
    }
    
    public abstract void convert(BinarySolution toConvert) throws AlgorithmException;
}
