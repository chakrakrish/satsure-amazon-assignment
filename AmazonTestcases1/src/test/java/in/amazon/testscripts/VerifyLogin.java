package in.amazon.testscripts;

import java.io.IOException;
import java.time.Duration;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import in.amazon.generics.BaseClass;
import in.amazon.generics.FileLibrary;
import in.amazon.pom.HomePOM;
import in.amazon.pom.SigninPOM;

public class VerifyLogin extends BaseClass{
	private void performLogin(String email, String password, boolean shouldPass) throws IOException, InterruptedException {
		FileLibrary f = new FileLibrary();
		String url = f.getProperty("url");

		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().deleteAllCookies();
		//driver.navigate().refresh();

		HomePOM home = new HomePOM(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		home.getHamburgerMenuBtn().click();
		WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(home.getSigninLink()));

		// Scroll to sign-in button
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", signInButton);
		signInButton.click();

		SigninPOM signin = new SigninPOM(driver);
		signin.getEmailTxtBox().sendKeys(email);
		signin.getContinueBtn().click();
		signin.getPasswordTxtBox().sendKeys(password);
		Thread.sleep(4000); //To mimic as human
		signin.getSigninBtn().click();

		// Verification
		wait.until(ExpectedConditions.urlContains("encoding"));
		String currentUrl = driver.getCurrentUrl();

		if (shouldPass) {
			Assert.assertTrue(currentUrl.contains("encoding"), "Login success with valid credentials!");
			home.getHamburgerMenuBtn().click();
			WebElement signOutButton = wait.until(ExpectedConditions.elementToBeClickable(home.getSignOutLink()));
			JavascriptExecutor j=(JavascriptExecutor)driver;
			j.executeScript("arguments[0].scrollIntoView(true);", signOutButton);
			signOutButton.click();
		} else {
			Assert.assertFalse(currentUrl.contains("encoding"), "Login failed with invalid credentials!");
		}
	}

	@Test(priority = 1, description = "Verify login with valid username and password")
	public void testValidLogin() throws Exception {
		performLogin(new FileLibrary().getExcelData("login", 1, 1), new FileLibrary().getExcelData("login", 1, 2), true);
	}

	@Test(priority = 2, description = "Verify login with valid username and invalid password")
	public void testInvalidLogin() throws Exception {
		performLogin(new FileLibrary().getExcelData("login", 2, 1), new FileLibrary().getExcelData("login", 2, 2), false);
	}

	@Test(priority = 3, description = "Verify login with invalid username and password")
	public void testInvalidLogin2() throws Exception {
		performLogin(new FileLibrary().getExcelData("login", 3, 1), new FileLibrary().getExcelData("login", 3, 2), false);
	}
}
