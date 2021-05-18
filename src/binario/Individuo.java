/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binario;

/**
 *
 * @author working
 */
public class Individuo {
        
    // atributos de un individuo simple
    private int[] genotipo;
    private int fenotipo;
    private int fitness;

    public Individuo(int n) {
        // valorar su inicio aleatorio
        this.genotipo = Herramientas.generarArregloBinarios(n);
        calcularFitness();
    }
    
    public Individuo(int[] gen) {
        // valorar su inicio aleatorio
        this.genotipo = gen.clone();
        calcularFitness();
    }

    public Individuo(Individuo i) {
        this.genotipo = i.getGenotipo().clone();
        this.fenotipo = i.getFenotipo();
        this.fitness = i.getFitness();
    }

    private void calcularFenotipo (){
        // conversión entre la  representacion del arreglo de enteros a un valor decimal
        int acu = 0;
        int s = 0;
        for(int x = genotipo.length - 1; x>=0; x--){
            if (genotipo[x]==1){
              acu +=Math.pow(2, s);
            }
            s++;
        }
        this.fenotipo = acu;
    }
    
    public void calcularFitness(){
        calcularFenotipo();
        // evaluar el fenotipo en la función f(x)=x2
        this.fitness = this.fenotipo*this.fenotipo;
            
    }
    
    
    
    /**
     * @return the genotipo
     */
    public int[] getGenotipo() {
        return genotipo;
    }

    /**
     * @param genotipo the genotipo to set
     */
    public void setGenotipo(int[] genotipo) {
        this.genotipo = genotipo;
    }

    /**
     * @return the fenotipo
     */
    public int getFenotipo() {
        return fenotipo;
    }

    /**
     * @param fenotipo the fenotipo to set
     */
    public void setFenotipo(int fenotipo) {
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

    @Override
    public String toString() {
        
        String aux = "Gen: "+ this.genotipo.toString()+" Fen: "+
        this.fenotipo+" Fit: "+this.fitness;
        return aux; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
