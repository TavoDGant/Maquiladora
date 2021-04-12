package com.tdgames.excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.tdgames.entity.Empleado;

public class ExcelHelper {

	public static String tipo = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] titulos = { "nombre", "apellido_paterno", "apellido_materno", "sueldo" };
	static String SHEET = "Empleado";
	
	public static boolean FormatoExcel(MultipartFile archivo) {
		if(!tipo.equals(archivo.getContentType())) {
			return false;
		}
			return true;
	}
	
	public static List<Empleado> excelEmpleados(InputStream inStream){
		try {
			Workbook workbook = new XSSFWorkbook(inStream);
			
			Sheet sheet = workbook.getSheet(SHEET);
			Iterator<Row> columnas = sheet.iterator();
			
			List<Empleado> empleados = new ArrayList<Empleado>();
			
			int numColumna = 0;
			while (columnas.hasNext()) {
				Row currentRow = columnas.next();
				
				//saltar los encabezados
				if(numColumna == 0) {
					numColumna++;
					continue;
				}
				
				Iterator<Cell> celdas = currentRow.iterator();
				
				Empleado empleado = new Empleado();
				
				int celdaIidx = 0;
				
				while (celdas.hasNext()) {
					Cell currentCell = celdas.next();
					
					switch (celdaIidx) {
					
					case 0: empleado.setId((int) currentCell.getNumericCellValue()); 
					 	break;
					 
					case 1:
						empleado.setNombre(currentCell.getStringCellValue());
						break;
						
					case 2:
						empleado.setApellido_paterno(currentCell.getStringCellValue());
						break;

					case 3:
						empleado.setApellido_materno(currentCell.getStringCellValue());
						break;
						
					case 4:
						empleado.setSueldo(currentCell.getNumericCellValue());
						break;
						
					default:
						break;
					}
					celdaIidx++;
				}
				empleados.add(empleado);
			}
			workbook.close();
			return empleados;
		} catch (IOException e) {
			throw new RuntimeException("Error al cargar el archivo " + e.getMessage());
		}
	}
}
