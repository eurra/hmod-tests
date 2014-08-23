
package hmod.domain.binary.components;

import hmod.core.Operator;
import hmod.core.AlgorithmException;
import hmod.domain.binary.BinarySolution;

public class InitSolution implements Operator
{
    private BinarySolutionHandler solutionHandler;

    public void setSolutionData(BinarySolutionHandler solutionHandler)
    {
        this.solutionHandler = solutionHandler;
    }
    
    @Override
    public void execute() throws AlgorithmException
    {
        if(solutionHandler == null)
            throw new NullPointerException("The solution handler has not been set");
        
        BinarySolution newSol = new BinarySolution(100, true);
        solutionHandler.setInputSolution(newSol);
    }
}
