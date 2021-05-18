/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package satisfaccion_booleana;

/**
 *
 * @author working
 */
public class CruzaSB {
    
    public static IndividuoSB cruzaPorMascaraBinaria(IndividuoSB madre, IndividuoSB padre)
    {
        
        boolean[] gen1 = padre.getFenotipo();
        boolean[] gen2 = madre.getFenotipo();
        
        int num = gen2.length/2;
        
        for(int x = 0; x < padre.getFenotipo().length; x++)
        {
            if(x<num)
            {
                boolean aux = gen1[x];
                gen1[x] = gen2[x];
                gen2[x] = aux;
            }
        }
        
        for(int x = 0; x < padre.getFenotipo().length; x++)
        {
            if(x<num)
            {
                boolean aux = gen2[x];
                gen2[x] = gen1[x];
                gen1[x] = aux;
            }
        }
        
        IndividuoSB i1 = new IndividuoSB(padre.getGenotipo(),gen1, madre.getNumPosi(), madre.getNumVariables());
        IndividuoSB i2 = new IndividuoSB(madre.getGenotipo(),gen2, madre.getNumPosi(), madre.getNumVariables());
        
        if(i1.getFitness() > i2.getFitness())
        {
            return i1;
        } 
        else
        {
            return i2;
        }
    }
    
}
