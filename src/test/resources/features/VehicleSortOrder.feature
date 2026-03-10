Feature: Custom Collectors

  Scenario: Find duplicate elements
    Given I have a list of characters "a, b, c, c, c, d, d"
    When I collect duplicate elements
    Then the result should contain "c" and "d"

  Scenario: Find duplicate elements in another list
    Given I have a list of characters "c, d, e, e, d"
    When I collect duplicate elements
    Then the result should contain "d" and "e"

  Scenario: Find consecutive duplicate elements
    Given I have a list of characters "a, b, c, c, c, d"
    When I collect consecutive duplicate elements
    Then the result should contain only "c"

  Scenario: Find consecutive duplicate elements in another list
    Given I have a list of characters "c, d, e, e, d"
    When I collect consecutive duplicate elements
    Then the result should contain only "e"

  Scenario: No duplicates found in unique list
    Given I have a list of characters "a, b, c"
    When I collect duplicate elements
    Then the result should be empty
