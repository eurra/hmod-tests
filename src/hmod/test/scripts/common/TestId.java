
package hmod.test.scripts.common;

import flexbuilders.graph.NodeId;
import flexbuilders.graph.NodeType;
import hmod.core.Step;

/**
 *
 * @author Enrique Urra C.
 */
public enum TestId implements NodeId
{
    @NodeType(Step.class) INIT,
    @NodeType(Step.class) ITERATION,
    @NodeType(Step.class) FINISH
}
