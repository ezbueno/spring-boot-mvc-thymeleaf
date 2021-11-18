package com.buenoezandro.boot.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.buenoezandro.boot.domain.Cargo;
import com.buenoezandro.boot.domain.Departamento;
import com.buenoezandro.boot.service.CargoService;
import com.buenoezandro.boot.service.DepartamentoService;

@Controller
@RequestMapping(path = "/cargos")
public class CargoController {

	private static final String SUCCESS = "success";
	private static final String FAIL    = "Cargo não removido. Possui cargo(s) vinculados(s).";
	private static final String INSERT  = "Cargo cadastrado com sucesso.";
	private static final String EDIT    = "Cargo atualizado com sucesso.";
	private static final String DELETE  = "Cargo excluído com sucesso.";
	
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
	public String listar(ModelMap model) {
		model.addAttribute("cargos", this.cargoService.buscarTodos());
		return "/cargo/lista";
	}

	@PostMapping(path = "/salvar")
	public String salvar(Cargo cargo, RedirectAttributes attributes) {
		this.cargoService.salvar(cargo);
		attributes.addFlashAttribute(SUCCESS, INSERT);
		return "redirect:/cargos/cadastrar";
	}
	
	@GetMapping(path = "/editar/{id}")
	public String preEditar(@PathVariable(value = "id") Long id, ModelMap model) {
		model.addAttribute("cargo", this.cargoService.buscarPorId(id));
		return "cargo/cadastro";
	}
	
	@PostMapping(path = "/editar")
	public String editar(Cargo cargo, RedirectAttributes attributes) {
		this.cargoService.editar(cargo);
		attributes.addFlashAttribute(SUCCESS, EDIT);
		return "redirect:/cargos/cadastrar";
	}
	
	@GetMapping(path = "/excluir/{id}")
	public String excluir(@PathVariable(value = "id") Long id, RedirectAttributes attributes) {
		if (this.cargoService.cargoContemFuncionarios(id)) {
			attributes.addFlashAttribute("fail", FAIL);
		} else {
			this.cargoService.excluir(id);
			attributes.addFlashAttribute(SUCCESS, DELETE);
		}
		return "redirect:/cargos/listar";
	}
	
	@ModelAttribute(name = "departamentos")
	public List<Departamento> listarDepartamentos() {
		return this.departamentoService.buscarTodos();
	}
}
