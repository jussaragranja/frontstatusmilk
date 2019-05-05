package br.com.conductor.frontstatusmilk.core;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * @authors Jussara Souza
 * @date 5 de mai de 2019
 */
public abstract class CoreBaseTest {

	public CoreBaseTest() {
		this.driver = CoreDriver.getDriver();
		PageFactory.initElements(driver, this);
	}

	private static final int LOAD_TIMEOUT = 30;
	private String windowHandleJanelaInicial;
	protected WebDriver driver;
	
	/*
	 * Buscando o nome do proximo teste a ser executado
	 */
	@Rule
	public TestName nameTest = new TestName();
	
	
	/*
	 * metodo executado antes de cada caso de teste
	 */
	@After
	public void after(){
		if(driver != null){
//			driver.close();
			driver.quit();
		}
		driver = null;
	}
	
	
	/*
	 * Para cada caso de teste, ira instanciar um novo driver
	 */
	@Before
	public void before() throws MalformedURLException{
		driver = CoreDriver.getDriver();
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
	
   public void actionsClick(WebElement element) {
       try {
           aguardarElementoVisivel(element);
           Actions actions = new Actions(CoreDriver.getDriver());
           actions.click(element).build().perform();
       } catch (Exception e) {
       }
		
   }
}
