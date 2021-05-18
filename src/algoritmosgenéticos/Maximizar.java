/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosgenéticos;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author peper
 */
public class Maximizar 
{
    String exprecion;
    double valor;
    int valorSerieBits;

    public Maximizar(String exprecion, String serieBits) 
    {
        this.exprecion = exprecion;
        this.valorSerieBits = conversorBits(serieBits);
        //limpiarExprecion();
        calcularValor();
    }
    
    public int conversorBits(String serieBits)
    {
        int auxValor = 0;
        for(int i = 0; i < serieBits.length(); i++)
        {
            auxValor = auxValor + (Integer.parseInt(serieBits.substring(i,i+1)) *((int)Math.pow(2,(serieBits.length()-i)-1)));
        }
        return auxValor;
    }
    
    public double getValor()
    {
        return valor;
    }
    
    public void limpiarExprecion()
    {
        for(int i = 0; i < exprecion.length(); i++)
        {
            switch (exprecion.substring(i, i+1))
            {
                case "^":
                {
                    String base = exprecion.substring(i-1, i);
                    String exponente = exprecion.substring(i, i+1);
                    String espo = "Math.pow("+base+","+exponente+")";
                    exprecion = exprecion.substring(0, i)+ espo + exprecion.substring(i, exprecion.length());
                    break;
                }
            }
        }
        System.out.println(exprecion);
    }
    
    public void calcularValor()
    {
        ScriptEngineManager manager = new ScriptEngineManager(); 
        ScriptEngine interprete = manager.getEngineByName("js"); 
        try 
        { 
            String formula = exprecion;//"X*Y/100" 
            interprete.put("X", valorSerieBits); 
            System.out.println("Resultado = "+interprete.eval(formula)); 
            valor = (double)interprete.eval(formula);
        } 
        catch(ScriptException se) 
        { 
            se.printStackTrace(); 
        } 
    }
}
