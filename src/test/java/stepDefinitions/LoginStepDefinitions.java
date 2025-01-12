package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import steps.LoginSteps;
import utils.ClassList;

public class LoginStepDefinitions {


    public final LoginSteps loginSteps = ClassList.getInstance().get(LoginSteps.class);
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Given("User logins")
    public void performLogin(DataTable table) {
        logger.info("Entered.Parameters; table: \n{}", table);
        loginSteps.loginProcess(table);
    }
}
