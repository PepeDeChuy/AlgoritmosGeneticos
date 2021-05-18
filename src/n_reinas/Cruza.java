/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package n_reinas;


/**
 *
 * @author working
 */
public class Cruza 
{
    
    public static IndividuoNR cruzaPorMascaraBinaria(IndividuoNR madre, IndividuoNR padre, int[] mask)
    {
        Tablero gen1 = new Tablero(padre.getN());
        Tablero gen2 = new Tablero(madre.getN());
        
        // recorrer la mascara ¿
        for(int x=0; x < mask.length; x++)
        {
            // padre
            if(mask[x] == 0)
            {
                gen1.agregarReina(x, padre.getFenotipo()[x]);
                gen2.agregarReina(x, madre.getFenotipo()[x]);
            } 
            // información madre
            else
            {
                gen1.agregarReina(x, madre.getFenotipo()[x]);
                gen2.agregarReina(x, padre.getFenotipo()[x]);
            }
        }
        
        IndividuoNR i1 = new IndividuoNR(gen1);
        IndividuoNR i2 = new IndividuoNR(gen2);
        
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
