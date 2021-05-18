/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramientas;


import n_ciudades.*;
import java.util.ArrayList;
import java.util.Random;
import satisfaccion_booleana.IndividuoSB;

/**
 *
 * @author working
 */
public class Herramientas 
{
    public static int[] generarArregloBinarios(int n )
    {
        int[] arreglo = new int[n];
        Random ran = new Random();
        for(int x=0; x< n ;x++)
        {
            arreglo[x]= ran.nextInt(2);
        }
        return arreglo;
    }
    
    public static Individuo mejorPoblacion(ArrayList<Individuo> pob)
    {
        Individuo mejor = new Individuo(pob.get(0));
        for(Individuo aux: pob)
        {
            if (aux.getFitness() < mejor.getFitness())
            {
                mejor = new Individuo(aux);
            }
        }
        return mejor;
    }
    /* Seleccion SB */
    public static IndividuoSB mejorPoblacionSB(ArrayList<IndividuoSB> pobSB)
    {
        IndividuoSB mejor = new IndividuoSB(pobSB.get(0));
        for(IndividuoSB aux: pobSB)
        {
            if (aux.getFitness() > mejor.getFitness())
            {
                mejor = new IndividuoSB(aux);
            }
        }
        return mejor;
    }
    
    public static IndividuoSB seleccionAleatoriaSB(ArrayList<IndividuoSB> pob)
    {
        Random ran = new Random();
        int pos = ran.nextInt(pob.size());
        return new IndividuoSB(pob.get(pos));
    }
}
