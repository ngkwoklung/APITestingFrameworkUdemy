Feature: Validating Place API's
@AddPlace
  Scenario Outline: Verify if place is being succesfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "addPlaceAPI" with "POST" http request
    Then the API call is success with status code 200
    And "status" in response is "OK"
    And "scope" in response is "APP"
    And verfy place_Id created maps to "<name>" using "getPlaceAPI"

    Examples:
      | name    | language | address            |
      | AAHouse | English  | World cross center |
#      | BBHouse | Spanish  | Sea cross center   |

@DeletePlace
  Scenario: Verify if Delete Place functionality is working
    Given DeletePlace Payload
    When user calls "deletePlaceAPI" with "POST" http request
    Then the API call is success with status code 200
    And "status" in response is "OK"

