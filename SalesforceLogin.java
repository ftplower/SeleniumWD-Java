package salesforcepackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import java.util.concurrent.TimeUnit;
import java.util.Random;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import java.awt.List;
import java.util.List;
//import org.openqa.selenium.JavascriptExecutor;
//import java.lang.*;


public class SalesforceLogin {

	private WebDriver driver;
	
	static String baseUrl = "https://pi.pardot.com";
	static String username = "pardot.applicant@pardot.com";
	static String password = "Applicant2012";
	
	Random rand = new Random(); 
	int randomNumber = rand.nextInt(999) + 699;

	public SalesforceLogin(WebDriver driver){
		this.driver=driver;
	}
	
	public void loginTest(String username, String password) {   
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl);
        driver.findElement(By.id("email_address")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.name("commit")).submit(); 
        
	}
        
	 public void createList() {
		 driver.findElement(By.id("mark-tog")).click();
		 driver.findElement(By.linkText("Segmentation")).click();
		 driver.findElement(By.linkText("Lists")).click();
		 driver.findElement(By.id("listxistx_link_create")).click();
		 
		 WebDriverWait wait=new WebDriverWait(driver, 10);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
		 driver.findElement(By.id("name")).clear();
		 driver.findElement(By.id("name")).sendKeys("TestListRandom" + randomNumber);
		 driver.findElement(By.id("save_information")).click();
		 
	      
	 }
	
	 public void createDuplicateList() {
		 driver.findElement(By.id("mark-tog")).click();
		 driver.findElement(By.linkText("Segmentation")).click();
		 driver.findElement(By.linkText("Lists")).click();
		 driver.findElement(By.id("listxistx_link_create")).click();
		 
		 WebDriverWait wait=new WebDriverWait(driver, 10);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
		 driver.findElement(By.id("name")).clear();
		 driver.findElement(By.id("name")).sendKeys("TestListRandom" + randomNumber);
		 driver.findElement(By.id("save_information")).click();
		 	
		 String text = driver.findElement(By.xpath("//span[@id='error_for_name']")).getText();
		 System.out.println(text);
		
		 String str = "Please input a unique value for this field";
		 int result = str.compareToIgnoreCase(text);	
		 if (result == 0)
		 	driver.findElement(By.linkText("Cancel")).click();
		
}
	 
	public void editCreatedList() {
		String search = "TestListRandom" + randomNumber;
		driver.findElement(By.linkText(search)).click();
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Split")));
		driver.findElement(By.linkText("Edit")).click();
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("TestListRandom" + randomNumber + "1");
		driver.findElement(By.id("save_information")).click();
		
	}
	
	public void createProspectList() {
		
		String email = randomNumber + "fplower@gmail.com";
		
		driver.findElement(By.id("pro-tog")).click();
		driver.findElement(By.linkText("Prospect List")).click();
		driver.findElement(By.id("pr_link_create")).click();
		 
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("default_field_3361")));
		
		driver.findElement(By.id("default_field_3361")).clear();
		driver.findElement(By.id("default_field_3361")).sendKeys("Fintan");
		driver.findElement(By.id("default_field_3371")).clear();
		driver.findElement(By.id("default_field_3371")).sendKeys("Plower");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(email);
		
		Select dropdown1 = new Select(driver.findElement(By.id("campaign_id")));
		dropdown1.selectByIndex(2);
		
		Select dropdown2 = new Select(driver.findElement(By.id("profile_id")));
		dropdown2.selectByIndex(2);
		
		driver.findElement(By.id("toggle-inputs-lists-")).click();
		
		driver.findElement(By.xpath("//*[@class='chzn-single chzn-default']")).click();
		
	    List<WebElement> element = driver.findElements(By.cssSelector("div[style*='block']"));
		
		for (int i = 0; i < element.size(); i++) {
	       String temp = element.get(i).getText();
	       //System.out.println(temp);
		
	   	String str = "TestListRandom" + randomNumber; 
		int result = str.compareToIgnoreCase(temp);	
		if (result == 0) { 
	    	   new Select(element.get(i)).selectByVisibleText("TestListRandom" + randomNumber);
	           break;
	        }
		}
		
		//driver.findElement(By.linkText("Create prospect")).submit();
		//Thread.sleep(10);
		driver.findElement(By.name("commit")).click();
		
		 //WebDriverWait wait2=new WebDriverWait(driver, 10);
		 //wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("mark-tog")));
	}
	
	
	public void sendEmailToMarketing() {
		
		driver.findElement(By.id("mark-tog")).click();
		driver.findElement(By.linkText("Emails")).click();
		driver.findElement(By.linkText("Send New List Email")).click();
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("TestEmailRandom" + randomNumber);
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		driver.findElement(By.cssSelector("h4.pull-left")).click();
		driver.findElement(By.id("select-asset")).click();
		driver.findElement(By.id("email_type_text_only")).click();
		driver.findElement(By.id("from_template")).click();
		driver.findElement(By.id("save_information")).click();
		driver.findElement(By.id("flow_sending")).click();
	}
	
	public void quitTest () {
		driver.quit();
	}
	
	public static void main(String[] args) {
		
		  SalesforceLogin login = new SalesforceLogin(new FirefoxDriver());
	      login.loginTest(username,password);
	      login.createList();
	      login.createDuplicateList();
	      login.editCreatedList();
	      login.createList();
	      login.createProspectList();
	      login.sendEmailToMarketing();
	      login.quitTest();
        
	}
	 
}
