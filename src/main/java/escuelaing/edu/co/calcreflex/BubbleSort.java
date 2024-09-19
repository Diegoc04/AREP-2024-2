/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package escuelaing.edu.co.calcreflex;

/**
 *
 * @author diego.castellanos-a
 */
public class BubbleSort {
        int[] solution = {};
    int A;
    int B;
    boolean go;
    public int[] BubbleSort(int[] lista ){
        A = lista[0];
        while(go){
            for(int i = 1; i < lista.length - 2; i++ ){
                int cont = 0;
                if(A > lista[i]){
                    B = lista[i];
                    lista[i] = A;
                    lista[i+1] = B;
                    cont++;
                }
                if(cont==0){
                    go = false;
                }
            }
        }
    solution = lista;
    return solution;
    }    
}

