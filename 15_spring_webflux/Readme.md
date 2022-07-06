## Spring Webflux

You want to supply quotes to your consumers. As there are a possible infinite number of quotes
and new quotes become available continuously the consumers should be able to stream quotes.
In order to do this you have to complete some tasks!

### Task 1

Implement a REST resource 'quotes' and a repository to fetch the 
quotes from the database. Extend the existing skeleton classes
'QuoteReactiveController' and 'QuoteReactiveRepository'.
Using the HTTP GET for url 
http://localhost8080/quotes should get all users.

Verify your controller with an integration test by using `WebClient`
and `StepVerifier`. Extend the existing skeleton
'QuoteReactiveControllerIntegrationTest'.

### Task 2

Extend the 'QuoteReactiveController' and 'QuoteReactiveRepository'
to support pagination.

Verify the pagination with another integration test.