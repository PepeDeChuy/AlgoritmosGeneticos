/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package n_reinas;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author working
 */
public class Seleccion 
{
    
    public static IndividuoNR seleccionAleatoria(ArrayList<IndividuoNR> pob)
    {
        Random ran = new Random();
        int pos = ran.nextInt(pob.size());
        return new IndividuoNR(pob.get(pos));
    }
    
}
