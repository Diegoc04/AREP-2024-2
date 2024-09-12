package com.mycompany.parcial_1;

import co.jufeng.mvc.web.servlet.bind.annotation.RestController;
import com.yanzhenjie.andserver.annotation.GetMapping;

@RestController 
public class ServiceFacade {
    
    @GetMapping("/calculadora")
    public static String calculadora(){
        return "src/main/java/resources/calculadora";
    }

    @GetMapping("/compreflex")
    public static String reflexiva(){
        return "src/main/java/resources/reflexiva";
    }
  
}

