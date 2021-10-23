
Feature:Account Summary

  Background:
    Given : the user should be already logged in
    And : the user navigates to "Account Summary" tab

  Scenario: Account Summary page
    Then : page should have the title "Zero - Account Summary"


  Scenario: Account types
    Then :Account summary page should have to following	account	types:
      | Cash Accounts       |
      | Investment Accounts |
      | Credit Accounts     |
      | Loan Accounts       |

  Scenario: Credit Accounts table
    Then : Credit Accounts table must have following column names:
      | Account     |
      | Credit Card |
      | Balance     |