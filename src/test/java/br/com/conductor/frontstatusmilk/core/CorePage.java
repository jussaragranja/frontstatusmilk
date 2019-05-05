package br.com.conductor.frontstatusmilk.core;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * 
 * @author thiago.freire
 * @param <T>
 * Classe resposavel por gerencia todas as ações das paginas.
 */
public abstract class CorePage<T> {

	private static final int LOAD_TIMEOUT = 30;
	private String windowHandleJanelaInicial;
	
	public WebDriver driver;
	
	public CorePage() {
		this.driver = CoreDriver.getDriver();
		PageFactory.initElements(CoreDriver.getDriver(), this);
	}
	
	public T openPage(Class<T> clazz, String BASE_URL){
		T page = PageFactory.initElements(this.driver, clazz);
		this.driver.get(BASE_URL + getUrl());
//		this.driver.manage().window().maximize();
		return page;
	}

	public String getUrl() {
		return "";
	}
	
	public void preencherCampo(WebElement element, String keysToSend){
		try {
			element.clear();
			element.sendKeys(keysToSend);
		} catch (WebDriverException e) {
			fail("Nao foi possivel encontrar o elemento para preencher: "+element +". Pagina: " +this.driver.getTitle()+"\n "+e.getMessage());
			
		}
	}
	public void click(WebElement element){
		try {
			aguardarElementoVisivel(element);
			element.click();
		} catch (Exception e) {
			fail("Nao foi possivel encontrar o elemento para clicar: "+element +". Pagina: " +this.driver.getTitle()+"\n "+e.getMessage());
		}
	}
	
	public String getTextElement(WebElement element){
		if(!isVisibility(element)){
			fail("Erro ao buscar texto em tela. Elemento: ["+element+"] Favor verificar evidencia.");
		}
		return element.getText();
	}
	
	public String getValorAtributo(WebElement element){
		return element.getAttribute("value");
	}
	
	public void selectElementByVisibleText(WebElement element, String textVisible){
		try{
			new Select(element).selectByVisibleText(textVisible);
		}catch(NoSuchElementException e){
//			Utils.takeScreenshot(true);
			fail("Erro ao selecionar no elemento: ["+element.getTagName()+ "] com o o valor: "+textVisible);
		}
	}
	public void selectElementByVisibleValue(WebElement element, String valueVisibel){
		try{
			new Select(element).selectByValue(valueVisibel);
		}catch(NoSuchElementException e){
//			Utils.takeScreenshot(true);
			fail("Erro ao selecionar no elemento: ["+element.getTagName()+ "] com o o valor: "+valueVisibel);
		}
	}
	public void acceptAlert(){
		try {
	        Alert alert = this.driver.switchTo().alert();
	        alert.accept();
	    } catch (Exception e) {
	    	fail("Erro ao realizar a confirmacao do Alerta");
	    }
	}
	
	public String getAlert(){
		String alerta="";
		try {
			Alert alert = this.driver.switchTo().alert();
			alerta = alert.getText();
		} catch (Exception e) {
		}
		return alerta;
	}
	public void aguardarElementoVisivel(WebElement element){
		try {
			WebDriverWait driverWait = new WebDriverWait(this.driver, LOAD_TIMEOUT);
			driverWait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
//			Utils.takeScreenshot(true);
			fail("Tempo excedido para aguardar elemento: "+element);
		}
	}
	public void aguardarListElementoVisivel(List<WebElement> elements){
		try {
			WebDriverWait driverWait = new WebDriverWait(this.driver, LOAD_TIMEOUT);
			driverWait.until(ExpectedConditions.visibilityOfAllElements(elements));
		} catch (Exception e) {
//			Utils.takeScreenshot(true);
			fail("Tempo excedido para aguardar elemento: "+elements);
		}
	}
	public void aguardarElementoDesaparecer(By locator){
		try {
			WebDriverWait driverWait = new WebDriverWait(this.driver, LOAD_TIMEOUT);
			driverWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		} catch (Exception e) {
//			Utils.takeScreenshot(true);
			fail("Tempo excedido para aguardar elemento desaparecer da tela: "+locator);
		}
	}
	
	public void aguardarElementoVisivelComTexto(WebElement element, String text){
		try {
			WebDriverWait driverWait = new WebDriverWait(this.driver, LOAD_TIMEOUT);
			driverWait.until(ExpectedConditions.textToBePresentInElement(element, text.trim()));
		} catch (Exception e) {
//			Utils.takeScreenshot(true);
			fail("Tempo excedido para aguardar elemento: "+element);
		}
	}
	public void aguardarElementoClicado(WebElement element){
		try {
			WebDriverWait wait = new WebDriverWait(this.driver, LOAD_TIMEOUT);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
//			Utils.takeScreenshot(true);
			fail("Tempo excedido para aguardar elemento: "+element);
		}
	}

	public boolean isVisibility(WebElement elemento) {
		try{
			return elemento.isDisplayed();	
		}catch (NoSuchElementException e){
			return false;
		}
	}
	
	public boolean isVisibility(By locator) {
        try {
            WebElement element = this.driver.findElement(locator);
            element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
	
	public void clicarBotaoDireito(WebElement elemento){
		try {
			Actions action = new Actions(this.driver);
			action.contextClick(elemento).build().perform();
		} catch (Exception e) {
			Actions action = new Actions(this.driver);
			action.contextClick().build().perform();
		}
	}
	
	public void moverCursorPara(WebElement elemento){
		Actions action = new Actions(this.driver);
		action.moveToElement(elemento).build().perform();
	}
	
	public boolean existText(WebElement elemento, String texto) {
		aguardarElementoVisivel(elemento);
		return elemento.getText().contains(texto);
	}
	
	/**
	 * Seta para nova janela aberta
	 */
	public void alternarJanela() {
		windowHandleJanelaInicial = this.driver.getWindowHandle();
		Set<String> windowHandles = this.driver.getWindowHandles();
		for (String windowHandle : windowHandles) {
			if (!windowHandle.equals(windowHandleJanelaInicial)) {
				this.driver.switchTo().window(windowHandle);
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
	
	/**
	 * @return Janela anterior
	 */
	public void retornarJanelaOriginal(){
		this.driver.switchTo().window(getWindowHandleJanelaInicial());
	}
	
	public void alertaSaidaDoSistema() {
		Alert alert = this.driver.switchTo().alert();
		alert.accept();
	}
	
	public WebElement getElement(By by){
		return this.driver.findElement(by);
	}
	public void aguardaLoadingModuloSac(){
		WebDriverWait driverWait = new WebDriverWait(this.driver, LOAD_TIMEOUT);
		Boolean args = driverWait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                         WebElement button = driver.findElement(By.id("dialogLoading"));
                         String enabled = button.getAttribute("aria-hidden");
                         if(enabled.equals("true")) 
                        	return true;
                         else
                            return false;
                    }
			});
		
		if(!args){
			fail("Elemento nao foi alterado");
		}
	}
	public void cleanElement(WebElement element){
		try {
			element.clear();
		} catch (Exception e) {
			fail("Erro ao limpar campo em tela. "+element);
		}
	}
	public String getTitle(){
		return this.driver.getTitle();
	}
}
