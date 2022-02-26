Feature: ReqresAPI Demo

Scenario: Login - Success
Given Set the Login URL
When Enter the valid credentials
Then Verify the status code 200

Scenario: Login - Failure
Given Set the Login URL
When Enter the invalid Credentials
Then Verify the status code 400 for failure

Scenario: Get List of Users - Success
Given Send the endpoint URL for List users
Then Verify the user list status code 200

Scenario: Get List of Users - Failure
Given Send the endpoint URL for usersList
Then Verify the status code 400 for failure

Scenario: Update User - Success
Given Set the URL for Update user
When Send the valid details to be updated
Then Verify the status code 200

Scenario: Update User - Failure
Given Set the invalid URL for Update user
When Send the valid details to be updated
Then Verify the status code 404 for failure

Scenario: Delete User - Success
When Set the URL for Delete User
Then Verify the status code 204 for delete

Scenario: Delete User - Failure
When Set the invalid URL for Delete User
Then Verify the status code 404 for delete
