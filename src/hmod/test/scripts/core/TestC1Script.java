
package hmod.test.scripts.core;

import static flexbuilders.basic.BasicBuilders.setFor;
import hmod.test.TestBOperator;
import hmod.test.TestInitOperator;
import hmod.test.TestFinishOperator;
import static flexbuilders.basic.BasicBuilders.value;
import flexbuilders.core.BuildException;
import flexbuilders.scripting.TreeBuildScript;
import flexbuilders.tree.BranchBuilder;
import flexbuilders.tree.TreeHandler;
import hmod.common.heuristic.components.UpdateIterationCount;
import hmod.common.heuristic.components.IterationData;
import static hmod.parser.builders.AlgorithmBuilders.decisionStep;
import static hmod.parser.builders.AlgorithmBuilders.operator;
import static hmod.parser.builders.AlgorithmBuilders.sequentialStep;
import hmod.test.TestHandler;
import hmod.core.DataObjectProxy;

public class TestC1Script extends TreeBuildScript
{   
    private BranchBuilder data, start, doTest, checkIteration, end;
    
    public TestC1Script(TreeHandler input) throws BuildException
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
        Object dataObj = DataObjectProxy.createFor(
            TestHandler.class,
            IterationData.class
        );
        
        data.setBuildable(
            setFor(value(dataObj)).
            valueIn("prueba", value(10)).
            valueIn("maxIteration", value(3))
        );
        
        start.setBuildable(
            sequentialStep().setNextStep(doTest).
            addOperator(operator(TestInitOperator.class))
        );
        
        doTest.setBuildable(
            sequentialStep().setNextStep(checkIteration).
            addOperator(
                operator(TestBOperator.class).setData(data)
            )
        );
        
        checkIteration.setBuildable(
            decisionStep().setTrueStep(end).setFalseStep(doTest).
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