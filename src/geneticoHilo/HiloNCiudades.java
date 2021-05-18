/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticoHilo;

import java.util.ArrayList;
import java.util.Random;
import n_ciudades.Cruza;
import n_ciudades.Herramientas;
import n_ciudades.Individuo;
import n_ciudades.Muta;
import n_ciudades.Seleccion;

/**
 *
 * @author peper
 */
public class HiloNCiudades extends Thread
{
    JFramePrincipal jfp;
    private ArrayList<Individuo> poblacionActual = new ArrayList<>();
    
    public HiloNCiudades(JFramePrincipal jfp) 
    {
        this.jfp = jfp;
    }
    
    public void run()
    {
        ArrayList<Integer> datops = new ArrayList<>();
        generarPoblacionInicial();    
        Individuo mejor = Herramientas.mejorPoblacion(poblacionActual);
        datops.add(mejor.getFitness());
        jfp.actualizarGrafica(datops);
        
        // proceso evolutivo que tiene relación con el numero de generaciones
        for(int g=1;g < (int)jfp.getjSpinnerNG().getValue() ;g++)
        {
            ArrayList<Individuo> nuevaPob = new ArrayList<>();
            // garantizar que se va a generar una población nueva 
            for (int i=0; i< (int)jfp.getjSpinnerTP().getValue();i++)
            {
                // Seleccion de una madre 
                Individuo madre = Seleccion.seleccionAleatoria(this.getPoblacionActual());
                // Seleccion de un padre
                Individuo padre = Seleccion.seleccionAleatoria(this.getPoblacionActual());
                // cruza (Retornar el mejor ind de la cruza)
                Individuo hijo = Cruza.cruzaPorMascaraBinaria(madre, padre);
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
       int[][] array = herramientas.LeerDatos.tokenizarDataSet();
       
       //int[][] array = new int[][]{{0,64,22,64},{64,0,51,60},{22,51,0,50},{64,60,50,0}}; 
       for(int i=0; i < (int)jfp.getjSpinnerTP().getValue();i++)
       {
           int[] ruta = new int[array.length];
           ruta[0] = (int)jfp.getjSpinnerAux().getValue();
           for(int x = 1; x < array.length; x++)
           {
               boolean diferente = true;
               Random numAleatorio = new Random();
               int aux = 0;
               while(diferente)
               {
                    diferente = false;
                    aux = numAleatorio.nextInt(array.length);
                    for(int y = 0; y < ruta.length; y++)
                    {
                        if(ruta[y] == aux)
                        {
                            diferente = true;
                        }
                    }
               }
               ruta[x] = aux;
           }
           this.getPoblacionActual().add(new Individuo(array,ruta));
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
    public ArrayList<Individuo> getPoblacionActual() 
    {
        return poblacionActual;
    }
}
