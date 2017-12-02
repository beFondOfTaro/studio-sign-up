package com.iotstudio.studiosignup.controller.viewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 视图url映射
 */
@Controller
public class ViewController {
    
    @GetMapping("/sighupinfotable")
    public String sighUpInfoTable(){
        return "/sighUpInfoTable";
    }
    
    @GetMapping("/projecttable")
    public String projectTable(){
        return "/projectTable";
    }

    @GetMapping("/usertable")
    public String userTable(){
        return "/userTable";
    }
}
