/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticoHilo;

import herramientas.DatosSB;
import java.util.ArrayList;
import satisfaccion_booleana.CruzaSB;
import satisfaccion_booleana.IndividuoSB;
import satisfaccion_booleana.MutaSB;

/**
 *
 * @author peper
 */
public class HiloSastifaccionBoolena extends Thread
{
    private JFramePrincipal jfp;
    private ArrayList<IndividuoSB> poblacionActual = new ArrayList<>();

    public HiloSastifaccionBoolena(JFramePrincipal jfp) 
    {
        this.jfp = jfp;
    }
    
    public void run()
    {
        ArrayList<Integer> datops = new ArrayList<>();
        generarPoblacionInicial();    
        IndividuoSB mejor = herramientas.Herramientas.mejorPoblacionSB(poblacionActual);
        datops.add(mejor.getFitness());
        jfp.actualizarGrafica(datops);
        
        // proceso evolutivo que tiene relación con el numero de generaciones
        for(int g=1;g<(int)jfp.getjSpinnerNG().getValue();g++)
        {
            ArrayList<IndividuoSB> nuevaPob = new ArrayList<>();
            // garantizar que se va a generar una población nueva 
            for (int i=0; i<(int)jfp.getjSpinnerTP().getValue();i++)
            {
                // Seleccion de una madre 
                IndividuoSB madre = herramientas.Herramientas.seleccionAleatoriaSB(this.getPoblacionActual());
                // Seleccion de un padre
                IndividuoSB padre = herramientas.Herramientas.seleccionAleatoriaSB(this.getPoblacionActual());
                // cruza (Retornar el mejor ind de la cruza)
                IndividuoSB hijo = CruzaSB.cruzaPorMascaraBinaria(madre, padre);
                // Se aplica una muta evaluando una probabilidad
                if (generarProbabilidadMuta())
                {
                   MutaSB.mutaSimple(hijo);
                }
                nuevaPob.add(hijo);
            }
            // actualización de la población
            sustituirPoblacion(nuevaPob);
            mejor = herramientas.Herramientas.mejorPoblacionSB(poblacionActual);
            datops.add(mejor.getFitness());
            jfp.actualizarGrafica(datops);
        }
    }
    
     private void generarPoblacionInicial() 
    {
       // generar un población aleatoria de individuos
       DatosSB dt = herramientas.LeerDatos.tokenizarDataSetSB();
       
       //int[][] array = new int[][]{{0,64,22,64},{64,0,51,60},{22,51,0,50},{64,60,50,0}}; 
       for(int i=0; i < (int)jfp.getjSpinnerTP().getValue();i++)
       {
           this.getPoblacionActual().add(new IndividuoSB(dt.getArray(),dt.getTamArray(),dt.getNumPosi(),dt.getNumVariables()));
       }
    }

    private boolean generarProbabilidadMuta() 
    {
       
        if ((int)jfp.getjSpinnerPM().getValue()>Math.random())
        {
           return true;
        } 
        else{ return false;}
        
    }

    private void sustituirPoblacion(ArrayList<IndividuoSB> nuevaPob) 
    {
       this.getPoblacionActual().clear();
       for(IndividuoSB aux:nuevaPob)
       {
           this.getPoblacionActual().add(new IndividuoSB(aux));
       }
    }

    /**
     * @return the poblacionActual
     */
    public ArrayList<IndividuoSB> getPoblacionActual() 
    {
        return poblacionActual;
    }
}
