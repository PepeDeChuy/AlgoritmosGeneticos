/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binario;

/**
 *
 * @author working
 */
public class Cruza {
    
    public static Individuo cruzaPorMascaraBinaria(Individuo madre, Individuo padre,
             int[] mask){
        int[] gen1 = new int[madre.getGenotipo().length];
        int[] gen2 = new int[madre.getGenotipo().length];
        // recorrer la mascara ¿
        for(int x=0; x < mask.length; x++){
            // padre
            if(mask[x]==0){
                gen1[x]= padre.getGenotipo()[x];
                gen2[x]= madre.getGenotipo()[x];
            } 
            // información madre
            else{
                gen1[x] = madre.getGenotipo()[x];
                gen2[x] = padre.getGenotipo()[x];
            }
        }
        Individuo i1 = new Individuo(gen1);
        Individuo i2 = new Individuo(gen2);
        
        if(i1.getFitness()< i2.getFitness()){
            return i1;
        } else{
            return i2;
        }
    }
    
}
