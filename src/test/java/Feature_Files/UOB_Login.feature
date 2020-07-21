Feature: Login to UOB

@test
Scenario Outline: Login to UOB Application
Given Navigate to UOB application
When User enter company id "<companyId>"
And User enter user id "<userId>"
And User enter password "<password>"
And User select language "<language>"
And User click on Access account button
Then Verify that user is able to see UOB home page


Examples:
|companyId|userId|password|langauge|
|CB10012|schaurasia|Welcome1!|English|