package pages.web;

import org.openqa.selenium.By;
import pages.commen.BaseElement;

public class TransferMoneyPage extends WebMainPage {

    public static final String PAGE_NAME = "Transfer Money Page";
    public static final BaseElement OPEN_MONEY_TRANSFER_BUTTON = new BaseElement(By.xpath("//div[text()='Open Money Transfer']//parent::div"));

    public static final BaseElement TRANSFER_MONEY_BUTTON = new BaseElement(By.xpath("//div[text()='Transfer money']//parent::div"));
    public static final BaseElement SENDER_ACCOUNT_DROP = new BaseElement(By.xpath("(//div[@class='css-175oi2r r-1777fci']//select)[1]"));
    public static final BaseElement RECEIVER_ACCOUNT_DROP = new BaseElement(By.xpath("(//div[@class='css-175oi2r r-1777fci']//select)[2]"));
    public static final BaseElement TRANSFER_MONEY_AMOUNT_INPUT = new BaseElement(By.xpath("//input[contains(@inputmode,'numeric')]"));
    public static final BaseElement TRANSFER_MONEY_SEND_BUTTON = new BaseElement(By.xpath("//div[text()='Send']//parent::div"));
    public static final BaseElement TRANSFERRED_MONEY_AMOUNT_TEXT = new BaseElement(By.xpath("(//div[text()='Amount:']/following-sibling::div//div)[1]"));

    @Override
    public String getPageName() {
        return PAGE_NAME;
    }
}
