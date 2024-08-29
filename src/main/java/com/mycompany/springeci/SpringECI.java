/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.springeci;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author diego.castellanos-a
 */
public class SpringECI {

    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, IllegalAccessException, InvocationTargetException {
        Class c = Class.forName(args[0]);
        Map<String, Method> services = new HashMap();
        
        //Cargar componentes
        if(c.isAnnotationPresent(RestController.class)){
            Method[] methods = c.getDeclaredMethods();
            for(Method m: methods){
                if(m.isAnnotationPresent(GetMapping.class)){
                    String key = m.getAnnotation(GetMapping.class).value();
                    services.put(key, m);
                }
            }
        }
        
        // Codigo quemado para ejemplo
        
        URL serviceurl = new URL("http://localhost:8080/App/hello");
        String path = serviceurl.getPath();
        System.out.println("Path " + path);
        String servicename = path.substring(4);
        System.out.println("Service name " + servicename);
        
        Method ms = services.get(servicename);
        ms.invoke(ms);

        // Codigo quemado para ejemplo
        
        serviceurl = new URL("http://localhost:8080/App/pi");
        path = serviceurl.getPath();
        System.out.println("Path " + path);
        servicename = path.substring(4);
        System.out.println("Service name " + servicename);
        
        ms = services.get(servicename);
        ms.invoke(ms);

        // Codigo quemado para ejemplo
        
        serviceurl = new URL("http://localhost:8080/App/luz");
        path = serviceurl.getPath();
        System.out.println("Path " + path);
        servicename = path.substring(4);
        System.out.println("Service name " + servicename);
        
        ms = services.get(servicename);
        ms.invoke(ms);

        // Codigo quemado para ejemplo
        
        serviceurl = new URL("http://localhost:8080/App/bye");
        path = serviceurl.getPath();
        System.out.println("Path " + path);
        servicename = path.substring(4);
        System.out.println("Service name " + servicename);
        
        ms = services.get(servicename);
        ms.invoke(ms);

        // Codigo quemado para ejemplo
        
        serviceurl = new URL("http://localhost:8080/App/planck");
        path = serviceurl.getPath();
        System.out.println("Path " + path);
        servicename = path.substring(4);
        System.out.println("Service name " + servicename);
        
        ms = services.get(servicename);
        ms.invoke(ms);
    }
}
