/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.springeci;

/**
 *
 * @author diego.castellanos-a
 */
@RestController
public class HelloService {
    
    @GetMapping("/hello")
    public static String hello(){
        return "Hello World";
    }

    @GetMapping("/pi")
    public static String pi(){
        return "3.1416";
    }

    @GetMapping("/luz")
    public static String luz(){
        return "299 792 458 metros por segundo ";
    }

    @GetMapping("/bye")
    public static String bye(){
        return "bye World";
    }

    @GetMapping("/planck")
    public static String planck(){
        return "6.626e-34 J⋅s";
    }
}
