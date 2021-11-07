package com.buenoezandro.boot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/funcionarios")
public class FuncionarioController {

	@GetMapping(path = "/cadastrar")
	public String cadastrar() {
		return "/funcionario/cadastro";
	}
	
	@GetMapping(path = "/listar")
	public String listar() {
		return "/funcionario/lista";
	}
}
