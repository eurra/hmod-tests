
package hmod.test.scripts.core;

import hmod.test.TestOperator2;
import hmod.test.TestInitOperator;
import hmod.test.TestFinishOperator;
import static flexbuilders.basic.BasicBuilders.*;
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

public class TestB2Script extends TreeBuildScript
{    
    private BranchBuilder data, param, testNum, start, doTest, checkIteration, end;

    public TestB2Script(TreeHandler input) throws BuildException
    {
        super(input);
        
        data = branch();
        param = branch();
        testNum = branch();
        start = branch("!start");
        doTest = branch();
        checkIteration = branch();
        end = branch();
    }
    
    @Override
    public void process() throws BuildException
    {
        testNum.setBuildable(value(6));
        
        start.setBuildable(
            sequentialStep().setNextStep(doTest).
            addOperator(operator(TestInitOperator.class))
        );
        
        doTest.setBuildable(
            sequentialStep().setNextStep(checkIteration).
            addOperator(
                setFor(operator(TestOperator2.class)).
                valueIn("elems", array(int.class).
                    elem(0, testNum).
                    elem(1, value(2)).
                    elem(2, testNum).
                    elem(4, value(3)).
                    elem(3, testNum)
                )
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
        
        Object dataObj = DataObjectProxy.createFor(
            TestHandler.class,
            IterationData.class
        );
        
        data.setBuildable(
            setFor(value(dataObj)).
            valueIn("maxIteration", param)
        );
        
        param.setBuildable(value(3));
    }
}