
package hmod.test;

import hmod.core.Operator;
import hmod.core.AlgorithmException;
import optefx.util.output.OutputManager;

public class TestBOperator implements Operator
{
    private DummyClass[] elems;
    private TestHandler testHandler;
    
    public void setTestData(TestHandler testHandler)
    {
        this.testHandler = testHandler;
    }
    
    public void setElems(DummyClass[] elems)
    {
        this.elems = elems;
    }
    
    @Override
    public void execute() throws AlgorithmException
    {
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
        
        OutputManager.println("test", "Prueba 2! " + elemsStr);
    }
}
