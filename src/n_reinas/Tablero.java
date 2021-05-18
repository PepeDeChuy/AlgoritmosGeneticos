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
public class Tablero 
{
    int[][] tablero;

    public Tablero(int n) 
    {
        tablero = new int[n][n];
        
        for(int x = 0; x < n; x++)
        {
            for(int y = 0; y < n; y++)
            {
                tablero[x][y] = 0;
            }
        }
    }
    
    public Tablero(int[] cordenadas)
    {
        int n = cordenadas.length;
        tablero = new int[n][n];
        
        for(int x = 0; x < n; x++)
        {
            for(int y = 0; y < n; y++)
            {
                tablero[x][y] = 0;
            }
        }
        
        for(int x = 0; x < n; x++)
        {
            tablero[x][cordenadas[x]] = 1;
        }
    }
    
    public boolean getCelda(int x, int y)
    {
        if(tablero[x][y] == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public int getN()
    {
        return tablero.length;
    }
    
    public void agregarReina(int x, int y)
    {
        tablero[x][y] = 1;
    }
}
