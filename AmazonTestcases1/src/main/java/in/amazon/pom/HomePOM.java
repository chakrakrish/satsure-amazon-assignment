package in.amazon.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePOM {
	@FindBy(id = "nav-hamburger-menu")
	private WebElement HamburgerMenuBtn;
	@FindBy (xpath = "//a[text()='Sign in']")
	private WebElement SigninLink;
	@FindBy(linkText = "Sign Out")
	private WebElement SignOutLink;
	public HomePOM(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	public WebElement getHamburgerMenuBtn() {
		return HamburgerMenuBtn;
	}
	public WebElement getSigninLink() {
		return SigninLink;
	}
	public WebElement getSignOutLink() {
		return SignOutLink;
	}

}
