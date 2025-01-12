package pages.web;

import org.openqa.selenium.By;
import pages.commen.BaseElement;

public class EditAccountPage extends WebMainPage {

    public static final String PAGE_NAME = "Edit Account Page";

    public static final BaseElement EDIT_ACCOUNT_BUTTON = new BaseElement(By.xpath("//div[text()='Edit account']//parent::div"));
    public static final BaseElement ACCOUNT_NAME_INPUT = new BaseElement(By.xpath("//div[text()='Account name']//parent::div//input"));
    public static final BaseElement UPDATE_BUTTON = new BaseElement(By.xpath("//div[text()='UPDATE']//parent::div"));
    public static final BaseElement ACCOUNT_NAME_TEXT = new BaseElement(By.xpath("//div[text()='Account name']/following-sibling::div"));


    @Override
    public String getPageName() {
        return PAGE_NAME;
    }
}
