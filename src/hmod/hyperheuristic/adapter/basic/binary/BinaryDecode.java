
package hmod.hyperheuristic.adapter.basic.binary;

import hmod.core.AlgorithmException;
import hmod.domain.binary.BinarySolution;
import hmod.solvers.hh.model.basicadapter.ac.DecodeSolutionOperator;

public class BinaryDecode extends DecodeSolutionOperator<HHBinarySolution, BinarySolution>
{
    @Override
    public BinarySolution decode(HHBinarySolution solution) throws AlgorithmException
    {
        return solution.getInnerSolution();
    }
}
