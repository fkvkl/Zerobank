Feature:Account activity page

  Background:
    Given : the user should be already logged in
    And : the user navigates to "Account Activity" tab

  Scenario: Account Activity page
    Then : page should have the title "Zero - Account Activity"

  Scenario: Account drop down default option
    Then : In the Account drop down default option should be "Savings"

  Scenario: Account drop down options
    Then : Account drop down should have the following options:
      | Savings     |
      | Checking    |
      | Loan        |
      | Credit Card |
      | Brokerage   |

  Scenario: Account drop down options
    Then : Transactions table should have following column names:
      | Date        |
      | Description |
      | Deposit     |
      | Withdrawal  |