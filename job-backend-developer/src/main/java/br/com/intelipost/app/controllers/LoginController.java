package br.com.intelipost.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.intelipost.app.entity.Cliente;
import br.com.intelipost.app.repositorios.Clientes;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private Clientes clientes;
	
	@GetMapping
	public ModelAndView efetuarLogin() {
		
		List<Cliente> c = clientes.findAll();
		
		ModelAndView m = new ModelAndView("login");
		m.addObject("clientes", c);
		
		return m;
	}
}