Feature:Web Feature

  Background:

    Given Setup Driver "chrome"
    And   Go to "https://catchylabs-webclient.testinium.com/signIn" address
    Given User logins
      | UserName | Password |
      | Username | Password |

  Scenario: web1-LoginScenario
    Given User Checks Home Page

  Scenario: web2-UpdateAccountName
    Given Select Transfer Money
      | Transactions My Account | Edit Account Name | Current Account Name | Edit Complete |
      | EDIT_ACCOUNT_NAME       | True              | CurrentAccountName   | True          |
    Given Check if new account name changed


  Scenario: web3-DepositMoney
    When Save the amount value
    Given Select Transfer Money
      | Transactions My Account | Add Money | Entry Card Number | Entry Card Holder | Entry Expiry Date | Entry Cvv | Entry Add Amount | Add Complete |
      | ADD_MONEY               | True      | CardNumber        | CardHolder        | ExpiryDate        | Cvv       | Amount           | True         |
    Given Click if the warning message appears on the page
    When Verify the updated amount

  Scenario: web4-UserLogout
    Given The account is successfully logged out
    Given User logs out

  Scenario: web5-AddMoneyWithInvalidCard
    When Save the amount value
    Given Select Transfer Money
      | Transactions My Account | Add Money | Entry Card Number | Entry Card Holder | Entry Expiry Date | Entry Cvv | Entry Add Amount | Add Complete |
      | ADD_MONEY               | True      | WrongCardNumber   | CardHolder        | ExpiryDate        | Cvv       | Amount           | True         |
    Given Click if the warning message appears on the page
    When Check wrong card number text




