
package hmod.test.scripts.core;

import static flexbuilders.basic.BasicBuilders.setFor;
import hmod.test.TestInitOperator;
import hmod.test.TestOperator;
import hmod.test.TestFinishOperator;
import static flexbuilders.basic.BasicBuilders.value;
import flexbuilders.core.BuildException;
import flexbuilders.scripting.TreeBuildScript;
import flexbuilders.tree.BranchBuilder;
import flexbuilders.tree.TreeHandler;
import hmod.common.heuristic.components.CheckIterationFinished;
import hmod.common.heuristic.components.UpdateIterationCount;
import hmod.common.heuristic.components.IterationData;
import hmod.common.heuristic.components.IterationHandler;
import hmod.common.heuristic.components.IterationHandlerFactory;
import hmod.common.heuristic.components.NextIteration;
import static hmod.parser.builders.AlgorithmBuilders.decisionStep;
import static hmod.parser.builders.AlgorithmBuilders.operator;
import static hmod.parser.builders.AlgorithmBuilders.sequentialStep;
import hmod.test.TestHandler;
import hmod.core.DataObjectProxy;
import static hmod.parser.builders.AlgorithmBuilders.dataProxy;
import hmod.test.TestHandlerFactory;

public class TestA1Script extends TreeBuildScript
{
    private BranchBuilder data, start, doTest, checkIteration, end;

    public TestA1Script(TreeHandler input) throws BuildException
    {
        super(input);
        
        data = branch();
        start = branch("!start");
        doTest = branch();
        checkIteration = branch();
        end = branch();
    }
    
    @Override
    public void process() throws BuildException
    {        
        data.setBuildable(
            dataProxy(TestHandler.class, IterationHandler.class).
            addFactory(TestHandlerFactory.getInstance(), value(10)).
            addFactory(IterationHandlerFactory.getInstance(), value(5))
        );
        
        start.setBuildable(
            sequentialStep().setNextStep(doTest).
            addOperator(operator(TestInitOperator.class))
        );
        
        doTest.setBuildable(
            sequentialStep().setNextStep(checkIteration).
            addOperator(
                operator(TestOperator.class).setData(data)
            ).
            addOperator(
                operator(NextIteration.class).setData(data)
            )
        );
        
        checkIteration.setBuildable(
            decisionStep().setTrueStep(end).setFalseStep(doTest).
            setDecider(
                operator(CheckIterationFinished.class).setData(data)
            )
        );
        
        end.setBuildable(
            sequentialStep().
            addOperator(operator(TestFinishOperator.class))
        );
    }
}
