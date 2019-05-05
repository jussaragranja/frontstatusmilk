package br.com.conductor.frontstatusmilk.page;

import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.conductor.frontstatusmilk.core.CoreDriver;


/**
 * @authors Jussara Souza
 * @date 5 de mai de 2019
 */
public abstract class BasePage<T> {
	
	private static final int LOAD_TIMEOUT = 30;
	private String windowHandleJanelaInicial;
	protected WebDriver driver;


	public BasePage() {
		this.driver = CoreDriver.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	public void click(WebElement element) {
		try {
			aguardarElementoVisivel(element);
			element.click();
		} catch (Exception e) {
		}
	}
	
	public void aguardarElementoVisivel(WebElement element) {
		try {
			WebDriverWait driverWait = new WebDriverWait(driver, LOAD_TIMEOUT);
			driverWait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
		}
	}
	
	public void preencherCampo(WebElement element, String keysToSend) {
		try {
			aguardarElementoVisivel(element);
			element.clear();
			element.sendKeys(keysToSend);
		} catch (WebDriverException e) {

		}
	}
	
	public WebElement selecionarComboBoxAleatorio(WebElement elemento) {
		
		Select comboBox = new Select(elemento);
		int randomIndex = new Random().nextInt(comboBox.getOptions().size());
		comboBox.selectByIndex(randomIndex);
		
		return elemento;
	}
	
	/**
	 * Retorna apenas linhas de uma tabela
	 */

	public List<WebElement> getLinhaTable(WebElement table){
		
		List<WebElement> tr = table.findElements(By.cssSelector("tr"));
		tr = table.findElements(By.cssSelector("tr"));	
		
		return tr;
	}
		
	public void alternarJanela() {
		windowHandleJanelaInicial = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		for (String windowHandle : windowHandles) {
			if (!windowHandle.equals(windowHandleJanelaInicial)) {
				driver.switchTo().window(windowHandle);
			}
		}
		setWindowHandleJanelaInicial(windowHandleJanelaInicial);
	}
	
	public String getWindowHandleJanelaInicial() {
		return windowHandleJanelaInicial;
	}

	public void setWindowHandleJanelaInicial(String windowHandleJanelaInicial) {
		this.windowHandleJanelaInicial = windowHandleJanelaInicial;
	}
	
	public void retonarJanelaOriginal() {
		driver.switchTo().window(getWindowHandleJanelaInicial());
	}
	
	public void aguardarListElementoVisivel(List<WebElement> elements) {
		try {
			WebDriverWait driverWait = new WebDriverWait(driver, LOAD_TIMEOUT);
			driverWait.until(ExpectedConditions.visibilityOfAllElements(elements));
		} catch (Exception e) {
		
		}
	}


}
