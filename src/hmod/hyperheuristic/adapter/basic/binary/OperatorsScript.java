
package hmod.hyperheuristic.adapter.basic.binary;

import static hmod.parser.builders.AlgorithmBuilders.*;
import flexbuilders.core.BuildException;
import hmod.parser.builders.SingleGraphScript;
import flexbuilders.graph.GraphHandler;

/**
 *
 * @author Enrique Urra C.
 */
public class OperatorsScript extends SingleGraphScript
{
    public OperatorsScript(GraphHandler input)
    {
        super(input);
    }
    
    @Override
    public void process() throws BuildException
    {
        branch("binExample_baseAdapter_decoder").value(
            operator(BinaryDecode.class)
        );
        
        branch("binExample_baseAdapter_encoder").value(
            operator(BinaryEncode.class)
        );
            
        branch("binExample_baseAdapter_downloader").value(
            operator(BinaryDownload.class).setData(ref("binExample_data"))
        );
        
        branch("binExample_baseAdapter_uploader").value(
            operator(BinaryUpload.class).setData(ref("binExample_data"))
        );
    }
}
