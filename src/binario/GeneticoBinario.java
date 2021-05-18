/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binario;

import java.util.ArrayList;

/**
 *
 * @author working
 */
public class GeneticoBinario {
    
    private int tamanoPob;
    private int numGeneraciones;
    private double probMuta;
    private int bits;
    private ArrayList<Individuo> poblacionActual;

    public GeneticoBinario(int tamanoPob, int numGeneraciones, double probMuta,int bits) 
    {
        this.tamanoPob = tamanoPob;
        this.numGeneraciones = numGeneraciones;
        this.probMuta = probMuta;
        this.bits =bits;
        this.poblacionActual = new ArrayList<>();
    }
    
    public void evolucionar()
    {
    
    
    generarPoblacionInicial();    
    // proceso evolutivo que tiene relación con el numero de generaciones
    for(int g=1;g<this.numGeneraciones;g++){
        ArrayList<Individuo> nuevaPob = new ArrayList<>();
        // garantizar que se va a generar una población nueva 
        for (int i=0; i<this.tamanoPob;i++){
            // Seleccion de una madre 
            Individuo madre = Seleccion.seleccionAleatoria(this.getPoblacionActual());
            // Seleccion de un padre
            Individuo padre = Seleccion.seleccionAleatoria(this.getPoblacionActual());
            // cruza (Retornar el mejor ind de la cruza)
            int[] mask = Herramientas.generarArregloBinarios(madre.getGenotipo().length);
            Individuo hijo = Cruza.cruzaPorMascaraBinaria(madre, padre, mask);
            // Se aplica una muta evaluando una probabilidad
            if (generarProbabilidadMuta()){
               Muta.mutaSimple(hijo);
            }
            nuevaPob.add(hijo);
        }
        // actualización de la población
        sustituirPoblacion(nuevaPob);
    }
    
    
    
    
    }

    private void generarPoblacionInicial() {
       // generar un población aleatoria de individuos
       for(int i=0; i < this.tamanoPob;i++){
           this.getPoblacionActual().add(new Individuo(this.bits));
       }
       
    }

    private boolean generarProbabilidadMuta() {
       
        if (this.probMuta>Math.random()){
           return true;
       } else{ return false;}
        
    }

    private void sustituirPoblacion(ArrayList<Individuo> nuevaPob) {
        this.getPoblacionActual().clear();
       for(Individuo aux:nuevaPob){
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
