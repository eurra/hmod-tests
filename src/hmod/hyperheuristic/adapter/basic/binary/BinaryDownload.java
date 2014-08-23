
package hmod.hyperheuristic.adapter.basic.binary;

import hmod.core.AlgorithmException;
import hmod.domain.binary.BinarySolution;
import hmod.domain.binary.components.BinarySolutionHandler;
import hmod.solvers.hh.model.basicadapter.ac.DownloadSolutionOperator;

/**
 *
 * @author Enrique Urra C.
 */
public class BinaryDownload extends DownloadSolutionOperator<BinarySolution>
{
    private BinarySolutionHandler binarySolutionData;

    public void setBinarySolutionData(BinarySolutionHandler binarySolutionData)
    {
        this.binarySolutionData = binarySolutionData;
    }

    @Override
    public void download(BinarySolution llSolution) throws AlgorithmException
    {
        binarySolutionData.setInputSolution(llSolution);
    }
}
