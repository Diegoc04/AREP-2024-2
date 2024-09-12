package com.mycompany.parcial_1;

public class algoritmoOrdenamientoService {
    int[] solution = {};
    int A;
    int B;
    boolean go;
    int cont = 0
    public int[] BubbleSort(int[] lista ){
        A = lista[0];
        while(go){
            for(int i = 1; i < lista.length - 2; i++ ){
                if(A > lista[i]){
                    B = lista[i];
                    lista[i] = A;
                    lista[i+1] = B;
                    cont += 1
                }
            }
        }
    return solution;
    }    
}
