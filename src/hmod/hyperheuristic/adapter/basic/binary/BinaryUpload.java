
package hmod.hyperheuristic.adapter.basic.binary;

import hmod.domain.binary.BinarySolution;
import hmod.domain.binary.components.BinarySolutionHandler;
import hmod.solvers.hh.model.basicadapter.ac.UploadSolutionOperator;

/**
 *
 * @author Enrique Urra C.
 */
public class BinaryUpload extends UploadSolutionOperator<BinarySolution>
{
    private BinarySolutionHandler binarySolutionData;

    public void setBinarySolutionData(BinarySolutionHandler binarySolutionData)
    {
        this.binarySolutionData = binarySolutionData;
    }

    @Override
    public BinarySolution upload()
    {
        return binarySolutionData.getInputSolution();
    }
}
