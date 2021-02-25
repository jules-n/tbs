package com.example.tbs.controllers;

import com.example.tbs.dataMapper.IAreaMapper;
import com.example.tbs.dataMapper.oracleMappers.AreaMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private IAreaMapper areaMapper = new AreaMapper();

    @GetMapping("/")
    String home(Model model){
        model.addAttribute("areas", areaMapper.getAll());
        return "index";
    }

    @GetMapping("/register")
        String register(){
            return "registration";
        }

    @GetMapping("/logIn")
        String logIn(){
        return "authorization";
    }
}
