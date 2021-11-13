package com.buenoezandro.boot.web.conversor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.buenoezandro.boot.domain.Departamento;
import com.buenoezandro.boot.service.DepartamentoService;

@Component
public class StringToDepartamentoConverter implements Converter<String, Departamento> {

	private final DepartamentoService departamentoService;
	
	public StringToDepartamentoConverter(DepartamentoService departamentoService) {
		this.departamentoService = departamentoService;
	}
	
	@Override
	public Departamento convert(String text) {
		if (text.isEmpty()) {
			return null;
		}		
		Long id = Long.valueOf(text);		
		return this.departamentoService.buscarPorId(id);
	}
}
