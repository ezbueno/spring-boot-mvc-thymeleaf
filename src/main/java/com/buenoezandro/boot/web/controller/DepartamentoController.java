package com.buenoezandro.boot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.buenoezandro.boot.domain.Departamento;
import com.buenoezandro.boot.service.DepartamentoService;

@Controller
@RequestMapping(path = "/departamentos")
public class DepartamentoController {
		
	private final DepartamentoService departamentoService;
	
	private static final String SUCCESS = "success";
	private  static final String FAIL = "Departamento não removido. Possui cargo(s) vinculados(s).";
	private static final String INSERT = "Departamento cadastrado com sucesso.";
	private static final String EDIT = "Departamento editado com sucesso.";
	public static final String DELETE = "Departamento excluído com sucesso.";
	
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
	public String salvar(Departamento departamento, RedirectAttributes attributes) {
		this.departamentoService.salvar(departamento);
		attributes.addFlashAttribute(SUCCESS, INSERT);
		return "redirect:/departamentos/cadastrar";
	}
	
	@GetMapping(path = "/editar/{id}")
	public String preEditar(@PathVariable(value = "id") Long id, ModelMap model) {
		model.addAttribute("departamento", this.departamentoService.buscarPorId(id));
		return "/departamento/cadastro";
	}
	
	@PostMapping(path = "/editar")
	public String editar(Departamento departamento, RedirectAttributes attributes) {
		this.departamentoService.editar(departamento);
		attributes.addFlashAttribute(SUCCESS, EDIT);
		return "redirect:/departamentos/cadastrar";
	}
	
	@GetMapping(path = "/excluir/{id}")
	public String excluir(@PathVariable(value = "id") Long id, ModelMap model) {
		if (this.departamentoService.departamentoContemCargos(id)) {
			model.addAttribute("fail", FAIL);
		} else {
			this.departamentoService.excluir(id);
			model.addAttribute(SUCCESS, DELETE);
		}
		return this.listar(model);
	}
}
