Feature:Verify the Get Posts calling the API.

  Scenario Outline: GET ALL posts from the API
    Given GET call "<url>" as the request
    Then all posts will be displayed with a response status code as "<statusCode>"

    Examples:
    | url    | statusCode |
    | /posts | 200        |

  Scenario Outline: GET  posts for single ID from the API
    Given GET call "<url>" as the request
    Then  posts for the respective ID will be displayed with a title name as "<title>"

    Examples:
      |  url      |   title         |
      | /posts/2  |   another title  |

  Scenario Outline: POST a new post with a new ID generated.
    Given GET call "<url>" as the request
    When POST call "<url>" as the request to create a new post
    Then the new post will be displayed with a response status code as "<statuscode>"

    Examples:
      |  url      | statuscode   |
      | /posts    |   201        |

  Scenario Outline: Delete a post
    Given DELETE call "<url>" as the request
    Then the post will be deleted with a response status code as "<statuscode>" and "<id>"

    Examples:
      |  url      | statuscode   | id |
      | /posts/8   |   200        |8  |



