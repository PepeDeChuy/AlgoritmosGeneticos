package n_ciudades;

public class Individuo 
{
    private int[][] genotipo;
    private int[] fenotipo;
    private int fitness;

    public Individuo(int[][] genotipo, int[] fenotipo, int fitness) 
    {
        this.genotipo = genotipo;
        this.fenotipo = fenotipo;
        this.fitness = fitness;
    }
    
    public Individuo(int[][] array)
    {
        this.genotipo = array;
        
        fenotipo = new int[array.length];
    }
    
    public Individuo(int[][] array, int[] ruta)
    {
        this.genotipo = array;
        this.fenotipo = ruta;
        calcularFittnes();
    }
    
    public Individuo(Individuo individuo) 
    {
        this.genotipo = individuo.getGenotipo();
        this.fenotipo = individuo.getFenotipo();
        this.fitness = individuo.getFitness();
    }
    
    public void calcularFittnes()
    {
        int aux = 0;
        for(int x = 0; x < fenotipo.length-1; x++)
        {
            aux +=  genotipo[fenotipo[x]][fenotipo[x+1]];
        }
        
        aux +=  genotipo[fenotipo[fenotipo.length-1]][fenotipo[0]];
        
        fitness = aux;
    }

    /**
     * @return the genotipo
     */
    public int[][] getGenotipo() {
        return genotipo;
    }
    
    public int numCiudades()
    {
        return getGenotipo().length;
    }

    /**
     * @return the fenotipo
     */
    public int[] getFenotipo() {
        return fenotipo;
    }

    /**
     * @return the fitness
     */
    public int getFitness() {
        return fitness;
    }
    
    
}
