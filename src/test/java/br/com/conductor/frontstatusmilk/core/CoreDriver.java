package br.com.conductor.frontstatusmilk.core;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import br.com.conductor.frontstatusmilk.util.Browser;
import br.com.conductor.frontstatusmilk.util.ConversorTipos;
import br.com.conductor.frontstatusmilk.util.Property;



/**
 * Selenium WebDriver
 *
 * @author thiago.freire
 */
public class CoreDriver {

	static WebDriver driver;
	static String OS = System.getProperty("os.name").toLowerCase();
	static String OS_ARCH = System.getProperty("sun.arch.data.model").toLowerCase();
	static String phantomPathDriver;

	public static WebDriver getDriver() {
		if (driver == null) {
			try {
				if(Browser.CHROME.equals(Property.BROWSER_NAME)){
					ChromeOptions chromeOptions = new ChromeOptions();
					chromeOptions.addArguments("--start-maximized");// Maximiza o navegador
//					chromeOptions.addArguments("headless");
					System.setProperty("webdriver.chrome.driver",getFileDriverPathChrome().getAbsolutePath());
					driver = new ChromeDriver(chromeOptions);
				}else{
					if(Browser.FIREFOX.equals(Property.BROWSER_NAME)){
						FirefoxProfile firefoxProfile = new FirefoxProfile();
						System.setProperty("webdriver.gecko.driver",getFileDriverPathFirefox().getAbsolutePath());
						//Remove o debug do "Marionette"
						System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
						
						//Define a configuracao de proxy
						Proxy proxy = new Proxy();
						proxy.setProxyType(Proxy.ProxyType.AUTODETECT);
						FirefoxOptions firefoxOptions = new FirefoxOptions();
						firefoxOptions.setCapability("marionette", true);
						firefoxOptions.setCapability(CapabilityType.PROXY, proxy);
						firefoxOptions.setCapability("pdfjs.disabled", false);
						//Rodar sem abrir o navegador = false, com o navegador = true
						firefoxOptions.setHeadless(false);
						firefoxOptions.setProfile(getProfileFireFox(firefoxProfile));
						driver = new FirefoxDriver(firefoxOptions);
					}else {
						if (Browser.REMOTEFIREFOX.equals(Property.BROWSER_NAME)) {
							FirefoxOptions firefoxOptions = new FirefoxOptions();
							firefoxOptions.setCapability("marionette", true);
							firefoxOptions.setCapability("pdfjs.disabled", false);
							firefoxOptions.setHeadless(false);
							URL url = new URL("http://localhost:4444/wd/hub");
							driver = new RemoteWebDriver(url, firefoxOptions);
						} else if (Browser.REMOTECHROME.equals(Property.BROWSER_NAME)) {

							DesiredCapabilities capabilities = DesiredCapabilities.chrome();
							URL url = new URL("http://localhost:4444/wd/hub");
							driver = new RemoteWebDriver(url, capabilities);

						}
					}
					

				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return driver;
	}
	
	/*public static void resetDriver() {
		if(driver != null){
			driver.close();
		}
		driver = null;
	}*/
	
	/**
	 * 
	 * @return sistema Operacional Windows
	 */
	public static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}
	/**
	 * @return sistema Operacional Linux
	 */
	public static boolean isUnix() {
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS
				.indexOf("aix") > 0);
	}
	
	public static boolean isOS64bits() {
		if (ConversorTipos.check(OS_ARCH)) {
			return Integer.valueOf(OS_ARCH) == 64;
		}
		return false;
	}
	
	private static File getFileDriverPathChrome() throws Exception{
		File file = null;
			if (isWindows()) {
				file = new File(Property.CHROME_DRIVE_PATH);
			} else if (isUnix()) {
				file = new File(Property.CHROME_DRIVE_LINUX_PATH);
			} else
				throw new Exception("Sistema operacional nao compativel");
		return file;
	}
	private static File getFileDriverPathFirefox() throws Exception{
		File file = null;
			if (isWindows()) {
				file = new File(Property.CHROME_DRIVE_PATH);
			} else if (isUnix()) {
				file = new File(Property.FIREFOX_DRIVE_LINUX_PATH);
			} else
				throw new Exception("Sistema operacional nao compativel");
		return file;
	}
	private static FirefoxProfile getProfileFireFox(FirefoxProfile firefoxProfile) {
//		Alterar o perfil para aceitar certificado naoo confiaveis
		firefoxProfile.setAcceptUntrustedCertificates(true);
		firefoxProfile.setAssumeUntrustedCertificateIssuer(false);
//		Definida como false , portanto, nenhum popup exibido quando o download terminar.
		firefoxProfile.setPreference("browser.download.manager.showAlertOnComplete",false);
//		desabilita botao de imprimir
//		firefoxProfile.setPreference("dom.disable_beforeunload", true);
		firefoxProfile.setPreference("print.always_print_silent", true);
		firefoxProfile.setPreference("print.show_print_progress", false);

		/*
		0 - Direct connection (or) no proxy. 
		1 - Manual proxy configuration
		2 - Proxy auto-configuration (PAC).
		4 - Auto-detect proxy settings.
		5 - Use system proxy settings.
		 */
		firefoxProfile.setPreference("network.proxy.type", 0);
		
		
		firefoxProfile.setPreference("browser.download.folderList", 2);
		firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/zip, application/pdf, application/csv, application/vnd.ms-excel, application/aspx, application/x-pdf,application/octet-stream");
		
		
        //Set download location and file types
        firefoxProfile.setPreference("browser.download.folderList",0);
        firefoxProfile.setPreference("browser.download.manager.showWhenStarting",false);
        firefoxProfile.setPreference("browser.download.dir","C:\\selenium\\");
        firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/csv,application/pdf,application/csv,application/vnd.ms-excel,application/aspx,");

        firefoxProfile.setPreference("browser.download.manager.showAlertOnComplete",false);
        firefoxProfile.setPreference("browser.download.manager.showWhenStartinge",false);
        firefoxProfile.setPreference("browser.download.panel.shown",false);
        firefoxProfile.setPreference("browser.download.useToolkitUI",true);

        // Set this to true to disable the pdf opening
        firefoxProfile.setPreference("pdfjs.disabled", true);

		return firefoxProfile;
	}
}
