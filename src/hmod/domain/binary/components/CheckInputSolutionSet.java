
package hmod.domain.binary.components;

import hmod.core.AlgorithmException;
import hmod.core.BooleanOperator;

public class CheckInputSolutionSet extends BooleanOperator
{
    private BinarySolutionHandler solutionData;

    public void setSolutionData(BinarySolutionHandler solutionData)
    {
        this.solutionData = solutionData;
    }

    @Override
    public Boolean evaluate() throws AlgorithmException
    {
        return solutionData.getInputSolution() != null;
    }
}
