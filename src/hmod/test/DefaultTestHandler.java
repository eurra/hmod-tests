
package hmod.test;

/**
 *
 * @author Enrique Urra C.
 */
public class DefaultTestHandler implements TestHandler
{
    private int prueba;

    public DefaultTestHandler(int prueba)
    {
        this.prueba = prueba;
    }

    @Override
    public int getPrueba()
    {
        return prueba;
    }

    @Override
    public void incrementPrueba()
    {
        prueba++;
    }
    
}
