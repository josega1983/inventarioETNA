package es.enaire.inventario.util;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es.enaire.inventario.dtos.FilaExcelDTO;
import es.enaire.inventario.dtos.InformeExcelDTO;

/**
 *
 * Clase de utilidades para la generación de un archivo excell
 */
public class UtilidadesExcel {
	/**
	 * Rango maximo por pagina den excel
	 */
	private static int RANGO = 65000;
	/**
	 * Metodo que nos escribe un archivo excell
	 * @return Un workbook con los datos del excel rellenos
	 * @param informeExcel con los datos del informe
	 * @param excelFilePath la ubicacion del excel
	 * @throws Exception Excepcion producida en la generacion del Excel
	 */
	public static Workbook writeExcel(InformeExcelDTO informeExcel) throws Exception {
		Workbook workbook = new HSSFWorkbook();
	    List<FilaExcelDTO> filas = informeExcel.getFilas();
	    if (filas != null && filas.size() >0) {
			Integer numHojas = calcularNumHojas(filas);
			for (int i = 1; i <= numHojas; i++) {
				Sheet sheet = workbook.createSheet("hoja " + i);
				writeHeader(sheet, informeExcel.getCabecera());
				int rangoSuperior = (i * RANGO > filas.size() ? filas.size(): i * RANGO);
				List<FilaExcelDTO> filasParciales = filas.subList((i - 1)* RANGO, rangoSuperior);
				int rowCount = 0;
				if (filasParciales != null && filasParciales.size() > 0) {
					for (FilaExcelDTO fila : filasParciales) {
						Row row = sheet.createRow(++rowCount);
						writeLine(fila, row);
					}
				}
			}
		}
	    else{
			Sheet sheet = workbook.createSheet("hoja 0");
			writeHeader(sheet, informeExcel.getCabecera());
	    }
		return workbook;
	}
	/**
	 * Metodo que nos calcula el numero de hojas del informe excel
	 * @param filas filas del informe excel
	 * @return el numero de hojas
	 */
	private static Integer calcularNumHojas(List<FilaExcelDTO> filas) {
		Integer numHojas= 0;
		if(filas != null && filas.size()>0){
			int resto = filas.size() % RANGO ;
			numHojas = filas.size() / RANGO;
			if(resto != 0){
				numHojas = (filas.size() / RANGO)+1;
			}
		}
		return numHojas;
	}
	/**
	 * Metodo que nos genera las filas del informe
	 * @param fila con los datos de la fila
	 * @param row la fila a rellenar
	 */
	private static void writeLine(FilaExcelDTO fila, Row row) {
		String[] columnas = fila.getFila();
		for (int i = 0; i < columnas.length; i++) {
		    Cell cell = row.createCell(i);
		    cell.setCellValue(columnas[i]);
		}


	}
	/**
	 * Metodo que nos crea la cabecera del informe
	 * @param sheet la hoja para poner la cabecera
	 * @param cabecera un array de string con los datos de la cabecera
	 */
	private static void writeHeader(Sheet sheet, String[] cabecera) {
	    CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
	    Font font = sheet.getWorkbook().createFont();
	    font.setFontHeightInPoints((short) 16);
	    cellStyle.setFont(font);
	    if(cabecera != null && cabecera.length >0){
	    	Row row = sheet.createRow(0);
	    	for (int i = 0; i < cabecera.length; i++) {
				Cell cell = row.createCell(i);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(cabecera[i]);
			}

	    }

	}
}
