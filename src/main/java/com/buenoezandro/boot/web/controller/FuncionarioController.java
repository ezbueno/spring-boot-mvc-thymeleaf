package com.buenoezandro.boot.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.buenoezandro.boot.domain.Cargo;
import com.buenoezandro.boot.domain.Funcionario;
import com.buenoezandro.boot.domain.UF;
import com.buenoezandro.boot.service.CargoService;
import com.buenoezandro.boot.service.FuncionarioService;

@Controller
@RequestMapping(path = "/funcionarios")
public class FuncionarioController {
	
	private static final String SUCCESS = "success";
	private static final String INSERT  = "Funcionário cadastrado com sucesso.";
	private static final String EDIT    = "Funcionário atualizado com sucesso.";
	private static final String DELETE  = "Funcionário excluído com sucesso.";
	
	private final FuncionarioService funcionarioService;
	private final CargoService cargoService;
	
	public FuncionarioController(FuncionarioService funcionarioService, CargoService cargoService) {
		this.funcionarioService = funcionarioService;
		this.cargoService = cargoService;
	}

	@GetMapping(path = "/cadastrar")
	public String cadastrar(Funcionario funcionario) {
		return "/funcionario/cadastro";
	}
	
	@GetMapping(path = "/listar")
	public String listar(ModelMap model) {
		model.addAttribute("funcionarios", this.funcionarioService.buscarTodos());
		return "/funcionario/lista";
	}
	
	@PostMapping(path = "/salvar")
	public String salvar(Funcionario funcionario, RedirectAttributes attr) {
		this.funcionarioService.salvar(funcionario);
		attr.addFlashAttribute(SUCCESS, INSERT);
		return "redirect:/funcionarios/cadastrar";
	}
	
	@GetMapping(path = "/editar/{id}")
	public String preEditar(@PathVariable(value = "id") Long id, ModelMap model) {
		model.addAttribute("funcionario", this.funcionarioService.buscarPorId(id));
		return "funcionario/cadastro";
	}
	
	@PostMapping(path = "/editar")
	public String editar(Funcionario funcionario, RedirectAttributes attr) {
		this.funcionarioService.editar(funcionario);
		attr.addFlashAttribute(SUCCESS, EDIT);
		return "redirect:/funcionarios/cadastrar";
	}
	
	@GetMapping(path = "/excluir/{id}")
	public String excluir(@PathVariable(value = "id") Long id, RedirectAttributes attr) {
		this.funcionarioService.excluir(id);
		attr.addFlashAttribute(SUCCESS, DELETE);
		return "redirect:/funcionarios/listar";
	}
	
	@GetMapping(path = "/buscar/nome")
	public String getPorNome(@RequestParam(value = "nome") String nome, ModelMap model) {
		model.addAttribute("funcionarios", this.funcionarioService.buscarPorNome(nome));
		return "funcionario/lista";
	}
	
	@ModelAttribute(name = "cargos")
	public List<Cargo> listarCargos() {
		return this.cargoService.buscarTodos();
	}
	
	@ModelAttribute(name = "ufs")
	public UF[] getUFs() {
		return UF.values();
	}
}
