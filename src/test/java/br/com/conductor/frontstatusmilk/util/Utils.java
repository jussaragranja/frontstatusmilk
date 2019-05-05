package br.com.conductor.frontstatusmilk.util;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import br.com.conductor.frontstatusmilk.core.CoreDriver;




/**
 * @authors Jussara Souza
 * @date 5 de mai de 2019
 */
public abstract class Utils {




	public static String extrairStackTrace(Exception e) {
		StackTraceElement[] stack = e.getStackTrace();
		String exception = "";
		for (StackTraceElement s : stack) {
			exception = exception + s.toString() + "\n\t\t";
		}
		return exception;
	}

	
	/**
	 * Metodo para capturar screenshot
	 * @param driver 
	 * 
	 * @param fileName
	 *            - Nome do arquivo
	 */
	public static void takeScreenshot(WebDriver driver, String casoDeteste, String nomeEvidencia) {
		File scrFile = ((TakesScreenshot) CoreDriver.getDriver())
				.getScreenshotAs(OutputType.FILE);
		try {
			String pastaImages = "Evidencias/"+casoDeteste+"/"+nomeEvidencia+".jpg";
					
			String pastaResultado = "resultadoTest/" + pastaImages;
					
			File fileDestino = new File(pastaResultado);
			FileUtils.copyFile(scrFile,fileDestino,true);
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static Date getData(){
		Calendar cal = Calendar.getInstance();
		
		return cal.getTime();
	}
	
	public static String getDataAtual() {
		try {
			SimpleDateFormat formatDate = new SimpleDateFormat("ddMMyyyy");
			Calendar calendar = new GregorianCalendar();
			Date data = new Date();
			calendar.setTime(data);
			return formatDate.format(data);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Date conversorStringDate(String data) throws Exception {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date dataNova = formato.parse(data);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(dataNova);
		
		return cal.getTime();
	}
	
	public static String conversorStringDoubleString(String valor){
		double d = Double.parseDouble(valor);
		DecimalFormat df=new DecimalFormat("0.00");  
		String novoValor = df.format(d);
		return novoValor;
	} 
	
	public static String conversorDoubleString(double valor){
		DecimalFormat df=new DecimalFormat("0.00");  
		String novoValor = df.format(valor);
		return novoValor;
	} 
	
	public static Double conversorStringDouble(String valor){
		String novoValor = valor.replace(",", ".");
		return Double.parseDouble(novoValor);
	} 
}
