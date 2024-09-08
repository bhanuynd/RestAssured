Feature: Validating place apis

@Addplace
Scenario Outline: verify if place is added correctly using Add place api
 Given Add place payload with "<name>" "<language>" "<address>"
 When User calls the "AddPlace" with "post" request
 Then the API call is success with status code 200
 And "status" in the response body is "OK"
 Then I verify the place id created to "<name>" using "getPlace"
 
 Examples:
 | name    | language | address                  |
 |Apple Inc| English  | World trade center       |
 |Google   | English  | Mountain View, California|
 
 @deleteplace
 Scenario: verify the delete place request
 
 Given Delete place payload
 When User calls the "removePlace" with "post" request
 Then the API call is success with status code 200
 And "status" in the response body is "OK"