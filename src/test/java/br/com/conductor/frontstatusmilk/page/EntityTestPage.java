package br.com.conductor.frontstatusmilk.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.conductor.frontstatusmilk.core.CoreDriver;

public class EntityTestPage {
	
	protected WebDriver driver;
	public EntityTestPage() {
		this.driver = CoreDriver.getDriver();
		PageFactory.initElements(CoreDriver.getDriver(), this);
	}
	
	@FindBy(xpath = "//header[@class='ant-layout-header']//h2[text()='Welcome']")
	private WebElement labelWelcome;
	
	@FindBy(xpath = "//div[@class='welcome']//h1[text()='Welcome to ...']")
	private WebElement labelWelcomeTo;
	
	@FindBy(xpath = "//header[@class='ant-layout-header']//h2[text()='Monitoring']")
	private WebElement labelMonitoring;
	
	@FindBy(xpath = "//header[@class='ant-layout-header']//h2[text()='Integrations']")
	private WebElement labelIntegrations;
	
	@FindBy(xpath = "//button[@class='ant-btn ant-btn-primary']//span[text()='Monitoring']")
	private WebElement btnMonitoring;
	
	@FindBy(xpath = "//button[@class='ant-btn ant-btn-primary']//span[text()='Integrations']")
	private WebElement btnIntegrations;
	
	@FindBy(xpath = "//button[@class='ant-btn']//span[text()='Back']")
	private WebElement btnBack;
	
	@FindBy(xpath = "//button[@class='ant-btn ant-btn-primary']//span[text()='Save']")
	private WebElement btnSalvar;

	@FindBy(xpath = "//div[@class='ant-col']//h4[text()='You are in tests!']")
	private WebElement msgMonitoring;
	
	@FindBy(xpath = "//div[@class='ant-col']//h4[text()='You are in integrations!']")
	private WebElement msgIntegrations;
	

	
	public void clicarBotaoSalvar() {
		btnSalvar.click();
	}

}
