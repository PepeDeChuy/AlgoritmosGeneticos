package n_ciudades;

import herramientas.Grafica;
import java.util.ArrayList;
import java.util.Random;

public class GeneticoNCiudades 
{
    
    private int tamanoPob;
    private int numGeneraciones;
    private double probMuta;
    private int bits;
    private ArrayList<Individuo> poblacionActual;
    
    private int ciudadInicial;

    public GeneticoNCiudades(int tamanoPob, int numGeneraciones, double probMuta,int bits, int ciudadInicial) 
    {
        this.tamanoPob = tamanoPob;
        this.numGeneraciones = numGeneraciones;
        this.probMuta = probMuta;
        this.bits =bits;
        this.poblacionActual = new ArrayList<>();
        
        this.ciudadInicial = ciudadInicial;
    }
    
    public void evolucionar()
    {
        int[] datops = new int[numGeneraciones];
        generarPoblacionInicial();    
        Individuo mejor = Herramientas.mejorPoblacion(poblacionActual);
        datops[0] = mejor.getFitness();
        
        // proceso evolutivo que tiene relación con el numero de generaciones
        for(int g=1;g<this.numGeneraciones;g++)
        {
            ArrayList<Individuo> nuevaPob = new ArrayList<>();
            // garantizar que se va a generar una población nueva 
            for (int i=0; i<this.tamanoPob;i++)
            {
                // Seleccion de una madre 
                Individuo madre = Seleccion.seleccionAleatoria(this.getPoblacionActual());
                // Seleccion de un padre
                Individuo padre = Seleccion.seleccionAleatoria(this.getPoblacionActual());
                // cruza (Retornar el mejor ind de la cruza)
                Individuo hijo = Cruza.cruzaPorMascaraBinaria(madre, padre);
                // Se aplica una muta evaluando una probabilidad
                if (generarProbabilidadMuta())
                {
                   Muta.mutaSimple(hijo);
                }
                nuevaPob.add(hijo);
            }
            // actualización de la población
            sustituirPoblacion(nuevaPob);
            mejor = Herramientas.mejorPoblacion(poblacionActual);
            datops[g] = mejor.getFitness();
        }
        
        System.out.println("Num generaciones: "+datops.length);
        Grafica grafica = new Grafica("Generacion","Fitness","Algoritmo Genetico");
        grafica.agregarSerie("Generaciones", datops);
        grafica.crearGrafica();
        grafica.muestraGrafica();
    }

    private void generarPoblacionInicial() 
    {
       // generar un población aleatoria de individuos
       int[][] array = herramientas.LeerDatos.tokenizarDataSet();
       
       //int[][] array = new int[][]{{0,64,22,64},{64,0,51,60},{22,51,0,50},{64,60,50,0}}; 
       for(int i=0; i < this.tamanoPob;i++)
       {
           int[] ruta = new int[array.length];
           ruta[0] = ciudadInicial;
           for(int x = 1; x < array.length; x++)
           {
               boolean diferente = true;
               Random numAleatorio = new Random();
               int aux = 0;
               while(diferente)
               {
                    diferente = false;
                    aux = numAleatorio.nextInt(array.length);
                    for(int y = 0; y < ruta.length; y++)
                    {
                        if(ruta[y] == aux)
                        {
                            diferente = true;
                        }
                    }
               }
               ruta[x] = aux;
           }
           this.getPoblacionActual().add(new Individuo(array,ruta));
       }
    }

    private boolean generarProbabilidadMuta() 
    {
       
        if (this.probMuta>Math.random())
        {
           return true;
        } 
        else{ return false;}
        
    }

    private void sustituirPoblacion(ArrayList<Individuo> nuevaPob) 
    {
       this.getPoblacionActual().clear();
       for(Individuo aux:nuevaPob)
       {
           this.getPoblacionActual().add(new Individuo(aux));
       }
    }

    /**
     * @return the poblacionActual
     */
    public ArrayList<Individuo> getPoblacionActual() 
    {
        return poblacionActual;
    }

    public static void main(String args[])
    {
        GeneticoNCiudades g = new GeneticoNCiudades(100, 100, 90, 8,0);
        g.evolucionar();
        
        Individuo mejor = Herramientas.mejorPoblacion(g.getPoblacionActual());
        System.out.println("");
    }
}
