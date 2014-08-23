
package hmod.domain.binary.scripts;

import flexbuilders.core.BuildException;
import hmod.parser.builders.SingleGraphScript;
import flexbuilders.graph.NodeBuilder;
import flexbuilders.graph.GraphHandler;
import hmod.domain.binary.components.RandomMutate;
import static hmod.parser.builders.AlgorithmBuilders.operator;
import static hmod.parser.builders.AlgorithmBuilders.sequentialStep;
import static hmod.parser.builders.AlgorithmBuilders.subProcessStep;

/**
 *
 * @author Enrique Urra C.
 */
public class RandomMutateScript extends SingleGraphScript
{
    public RandomMutateScript(GraphHandler input)
    {
        super(input);
    }
    
    @Override
    public void process() throws BuildException
    {
        NodeBuilder step2 = node();
        
        branch("binExample_randomMutate").value(
            subProcessStep().setNextStep(step2).
            setSubStep(ref("binExample_initSolution"))
        );
        
        step2.value(
            sequentialStep().
            addOperator(
                operator(RandomMutate.class).setData(ref("binExample_data"))
            )
        );
    }
}
