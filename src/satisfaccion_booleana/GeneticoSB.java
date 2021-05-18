package satisfaccion_booleana;

import herramientas.DatosSB;
import herramientas.Grafica;
import java.util.ArrayList;

public class GeneticoSB 
{
    
    private int tamanoPob;
    private int numGeneraciones;
    private double probMuta;
    private ArrayList<IndividuoSB> poblacionActual;

    public GeneticoSB(int tamanoPob, int numGeneraciones, double probMuta) 
    {
        this.tamanoPob = tamanoPob;
        this.numGeneraciones = numGeneraciones;
        this.probMuta = probMuta;
        this.poblacionActual = new ArrayList<>();
    }
    
    public void evolucionar()
    {
        int[] datops = new int[numGeneraciones];
        generarPoblacionInicial();    
        IndividuoSB mejor = herramientas.Herramientas.mejorPoblacionSB(poblacionActual);
        datops[0] = mejor.getFitness();
        
        // proceso evolutivo que tiene relación con el numero de generaciones
        for(int g=1;g<this.numGeneraciones;g++)
        {
            ArrayList<IndividuoSB> nuevaPob = new ArrayList<>();
            // garantizar que se va a generar una población nueva 
            for (int i=0; i<this.tamanoPob;i++)
            {
                // Seleccion de una madre 
                IndividuoSB madre = herramientas.Herramientas.seleccionAleatoriaSB(this.getPoblacionActual());
                // Seleccion de un padre
                IndividuoSB padre = herramientas.Herramientas.seleccionAleatoriaSB(this.getPoblacionActual());
                // cruza (Retornar el mejor ind de la cruza)
                IndividuoSB hijo = CruzaSB.cruzaPorMascaraBinaria(madre, padre);
                // Se aplica una muta evaluando una probabilidad
                if (generarProbabilidadMuta())
                {
                   MutaSB.mutaSimple(hijo);
                }
                nuevaPob.add(hijo);
            }
            // actualización de la población
            sustituirPoblacion(nuevaPob);
            mejor = herramientas.Herramientas.mejorPoblacionSB(poblacionActual);
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
       DatosSB dt = herramientas.LeerDatos.tokenizarDataSetSB();
       
       //int[][] array = new int[][]{{0,64,22,64},{64,0,51,60},{22,51,0,50},{64,60,50,0}}; 
       for(int i=0; i < this.tamanoPob;i++)
       {
           this.getPoblacionActual().add(new IndividuoSB(dt.getArray(),dt.getTamArray(),dt.getNumPosi(),dt.getNumVariables()));
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

    private void sustituirPoblacion(ArrayList<IndividuoSB> nuevaPob) 
    {
       this.getPoblacionActual().clear();
       for(IndividuoSB aux:nuevaPob)
       {
           this.getPoblacionActual().add(new IndividuoSB(aux));
       }
    }

    /**
     * @return the poblacionActual
     */
    public ArrayList<IndividuoSB> getPoblacionActual() 
    {
        return poblacionActual;
    }

    public static void main(String args[])
    {
        GeneticoSB g = new GeneticoSB(100, 100, 90);
        g.evolucionar();
        
        IndividuoSB mejor = herramientas.Herramientas.mejorPoblacionSB(g.getPoblacionActual());
        System.out.println("");
    }
}
