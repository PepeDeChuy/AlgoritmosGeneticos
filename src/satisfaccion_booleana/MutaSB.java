/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package satisfaccion_booleana;

import n_reinas.*;
import java.util.Random;

/**
 *
 * @author working
 */
public class MutaSB 
{
    public static void mutaSimple(IndividuoSB ind)
    {
        Random ran = new Random();
        
        int pos = ran.nextInt(ind.getFenotipo().length);
        ind.getFenotipo()[pos] = ran.nextBoolean();
        
        pos = ran.nextInt(ind.getFenotipo().length);
        ind.getFenotipo()[pos] = ran.nextBoolean();
        
        pos = ran.nextInt(ind.getFenotipo().length);
        ind.getFenotipo()[pos] = ran.nextBoolean();
        
        ind.calcularFitness();
    }
}
