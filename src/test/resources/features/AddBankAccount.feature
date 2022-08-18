Feature: As a Xero User,
  I want to add an ANZ(NZ) bank account inside my xero organisation
  So that, I can manage my business successfully through Desktop UI interface


  @AddAccount
  Scenario Outline: Add <bankName> bank user account to Xero portal
    Given user is at xero company login page
    When user provides valid login credentials
    Then user redirects to xero company dashboard
    And user select the Bank accounts option from the Accounting tab
    And proceeds for adding his bank account information
    Then user choose his registered bank <bankName> from the list of popular available banks
    And enter his account details with following information
      | accountName   | accountType   | accountNumber   |
      | <accountName> | <accountType> | <accountNumber> |
    And user select the Continue button
    And choose to go upload form for activating the bank connection
    And logout from the portal


    Examples: We can add more bank name here
      | bankName      | accountName | accountType           | accountNumber |
      | ANZ (NZ)      | Saving      | Everyday (day-to-day) | 341234        |










