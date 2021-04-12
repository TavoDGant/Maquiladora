package com.tdgames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PostMapping("empleados/guardar")
	public @ResponseStatus String guardarEmpleado(@RequestBody Empleado empleado) {
		service.guardarEmpleados(empleado);
		return ""+HttpStatus.OK;
	}
	
	@DeleteMapping("empleados/eliminar/{id}")
	public @ResponseStatus void eliminarEmpleado(@PathVariable Integer id) {
		service.eliminarEmpleados(id);
	}
	
	@PutMapping("empleados/actualizar")
	public @ResponseStatus void actualizarEmpleado(@RequestBody Empleado empleado) {
		service.actualizarEmpleados(empleado);
	}
}
