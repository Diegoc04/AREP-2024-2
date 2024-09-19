package com.mycompany.parcial_1;

public class algoritmoOrdenamientoService {
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
