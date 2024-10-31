/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parcial2.Algoritmos;

/**
 *
 * @author diego.castellanos-a
 */
public class algoritmosService {
    int A;
    int B;
    int C;
    boolean go = true;
    public int linearSearch(int[] lista, int elemento){
        A = elemento;
        int cont = 0;
        for(int i = 0; i < lista.length - 1; i++){
            if(A == lista[i]){
                return cont;
            }
            else{
                cont++;
            }
        }   
        return -1;
    }    
    
    public int binarySearch(int[] lista, int elemento ){
        C = elemento;
        while(go){
            B = lista[0];
            int cont = 0;
            for(int i = 1; i < lista.length - 2; i++){
                if(lista[i-1] > lista[i]){
                    B = lista[i-1];
                    lista[i-1] = lista[i];
                    lista[i] = B;
                    cont++;
                }
            }
            if(cont == 0){
                go = false;
            }
        }
        int inicio = lista[0];
        int fin = lista[-1];
        while(go){
            int medio = lista[lista.length/2];
            if(C == medio){
                return lista.length/2;
            }else if(medio > elemento){
                
            }
       
            
    } 
        return 3;
    }
}
