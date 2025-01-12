package steps;

import base.BaseTest;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import pages.web.LoginPage;

import java.util.HashMap;
import java.util.Map;

public class LoginSteps extends BaseSteps {


    private final HashMap<String, Runnable> hashMapLogOut = new HashMap<>();
    private HashMap<String, Runnable> hashMap = new HashMap<>();




    public void loginProcess(DataTable table) {
        logger.info("Entered. Parameters; table: {}", table);
        for (Map<String, String> data : table.asMaps(String.class, String.class)) {
            if (data.containsKey("UserName") && data.containsKey("Password")) {
                String userName = data.get("UserName");
                enterUsername(userName);
                String password = data.get("Password");
                enterPassword(password);
                clickLoginButton();
                Assert.assertTrue("Login validation successful", isDisplayedBy(LoginPage.TRANSFER_MONEY_LOGOUT_TEXT.getLocator()));
            }
        }
    }

    public void enterUsername(String userName) {
        logger.info("Entered. Parameters; userName : {}", userName);
        userName = BaseTest.LoginInfo.getString(userName + "_Input").trim();
        sendKeys(LoginPage.TRANSFER_MONEY_USERNAME_INPUT.getLocator(), userName);
    }

    public void enterPassword(String Password) {
        logger.info("Entered. Parameters; Password : {}", Password);
        Password = BaseTest.LoginInfo.getString(Password + "_Input").trim();
        sendKeys(LoginPage.TRANSFER_MONEY_PASSWORD_INPUT.getLocator(), Password);
    }

    public void clickLoginButton() {
        clickElement(LoginPage.TRANSFER_MONEY_LOGIN_BUTTON.getLocator());
    }


}
