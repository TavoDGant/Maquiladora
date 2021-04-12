package com.tdgames.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.tdgames.entity.Empleado;
import com.tdgames.excel.ExcelHelper;
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

	public @ResponseStatus void eliminarEmpleados(Integer id) {
		empleadoRepository.deleteById(id);
	}

	public @ResponseStatus void actualizarEmpleados(Empleado empleado) {
		empleadoRepository.save(empleado);
	}
	
	public void guardarExcel(MultipartFile archivo) {
		try {
			List<Empleado> empleados = ExcelHelper.excelEmpleados(archivo.getInputStream());
			empleadoRepository.saveAll(empleados);
		} catch (IOException e) {
			throw new RuntimeException("Error al cargar archivo " + e.getMessage());
		}
	}
}
