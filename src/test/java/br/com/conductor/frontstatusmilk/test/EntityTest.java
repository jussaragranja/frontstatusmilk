package br.com.conductor.frontstatusmilk.test;

import org.junit.Test;

import br.com.conductor.frontstatusmilk.core.CoreBaseTest;
import br.com.conductor.frontstatusmilk.page.CreatePage;
import br.com.conductor.frontstatusmilk.page.MenuPage;
import br.com.conductor.frontstatusmilk.util.Property;


public class EntityTest extends CoreBaseTest {
	
	
	@Test
	public void realizarPrimeiroTest() {
		
		MenuPage menuPage = new MenuPage();
		
		driver.navigate().to(Property.URL);
		driver.manage().window().maximize();
		
		menuPage
				.expandirMenuMonitoring();
		
		menuPage.clickSubMenuNovoTest();
		
	}
	
	@Test
	public void deveCadastrarEntityTest() throws InterruptedException {
		
		driver.navigate().to(Property.URL);
		driver.manage().window().maximize();
		
		MenuPage menuPage = new MenuPage();
		menuPage.clickBotaoNovoTest();
		
		CreatePage createPage = new CreatePage();
		createPage.inputName();
		
	}
}
