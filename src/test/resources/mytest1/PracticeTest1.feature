Feature: Test Amazon explore tour to apple store

  @test1
  Scenario: Explore the apple products in amazon
    Given Open the browser and login into amazon "https://www.amazon.in/"
    When User click on "Electronics" from dropdown menu and search for "iphone 13"
    And User search for "iphone 13" and validate all suggestions
    Then Type again "iPhone 13 128GB" and click on "128gb" variant
    And click on search product and validate new tab is opened
    Then Navigate to next tab and click on Visit the Apple Store link appears below Apple iPhone "13" ("128" GB) variant
    And Click on Apple Watch dropdown and select Apple Watch SE GPS + Cellular
    And Hover over the watch and verify quick look is displayed for the watch
    And Verify the newly opened modal is related to same product
