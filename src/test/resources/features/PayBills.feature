@test
Feature:Account Summary

  Background:
    Given : the user should be already logged in
    And : the user navigates to "Pay Bills" tab

  Scenario: Pay Bills page
    Then : page should have the title "Zero - Pay Bills"


  Scenario: successful	Pay	operation
    When : the user uses selected option as payee, selected option as Account, "10" as Amount, "20220101" as date and "random payment" as Description
    And : click the Pay button
    And : "The payment was successfully submitted." message should be displayed

  Scenario: Pay	operation without entering	the	amount
    When : the user uses selected option as payee, selected option as Account, "" as Amount, "20220101" as date and "random payment" as Description
    And : click the Pay button
    And : "Please fill out this field." alert should be displayed

  Scenario: Pay	operation without entering	the	date
    When : the user uses selected option as payee, selected option as Account, "10" as Amount, "" as date and "random payment" as Description
    And : click the Pay button
    And : "Please fill out this field." alert should be displayed

  Scenario: Pay	operation with entering alphabetical character as amount
    When : the user uses selected option as payee, selected option as Account, "ten" as Amount, "20220101" as date and "random payment" as Description
    And : click the Pay button
    And : "Please fill out this field." alert should be displayed

  Scenario: Pay	operation with entering special character as amount
    When : the user uses selected option as payee, selected option as Account, "*-" as Amount, "20220101" as date and "random payment" as Description
    And : click the Pay button
    And : "Please fill out this field." alert should be displayed

  Scenario: Pay	operation with entering alphabetical character as date
    When : the user uses selected option as payee, selected option as Account, "10" as Amount, "today" as date and "random payment" as Description
    And : click the Pay button
    And : "Please fill out this field." alert should be displayed