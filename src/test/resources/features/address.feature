Scenario Outline: Filling out the new address form
Given user is logged in and is on the page with the form
When user fills out a form with data "<alias>" "<company>" "<vat>" "<address>" "<addresscomplement>" "<city>" "<zip>" "<phone>"
And user confirms form
Then the system closes the browser

Examples:
| alias | company | vat | address | addresscomplement | city | zip | phone |
| Tester | IT Company | 932932040 | 7 Fountains Rd | 2nd floor | Liverpool | L4 1YA | 419991282|