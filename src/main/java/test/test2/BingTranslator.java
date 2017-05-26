package test.test2;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yaml.snakeyaml.Yaml;

public class BingTranslator {

	public static void search(WebDriver driver, String url, String txt)
	{
		driver.get(url);
		WebElement element = driver.findElement(By.cssSelector("input[name = 'q']"));
        element.sendKeys(txt);
        element.submit();
	}
	
	public static void accessLink(WebDriver driver)
	{
		WebDriverWait myWait = new WebDriverWait(driver, 10);
    	myWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Bing Translator")));
        driver.findElement(By.linkText("Bing Translator")).click();       
	}
	
	public static void enterText(WebDriver driver, String input)
	{
		WebDriverWait myWait = new WebDriverWait(driver, 10);
    	myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'srcTextarea')]")));
    	driver.findElement(By.xpath("//*[contains(@class, 'srcTextarea')]")).sendKeys(input);
	}
	
	public static void changeLanguage(WebDriver driver)
	{
        driver.findElement(By.xpath(".//*[@id='content']/div/div[2]/div[3]/div[1]/div[1]/div[1]/div/div[1]")).click();
        driver.findElement(By.cssSelector("div.col.translationContainer.destinationText td[value = 'nl']")).click();
        //driver.findElement(By.xpath(".//*[@id='content']/div/div[2]/div[3]/div[1]/div[1]/div[1]/table/tbody/tr[13]/td[1]")).click();         
	}
	
	public static void chkInput(WebDriver driver)
	{
        WebElement inputBox = driver.findElement(By.xpath("//*[contains(@class, 'srcTextarea')]"));
        String textInsideInputBox = inputBox.getAttribute("value");
        if(textInsideInputBox.isEmpty())
        {
           System.out.println("Input field is empty");
        }  
        else
        {
        	System.out.println("Input field is not empty");
        }
	}
	
	public static void chkOutput(WebDriver driver) throws InterruptedException
	{
		Thread.sleep(100);
		WebElement outputBox = driver.findElement(By.xpath("//*[@id='destText']"));
        String textInsideOutputBox = outputBox.getText();
        if(textInsideOutputBox.isEmpty())
        {
           System.out.println("Output field is empty");
        }  
        else
        {
        	System.out.println("Output field is not empty");
        }
	}
	
	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		
		//System.setProperty("webdriver.firefox.marionette", "C:\\GeckoDriver\\geckodriver.exe");
    	//WebDriver driver = new FirefoxDriver();
    	System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
    	WebDriver driver = new ChromeDriver();
    	
        /*InputStream input = new FileInputStream(new File("src/Bing.yml"));
    	Yaml yaml = new Yaml();
    	Object data = yaml.load(input);*/
    
        String url = "http://www.google.com";
        String input = "Poppy! How are you doing?";
        
    	search(driver, url, "Bing Translator");
        accessLink(driver);
        enterText(driver, input);
        changeLanguage(driver);
        chkInput(driver);
        chkOutput(driver);
        }
}
