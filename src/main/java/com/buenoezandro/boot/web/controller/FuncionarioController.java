package com.buenoezandro.boot.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	private static final String FAIL    = "Funcionário não removido. Possui cargo(s) vinculados(s).";
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
	public String listar() {
		return "/funcionario/lista";
	}
	
	@PostMapping(path = "/salvar")
	public String salvar(Funcionario funcionario, RedirectAttributes attr) {
		this.funcionarioService.salvar(funcionario);
		attr.addFlashAttribute(SUCCESS, INSERT);
		return "redirect:/funcionarios/cadastrar";
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
