package com.sgeumsaapi.sge_umsa_api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PanelUsuariosController {

    @GetMapping("/panel_usuario_prueba")
  
    public String prueba() {
    
        return "panel_usuario_prueba";
    }

}
