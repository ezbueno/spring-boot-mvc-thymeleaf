package com.buenoezandro.boot.web.error;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class MyErrorView implements ErrorViewResolver {

	private static final String ERROR = "error";
	private static final String MESSAGE = "message";

	@Override
	public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> map) {
		ModelAndView model = new ModelAndView(ERROR);
		model.addObject("status", status.value());

		switch (status.value()) {
		case 404:
			model.addObject(ERROR, "Página não encontrada!");
			model.addObject(MESSAGE, "A URL para a página '" + map.get("path") + "' não existe!");
			break;
		case 500:
			model.addObject(ERROR, "Ocorreu um erro interno no servidor!");
			model.addObject(MESSAGE, "Ocorreu um erro inesperado, tente mais tarde!");
			break;
		default:
			model.addObject(ERROR, map.get(ERROR));
			model.addObject(MESSAGE, map.get(MESSAGE));
			break;
		}
		return model;
	}
}
