
package hmod.domain.binary.scripts;

import hmod.domain.binary.components.BinarySolutionHandler;
import static flexbuilders.basic.BasicBuilders.*;
import flexbuilders.core.BuildException;
import hmod.parser.builders.SingleGraphScript;
import flexbuilders.graph.GraphHandler;
import hmod.core.DataObjectProxy;

/**
 *
 * @author Enrique Urra C.
 */
public class DataScript extends SingleGraphScript
{
    public DataScript(GraphHandler input)
    {
        super(input);
    }
    
    @Override
    public void process() throws BuildException
    {        
        branch("binExample_data").value(
            builderFor(DataObjectProxy.createFor(BinarySolutionHandler.class))
        );
    }
}
