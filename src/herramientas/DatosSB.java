/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramientas;

/**
 *
 * @author peper
 */
public class DatosSB 
{
    private int[][] array;
    private int tamArray;
    private int numPosi;
    private int numVariables;

    public DatosSB(int[][] array, int tamArray, int numPosi, int numVariables) 
    {
        this.array = array;
        this.tamArray = tamArray;
        this.numPosi = numPosi;
        this.numVariables = numVariables;
    }

    /**
     * @return the array
     */
    public int[][] getArray() {
        return array;
    }

    /**
     * @param array the array to set
     */
    public void setArray(int[][] array) {
        this.array = array;
    }

    /**
     * @return the tamArray
     */
    public int getTamArray() {
        return tamArray;
    }

    /**
     * @param tamArray the tamArray to set
     */
    public void setTamArray(int tamArray) {
        this.tamArray = tamArray;
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
