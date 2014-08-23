
package hmod.domain.binary.components;

import hmod.core.AlgorithmException;
import hmod.domain.binary.BinarySolution;

public class Randomize extends BinaryConversion
{
    @Override
    public void convert(BinarySolution toConvert) throws AlgorithmException
    {
        toConvert.randomize();
    }
}
