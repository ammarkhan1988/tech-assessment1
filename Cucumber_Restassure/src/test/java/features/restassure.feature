Feature: Login Feature
  Verify if user is able to Login in to the site

  
Scenario: Find all available pets
When a user executes a search
Then I get a "200" response code
And the number of pets that have status "available" is "10"
And the number of dogs with the name "doggie" is "10"