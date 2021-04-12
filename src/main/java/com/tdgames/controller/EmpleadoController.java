package com.tdgames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tdgames.entity.Empleado;
import com.tdgames.excel.ExcelHelper;
import com.tdgames.mensajesRespuesta.Mensajes;
import com.tdgames.service.EmpleadoService;

@CrossOrigin("http://localhost:8080")
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
	
	@PostMapping("empleados/upload")
	public ResponseEntity<Mensajes> subirEmpleados(@RequestParam("file") MultipartFile archivo){
		String mensaje = "";
		
		if(ExcelHelper.FormatoExcel(archivo)) {
			try {
				service.guardarExcel(archivo);
				mensaje = "Archivo subido exitosamente: " + archivo.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new Mensajes(mensaje));
			} catch (Exception e) {
				mensaje = "Error al subir el archivo: " + archivo.getOriginalFilename()+ " " +e.getMessage();
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Mensajes(mensaje));
			}
		}
		mensaje = "Agregue un archivo de Excel";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensajes(mensaje));
	}
	
	
}
