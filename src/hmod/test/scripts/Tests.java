
package hmod.test.scripts;

import hmod.loader.GraphLoader;
import hmod.loader.GraphLoadException;
import hmod.core.Algorithm;
import hmod.core.AlgorithmException;
import hmod.loader.GraphBuilder;
import hmod.loader.GraphLoaderFactory;
import optefx.util.output.OutputConfigBuilder;
import optefx.util.output.OutputManager;

public class Tests
{
    public static void main(String[] args) throws AlgorithmException, GraphLoadException
    {
        OutputManager.getCurrent().setOutputsFromConfig(
            new OutputConfigBuilder().addSystemOutputId("test").
                build()
        );
        
        GraphLoader loader = GraphLoaderFactory.getInstance().createLoader();
        
        //runDemo(loader);
        runExp(loader, true, "hmod.test.scripts.blocks.TestA1Script");
        runExp(loader, false, "hmod.test.scripts.blocks.TestA2Script");
        //runExp(loader, false, "hmod.test.script.core.TestB2Script");
        //runExp(loader, false, "hmod.test.script.core.TestB3Script");
        //runExp(loader, false, "hmod.test.script.core.TestC1Script");
    }
    
    private static void runSimpleAlgorithm()
    {
        // Data
        int maxIteration = 10;
        int currIteration = 0;
        int prueba = 0;
        
        // step 1: TestInitOperator
        OutputManager.println("test", "Initializing heuristic!");
        
        do
        {
            // step 2: TestOperator
            String elemsStr = " (test existe!, prev: " + prueba + ")";
            prueba++;
            
            OutputManager.println("test", "Prueba!" + elemsStr);
            
            // step 3: CheckMaxIterationOperator
            // iteration update and check
            currIteration++;
        }        
        while(currIteration < maxIteration);
        
        // step 4: TestFinishOperator
        OutputManager.println("test", "Finishing heuristic!");
    }
    
    private static void runDemo(GraphLoader loader) throws AlgorithmException, GraphLoadException
    {
        OutputManager.println("test", ">>> Run using the 'runSimpleAlgorithm()' method");
        runSimpleAlgorithm();
        
        OutputManager.println("test");
        OutputManager.println("test", ">>> Run using the script");
        runExp(loader, false, "hmod.test.scripts.blocks.TestA1Script");
    }
    
    public static void runExp(GraphLoader loader, boolean handleError, String ... files) throws AlgorithmException, GraphLoadException
    {
        OutputManager.println("test", "************************\n");
        
        try
        {
            loader.restart();
            GraphBuilder layer = loader.createLayer();
            
            for(int i = 0; i < files.length; i++)
            {
                OutputManager.print("test", "Loading '" + files[i] + "'... ");
                loader.load(files[i], layer);
                OutputManager.println("test", "ok");
            }
            
            OutputManager.print("test", "Starting algorithm...\n");
            Algorithm alg = loader.getNode(layer);
            alg.start();
            loader.restart();
        }
        catch(Exception e)
        {
            if(handleError)
                e.printStackTrace(OutputManager.getCurrent().getOutput("test"));
            else
                throw e;
        }
        
        OutputManager.println("test");
    }
    
    public static void runExpError(GraphLoader loader, String file)
    {
        OutputManager.println("test", "****** Prueba " + file + " ******\n");
        
        try
        {
            loader.restart();
            GraphBuilder layer = loader.createLayer();
            loader.load(file, layer, "test");
            loader.getNode(layer).start();
        }
        catch(Exception e)
        {
            e.printStackTrace(OutputManager.getCurrent().getOutput("test"));
        }
        
        OutputManager.println("test");
    }
}
