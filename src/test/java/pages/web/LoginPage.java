package pages.web;

import org.openqa.selenium.By;
import pages.commen.BaseElement;

public class LoginPage extends WebMainPage {

    public static final String PAGE_NAME = "Login Page";

    public static final BaseElement TRANSFER_MONEY_USERNAME_INPUT = new BaseElement(By.xpath("//input[@placeholder='Username']"));
    public static final BaseElement TRANSFER_MONEY_PASSWORD_INPUT = new BaseElement(By.xpath("//input[@placeholder='Password']"));
    public static final BaseElement TRANSFER_MONEY_LOGIN_BUTTON = new BaseElement(By.xpath("//div[text()='Login']//parent::div"));
    public static final BaseElement TRANSFER_MONEY_LOGOUT_TEXT = new BaseElement(By.xpath("//div[text()='Logout']//parent::div"));

    @Override
    public String getPageName() {
        return PAGE_NAME;
    }
}
