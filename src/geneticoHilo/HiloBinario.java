package geneticoHilo;

import binario.Cruza;
import binario.Herramientas;
import binario.Individuo;
import binario.Muta;
import binario.Seleccion;
import herramientas.Grafica;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Arrays;
import org.jfree.chart.ChartPanel;

public class HiloBinario extends Thread
{
    JFramePrincipal jfp;
    
    private ArrayList<Individuo> poblacionActual = new ArrayList<>();
    
    public HiloBinario(JFramePrincipal jfp) 
    {
        this.jfp = jfp;
    }
    
    public void run()
    {   
        //int[] datops = new int[(Integer)jfp.getjSpinnerNG().getValue()];
        ArrayList<Integer> datops = new ArrayList<>();
        generarPoblacionInicial();
        
        Individuo mejor = Herramientas.mejorPoblacion(poblacionActual);
        //datops[0] = mejor.getFitness();
        datops.add(mejor.getFitness());
        jfp.actualizarGrafica(datops);
        
        // proceso evolutivo que tiene relación con el numero de generaciones
        for(int g=1; g<(Integer)jfp.getjSpinnerNG().getValue();g++)
        {
            ArrayList<Individuo> nuevaPob = new ArrayList<>();
            // garantizar que se va a generar una población nueva 
            for (int i=0; i<(Integer)jfp.getjSpinnerTP().getValue(); i++)
            {
                // Seleccion de una madre 
                Individuo madre = Seleccion.seleccionAleatoria(this.getPoblacionActual());
                // Seleccion de un padre
                Individuo padre = Seleccion.seleccionAleatoria(this.getPoblacionActual());
                // cruza (Retornar el mejor ind de la cruza)
                int[] mask = Herramientas.generarArregloBinarios(madre.getGenotipo().length);
                Individuo hijo = Cruza.cruzaPorMascaraBinaria(madre, padre, mask);
                // Se aplica una muta evaluando una probabilidad
                if (generarProbabilidadMuta())
                {
                   Muta.mutaSimple(hijo);
                }
                nuevaPob.add(hijo);
            }
            // actualización de la población
            sustituirPoblacion(nuevaPob);
            mejor = Herramientas.mejorPoblacion(poblacionActual);
            datops.add(mejor.getFitness());
            jfp.actualizarGrafica(datops);
        }
    }
    
    private void generarPoblacionInicial() 
    {
       // generar un población aleatoria de individuos
       for(int i=0; i <(Integer)jfp.getjSpinnerTP().getValue();i++)
       {
           this.getPoblacionActual().add(new Individuo((Integer)jfp.getjSpinnerAux().getValue()));
       }
       
    }

    private boolean generarProbabilidadMuta() 
    {
       
        if((Integer)jfp.getjSpinnerPM().getValue() > Math.random())
        {
           return true;
        } 
        else
        { 
            return false;
        }
        
    }

    private void sustituirPoblacion(ArrayList<Individuo> nuevaPob) 
    {
        this.getPoblacionActual().clear();
        for(Individuo aux:nuevaPob)
        {
           this.getPoblacionActual().add(new Individuo(aux));
        }
    }

    /**
     * @return the poblacionActual
     */
    public ArrayList<Individuo> getPoblacionActual() {
        return poblacionActual;
    }
}
