package br.com.conductor.frontstatusmilk.page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.conductor.frontstatusmilk.core.CoreDriver;
import br.com.conductor.frontstatusmilk.core.CorePage;


public class MenuPage extends CorePage<MenuPage>{

	protected WebDriver driver;
	public MenuPage() {
		this.driver = CoreDriver.getDriver();
		PageFactory.initElements(CoreDriver.getDriver(), this);
	}

	@FindBy(xpath = "//*[@id=\"root\"]/section/aside/div/ul/li[2]/div")
	private WebElement menuMonitoring;
	
	@FindBy(xpath = "//*[@id=\"2$Menu\"]/li[1]/div/span")
	private WebElement subMenuListarTests;
	
	@FindBy(xpath = "//*[@id=\"2$Menu\"]/li[2]/div/span")
	private WebElement subMenuNovoTest;
	
	@FindBy(xpath = "//*[@id=\"root\"]/section/section/main/div/div/div/div/div[2]/div/button[1]")
	private WebElement btnMonitoring;
	
	@FindBy(id = "bt-new-test")
	private WebElement btnNewTest;

	
	public void expandirMenuMonitoring() {
		aguardarElementoVisivel(menuMonitoring);
		click(menuMonitoring);
	}
	
	public void clickSubMenuListarTests() {
		aguardarElementoVisivel(subMenuListarTests);
		subMenuListarTests.click();
	}

	public void clickSubMenuNovoTest() {
		aguardarElementoVisivel(subMenuListarTests);
		subMenuNovoTest.click();
	}
	
	public void clickBotaoMonitoring() {
		aguardarElementoVisivel(btnMonitoring);
		btnMonitoring.click();
	}

	public void clickBotaoNovoTest() {
		aguardarElementoVisivel(btnNewTest);
		btnNewTest.click();
	}
	
	/*
	 * 
	 * @FindBy(xpath = "//div[@id='home']//h3[@class='head text-center']") private
	 * WebElement headPage;
	 * 
	 * @FindBy(id="btn_basic_example") private WebElement inicioteste;
	 * 
	 * @FindBy(xpath="//div[@id='basic']//h3[@class='head text-center']") private
	 * WebElement labelHeadBasicTest;
	 * 
	 * @FindBy(linkText="Simple Form Demo") private WebElement linkSimpleFormDemo;
	 * 
	 * 
	 * public void acessarTestSelenium(String text) { inicioteste.click();
	 * WebDriverWait driverWait = new WebDriverWait(CoreDriver.getDriver(), 30);
	 * driverWait.until(ExpectedConditions.visibilityOf(linkSimpleFormDemo));
	 * Assert.assertEquals(text, labelHeadBasicTest.getText());
	 * linkSimpleFormDemo.click(); }
	 */
}
