/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramientas;

import binario.Individuo;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author peper
 */
public class Ruleta 
{

    private Individuo[] rulet;
    private int auxSuma = 0;
    public Ruleta(ArrayList<Individuo> poblacion) 
    {
        auxSuma = 0;
        
        for(int x = 0; x < poblacion.size(); x++)
        {
            auxSuma += poblacion.get(x).getFitness();
        }
        
        rulet = new Individuo[auxSuma];
        
        int auxCelda = 0;
        
        for(int x = 0; x < poblacion.size(); x++)
        {
            for(int y = 0; y < poblacion.get(x).getFitness(); y++)
            {
                rulet[auxCelda] = poblacion.get(x);
                auxCelda++;
            }
        }
    }
    
    public Individuo extraerIndividuo()
    {
        Random r = new Random();
        return rulet[r.nextInt(auxSuma+1)];
    }
    
    public static void main(String args[])
    {
        ArrayList<Individuo> poblacion = new ArrayList<>();
        poblacion.add(new Individuo(0));
        poblacion.add(new Individuo(1));
        poblacion.add(new Individuo(2));
        poblacion.add(new Individuo(3));
        poblacion.add(new Individuo(4));
        poblacion.add(new Individuo(5));
        poblacion.add(new Individuo(6));
        poblacion.add(new Individuo(7));
        poblacion.add(new Individuo(8));
        poblacion.add(new Individuo(9));
        poblacion.add(new Individuo(10));
        
        Ruleta r = new Ruleta(poblacion);
    }
}
