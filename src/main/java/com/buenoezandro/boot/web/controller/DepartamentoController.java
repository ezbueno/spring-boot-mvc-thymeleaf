package com.buenoezandro.boot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/departamentos")
public class DepartamentoController {

	@GetMapping(path = "/cadastrar")
	public String cadastrar() {
		return "/departamento/cadastro";
	}
	
	@GetMapping(path = "/listar")
	public String listar() {
		return "/departamento/lista";
	}
}
