Feature: search Wikipedia
 
  Scenario Outline:
    Given Enter search term '<searchTerm>'
    When Do search
    Then Single result is shown for '<searchTerm>'

    Examples:
      | searchTerm|
      | Cucumber  |
      | Cheese    |