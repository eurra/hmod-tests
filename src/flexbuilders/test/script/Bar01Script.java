
package flexbuilders.test.script;

import flexbuilders.core.BuildException;
import flexbuilders.graph.GraphHandler;
import hmod.loader.graph.SingleGraphScript;

/**
 *
 * @author Enrique Urra C.
 */
public class Bar01Script extends SingleGraphScript
{
    public Bar01Script(GraphHandler input)
    {
        super(input);
    }
    
    @Override
    public void process() throws BuildException
    {
        node(TestId.SOME_BAR).value(
            bar().
            setName("Some bar").
            setLuckyNumber(7).
            setSpecialFoo(ref(TestId.SPECIAL_FOO)).
            addCommonFoo(
                foo().
                setText("I'm some foo")
            ).
            addCommonFoo(ref(TestId.SPECIAL_FOO)).
            addCommonFoo(
                foo().
                setText("I'm a third foo")
            )
        );
    }
}
