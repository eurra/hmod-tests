
package hmod.domain.binary.components;

import hmod.core.DataHandler;
import hmod.domain.binary.BinarySolution;

public interface BinarySolutionHandler extends DataHandler
{    
    void setInputSolution(BinarySolution solution);
    BinarySolution getInputSolution();
    
    void setBestSolution(BinarySolution solution);
    BinarySolution getBestSolution();
}
