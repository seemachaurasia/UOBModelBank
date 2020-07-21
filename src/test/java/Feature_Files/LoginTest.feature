Feature: Google testing

  @test 
  Scenario Outline: Login Google application
    Given login to the google app
    When  Google home page open 
    Then verify google icon on the home page
    

    Examples: 
      | userName  | password    |
      | AU15062   | abc | 
      