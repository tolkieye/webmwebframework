package pages.web;

import org.openqa.selenium.By;
import pages.commen.BaseElement;

public class AddMoneyPage extends WebMainPage {

    public static final String PAGE_NAME = "Add Money Page";

    public static final BaseElement ADD_MONEY_BUTTON = new BaseElement(By.xpath("//div[text()='Add money']//parent::div"));
    public static final BaseElement ADD_MONEY_CARD_NUMBER_INPUT = new BaseElement(By.xpath("//div[text()='Card number']//parent::div//input"));
    public static final BaseElement ADD_MONEY_CARD_HOLDER_INPUT = new BaseElement(By.xpath("//div[text()='Card holder']//parent::div//input"));
    public static final BaseElement ADD_MONEY_EXPIRY_DATE_INPUT = new BaseElement(By.xpath("//div[text()='Expiry date']//parent::div//input"));
    public static final BaseElement ADD_MONEY_CVV_INPUT = new BaseElement(By.xpath("//div[text()='CVV']//parent::div//input"));
    public static final BaseElement ADD_MONEY_AMOUNT_INPUT = new BaseElement(By.xpath("//div[text()='Amount']//parent::div//input"));
    public static final BaseElement ADD_BUTTON = new BaseElement(By.xpath("//div[text()='Add']//parent::div"));
    public static final BaseElement WARNING_CLOSE_BUTTON = new BaseElement(By.xpath("//div[@class='css-146c3p1 r-lrvibr r-1loqt21']"));


    @Override
    public String getPageName() {
        return PAGE_NAME;
    }
}
