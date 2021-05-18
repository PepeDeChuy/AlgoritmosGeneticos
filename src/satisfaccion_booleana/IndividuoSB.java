/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package satisfaccion_booleana;

import java.util.Random;

/**
 *
 * @author peper
 */
public class IndividuoSB 
{
    private int[][] genotipo;
    private boolean[] fenotipo;
    private int fitness;
    
    private int numPosi;
    private int numVariables;
    
    public IndividuoSB(int[][] genotipo, int tamArray, int numPosi, int numVariables)
    {
        this.genotipo = genotipo;
        fenotipo = new boolean[tamArray];
        this.numPosi = numPosi;
        this.numVariables = numVariables;
        generarFenotipo();
        calcularFitness();
    }
    
    public IndividuoSB(int[][] genotipo, boolean[] fenotipo, int numPosi, int numVariables)
    {
        this.genotipo = genotipo;
        this.fenotipo = fenotipo;
        this.numPosi = numPosi;
        this.numVariables = numVariables;
        calcularFitness();
    }
    
    public IndividuoSB(IndividuoSB i) 
    {
        this.genotipo = i.getGenotipo();
        this.fenotipo = i.getFenotipo();
        this.fitness = i.getFitness();
        
        this.numPosi = i.getNumPosi();
        this.numVariables = i.getNumVariables();
    }
    
    public void generarFenotipo()
    {
        Random random = new Random();
        for(int i = 0; i < getFenotipo().length; i++)
        {
            this.getFenotipo()[i] = random.nextBoolean();
        }
        System.out.println("");
    }
    
    public void calcularFitness()
    {
        int auxFitness = 0;
        
        for(int x = 0; x < getNumPosi(); x++)
        {
            boolean auxOR = false;
            for(int y = 0; y < getNumVariables(); y++)
            {
                if(getGenotipo()[x][y] < 0)
                {
                    auxOR = auxOR || !getFenotipo()[((-1 * getGenotipo()[x][y]))-1];
                }
                else
                {
                    auxOR = auxOR || getFenotipo()[(getGenotipo()[x][y])-1];
                }
            }
            
            if(auxOR)
            {
                auxFitness++;
            }
        }
                
        this.setFitness(auxFitness);
    }

    /**
     * @return the genotipo
     */
    public int[][] getGenotipo() {
        return genotipo;
    }

    /**
     * @param genotipo the genotipo to set
     */
    public void setGenotipo(int[][] genotipo) {
        this.genotipo = genotipo;
    }

    /**
     * @return the fenotipo
     */
    public boolean[] getFenotipo() {
        return fenotipo;
    }

    /**
     * @param fenotipo the fenotipo to set
     */
    public void setFenotipo(boolean[] fenotipo) {
        this.fenotipo = fenotipo;
    }

    /**
     * @return the fitness
     */
    public int getFitness() {
        return fitness;
    }

    /**
     * @param fitness the fitness to set
     */
    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    /**
     * @return the numPosi
     */
    public int getNumPosi() {
        return numPosi;
    }

    /**
     * @param numPosi the numPosi to set
     */
    public void setNumPosi(int numPosi) {
        this.numPosi = numPosi;
    }

    /**
     * @return the numVariables
     */
    public int getNumVariables() {
        return numVariables;
    }

    /**
     * @param numVariables the numVariables to set
     */
    public void setNumVariables(int numVariables) {
        this.numVariables = numVariables;
    }
}
