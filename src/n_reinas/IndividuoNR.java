/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package n_reinas;
import java.util.Random;

/**
 *
 * @author peper
 */
public class IndividuoNR 
{
    private Tablero genotipo;
    private int[] fenotipo;
    private int fitness;

    private int n;
    public IndividuoNR(Tablero genotipo) 
    {
        this.genotipo = genotipo;
        fenotipo = new int[genotipo.getN()];
        n = genotipo.getN();
        calcularFenotipo();
    }
    
    public void Individuo(int[] fenotipo, int fitness)
    {
        this.fenotipo = fenotipo;
        this.fitness = fitness;
        
        genotipo = new Tablero(fenotipo);
    }
    
    public IndividuoNR(int n) 
    {
        this.genotipo = new Tablero(n);
        this.n = n;
        this.fenotipo = new int[genotipo.getN()];
        
        Random random = new Random();
        
        for(int x = 0; x < n; x++)
        {
            this.fenotipo[x] = random.nextInt(n);
        }
        
        calcularFitnessProfe();
    }
    

    public IndividuoNR(IndividuoNR i) 
    {
        this.genotipo = i.getGenotipo();
        this.fenotipo = i.getFenotipo();
        this.fitness = i.getFitness();
        this.n = i.getN();
    }
    
    public void calcularFenotipo()
    {
        for(int x = 0; x < getGenotipo().getN(); x++)
        {
            for(int y = 0; y < getGenotipo().getN(); y++)
            {
                if(getGenotipo().getCelda(x, y))
                {
                    getFenotipo()[x] = y;
                }
            }
        }
        calcularFitnessProfe();
    }
    
    /*
    public void calcularFitness()
    {
        int numChoques = 0;
        
        for(int x = 0; x < n; x++)
        {
            //Paso 1: Posicion y--(Para atras)
            for(int paso1 = getFenotipo()[x].getY()-1; paso1 >= 0; paso1--)
            {
                if(getGenotipo().getCelda(getFenotipo()[x].getX(), paso1))
                {
                    numChoques++;
                }
            }
            
            //Paso2: Posicion x+ y-(Diagonal Derecha Arriba)
            int paso2X = getFenotipo()[x].getX()+1;
            int paso2Y = getFenotipo()[x].getY()-1;
            while(paso2X < n && paso2Y >= 0)
            {
                if(getGenotipo().getCelda(paso2X, paso2Y))
                {
                    numChoques++;
                }
                paso2X++;
                paso2Y--;
            }
            
            //Paso3:Posicion x+ (Derecha)
            for(int paso3 = getFenotipo()[x].getX()+1; paso3 < n; paso3++)
            {
                if(getGenotipo().getCelda(paso3, getFenotipo()[x].getY()))
                {
                    numChoques++;
                }
            }
            
            //Paso4: Posicion x+ y+ (Diagonal Abajo Derecha)
            int paso4X = getFenotipo()[x].getX()+1;
            int paso4Y = getFenotipo()[x].getY()+1;
            while(paso4X < n && paso4Y < n)
            {
                if(getGenotipo().getCelda(paso4X, paso4Y))
                {
                    numChoques++;
                }
                paso4X++;
                paso4Y++;
            }
            
            //Paso5: Posicion y++ (Abajo)
            for(int paso5 = getFenotipo()[x].getY()+1; paso5 < n; paso5++)
            {
                if(getGenotipo().getCelda(getFenotipo()[x].getX(), paso5))
                {
                    numChoques++;
                }
            }
            
            //Paso 6: Posicion x- y+ (Diagonal Abajo Izquierda)
            int paso6X = getFenotipo()[x].getX()-1;
            int paso6Y = getFenotipo()[x].getY()+1;
            while(paso6X >= 0 && paso6Y < n)
            {
                if(getGenotipo().getCelda(paso6X, paso6Y))
                {
                    numChoques++;
                }
                paso6X--;
                paso6Y++;
            }
            
            //Paso 7: Posicion x- (Izquierda)
            for(int paso7 = getFenotipo()[x].getX()-1; paso7 >= 0; paso7--)
            {
                if(getGenotipo().getCelda(paso7, getFenotipo()[x].getY()))
                {
                    numChoques++;
                }
            }
            
            //Paso 8: Posicion x- y- (Diagonal Izquierda Arriba)
            int paso8X = getFenotipo()[x].getX()-1;
            int paso8Y = getFenotipo()[x].getY()-1;
            while(paso8X >= 0 && paso8Y >= 0)
            {
                if(getGenotipo().getCelda(paso8X, paso8Y))
                {
                    numChoques++;
                }
                paso8X--;
                paso8Y--;
            }
        }
        
        int numPosibilidades = n * n;
        
        this.setFitness(numChoques);
    }
    */
    
    public void calcularFitnessProfe()
    {
       this.fitness = 0;
       for(int x = 0; x < this.n-1;x++)
       {
            for(int y = x+1; y < this.n;y++)
            {
                int a = this.getFenotipo()[x];
                int b = this.getFenotipo()[y];
                
                int auxx = this.getFenotipo()[x] - x;
                int auxy = this.getFenotipo()[y] - y;
                
                int aux2x = this.getFenotipo()[x] + x;
                int aux2y = this.getFenotipo()[y] + y;
                
                if((a==b) || (auxx == auxy) || (aux2x== aux2y))
                {
                    this.fitness+=2;
                }
            } 
       }
    }
    
    public int getN()
    {
        return n;
    }

    /**
     * @return the genotipo
     */
    public Tablero getGenotipo() {
        return genotipo;
    }

    /**
     * @param genotipo the genotipo to set
     */
    public void setGenotipo(Tablero genotipo) {
        this.genotipo = genotipo;
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
    public void setFitness(int fitness) 
    {
        this.fitness = fitness;
    }

    /**
     * @return the fenotipo
     */
    public int[] getFenotipo() {
        return fenotipo;
    }

    /**
     * @param fenotipo the fenotipo to set
     */
    public void setFenotipo(int[] fenotipo) {
        this.fenotipo = fenotipo;
    }
}
