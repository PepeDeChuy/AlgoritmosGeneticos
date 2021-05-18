/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package n_ciudades;

import java.util.Random;

/**
 *
 * @author working
 */
public class Muta 
{
    public static void mutaSimple(Individuo ind)
    {

        Random ran = new Random();
        
        int pos =0, pos2 =0;
        while(pos == 0 || pos2 == 0)
        {
            if(pos==0)
            {
               pos = ran.nextInt(ind.getFenotipo().length); 
            }
            if(pos2==0)
            {
               pos2 = ran.nextInt(ind.getFenotipo().length); 
            }
        }
        int aux = ind.getFenotipo()[pos];
        ind.getFenotipo()[pos] = ind.getFenotipo()[pos2];
        ind.getFenotipo()[pos2] = aux;
        ind.calcularFittnes();
    }
}
