package com.tdgames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tdgames.entity.Empleado;
import com.tdgames.service.EmpleadoService;

@RestController
public class EmpleadoController {

	@Autowired
	EmpleadoService service;
	
	@GetMapping("/empleados")
	public @ResponseStatus List<Empleado> listaEmpleados(){
		return service.ListarEmpleados();
	} 
	
	@PostMapping("/guardar")
	public @ResponseStatus void guardarEmpleado(@RequestBody Empleado empleado) {
		service.guardarEmpleados(empleado);
	}
}
