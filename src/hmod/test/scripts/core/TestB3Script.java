
package hmod.test.scripts.core;

import hmod.test.TestInitOperator;
import hmod.test.TestOperator3;
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

public class TestB3Script extends TreeBuildScript
{
    private BranchBuilder data, param, start, doTest, checkIteration, end;
    
    public TestB3Script(TreeHandler input) throws BuildException
    {
        super(input);
        
        data = branch();
        param = branch();
        start = branch("!start");
        doTest = branch();
        checkIteration = branch();
        end = branch();
    }
    
    @Override
    public void process() throws BuildException
    {
        start.setBuildable(
            sequentialStep().setNextStep(doTest).
            addOperator(operator(TestInitOperator.class))
        );
        
        doTest.setBuildable(
            sequentialStep().setNextStep(checkIteration).
            addOperator(
                setFor(operator(TestOperator3.class)).
                valueIn("elems", array(int[].class).
                    elem(0, array(int.class).setLength(5).
                        elem(0, value(5))
                    ).
                    elem(1, array(int.class).setLength(5).
                        elem(3, value(1)).
                        elem(0, value(2))
                    ).
                    elem(2, array(int.class).setLength(5).
                        elem(1, value(1))
                    ).
                    elem(4, array(int.class).setLength(5).
                        elem(4, value(3))
                    ).
                    elem(3, array(int.class).setLength(5).
                        elem(2, value(1))
                    )
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
        
        param.setBuildable(value(6));
    }
}