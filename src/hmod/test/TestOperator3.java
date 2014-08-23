
package hmod.test;

import hmod.core.Operator;
import hmod.core.AlgorithmException;
import optefx.util.output.OutputManager;

public class TestOperator3 implements Operator
{
    private int[][] elems;
    
    @Override
    public void execute() throws AlgorithmException
    {
        String elemsStr = "";
        
        if(elems != null)
        {
            for(int i = 0; i < elems.length; i++)
                for(int j = 0; j < elems[i].length; j++)
                    elemsStr += " " + elems[i][j];
        }
        
        OutputManager.println("test", "Prueba!" + elemsStr);
    }

    public void setElems(int[][] elems)
    {
        this.elems = elems;
    }
}
