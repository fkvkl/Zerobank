Feature:Login function

  Scenario: User should be	able to	login with valid credentials
    Given the user navigates to login page
    When the user enters the credentials: as username:"username" and password: "password"
    And the user approves security message
    Then the user should be able to login and the Account summary page should be displayed

  Scenario: User should be unable to login with invalid credentials
    Given the user navigates to login page
    When the user enters the credentials: as username:"user" and password: "password"
    Then the user should unable to login and the error	message	"Login and/or password are wrong." should be displayed

  Scenario: User should be unable to login with blank credentials
    Given the user navigates to login page
    When the user enters the credentials: as username:"" and password: ""
    Then the user should unable to login and the error	message	"Login and/or password are wrong." should be displayed


