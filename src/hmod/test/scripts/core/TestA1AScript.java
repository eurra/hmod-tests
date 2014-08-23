
package hmod.test.scripts.core;

import hmod.test.TestInitOperator;
import hmod.test.TestOperator;
import static flexbuilders.basic.BasicBuilders.*;
import flexbuilders.core.BuildException;
import flexbuilders.core.NestedBuilder;
import flexbuilders.tree.BranchBuilder;
import flexbuilders.tree.SingleTreeScript;
import flexbuilders.tree.TreeHandler;
import static hmod.common.heuristic.components.HeuristicHandlers.iterationHandler;
import static hmod.parser.builders.AlgorithmBuilders.*;
import static hmod.test.TestHandlers.testHandler;

public class TestA1AScript extends SingleTreeScript
{
    public TestA1AScript(TreeHandler input) throws BuildException
    {
        super(input);
        
        data = branch("data");
        start = branch("!start");
        iterationStart = branch("iterationStart");        
        nextScriptStart = ref("nextScriptStart");
    }
    
    @Override
    public void process() throws BuildException
    {
        NestedBuilder iterationData = iterationHandler(value(5));
        NestedBuilder testData = testHandler(value(0));
        
        start.value(
            sequentialStep().setNextStep(iterationStart).
            addOperator(operator(TestInitOperator.class))
        );
        
        iterationStart.value(
            sequentialStep().setNextStep(nextScriptStart).
            addOperator(
                operator(TestOperator.class).setData(data)
            )
        );
    }
}