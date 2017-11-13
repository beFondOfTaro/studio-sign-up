package com.iotstudio.studiosignup.controller.viewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/admincenter")
    public String adminCenter(){
        return "/admin";
    }

    @GetMapping("/studenttable")
    public String studentTable(){
        return "/studentTable";
    }
    
    @GetMapping("/teachertable")
    public String teacherTable(){
        return "/teacherTable";
    }
    
    @GetMapping("/sighupinfotable")
    public String sighUpInfoTable(){
        return "/sighUpInfoTable";
    }
    
    @GetMapping("/projecttable")
    public String projectTable(){
        return "/projectTable";
    }
}
