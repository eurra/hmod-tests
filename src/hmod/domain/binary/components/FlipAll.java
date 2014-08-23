
package hmod.domain.binary.components;

import hmod.core.AlgorithmException;
import hmod.domain.binary.BinarySolution;

public class FlipAll extends BinaryConversion
{
    @Override
    public void convert(BinarySolution toConvert) throws AlgorithmException
    {
        int length = toConvert.getLength();
        
        for(int i = 0; i < length; i++)
        {
            boolean currVal = toConvert.getVal(i);
            toConvert.setVal(i, !currVal);
        }
    }
}
