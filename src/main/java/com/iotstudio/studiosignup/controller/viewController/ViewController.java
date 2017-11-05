package com.iotstudio.studiosignup.controller.viewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping("/admincenter")
    public String adminCenter(){
        return "/admin";
    }

    @RequestMapping("/studenttable")
    public String studentTable(){
        return "/studentTable";
    }
}
