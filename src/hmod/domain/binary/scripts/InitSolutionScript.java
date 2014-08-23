
package hmod.domain.binary.scripts;

import flexbuilders.core.BuildException;
import hmod.parser.builders.SingleGraphScript;
import flexbuilders.graph.NodeBuilder;
import flexbuilders.graph.GraphHandler;
import hmod.domain.binary.components.CheckInputSolutionSet;
import hmod.domain.binary.components.InitSolution;
import static hmod.parser.builders.AlgorithmBuilders.*;

/**
 *
 * @author Enrique Urra C.
 */
public class InitSolutionScript extends SingleGraphScript
{
    public InitSolutionScript(GraphHandler input)
    {
        super(input);
    }
    
    @Override
    public void process() throws BuildException
    {        
        NodeBuilder step2 = node();
        
        branch("binExample_initSolution").value(
            decisionStep().setFalseStep(step2).setCondition(
                operator(CheckInputSolutionSet.class).setData(ref("binExample_data"))
            )
        );
        
        step2.value(
            sequentialStep().
            addOperator(
                operator(InitSolution.class).setData(ref("binExample_data"))
            )
        );
    }
}
