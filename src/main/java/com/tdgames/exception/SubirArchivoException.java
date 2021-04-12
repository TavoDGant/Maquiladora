package com.tdgames.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tdgames.mensajesRespuesta.Mensajes;

@ControllerAdvice
public class SubirArchivoException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<Mensajes> maxPesoException(MaxUploadSizeExceededException exceededException){
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Mensajes("El archivo es demasiado grande"));
	}
}
