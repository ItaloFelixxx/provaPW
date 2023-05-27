package br.com.project.suplementos.loja.de.suplementos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SuplementoController {
    @GetMapping("/")
    public String paginaUser(){
        return "user";
    }

    @GetMapping("/admin")
    public String paginaAdmin(){
        return "index";
    }
}
