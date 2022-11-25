Feature: Validating Place API's

  Scenario Outline: Verify if place is being succesfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "addPlaceAPI" with "Post" http request
    Then the API call is success with status code 200
    And "status" in response is "OK"
    And "scope" in response is "APP"

    Examples:
      | name    | language | address            |
      | AAHouse | English  | World cross center |
      | BBHouse | Spanish  | Sea cross center   |
