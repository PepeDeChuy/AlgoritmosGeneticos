/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package n_reinas;

/**
 *
 * @author peper
 */
public class N_Reinas 
{
    public static void main(String args[])
    {
        GeneticoNReinas gb = new GeneticoNReinas(10, 10, 20, 8);
        gb.evolucionar(true);
        
        IndividuoNR mejor = Herramientas.mejorPoblacion(gb.getPoblacionActual());
        System.out.println("El mejor individuo encontrado fue "+mejor.getFitness());
    }
}
