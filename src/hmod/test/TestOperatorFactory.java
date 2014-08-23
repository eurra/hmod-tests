
package hmod.test;

import hmod.core.Operator;
import hmod.loader.graph.ArgumentFactory;
import hmod.loader.graph.ArgumentList;
import hmod.loader.graph.ArgumentInfo;
import optefx.util.output.OutputManager;

/**
 *
 * @author Enrique Urra C.
 */
public final class TestOperatorFactory
{    
    private static TestOperatorFactory instance;

    public static TestOperatorFactory getInstance()
    {
        if(instance == null)
            instance = new TestOperatorFactory();
        
        return instance;
    }

    private TestOperatorFactory()
    {
    }
    
    @ArgumentFactory
    public Operator testInit(ArgumentList args)
    {
        return () -> {
            OutputManager.println("test", "Initializing heuristic!");
        };
    }
    
    @ArgumentInfo(type = TestHandler.class)
    @ArgumentInfo(type = DummyClass[].class, optional = true)
    public Operator test(ArgumentList args)
    {
        TestHandler testHandler = args.next(TestHandler.class);
        DummyClass[] elems = args.next(DummyClass[].class, null);
        
        return () -> {
            String elemsStr = "";

            if(elems != null)
            {
                for(int i = 0; i < elems.length; i++)
                    elemsStr += " " + elems[i].getNum();
            }

            if(testHandler != null)
            {
                int prueba = testHandler.getPrueba();
                elemsStr += " (test existe!, prev: " + prueba + ")";
                testHandler.incrementPrueba();
            }

            OutputManager.println("test", "Prueba!" + elemsStr);
        };
    }
    
    @ArgumentFactory
    public Operator testFinish(ArgumentList args)
    {
        return () -> {
            OutputManager.println("test", "Finishing heuristic!");
        };
    }
}
