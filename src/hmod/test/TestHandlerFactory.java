
package hmod.test;

import hmod.loader.graph.ArgumentList;
import hmod.loader.graph.ArgumentInfo;

/**
 *
 * @author Enrique Urra C.
 */
public final class TestHandlerFactory
{
    private static TestHandlerFactory instance;

    public static TestHandlerFactory getInstance()
    {
        if(instance == null)
            instance = new TestHandlerFactory();
        
        return instance;
    }

    private TestHandlerFactory()
    {
    }
    
    @ArgumentInfo(type = Integer.class)
    public TestHandler test(ArgumentList list)
    {
        int prueba = list.next(Integer.class);
        return new DefaultTestHandler(prueba);
    }
}
