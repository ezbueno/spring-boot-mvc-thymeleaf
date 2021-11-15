package com.buenoezandro.boot.web.conversor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.buenoezandro.boot.domain.Cargo;
import com.buenoezandro.boot.service.CargoService;

@Component
public class StringToCargoConverter implements Converter<String, Cargo> {

	private final CargoService cargoService;

	public StringToCargoConverter(CargoService cargoService) {
		this.cargoService = cargoService;
	}

	@Override
	public Cargo convert(String text) {
		if (text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return this.cargoService.buscarPorId(id);
	}
}
