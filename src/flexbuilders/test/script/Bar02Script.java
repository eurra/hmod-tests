
package flexbuilders.test.script;

import flexbuilders.core.BuildException;
import static tests.flexbuilders.TestBuilders.*;
import flexbuilders.graph.GraphHandler;
import hmod.loader.graph.SingleGraphScript;

/**
 *
 * @author Enrique Urra C.
 */
public class Bar02Script extends SingleGraphScript
{
    public Bar02Script(GraphHandler input)
    {
        super(input);
    }
    
    @Override
    public void process() throws BuildException
    {        
        node(TestId.SOME_FOO).value(
            foo().
            setText("I'm a referenced foo")
        );
        
        node(TestId.SPECIAL_FOO).value(
            foo().
            setText("I'm a special foo!")
        );
    }
}
