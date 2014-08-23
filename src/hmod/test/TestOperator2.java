
package hmod.test;

import hmod.core.Operator;
import hmod.core.AlgorithmException;
import optefx.util.output.OutputManager;

public class TestOperator2 implements Operator
{
    private int[] elems;
    
    @Override
    public void execute() throws AlgorithmException
    {
        String elemsStr = "";
        
        if(elems != null)
        {
            for(int i = 0; i < elems.length; i++)
                elemsStr += " " + elems[i];
        }
        
        OutputManager.println("test", "Prueba!" + elemsStr);
    }

    public void setElems(int[] elems)
    {
        this.elems = elems;
    }
}
