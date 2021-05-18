package geneticoHilo;

import java.util.ArrayList;
import n_reinas.Cruza;
import n_reinas.Herramientas;
import n_reinas.IndividuoNR;
import n_reinas.Muta;
import n_reinas.Seleccion;

public class HiloNReinas extends Thread
{
    private JFramePrincipal jfp;
    private ArrayList<IndividuoNR> poblacionActual = new ArrayList<>();

    public HiloNReinas(JFramePrincipal jfp) 
    {
        this.jfp = jfp;
    }
    
    public void run()
    {
        ArrayList<Integer> datops = new ArrayList<>();
        generarPoblacionInicial();
        IndividuoNR mejor = Herramientas.mejorPoblacion(poblacionActual);
        datops.add(mejor.getFitness());
        jfp.actualizarGrafica(datops);
        
        // proceso evolutivo que tiene relación con el numero de generaciones
        for(int g=1; g< (int)jfp.getjSpinnerNG().getValue(); g++)
        {
            ArrayList<IndividuoNR> nuevaPob = new ArrayList<>();
            // garantizar que se va a generar una población nueva 
            for (int i=0; i< (int)jfp.getjSpinnerTP().getValue() ;i++)
            {
                // Seleccion de una madre 
                IndividuoNR madre = Seleccion.seleccionAleatoria(this.getPoblacionActual());
                // Seleccion de un padre
                IndividuoNR padre = Seleccion.seleccionAleatoria(this.getPoblacionActual());
                
                // cruza (Retornar el mejor ind de la cruza)
                int[] mask = Herramientas.generarArregloBinarios(madre.getN());
                IndividuoNR hijo = Cruza.cruzaPorMascaraBinaria(madre, padre, mask);
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
       for(int i=0; i < (int)jfp.getjSpinnerTP().getValue();i++)
       {
           this.getPoblacionActual().add(new IndividuoNR((int)jfp.getjSpinnerTP().getValue()));
       } 
    }

    private boolean generarProbabilidadMuta() 
    {
       
        if ((int)jfp.getjSpinnerPM().getValue()>Math.random())
        {
           return true;
        } 
        else
        { 
            return false;
        }
    }

    private void sustituirPoblacion(ArrayList<IndividuoNR> nuevaPob) 
    {
       this.getPoblacionActual().clear();
       for(IndividuoNR aux:nuevaPob)
       {
           this.getPoblacionActual().add(new IndividuoNR(aux));
       }
    }

    /**
     * @return the poblacionActual
     */
    public ArrayList<IndividuoNR> getPoblacionActual() 
    {
        return poblacionActual;
    }
}
