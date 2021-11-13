package com.buenoezandro.boot.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.buenoezandro.boot.domain.Cargo;
import com.buenoezandro.boot.domain.Departamento;
import com.buenoezandro.boot.service.CargoService;
import com.buenoezandro.boot.service.DepartamentoService;
import com.buenoezandro.boot.utils.MensagemUtils;

@Controller
@RequestMapping(path = "/cargos")
public class CargoController {

	private final CargoService cargoService;
	private final DepartamentoService departamentoService;

	public CargoController(CargoService cargoService, DepartamentoService departamentoService) {
		this.cargoService = cargoService;
		this.departamentoService = departamentoService;
	}

	@GetMapping(path = "/cadastrar")
	public String cadastrar(Cargo cargo) {
		return "/cargo/cadastro";
	}

	@GetMapping(path = "/listar")
	public String listar() {
		return "/cargo/lista";
	}

	@PostMapping(path = "/salvar")
	public String salvar(Cargo cargo, RedirectAttributes attributes) {
		this.cargoService.salvar(cargo);
		attributes.addFlashAttribute("success", MensagemUtils.INSERT);
		return "redirect:/cargos/cadastrar";
	}
	
	@ModelAttribute(name = "departamentos")
	public List<Departamento> listarDepartamentos() {
		return this.departamentoService.buscarTodos();
	}
}
