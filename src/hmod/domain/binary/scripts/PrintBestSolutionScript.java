
package hmod.domain.binary.scripts;

import flexbuilders.core.BuildException;
import hmod.parser.builders.SingleGraphScript;
import flexbuilders.graph.NodeBuilder;
import flexbuilders.graph.GraphHandler;
import hmod.domain.binary.components.PrintBestSolution;
import static hmod.parser.builders.AlgorithmBuilders.*;

/**
 *
 * @author Enrique Urra C.
 */
public class PrintBestSolutionScript extends SingleGraphScript
{
    public PrintBestSolutionScript(GraphHandler input)
    {
        super(input);
    }
    
    @Override
    public void process() throws BuildException
    {
        NodeBuilder step2 = node();
        
        branch("binExample_printBestSolution").value(
            subProcessStep().setNextStep(step2).
            setSubStep(ref("binExample_initSolution"))
        );
        
        step2.value(
            sequentialStep().
            addOperator(
                operator(PrintBestSolution.class).setData(ref("binExample_data"))
            )
        );
    }
}
