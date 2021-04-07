package com.tdgames.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tdgames.entity.Empleado;
import com.tdgames.repository.EmpleadoRepository;

@Service
public class EmpleadoService {

	@Autowired
	EmpleadoRepository empleadoRepository;
	
	public @ResponseStatus List<Empleado> ListarEmpleados(){
		return empleadoRepository.findAll();
	}
	
	public @ResponseStatus void guardarEmpleados(Empleado empleado) {
		empleadoRepository.save(empleado);
	}
}
