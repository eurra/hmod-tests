
package hmod.test.scripts.common;

import static flexbuilders.basic.BasicBuilders.builderFor;
import flexbuilders.core.BuildException;
import flexbuilders.core.NestedBuilder;
import flexbuilders.graph.GraphHandler;
import static hmod.loader.graph.AlgorithmBuilders.algorithmBlock;
import static hmod.loader.graph.AlgorithmBuilders.argFactory;
import hmod.loader.graph.SingleGraphScript;
import hmod.test.TestHandlers;
import hmod.test.TestOperators;

public class TestHeuristicScript extends SingleGraphScript
{
    public TestHeuristicScript(GraphHandler input) throws BuildException
    {
        super(input);
    }

    @Override
    public void process() throws BuildException
    {
        NestedBuilder testData = argFactory(TestHandlers::test, builderFor(0));
        
        node(TestId.INIT).value(
            algorithmBlock().run(TestOperators::testInit)
        );
        
        node(TestId.ITERATION).value(
            algorithmBlock().run(TestOperators::test, testData)
        );
        
        node(TestId.FINISH).value(
            algorithmBlock().run(TestOperators::testFinish)
        );
    }
}
