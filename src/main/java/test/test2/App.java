package test.test2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//System.setProperty("webdriver.firefox.marionette", "C:\\GeckoDriver\\geckodriver.exe");
    	//WebDriver driver = new FirefoxDriver();
    	System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
    	WebDriver driver = new ChromeDriver();
    
        // And now use this to visit Gmail
        driver.get("http://mail.google.com");

        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("identifier"));

        // Enter username and click next
        element.sendKeys("dummya481@gmail.com");
    	driver.findElement(By.xpath("//*[@id='identifierNext']/content")).click();

    	//Explicit wait
    	WebDriverWait myWait = new WebDriverWait(driver, 10);
    	myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
    	
    	//pwd = asdfghjkl@12345
    	driver.findElement(By.xpath("//*[@id='password']/div[1]/div/div[1]/input")).sendKeys ("asdfghjkl@12345");
    	driver.findElement(By.xpath("//*[@id='passwordNext']")).click();

    	myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'COMPOSE')]")));
    	driver.findElement(By.xpath("//div[contains(text(),'COMPOSE')]")).click(); 
    	
    	myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@name, 'to')]")));
    	
    	//Get initial number of unread mails 
    	String title = driver.getTitle();
    	System.out.println(title);
    	int a = title.indexOf('(');
    	int b = title.indexOf(')');
    	int ini = Integer.parseInt(title.substring(a+1, b));
    	System.out.println(ini);
    	
    	//Type content, send mail and refresh
    	driver.findElement(By.xpath("//*[contains(@name, 'to')]")).sendKeys("dummya481@gmail.com");
    	driver.findElement(By.xpath("//*[contains(@name, 'subjectbox')]")).sendKeys("Auto-generated mail");
    	driver.findElement(By.xpath("//*[contains(@class, 'Am Al editable LW-avf')]")).sendKeys("This mail has been generated automatically.");
    	driver.findElement(By.xpath("//*[contains(text(), 'Send')]")).click();
    	driver.findElement(By.xpath("//*[contains(@class, 'asf T-I-J3 J-J5-Ji')]")).click();
    	
    	//Get final number of unread mails
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	String title2 = driver.getTitle();
    	System.out.println(title2);
    	int a1 = title2.indexOf('(');
    	int b1 = title2.indexOf(')');
    	int fin = Integer.parseInt(title2.substring(a1+1, b1));
    	System.out.println(fin);
    	
    	if(fin-ini == 1)
    		System.out.println("Mail sent and receied at inbox");
    	else
    		System.out.println("Nope");
    }
}
