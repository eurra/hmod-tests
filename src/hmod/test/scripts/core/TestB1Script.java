
package hmod.test.scripts.core;

import hmod.test.DummyClass;
import hmod.test.TestInitOperator;
import hmod.test.TestOperator;
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

public class TestB1Script extends TreeBuildScript
{    
    private BranchBuilder data, param, baseDummy, start, doTest, checkIteration, end;

    public TestB1Script(TreeHandler input) throws BuildException
    {
        super(input);
        
        data = branch();
        param = branch();
        baseDummy = branch();
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
            valueIn("maxIteration", param)
        );
        
        param.setBuildable(value(7));
        
        baseDummy.setBuildable(
            setFor(object(DummyClass.class)).
            valueIn("num", value(4))
        );
        
        start.setBuildable(
            sequentialStep().setNextStep(doTest).
            addOperator(operator(TestInitOperator.class))
        );
        
        doTest.setBuildable(
            sequentialStep().setNextStep(checkIteration).
            addOperator(
                setFor(operator(TestOperator.class)).
                valueIn("elems", array(DummyClass.class).
                    elem(0, setFor(object(DummyClass.class)).valueIn("num", value(1))).
                    elem(1, baseDummy).
                    elem(2, baseDummy).
                    elem(4, baseDummy).
                    elem(3, setFor(object(DummyClass.class)).valueIn("num", value(3)))
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
    }
}