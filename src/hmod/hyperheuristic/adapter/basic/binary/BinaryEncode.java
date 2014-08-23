
package hmod.hyperheuristic.adapter.basic.binary;

import hmod.core.AlgorithmException;
import hmod.domain.binary.BinarySolution;
import hmod.solvers.hh.model.basicadapter.ac.EncodeSolutionOperator;

public class BinaryEncode extends EncodeSolutionOperator<HHBinarySolution, BinarySolution>
{
    @Override
    public HHBinarySolution encode(BinarySolution llSolution) throws AlgorithmException
    {
        return new HHBinarySolution(llSolution);
    }
}
