
package hmod.test.scripts.core;

import hmod.test.TestFinishOperator;
import static hmod.parser.builders.AlgorithmBuilders.*;
import flexbuilders.core.BuildException;
import flexbuilders.core.NestedBuilder;
import flexbuilders.scripting.TreeBuildScript;
import flexbuilders.tree.BranchBuilder;
import flexbuilders.tree.TreeHandler;
import hmod.common.heuristic.components.UpdateIterationCount;

/**
 *
 * @author Enrique Urra C.
 */
public class TestA1BScript extends TreeBuildScript
{
    private BranchBuilder start, end;
    private NestedBuilder data, iterationStart;

    public TestA1BScript(TreeHandler input) throws BuildException
    {
        super(input);
        
        start = branch("nextScriptStart");
        end = branch();
        data = ref("data");
        iterationStart = ref("iterationStart");
    }
    
    @Override
    public void process() throws BuildException
    {
        start.setBuildable(
            decisionStep().setTrueStep(end).setFalseStep(iterationStart).
            setDecider(
                operator(UpdateIterationCount.class).setData(data)
            )
        );
        
        end.setBuildable(
            sequentialStep().
            addOperator(operator(TestFinishOperator.class))
        );
    }
}