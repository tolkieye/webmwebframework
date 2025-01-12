package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import steps.BaseSteps;
import utils.ClassList;
import utils.SharedData;


import static constants.Constants.DEFAULT_MAX_ITERATION_COUNT;
import static constants.Constants.DEFAULT_MILLISECOND_WAIT_AMOUNT;

import static pages.web.AddMoneyPage.*;
import static pages.web.EditAccountPage.*;
import static pages.web.LoginPage.*;
import static pages.web.TransferMoneyPage.*;

public class CommonStepDefinitions {
    private final BaseSteps baseSteps = ClassList.getInstance().get(BaseSteps.class);
    protected Logger logger = LoggerFactory.getLogger(getClass());

    double addedAmount = 100000;

    @Given("Go to {string} address")
    public void goToUrl(String url) {
        logger.info("Entered. Parameters; key: {}", url);
        baseSteps.switchNewWindow();
        baseSteps.goToUrl(url);
        logger.info("Current Url: {}", baseSteps.getCurrentUrl());
        baseSteps.waitForPageToCompleteState();
    }

    @Given("Check if current URL contains the value {string}")
    public void checkURLContainsRepeat(String expectedURL) {
        logger.info("Entered. Parameters; expectedURL: {}", expectedURL);
        int loopCount = 0;
        String actualURL = baseSteps.getCurrentUrl();
        while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {

            if (actualURL != null && actualURL.contains(expectedURL)) {
                logger.info("Şuanki URL: " + expectedURL + " değerini içeriyor.");
                return;
            }
            loopCount++;
            waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
        }
        Assertions.fail(
                "Actual URL doesn't match the expected." + "Expected: " + expectedURL + ", Actual: "
                        + actualURL);
    }

    @Given("User wants to hover and click on the {string} category")
    public void test(String categoryName) {
        baseSteps.hoverOnTheElementAndClick(By.xpath(
                String.format("//*[text()='%s']", categoryName)
        ));
    }

    @And("Switch to new window")
    public void switchNewWindow() {
        logger.info("Entered.");
        baseSteps.switchNewWindow();
    }

    @Given("Accept Chrome alert popup")
    public void acceptChromeAlertPopup() {
        logger.info("Entered");
        baseSteps.acceptChromeAlertPopup();
    }


    @And("User waits {long} milliseconds")
    public void waitByMilliSeconds(long milliseconds) {
        logger.info("Entered. Parameters; milliseconds: {}", milliseconds);
        baseSteps.waitByMilliSeconds(milliseconds);
    }

    @And("Wait {int} seconds")
    public void waitBySeconds(int seconds) {
        logger.info("Entered. Parameters; seconds: {}", seconds);
        try {
            logger.info(seconds + " saniye bekleniyor.");
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Given("The account is successfully logged out")
    public void signOutAccount() {
        logger.info("Entered");
        waitBySeconds(2);
        baseSteps.clickElement(TRANSFER_MONEY_LOGOUT_TEXT.getLocator());
    }

    @Given("Check that the account has been successfully logged out")
    public void checkSuccessfullyLoggedOut() {
        logger.info("Entered.");
        baseSteps.checkIfElementExistLogCurrentText(TRANSFER_MONEY_LOGIN_BUTTON.getLocator());
    }

    @Given("User Checks Home Page")
    public void checkSuccessfullyLoggedIn() {
        logger.info("Entered.");
        baseSteps.checkIfElementExistLogCurrentText(TRANSFER_MONEY_LOGOUT_TEXT.getLocator());
    }


    @Given("Click if the warning message appears on the page")
    public void clickElementIfExistWarningMessage() {
        logger.info("Entered.");
        baseSteps.ifElementExistsClick(WARNING_CLOSE_BUTTON.getLocator(), 5);
    }

    @Given("Check if new account name changed")
    public void checkIfElementCurrentText() {
        logger.info("Entered.");
        baseSteps.checkIfElementExistLogCurrentText(ACCOUNT_NAME_TEXT.getLocator());
    }

    @When("Assert error message is displayed for negative amount")
    public void assertErrorMessageForNegativeAmount() {
        boolean isErrorDisplayed = baseSteps.isDisplayedBy(By.xpath("//div[contains(@class, 'error')]"));
        Assertions.assertTrue(isErrorDisplayed, "Error: No error message was displayed for the negative Amount value!");
        logger.info("Assertion passed: Error message displayed for negative Amount value.");
    }


    @When("Save the amount value")
    public void logAmountValue() {
        logger.info("Entered");
        baseSteps.clickElement(OPEN_MONEY_TRANSFER_BUTTON.getLocator());

        try {
            WebElement amountElement = baseSteps.findElement(By.xpath("//div[contains(text(), 'Amount')]/following-sibling::div"));
            String amountValue = amountElement.getText();
            logger.info("Amount value: {}", amountValue);

        } catch (Exception e) {
            logger.error("An error occurred while logging the Amount value.", e);
            throw new RuntimeException("The Amount value could not be retrieved.", e);
        }
    }

    @When("Verify the updated amount")
    public void verifyUpdatedAmount() {
        try {
            String initialAmountText = SharedData.getData(String.class, "initialAmount");
            double initialAmount = Double.parseDouble(initialAmountText.replace(",", ""));

            baseSteps.waitForPageToCompleteState();
            waitByMilliSeconds(3000);
            WebElement updatedAmountElement = baseSteps.findElement(By.xpath("//div[contains(text(), 'Amount')]/following-sibling::div"));
            String updatedAmountText = updatedAmountElement.getText();
            double updatedAmount = Double.parseDouble(updatedAmountText.replace(",", ""));

            double expectedAmount = initialAmount + addedAmount;

            Assertions.assertEquals(expectedAmount, updatedAmount, "The total Amount value is different from expected!");
            logger.info("The Amount value has been updated correctly. Expected: {}, Current: {}", expectedAmount, updatedAmount);

        } catch (Exception e) {
            logger.error("An error occurred while checking the Amount value.", e);
            throw new RuntimeException("Amount check failed.", e);
        }
    }

    @When("Verify error message is displayed for wrong card number")
    public void verifyErrorMessageForInvalidCardNumber() {
        try {
            boolean isErrorDisplayed = baseSteps.isDisplayedBy(By.xpath("//div[contains(@class, 'error')]"));
            Assertions.assertTrue(isErrorDisplayed, "Error: No error message was displayed despite entering an invalid Card Number!");
            logger.info("The error message was displayed correctly for the wrong Card Number.");

        } catch (Exception e) {
            logger.error("An error occurred while checking the wrong Card Number.", e);
            throw new RuntimeException("Invalid Card Number check failed.", e);
        }
    }
}
