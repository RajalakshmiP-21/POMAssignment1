package pages;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeleteArticlePage {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath="(//button[contains(.,'Delete Article')])[1]") WebElement deleteBtn;
	@FindBy(xpath="//div[@class='article-preview']") WebElement deleteSucc;
	
	
	public DeleteArticlePage(WebDriver driver) {
		this.driver=driver;
		this.wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	public void DeleteArtcile() {
		deleteBtn.click();
         Alert confirmationAlert = wait.until(ExpectedConditions.alertIsPresent());
         String actualAlertText = confirmationAlert.getText();
        String expectedAlertText = "Want to delete the article?"; // Match expected text
        if (actualAlertText.equals(expectedAlertText)) {
            System.out.println("Alert message verified: " + actualAlertText);
        } else {
            System.out.println("Alert message mismatch!");
        }

        // Accept the confirmation (click "OK")
        confirmationAlert.accept();

        //Verify the action was successful (e.g., check for a confirmation message on the page)
        // Example: WebElement confirmationMessage = driver.findElement(By.id("successMessage"));

    }
        
	public boolean isDeleted()
	{
		    try {
		        return deleteSucc.getText().contains("Articles not available.");
		    } catch (NoSuchElementException e) {
		        return false;
		    }
		}

//		String deleteSuccess = deleteSucc.getAttribute("Articles not available.");
//		return deleteSuccess;
	}
	


