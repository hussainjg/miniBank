Feature: User login and receive JWT token

  Scenario: Login with valid credentials
    Given a user with username "testuser", password "secret123"
    When I send a POST request to "/api/auth/login"
    Then the response status should be 200
    And the response should contain a JWT token