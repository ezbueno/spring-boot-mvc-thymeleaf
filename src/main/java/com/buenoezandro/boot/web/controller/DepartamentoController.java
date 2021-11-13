package com.buenoezandro.boot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.buenoezandro.boot.domain.Departamento;
import com.buenoezandro.boot.service.DepartamentoService;

@Controller
@RequestMapping(path = "/departamentos")
public class DepartamentoController {
	
	private final DepartamentoService departamentoService;
	
	public DepartamentoController(DepartamentoService departamentoService) {
		this.departamentoService = departamentoService;
	}

	@GetMapping(path = "/cadastrar")
	public String cadastrar(Departamento departamento) {
		return "/departamento/cadastro";
	}
	
	@GetMapping(path = "/listar")
	public String listar(ModelMap model) {
		model.addAttribute("departamentos", this.departamentoService.buscarTodos());
		return "/departamento/lista";
	}
	
	@PostMapping(path = "/salvar")
	public String salvar(Departamento departamento) {
		this.departamentoService.salvar(departamento);
		return "redirect:/departamentos/cadastrar";
	}
	
	@GetMapping(path = "/editar/{id}")
	public String preEditar(@PathVariable(value = "id") Long id, ModelMap model) {
		model.addAttribute("departamento", this.departamentoService.buscarPorId(id));
		return "/departamento/cadastro";
	}
	
	@PostMapping(path = "/editar")
	public String editar(Departamento departamento) {
		this.departamentoService.editar(departamento);
		return "redirect:/departamentos/cadastrar";
	}
	
	@GetMapping(path = "/excluir/{id}")
	public String excluir(@PathVariable(value = "id") Long id, ModelMap model) {
		if (!this.departamentoService.departamentoContemCargos(id)) {
			this.departamentoService.excluir(id);
		}
		return this.listar(model);
	}
}
