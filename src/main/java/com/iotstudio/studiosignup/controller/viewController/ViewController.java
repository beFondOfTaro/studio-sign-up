package com.iotstudio.studiosignup.controller.viewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 视图url映射
 */
@Controller
public class ViewController {
    
    @GetMapping("/sighUpInfoTable")
    public String sighUpInfoTable(){
        return "/sighUpInfoTable";
    }
    
    @GetMapping("/projectTable")
    public String projectTable(){
        return "/projectTable";
    }

    @GetMapping("/userTable")
    public String userTable(){
        return "/userTable";
    }

//    @GetMapping("/api")
//    public String api(){
//        return "swagger-ui";
//    }
}
