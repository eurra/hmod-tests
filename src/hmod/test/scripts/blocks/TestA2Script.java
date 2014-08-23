
package hmod.test.scripts.blocks;

import static flexbuilders.basic.BasicBuilders.builderFor;
import flexbuilders.core.BuildException;
import flexbuilders.core.NestedBuilder;
import flexbuilders.graph.GraphHandler;
import static hmod.loader.graph.AlgorithmBuilders.algorithmBlock;
import static hmod.loader.graph.AlgorithmBuilders.NOT;
import static hmod.loader.graph.AlgorithmBuilders.argFactory;
import hmod.loader.graph.SingleGraphScript;
import hmod.solvers.common.ac.HeuristicHandlers;
import hmod.solvers.common.ac.HeuristicOperators;
import hmod.test.TestHandlers;
import hmod.test.TestOperators;

public class TestA2Script extends SingleGraphScript
{
    public TestA2Script(GraphHandler input) throws BuildException
    {
        super(input);
    }
    
    @Override
    public void process() throws BuildException
    {
        NestedBuilder iterationData = argFactory(HeuristicHandlers::iteration, builderFor(10));
        NestedBuilder testData = argFactory(TestHandlers::test, builderFor(0));
        
        root().value(
            algorithmBlock().
            run(TestOperators::testInit, testData).
            While(NOT(HeuristicOperators::checkIterationsFinished, iterationData)).
                run(TestOperators::test, testData).
                run(HeuristicOperators::nextIteration, iterationData).
            end().
            run(TestOperators::testFinish)
        );
    }
}
