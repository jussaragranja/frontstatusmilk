package br.com.conductor.frontstatusmilk.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.conductor.frontstatusmilk.core.CoreBaseTest;
import br.com.conductor.frontstatusmilk.core.CoreDriver;

public class CreatePage extends CoreBaseTest {

	protected WebDriver driver;
	public CreatePage() {
		this.driver = CoreDriver.getDriver();
		PageFactory.initElements(CoreDriver.getDriver(), this);
	}

	@FindBy(id = "name")
	private WebElement inputName;
	
	@FindBy(id = "url")
	private WebElement inputUrl;
	
	@FindBy(xpath = "//*[@id="+"checkRate"+"]"+"/div/div")
	private WebElement inputCheckRate;
	
	@FindBy(id = "acceptedStatusCodes")
	private WebElement inputAcceptedStatusCode;

	@FindBy(xpath = "//li[text()='200: OK']")
	private WebElement liAcceptedStatusCode;
	
	@FindBy(xpath = "//*[@id="+"new-name-input"+"]")
	private WebElement vazio;

	@FindBy(id = "save-test-bt")
	private WebElement botaoSalvar;
			
	public void inputName() throws InterruptedException {
		preencherCampo(inputName, "TESTE1");
		preencherCampo(inputUrl, "mysite.com");
		new WebDriverWait(CoreDriver.getDriver(), 4000).until(ExpectedConditions.visibilityOf(inputAcceptedStatusCode));
		click(inputAcceptedStatusCode);
		aguardarElementoVisivel(liAcceptedStatusCode);
		click(liAcceptedStatusCode);
		aguardarElementoVisivel(vazio);
		click(vazio);
		click(botaoSalvar);
		
	}
	
	
	
}
