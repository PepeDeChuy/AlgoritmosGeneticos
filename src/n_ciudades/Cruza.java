/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package n_ciudades;

/**
 *
 * @author working
 */
public class Cruza {
    
    public static Individuo cruzaPorMascaraBinaria(Individuo madre, Individuo padre)
    {
        int[] gen1 = padre.getFenotipo();
        int[] gen2 = madre.getFenotipo();
        
        for(int x = 1; x < madre.getFenotipo().length; x++)
        {
            int aux = gen1[x];
            gen1[x] = gen2[x];
            gen2[x] = aux;
        }
        
        Individuo i1 = new Individuo(padre.getGenotipo(),gen1);
        Individuo i2 = new Individuo(madre.getGenotipo(),gen2);
        
        if(i1.getFitness() < i2.getFitness())
        {
            return i1;
        } 
        else
        {
            return i2;
        }
    }
    
}
