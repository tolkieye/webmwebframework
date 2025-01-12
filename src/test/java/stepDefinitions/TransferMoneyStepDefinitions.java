package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import steps.BaseSteps;
import steps.TransferMoneySteps;
import utils.ClassList;


public class TransferMoneyStepDefinitions {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    TransferMoneySteps transferMoneySteps = ClassList.getInstance().get(TransferMoneySteps.class);

    @Given("Select Transfer Money")
    public void transferMoneyOption(DataTable table) {
        logger.info("Entered.");
        transferMoneySteps.transferMoneyOption(table);
    }

}
