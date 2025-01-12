package steps;

import base.BaseTest;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import utils.SharedData;

import java.util.HashMap;
import java.util.Map;

import static pages.web.AddMoneyPage.*;
import static pages.web.EditAccountPage.*;
import static pages.web.TransferMoneyPage.*;

public class TransferMoneySteps extends BaseSteps {

    public enum TransferMoneyOption {
        TRANSFER_MONEY,
        ADD_MONEY,
        EDIT_ACCOUNT_NAME
    }


    private final HashMap<String, Runnable> hashMapLogOut = new HashMap<>();

    private HashMap<String, Runnable> hashMap = new HashMap<>();

    public void TransferMoneyHashMap(DataTable option) {
        for (Map<String, String> data : option.asMaps(String.class, String.class)) {

            logger.info("Entered. Parameters; table:{}\n", option);
            hashMap.put(String.valueOf(TransferMoneyOption.TRANSFER_MONEY), () -> transferMoneyProcess(option,
                    OPEN_MONEY_TRANSFER_BUTTON.getLocator(),
                    TRANSFER_MONEY_BUTTON.getLocator(),
                    SENDER_ACCOUNT_DROP.getLocator(),
                    data.get("Select Sender Account"),
                    RECEIVER_ACCOUNT_DROP.getLocator(),
                    data.get("Select Receiver Account"),
                    data.get("Entry Amount"),
                    TRANSFER_MONEY_AMOUNT_INPUT.getLocator(),
                    TRANSFER_MONEY_SEND_BUTTON.getLocator()));
            hashMap.put(String.valueOf(TransferMoneyOption.ADD_MONEY), () -> addMoneyProcess(option,
                    ADD_MONEY_BUTTON.getLocator(),
                    ADD_MONEY_CARD_NUMBER_INPUT.getLocator(),
                    data.get("Entry Card Number"),
                    ADD_MONEY_CARD_HOLDER_INPUT.getLocator(),
                    data.get("Entry Card Holder"),
                    ADD_MONEY_EXPIRY_DATE_INPUT.getLocator(),
                    data.get("Entry Expiry Date"),
                    ADD_MONEY_CVV_INPUT.getLocator(),
                    data.get("Entry Cvv"),
                    ADD_MONEY_AMOUNT_INPUT.getLocator(),
                    data.get("Entry Add Amount"),
                    ADD_BUTTON.getLocator()));
            hashMap.put(String.valueOf(TransferMoneyOption.EDIT_ACCOUNT_NAME), () -> editAccountProcess(option,
                    OPEN_MONEY_TRANSFER_BUTTON.getLocator(),
                    EDIT_ACCOUNT_BUTTON.getLocator(),
                    ACCOUNT_NAME_INPUT.getLocator(),
                    data.get("Current Account Name"),
                    UPDATE_BUTTON.getLocator()));
        }
    }

    public void transferMoneyOption(DataTable option) {
        logger.info("Entered. Parameters; option:{}", option);
        for (Map<String, String> data : option.asMaps(String.class, String.class)) {

            TransferMoneyHashMap(option);
            if (data.containsKey("Transactions My Account")) {
                Runnable locator = hashMap.get(data.get("Transactions My Account").toUpperCase());
                locator.run();
            } else {
                logger.info("Invalid Transactions My Account option");
            }
        }
    }

    public void transferMoneyProcess(DataTable option, By openMoneyTransferButton, By openTransferMoney, By selectSenderAccountButton, String selectSenderText, By selectReceiverAccountButton, String selectReceiverText, String amount, By amountInput, By transferMoneySendButton) {
        logger.info("Entered.");
        for (Map<String, String> data : option.asMaps(String.class, String.class)) {

            if (data.containsKey("Open Money Transfer")) {
                hoverAndClickElementByJs(openMoneyTransferButton);
                hoverAndClickElementByJs(openTransferMoney);
            }
            if (data.containsKey("Select Sender Account")) {
                selectDropDown(selectSenderText, selectSenderAccountButton);
            }
            if (data.containsKey("Select Receiver Account")) {
                selectDropDown(selectReceiverText, selectReceiverAccountButton);
                acceptChromeAlertPopup();
            }
            if (data.containsKey("Entry Amount")) {
                amountEntry(amount, amountInput);
            }
            if (data.containsKey("Check Amount")) {
                checkAmount(transferMoneySendButton);
            }
        }
    }

    public void addMoneyProcess(DataTable option, By addMoneyButton, By cardNumber, String cardNumberInput, By cardHolder, String cardHolderInput, By expiryDate, String expiryDateInput, By cvv, String cvvInput, By addAmount, String addAmountInput, By addButton) {
        logger.info("Entered.");
        for (Map<String, String> data : option.asMaps(String.class, String.class)) {

            if (data.containsKey("Add Money")) {
                hoverAndClickElementByJs(addMoneyButton);
            }
            if (data.containsKey("Entry Card Number")) {
                cardNumberEntry(cardNumberInput, cardNumber);
            }
            if (data.containsKey("Entry Card Holder")) {
                cardHolderEntry(cardHolderInput, cardHolder);
            }
            if (data.containsKey("Entry Expiry Date")) {
                cardExpiryDateEntry(expiryDateInput, expiryDate);
            }
            if (data.containsKey("Entry Cvv")) {
                cardCvvEntry(cvvInput, cvv);
            }
            if (data.containsKey("Entry Add Amount")) {
                amountEntry(addAmountInput, addAmount);
            }
            if (data.containsKey("Add Complete")) {
                hoverAndClickElementByJs(addButton);
            }
        }
    }

    public void editAccountProcess(DataTable option, By openMoneyTransferButton, By editAccountButton, By accountName, String accountNameInput, By updateButton) {
        logger.info("Entered.");
        for (Map<String, String> data : option.asMaps(String.class, String.class)) {

            if (data.containsKey("Edit Account Name")) {
                hoverAndClickElementByJs(openMoneyTransferButton);
                hoverAndClickElementByJs(editAccountButton);
            }
            if (data.containsKey("Current Account Name")) {
                accountNameEntry(accountNameInput, accountName);
            }
            if (data.containsKey("Edit Complete")) {
                hoverAndClickElementByJs(updateButton);
            }
        }
    }

    public void selectDropDown(String text, By by) {
        logger.info("Entered.");
        text = BaseTest.AccountName.getString(text + "_Selection").trim();
        selectTextFromDropDown(text, by);
    }

    public void hoverAndClickElementByJs(By by) {
        logger.info("Entered. Parameters; by: {}", by);
        hoverElement(findElement(by));
        waitByMilliSeconds(1000);
        javaScriptClicker(findElement(by));
        waitForPageToCompleteState();
        logger.info("{} clicked", by);
    }

    public void amountEntry(String amount, By by) {
        logger.info("Entered. Parameters; password: {}", amount);
        amount = BaseTest.AccountName.getString(amount + "_Input").trim();
        SharedData.saveData("amount", amount);
        sendKeys(by, amount);
    }

    public void checkAmount(By by) {
        logger.info("Entered.");
        clickElement(by);
        Assert.assertTrue("Amount transferred and the amount seen on the page are not correct", findElement(TRANSFERRED_MONEY_AMOUNT_TEXT.getLocator()).getText().equalsIgnoreCase(SharedData.getData(String.class, "amount")));
    }

    public void cardNumberEntry(String cardNumber, By by) {
        logger.info("Entered. Parameters; password: {}", cardNumber);
        cardNumber = BaseTest.AccountName.getString(cardNumber + "_Input").trim();
        waitByMilliSeconds(1000);
        sendKeys(by, cardNumber);
    }

    public void cardHolderEntry(String cardHolder, By by) {
        logger.info("Entered. Parameters; password: {}", cardHolder);
        cardHolder = BaseTest.AccountName.getString(cardHolder + "_Input").trim();
        sendKeys(by, cardHolder);
    }

    public void cardExpiryDateEntry(String expiryDate, By by) {
        logger.info("Entered. Parameters; password: {}", expiryDate);
        expiryDate = BaseTest.AccountName.getString(expiryDate + "_Input").trim();
        sendKeys(by, expiryDate);
    }

    public void cardCvvEntry(String cvv, By by) {
        logger.info("Entered. Parameters; password: {}", cvv);
        cvv = BaseTest.AccountName.getString(cvv + "_Input").trim();
        sendKeys(by, cvv);
    }

    public void accountNameEntry(String accountName, By by) {
        logger.info("Entered. Parameters; password: {}", accountName);
        accountName = BaseTest.AccountName.getString(accountName + "_Input").trim();
        sendKeys(by, accountName);
    }

}
