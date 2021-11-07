package com.buenoezandro.boot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/cargos")
public class CargoController {

	@GetMapping(path = "/cadastrar")
	public String cadastrar() {
		return "/cargo/cadastro";
	}
	
	@GetMapping(path = "/listar")
	public String listar() {
		return "/cargo/lista";
	}
}
