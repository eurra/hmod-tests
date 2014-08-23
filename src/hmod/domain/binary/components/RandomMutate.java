
package hmod.domain.binary.components;

import hmod.core.AlgorithmException;
import hmod.domain.binary.BinarySolution;
import optefx.util.random.RandomTool;

public class RandomMutate extends BinaryConversion
{
    @Override
    public void convert(BinarySolution toConvert) throws AlgorithmException
    {
        int length = toConvert.getLength();
        int randIndex = RandomTool.getInt(length);
        boolean currVal = toConvert.getVal(randIndex);
        toConvert.setVal(randIndex, !currVal);
    }
}
