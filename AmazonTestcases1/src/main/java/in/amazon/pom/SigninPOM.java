package in.amazon.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SigninPOM {
	@FindBy(name = "email")
	private WebElement EmailTxtBox;
	@FindBy(id = "continue")
	private WebElement ContinueBtn;
	@FindBy (name = "password")
	private WebElement PasswordTxtBox;
	@FindBy(id = "signInSubmit")
	private WebElement SigninBtn;
	public SigninPOM(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	public WebElement getEmailTxtBox() {
		return EmailTxtBox;
	}
	public WebElement getContinueBtn() {
		return ContinueBtn;
	}
	public WebElement getPasswordTxtBox() {
		return PasswordTxtBox;
	}
	public WebElement getSigninBtn() {
		return SigninBtn;
	}

}
