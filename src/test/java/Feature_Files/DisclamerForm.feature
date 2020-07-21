Feature: Capture the fields

@test
Scenario Outline: Validating fields
Given Initialize the browser with chrome
And Navigate to "https://clerk.clevelandcountyok.com/web/user/disclaimer" Site
And Click on ACCEPT link 
And click on official Records for web search
And click on Docuemnt Type Web Search
And enter start date "<sdate>" and end date "<edate>"
When User enters search "<value>" and click on searh button
And view the docuemnts and download file in local 
Then Verify that user is able to see all the fields
And close browsers

Examples:
|sdate			|edate	              | value                  |
|02/02/2020	|current date|State Tax Lien, Release State Tax Lien, Federal Tax Lien, Release Federal Tax Lien|