package testScripts;

import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.TestBase;
import pages.ArticlePage;
import pages.DeleteArticlePage;
import pages.LoginPage;

public class ArticleTest {
	WebDriver driver;
	LoginPage loginP;
	ArticlePage articleP;
	DeleteArticlePage deleteP;
	
	
	@BeforeTest
	public void setup() throws IOException {
			TestBase.initDriver();
			driver = TestBase.getDriver();
			TestBase.openURL("https://conduit-realworld-example-app.fly.dev/");
			loginP= new LoginPage(driver);
			articleP = new ArticlePage(driver);
			deleteP= new DeleteArticlePage(driver);
			}
	
	
  @Test (priority=1)
  public void LoginPageTest() {
	  loginP.launchLoginPage();
	  loginP.login("p.rajalakshmi1@tcs.com", "Password3");
	  loginP.verifyLoginSuccess();
	  Assert.assertTrue(loginP.verifyLoginSuccess(),"Your Feeds not shown");
  }
  
  @Test(priority=2)
  public void newArticleTest() throws NoSuchElementException{
	  articleP.launchArticle();
	  Assert.assertTrue(articleP.isArticlePageDisp(),"Article page not displayed");
	  articleP.enterArticleDetails("TESTRP14","JAVA","Testing Concepts - JAVA and Selenium","RP1");
	  articleP.publishArticle();
	  
	  if (articleP.checkErr())
	  {
		  articleP.enterArticleDetails("TESTRP13","JAVA","Testing Concepts - JAVA and Selenium","RP1");
		  articleP.publishArticle();
		  articleP.verifyHeader();
		  System.out.println("New Article Published Successfully - post updating the Title");
	  }
	  else {
		  articleP.verifyHeader();
		  System.out.println("New Article Published Successfully");
	  }
	  
  }
  
  @Test(priority=3)
  public void editArticle() throws NoSuchElementException {
	  articleP.editArticle();
	  articleP.enterArticleDetails("TESTRP16","JAVA","Testing Concepts - JAVA and Selenium","RP1");
	  articleP.updatetArticle();
	  if (articleP.checkErr())
	  {
		  articleP.enterArticleDetails("TESTRP7","JAVA","Testing Concepts - JAVA and Selenium","RP1");
		  articleP.updatetArticle();
		  articleP.verifyHeader();
		  System.out.println("Edit Article Published Successfully - post updating the Title");
	  }
	  else {
		    System.out.println("Edit Article Published successfully");
	  }
	  
  }
  
   @Test(priority=4)
   public void deleteArticleTest() {
	   deleteP.DeleteArtcile();
	   boolean deletedS = deleteP.isDeleted();
	   //System.out.println("Deleted: "+deletedS);
   }
}
