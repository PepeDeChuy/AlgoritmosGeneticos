package herramientas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author working
 */
public class LeerDatos 
{
    
    
    public static int[][] tokenizarDataSet()
    {
        int cont1 = 0;
        ArrayList<int[]> preInstncias = new ArrayList<>();
     // conjunto de patrones del data set
     String texto, aux;
   
     LinkedList<String> lista = new LinkedList();
        
        try 
        {
            //llamamos el metodo que permite cargar la ventana
            JFileChooser file = new JFileChooser();
            file.setCurrentDirectory(new File("./"));
            file.showOpenDialog(file);
            //abrimos el archivo seleccionado
            File abre = file.getSelectedFile();

            //recorremos el archivo y lo leemos
            if (abre != null) 
            {
                FileReader archivos = new FileReader(abre);
                BufferedReader lee = new BufferedReader(archivos);

                while ((aux = lee.readLine()) != null) 
                {
                    texto = aux;
                    lista.add(texto);
                    cont1++;
                }
                lee.close();
                //System.out.println(lista.size());

                ArrayList<String> lista2 = new ArrayList<>();
                for (int i = 0; i < lista.size(); i++) 
                {
                    StringTokenizer st = new StringTokenizer(lista.get(i), ",");

                    while (st.hasMoreTokens()) 
                    {
                        lista2.add(st.nextToken());
                    }

                    int[] vector = new int[lista2.size()];

                    for (int x = 0; x < lista2.size(); x++) 
                    {
                        vector[x] = Integer.parseInt(lista2.get(x));
                    }
                    
                    preInstncias.add(vector);
                    lista2.clear();

                }
          
            }
        } 
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex + ""
                    + "\nNo se ha encontrado el archivo",
                    "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
            
        }
        
        int[][] instancias = new int[cont1][cont1];
        
        for(int b = 0; b < cont1; b++)
        {
            for(int m = 0; m < cont1; m++)
            {
                instancias[b][m] = preInstncias.get(b)[m];
            }
        }
        return instancias;
    }
    
    public static DatosSB tokenizarDataSetSB()
    {
        DatosSB datos = new DatosSB(null, 0, 0, 0);
        ArrayList<int[]> preInstncias = new ArrayList<>();
        // conjunto de patrones del data set
        String texto, aux;
   
        LinkedList<String> lista = new LinkedList();
        
        try 
        {
            //llamamos el metodo que permite cargar la ventana
            JFileChooser file = new JFileChooser();
            file.setCurrentDirectory(new File("./"));
            file.showOpenDialog(file);
            //abrimos el archivo seleccionado
            File abre = file.getSelectedFile();

            //recorremos el archivo y lo leemos
            if (abre != null) 
            {
                FileReader archivos = new FileReader(abre);
                BufferedReader lee = new BufferedReader(archivos);

                while ((aux = lee.readLine()) != null) 
                {
                    texto = aux;
                    lista.add(texto);
                }
                lee.close();
                //System.out.println(lista.size());

                ArrayList<String> lista2 = new ArrayList<>();
                for (int i = 0; i < lista.size(); i++) 
                {
                    if(i==1)
                    {
                        StringTokenizer st = new StringTokenizer(lista.get(i), " ");

                        while (st.hasMoreTokens()) 
                        {
                            lista2.add(st.nextToken());
                        }

                        int[] vector = new int[lista2.size()];

                        for (int x = 0; x < lista2.size(); x++) 
                        {
                            vector[x] = Integer.parseInt(lista2.get(x));
                        }

                        datos.setTamArray(vector[0]);
                        datos.setNumPosi(vector[1]);
                        datos.setNumVariables(vector[2]);
                        lista2.clear();
                        
                        datos.setArray(new int[datos.getNumPosi()][datos.getNumVariables()]);
                    }
                    else if(i>1)
                    {
                        StringTokenizer st = new StringTokenizer(lista.get(i), " ");

                        while (st.hasMoreTokens()) 
                        {
                            lista2.add(st.nextToken());
                        }

                        for (int x = 0; x < lista2.size(); x++) 
                        {
                            datos.getArray()[i-2][x] = Integer.parseInt(lista2.get(x));
                        }
                        lista2.clear();
                    }
                }
            }
        } 
        catch (IOException ex) 
        {
            JOptionPane.showMessageDialog(null, ex + ""
                    + "\nNo se ha encontrado el archivo",
                    "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
            
        }
        return datos;
    }
}
